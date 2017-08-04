package com.zd.csms.supervisorymanagement.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.DataMailcostReceiverTypeContants;
import com.zd.csms.supervisorymanagement.dao.IDataMailcostDAO;
import com.zd.csms.supervisorymanagement.model.DataMailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostToExtVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class DataMailcostService extends ServiceSupport {

	private IDataMailcostDAO dao = SupervisorDAOFactory.getDataMailcostDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private UserService userService=new UserService();
	public boolean addDataMailcost(DataMailcostVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(DataMailcostVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updDataMailcost(DataMailcostVO vo) throws Exception {
		/*DataMailcostVO bean = this.loadDataMailcostById(vo.getId());
		bean.setPromoter(vo.getPromoter());
		bean.setFqDate(vo.getFqDate());
		bean.setMailItems(vo.getMailItems());
		bean.setParts(vo.getParts());
		bean.setOther(vo.getOther());
		bean.setMailPerson(vo.getMailPerson());
		bean.setExpress(vo.getExpress());
		bean.setMoney(vo.getMoney());
		bean.setTransportBegin(vo.getTransportBegin());
		bean.setTransportEnd(vo.getTransportEnd());
		bean.setReceiveid(vo.getReceiveid());
		bean.setDes(vo.getDes());
		bean.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());*/
		return dao.update(vo);
	}
	
	public boolean deleteDataMailcost(int id) throws Exception {
		return dao.delete(DataMailcostVO.class, id);
    }
	
	public DataMailcostVO loadDataMailcostById(int id) throws Exception{
		DataMailcostVO mailcost = dao.get(DataMailcostVO.class, id,new BeanPropertyRowMapper(DataMailcostVO.class));
		return mailcost;
	}
	
	public List<DataMailcostVO> searchDataMailcostList(DataMailcostQueryVO query){
		return dao.searchDataMailcostList(query);
	}
	
	public List<DataMailcostQueryVO> searchDataMailcostListByPage(DataMailcostQueryVO vo, IThumbPageTools tools){
		List<DataMailcostQueryVO> dataMailcostQueryList=new ArrayList<DataMailcostQueryVO>();
		
		List<DataMailcostVO> dataMailcostList= dao.searchDataMailcostListByPage(vo, tools);
		if(dataMailcostList!=null && dataMailcostList.size()>0){
			for(DataMailcostVO dataMailcost:dataMailcostList){
				DataMailcostQueryVO dataMailcostQuery=new DataMailcostQueryVO();
				try {
					BeanUtils.copyProperties(dataMailcostQuery, dataMailcost);
					//设置接收人
					String receiver="";
					if(dataMailcost.getReceiverType()==DataMailcostReceiverTypeContants.BUSINESSOFFICER.getCode()){
						UserVO user=userService.get(dataMailcost.getBusinessOfficer());
						if(user!=null){
							receiver=user.getUserName();
						}
					}else if(dataMailcost.getReceiverType()==DataMailcostReceiverTypeContants.BANK.getCode()){
						BankService bankService=new BankService();
						receiver=bankService.getBankNameById(dataMailcost.getHeadBank())+"/"
								+bankService.getBankNameById(dataMailcost.getBranch())+"/"
								+bankService.getBankNameById(dataMailcost.getSubbranch());
					}else if(dataMailcost.getReceiverType()==DataMailcostReceiverTypeContants.DEALER.getCode()){
						DealerService dealerService=new DealerService();
						DealerVO dealer=dealerService.get(dataMailcost.getDealerId());
						if(dealer!=null){
							receiver=dealer.getDealerName();
						}
					}else if(dataMailcost.getReceiverType()==DataMailcostReceiverTypeContants.SUPERVISORY.getCode()){
						SupervisoryService  supervisoryService=new SupervisoryService();
						RepositoryService repositoryService=new RepositoryService();
						
						RepositoryVO repository=repositoryService.load(dataMailcost.getSupervisoryId());
						if(repository!=null){
							SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
							if(supervisorBaseInfoVO!=null){
								//当前监管员名称
								receiver=supervisorBaseInfoVO.getName();
							}
						}
					}
					dataMailcostQuery.setReceiver(receiver);
					dataMailcostQuery.setCreatedate(dataMailcost.getCreatedate());
					dataMailcostQuery.setUpddate(dataMailcost.getUpddate());
					dataMailcostQuery.setFqDate(dataMailcost.getFqDate());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				dataMailcostQueryList.add(dataMailcostQuery);
			}
		}
		
		
		return dataMailcostQueryList;
				
				
				
				
	}
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean approval(DataMailcostQueryVO query,UserSession user) throws Exception{
		DataMailcostVO bean = loadDataMailcostById(query.getId());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			//给发起人发送消息
			try {
				MessageUtil.addMsg(bean.getPromoter(), "资料邮寄费用申请审批不通过", "/dataMailcost.do?method=findList&dataMailquery.pageType=2", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			//将资料邮寄费用申请单状态修改为可编辑状态
			bean.setStatus(0);
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(), currRole) ){
				//综合专员
				bean.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+""}, 
					"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
			
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)){
				//业务专员
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_AMALDAR.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
				
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)){
				//业务经理
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
				
			}else if(bean.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				//运营部长
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
				
			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//物流总监
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
				
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(), currRole)){
				//薪酬专员
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.FINANCE_ACCOUNTING.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
			}
			/*if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole) ){
				//监管员管理部经理
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+""}, 
					"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
			
			}else if(bean.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				//运营管理部部长
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
				
			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//物流金融中心总监
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
			
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(), currRole)){
				//监管员管理部薪酬专员
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORY.getCode()+""}, 
						"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
			}*/
		}
		flag=dao.update(bean);
		if(flag){
			//审批记录表
			MarketApprovalVO approval = new MarketApprovalVO();
			approval.setId(Util.getID(MarketApprovalVO.class));
			approval.setApprovalObjectId(query.getId());
			approval.setApprovalPerson(userVO.getUserName());
			approval.setApprovalType(ApprovalTypeContant.DATAMAILPOSTAGEREQUISITION.getCode());
			approval.setCreateDate(new Date());
			approval.setRemark(query.getRemark());
			approval.setApprovalResult(query.getApprovalState());
			approval.setApprovalUserId(user.getUser().getId());
			approval.setApprovalUserRole(currRole);
			flag=dao.add(approval);
		}
		
		return flag;
	}
	/*public void sedMessage(int roleId){
		RoleService rs = new RoleService();
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		urquery.setRole_id(roleId);
		List<UserRoleVO> urList;
		try {
			urList = rs.searchUserRoleList(urquery);
			if(urList != null && urList.size()>0){
				for(UserRoleVO ur : urList){
					MessageUtil.addMsg(ur.getUser_id(), "资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submit(int id) throws Exception{
		
		DataMailcostVO bean =  loadDataMailcostById(id);
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.POSTAGEREQUISITION.getCode());*/
		//设置状态为不可编辑
		bean.setStatus(1);
		//bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
		//流程更改，由综合专员开始
		bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+""}, 
				"资料邮寄费用申请", "/dataMailcost.do?method=findList", 1,MessageTypeContant.DATAMAILPOSTAGEREQUISITION.getCode(),bean.getCreateuserid());
	
		return dao.update(bean);
	}

	/**
	 * 导出
	 * @time 20170514
	 */
	public List<DataMailcostToExtVO> searchDataMailcostListToExt(DataMailcostQueryVO query) {
		return dao.searchDataMailcostListToExt(query);
	}
	
}
