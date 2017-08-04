package com.zd.csms.supervisorymanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.service.BusinessTransferService;
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
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageVO;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageVO;
import com.zd.csms.supervisory.service.costmail.SupervisorCostMailMessageService;
import com.zd.csms.supervisory.service.repairecostms.SupervisorRepairCostMessageService;
import com.zd.csms.supervisorymanagement.dao.IMailcostDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.MailcostMapper;
import com.zd.csms.supervisorymanagement.model.MailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.MailcostVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class MailcostService extends ServiceSupport {

	private IMailcostDAO dao = SupervisorDAOFactory.getMailcostDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private BusinessTransferService businessTransferService = new BusinessTransferService();
	
	public boolean addMailcost(MailcostVO vo) throws Exception {
		boolean flag = false;
		vo.setId(Util.getID(MailcostVO.class));
		flag=dao.add(vo);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public boolean updMailcost(MailcostVO vo) throws Exception {
		MailcostVO bean = this.loadMailcostById(vo.getId());
		bean.setPromoter(vo.getPromoter());
		bean.setFqdate(vo.getFqdate());
		bean.setMailingitems(vo.getMailingitems());
		bean.setParts(vo.getParts());
		bean.setOther(vo.getOther());
		bean.setMailperson(vo.getMailperson());
		bean.setExpress(vo.getExpress());
		bean.setMoney(vo.getMoney());
		bean.setTransportbegin(vo.getTransportbegin());
		bean.setTransportend(vo.getTransportend());
		bean.setReceiveid(vo.getReceiveid());
		bean.setDes(vo.getDes());
		bean.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		boolean flag=dao.update(vo);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}
	
	public boolean deleteMailcost(int id) throws Exception {
		boolean flag=dao.delete(MailcostVO.class, id);
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
    }
	
	public MailcostVO loadMailcostById(int id) throws Exception{
		MailcostVO mailcost = dao.get(MailcostVO.class, id,new MailcostMapper());
		return mailcost;
	}
	
	public List<MailcostVO> searchMailcostList(MailcostQueryVO query){
		return dao.searchMailcostList(query);
	}
	
	//
	public List<MailcostVO> searchMailcostListByPage(MailcostQueryVO vo, IThumbPageTools tools){
		List<MailcostVO> mailcostList=dao.searchMailcostListByPage(vo, tools);
		if(mailcostList!=null && mailcostList.size()>0){
			for(MailcostVO  mailcost:mailcostList){
				String mailItem="";
				if(mailcost.getMailingitems().contains("1")){
					mailItem=mailItem+" "+"保险柜";
				}
				if(mailcost.getMailingitems().contains("2")){
					mailItem=mailItem+" "+"笔记本电脑";
				}
				if(mailcost.getMailingitems().contains("3")){
					mailItem=mailItem+" "+"配件";
				}
				if(mailcost.getMailingitems().contains("4")){
					mailItem=mailItem+" "+"资料";
				}
				if(mailcost.getMailingitems().contains("5")){
					mailItem=mailItem+" "+"其他";
				}
				mailcost.setMailingitems(mailItem);
			}
			
		}
		return mailcostList;
	}
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean approval(MailcostQueryVO query,UserSession user) throws Exception{
		MailcostVO bean = dao.get(MailcostVO.class, query.getId(),new MailcostMapper());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			try {
				MessageUtil.addMsg(bean.getPromoter(), "邮寄费用申请审批不通过", "/mailcost.do?method=findList&mailcostquery.pageType=2", 1,MessageTypeContant.POSTAGEREQUISITION.getCode(),bean.getCreateuserid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole) ){
				//监管员管理部经理
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+""},
						"邮寄费用申请", "/mailcost.do?method=findList", 1,MessageTypeContant.POSTAGEREQUISITION.getCode(),bean.getCreateuserid());
				
			}else if(bean.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				//运营管理部部长
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""},
						"邮寄费用申请", "/mailcost.do?method=findList", 1,MessageTypeContant.POSTAGEREQUISITION.getCode(),bean.getCreateuserid());
				
			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//物流金融中心总监
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()+""},
						"邮寄费用申请", "/mailcost.do?method=findList", 1,MessageTypeContant.POSTAGEREQUISITION.getCode(),bean.getCreateuserid());

			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(), currRole)){
				//监管员管理部薪酬专员
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()+"",
						RoleConstants.FINANCE_ACCOUNTING.getCode()+""},
						"邮寄费用申请审批完成", "/mailcost.do?method=findList&mailcostquery.pageType=2", 1,MessageTypeContant.POSTAGEREQUISITION.getCode(),bean.getCreateuserid());

			}
		}
		flag=dao.update(bean);
		if(flag){
			//审批记录表
			MarketApprovalVO approval = new MarketApprovalVO();
			approval.setId(Util.getID(MarketApprovalVO.class));
			approval.setApprovalObjectId(query.getId());
			approval.setApprovalPerson(userVO.getUserName());
			approval.setApprovalType(ApprovalTypeContant.POSTAGEREQUISITION.getCode());
			approval.setCreateDate(new Date());
			approval.setRemark(query.getRemark());
			approval.setApprovalResult(query.getApprovalState());
			approval.setApprovalUserId(user.getUser().getId());
			approval.setApprovalUserRole(currRole);
			flag=dao.add(approval);
		}
		return flag;
	}
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submit(int id) throws Exception{
		MailcostVO bean =  loadMailcostById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.POSTAGEREQUISITION.getCode());
		bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+""},
				"邮寄费用申请", "/mailcost.do?method=findList", 1,MessageTypeContant.POSTAGEREQUISITION.getCode(),bean.getCreateuserid());

		return dao.update(bean);
	}
	
	/**
	 * 提交,邮寄费用申请提醒
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submitAndCostMail(int id,SupervisorCostMailMessageVO srcmvo) throws Exception{
		MailcostVO bean =  loadMailcostById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.POSTAGEREQUISITION.getCode());
		bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		boolean flag = false;
		RoleService rs = new RoleService();
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		//综合专员
		urquery.setRole_id(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
		List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
        flag = addSRCMmessage(urList, srcmvo);
        //监管员管理部经理
        if (flag) {
        	urquery.setRole_id(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
    		urList = rs.searchUserRoleList(urquery);
            flag = addSRCMmessage(urList, srcmvo);
		}
        //财务部经理
        if (flag) {
        	urquery.setRole_id(RoleConstants.FINANCE_AMALDAR.getCode());
    		urList = rs.searchUserRoleList(urquery);
            flag = addSRCMmessage(urList, srcmvo);
		}
        //会计
        if (flag) {
        	urquery.setRole_id(RoleConstants.FINANCE_ACCOUNTING.getCode());
    		urList = rs.searchUserRoleList(urquery);
            flag = addSRCMmessage(urList, srcmvo);
		}
        
        if (flag) {
        	flag=dao.update(bean);
		}
        if(flag){
			this.setExecuteMessage("提交成功！");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}
	
	  //邮寄费用申请提醒
		private boolean addSRCMmessage(List<UserRoleVO> urList,SupervisorCostMailMessageVO srcmvo) throws Exception{
			MessageService ms = new MessageService();
			SupervisorCostMailMessageService srcmService = new SupervisorCostMailMessageService();
			UserService userService = new UserService();
			UserVO userVO = null;
			boolean flag = false;
			if(urList != null && urList.size()>0){
				for(UserRoleVO ur : urList){
					userVO = userService.get(ur.getUser_id());
					if (userVO != null && userVO.getState() == 1) {
						MessageVO msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVISCOSTMAIL.getCode());
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
								flag = srcmService.addCostMailMessage(srcmvo);
								if (!flag) {
									return false;
								}
							}else {
								this.setExecuteMessage("提交失败");
							}
						}else {
							MessageUtil.addMsg(ur.getUser_id(),String.valueOf(1), MsgUrlContant.SUPERRVISEMAILCOST.getName(), 1,MsgIsContants.NOREAD.getCode(),MessageTypeContant.SUPSERVISCOSTMAIL.getCode(),ClientTypeConstants.SUPERVISORY.getName());
							msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVISCOSTMAIL.getCode());
							srcmvo.setUserId(ur.getUser_id());
							srcmvo.setMessageId(msVo.getId());
							flag = srcmService.addCostMailMessage(srcmvo);
						}
					}
				}
			}
			return flag;
		}

}
