package com.zd.csms.supervisorymanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.dao.IResignApplyDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyBean;
import com.zd.csms.supervisorymanagement.model.ResignApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ResignApplyService  extends ServiceSupport {
	
	private IResignApplyDAO dao = SupervisoryManagementDAOFactory.getResignApplyDAO();
	RepositoryService repositoryService=new RepositoryService();
	SupervisoryService supervisoryService=new SupervisoryService();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	
	public List<ResignApplyBean> findPageList(ResignApplyQueryVO query,
			IThumbPageTools tools) {
		List<ResignApplyBean> list=new ArrayList<ResignApplyBean>();
		List<ResignApplyVO> resignApplyList=dao.findPageList(query, tools);
		if(resignApplyList!=null && resignApplyList.size()>0){
			for(ResignApplyVO resignApply:resignApplyList){
				ResignApplyBean bean=new ResignApplyBean();;
				BeanUtils.copyProperties(resignApply, bean);
				String dealerName="";
				String bankName="";
				String brandName="";
				List<CurrentDealerVO> dealerList=dao.findCurrentDealerListByResignApplyId(resignApply.getId(), CurrentDealerTypeContants.RESIGNAPPLY.getCode());
				if(dealerList!=null && dealerList.size()>0){
					for(CurrentDealerVO dealer:dealerList){
						dealerName=dealerName+" "+dealer.getDealerName();
						bankName=bankName+" "+dealer.getBrandName();
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
	public List<ResignApplyBean> findList(ResignApplyQueryVO query) {
		List<ResignApplyBean> list=new ArrayList<ResignApplyBean>();
		List<ResignApplyVO> resignApplyList=dao.findList(query);
		if(resignApplyList!=null && resignApplyList.size()>0){
			for(ResignApplyVO resignApply:resignApplyList){
				ResignApplyBean bean=new ResignApplyBean();;
				BeanUtils.copyProperties(resignApply, bean);
				String dealerName="";
				String bankName="";
				String brandName="";
				String dealerId="0";
				List<CurrentDealerVO> dealerList=dao.findCurrentDealerListByResignApplyId(resignApply.getId(), CurrentDealerTypeContants.RESIGNAPPLY.getCode());
				if(dealerList!=null && dealerList.size()>0){
					for(CurrentDealerVO dealer:dealerList){
						dealerName=dealerName+" "+dealer.getDealerName();
						bankName=bankName+" "+dealer.getBankName();
						brandName=brandName+" "+dealer.getBrandName();
						dealerId=dealerId+","+dealer.getDealerId();
					}
				}
				bean.setDealerName(dealerName);
				bean.setBankName(bankName);
				bean.setBrandName(brandName);
				bean.setDealerId(dealerId);
				list.add(bean);
			}
		}
		return list;
	}
	public List<ResignApplyBean> findListByExpectResignTime(Date ExpectResignTime) {
		List<ResignApplyBean> list=new ArrayList<ResignApplyBean>();
		List<ResignApplyVO> resignApplyList=dao.findListByExpectResignTime(ExpectResignTime);
		if(resignApplyList!=null && resignApplyList.size()>0){
			for(ResignApplyVO resignApply:resignApplyList){
				ResignApplyBean bean=new ResignApplyBean();;
				BeanUtils.copyProperties(resignApply, bean);
				String dealerName="";
				String bankName="";
				String brandName="";
				String dealerId="0";
				List<CurrentDealerVO> dealerList=dao.findCurrentDealerListByResignApplyId(resignApply.getId(), CurrentDealerTypeContants.RESIGNAPPLY.getCode());
				if(dealerList!=null && dealerList.size()>0){
					for(CurrentDealerVO dealer:dealerList){
						dealerName=dealerName+" "+dealer.getDealerName();
						bankName=bankName+" "+dealer.getBankName();
						brandName=brandName+" "+dealer.getBrandName();
						dealerId=dealerId+","+dealer.getDealerId();
					}
				}
				bean.setDealerName(dealerName);
				bean.setBankName(bankName);
				bean.setBrandName(brandName);
				bean.setDealerId(dealerId);
				list.add(bean);
			}
		}
		return list;
	}
	public ResignApplyVO get(int resignApplyId) {
		return dao.get(ResignApplyVO.class, resignApplyId, new BeanPropertyRowMapper(ResignApplyVO.class));
	}
	
	public ResignApplyVO getByResignPerson(int resignPerson) {
		return dao.getByResignPerson(resignPerson);
	}

	public boolean addResignApply(ResignApplyVO resignApply) {
		try {
			resignApply.setId(Util.getID(ResignApplyVO.class));
			boolean flag=dao.add(resignApply);
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

	public boolean updateResignApply(ResignApplyVO resignApply) {
		boolean flag=dao.update(resignApply);
		if(flag){
			this.setExecuteMessage("修改成功！");
		}else{
			this.setExecuteMessage("修改失败！");
		}
		return flag;
	}

	public boolean deleteResignApply(int resignApplyId) {
		boolean flag=dao.deleteCurrentDealerByResignApply(resignApplyId,CurrentDealerTypeContants.RESIGNAPPLY.getCode());
		if(flag){
			flag=dao.delete(ResignApplyVO.class, resignApplyId);
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

	public List<CurrentDealerVO> findCurrentDealerListByResignApplyId(
			int resignApplyId, int type) {
		return dao.findCurrentDealerListByResignApplyId(resignApplyId,type);
	}

	public boolean submit(int id) {
		ResignApplyVO resignApply=get(id);
		/*try {
			if(ApprovalContant.APPROVAL_NOPASS.getCode()==resignApply.getApprovalState()){
				marketDao.deleteApprovalByDealerId(resignApply.getId(), ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		boolean flag= dao.updateResignApplyStatus(id, 1, ApprovalContant.APPROVAL_WAIT.getCode(), RoleConstants.BUSINESS_COMMISSIONER.getCode());
		if(flag){
			this.setExecuteMessage("提交成功！");
			sedMessage(RoleConstants.BUSINESS_COMMISSIONER.getCode(),resignApply.getName(),resignApply.getCreateUser());
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}

	public boolean approval(ResignApplyQueryVO query, UserSession user) {
		ResignApplyVO bean = get(query.getId());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrentRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		boolean flag=false;
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
			bean.setNextApproval(0);
			//将辞职申请单状态修改为可编辑状态
			bean.setStatus(0);
			//给发起人发送消息
			try {
				MessageUtil.addMsg(bean.getCreateUser(), "辞职申请审批不通过", "/resignApply.do?method=addResignEntry", 1,MessageTypeContant.RESIGNAPPLY.getCode(),bean.getCreateUser());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务部专员
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_AMALDAR.getCode()+""}, "辞职申请审批通过",
						"/resignApply.do?method=findPageList", 1,MessageTypeContant.RESIGNAPPLY.getCode(),bean.getCreateUser());
			}else if (bean.getNextApproval()==RoleConstants.BUSINESS_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)){
				//业务经理
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()+""}, "辞职申请审批通过",
						"/resignApply.do?method=findPageList", 1,MessageTypeContant.RESIGNAPPLY.getCode(),bean.getCreateUser());
			}else if (bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(), currRole)){
				//招聘专员
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+""}, "辞职申请审批通过",
						"/resignApply.do?method=findPageList", 1,MessageTypeContant.RESIGNAPPLY.getCode(),bean.getCreateUser());
			}else if(bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole)){
				//监管员管理部经理
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				bean.setNextApproval(0);
				//给发起人发送消息
				try {
					MessageUtil.addMsg(bean.getCreateUser(), "辞职申请审批通过", "/resignApply.do?method=addResignEntry", 1,MessageTypeContant.RESIGNAPPLY.getCode(),bean.getCreateUser());
					//给考勤专员发送消息
					MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()+""}, "辞职申请审批通过",
							"/resignApply.do?method=findPageList", 1,MessageTypeContant.RESIGNAPPLY.getCode(),bean.getCreateUser());
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
				approval.setApprovalType(ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
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
					sedMessage(bean.getNextApproval(),bean.getName(),bean.getCreateUser());
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			this.setExecuteMessage("审批操作失败！");
		}
		return flag;
	}
	public void sedMessage(int roleId,String resignPersonName,int createUserId){
		MessageUtil.sendMsg(new String[]{roleId+""}, "监管员"+resignPersonName+"辞职申请", "/resignApply.do?method=findPageList", 1,MessageTypeContant.RESIGNAPPLY.getCode(),createUserId);
	}
	public boolean delete(int id) {
		try {
			marketDao.deleteApprovalByDealerId(id, ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag=dao.delete(ResignApplyVO.class, id);
		if(flag){
			this.setExecuteMessage("删除成功！");
		}else{
			this.setExecuteMessage("删除失败！");
		}
		return flag;
	}

}
