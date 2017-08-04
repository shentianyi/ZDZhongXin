package com.zd.csms.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.business.dao.BusinessDAOFactory;
import com.zd.csms.business.dao.IComplaintDAO;
import com.zd.csms.business.model.ComplaintInfoQueryBean;
import com.zd.csms.business.model.ComplaintInfoQueryVO;
import com.zd.csms.business.model.ComplaintInfoVO;
import com.zd.csms.business.model.ComplaintQueryVO;
import com.zd.csms.business.model.ComplaintVO;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ComplaintService extends ServiceSupport {
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private IComplaintDAO dao = BusinessDAOFactory.getComplaintDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();

	public ComplaintInfoVO get(int id) {
		return dao.get(ComplaintInfoVO.class, id, new BeanPropertyRowMapper(ComplaintInfoVO.class));
	}
	public ComplaintInfoVO getComplaintInfoVO(int id) {
		return dao.get(ComplaintInfoVO.class, id, new BeanPropertyRowMapper(ComplaintInfoVO.class));
	}

	public boolean add(ComplaintInfoVO bean) throws Exception {
		bean.setId(Util.getID(ComplaintInfoVO.class));
		MessageUtil.addOrUpdateMeg(bean.getProcessingId(),
				"来电记录单", "/complaint.do?method=findList", 1, 
				MessageTypeContant.COMPLAINTINFO.getCode(), bean.getCreateUserId());
		System.out.println("信息发送："+bean.getProcessingId());
		return dao.add(bean);
	}

	public boolean update(ComplaintInfoVO bean) throws Exception {
		return dao.update(bean);
	}

	public boolean delete(ComplaintInfoVO bean) throws Exception {
		return dao.delete(ComplaintInfoVO.class, bean.getId());
	}

	/**
	 * 监管费变更分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<ComplaintInfoQueryBean> findList(ComplaintInfoQueryVO query, IThumbPageTools tools) throws Exception {
		List<ComplaintInfoQueryBean> list = dao.findList(query, tools);
		return list;
	}
	
	/**
	 * 台账投诉记录分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<ComplaintInfoQueryBean> findListLedger(ComplaintInfoQueryVO query, IThumbPageTools tools) throws Exception {
		List<ComplaintInfoQueryBean> list = dao.findListLedger(query, tools);
		return list;
	}

	/**
	 * 根据选择的部门不同 走的流程也不同
	 * 
	 * @param query
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean approval(ComplaintQueryVO query, UserSession user) throws Exception {
		ComplaintVO bean = dao.get(ComplaintVO.class, query.getId(), new BeanPropertyRowMapper(ComplaintVO.class));
		UserVO userVO = user.getUser();
		int currRole = query.getCurrRole();
		// 判断权限 如果是最高权限审批通过则 增加经销商名录
		if (query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()) {
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		} else if (query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()) {

			if (bean.getNextApproval() == RoleConstants.MARKET_COMMISSIONER.getCode()
					&& RoleUtil.roleValidate(RoleConstants.MARKET_COMMISSIONER.getCode(), currRole)) {
				// 市场部
				bean.setTreatmentOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
			} else if (bean.getNextApproval() == RoleConstants.MARKET_AMALDAR.getCode()
					&& RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRole)) {
				bean.setJlOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			} else if (bean.getNextApproval() == RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()
					&& RoleUtil.roleValidate(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(), currRole)) {
				bean.setBzOpinion(query.getRemark());
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());// 最后一级审批
																				// ，如果成功则审批完成

			} else if (bean.getNextApproval() == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()
					&& RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(), currRole)) {
				bean.setTreatmentOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
			} else if (bean.getNextApproval() == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
					&& RoleUtil.roleValidate(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), currRole)) {
				bean.setJlOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());

			} else if (bean.getNextApproval() == RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&& RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole)) {
				bean.setTreatmentOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
			} else if (bean.getNextApproval() == RoleConstants.BUSINESS_AMALDAR.getCode()
					&& RoleUtil.roleValidate(RoleConstants.BUSINESS_AMALDAR.getCode(), currRole)) {
				bean.setJlOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());

			} else if (bean.getNextApproval() == RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()
					&& RoleUtil.roleValidate(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(), currRole)) {
				bean.setBzOpinion(query.getRemark());
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());// 最后一级审批
																				// ，如果成功则审批完成

			} else if (bean.getNextApproval() == RoleConstants.WINDCONRTOL_DATA.getCode()
					&& RoleUtil.roleValidate(RoleConstants.WINDCONRTOL_DATA.getCode(), currRole)) {
				bean.setTreatmentOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.WINDCONRTOL_AMALDAR.getCode());
			} else if (bean.getNextApproval() == RoleConstants.WINDCONRTOL_AMALDAR.getCode()
					&& RoleUtil.roleValidate(RoleConstants.WINDCONRTOL_AMALDAR.getCode(), currRole)) {
				bean.setJlOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());

			} else if (bean.getNextApproval() == RoleConstants.VIDEO_COMMISSIONER.getCode()
					&& RoleUtil.roleValidate(RoleConstants.VIDEO_COMMISSIONER.getCode(), currRole)) {
				bean.setTreatmentOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.VIDEO_AMALDAR.getCode());
			} else if (bean.getNextApproval() == RoleConstants.VIDEO_AMALDAR.getCode()
					&& RoleUtil.roleValidate(RoleConstants.VIDEO_AMALDAR.getCode(), currRole)) {
				bean.setJlOpinion(query.getRemark());
				bean.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());

			} else if (bean.getNextApproval() == RoleConstants.RISKMANAGEMENT_MINISTER.getCode()
					&& RoleUtil.roleValidate(RoleConstants.RISKMANAGEMENT_MINISTER.getCode(), currRole)) {
				bean.setBzOpinion(query.getRemark());
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());// 最后一级审批
																				// ，如果成功则审批完成
			}
		}
		dao.update(bean);
		// 审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.COMPLAINT.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);

		return true;
	}
	
	/*
	 * 需求38
	 * 投诉记录信息汇总表
	*/
	public List<ComplaintInfoQueryBean> ExtComplaintLedger(ComplaintInfoQueryVO query) {
		return dao.ExtComplaintLedger(query);
	}

}
