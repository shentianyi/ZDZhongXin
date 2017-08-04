package com.zd.csms.supervisorymanagement.warning;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.service.AttendanceService;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.dao.IDealerSupervisorDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketProjectCirculationVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.service.MarketProjectCirculationService;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageQueryVO;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.message.model.SupMaMsgQueryVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.message.service.SupMaMsgService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryQueryVO;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyBean;
import com.zd.csms.supervisorymanagement.model.ResignApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.service.HandoverLogService;
import com.zd.csms.supervisorymanagement.service.HandoverPlanService;
import com.zd.csms.supervisorymanagement.service.ResignApplyService;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

/**
 * 监管员管理部消息提醒和预警
 *
 */
public class SupervisoryManagementWarning extends ServiceSupport {
	/**
	 * 每月25日提交轮岗计划提醒
	 * @throws Exception
	 */
	public void handoverPlan() throws Exception {
		//接收信息角色：监管员管理部经理、业务部经理、调配专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.BUSINESS_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()+""};
		SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
		messageInfo.setContent("每月轮岗计划提醒");
		messageInfo.setIsread(1);
		sendMesssage(roleIds,MessageTypeContant.HANDOVERPLAN.getCode(),1,messageInfo);
	}
	/**
	 * 每月三日提交前一月监管员管理费及社保费用
	 * @throws Exception
	 */
	public void supervisionFee() throws Exception {
		//接收信息角色：运营管理部部长、监管员管理部经理、薪酬专员,财务部经理、财务部会计
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()+"",
				RoleConstants.FINANCE_AMALDAR.getCode()+"",
				RoleConstants.FINANCE_ACCOUNTING.getCode()+""};
		SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
		messageInfo.setContent("提交前一月监管员管理费及社保费用");
		messageInfo.setIsread(1);
		sendMesssage(roleIds,MessageTypeContant.SUPERVISIONFEE.getCode(),1,messageInfo);
	}
	/**
	 * 每月一日提交监管员考勤提醒
	 * @throws Exception
	 */
	public void supervisorAttendance() throws Exception {
		//接收信息角色：监管员管理部经理、考勤专员,财务部经理、财务部会计
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()+"",
				RoleConstants.FINANCE_AMALDAR.getCode()+"",
				RoleConstants.FINANCE_ACCOUNTING.getCode()+""};
		SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
		messageInfo.setContent("提交监管员考勤");
		messageInfo.setIsread(1);
		sendMesssage(roleIds,MessageTypeContant.SUPERVISORATTENDANCE.getCode(),1,messageInfo);
	}
	
	/**
	 * 未按轮岗计划执行提醒
	 * @throws Exception
	 */
	public void noExecuteHandoverPlan() throws Exception {
		//接收信息角色：监管员管理部经理、调配专员、
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()+""};
		HandoverPlanService handoverPlanService=new HandoverPlanService();
		HandoverLogService handoverLogService=new HandoverLogService();
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		HandoverPlanQueryVO handoverPlanQuery=new HandoverPlanQueryVO();
		handoverPlanQuery.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
		List<HandoverPlanVO> handoverPlanList=handoverPlanService.searchHandoverPlanList(handoverPlanQuery);
		if(handoverPlanList!=null && handoverPlanList.size()>0){
			HandoverLogQueryVO  handoverLogQueryVO=new HandoverLogQueryVO();
			for(HandoverPlanVO handoverPlanVO:handoverPlanList){
				//handoverLogQueryVO.setDealerId(Integer.parseInt(handoverPlanVO.getDelivererDealerID()));
				handoverLogQueryVO.setDeliverer(handoverPlanVO.getDeliverer());
				handoverLogQueryVO.setReceiver(handoverPlanVO.getReceiver());
				handoverLogQueryVO.setMissionDate(handoverPlanVO.getMissionDate());
				List<HandoverLogVO> handoverLogList=handoverLogService.searchHandoverLogList(handoverLogQueryVO);
				if(handoverLogList==null || handoverLogList.size()==0){
					//经销商名称、品牌、金融机构（总、分、支）、客户经理、计划轮岗时间
					String dealerName="";
					String brandName="";
					String bankName="";
					String customerManager="";
					if(StringUtil.isNotEmpty(handoverPlanVO.getDelivererDealerID())){
						String[] delivererDealerIds=handoverPlanVO.getDelivererDealerID().split(",");
						if(delivererDealerIds!=null && delivererDealerIds.length>0){
							for(String delivererDealerIdStr:delivererDealerIds){
								int delivererDealerId=Integer.parseInt(delivererDealerIdStr);
								if(delivererDealerId>0){
									DealerQueryBean DealerQueryBean=dealerByIdJsonAction.getDealer(delivererDealerId);
									dealerName=dealerName+DealerQueryBean.getDealerName()+" ";
									brandName=brandName+DealerQueryBean.getBrand()+" ";
									bankName=bankName+DealerQueryBean.getBankName()+" ";
									customerManager=customerManager+DealerQueryBean.getBankContact()+" ";
								}
							}
						}
					}
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					messageInfo.setIsread(1);
					messageInfo.setIsmask(1);
					messageInfo.setDealerName(dealerName);
					messageInfo.setBankName(bankName);
					messageInfo.setBrandName(brandName);
					messageInfo.setCustomerManager(customerManager);
					messageInfo.setHandoverPlanTime(handoverPlanVO.getMissionDate());
					sendMesssage(roleIds,MessageTypeContant.NOEXECUTEHANDOVERPLAN.getCode(),1,messageInfo);
				}
			}
		}
	}
	/**
	 * 监管员系统签到异常信息
	 * @throws Exception
	 */
	public void supervisorSignInAbnormal() throws Exception{
		AttendanceService attendanceService=new AttendanceService();
		RepositoryService repositoryService=new RepositoryService();
		RosterService rosterService=new RosterService();
		IDealerSupervisorDAO dsDao = MarketFactory.getDealerSupervisorDAO();
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		//接收信息角色：考勤专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()+""};
		List<SignRecord> signRecordList=attendanceService.findSignRecordListDateAndStatus(new Date(), 1, 0, 0, 0,0);
		if(signRecordList!=null && signRecordList.size()>0){
			for(SignRecord signRecord:signRecordList){
				AttendanceTime attendanceTime=attendanceService.getAttendanceTimeByRepId(signRecord.getRespId());
				//信息内容：监管员姓名、工号、经销商名称、金融机构（总、分、支）、应签到时间、实际签到时间
				SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
				SupervisorBaseInfoVO SupervisorBaseInfoVO=repositoryService.getSupervisorBaseInfoByRepositoryId(signRecord.getRespId());
				if(SupervisorBaseInfoVO!=null){
					messageInfo.setSupervisorName(SupervisorBaseInfoVO.getName());
				}
				List<RosterVO> rosterList=rosterService.searchRosterByRepositoryId(signRecord.getRespId());
				if(rosterList!=null && rosterList.size()>0){
					RosterVO roster=rosterList.get(0);
					if(roster!=null){
						messageInfo.setStaffNo(roster.getStaffNo());
					}
				}
				DealerSupervisorQueryVO dealerSupQuery=new DealerSupervisorQueryVO();
				dealerSupQuery.setSupervisorId(signRecord.getRespId());
				List<DealerSupervisorVO> dealerSupList=dsDao.searchDealerSupervisorList(dealerSupQuery);
				if(dealerSupList!=null && dealerSupList.size()>0){
					String dealerName="";
					String bankName="";
					String brandName="";
					for(DealerSupervisorVO dealerSup:dealerSupList){
						DealerQueryBean dealer=dealerByIdJsonAction.getDealer(dealerSup.getDealerId());
						if(dealer!=null){
							dealerName=dealerName+dealer.getDealerName()+" ";
							bankName=bankName+dealer.getBankName()+" ";
							brandName=brandName+dealer.getBrand()+" ";
						}
					}
					messageInfo.setDealerName(dealerName);
					messageInfo.setBankName(bankName);
					messageInfo.setBrandName(brandName);
				}
				if(attendanceTime!=null){
					messageInfo.setShouldMorningStart(attendanceTime.getMorningStart());
					messageInfo.setShouldAfternoonStart(attendanceTime.getAfternoonStart());
				}
				messageInfo.setActualMorningStart(DateUtil.getStringFormatByDate(
						signRecord.getMorningStart(), "yyyy-MM-dd HH:mm:ss"));
				messageInfo.setActualAfternoonStart(DateUtil.getStringFormatByDate(
						signRecord.getAfternoonStart(), "yyyy-MM-dd HH:mm:ss"));
				messageInfo.setContent("监管员系统签到迟到");
				messageInfo.setIsread(1);
				sendMesssage(roleIds,MessageTypeContant.SUPERVISORSIGNINABNORMAL.getCode(),1,messageInfo);
			}
		}
	}
	/**
	 * 监管员系统签退异常信息
	 * @throws Exception
	 */
	public void supervisorSignOutAbnormal() throws Exception {
		AttendanceService attendanceService=new AttendanceService();
		RepositoryService repositoryService=new RepositoryService();
		RosterService rosterService=new RosterService();
		IDealerSupervisorDAO dsDao = MarketFactory.getDealerSupervisorDAO();
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		//接收信息角色：考勤专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()+""};
		List<SignRecord> signRecordList=attendanceService.findSignRecordListDateAndStatus(new Date(), 0, 1, 0, 0,0);
		if(signRecordList!=null && signRecordList.size()>0){
			for(SignRecord signRecord:signRecordList){
				AttendanceTime attendanceTime=attendanceService.getAttendanceTimeByRepId(signRecord.getRespId());
				//信息内容：监管员姓名、工号、经销商名称、金融机构（总、分、支）、应签退时间、实际签退时间
				SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
				SupervisorBaseInfoVO SupervisorBaseInfoVO=repositoryService.getSupervisorBaseInfoByRepositoryId(signRecord.getRespId());
				if(SupervisorBaseInfoVO!=null){
					messageInfo.setSupervisorName(SupervisorBaseInfoVO.getName());
				}
				List<RosterVO> rosterList=rosterService.searchRosterByRepositoryId(signRecord.getRespId());
				if(rosterList!=null && rosterList.size()>0){
					RosterVO roster=rosterList.get(0);
					if(roster!=null){
						messageInfo.setStaffNo(roster.getStaffNo());
					}
				}
				DealerSupervisorQueryVO dealerSupQuery=new DealerSupervisorQueryVO();
				dealerSupQuery.setSupervisorId(signRecord.getRespId());
				List<DealerSupervisorVO> dealerSupList=dsDao.searchDealerSupervisorList(dealerSupQuery);
				if(dealerSupList!=null && dealerSupList.size()>0){
					String dealerName="";
					String bankName="";
					String brandName="";
					for(DealerSupervisorVO dealerSup:dealerSupList){
						DealerQueryBean dealer=dealerByIdJsonAction.getDealer(dealerSup.getDealerId());
						if(dealer!=null){
							dealerName=dealerName+dealer.getDealerName()+" ";
							bankName=bankName+dealer.getBankName()+" ";
							brandName=brandName+dealer.getBrand()+" ";
						}
					}
					messageInfo.setDealerName(dealerName);
					messageInfo.setBankName(bankName);
					messageInfo.setBrandName(brandName);
				}
				if(attendanceTime!=null){
					messageInfo.setShouldMorningEnd(attendanceTime.getMorningEnd());
					messageInfo.setShouldAfternoonEnd(attendanceTime.getAfternoonEnd());
				}
				
				messageInfo.setActualMorningEnd(DateUtil.getStringFormatByDate(
						signRecord.getMorningEnd(), "yyyy-MM-dd HH:mm:ss"));
				messageInfo.setActualAfternoonEnd(DateUtil.getStringFormatByDate(
						signRecord.getAfternoonEnd(), "yyyy-MM-dd HH:mm:ss"));
				messageInfo.setContent("监管员系统签退早退");
				messageInfo.setIsread(1);
				sendMesssage(roleIds,MessageTypeContant.SUPERVISORSIGNOUTABNORMAL.getCode(),1,messageInfo);
			}
		}
	}
	
	/**
	 * 项目进驻流转单发出后三天未录入人员信息提醒
	 * @throws Exception
	 */
	public void projectCircuNoSupervisor() throws Exception {
		MarketProjectCirculationService MarProCirService=new MarketProjectCirculationService();
		RepositoryService repService=new RepositoryService();
		BankService bankService=new BankService();
		//接收信息角色：监管员管理部经理、招聘专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 3);
		Date endDate = dft.parse(dft.format(date.getTime()));
		List<MarketProjectCirculationVO> MarProCirList=MarProCirService.findListByCreateDate(endDate,1);
		if(MarProCirList!=null && MarProCirList.size()>0){
			for(MarketProjectCirculationVO MarProCir:MarProCirList){
				List<RepositoryDispatchCityVO> repDispatchCityList=repService.findDispatchCityList(MarProCir.getProvince(), MarProCir.getCity(), MarProCir.getDistrict());
				if(repDispatchCityList==null || repDispatchCityList.size()==0){
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					messageInfo.setContent("项目进驻流转单发出后三天未录入人员信息提醒");
					messageInfo.setDealerName(MarProCir.getDealerName());
					BankVO bank = bankService.get(MarProCir.getBankId());
					if(bank!=null){
						messageInfo.setBankName(bank.getBankFullName());
					}
					messageInfo.setExpectedInDate(MarProCir.getInStoreDate());
					messageInfo.setIsread(1);
					sendMesssage(roleIds,MessageTypeContant.PROJECTCIRCUNOSUPERVISOR.getCode(),1,messageInfo);
				}
			}
		}
		
	}
	/**
	 * 监管员面试完成一日未安排培训提醒
	 * @throws Exception
	 */
	public void repositoryNoTrain() throws Exception {
		RepositoryService repService=new RepositoryService();
		//接收信息角色：监管员管理部经理、招聘专员、培训专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate = dft.parse(dft.format(date.getTime()));
		RepositoryQueryVO repositoryQueryVO=new RepositoryQueryVO();
		repositoryQueryVO.setCreateTime(endDate);
		repositoryQueryVO.setMsgType(1);
		List<RepositoryVO> repList=repService.repositoryList(repositoryQueryVO);
		if(repList!=null && repList.size()>0){
			for(RepositoryVO rep:repList){
				RepositoryTrainVO train=repService.loadRepositoryTrainByRepositoryId(rep.getId());
				if(train == null){
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					SupervisorBaseInfoVO supBaseInfo=repService.getSupervisorBaseInfoByRepositoryId(rep.getId());
					if(supBaseInfo!=null){
						messageInfo.setSupervisorName(supBaseInfo.getName());
					}
					messageInfo.setInterviewTime(rep.getCreateTime());
					StringBuffer repDisCityStr=new StringBuffer();
					List<RepositoryDispatchCityVO> repDispatchCityList=repService.getRepositoryDispatchCityByRepositoryId(rep.getId());
					if(repDispatchCityList!=null && repDispatchCityList.size()>0){
						for(RepositoryDispatchCityVO repDisCity:repDispatchCityList){
							repDisCityStr.append(repDisCity.getProvince()+" ").append(repDisCity.getCity()+" ").append(repDisCity.getCounty()+"  ");
						}
					}
					messageInfo.setIntentionCity(repDisCityStr.toString());
					messageInfo.setContent("监管员面试完成一日未安排培训提醒");
					messageInfo.setIsread(1);
					sendMesssage(roleIds,MessageTypeContant.REPOSITORYNOTRAIN.getCode(),1,messageInfo);
				}
			}
		}
	}
	/**
	 * 监管员进入储备库15天未安排上岗提醒
	 * @throws Exception
	 */
	public void repFifteenDaysNoPost() throws Exception {
		RepositoryService repService=new RepositoryService();
		//接收信息角色：监管员管理部经理、招聘专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 15);
		Date endDate = dft.parse(dft.format(date.getTime()));
		RepositoryQueryVO repositoryQueryVO=new RepositoryQueryVO();
		repositoryQueryVO.setTrainEndDate(endDate);
		repositoryQueryVO.setStatus(new Integer[]{RepositoryStatus.VALID.getCode()});
		List<RepositoryVO> repList=repService.repositoryList(repositoryQueryVO);
		if(repList!=null && repList.size()>0){
			for(RepositoryVO rep:repList){
				RepositoryTrainVO train=repService.loadRepositoryTrainByRepositoryId(rep.getId());
				if(train!=null){
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					messageInfo.setTrainTime(train.getEndTime());
					SupervisorBaseInfoVO supBaseInfo=repService.getSupervisorBaseInfoByRepositoryId(rep.getId());
					if(supBaseInfo!=null){
						messageInfo.setSupervisorName(supBaseInfo.getName());
					}
					messageInfo.setInterviewTime(rep.getCreateTime());
					StringBuffer repDisCityStr=new StringBuffer();
					List<RepositoryDispatchCityVO> repDispatchCityList=repService.getRepositoryDispatchCityByRepositoryId(rep.getId());
					if(repDispatchCityList!=null && repDispatchCityList.size()>0){
						for(RepositoryDispatchCityVO repDisCity:repDispatchCityList){
							repDisCityStr.append(repDisCity.getProvince()+" ").append(repDisCity.getCity()+" ").append(repDisCity.getCounty()+"  ");
						}
					}
					messageInfo.setIntentionCity(repDisCityStr.toString());
					messageInfo.setContent("监管员进入储备库15天未安排上岗提醒");
					messageInfo.setIsread(1);
					sendMesssage(roleIds,MessageTypeContant.REPFIFTEENDAYSNOPOST.getCode(),1,messageInfo);
				}
			}
		}
	}
	/**
	 * 监管员辞职离岗时间前十天未提交人员信息
	 * @throws Exception
	 */
	public void resignNoHandoverLog() throws Exception {
		ResignApplyService resignService=new ResignApplyService();
		HandoverLogService handoverLogService=new HandoverLogService();
		TransferService transferService=new TransferService();
		//接收信息角色：监管员管理部经理、招聘专员,市场部专员,风控部经理、内控专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+"",
				RoleConstants.MARKET_COMMISSIONER.getCode()+"",
				RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
				RoleConstants.WINDCONRTOL_INTERNAL.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE)+10);
		Date endDate = dft.parse(dft.format(date.getTime()));
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE)-20);
		Date handoverLogDate = dft.parse(dft.format(date.getTime()));
		ResignApplyQueryVO resignQuery=new ResignApplyQueryVO();
		resignQuery.setExpectResignTime(endDate);
		List<ResignApplyBean> resignBeanList=resignService.findList(resignQuery);
		if(resignBeanList!=null && resignBeanList.size()>0){
			for(ResignApplyBean resignBean:resignBeanList){
				HandoverLogQueryVO handoverLogQuery=new HandoverLogQueryVO();
				handoverLogQuery.setCreateDate(handoverLogDate);
				handoverLogQuery.setDeliverer(resignBean.getRepositoryId());
				List<HandoverLogVO> handoverLog=handoverLogService.searchHandoverLogList(handoverLogQuery);
				if(handoverLog==null || handoverLog.size()==0){
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					messageInfo.setSupervisorName(resignBean.getName());
					messageInfo.setStaffNo(resignBean.getStaffNo());
					messageInfo.setDealerName(resignBean.getDealerName());
					messageInfo.setBankName(resignBean.getBankName());
					//TODO 离职时间和离岗时间分别指什么时间
					messageInfo.setDimissionDate(resignBean.getExpectResignTime());
					messageInfo.setLeaveTime(resignBean.getExpectResignTime());
					if(StringUtil.isNotEmpty(resignBean.getDealerId())){
						String[] dealerIds=resignBean.getDealerId().split(",");
						List<TransferRepositoryVO> transferRepList=transferService.getSupervisorListByDealerIdAndRepId(dealerIds,resignBean.getRepositoryId());
						if(transferRepList!=null && transferRepList.size()>0){
							messageInfo.setEntryTime(transferRepList.get(0).getEntryTime());
						}
					}
					messageInfo.setContent("监管员辞职离岗时间前十天未提交人员信息");
					messageInfo.setIsread(1);
					sendMesssage(roleIds,MessageTypeContant.RESIGNNOHANDOVERLOG.getCode(),1,messageInfo);
				}
			}
		}
		
	}
	/**
	 * 监管员在一家店连续工作五个月提醒
	 * @throws Exception
	 */
	public void supWorkFiveMonth() throws Exception {
		TransferService transferService=new TransferService();
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		RepositoryService repositoryService=new RepositoryService();
		RosterService rosterService=new RosterService();
		//接收信息角色：监管员管理部经理、调配专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.MONTH, date.get(Calendar.MONTH)-5);
		Date endDate = dft.parse(dft.format(date.getTime()));
		List<TransferRepositoryVO> transferRepList=transferService.getSupervisorListByEntryTime(endDate,1);
		if(transferRepList!=null && transferRepList.size()>0){
			for(TransferRepositoryVO transferRep:transferRepList){
				SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
				DealerQueryBean dealerBean=dealerByIdJsonAction.getDealer(transferRep.getDealerId());
				if(dealerBean!=null){
					messageInfo.setDealerName(dealerBean.getDealerName());
					messageInfo.setBankName(dealerBean.getBankName());
				}
				SupervisorBaseInfoVO SupervisorBaseInfoVO=repositoryService.getSupervisorBaseInfoByRepositoryId(transferRep.getRepositoryId());
				if(SupervisorBaseInfoVO!=null){
					messageInfo.setSupervisorName(SupervisorBaseInfoVO.getName());
				}
				List<RosterVO> rosterList=rosterService.searchRosterByRepositoryId(transferRep.getRepositoryId());
				if(rosterList!=null && rosterList.size()>0){
					RosterVO roster=rosterList.get(0);
					if(roster!=null){
						messageInfo.setStaffNo(roster.getStaffNo());
					}
				}
				messageInfo.setEntryTime(transferRep.getEntryTime());
				//轮岗到期时间是进店时间后6个月
				date.setTime(transferRep.getEntryTime());
				date.set(Calendar.MONTH, date.get(Calendar.MONTH)+6);
				Date handoverDuedate = dft.parse(dft.format(date.getTime()));
				messageInfo.setHandoverDuedate(handoverDuedate);
				messageInfo.setContent("监管员在一家店连续工作五个月提醒");
				messageInfo.setIsread(1);
				sendMesssage(roleIds,MessageTypeContant.SUPWORKFIVEMONTH.getCode(),1,messageInfo);
			}
		}
	}
	
	/**
	 * 监管员培训考核不通过提醒
	 * @throws Exception
	 */
	public void supAssessNotPass() throws Exception {
		//接收信息角色：监管员管理部经理、招聘专员、培训专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode()+""};
		RepositoryService repositoryService=new RepositoryService();
		List<SupMaMsgInfoVO> supMaMsgInfoList=repositoryService.supAssessNotPassList(new Date());
		if(supMaMsgInfoList!=null && supMaMsgInfoList.size()>0){
			for(SupMaMsgInfoVO supMaMsgInfo:supMaMsgInfoList){
				supMaMsgInfo.setContent("监管员培训考核不通过提醒");
				sendMesssage(roleIds,MessageTypeContant.SUPERVISORASSESSMENTNOTPASS.getCode(),1,supMaMsgInfo);
			}
		}
	}
	
	public boolean sendMesssage(String[] roleIds,int type,int msgType, SupMaMsgInfoVO messageInfo){
		boolean flag=false;
		IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
		MessageService messageService=new MessageService();
		SupMaMsgService supMaMsgService=new SupMaMsgService();
		//根据接收信息角色获取UserID
		String userid = userRoleDao.findUsingUserIdByRole(roleIds);
		if(StringUtil.isNotEmpty(userid)){
			String[] userids = userid.split(",");
			for (String uid : userids) {
				MessageQueryVO query=new MessageQueryVO();
				query.setUserid(Integer.parseInt(uid));
				query.setType(type);
				query.setMsgtype(msgType);
				List<MessageVO> messageList=messageService.searchMessageList(query);
				if(messageList!=null && messageList.size()>0){
					//消息提醒
					if(msgType==1){
						for(MessageVO message:messageList){
							try {
								messageInfo.setMessageId(message.getId());
								messageInfo.setIsread(1);
								messageInfo.setIsmask(1);
								messageInfo.setInfoHashCode(messageInfo.toString().hashCode());
								messageInfo.setSendDate(new Date());
								flag=supMaMsgService.addSupervisorManagementMessageInfo(messageInfo);
								if(flag){
									message.setName(String.valueOf(Integer.parseInt(message.getName())+1));
									message.setIsread(1);
									flag=messageService.updMessage(message);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					//消息预警
					}else if(msgType==2){
						for(MessageVO message:messageList){
							//发送新的预警
							try {
								messageInfo.setMessageId(message.getId());
								messageInfo.setIsread(1);
								messageInfo.setIsmask(1);
								int infoHashCode=messageInfo.toString().hashCode();
								messageInfo.setInfoHashCode(infoHashCode);
								messageInfo.setSendDate(new Date());
								//查询该用户是否已屏蔽该类型预警
								boolean ismaskMessage=supMaMsgService.searchMsgListByMsgIdAndIsmask(infoHashCode, 2);
								if(ismaskMessage){
									flag=supMaMsgService.addSupervisorManagementMessageInfo(messageInfo);
									if(flag){
										message.setName(String.valueOf(Integer.parseInt(message.getName())+1));
										message.setIsread(1);
										flag=messageService.updMessage(message);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}else{
					MessageVO message=new MessageVO(Integer.parseInt(uid), "1", 
							"/message/supMaMsg.do?method=supMaMsgInfoList&supMaQuery.type="+type,
							1,msgType,type,ClientTypeConstants.SUPERVISORYMANAGEMENT.getName());
					int messageId;
					try {
						messageId = messageService.addMessages(message);
						if(messageId>0){
							messageInfo.setMessageId(messageId);
							messageInfo.setIsread(1);
							messageInfo.setIsmask(1);
							messageInfo.setInfoHashCode(messageInfo.toString().hashCode());
							messageInfo.setSendDate(new Date());
							flag=supMaMsgService.addSupervisorManagementMessageInfo(messageInfo);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 发送重复的预警信息
	 * @param roleIds
	 * @param type
	 * @param msgType
	 * @return
	 */
	private boolean sendRepeatWarning(String[] roleIds,int type,int msgType){
		boolean flag=false;
		IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
		MessageService messageService=new MessageService();
		SupMaMsgService supMaMsgService=new SupMaMsgService();
		//根据接收信息角色获取UserID
		String userid = userRoleDao.findUsingUserIdByRole(roleIds);
		if(StringUtil.isNotEmpty(userid)){
			String[] userids = userid.split(",");
			for (String uid : userids) {
				MessageQueryVO query=new MessageQueryVO();
				query.setUserid(Integer.parseInt(uid));
				query.setType(type);
				query.setMsgtype(msgType);
				List<MessageVO> messageList=messageService.searchMessageList(query);
				if(messageList!=null && messageList.size()>0 && msgType==2){
					//消息提醒
					for(MessageVO message:messageList){
						try {
							//发送当月已发送的预警
							SupMaMsgQueryVO supMaMsgQuery =new SupMaMsgQueryVO();
							supMaMsgQuery.setMessageId(message.getId());
							supMaMsgQuery.setMsgtype(message.getType());
							supMaMsgQuery.setUserid(message.getUserid());
							supMaMsgQuery.setSendDate(new Date());
							List<SupMaMsgInfoVO> supMaMsgInfoList=supMaMsgService.searchMsgInfoList(supMaMsgQuery);
							if(supMaMsgInfoList!=null && supMaMsgInfoList.size()>0){
								int count=0;
								for(SupMaMsgInfoVO supMaMsgInfo:supMaMsgInfoList){
									supMaMsgInfo.setSendDate(new Date());
									supMaMsgInfo.setIsread(1);
									supMaMsgInfo.setIsmask(1);
									//查询该用户是否已屏蔽该类型预警
									boolean ismaskMessage=supMaMsgService.searchMsgListByMsgIdAndIsmask(supMaMsgInfo.getInfoHashCode(), 2);
									if(ismaskMessage){
										//如果是监管员在一家店工作六个月预警，重新计算超期时间
										if(type==MessageTypeContant.SUPWORKSIXMONTHWARNING.getCode()){
											int days = (int) ((new Date().getTime() - supMaMsgInfo.getHandoverDuedate().getTime()) / (1000*3600*24));
											supMaMsgInfo.setHandoverOverDuedate(days);
										}
										supMaMsgService.addSupervisorManagementMessageInfo(supMaMsgInfo);
										count++;
									}
								}
								message.setName(String.valueOf(Integer.parseInt(message.getName())+count));
								message.setIsread(1);
								messageService.updMessage(message);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 监管员连续三天未正常出勤预警
	 * @throws Exception
	 */
	public void supervisorThreeDaySignAbnormal() throws Exception {
		AttendanceService attendanceService=new AttendanceService();
		RepositoryService repositoryService=new RepositoryService();
		RosterService rosterService=new RosterService();
		IDealerSupervisorDAO dsDao = MarketFactory.getDealerSupervisorDAO();
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance();
		//今天
		date.setTime(new Date());
		Date today = sdf.parse(sdf.format(date.getTime()));
		//昨天
		date.setTime(new Date());
		date.set(Calendar.DATE, date.get(Calendar.DATE)-1);
		Date yesterday = sdf.parse(sdf.format(date.getTime()));
		//前天
		date.setTime(new Date());
		date.set(Calendar.DATE, date.get(Calendar.DATE)-2);
		Date theDayBeforeYesterday=sdf.parse(sdf.format(date.getTime()));
		//接收信息角色：考勤专员、监管员管理部经理
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+""};
		//发送当月已经发送过的预警
		sendRepeatWarning(roleIds,MessageTypeContant.SUPERVISORTHREEDAYSIGNABNORMAL.getCode(),2);
		List<SignRecord> signRecordList=attendanceService.findAbnormalSignRecordList(today, 0);
		if(signRecordList!=null && signRecordList.size()>0){
			for(SignRecord signRecord:signRecordList){
				List<SignRecord> signRecordSeconds=attendanceService.findAbnormalSignRecordList(yesterday, signRecord.getRespId());
				if(signRecordSeconds!=null && signRecordSeconds.size()>0){
					for(SignRecord signRecordSecond:signRecordSeconds){
						List<SignRecord> signRecordThirds=attendanceService.findAbnormalSignRecordList(theDayBeforeYesterday, signRecordSecond.getRespId());
						if(signRecordThirds!=null && signRecordThirds.size()>0){
							AttendanceTime attendanceTime=attendanceService.getAttendanceTimeByRepId(signRecord.getRespId());
							//信息内容：监管员姓名、工号、经销商名称、金融机构（总、分、支）、应签到时间、实际签到时间
							SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
							SupervisorBaseInfoVO SupervisorBaseInfoVO=repositoryService.getSupervisorBaseInfoByRepositoryId(signRecord.getRespId());
							if(SupervisorBaseInfoVO!=null){
								messageInfo.setSupervisorName(SupervisorBaseInfoVO.getName());
							}
							List<RosterVO> rosterList=rosterService.searchRosterByRepositoryId(signRecord.getRespId());
							if(rosterList!=null && rosterList.size()>0){
								RosterVO roster=rosterList.get(0);
								if(roster!=null){
									messageInfo.setStaffNo(roster.getStaffNo());
								}
							}
							DealerSupervisorQueryVO dealerSupQuery=new DealerSupervisorQueryVO();
							dealerSupQuery.setSupervisorId(signRecord.getRespId());
							List<DealerSupervisorVO> dealerSupList=dsDao.searchDealerSupervisorList(dealerSupQuery);
							if(dealerSupList!=null && dealerSupList.size()>0){
								String dealerName="";
								String bankName="";
								String brandName="";
								for(DealerSupervisorVO dealerSup:dealerSupList){
									DealerQueryBean dealer=dealerByIdJsonAction.getDealer(dealerSup.getDealerId());
									if(dealer!=null){
										dealerName=dealerName+dealer.getDealerName()+" ";
										bankName=bankName+dealer.getBankName()+" ";
										brandName=brandName+dealer.getBrand()+" ";
									}
								}
								messageInfo.setDealerName(dealerName);
								messageInfo.setBankName(bankName);
								messageInfo.setBrandName(brandName);
							}
							if(attendanceTime!=null){
								messageInfo.setShouldMorningStart(attendanceTime.getMorningStart());
								messageInfo.setShouldMorningEnd(attendanceTime.getMorningEnd());
								messageInfo.setShouldAfternoonStart(attendanceTime.getAfternoonStart());
								messageInfo.setShouldAfternoonEnd(attendanceTime.getAfternoonEnd());
							}
							messageInfo.setActualMorningStart(
									DateUtil.getStringFormatByDate(signRecord.getMorningStart(),"yyyy-MM-dd HH:mm:ss"));
							messageInfo.setActualMorningEnd(
									DateUtil.getStringFormatByDate(signRecord.getMorningEnd(),"yyyy-MM-dd HH:mm:ss"));
							messageInfo.setActualAfternoonStart(
									DateUtil.getStringFormatByDate(signRecord.getAfternoonStart(),"yyyy-MM-dd HH:mm:ss"));
							messageInfo.setActualAfternoonEnd(
									DateUtil.getStringFormatByDate(signRecord.getAfternoonEnd(),"yyyy-MM-dd HH:mm:ss"));
							messageInfo.setContent("监管员连续三天未正常出勤预警");
							sendMesssage(roleIds,MessageTypeContant.SUPERVISORTHREEDAYSIGNABNORMAL.getCode(),2,messageInfo);
						}
					}
				}
			}
		}
	}

	/**
	 * 项目进驻流转单发出后五天未匹配人员信息预警
	 * @throws Exception
	 */
	public void projectCircuNoSupervisorWarning() throws Exception {
		MarketProjectCirculationService MarProCirService=new MarketProjectCirculationService();
		RepositoryService repService=new RepositoryService();
		BankService bankService=new BankService();
		//接收信息角色：监管员管理部经理、招聘专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 5);
		Date endDate = dft.parse(dft.format(date.getTime()));
		List<MarketProjectCirculationVO> MarProCirList=MarProCirService.findListByCreateDate(endDate,2);
		if(MarProCirList!=null && MarProCirList.size()>0){
			for(MarketProjectCirculationVO MarProCir:MarProCirList){
				List<RepositoryDispatchCityVO> repDispatchCityList=repService.findDispatchCityList(MarProCir.getProvince(), MarProCir.getCity(), MarProCir.getDistrict());
				if(repDispatchCityList==null || repDispatchCityList.size()==0){
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					messageInfo.setContent("项目进驻流转单发出后五天未匹配人员信息预警");
					messageInfo.setDealerName(MarProCir.getDealerName());
					BankVO bank = bankService.get(MarProCir.getBankId());
					if(bank!=null){
						messageInfo.setBankName(bank.getBankFullName());
					}
					messageInfo.setExpectedInDate(MarProCir.getInStoreDate());
					sendMesssage(roleIds,MessageTypeContant.PROJECTCIRCUNOSUPERVISORWARNING.getCode(),2,messageInfo);
				}
			}
		}
	}
	/**
	 * 监管员上岗未培训考核预警
	 * @throws Exception
	 */
	public void postNoTrain() throws Exception {
		//接收信息角色：监管员管理部经理、招聘专员、培训专员、业务部经理、业务专员，风控部经理、内控专员、风控专员、视频部经理、视频专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.BUSINESS_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode()+"",
				RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",
				RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
				RoleConstants.WINDCONRTOL_INTERNAL.getCode()+"",
				RoleConstants.WINDCONRTOL_EXTERNAL.getCode()+"",
				RoleConstants.VIDEO_AMALDAR.getCode()+"",
				RoleConstants.VIDEO_COMMISSIONER.getCode()+""};
		RepositoryService  repositoryService=new RepositoryService();
		RosterService rosterService=new RosterService();
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		List<RepositoryVO> repositoryList=repositoryService.findRepositoryByStatus(RepositoryStatus.ALREADYPOST.getCode());
		if(repositoryList!=null && repositoryList.size()>0){
			for(RepositoryVO bean:repositoryList){
				RepositoryTrainVO receiverTrain=repositoryService.loadRepositoryTrainByRepositoryId(bean.getId());
				if(receiverTrain==null){
					SupervisorBaseInfoVO supervisorBaseInfo=repositoryService.getSupervisorBaseInfoByRepositoryId(bean.getId());
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					if(supervisorBaseInfo!=null){
						messageInfo.setSupervisorName(supervisorBaseInfo.getName());
					}
					List<RosterVO> rosters=rosterService.searchRosterByRepositoryId(bean.getId());
					if(rosters!=null && rosters.size()>0){
						messageInfo.setStaffNo(rosters.get(0).getStaffNo());
					}
					DealerSupervisorService dealerSupService=new DealerSupervisorService();
					DealerSupervisorQueryVO dealerSupQuery=new DealerSupervisorQueryVO();
					dealerSupQuery.setSupervisorId(bean.getId());
					List<DealerSupervisorVO> dealerSupervisorList=dealerSupService.searchDealerSupervisorList(dealerSupQuery);
					//TODO 怀疑调动时间为空  是由此条件未达成,记录此日志
					if(dealerSupervisorList!=null && dealerSupervisorList.size()>0){
						StringBuffer dealerName=new StringBuffer();
						StringBuffer bankName=new StringBuffer();
						List<String> dealerIds=new ArrayList<String>();
						for(DealerSupervisorVO dealerSupervisor:dealerSupervisorList){
							DealerQueryBean dealerBean=dealerByIdJsonAction.getDealer(dealerSupervisor.getDealerId());
							if(dealerBean!=null){
								dealerName.append(dealerBean.getDealerName()+" ");
								bankName.append(dealerBean.getBankName()+" ");
							}
							dealerIds.add(String.valueOf(dealerSupervisor.getDealerId()));
						}
						messageInfo.setDealerName(dealerName.toString());
						messageInfo.setBankName(bankName.toString());
						//调入时间
						TransferService transferService=new TransferService();
						List<TransferRepositoryVO> transferRepositoryList=transferService.getSupervisorListByDealerIdAndRepId(dealerIds.toArray(new String[]{}),bean.getId());
						if(transferRepositoryList!=null && transferRepositoryList.size()>0){
							messageInfo.setEntryTime(transferRepositoryList.get(0).getEntryTime());
						}
						messageInfo.setIsTrain("否");
						messageInfo.setContent("监管员上岗未培训考核预警");
						sendMesssage(roleIds,MessageTypeContant.POSTNOTRAIN.getCode(),2,messageInfo);
					}
				}
			}
		}
	}
	/**
	 * 监管员面试完成三日未安排培训预警
	 * @throws Exception
	 */
	public void repositoryNoTrainWarning() throws Exception {
		RepositoryService repService=new RepositoryService();
		//接收信息角色：运营管理部部长、监管员管理部经理、招聘专员、培训专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode()+"",
				RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 3);
		Date endDate = dft.parse(dft.format(date.getTime()));
		RepositoryQueryVO repositoryQueryVO=new RepositoryQueryVO();
		repositoryQueryVO.setCreateTime(endDate);
		repositoryQueryVO.setMsgType(2);
		List<RepositoryVO> repList=repService.repositoryList(repositoryQueryVO);
		if(repList!=null && repList.size()>0){
			for(RepositoryVO rep:repList){
				RepositoryTrainVO train=repService.loadRepositoryTrainByRepositoryId(rep.getId());
				if(train == null){
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					SupervisorBaseInfoVO supBaseInfo=repService.getSupervisorBaseInfoByRepositoryId(rep.getId());
					if(supBaseInfo!=null){
						messageInfo.setSupervisorName(supBaseInfo.getName());
					}
					messageInfo.setInterviewTime(rep.getCreateTime());
					StringBuffer repDisCityStr=new StringBuffer();
					List<RepositoryDispatchCityVO> repDispatchCityList=repService.getRepositoryDispatchCityByRepositoryId(rep.getId());
					if(repDispatchCityList!=null && repDispatchCityList.size()>0){
						for(RepositoryDispatchCityVO repDisCity:repDispatchCityList){
							repDisCityStr.append(repDisCity.getProvince()+" ").append(repDisCity.getCity()+" ").append(repDisCity.getCounty()+"  ");
						}
					}
					messageInfo.setIntentionCity(repDisCityStr.toString());
					messageInfo.setContent("监管员面试完成三日未安排培训预警");
					sendMesssage(roleIds,MessageTypeContant.REPOSITORYNOTRAINWARNING.getCode(),2,messageInfo);
				}
			}
		}
	}
	/**
	 * 监管员辞职离岗时间前五天未提交人员信息预警
	 * @throws Exception
	 */
	public void resignNoHandoverLogWarning() throws Exception {
		ResignApplyService resignService=new ResignApplyService();
		HandoverLogService handoverLogService=new HandoverLogService();
		DealerService dealerService=new DealerService();
		//接收信息角色：运营管理部部长、监管员管理部经理、招聘专员,市场部专员,风控部经理、风控部内控专员
		String[] roleIds=new String[]{
				RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+"",
				RoleConstants.MARKET_COMMISSIONER.getCode()+"",
				RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
				RoleConstants.WINDCONRTOL_INTERNAL.getCode()+""};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE)+5);
		Date endDate = dft.parse(dft.format(date.getTime()));
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE)-25);
		Date handoverLogDate = dft.parse(dft.format(date.getTime()));
		List<ResignApplyBean> resignBeanList=resignService.findListByExpectResignTime(endDate);
		if(resignBeanList!=null && resignBeanList.size()>0){
			for(ResignApplyBean resignBean:resignBeanList){
				HandoverLogQueryVO handoverLogQuery=new HandoverLogQueryVO();
				handoverLogQuery.setCreateDate(handoverLogDate);
				handoverLogQuery.setDeliverer(resignBean.getRepositoryId());
				List<HandoverLogVO> handoverLog=handoverLogService.searchHandoverLogList(handoverLogQuery);
				if(handoverLog==null || handoverLog.size()==0){
					SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
					messageInfo.setSupervisorName(resignBean.getName());
					messageInfo.setStaffNo(resignBean.getStaffNo());
					messageInfo.setDealerName(resignBean.getDealerName());
					messageInfo.setBankName(resignBean.getBankName());
					//经销商地址
					String address="";
					String dealerIdStr=resignBean.getDealerId();
					if(StringUtil.isNotEmpty(dealerIdStr)){
						String[] dealerIds = dealerIdStr.split(",");
						for (String dealerId : dealerIds) {
							if(dealerId!=null){
								DealerVO dealer=dealerService.get(Integer.parseInt(dealerId));
								if(dealer!=null ){
									address=address+" "+dealer.getAddress();
								}
							}
						}
					}
					messageInfo.setAddress(address);
					messageInfo.setResignApplyDate(resignBean.getApplyTime());
					messageInfo.setExpectResignTime(resignBean.getExpectResignTime());
					messageInfo.setContent("监管员辞职离岗时间前五天未提交人员信息预警");
					sendMesssage(roleIds,MessageTypeContant.RESIGNNOHANDOVERLOGWARNING.getCode(),2,messageInfo);
				}
			}
		}
	}
	/**
	 * 监管员在一家店工作六个月预警
	 * @throws Exception
	 */
	public void supWorkSixMonthWarning() throws Exception {
		TransferService transferService=new TransferService();
		DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
		RepositoryService repositoryService=new RepositoryService();
		RosterService rosterService=new RosterService();
		//接收信息角色：监管员管理部经理、调配专员,风控部经理、内控专员
		String[] roleIds=new String[]{
				RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
				RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()+"",
				RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
				RoleConstants.WINDCONRTOL_INTERNAL.getCode()+"",};
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.MONTH, date.get(Calendar.MONTH)-6);
		Date endDate = dft.parse(dft.format(date.getTime()));
		List<TransferRepositoryVO> transferRepList=transferService.getSupervisorListByEntryTime(endDate,2);
		if(transferRepList!=null && transferRepList.size()>0){
			for(TransferRepositoryVO transferRep:transferRepList){
				SupMaMsgInfoVO messageInfo=new SupMaMsgInfoVO();
				DealerQueryBean dealerBean=dealerByIdJsonAction.getDealer(transferRep.getDealerId());
				if(dealerBean!=null){
					messageInfo.setDealerName(dealerBean.getDealerName());
					messageInfo.setBankName(dealerBean.getBankName());
				}
				SupervisorBaseInfoVO SupervisorBaseInfoVO=repositoryService.getSupervisorBaseInfoByRepositoryId(transferRep.getRepositoryId());
				if(SupervisorBaseInfoVO!=null){
					messageInfo.setSupervisorName(SupervisorBaseInfoVO.getName());
				}
				List<RosterVO> rosterList=rosterService.searchRosterByRepositoryId(transferRep.getRepositoryId());
				if(rosterList!=null && rosterList.size()>0){
					RosterVO roster=rosterList.get(0);
					if(roster!=null){
						messageInfo.setStaffNo(roster.getStaffNo());
					}
				}
				messageInfo.setEntryTime(transferRep.getEntryTime());
				//轮岗到期时间是进店时间后6个月
				date.setTime(transferRep.getEntryTime());
				date.set(Calendar.MONTH, date.get(Calendar.MONTH)+6);
				Date handoverDuedate = dft.parse(dft.format(date.getTime()));
				messageInfo.setHandoverDuedate(handoverDuedate);
				//计算超期时间
				int days = (int) ((new Date().getTime() - handoverDuedate.getTime()) / (1000*3600*24));
				messageInfo.setHandoverOverDuedate(days);
				messageInfo.setContent("监管员在一家店工作六个月预警");
				sendMesssage(roleIds,MessageTypeContant.SUPWORKSIXMONTHWARNING.getCode(),2,messageInfo);
			}
		}
	}
	
}
