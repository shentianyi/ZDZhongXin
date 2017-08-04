package com.zd.csms.supervisorymanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisorymanagement.dao.IHandoverPlanDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.ExtHandoverPlanVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

	
public class HandoverPlanService extends ServiceSupport {

	private IHandoverPlanDAO dao = SupervisoryManagementDAOFactory.getHandoverPlanDAO();
	
	public boolean add(HandoverPlanVO vo) throws Exception{
		vo.setId(Util.getID(HandoverPlanVO.class));
		boolean flag=dao.add(vo);
		if(flag){
			this.setExecuteMessage("新增成功！");
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}
	
	public boolean update(HandoverPlanVO vo) throws Exception{
		boolean flag=dao.update(vo);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}
	
	public boolean delete(int id) throws Exception{
		boolean flag=dao.delete(HandoverPlanVO.class, id);
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
	}
	
	public HandoverPlanVO load(int id) throws Exception{
		return dao.get(HandoverPlanVO.class, id,  new BeanPropertyRowMapper(HandoverPlanVO.class));
	}
	
	public List<HandoverPlanVO> searchHandoverPlanList(HandoverPlanQueryVO query){
		return dao.searchHandoverPlanList(query);
	}
	
	public List<HandoverPlanVO> searchHandoverPlanListByPage(HandoverPlanQueryVO query,IThumbPageTools tools){
		return dao.searchHandoverPlanListByPage(query, tools);
	}

	public boolean updHandoverPlanEditStatus(HandoverPlanVO handoverPlan) {
		return dao.updHandoverPlanEditStatus(handoverPlan);
	}

	public boolean approval(HandoverPlanQueryVO query, UserSession user) throws Exception {
		HandoverPlanVO bean =load(query.getId());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrentRole();
		
		if(currRole == RoleConstants.SR.getCode()){
			currRole = bean.getNextApproval();
		}
		
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApproveStatus(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			bean.setIsEdit(0);
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole) ){
				//业务部经理
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)){
				//运营管理部部长
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			}else if(bean.getNextApproval()==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)){
				
				bean.setApproveStatus(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""},
						"轮岗计划审核通过", "/handoverPlan.do?method=handoverPlanPageList", 1, 
						MessageTypeContant.PROJECTCIRCULATION.getCode(), bean.getCreateUser());
			}
		}
		flag=dao.update(bean);
		if(flag){
			//审批记录表
			MarketApprovalVO approval = new MarketApprovalVO();
			try {
				approval.setId(Util.getID(MarketApprovalVO.class));
				approval.setApprovalObjectId(query.getId());
				approval.setApprovalPerson(userVO.getUserName());
				approval.setApprovalType(ApprovalTypeContant.HANDOVERPLAN.getCode());
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
			this.setExecuteMessage("审批操作成功!");
			try {
				if(bean.getApproveStatus()==ApprovalContant.APPROVAL_PASS.getCode()){
					//TODO 交换监管员与经销商绑定表信息
					/*DealerSupervisorService dealerSupervisorService=new DealerSupervisorService();
					DealerSupervisorQueryVO dealerSupervisorQuery=new DealerSupervisorQueryVO();
					//交付人
					dealerSupervisorQuery.setSupervisorId(bean.getDeliverer());
					List<DealerSupervisorVO> delivererSupervisorList=dealerSupervisorService.searchDealerSupervisorList(dealerSupervisorQuery);
					//接收人
					dealerSupervisorQuery.setSupervisorId(bean.getReceiver());
					List<DealerSupervisorVO> receiverSupervisorList=dealerSupervisorService.searchDealerSupervisorList(dealerSupervisorQuery);
					
					if(delivererSupervisorList!=null && delivererSupervisorList.size()>0){
						for(DealerSupervisorVO dealerSupervisor:delivererSupervisorList){
							dealerSupervisor.setRepositoryId(bean.getReceiver());
							dealerSupervisorService.update(dealerSupervisor);
						}
					}
					if(receiverSupervisorList!=null && receiverSupervisorList.size()>0){
						for(DealerSupervisorVO dealerSupervisor:receiverSupervisorList){
							dealerSupervisor.setRepositoryId(bean.getDeliverer());
							dealerSupervisorService.update(dealerSupervisor);
						}
					}*/
					
					
				}else if(bean.getApproveStatus()==ApprovalContant.APPROVAL_NOPASS.getCode()){
					//审批不通过调配专员可重新修改，提交
					HandoverPlanVO handoverPlan=new HandoverPlanVO();
					handoverPlan.setId(bean.getId());
					handoverPlan.setIsEdit(0);
					handoverPlan.setNextApproval(0);
					handoverPlan.setApproveStatus(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
					updHandoverPlanEditStatus(handoverPlan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.setExecuteMessage("审批操作失败!");
		}
		return flag;
	}

	/**
	 * 导出轮岗计划表台账 --需求57
	 * @time 20170517
	 */
	public List<ExtHandoverPlanVO> ExtHandoverPlanLedgerList(HandoverPlanQueryVO handoverPlanQuery) {
		return dao.ExtHandoverPlanLedgerList(handoverPlanQuery);
	}



}
