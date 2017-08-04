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
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.contants.MsgUrlContant;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.overtime.SupervisorOverTimeMessageVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisory.service.overtime.SupervisorOverTimeMessageService;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.dao.IExtraworkApplyDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyBean;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyVO;
import com.zd.csms.supervisorymanagement.model.MailCostJsonVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ExtraworkApplyService  extends ServiceSupport {
	
	private IExtraworkApplyDAO dao = SupervisoryManagementDAOFactory.getExtraworkApplyDAO();
	RepositoryService repositoryService=new RepositoryService();
	SupervisoryService supervisoryService=new SupervisoryService();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	
	public List<ExtraworkApplyBean> findPageList(ExtraworkApplyQueryVO query,
			IThumbPageTools tools) {
		List<ExtraworkApplyBean> list=new ArrayList<ExtraworkApplyBean>();
		List<ExtraworkApplyVO> extraworkApplyList=dao.findPageList(query, tools);
		if(extraworkApplyList!=null && extraworkApplyList.size()>0){
			for(ExtraworkApplyVO extraworkApply:extraworkApplyList){
				ExtraworkApplyBean bean=new ExtraworkApplyBean();;
				BeanUtils.copyProperties(extraworkApply, bean);
				String dealerName="";
				String bankName="";
				String brandName="";
				List<CurrentDealerVO> dealerList=dao.findCurrentDealerListByExtraworkApplyId(extraworkApply.getId(), CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
				if(dealerList!=null && dealerList.size()>0){
					for(CurrentDealerVO dealer:dealerList){
						dealerName=dealerName+" "+dealer.getDealerName();
						bankName=bankName+" "+dealer.getBankName();
						brandName=brandName+" "+dealer.getBrandName();
					}
				}
				bean.setDealerName(dealerName);
				bean.setBankName(bankName);
				bean.setBrandName(brandName);
				list.add(bean);
			}
		}
		return list;
	}
	public ExtraworkApplyVO get(int extraworkApplyId) {
		return dao.get(ExtraworkApplyVO.class, extraworkApplyId, new BeanPropertyRowMapper(ExtraworkApplyVO.class));
	}

	public boolean addExtraworkApply(ExtraworkApplyVO extraworkApply) {
		try {
			extraworkApply.setId(Util.getID(ExtraworkApplyVO.class));
			boolean flag=dao.add(extraworkApply);
			if(flag){
				this.setExecuteMessage("新增成功！");
			}else{
				this.setExecuteMessage("新增失败！");
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateExtraworkApply(ExtraworkApplyVO extraworkApply) {
		boolean flag=dao.update(extraworkApply);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}

	public boolean deleteExtraworkApply(int extraworkApplyId) {
		boolean flag=dao.deleteCurrentDealerByExtraworkApply(extraworkApplyId,CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		if(flag){
			flag=dao.delete(ExtraworkApplyVO.class, extraworkApplyId);
		}
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
	}
	public boolean addCurrentDealerList(List<CurrentDealerVO> dealerList) {
		boolean flag=false;
		if(dealerList!=null && dealerList.size()>0){
			for(CurrentDealerVO dealer:dealerList){
				try {
					dealer.setId(Util.getID(CurrentDealerVO.class));
					flag=true && dao.add(dealer);
					if(!flag){
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			flag=true;
		}
		return flag;
	}

	public List<CurrentDealerVO> findCurrentDealerListByExtraworkApplyId(
			int extraworkApplyId, int type) {
		return dao.findCurrentDealerListByExtraworkApplyId(extraworkApplyId,type);
	}

	public boolean submit(int id) {
		ExtraworkApplyVO extraworkApply=get(id);
		
		//储备库Id
		RepositoryService sepositoryService = new RepositoryService();
		RepositoryVO repositoryVO = sepositoryService.load(extraworkApply.getRepositoryId());
		//监管员的Id
		SupervisoryService sservice = new SupervisoryService();
		int supervisorId = repositoryVO.getSupervisorId();
		SupervisorBaseInfoVO  supervisorVO = sservice.getBaseInfo(supervisorId);
		//经销商，金融机构
		MailCostJsonVO mJsonVO = null;
		try {
			mJsonVO = getBankAndMerchant(extraworkApply.getRepositoryId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//提醒信息
		SupervisorOverTimeMessageVO srCostVO = new SupervisorOverTimeMessageVO();
		srCostVO.setIsread(MsgIsContants.NOREAD.getCode());
		srCostVO.setJobnumber(extraworkApply.getStaffNo());
		srCostVO.setMerchantname(mJsonVO.getJxs());
		srCostVO.setBankname(mJsonVO.getJrjg());
		srCostVO.setName(supervisorVO.getName());
		srCostVO.setSupervisorid(supervisorId);
		srCostVO.setMessagetype(MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getName());
		srCostVO.setCreateDate(new Date());
		srCostVO.setBeginDate(extraworkApply.getStartTime());
		srCostVO.setEndDate(extraworkApply.getEndTime());

		try {
			if(ApprovalContant.APPROVAL_NOPASS.getCode()==extraworkApply.getApprovalState()){
				marketDao.deleteApprovalByDealerId(extraworkApply.getId(), ApprovalTypeContant.SUPERVISORYEXTRAWORKAPPLY.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag= dao.updateExtraworkApplyStatus(id, 1, ApprovalContant.APPROVAL_WAIT.getCode(), RoleConstants.BUSINESS_COMMISSIONER.getCode());
		if(flag){
			this.setExecuteMessage("提交成功！");
			sedMessageAndOverTimeMes(RoleConstants.BUSINESS_COMMISSIONER.getCode(),srCostVO);
			sedMessageAndOverTimeMes(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),srCostVO);
			sedMessageAndOverTimeMes(RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),srCostVO);
			sedMessageAndOverTimeMes(RoleConstants.BUSINESS_AMALDAR.getCode(),srCostVO);
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}

	/**
	 * 金融机构
	 * @param repositoryId
	 * @return
	 * @throws Exception
	 */
	public MailCostJsonVO getBankAndMerchant(int repositoryId) throws Exception{
		String jxs = "";
		String jrjg = "";
		DealerSupervisorService dsservice = new DealerSupervisorService();
		DealerService dservice = new DealerService();
		DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
		dsquery.setSupervisorId(repositoryId);
		List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
		if(dsList != null && dsList.size()>0){
			for(DealerSupervisorVO ds:dsList){
				DealerVO dvo = dservice.loadDealerById( ds.getDealerId());
				if(dvo != null){
					jxs =jxs+" "+ dvo.getDealerName();
				}
				jrjg =jrjg+" "+ dservice.getBankNameByDealerId( ds.getDealerId());
			}
		}
		
		MailCostJsonVO mcvo = new MailCostJsonVO();
		mcvo.setJxs(jxs);
		mcvo.setJrjg(jrjg);
		
		return mcvo;
	}
	
	
	public boolean approval(ExtraworkApplyQueryVO query, UserSession user) {
		ExtraworkApplyVO bean = get(query.getId());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrentRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			//将加班申请单状态修改为可编辑状态
			bean.setStatus(0);
			//给发起人发送消息
			try {
				MessageUtil.addMsg(bean.getCreateUser(), "加班申请审批不通过", "/extraworkApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.EXTRAWORKAPPLY.getCode(),bean.getCreateUser());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务部专员
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole) ){
				//业务部经理
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode());
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(), currRole) ){
				//考勤专员
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole) ){
				//监管员经理
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			}else if(bean.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				//运营部部长
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				//给发起人发送消息
				try {
					MessageUtil.addOrUpdateMeg(bean.getCreateUser(), "加班申请审批通过", "/extraworkApply.do?method=findPageList&query.pageType=2", 1,MessageTypeContant.EXTRAWORKAPPLY.getCode(),bean.getCreateUser());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		flag=dao.update(bean);
		if(flag){
			//审批记录表
			try {
				MarketApprovalVO approval = new MarketApprovalVO();
				approval.setId(Util.getID(MarketApprovalVO.class));
				approval.setApprovalObjectId(query.getId());
				approval.setApprovalPerson(userVO.getUserName());
				approval.setApprovalType(ApprovalTypeContant.SUPERVISORYEXTRAWORKAPPLY.getCode());
				approval.setCreateDate(new Date());
				approval.setRemark(query.getRemark());
				approval.setApprovalResult(query.getApprovalState());
				approval.setApprovalUserId(user.getUser().getId());
				approval.setApprovalUserRole(currRole);
				flag=dao.add(approval);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(flag){
			this.setExecuteMessage("审批操作成功！");
			
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_WAIT.getCode()){
				try{
					MessageUtil.sendOrUpdateMeg(new String[]{bean.getNextApproval()+""}, "监管员"+bean.getName()+"加班申请",
							"/extraworkApply.do?method=findPageList", 1,MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getCode(),bean.getCreateUser());
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		
		}else{
			this.setExecuteMessage("审批操作失败！");
		}
		return flag;
	}
	public void sedMessageAndOverTimeMes(int roleId,SupervisorOverTimeMessageVO srCostVO){
		RoleService rs = new RoleService();
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		urquery.setRole_id(roleId);
		List<UserRoleVO> urList;
		boolean flag = false;
		try {
			urList = rs.searchUserRoleList(urquery);
			flag = addSRCMmessage(urList,srCostVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 //邮寄费用申请提醒
	private boolean addSRCMmessage(List<UserRoleVO> urList,SupervisorOverTimeMessageVO srcmvo) throws Exception{
		MessageService ms = new MessageService();
		SupervisorOverTimeMessageService srcmService = new SupervisorOverTimeMessageService();
		UserService userService = new UserService();
		UserVO userVO = null;
		boolean flag = false;
		if(urList != null && urList.size()>0){
			for(UserRoleVO ur : urList){
				userVO = userService.get(ur.getUser_id());
				if (userVO != null && userVO.getState() == 1) {
					MessageVO msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getCode());
					if (msVo != null) {
						//该用户该类型提醒信息数量增加1
						msVo.setName(String.valueOf(Integer.parseInt(msVo.getName())+1));//数量增加1
						if (msVo.getIsread()==2) {
							msVo.setIsread(1);
						}
						flag = ms.updMessage(msVo);
						if (flag) {
							srcmvo.setUserId(ur.getUser_id());
							srcmvo.setMessageId(msVo.getId());
							flag = srcmService.addOverTimeMessage(srcmvo);
							if (!flag) {
								return false;
							}
						}else {
							this.setExecuteMessage("提交失败");
						}
					}else {
						MessageUtil.addMsg(ur.getUser_id(),String.valueOf(1),MsgUrlContant.SUPERRVISEMAILOVER.getName(), 1,MsgIsContants.NOREAD.getCode(),MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getCode(),ClientTypeConstants.SUPERVISORY.getName());
						msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVISOVERTIMEAPPLICATION.getCode());
						srcmvo.setUserId(ur.getUser_id());
						srcmvo.setMessageId(msVo.getId());
						flag = srcmService.addOverTimeMessage(srcmvo);
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 根据储备库ID和年月查询该监管员该月加班天数
	 * @param repositoryId
	 * @param year
	 * @param month
	 * @return
	 */
	public double getExtraworkDaysByRepositoryIdAndMonth(int repositoryId, int year, int month) {
		double extraworkDays=0;
		Date MonthStartDay = new Date();
		Date MonthEndDay = new Date();
		GregorianCalendar gc = new GregorianCalendar(year,month-1,1);
		MonthStartDay = gc.getTime();
		gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));//设置天
		gc.set(Calendar.HOUR_OF_DAY, gc.getActualMaximum(Calendar.HOUR_OF_DAY));//设置小时
		gc.set(Calendar.MINUTE,gc.getActualMaximum(Calendar.MINUTE));//分
		gc.set(Calendar.SECOND,gc.getActualMaximum(Calendar.SECOND));//秒
		MonthEndDay=gc.getTime();
		List<ExtraworkApplyVO> extraworkApplyList=dao.findExtraworkListByRepositoryIdAndMonth(repositoryId,year,month);
		for(ExtraworkApplyVO extraworkApply:extraworkApplyList){
			if(extraworkApply.getStartTime().before(MonthStartDay)){
				Calendar c = Calendar.getInstance();
				c.setTime(extraworkApply.getEndTime());
				int day = c.get(Calendar.DAY_OF_MONTH);
				extraworkDays=extraworkDays+day;
			}else if(extraworkApply.getEndTime().after(MonthEndDay)){
				Calendar c = Calendar.getInstance();
				c.setTime(extraworkApply.getStartTime());
				int startDay = c.get(Calendar.DAY_OF_MONTH);
				c.setTime(MonthEndDay);
				int endDay=c.get(Calendar.DAY_OF_MONTH);
				int day=endDay-startDay+1;
				extraworkDays=extraworkDays+day;
			}else{
				extraworkDays=extraworkDays+extraworkApply.getExtraWorkDays();
			}
		}
		return extraworkDays;
	}
}
