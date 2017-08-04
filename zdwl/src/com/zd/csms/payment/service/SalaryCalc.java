package com.zd.csms.payment.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.zd.core.ServiceSupport;
import com.zd.csms.attendance.dao.AttendanceDAOFactory;
import com.zd.csms.attendance.dao.IAttendanceDao;
import com.zd.csms.attendance.model.AttendanceBean;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.quartz.CreateAttendance;
import com.zd.csms.attendance.quartz.LeaveInfoBean;
import com.zd.csms.attendance.quartz.TranInfoBean;
import com.zd.csms.attendance.service.AttendanceService;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.payment.dao.IPaymentInfoDao;
import com.zd.csms.payment.dao.PaymentInfoDAOFactory;
import com.zd.csms.payment.model.PaymentInfoVO;
import com.zd.csms.payment.model.PaymentVO;
import com.zd.csms.planandreport.model.ReportBaseInfoBean;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.repository.dao.IRepositoryDAO;
import com.zd.csms.repository.dao.RepositoryDAOFactory;
import com.zd.csms.roster.dao.IRosterDAO;
import com.zd.csms.roster.dao.RosterDAOFactory;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.salary.constants.CityLevelConstants;
import com.zd.csms.salary.model.BasePay;
import com.zd.csms.salary.service.SalaryService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisorymanagement.dao.ILeaveApplyDAO;
import com.zd.csms.supervisorymanagement.dao.ITransferDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.LeaveApplyVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.service.LeaveApplyService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

public class SalaryCalc extends ServiceSupport implements Runnable {
	private AttendanceService attService = new AttendanceService();
	private IRepositoryDAO respDao = RepositoryDAOFactory.getRepositoryDAO();
	private IRosterDAO rosterDao = RosterDAOFactory.getRosterDAO();
	private SalaryService salaryService = new SalaryService();
	private DealerService dealerService = new DealerService();
	private RegionService regionService = new RegionService();
	private ILeaveApplyDAO leaveDao = SupervisoryManagementDAOFactory.getLeaveApplyDAO();
	private IAttendanceDao attdao = AttendanceDAOFactory.getAttendanceDao();
	private CreateAttendance createAtt = new CreateAttendance();
	private IPaymentInfoDao dao = PaymentInfoDAOFactory.getPaymentInfoDao();
	private ITransferDAO tranDao = SupervisoryManagementDAOFactory.getTransferDAO();
	private DealerSupervisorService dsService = new DealerSupervisorService();
	private LeaveApplyService leaveService = new LeaveApplyService();

	private static Log log = LogFactory.getLog(CreateAttendance.class);

	@Override
	public void run() {
		try {
			transactionBegin(leaveDao.getDataSourceName());
			BasePay bp = salaryService.getBasePay();
			Calendar nowDate = Calendar.getInstance();
			nowDate.add(Calendar.MONTH, -1);
			int year = nowDate.get(Calendar.YEAR);
			int month = nowDate.get(Calendar.MONTH) + 1;
			int dayCount = nowDate.getActualMaximum(Calendar.DAY_OF_MONTH);// 上个月一共多少天
			PaymentVO payment = new PaymentVO();
			try {
				payment.setId(com.zd.csms.util.Util.getID(PaymentVO.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			payment.setYear(year);
			payment.setMonth(month);
			payment.setState(0);
			dao.add(payment);

			AttendanceQueryVO query = new AttendanceQueryVO();
			query.setCurrentRole(1);
			query.setYear(year);
			query.setMonth(month);
			query.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
			List<AttendanceBean> attList = attService.findAttendanceList(query);// 全部考勤记录
			if (attList == null || attList.size() == 0) {
				return;
			}
			for (AttendanceBean att : attList) {
				SupervisorBaseInfoVO baseInfo = respDao.getSupervisorBaseInfoByRepositoryId(att.getRespId());// 基本信息
				if (baseInfo == null)
					continue;
				RosterVO roster = rosterDao.getRosterBySupervisorId(baseInfo.getId());// 花名册信息
				if (roster == null)
					continue;

				// 调动信息
				List<TransferRepositoryVO> transferList = tranDao.findTranRespListByRespId(att.getRespId());
				if (transferList == null || transferList.size() == 0)
					continue;
				// 当前所在的经销商
				TransferRepositoryVO currTran = transferList.get(transferList.size() - 1);
				
				DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
				dsQuery.setSupervisorId(att.getRespId());
				List<DealerSupervisorVO> dsList = dsService.searchDealerSupervisorList(dsQuery);
				
				
				List<LeaveApplyVO> leaveList = leaveDao.findLeaveReplaceListByUserId(att.getUserid(), year, month);// 此人这个月的请假信息
				Map<Integer, LeaveInfoBean> leaveMap = new HashMap<Integer, LeaveInfoBean>();
				// 统计请假
				createAtt.leaveStatistics(leaveList, leaveMap, month, dayCount, year);

				List<SignRecord> signList = attdao.findListByUserId(att.getUserid(), year, month);// 签到信息
				Map<Integer, SignRecord> signMap = new HashMap<Integer, SignRecord>();
				for (SignRecord sign : signList) {// 循环将签到集合装载为Map key为day
													// value为签到信息
					int signDay = Integer.parseInt(DateUtil.getStringFormatByDate(sign.getCreateDate(), "dd"));
					signMap.put(signDay, sign);
				}

				// 半转正计算
				Calendar c = Calendar.getInstance();
				c.setTime(baseInfo.getEntryTime());
				c.add(Calendar.MONTH, 3);
				int day = c.get(Calendar.DAY_OF_MONTH);// 转正日

				// 是否转正 1:转正 0:半转正 -1：未转正
				int isRegular = isRegular(year, month, baseInfo.getEntryTime());
				// 城市等级 1：特殊城市,2:一类城市,3:二类城市
				int cityLevel = getCityLevel(att.getProvinceName(), att.getCityName());
				// 实际出勤
				double actualAttendance = att.getActualAttendanceUpdate() > 0.0 ? att.getActualAttendanceUpdate()
						: att.getActualAttendance();
				// 应出勤
				double shouldAttendance = att.getShouldAttendance();

				List<TranInfoBean> tranList = null;
				if (StringUtil.isNotEmpty(att.getChangePostInfo())) {
					tranList = JSONArray.parseArray(att.getChangePostInfo(), TranInfoBean.class);
				}
				PaymentInfoVO paymentInfo = new PaymentInfoVO();
				try {
					paymentInfo.setId(com.zd.csms.util.Util.getID(PaymentInfoVO.class));
				} catch (Exception e) {
					e.printStackTrace();
				}
				paymentInfo.setPaymentId(payment.getId());
				paymentInfo.setStaffNo(att.getStaffNo());
				paymentInfo.setStaffName(att.getName());
				paymentInfo.setCardNo(baseInfo.getIdNumber());
				paymentInfo.setDealerNames(att.getDealerNames());
				paymentInfo.setProvinceId(att.getProvinceId());
				paymentInfo.setProvinceName(att.getProvinceName());
				paymentInfo.setCityId(att.getCityId());
				paymentInfo.setCityName(att.getCityName());
				paymentInfo.setCityType(cityLevel);
				paymentInfo.setIsFormal(isRegular);
				// paymentInfo.setIsFar(isFar);
				paymentInfo.setStationedPro(currTran.getSupervisorSource());
				paymentInfo.setActualAttendance(actualAttendance);
				paymentInfo.setShouldAttendance(shouldAttendance);
				paymentInfo.setIsFullTime(actualAttendance == shouldAttendance ? 1 : 0);
				paymentInfo.setEntryDate(baseInfo.getEntryTime());
				paymentInfo.setBankCardNo(roster.getPaycardNo());
				paymentInfo.setOpenBankName(roster.getOpenBank());

				// 基础工资Start
				calcBasePay(bp, dayCount, att, leaveMap, signMap, day, isRegular, cityLevel, actualAttendance,
						shouldAttendance, tranList, paymentInfo, currTran);
				// 基础工资END
				paymentInfo.setStationedPro(currTran.getSupervisorSource());
				paymentInfo.setBasicSalary(getBasePay(bp, (isRegular>=0?1:isRegular), cityLevel));
				
				//司龄Start
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal1.set(year, month-1, 1);
				cal2.setTime(baseInfo.getEntryTime());
				paymentInfo.setCompanyAge(
						((cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR))*12
						+(cal1.get(Calendar.MONTH)-cal2.get(Calendar.MONTH))));
				if(paymentInfo.getCompanyAge()>12){
					double companyAgePay = 0.0;
					int companyYear = paymentInfo.getCompanyAge()/12;
					int companyMonth = paymentInfo.getCompanyAge();
					for(int i=0;i<companyYear;i++){
						if(companyMonth>=12){
							companyAgePay+=i*50*12;
							companyMonth-=12;
						}else{
							companyAgePay+=i*50*companyMonth;
						}						
					}
					paymentInfo.setCompanyAgePay(companyAgePay);
				}else{
					paymentInfo.setCompanyAgePay(0.0);
				}
				//司龄End
				
				//援疆补助
				paymentInfo.setFarSubsidy(paymentInfo.getIsFar()==1?200.0:0.0);
				if(dsList!=null&&dsList.size()>1){
					paymentInfo.setManySubsidy(200.0*(dsList.size()-1));
				}
				
				if(paymentInfo.getSettleCost()==null&&currTran.getSupervisorSource()==2){
					boolean bool = true;
					for (TransferRepositoryVO trvo : transferList) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(trvo.getEntryTime());
						if(!(cal.get(Calendar.YEAR)==year&&cal.get(Calendar.MONTH)==month)){
							bool=false;
							break;
						}
					}
					if(bool){
						paymentInfo.setSettleCost(200.0);
					}
				}
				
				//替岗
				double replaceCost = leaveService.getReplaceDaysByRepositoryIdAndMonth(att.getRespId(), year, month)*80.0;
				paymentInfo.setReplaceCost(replaceCost);
				double shouldMoney = paymentInfo.getBasePay()
						+paymentInfo.getMealSubsidy()
						+paymentInfo.getPhoneSubsidy()
						+paymentInfo.getTrafficSubsidy()
						+paymentInfo.getHouseSubsidy()
						+paymentInfo.getManySubsidy()
						+paymentInfo.getFarSubsidy()
						+paymentInfo.getSettleCost()
						+paymentInfo.getReplaceCost();
				paymentInfo.setShouldMoney(shouldMoney);
				paymentInfo.setRevenue(getRevenue(paymentInfo.getShouldAttendance()));
				paymentInfo.setPraticleMoney(paymentInfo.getShouldMoney()-paymentInfo.getRevenue());
				dao.add(paymentInfo);

			}
			transactionCommit(leaveDao.getDataSourceName());
		} catch (Exception e) {
			transactionRollback(leaveDao.getDataSourceName());
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void calcBasePay(BasePay bp, int dayCount, AttendanceBean att, Map<Integer, LeaveInfoBean> leaveMap,
			Map<Integer, SignRecord> signMap, int day, int isRegular, int cityLevel, double actualAttendance,
			double shouldAttendance, List<TranInfoBean> tranList, PaymentInfoVO paymentInfo,
			TransferRepositoryVO currTran) {
		// 基础工资
		double basePay = 0.0;
		double foodSubsidies = 0.0;// 饭补
		double houseSubsidies = 0.0;// 房补
		double phoneBillSubsidies = 0.0;// 话费补助
		double trafficSubsidies = 0.0;// 交通补助
		int isFar=0;//是否远疆
		int isChangeHome=0;//是否有安家费 0：没有 1：有

		if (tranList == null) {// 未换过岗
			if (isRegular == 1 || isRegular == -1) {
				basePay = getBasePay(bp, isRegular, cityLevel) / shouldAttendance * actualAttendance;
			} else {
				double beforeattday = 0.0;// 转正前实际出勤
				double afterattday = 0.0;// 转正后实际出勤
				String[] actudays = att.getActuDay().split(",");
				for (int i = 0; i < actudays.length; i++) {
					if (Integer.parseInt(actudays[i]) <= day) {
						if (signMap.get(Integer.parseInt(actudays[i])) != null
								&& signMap.get(Integer.parseInt(actudays[i])).getIsAbsenteeism() != 1) {
							// 如果存在请假肯定是0.5天
							if (leaveMap.get(Integer.parseInt(actudays[i])) != null) {
								beforeattday += 0.5;
							} else {
								beforeattday += 1.0;
							}

						}
					} else {
						if (signMap.get(Integer.parseInt(actudays[i])) != null
								&& signMap.get(Integer.parseInt(actudays[i])).getIsAbsenteeism() != 1) {
							// 如果存在请假肯定是0.5天
							if (leaveMap.get(Integer.parseInt(actudays[i])) != null) {
								afterattday += 0.5;
							} else {
								afterattday += 1.0;
							}
						}
					}
				}
				basePay += getBasePay(bp, -1, cityLevel) / shouldAttendance * beforeattday;
				basePay += getBasePay(bp, 1, cityLevel) / shouldAttendance * afterattday;
			}

			Map<Integer, Double> subsidiesMap = calcSubsidies(bp, currTran, cityLevel);
			foodSubsidies = subsidiesMap.get(1) / shouldAttendance * actualAttendance;
			houseSubsidies = subsidiesMap.get(2) / shouldAttendance * actualAttendance;
			phoneBillSubsidies = subsidiesMap.get(3) / shouldAttendance * actualAttendance;
			trafficSubsidies = subsidiesMap.get(4) / shouldAttendance * actualAttendance;
			
			DealerVO currDealer = dealerService.get(currTran.getDealerId());
			int  provinceId = StringUtil.isNotEmpty(currDealer.getProvince())?Integer.parseInt(currDealer.getProvince()):-1;
			int  cityId = StringUtil.isNotEmpty(currDealer.getCity())?Integer.parseInt(currDealer.getCity()):-1;
			int  countyId = StringUtil.isNotEmpty(currDealer.getDistrict())?Integer.parseInt(currDealer.getDistrict()):-1;
			if(isBorderland(provinceId, cityId, countyId)){
				isFar=1;
			}
		} else {
			// 换岗详细计算
			for (TranInfoBean tranInfo : tranList) {
				DealerVO dealer = dealerService.get(tranInfo.getDealer());
				cityLevel = getCityLevel(regionService.load(Integer.parseInt(dealer.getProvince())).getName(),
						regionService.load(Integer.parseInt(dealer.getCity())).getName());
				if (isRegular == 1 || isRegular == -1) {// 转正或者不转正
					basePay += getBasePay(bp, isRegular, cityLevel) / shouldAttendance * tranInfo.getActualAttendance();
				} else {
					if (tranInfo.getStart() <= day && day <= tranInfo.getEnd()) {// 说明转正日在这个店任职的区间内
						double beforeattday = 0.0;// 转正前实际出勤
						double afterattday = 0.0;// 转正后实际出勤
						for (int i = tranInfo.getStart(); i <= tranInfo.getEnd(); i++) {
							if (i <= day) {
								if (signMap.get(i) != null && signMap.get(i).getIsAbsenteeism() != 1) {
									// 如果存在请假肯定是0.5天
									if (leaveMap.get(i) != null) {
										beforeattday += 0.5;
									} else {
										beforeattday += 1.0;
									}

								}
							} else {
								if (signMap.get(i) != null && signMap.get(i).getIsAbsenteeism() != 1) {
									// 如果存在请假肯定是0.5天
									if (leaveMap.get(i) != null) {
										afterattday += 0.5;
									} else {
										afterattday += 1.0;
									}
								}
							}
						}
						basePay += getBasePay(bp, -1, cityLevel) / shouldAttendance * beforeattday;
						basePay += getBasePay(bp, 1, cityLevel) / shouldAttendance * afterattday;
					} else {// 转正日不在这个期间内
						if (day < tranInfo.getStart()) {
							basePay += getBasePay(bp, -1, cityLevel) / shouldAttendance
									* tranInfo.getActualAttendance();
						} else {
							basePay += getBasePay(bp, 1, cityLevel) / shouldAttendance * tranInfo.getActualAttendance();
						}

					}
					basePay += getBasePay(bp, -1, cityLevel) * day;
					basePay += getBasePay(bp, 1, cityLevel) * (dayCount - day);
				}

				Map<Integer, Double> subsidiesMap = calcSubsidies(bp, tranInfo, cityLevel);
				foodSubsidies += subsidiesMap.get(1) / shouldAttendance * tranInfo.getActualAttendance();
				houseSubsidies += subsidiesMap.get(2) / shouldAttendance * tranInfo.getActualAttendance();
				phoneBillSubsidies += subsidiesMap.get(3) / shouldAttendance * tranInfo.getActualAttendance();
				trafficSubsidies += subsidiesMap.get(4) / shouldAttendance * tranInfo.getActualAttendance();
				
				if(15>=tranInfo.getStart()&&15<=tranInfo.getEnd()){
					DealerVO currDealer = dealerService.get(tranInfo.getDealer());
					int  provinceId = StringUtil.isNotEmpty(currDealer.getProvince())?Integer.parseInt(currDealer.getProvince()):-1;
					int  cityId = StringUtil.isNotEmpty(currDealer.getCity())?Integer.parseInt(currDealer.getCity()):-1;
					int  countyId = StringUtil.isNotEmpty(currDealer.getDistrict())?Integer.parseInt(currDealer.getDistrict()):-1;
					if(isBorderland(provinceId, cityId, countyId)){
						isFar=1;
					}
				}
				
				if(tranInfo.getSupervisorSource()==2){
					isChangeHome=1;//出现外派，说明有安家费
				}
			}
		}

		//
		paymentInfo.setBasePay(basePay);
		paymentInfo.setMealSubsidy(foodSubsidies);
		paymentInfo.setPhoneSubsidy(phoneBillSubsidies);
		paymentInfo.setTrafficSubsidy(trafficSubsidies);
		paymentInfo.setHouseSubsidy(houseSubsidies);
		paymentInfo.setIsFar(isFar);
		if(isChangeHome==1){
			paymentInfo.setSettleCost(200.0);
		}
		

	}

	/**
	 * 是否转正 1:转正 0:半转正 -1：未转正
	 * 
	 * @param year
	 * @param month
	 * @param entryTime
	 * @return
	 */
	private int isRegular(int year, int month, Date entryTime) {
		int state = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(entryTime);
		c.add(Calendar.MONTH, 3);
		int regularMonth = c.get(Calendar.MONTH) + 1;
		int regularYear = c.get(Calendar.YEAR);
		if (year > regularYear) {
			state = 1;
		} else if (year == regularYear) {
			if (month > regularMonth) {
				state = 1;
			} else if (month == regularMonth) {
				state = 0;
			} else {
				state = -1;
			}
		} else {
			state = -1;
		}
		return state;
	}

	/**
	 * 
	 * @param province
	 *            省
	 * @param city
	 *            市
	 * @return 1：特殊城市,2:一类城市,3:二类城市
	 */
	private int getCityLevel(String province, String city) {
		int state = 0;
		if (CityLevelConstants.especialCity.contains(city)) {
			state = 1;
		} else if (CityLevelConstants.firstCity.contains(city)) {
			state = 2;
		} else if (CityLevelConstants.firstProvince.contains(province)) {
			state = 2;
		} else {
			state = 3;
		}
		return state;
	}

	/**
	 * 
	 * @param isRegular
	 *            1:已转正，0：半转正，-1：未转正
	 * @param cityLevel
	 *            1：特殊城市,2:一类城市,3：二类城市；
	 * @return 基本工资
	 */
	public double getBasePay(BasePay bp, int isRegular, int cityLevel) {

		double basePay = 0;
		if (isRegular == 1) {
			if (cityLevel == 1) {
				basePay = bp.getZhuanzheng_teshu();
			} else if (cityLevel == 2) {
				basePay = bp.getZhuanzheng_yilei();
			} else if (cityLevel == 3) {
				basePay = bp.getZhuanzheng_erlei();
			}
		} else if (isRegular == -1) {
			if (cityLevel == 1) {
				basePay = bp.getShiyong_teshu();
			} else if (cityLevel == 2) {
				basePay = bp.getShiyong_yilei();
			} else if (cityLevel == 3) {
				basePay = bp.getShiyong_erlei();
			}
		}
		return basePay;
	}

	private Map<Integer, Double> calcSubsidies(BasePay bp, TransferRepositoryVO tran, int cityLevel) {
		double foodSubsidies = 0.0;// 饭补
		double houseSubsidies = 0.0;// 房补
		double phoneBillSubsidies = 0.0;// 话费补助
		double trafficSubsidies = 0.0;// 交通补助
		if (tran.getSupervisorSource() == 1) {// 属地
			if (cityLevel == 1) {
				foodSubsidies = bp.getFanbu_teshu_shudi();
				houseSubsidies = bp.getFangbu_teshu_shudi();
				phoneBillSubsidies = bp.getHuabu_teshu_shudi();
				trafficSubsidies = bp.getJiaotong_teshu_shudi();
			} else if (cityLevel == 2) {
				foodSubsidies = bp.getFanbu_yilei_shudi();
				houseSubsidies = bp.getFangbu_yilei_shudi();
				phoneBillSubsidies = bp.getHuabu_yilei_shudi();
				trafficSubsidies = bp.getJiaotong_yilei_shudi();
			} else if (cityLevel == 3) {
				foodSubsidies = bp.getFanbu_erlei_shudi();
				houseSubsidies = bp.getFangbu_erlei_shudi();
				phoneBillSubsidies = bp.getHuabu_erlei_shudi();
				trafficSubsidies = bp.getJiaotong_erlei_shudi();
			}
		} else if (tran.getSupervisorSource() == 2) {// 外派
			if (cityLevel == 1) {
				foodSubsidies = bp.getFanbu_teshu_waipai();
				houseSubsidies = bp.getFangbu_teshu_waipai();
				phoneBillSubsidies = bp.getHuabu_teshu_waipai();
				trafficSubsidies = bp.getJiaotong_teshu_waipai();
			} else if (cityLevel == 2) {
				foodSubsidies = bp.getFanbu_yilei_waipai();
				houseSubsidies = bp.getFangbu_yilei_waipai();
				phoneBillSubsidies = bp.getHuabu_yilei_waipai();
				trafficSubsidies = bp.getJiaotong_yilei_waipai();
			} else if (cityLevel == 3) {
				foodSubsidies = bp.getFanbu_erlei_waipai();
				houseSubsidies = bp.getFangbu_erlei_waipai();
				phoneBillSubsidies = bp.getHuabu_erlei_waipai();
				trafficSubsidies = bp.getJiaotong_erlei_waipai();
			}
		}

		Map<Integer, Double> map = new HashMap<Integer, Double>();
		map.put(1, foodSubsidies);
		map.put(2, houseSubsidies);
		map.put(3, phoneBillSubsidies);
		map.put(4, trafficSubsidies);
		return map;
	}

	private Map<Integer, Double> calcSubsidies(BasePay bp, TranInfoBean tran, int cityLevel) {
		TransferRepositoryVO tranvo = new TransferRepositoryVO();
		tranvo.setSupervisorSource(tran.getSupervisorSource());
		return calcSubsidies(bp, tranvo, cityLevel);
	}
	
	/**
	 * 判断是否远疆城市
	 * @param provinceId 经销商所在省ID
	 * @param cityId 经销商所在市ID
	 * @param countyId 经销商所在区/县ID
	 * @return true:是远疆城市;false:不是远疆城市
	 */
	public boolean isBorderland(int provinceId,int cityId,int countyId){
		boolean flag=false;
		RegionService regionService=new RegionService();
		String countyName=regionService.getNameById(countyId);
		String cityName=regionService.getNameById(cityId);
		String provinceName=regionService.getNameById(provinceId);
		if(CityLevelConstants.distantCounty.contains(countyName) || CityLevelConstants.distantCity.contains(countyName)){
			flag=true;
		}else if(CityLevelConstants.distantCounty.contains(cityName) || CityLevelConstants.distantCity.contains(cityName)){
			flag=true;
		}else if(CityLevelConstants.distantProvince.contains(provinceName)){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 税率计算
	 * @param money
	 * @return
	 */
	private double getRevenue(double money){
		double returnvalue = 0.0;
		if(money>8000){
			returnvalue = 1500*0.03+3000*0.1+(money-8000)*0.2;
		}else if(money>5000){
			returnvalue = 1500*0.03+(money-5000)*0.1;
		}else if(money>3500){
			returnvalue = (money-3500)*0.03;
		}
		
		return returnvalue;
	}

}
