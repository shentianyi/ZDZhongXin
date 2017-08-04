package com.zd.csms.supervisorymanagement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.contants.HandoverLogApprovalRoleContants;
import com.zd.csms.supervisorymanagement.contants.LeaveApplyApprovalRoleContants;
import com.zd.csms.supervisorymanagement.dao.IExtraworkApplyDAO;
import com.zd.csms.supervisorymanagement.dao.ILeaveApplyDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyBean;
import com.zd.csms.supervisorymanagement.model.LeaveApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyVO;
import com.zd.csms.supervisorymanagement.model.LeaveReplaceDynamicVO;
import com.zd.csms.supervisorymanagement.model.LeaveReplaceVO;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.dynamic.DynamicStateFlagConstants;
import com.zd.tools.thumbPage.IThumbPageTools;

public class LeaveApplyService  extends ServiceSupport {
	
	private ILeaveApplyDAO dao = SupervisoryManagementDAOFactory.getLeaveApplyDAO();
	private IExtraworkApplyDAO extraworkApplyDAO = SupervisoryManagementDAOFactory.getExtraworkApplyDAO();
	private SupervisoryService supervisoryService=new SupervisoryService();
	private RepositoryService repositoryService=new RepositoryService();
	private DealerSupervisorService  dealerSupervisorService=new DealerSupervisorService();
	private DealerService dealerService=new DealerService();
	private ExtraworkApplyService extraworkApplyService = new ExtraworkApplyService();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	
	public boolean addLeaveApply(LeaveApplyVO leaveApply)throws Exception{
		leaveApply.setId(Util.getID(LeaveApplyVO.class));
		boolean flag=dao.add(leaveApply);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public boolean updateLeaveApply(LeaveApplyVO leaveApply) {
		boolean flag=dao.update(leaveApply);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}
	
	public boolean deleteLeaveApply(int leaveApplyId){
		boolean flag=false;
		try{
			flag=dao.deleteLeaveReplaceListByLeaveApplyId(leaveApplyId);
			if(flag){
				flag=extraworkApplyDAO.deleteCurrentDealerByExtraworkApply(leaveApplyId,CurrentDealerTypeContants.LEAVEAPPLY.getCode());
				if(flag){
					flag=dao.delete(LeaveApplyVO.class, leaveApplyId);
				}
			}
			if(flag){
				this.setExecuteMessage("删除成功！");
			}else{
				this.setExecuteMessage("删除失败！");
			}
		} catch(Exception e){
			e.printStackTrace();
		} 
		return flag;
	}
	/**
	 * 批量修改示例
	 * @param list 示例集合
	 * @return boolean
	 * */
	public boolean manageLeaveReplace(List<LeaveReplaceDynamicVO> list){
		boolean flag = false;
		try {
			//循环处理传入的示例集合
			LeaveReplaceDynamicVO vo;
			int stateFlag = -1;
			int size = list.size();
			for(int i=0; i<size; i++){
				vo = list.get(i);
				stateFlag = vo.getStateFlag();
				LeaveReplaceVO leaveReplace=new LeaveReplaceVO();
				BeanUtils.copyProperties(vo, leaveReplace);
				//通过stateflag判断需要进行那种操作
				if(stateFlag == DynamicStateFlagConstants.ADD.getCode()){
					//新增操作
					flag = addLeaveReplace(leaveReplace);
				} else if(stateFlag == DynamicStateFlagConstants.UPDATE.getCode()){
					flag = updLeaveReplace(leaveReplace);
				} else if(stateFlag == DynamicStateFlagConstants.DELETE.getCode()){
					flag = delLeaveReplace(leaveReplace.getId());
				} else if(stateFlag == DynamicStateFlagConstants.NORMAL.getCode()){
					flag = true;
				}else if(stateFlag == DynamicStateFlagConstants.TEMPLET.getCode()){
					flag = true;
				} else{
					flag = false;
					this.setExecuteMessage("错误的操作状态:"+vo.getStateFlag());
				}
				//出现操作失败时停止后续操作跳出循环
				if(!flag){
					break;
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	
	private boolean delLeaveReplace(int id) {
		boolean flag=dao.delete(LeaveReplaceVO.class, id);
		return flag;
	}
	private boolean updLeaveReplace(LeaveReplaceVO leaveReplace) {
		boolean flag=dao.update(leaveReplace);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}
	public boolean addLeaveReplace(LeaveReplaceVO leaveReplace)throws Exception{
		
		leaveReplace.setId(Util.getID(LeaveReplaceVO.class));
		boolean flag=dao.add(leaveReplace);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}

	public List<LeaveApplyBean> findPageList(LeaveApplyQueryVO query,
			IThumbPageTools tools) {
		List<LeaveApplyVO> leaveApplyList=dao.findPageList(query,tools);
		List<LeaveApplyBean> list=new ArrayList<LeaveApplyBean>();
		if(leaveApplyList!=null && leaveApplyList.size()>0){
			for(LeaveApplyVO leaveApply:leaveApplyList){
				LeaveApplyBean leaveApplyBean=new LeaveApplyBean();
				BeanUtils.copyProperties(leaveApply, leaveApplyBean);
				//经销商
				String dealerName="";
				List<CurrentDealerVO> currentDealerList=extraworkApplyService.findCurrentDealerListByExtraworkApplyId(leaveApply.getId(),CurrentDealerTypeContants.LEAVEAPPLY.getCode());
				if(currentDealerList!=null && currentDealerList.size()>0){
					for(CurrentDealerVO currentDealer:currentDealerList){
						dealerName=dealerName+" "+currentDealer.getDealerName();
					}
				}
				leaveApplyBean.setDealerName(dealerName);
				//替岗人
				String leaveReplaceName="";
				List<LeaveReplaceVO> leaveReplaceList= findLeaveReplaceListByLeaveApplyId(leaveApply.getId());
				if(leaveReplaceList!=null && leaveReplaceList.size()>0){
					for(LeaveReplaceVO leaveReplace:leaveReplaceList){
						SupervisorBaseInfoVO supervisor=repositoryService.getSupervisorBaseInfoByRepositoryId(leaveReplace.getReplaceSupervisory());
						if(supervisor!=null){
							leaveReplaceName=leaveReplaceName+" "+supervisor.getName();
						}
					}
				}
				leaveApplyBean.setLeaveReplace(leaveReplaceName);
				list.add(leaveApplyBean);
			}
		}
		return list;
	}
	
	public List<LeaveReplaceVO> findLeaveReplaceListByLeaveApplyId(int leaveApplyId) {
		return dao.findLeaveReplaceListByLeaveApplyId(leaveApplyId);
	}
	public LeaveApplyVO get(int leaveApplyId) {
		return dao.get(LeaveApplyVO.class, leaveApplyId,new BeanPropertyRowMapper(LeaveApplyVO.class));
	}

	public boolean submit(int id) {
		LeaveApplyVO leaveApply=get(id);
		/*try {
			if(ApprovalContant.APPROVAL_NOPASS.getCode()==leaveApply.getApprovalState()){
				marketDao.deleteApprovalByDealerId(leaveApply.getId(), ApprovalTypeContant.SUPERVISORYLEAVEAPPLY.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		boolean flag=dao.updateLeaveApplyStatus(id,1,ApprovalContant.APPROVAL_WAIT.getCode(),RoleConstants.BUSINESS_COMMISSIONER.getCode());
		if(flag){
			this.setExecuteMessage("提交成功！");
			//给业务专员发消息
			try{
				sedMessage(RoleConstants.BUSINESS_COMMISSIONER.getCode(),leaveApply.getLeavePerson(),leaveApply.getCreateUser());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return false;
	}

	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean approval(LeaveApplyQueryVO query,UserSession user) throws Exception{
		LeaveApplyVO bean = get(query.getId());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrentRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			//将请假申请单状态修改为可编辑状态
			bean.setStatus(0);
			//给发起人发送消息
			try {
				MessageUtil.addMsg(bean.getCreateUser(), "请假申请申请审批不通过", "/leaveApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.LEAVEAPPLY.getCode(),bean.getCreateUser());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务部专员
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_AMALDAR.getCode()+""},
						"请假申请申请审批通过", "/leaveApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.LEAVEAPPLY.getCode(),bean.getCreateUser());
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)){
				//业务部经理
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+""},
						"请假申请申请审批通过", "/leaveApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.LEAVEAPPLY.getCode(),bean.getCreateUser());
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole)){
				//监管员管理部经理
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()+""},
						"请假申请申请审批通过", "/leaveApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.LEAVEAPPLY.getCode(),bean.getCreateUser());
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(), currRole)){
				//考勤专员
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				//给发起人发送消息
				try {
					MessageUtil.addMsg(bean.getCreateUser(), "请假申请申请审批通过", "/leaveApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.LEAVEAPPLY.getCode(),bean.getCreateUser());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//给所有人发送消息提醒 除银行，品牌集团，经销商集团
				try {
					for(LeaveApplyApprovalRoleContants approvalRole:LeaveApplyApprovalRoleContants.values()){
						String userid = userRoleDao.findUsingUserIdByRole(approvalRole.getCode()+"");
						if(StringUtil.isNotEmpty(userid)){
							String[] userids = userid.split(",");
							for (String uid : userids) {
								MessageUtil.addOrUpdateMeg(Integer.parseInt(uid), "请假申请申请审批通过", "/leaveApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.LEAVEAPPLY.getCode(),bean.getCreateUser());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		flag=dao.update(bean);
		if(flag){
			//审批记录表
			MarketApprovalVO approval = new MarketApprovalVO();
			approval.setId(Util.getID(MarketApprovalVO.class));
			approval.setApprovalObjectId(query.getId());
			approval.setApprovalPerson(userVO.getUserName());
			approval.setApprovalType(ApprovalTypeContant.SUPERVISORYLEAVEAPPLY.getCode());
			approval.setCreateDate(new Date());
			approval.setRemark(query.getRemark());
			approval.setApprovalResult(query.getApprovalState());
			approval.setApprovalUserId(user.getUser().getId());
			approval.setApprovalUserRole(currRole);
			flag=dao.add(approval);
		}
		if(flag){
			this.setExecuteMessage("审批操作成功！");
			
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_WAIT.getCode()){
				try{
					sedMessage(bean.getNextApproval(),bean.getLeavePerson(),bean.getCreateUser());
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}else{
			this.setExecuteMessage("审批操作失败！");
		}
		return flag;
	}
	public void sedMessage(int roleId,int leavePerson,int createUser){
		String leavePersonName="";
		RepositoryVO repository=repositoryService.load(leavePerson);
		if(repository!=null){
			SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
			if(supervisorBaseInfoVO!=null){
				//当前监管员名称
				leavePersonName=supervisorBaseInfoVO.getName();
			}
		}
		MessageUtil.sendMsg(new String[]{roleId+""}, "监管员"+leavePersonName+"请假申请", 
				"/leaveApply.do?method=findPageList", 1,MessageTypeContant.LEAVEAPPLY.getCode(),createUser);
	}

	public double getReplaceDaysByRepositoryIdAndMonth(int repositoryId, int year, int month) {
		double replaceDays=0;
		Date MonthStartDay = new Date();
		Date MonthEndDay = new Date();
		GregorianCalendar gc = new GregorianCalendar(year,month-1,1);
		MonthStartDay = gc.getTime();
		gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));//设置天
		gc.set(Calendar.HOUR_OF_DAY, gc.getActualMaximum(Calendar.HOUR_OF_DAY));//设置小时
		gc.set(Calendar.MINUTE,gc.getActualMaximum(Calendar.MINUTE));//分
		gc.set(Calendar.SECOND,gc.getActualMaximum(Calendar.SECOND));//秒
		MonthEndDay=gc.getTime();
		List<LeaveReplaceVO> leaveReplaceList=dao.findLeaveReplaceListByRepositoryIdAndMonth(repositoryId,year,month);
		for(LeaveReplaceVO leaveReplace:leaveReplaceList){
			if(leaveReplace.getReplaceStartTime().before(MonthStartDay)){
				Calendar c = Calendar.getInstance();
				c.setTime(leaveReplace.getReplaceEndTime());
				int day = c.get(Calendar.DAY_OF_MONTH);
				replaceDays=replaceDays+day;
			}else if(leaveReplace.getReplaceEndTime().after(MonthEndDay)){
				Calendar c = Calendar.getInstance();
				c.setTime(leaveReplace.getReplaceStartTime());
				int startDay = c.get(Calendar.DAY_OF_MONTH);
				c.setTime(MonthEndDay);
				int endDay=c.get(Calendar.DAY_OF_MONTH);
				int day=endDay-startDay+1;
				replaceDays=replaceDays+day;
			}else{
				replaceDays=replaceDays+leaveReplace.getReplaceDays();
			}
		}
		return replaceDays;
	}
	
	
}
