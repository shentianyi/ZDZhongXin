package com.zd.csms.attendance.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.alibaba.fastjson.JSONArray;
import com.zd.core.ServiceSupport;
import com.zd.csms.attendance.dao.AttendanceDAOFactory;
import com.zd.csms.attendance.dao.IAttendanceDao;
import com.zd.csms.attendance.model.Attendance;
import com.zd.csms.attendance.model.AttendanceBean;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.attendance.model.AttendanceLegerBean;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.model.SignRecordBean;
import com.zd.csms.attendance.model.SignRecordCheckSp;
import com.zd.csms.attendance.model.SignRecordCheckSpAll;
import com.zd.csms.attendance.model.SignRecordCheckSpDays;
import com.zd.csms.attendance.quartz.TranInfoBean;
import com.zd.csms.attendance.querybean.AttendanceTimeQueryBean;
import com.zd.csms.attendance.querybean.LedgerQueryBean;
import com.zd.csms.attendance.querybean.LedgerQueryVO;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.payment.service.SalaryCalc;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class AttendanceService extends ServiceSupport {
	private IAttendanceDao dao = AttendanceDAOFactory.getAttendanceDao();

	/**
	 * 新增或修改考勤时间
	 * 
	 * @return
	 */
	public boolean addorupdateAttendanceTime(AttendanceTime att) {
		AttendanceTime vo = getAttendanceTime(att.getId());
		boolean flag = false;
		if (vo != null) {
			flag = dao.update(att);
		} else {
			flag = dao.add(att);
		}
		if (flag) {
			this.setExecuteMessage("修改成功！");
		} else {
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}

	public AttendanceTime getAttendanceTime(int id) {
		AttendanceTime vo = dao.get(AttendanceTime.class, id,
				new BeanPropertyRowMapper(AttendanceTime.class));
		return vo;
	}

	public SignRecord getSignRecordByUserId(Date date, int respId) {
		SignRecord vo = dao.getSignRecordByUserId(date, respId);
		return vo;
	}

	/**
	 * 根据当前用户查询所在经销商签到时间集合
	 */
	public List<AttendanceTimeQueryBean> getAttendanceTimeByUserId(int id) {
		List<AttendanceTimeQueryBean> attendanceTimeVOs = dao
				.getAttendanceTimeByUserId(id);
		return attendanceTimeVOs;
	}
	/**
	 * 根据当前用户查询所在经销商签到时间
	 */
	public AttendanceTime getAttendanceTimeByRepId(int repositoryid) {
		AttendanceTime AttendanceTime=null;
		List<AttendanceTime> attendanceTimeList=dao.getAttendanceTimeByRepId(repositoryid);
		if(attendanceTimeList!=null && attendanceTimeList.size()>0){
			for (AttendanceTime bean : attendanceTimeList) {
				if (null != bean.getAfternoonStart() && null != bean.getAfternoonStart() && 
						null != bean.getAfternoonStart() && null != bean.getAfternoonStart()) {
					AttendanceTime = bean;
					break;
				}
			}
		}
		return AttendanceTime;
	}
	public int addSignRecord(SignRecord bean) throws Exception {
		bean.setId(Util.getID(SignRecord.class));
		if (dao.add(bean)) {
			return bean.getId();
		}
		return 0;
	}

	public boolean update(SignRecord bean) throws Exception {
		return dao.update(bean);
	}
	public boolean update(SignRecordCheckSpAll bean) throws Exception {
		return dao.update(bean);
	}
	public SignRecordCheckSpAll getSignRecordCheckSpAllId( int id) throws Exception {
		SignRecordCheckSpAll vo=dao.get(SignRecordCheckSpAll.class, id, new BeanPropertyRowMapper(SignRecordCheckSpAll.class));
		return	vo;
	}
	/**
	 * 考勤表台账列表实体
	 * 
	 * @param ledgerQuery
	 * @param tools
	 * @return
	 */
	public List<LedgerQueryBean> findLedgerList(LedgerQueryVO ledgerQuery,
			IThumbPageTools tools) {
		return dao.findLedgerList(ledgerQuery, tools);
	}

	public Attendance getAttendanceById(int id) {
		Attendance vo = dao.get(Attendance.class, id,
				new BeanPropertyRowMapper(Attendance.class));
		return vo;
	}

	public List<AttendanceBean> findAttendanceList(AttendanceQueryVO query,
			IThumbPageTools tools) {
		List<AttendanceBean> beanList = dao.findAttendanceList(query, tools);
		for (AttendanceBean bean : beanList) {
			String changePostInfo = "";
			List<TranInfoBean> tranList = null;
			if (StringUtil.isNotEmpty(bean.getChangePostInfo())) {
				tranList = JSONArray.parseArray(bean.getChangePostInfo(),
						TranInfoBean.class);
			}
			if (tranList != null && tranList.size() > 0) {
				for (TranInfoBean tran : tranList) {
					String changePost = tran.getYear() + "." + tran.getMonth()
							+ "." + tran.getStart() + "-" + tran.getEnd() + " "
							+ tran.getDealerName() + " \n ";
					changePostInfo = changePostInfo + changePost;
				}
			}
			bean.setChangePostInfo(changePostInfo);
		}
		return beanList;
	}

	public boolean updateAttendanceInfoState(int id, int state) {
		boolean flag = dao.updateAttendanceInfoState(id, state);
		if (flag) {
			this.setExecuteMessage("审批成功！");
		} else {
			this.setExecuteMessage("审批失败！");
		}
		return flag;
	}

	public boolean updateAttendanceState(int currRole, int id) {
		boolean flag = false;
		/*
		 * Attendance Attendance=getAttendanceById(id);
		 * if(currRole==RoleConstants.SR.getCode()){
		 * currRole=Attendance.getNextApproval(); }
		 */
		if (currRole == RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE
				.getCode()) {
			// 修改考勤表状态为正在审批
			flag = dao.updateAttendanceState(id,
					ApprovalContant.APPROVAL_WAIT.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
			// 修改考勤表详情状态为正在审批
			flag = dao.updateAttendanceInfoStateByAttendanceId(id,
					ApprovalContant.APPROVAL_WAIT.getCode());
		} else if (currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR
				.getCode()) {
			List<AttendanceInfo> attendanceInfoNoPass = dao
					.getAttendanceInfoByState(id,
							ApprovalContant.APPROVAL_NOPASS.getCode());
			List<AttendanceInfo> attendanceInfoPass = dao
					.getAttendanceInfoByState(id,
							ApprovalContant.APPROVAL_WAIT.getCode());
			if (attendanceInfoPass != null && attendanceInfoPass.size() > 0) {
				for (AttendanceInfo infoPass : attendanceInfoPass) {
					dao.updateAttendanceInfoState(infoPass.getId(),
							ApprovalContant.APPROVAL_PASS.getCode());
				}
			}
			if (attendanceInfoNoPass != null && attendanceInfoNoPass.size() > 0) {
				flag = dao.updateAttendanceState(id,
						ApprovalContant.APPROVAL_NOPASS.getCode(), 0);
			} else {
				flag = dao.updateAttendanceState(id,
						ApprovalContant.APPROVAL_PASS.getCode(), 0);
				try {
					new Thread(new SalaryCalc()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (flag) {
			this.setExecuteMessage("提交成功！");
		} else {
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}

	public List<SignRecordBean> findAttendanceSignRecordByRepositoryId(
			int repositoryId, Date monthStartDay, Date monthEndDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(monthStartDay);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		AttendanceInfo info = dao.getAttendanceInfo(repositoryId, year, month);
		List<SignRecordBean> beanList = new ArrayList<SignRecordBean>();
		if (info != null && !(StringUtil.isBlank(info.getActuDay()))) {
			String actuDay = info.getActuDay();
            if (!actuDay.endsWith(",")) {
				actuDay += ",";
			}

			List<SignRecord> list = dao.findAttendanceSignRecordByRepositoryId(
					repositoryId, monthStartDay, monthEndDay);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (list != null && list.size() > 0) {
				for (SignRecord signRecord : list) {
					String todayDate = sdf.format(signRecord.getCreateDate());
					String day = todayDate.substring(9) + ",";
					if (day.startsWith("0")) {
						day = day.substring(1);
					}
					if (actuDay.contains(day)) {
						SignRecordBean bean = new SignRecordBean();
						BeanUtils.copyProperties(signRecord, bean);
						bean.setOldAttendanceName(signRecord.getOldAttendance());
						bean.setTodayDate(todayDate);
						beanList.add(bean);
					}
				}
			}
		}
		return beanList;
	}

	/**
	 * 考勤更新：批量更新某个用户的几天的签到记录
	 * 
	 * @param signRecordList
	 * @return
	 */
	public boolean updateAttendanceSignRecordList(
			List<SignRecordBean> signRecordList) {
		boolean flag = false;
		this.setExecuteMessage("修改失败！");
		if (signRecordList != null && signRecordList.size() > 0) {
			for (SignRecordBean bean : signRecordList) {
				flag = updateAttendanceSignRecord(bean);
			}

			if (flag) {
				SignRecordBean signRecordBean = signRecordList.get(0);
				SignRecord signRecord = dao.get(SignRecord.class,
						signRecordBean.getId(), new BeanPropertyRowMapper(
								SignRecord.class));
				if (signRecord == null) {
					return false;
				}
				int respId = signRecord.getRespId();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(signRecord.getCreateDate());
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				AttendanceInfo info = dao
						.getAttendanceInfo(respId, year, month);
				if (info == null) {
					// 此处要抛异常
					return false;
				}
				flag = updateAttendanceInfo(signRecordList, info);
			}
		}

		return flag;
	}

	/**
	 * 更新某一天的签到记录
	 * 
	 * @param bean
	 * @return
	 */
	private boolean updateAttendanceSignRecord(SignRecordBean bean) {
		if (bean != null) {
			bean.updateSIGN();
			return dao.updateAttendanceSignRecord(bean.getId(),
					bean.getIsLate(), bean.getIsEarly(),
					bean.getIsAbsenteeism(), bean.getIsNormal());
		}
		return false;
	}

	public List<AttendanceLegerBean> findAttendanceLegerList(
			AttendanceQueryVO query, IThumbPageTools tools) {
		List<AttendanceLegerBean> legerList = new ArrayList<AttendanceLegerBean>();
		List<AttendanceBean> beanList = findAttendanceList(query, tools);
		if (beanList != null && beanList.size() > 0) {
			for (AttendanceBean bean : beanList) {
				AttendanceLegerBean leger = new AttendanceLegerBean();
				BeanUtils.copyProperties(bean, leger);
				leger.setDate(bean.getYear() + "-" + bean.getMonth());
				Date MonthStartDay = new Date();
				Date MonthEndDay = new Date();
				GregorianCalendar gc = new GregorianCalendar(bean.getYear(),
						bean.getMonth() - 1, 1);
				MonthStartDay = gc.getTime();
				gc.set(Calendar.DAY_OF_MONTH,
						gc.getActualMaximum(Calendar.DAY_OF_MONTH));// 设置天
				gc.set(Calendar.HOUR_OF_DAY,
						gc.getActualMaximum(Calendar.HOUR_OF_DAY));// 设置小时
				gc.set(Calendar.MINUTE, gc.getActualMaximum(Calendar.MINUTE));// 分
				gc.set(Calendar.SECOND, gc.getActualMaximum(Calendar.SECOND));// 秒
				MonthEndDay = gc.getTime();
				List<SignRecordBean> signRecordList = findAttendanceSignRecordByRepositoryId(
						bean.getRespId(), MonthStartDay, MonthEndDay);
				if (signRecordList != null && signRecordList.size() > 0) {
					for (SignRecordBean signRecord : signRecordList) {
						Calendar c = Calendar.getInstance();
						c.setTime(signRecord.getCreateDate());
						int day = c.get(Calendar.DAY_OF_MONTH);
						switch (day) {
						case 1:
							leger.setOne(getSignRecordStatus(signRecord));
							break;
						case 2:
							leger.setTwo(getSignRecordStatus(signRecord));
							break;
						case 3:
							leger.setThree(getSignRecordStatus(signRecord));
							break;
						case 4:
							leger.setFour(getSignRecordStatus(signRecord));
							break;
						case 5:
							leger.setFive(getSignRecordStatus(signRecord));
							break;
						case 6:
							leger.setSix(getSignRecordStatus(signRecord));
							break;
						case 7:
							leger.setSeven(getSignRecordStatus(signRecord));
							break;
						case 8:
							leger.setEight(getSignRecordStatus(signRecord));
							break;
						case 9:
							leger.setNine(getSignRecordStatus(signRecord));
							break;
						case 10:
							leger.setTen(getSignRecordStatus(signRecord));
							break;
						case 11:
							leger.setEleven(getSignRecordStatus(signRecord));
							break;
						case 12:
							leger.setTwelve(getSignRecordStatus(signRecord));
							break;
						case 13:
							leger.setThirteen(getSignRecordStatus(signRecord));
							break;
						case 14:
							leger.setFourteen(getSignRecordStatus(signRecord));
							break;
						case 15:
							leger.setFifteen(getSignRecordStatus(signRecord));
							break;
						case 16:
							leger.setSixteen(getSignRecordStatus(signRecord));
							break;
						case 17:
							leger.setSeventeen(getSignRecordStatus(signRecord));
							break;
						case 18:
							leger.setEighteen(getSignRecordStatus(signRecord));
							break;
						case 19:
							leger.setNineteen(getSignRecordStatus(signRecord));
							break;
						case 20:
							leger.setTwenty(getSignRecordStatus(signRecord));
							break;
						case 21:
							leger.setTwentyOne(getSignRecordStatus(signRecord));
							break;
						case 22:
							leger.setTwentyTwo(getSignRecordStatus(signRecord));
							break;
						case 23:
							leger.setTwentyThree(getSignRecordStatus(signRecord));
							break;
						case 24:
							leger.setTwentyFour(getSignRecordStatus(signRecord));
							break;
						case 25:
							leger.setTwentyFive(getSignRecordStatus(signRecord));
							break;
						case 26:
							leger.setTwentySix(getSignRecordStatus(signRecord));
							break;
						case 27:
							leger.setTwentySeven(getSignRecordStatus(signRecord));
							break;
						case 28:
							leger.setTwentyEight(getSignRecordStatus(signRecord));
							break;
						case 29:
							leger.setTwentyNine(getSignRecordStatus(signRecord));
							break;
						case 30:
							leger.setThirty(getSignRecordStatus(signRecord));
							break;
						case 31:
							leger.setThirtyOne(getSignRecordStatus(signRecord));
							break;
						default:
							break;
						}
					}
				}
				legerList.add(leger);
			}
		}
		return legerList;
	}

	private String getSignRecordStatus(SignRecordBean signRecord) {
		String status = "";
		if (signRecord.getIsLate() == 1) {
			status += "迟到";
		}
		if (signRecord.getIsEarly() == 1) {
			status += "早退";
		}
		if (signRecord.getIsAbsenteeism() == 1) {
			status += "旷工";
		}
		if (signRecord.getIsNormal() == 1) {
			status = "✔";
		}
		return status;
	}

	/**
	 * 更新某个用户某个月的出勤信息
	 */
	private boolean updateAttendanceInfo(List<SignRecordBean> signRecordList,
			AttendanceInfo info) {
		if (signRecordList != null && signRecordList.size() > 0) {
			int absenteeismint = 0, lateDay = 0, earlyDay = 0;
			for (SignRecordBean bean : signRecordList) {

				if (bean.getIsAbsenteeism() > 0) {
					absenteeismint += 1;
					continue;
				}

				if (bean.getIsLate() > 0) {
					lateDay += 1;
				}

				if (bean.getIsEarly() > 0) {
					earlyDay += 1;
				}

			}

			info.updateAttendance(absenteeismint, lateDay, earlyDay);
			return dao.updateAttendanceInfo(info);

		}
		return false;
	}

	/**
	 * 考勤列表修改事假,病假,倒休
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean updateAttendance(int attendanceInfoId, String name,
			double value) {
		// 修改事假,病假,倒休暂时待定
		 return dao.updateAttendance(attendanceInfoId, name, value);
		//return false;
	}

	public List<AttendanceBean> findAttendanceList(AttendanceQueryVO query) {
		return dao.findAttendanceList(query);
	}
	
	/**
	 * @param date 日期
	 * @param isLate 是否迟到 0：否 1：是
	 * @param isEarly 是否早退 0：否 1：是
	 * @param isAbsenteeism 是否旷工 0：否 1：是
	 * @param isNormal 是否正常出勤 0：否 1：是
	 * @return
	 */
	public List<SignRecord> findSignRecordListDateAndStatus(Date date,int isLate,int isEarly,int isAbsenteeism,int isNormal,int respId){
		return dao.findSignRecordListDateAndStatus( date, isLate, isEarly, isAbsenteeism, isNormal,respId);
	}
	/**
	 * @param date 日期
	 * @param isLate 是否迟到 0：否 1：是
	 * @param isEarly 是否早退 0：否 1：是
	 * @param isAbsenteeism 是否旷工 0：否 1：是
	 * @param isNormal 是否正常出勤 0：否 1：是
	 * @return
	 */
	public List<SignRecord> findAbnormalSignRecordList(Date date,int respId){
		return dao.findAbnormalSignRecordList( date, respId);
	}
	public List<AttendanceTime> getAttendanceTimes(int dealerId, IThumbPageTools tools) {
		return dao.getAttendanceTimes(dealerId,tools);
	}

	public List<AttendanceBean> findAttendanceListToExt(AttendanceQueryVO query) {
		List<AttendanceBean> beanList = dao.findAttendanceList(query);
		for (AttendanceBean bean : beanList) {
			String changePostInfo = "";
			List<TranInfoBean> tranList = null;
			if (StringUtil.isNotEmpty(bean.getChangePostInfo())) {
				tranList = JSONArray.parseArray(bean.getChangePostInfo(),
						TranInfoBean.class);
			}
			if (tranList != null && tranList.size() > 0) {
				for (TranInfoBean tran : tranList) {
					String changePost = tran.getYear() + "." + tran.getMonth()
							+ "." + tran.getStart() + "-" + tran.getEnd() + " "
							+ tran.getDealerName() + " \n ";
					changePostInfo = changePostInfo + changePost;
				}
			}
			bean.setChangePostInfo(changePostInfo);
		}
		return beanList;
	}

	/*
	 * 每日考勤详情 -- 需求86
	*/
	public List<SignRecordCheckSpAll> SignRecordCheckSpList(SignRecordCheckSpAll query, IThumbPageTools tools) {
		List<SignRecordCheckSpAll> alls = new ArrayList<SignRecordCheckSpAll>();
		List<SignRecordCheckSpAll> legerList = new ArrayList<SignRecordCheckSpAll>();
		List<SignRecordCheckSpAll> respidList = findRespIdCS(query, tools);
		if (respidList!=null && respidList.size()>0) {
			for (int j = 0; j < respidList.size(); j++) {
				query.setRespId(respidList.get(j).getRespId());
				
				List<SignRecordCheckSpAll> beanList = findSignRecordRespIdAndDayCheckCS(query);
				List<SignRecordCheckSpAll> beanList1 = findSignRecordDealerByRespIdCS(query);
				SignRecordCheckSpAll days = new SignRecordCheckSpAll();
				
				for (SignRecordCheckSpAll vo: beanList1){
					days.setStaffNo(vo.getStaffNo());
					days.setName(vo.getName());
					days.setProvince(vo.getProvince());
					days.setProvinceName(vo.getProvinceName());
					days.setCity(vo.getCity());
					days.setCityName(vo.getCityName());
					days.setRespId(vo.getRespId());
					days.setDealersName(vo.getDealersName());
					days.setBanksName(vo.getBanksName());
					days.setState(vo.getState());
					days.setNextApproval(vo.getNextApproval());
					days.setUpdateDate(vo.getUpdateDate());
					days.setUpdateUserId(vo.getUpdateUserId());
				}	
				alls.add(days);
				if (beanList.size() > 0 && beanList!=null) {
					for (SignRecordCheckSpAll bean: beanList) {
						StringBuilder status = new StringBuilder();
						if (bean.getIsLate() >0) {
							status.append( "迟到" );
						}
						
						if(bean.getIsEarly()>0 ){
							if (bean.getIsLate() >0) {
								status = status.append(",");
							}
							status = status.append("早退");
						}
						
						if (bean.getIsAbsenteeism()>0 ) {
							if (bean.getIsLate() >0 || bean.getIsEarly()>0 ){
								status = status.append(",");
							}
							status = status.append("旷工");
						}
						
						if (bean.getIsNormal() >0 ) {
							if (bean.getIsLate() >0 || bean.getIsEarly()>0 || bean.getIsAbsenteeism()>0 ){
								status = status.append(",");
							}
							status = status.append("正常出勤");
						}
						String state = status.toString();
						switch (bean.getTime()) {
						case 1:
							days.getDays().setDay_one(state);
							break;
						case 2:
							days.getDays().setDay_two(state);
							break;
						case 3:
							days.getDays().setDay_three(state);
							break;
						case 4:
							days.getDays().setDay_four(state);
							break;
						case 5:
							days.getDays().setDay_five(state);
							break;
						case 6:
							days.getDays().setDay_six(state);
							break;
						case 7:
							days.getDays().setDay_seven(state);
							break;
						case 8:
							days.getDays().setDay_eight(state);
							break;
						case 9:
							days.getDays().setDay_nine(state);
							break;
						case 10:
							days.getDays().setDay_ten(state);
							break;
						case 11:
							days.getDays().setDay_Eleven(state);
							break;
						case 12:
							days.getDays().setDay_twelve(state);
							break;
						case 13:
							days.getDays().setDay_thirteen(state);
							break;
						case 14:
							days.getDays().setDay_fourteen(state);
							break;
						case 15:
							days.getDays().setDay_fifteen(state);
							break;
						case 16:
							days.getDays().setDay_sixteen(state);
							break;
						case 17:
							days.getDays().setDay_seventeen(state);
							break;
						case 18:
							days.getDays().setDay_eighteen(state);
							break;
						case 19:
							days.getDays().setDay_nineteen(state);
							break;
						case 20:
							days.getDays().setDay_twenty(state);
							break;
						case 21:
							days.getDays().setDay_twentyOne(state);
							break;
						case 22:
							days.getDays().setDay_twentyTwo(state);
							break;
						case 23:
							days.getDays().setDay_twentyThree(state);
							break;
						case 24:
							days.getDays().setDay_twentyFour(state);
							break;
						case 25:
							days.getDays().setDay_twentyFive(state);
							break;
						case 26:
							days.getDays().setDay_twentySix(state);
							break;
						case 27:
							days.getDays().setDay_twentySeven(state);
							break;
						case 28:
							days.getDays().setDay_twentyEight(state);
							break;
						case 29:
							days.getDays().setDay_twentyNine(state);
							break;
						case 30:
							days.getDays().setDay_thirty(state);
							break;
						case 31:
							days.getDays().setDay_thirtyOne(state);
							break;
						default:
							break;
						}
						
					}
					days.getDays().setDates("1");
				}else {
					days.getDays().setDates("0");
				}
				legerList.add(days);
			}
		}
		
		return alls;
	}


	//需求86 - 储备库id对应经销商信息
	private List<SignRecordCheckSpAll> findSignRecordDealerByRespIdCS(
			SignRecordCheckSpAll query) {
		return dao.findSignRecordDealerByRespIdCS(query);
	}
	//需求86 - 考勤情况
	private List<SignRecordCheckSpAll> findSignRecordRespIdAndDayCheckCS(
			SignRecordCheckSpAll query) {
		return dao.findSignRecordRespIdAndDayCheckCS(query);
	}
	//需求86 - 储备库id
	private List<SignRecordCheckSpAll> findRespIdCS(SignRecordCheckSpAll query,IThumbPageTools tools) {
		return dao.findRespIdCS(query,tools);
	}
	//需求86 - 详情
	public List<SignRecordCheckSpAll> searchSignRecordCheckSpListDetails(
			int repositoryId, int year, int month) {
		return dao.searchSignRecordCheckSpListDetails(repositoryId,year,month);
	}
	//需求68 - 导出
	public List<SignRecordCheckSpAll> ExtSignRecordCheckSpList(
			SignRecordCheckSpAll query) {
		List<SignRecordCheckSpAll> alls = new ArrayList<SignRecordCheckSpAll>();
		List<SignRecordCheckSpAll> legerList = new ArrayList<SignRecordCheckSpAll>();
		List<SignRecordCheckSpAll> respidList = ExtfindRespId(query);
		if (respidList!=null && respidList.size()>0) {
			for (int j = 0; j < respidList.size(); j++) {
				query.setRespId(respidList.get(j).getRespId());
				
				List<SignRecordCheckSpAll> beanList = findSignRecordRespIdAndDayCheckCS(query);
				List<SignRecordCheckSpAll> beanList1 = findSignRecordDealerByRespIdCS(query);
				SignRecordCheckSpAll days = new SignRecordCheckSpAll();
				
				for (SignRecordCheckSpAll vo: beanList1){
					days.setStaffNo(vo.getStaffNo());
					days.setName(vo.getName());
					days.setProvince(vo.getProvince());
					days.setProvinceName(vo.getProvinceName());
					days.setCity(vo.getCity());
					days.setCityName(vo.getCityName());
					days.setRespId(vo.getRespId());
					days.setDealersName(vo.getDealersName());
					days.setBanksName(vo.getBanksName());
					days.setState(vo.getState());
					days.setNextApproval(vo.getNextApproval());
					days.setUpdateDate(vo.getUpdateDate());
					days.setUpdateUserId(vo.getUpdateUserId());
				}	
				alls.add(days);
				if (beanList.size() > 0 && beanList!=null) {
					for (SignRecordCheckSpAll bean: beanList) {
						StringBuilder status = new StringBuilder();
						if (bean.getIsLate() >0) {
							status.append( "迟到" );
						}
						
						if(bean.getIsEarly()>0 ){
							if (bean.getIsLate() >0) {
								status = status.append(",");
							}
							status = status.append("早退");
						}
						
						if (bean.getIsAbsenteeism()>0 ) {
							if (bean.getIsLate() >0 || bean.getIsEarly()>0 ){
								status = status.append(",");
							}
							status = status.append("旷工");
						}
						
						if (bean.getIsNormal() >0 ) {
							if (bean.getIsLate() >0 || bean.getIsEarly()>0 || bean.getIsAbsenteeism()>0 ){
								status = status.append(",");
							}
							status = status.append("正常出勤");
						}
						String state = status.toString();
						switch (bean.getTime()) {
						case 1:
							days.getDays().setDay_one(state);
							break;
						case 2:
							days.getDays().setDay_two(state);
							break;
						case 3:
							days.getDays().setDay_three(state);
							break;
						case 4:
							days.getDays().setDay_four(state);
							break;
						case 5:
							days.getDays().setDay_five(state);
							break;
						case 6:
							days.getDays().setDay_six(state);
							break;
						case 7:
							days.getDays().setDay_seven(state);
							break;
						case 8:
							days.getDays().setDay_eight(state);
							break;
						case 9:
							days.getDays().setDay_nine(state);
							break;
						case 10:
							days.getDays().setDay_ten(state);
							break;
						case 11:
							days.getDays().setDay_Eleven(state);
							break;
						case 12:
							days.getDays().setDay_twelve(state);
							break;
						case 13:
							days.getDays().setDay_thirteen(state);
							break;
						case 14:
							days.getDays().setDay_fourteen(state);
							break;
						case 15:
							days.getDays().setDay_fifteen(state);
							break;
						case 16:
							days.getDays().setDay_sixteen(state);
							break;
						case 17:
							days.getDays().setDay_seventeen(state);
							break;
						case 18:
							days.getDays().setDay_eighteen(state);
							break;
						case 19:
							days.getDays().setDay_nineteen(state);
							break;
						case 20:
							days.getDays().setDay_twenty(state);
							break;
						case 21:
							days.getDays().setDay_twentyOne(state);
							break;
						case 22:
							days.getDays().setDay_twentyTwo(state);
							break;
						case 23:
							days.getDays().setDay_twentyThree(state);
							break;
						case 24:
							days.getDays().setDay_twentyFour(state);
							break;
						case 25:
							days.getDays().setDay_twentyFive(state);
							break;
						case 26:
							days.getDays().setDay_twentySix(state);
							break;
						case 27:
							days.getDays().setDay_twentySeven(state);
							break;
						case 28:
							days.getDays().setDay_twentyEight(state);
							break;
						case 29:
							days.getDays().setDay_twentyNine(state);
							break;
						case 30:
							days.getDays().setDay_thirty(state);
							break;
						case 31:
							days.getDays().setDay_thirtyOne(state);
							break;
						default:
							break;
						}
						
					}
				}
				legerList.add(days);
			}
		}
		
		return alls;
	}
	//需求86 - 储备库id - 导出
	private List<SignRecordCheckSpAll> ExtfindRespId(SignRecordCheckSpAll query) {
		return dao.ExtfindRespId(query);
	}
	//需求68 - 提交
	public boolean SubmitSignRecordCheckSpToApprove(int repositoryId, int year,
			int month) {
		return dao.SubmitSignRecordCheckSpToApprove(repositoryId,year,month);
	}
	//需求68 - 审批通过/不通过 -- 监管员管理部经理
	public boolean ApproveSignRecordCheckSpList(SignRecordCheckSpAll query) {
		return dao.ApproveSignRecordCheckSpList(query);
	}

	public boolean updateSignRecord(SignRecord sr) {
		return dao.updateSignRecord(sr);
	}

	
	
}
