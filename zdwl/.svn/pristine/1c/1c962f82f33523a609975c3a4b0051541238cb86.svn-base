package com.zd.csms.attendance.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.alibaba.fastjson.JSONArray;
import com.zd.core.ServiceSupport;
import com.zd.csms.attendance.dao.AttendanceDAOFactory;
import com.zd.csms.attendance.dao.IAttendanceDao;
import com.zd.csms.attendance.model.Attendance;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.querybean.AttendanceTimeQueryBean;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.querybean.DealerBankQueryBean;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.dao.IUserDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.region.dao.IRegionDAO;
import com.zd.csms.region.dao.RegionDAOFactory;
import com.zd.csms.region.model.RegionVO;
import com.zd.csms.repository.dao.IRepositoryDAO;
import com.zd.csms.repository.dao.RepositoryDAOFactory;
import com.zd.csms.roster.dao.IRosterDAO;
import com.zd.csms.roster.dao.RosterDAOFactory;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisorymanagement.dao.IExtraworkApplyDAO;
import com.zd.csms.supervisorymanagement.dao.ILeaveApplyDAO;
import com.zd.csms.supervisorymanagement.dao.ITransferDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.LeaveApplyVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.service.ExtraworkApplyService;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;

/**
 * 计算考勤签到
 *
 */

public class CreateAttendance extends ServiceSupport{
	
	
	private Calendar cal = Calendar.getInstance();
	
	private static Log log = LogFactory.getLog(CreateAttendance.class);
	/**
	 * @throws Exception
	 */
	public void run() throws Exception{
		IUserDAO userDao = RbacDAOFactory.getUserDAO();
		IAttendanceDao dao = AttendanceDAOFactory.getAttendanceDao();
		ILeaveApplyDAO leaveDao = SupervisoryManagementDAOFactory.getLeaveApplyDAO();
		IRepositoryDAO respDao = RepositoryDAOFactory.getRepositoryDAO();
		IRosterDAO rosterDao = RosterDAOFactory.getRosterDAO();
		//private IDealerSupervisorDAO mdsDao = MarketFactory.getDealerSupervisorDAO();
		IDealerDAO dealerDao = MarketFactory.getIDealerDAO();
		IRegionDAO regDao = RegionDAOFactory.getRegionDAO();
		IExtraworkApplyDAO extDap = SupervisoryManagementDAOFactory.getExtraworkApplyDAO();
		ExtraworkApplyService  extraworkApplyService=new ExtraworkApplyService();
		ITransferDAO tranDao = SupervisoryManagementDAOFactory.getTransferDAO();
		
		
		try {
			transactionBegin(regDao.getDataSourceName());
			Calendar nowDate = Calendar.getInstance();
			nowDate.add(Calendar.MONTH, -1);
			int year = nowDate.get(Calendar.YEAR);
			int month = nowDate.get(Calendar.MONTH)+1;
			int dayCount = nowDate.getActualMaximum(Calendar.DAY_OF_MONTH);//上个月一共多少天
			
			Attendance attendance = new Attendance();
			attendance.setId(Util.getID(Attendance.class));
			attendance.setYear(year);
			attendance.setMonth(month);
			attendance.setState(0);
			attendance.setCurrentMonth(nowDate.getTime());
			dao.add(attendance);
			UserQueryVO userQuery = new UserQueryVO();
			userQuery.setClienttype(ClientTypeConstants.SUPERVISORY.getCode());
			userQuery.setStates(new Integer[]{1});
			List<UserVO> users = userDao.searchUserList(userQuery);
			if(users==null||users.size()<=0)
				return;
			
			/**
			 * 开始循环查询到的监管员用户 并进行考勤计算和数据的插入
			 */
			
			for (UserVO user : users) {
				SupervisorBaseInfoVO baseInfo = respDao.getSupervisorBaseInfoByRepositoryId(user.getClient_id());//基本信息
				if(baseInfo==null||baseInfo.getEntryTime()==null)
					continue;
				RosterVO roster =  rosterDao.getRosterBySupervisorId(baseInfo.getId());//花名册信息
				if(roster==null)
					continue;
				
				int entryDay = -1;
				cal.setTime(baseInfo.getEntryTime());
				if(cal.get(Calendar.YEAR)==year&&cal.get(Calendar.MONTH)+1==month){
					entryDay = cal.get(Calendar.DAY_OF_MONTH);
				}
				
				List<Integer> dealerBindIds = new ArrayList<Integer>();
				List<AttendanceTimeQueryBean> attendanceTimeVOList = null;
				AttendanceTimeQueryBean currDealer = null;//当前经销商考勤设置信息
				attendanceTimeVOList = dao.getAttendanceTimeByUserId(user.getId());
				for (AttendanceTimeQueryBean bean : attendanceTimeVOList) {
					dealerBindIds.add(bean.getId());
					if (null != bean.getMorningStart() && 
							null != bean.getMorningEnd() && 
							null != bean.getAfternoonStart() && 
							null != bean.getAfternoonEnd()) {
						currDealer = bean;
						break;
					}
				}
				if(currDealer==null)
					continue;
				
				int lateCount=0;//迟到次数
				int absenteeismCount=0;//旷工天数
				int earlyCount=0;//早退天数
				int shouldAttendance=0;//应出勤天数
				List<SignRecord> signList = dao.findListByUserId(user.getId(),year,month);//签到信息
				Map<Integer, SignRecord> signMap = new HashMap<Integer, SignRecord>();
				List<LeaveApplyVO> leaveList = leaveDao.findLeaveReplaceListByUserId(user.getId(),year,month);//此人这个月的请假信息
				/**
				 * 请假信息
				 * 例：1.5号下午开始请假，请到1.7号上午
				 * 格式：key为请假的那天日期分别为5,6,7
				 * value：请假时间和类型 5号位下半天天数为  
				 */
				Map<Integer, LeaveInfoBean> leaveMap = new HashMap<Integer, LeaveInfoBean>();
				//统计请假
				leaveStatistics(leaveList,leaveMap,month,dayCount,year);
				
				
				//调动信息
				List<TransferRepositoryVO> tranList = tranDao.findTranRespListByRespId(user.getClient_id());
				/**
				 * 调动记录，Map的格式为在XX店的天数List
				 * 例：在1月的 1号-10号在A店
				 * key为：{1,2,3,4,5,6,7,8,9,10}
				 * value:调入信息
				 */
				Map<List<Integer>, TransferRepositoryVO> tranMap = new HashMap<List<Integer>, TransferRepositoryVO>();//调动记录
				//调动信息统计
				tranCalc(tranList, tranMap,dealerBindIds,currDealer.getId(),year,month);
				
				Map<Integer, Double> dealerAttendance = new HashMap<Integer, Double>();//多个经销商应出勤天数
				StringBuffer actuDay = new StringBuffer();
				
				if(signList!=null&&signList.size()>=0){
					for (SignRecord sign : signList) {//循环将签到集合装载为Map key为day value为签到信息
						int signDay = Integer.parseInt(DateUtil.getStringFormatByDate(sign.getCreateDate(), "dd"));
						signMap.put(signDay, sign);
					}
					
					
					for (int i = 1; i <= dayCount; i++) {
						//如果当前日期小于入职日期，则不计算
						if(entryDay!=-1&&i<entryDay){
							continue;
						}
						
						String dayworks=null;
						int currdealerId = 0;
						//判断当月是否有调动
						if(!tranMap.isEmpty()){
							int dealerId = getDealerIdByTrac(tranMap, i);
							currdealerId = dealerId;
							AttendanceTime att = dao.get(AttendanceTime.class, dealerId, new BeanPropertyRowMapper(AttendanceTime.class));
							if(att!=null)
								dayworks=att.getWorkDays();
							else
								continue;
						}else{
							dayworks = currDealer.getWorkDays();
							currdealerId = currDealer.getId();
						}
						
						if(dayworks!=null){//如果今天是休息日，直接跳过本次循环进入下一次
							Calendar calendar = Calendar.getInstance();
							calendar.set(year, month-1, i);
							int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
					        if (w == 0)
					            w = 7;
				        	if(dayworks.indexOf(w+"")==-1){//说明今天是休息日
				        		if(leaveMap.get(i)!=null){
				        			leaveRemove(leaveMap.get(i), leaveMap);
				        		}
				        		continue;
				        	}else{
				        		shouldAttendance++;//应出勤天数+1
				        		dealerAttendance.put(currdealerId, 
				        				dealerAttendance.get(currdealerId)==null?1:dealerAttendance.get(currdealerId)+1);
				        		actuDay.append(i+",");
				        	}
						}
						/**
						 * 如果这一天没签到，去判断是不是正常休息日
						 */
						if(signMap.get(i)==null){
							//如果今天没有签到记录且没有请假，则插入旷工数据
							if(leaveMap.get(i)==null){
								SignRecord vo = new SignRecord();
								vo.setId(Util.getID(SignRecord.class));
								vo.setCreateUserId(user.getId());
								cal.set(year, month, i);
								vo.setCreateDate(cal.getTime());
								vo.setRespId(user.getClient_id());
								vo.setIsAbsenteeism(1);//旷工
								dao.add(vo);//保存签到记录
								signMap.put(i, vo);
								absenteeismCount++;//增加旷工
							}
						}else{
							SignRecord vo=signMap.get(i);
							//这一天有签到记录
							if(isAbsenteeism(vo)){
								if (leaveMap.get(i)==null){
									//有签到，但是签到记录不全，有缺失，算是旷工
									vo.setIsAbsenteeism(1);
									absenteeismCount++;//增加旷工
								}
								else if(leaveMap.get(i).getLeaveCount()==0.5){//上半天请假
									if(vo.getAfternoonStart()==null||vo.getAfternoonEnd()==null){
										vo.setIsAbsenteeism(1);
										absenteeismCount++;//增加旷工
									}
								}else if(leaveMap.get(i).getLeaveCount()==-0.5){//下半天请假
									if(vo.getMorningStart()==null||vo.getMorningEnd()==null){
										vo.setIsAbsenteeism(1);
										absenteeismCount++;//增加旷工
									}
									
								}
								
								dao.update(vo);
								
							}else{
								if(vo.getIsAbsenteeism()==1){
									absenteeismCount++;//增加旷工
								}else{
									if(vo.getIsLate()==1){
										lateCount++;//增加迟到次数
									}
									if(vo.getIsEarly()==1){
										earlyCount++;//增加早退次数
									}
								}
							}
						}
					}
					
				}
				AttendanceInfo ai = new AttendanceInfo();
				
				
				StringBuffer dealerName = new StringBuffer("");
				StringBuffer bankName = new StringBuffer("");
				StringBuffer dealerIds=new StringBuffer(",");
				StringBuffer bankIds=new StringBuffer(",");
				List<DealerBankQueryBean> dealers = dealerDao.findDealerListByRepId(user.getClient_id());
				if(dealers!=null&&dealers.size()>0){
					for (int i = 0; i < dealers.size(); i++) {
						dealerName.append(dealers.get(i).getDealerName());
						bankName.append(dealers.get(i).getBankName());
						dealerIds.append(dealers.get(i).getDealerId()+",");
						bankIds.append(dealers.get(i).getBankId()+",");
						if(i!=dealers.size()){
							bankName.append(",");
							dealerName.append(",");
						}
					}
					int dealerId = dealers.get(0).getDealerId();
					DealerVO dealervo = dealerDao.get(DealerVO.class, dealerId, new BeanPropertyRowMapper(DealerVO.class));
					ai.setProvinceId(Integer.parseInt(dealervo.getProvince()));
					ai.setCityId(Integer.parseInt(dealervo.getCity()));
					ai.setProvinceName(regDao.get(RegionVO.class, ai.getProvinceId(), new BeanPropertyRowMapper(RegionVO.class)).getName());
					ai.setCityName(regDao.get(RegionVO.class, ai.getCityId(), new BeanPropertyRowMapper(RegionVO.class)).getName());
					
				}
				ai.setOvertime(extraworkApplyService.getExtraworkDaysByRepositoryIdAndMonth(user.getClient_id(), year, month));
				
				ai.setId(Util.getID(AttendanceInfo.class));
				ai.setUserid(user.getId());
				ai.setRespId(user.getClient_id());
				ai.setAttendanceId(attendance.getId());
				ai.setStaffNo(roster.getStaffNo());
				ai.setName(baseInfo.getName());
				ai.setDealerNames(dealerName.toString());
				ai.setBankNames(bankName.toString());
				ai.setDealerIds(dealerIds.toString());
				ai.setBankIds(bankIds.toString());
				
				ai.setMatterHoliday(leaveMap.get(100).getLeaveCount());
				ai.setAilingHoliday(leaveMap.get(101).getLeaveCount());
				ai.setDaoxiu(leaveMap.get(102).getLeaveCount());
				ai.setZhengxiu(leaveMap.get(103).getLeaveCount());
				
				
				ai.setLateDay(lateCount);
				ai.setEarlyDay(earlyCount);
				ai.setAbsenteeism(absenteeismCount);
				ai.setState(0);
				//TODO
				//实际出勤等于应该出勤-旷工-事假-病假 
				ai.setActualAttendance(shouldAttendance-ai.getAbsenteeism()-ai.getAilingHoliday()-ai.getMatterHoliday());
				
				ai.setShouldAttendance(shouldAttendance);
				if(ai.getShouldAttendance()!=ai.getActualAttendance()){
					ai.setFullTime(0);//非全勤
				}else{
					ai.setFullTime(1);//全勤
				}
				if(actuDay.length()>0){
					actuDay.insert(0, ",");
				}
				if(!tranMap.isEmpty() && tranMap.size() > 0){
					List<TranInfoBean> tranBeanList = new ArrayList<TranInfoBean>();
					for (List<Integer> list : tranMap.keySet()) {
						System.out.println("tranMap.size:"+tranMap.size()+"\tlist.size:"+list.size()+" or "+list);
						if(list.size() == 0){
							continue;
						}
						int min = Collections.min(list);
						int max = Collections.max(list);
						int dealerId = tranMap.get(list).getDealerId();
						DealerVO dealervo = dealerDao.get(DealerVO.class, dealerId,new BeanPropertyRowMapper( DealerVO.class));
						TranInfoBean infoBean = new TranInfoBean();
						infoBean.setDealer(dealervo.getId());
						infoBean.setDealerName(dealervo.getDealerName());
						infoBean.setStart(min);
						infoBean.setEnd(max);
						infoBean.setYear(year);
						infoBean.setMonth(month);
						infoBean.setShouldAttendance(dealerAttendance.get(dealerId)==null?0.0:dealerAttendance.get(dealerId));
						infoBean.setSupervisorSource(tranMap.get(list).getSupervisorSource());
						double actuAtt=infoBean.getShouldAttendance();
						/**
						 * 计算在这个经销商内 出勤的天数
						 */
						for (int i = min; i <=max; i++) {
							if(actuDay.indexOf(","+i+",")==-1){//如果不在工作日，则不计算
								continue;
							}
							if(signMap.get(i)!=null&&signMap.get(i).getIsAbsenteeism()==1){
								actuAtt-=1.0;
								continue;
							}
							if(leaveMap.get(i)!=null){
								actuAtt-=Math.abs(leaveMap.get(i).getLeaveCount());
							}
						}
						infoBean.setActualAttendance(actuAtt);
						tranBeanList.add(infoBean);
					}
					String sb = JSONArray.toJSONString(tranBeanList);
					if(StringUtil.isNotEmpty(sb)){
						ai.setChangePostInfo(sb);
					}
				}
				if(actuDay.length()>0){
					actuDay.deleteCharAt(0);
					actuDay.deleteCharAt(actuDay.length()-1);
					ai.setActuDay(actuDay.toString());
				}
				
				dao.add(ai);
				
			}
			transactionCommit(regDao.getDataSourceName());
		} catch (Exception e) {
			transactionRollback(regDao.getDataSourceName());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 是否旷工
	 * 如果属于旷工返回true
	 * @return
	 */
	private boolean isAbsenteeism(SignRecord sign){
		if(sign.getAfternoonEnd()==null||sign.getMorningStart()==null
				||sign.getMorningEnd()==null||sign.getAfternoonStart()==null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 统计请假的数据
	 * 用key，value传回
	 * key,代表请假的日期，value是天数 1代表这一整天都请假 0.5代表上半天，-0.5代表下半天
	 * 以中午12点分辨上半天还是下半天
	 * @return
	 */
	public void leaveStatistics(List<LeaveApplyVO> leaveList,Map<Integer, LeaveInfoBean> leaveMap,int month,int dayCount,int year){
		Calendar leaveStartCal = Calendar.getInstance();
		Calendar leaveEndCal = Calendar.getInstance();
		leaveMap.put(100, new LeaveInfoBean(0.0, 1));//事假
		leaveMap.put(101,new LeaveInfoBean(0.0, 2));//病假
		leaveMap.put(102,new LeaveInfoBean(0.0, 3));//倒休
		leaveMap.put(103,new LeaveInfoBean(0.0, 4));//正休
		if(leaveList!=null&&leaveList.size()>0){
			for (LeaveApplyVO leave : leaveList) {
				leaveStartCal.setTime(leave.getLeaveStartTime());
				leaveEndCal.setTime(leave.getLeaveEndTime());
				
				if(leaveStartCal.get(Calendar.MONTH)+1==month
						&&leaveEndCal.get(Calendar.MONTH)+1==month){
					/**
					 * 如果请假日期的开始和结束时间都在本月，去开始日期和实际请假天数作为条件
					 */
					calcLeave(leaveMap,leave.getLeaveDays(),leave.getLeaveStartTime(),leave.getLeaveType());
				}else if(leaveStartCal.get(Calendar.MONTH)+1==month){
					/**
					 * 如果请假日期是开始时间是在本月，结束日期不在本月，则取本月开始时间和本月天数减去开始日期的天数作为条件
					 */
					int day = leaveStartCal.get(Calendar.DAY_OF_MONTH);//开始日期
					int leaveDay = dayCount-day+1;
					int hour = leaveStartCal.get(Calendar.HOUR_OF_DAY);//开始小时
					if(hour>=12){
						leaveDay-=0.5;
					}
					calcLeave(leaveMap,leaveDay,leave.getLeaveStartTime(),leave.getLeaveType());
					
				}else if(leaveEndCal.get(Calendar.MONTH)+1==month){
					/**
					 * 如果是到期日是这个月，则取这个月1号到到期日的那天之间的天数，在用这个月的第一天7点作为开始时间
					 */
					int day = leaveEndCal.get(Calendar.DAY_OF_MONTH);//结束日期
					int hour = leaveEndCal.get(Calendar.HOUR_OF_DAY);//结束时间
					if(hour<12){
						day-=0.5;
					}
					Calendar temp = Calendar.getInstance();
					temp.set(year, month-1, 1, 7, 0, 0);
					calcLeave(leaveMap,day,temp.getTime(),leave.getLeaveType());
				}
			}
		}
	}
	
	/**
	 * 计算请假
	 * @param leaveMap
	 */
	private void calcLeave(Map<Integer, LeaveInfoBean> leaveMap,double leaveday,Date startDate,int type){
		int startday = Integer.parseInt(DateUtil.getStringFormatByDate(startDate, "dd"));
		int forCount = (int)Math.ceil(leaveday);
		if(forCount>=1){
			for (int i = 0; i < forCount; i++) {
				if(i==0){//第一天需要判断是上午还是下午
					int hour = Integer.parseInt(DateUtil.getStringFormatByDate(startDate, "HH"));//开始小时
					if(hour>=12){//中午12点作为判断是上午开始请还是下午开始请的标准
						leaveMap.put(startday, new LeaveInfoBean(-0.5, type));//如果是下午开始清，就存-0.5
						leaveday-=0.5;
						addLeaveDay(leaveMap,type,Math.abs(leaveMap.get(startday).getLeaveCount()));
						if(leaveday==1){//如果只请一天假，则直接加上第二天上午的请假
							leaveMap.put(startday+1, new LeaveInfoBean(0.5, type));
							addLeaveDay(leaveMap,type,Math.abs(leaveMap.get(startday+1).getLeaveCount()));
							break;
						}
					}else{
						leaveMap.put(startday,new LeaveInfoBean(1.0, type));//上午开始的话就减去一天
						leaveday-=1;
						addLeaveDay(leaveMap,type,Math.abs(leaveMap.get(startday).getLeaveCount()));
					}
				}else{
					leaveMap.put(startday, new LeaveInfoBean(leaveday%1==0?1:leaveday%1, type));
					leaveday-=(leaveday%1==0?1:leaveday%1);
					addLeaveDay(leaveMap,type,Math.abs(leaveMap.get(startday).getLeaveCount()));
				}
			}
		}else{
			int hour = Integer.parseInt(DateUtil.getStringFormatByDate(startDate, "HH"));//开始小时
			if(hour>=12){
				leaveMap.put(startday, new LeaveInfoBean(-0.5, type));
				leaveday-=0.5;
				addLeaveDay(leaveMap,type,Math.abs(leaveMap.get(startday).getLeaveCount()));
			}else{
				leaveMap.put(startday, new LeaveInfoBean(0.5, type));
				leaveday-=0.5;
				addLeaveDay(leaveMap,type,Math.abs(leaveMap.get(startday).getLeaveCount()));
			}
		}
	}
	
	private void addLeaveDay(Map<Integer, LeaveInfoBean> leaveMap,int type,double day){
		if(type==1){
			leaveMap.get(100).setLeaveCount(leaveMap.get(100).getLeaveCount()+day);
		}else if(type==2){
			leaveMap.get(101).setLeaveCount(leaveMap.get(101).getLeaveCount()+day);
		}else if(type==3){
			leaveMap.get(102).setLeaveCount(leaveMap.get(102).getLeaveCount()+day);
		}else if(type==4){
			leaveMap.get(103).setLeaveCount(leaveMap.get(103).getLeaveCount()+day);
		}
	}
	
	
	private void tranCalc(List<TransferRepositoryVO> list,Map<List<Integer>, TransferRepositoryVO> map, List<Integer> dealerBindIds, int currDealer,int year,int month){
		if(list==null||list.size()==0){
			return;
		}
		List<TransferRepositoryVO> currMonth = new ArrayList<TransferRepositoryVO>();
		int prev = -1;
		for (int j=0;j<list.size();j++) {
			if(list.get(j).getEntryTime()!=null && 
					DateUtil.getStringFormatByDate(list.get(j).getEntryTime(), "yyyyMM")
					.equals(year+""+(month<10?"0"+month:month))){
				currMonth.add(list.get(j));
			}
			if(currMonth.size()==0){
				prev=j;
			}
			
			
		}
		if(currMonth==null||currMonth.size()==0||prev==-1){
			return;
		}
		
		
		if(currMonth.size()>1)
			for (int j=0;j<currMonth.size();j++) {
				TransferRepositoryVO vo = currMonth.get(j);
				cal.setTime(vo.getEntryTime());
				int day = cal.get(Calendar.DAY_OF_MONTH);//换岗日期
				for (int k=j+1;k<currMonth.size();k++) {
					TransferRepositoryVO temp = currMonth.get(k);
					cal.setTime(temp.getEntryTime());
					if(cal.get(Calendar.DAY_OF_MONTH)==day){
						currMonth.remove(j);
						break;
					}
				}
			}
			
			
		int start = 1;
		int end = 0;
		for (int j=0;j<currMonth.size();j++) {
			TransferRepositoryVO vo = currMonth.get(j);
			cal.setTime(vo.getEntryTime());
			int day = cal.get(Calendar.DAY_OF_MONTH);//换岗日期
			List<Integer> temp = new ArrayList<Integer>();
			TransferRepositoryVO tran=null;
			if(j==0){
				if(day==1)
					continue;
				tran = list.get(prev);
				start = 1;
				end = day-1;
			}else{
				tran = list.get(j-1);
				start = end+1;
				end = day-1;
			}
			for(int k = start;k<=end;k++){
				temp.add(k);
			}
			map.put(temp, tran);
			if(j==currMonth.size()-1){
				temp = new ArrayList<Integer>();
				start = day;
				end = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				for(int k = start;k<=end;k++){
					temp.add(k);
				}
				map.put(temp, vo);
			}
		}
		
	}
	
	/**
	 * 根据传入的日期确定那天所在的经销商位置
	 * @param map
	 * @param date
	 * @return
	 */
	public int getDealerIdByTrac(Map<List<Integer>, TransferRepositoryVO> map,int date){
		for (List<Integer> list : map.keySet()) {
			if(list.contains(date)){
				return map.get(list).getDealerId();
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 11, 24);
		System.out.println(calendar.getActualMaximum(calendar.DAY_OF_MONTH));
	}
	
	private void leaveRemove(LeaveInfoBean leave,Map<Integer, LeaveInfoBean> leaveMap){
		int type = leave.getLeaveType();
		double day = leave.getLeaveCount();
		if(type==1){
			leaveMap.get(100).setLeaveCount(leaveMap.get(100).getLeaveCount()-day);
		}else if(type==2){
			leaveMap.get(101).setLeaveCount(leaveMap.get(101).getLeaveCount()-day);
		}else if(type==3){
			leaveMap.get(102).setLeaveCount(leaveMap.get(102).getLeaveCount()-day);
		}else if(type==4){
			leaveMap.get(103).setLeaveCount(leaveMap.get(103).getLeaveCount()-day);
		}
	}
}
