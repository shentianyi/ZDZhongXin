package com.zd.csms.marketing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IDealerRefundDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerRefundQueryVO;
import com.zd.csms.marketing.model.DealerRefundVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.RefundQueryBean;
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


public class DealerRefundService extends ServiceSupport{
	private IDealerRefundDAO dao = MarketFactory.getDealerRefundDAO();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();

	public DealerRefundVO get(int id ){
		return dao.get(DealerRefundVO.class, id, new BeanPropertyRowMapper(DealerRefundVO.class));
	}
	
	public boolean add(DealerRefundVO bean) throws Exception{
		bean.setId(Util.getID(DealerRefundVO.class));
		return dao.add(bean);
	}
	
	public boolean update(DealerRefundVO bean) throws Exception{
		DealerRefundVO vo = get(bean.getId());
		vo.setDealerId(bean.getDealerId());
		vo.setActualPayment(bean.getActualPayment());
		vo.setActualPaymentDate(bean.getActualPaymentDate());
		vo.setRefundMoney(bean.getRefundMoney());
		vo.setRefundDate(bean.getRefundDate());
		vo.setIsInvoice(bean.getIsInvoice());
		vo.setInvoiceDate(bean.getInvoiceDate());
		vo.setInvoiceType(bean.getInvoiceType());
		vo.setInvoiceCompany(bean.getInvoiceCompany());
		vo.setRefundResource(bean.getRefundResource());
		vo.setApplyDate(bean.getApplyDate());
		vo.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		vo.setLastModifyDate(bean.getLastModifyDate());
		vo.setLastModifyUserId(bean.getLastModifyUserId());
		return dao.update(vo);
	}
	
	public boolean delete(DealerRefundVO bean) throws Exception{
		return dao.delete(DealerRefundVO.class, bean.getId());
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<RefundQueryBean> findList(DealerRefundQueryVO query,IThumbPageTools tools){
		return dao.findList(query, tools);
	}
	
	public List<RefundQueryBean> superviseRefundList(DealerRefundQueryVO query,IThumbPageTools tools){
		return dao.superviseRefundList(query, tools);
	}
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean approval(DealerRefundQueryVO query,UserSession user) throws Exception{
		DealerRefundVO bean = dao.get(DealerRefundVO.class, query.getId(), new BeanPropertyRowMapper(DealerRefundVO.class));
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.MARKET_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRole) ){
				//市场部经理
				bean.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			}else if(bean.getNextApproval()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(), currRole)){
				//市场部部长
				bean.setNextApproval(RoleConstants.FINANCE_ACCOUNTING.getCode());
			}else if(bean.getNextApproval()==RoleConstants.FINANCE_ACCOUNTING.getCode()
					&&RoleUtil.roleValidate(RoleConstants.FINANCE_ACCOUNTING.getCode(), currRole)){
				//财务部会计 -- 新增
				bean.setNextApproval(RoleConstants.FINANCE_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.FINANCE_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.FINANCE_AMALDAR.getCode(), currRole)){
				//财务部经理
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//总监
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
			}
		}
		dao.update(bean);
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(bean.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.DEALERREFUND.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "经销商退费流转单", "/market/refund.do?method=findList", 1, 
						MessageTypeContant.DEALERREFUND.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "经销商退费流转单审批未通过，请查看", "/market/refund.do?method=findList&query.pageType=2",1, MessageTypeContant.DEALERREFUND.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "经销商退费流转单审批已通过，请查看", "/market/refund.do?method=findList",1, MessageTypeContant.DEALERREFUND.getCode(),bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submit(int id) throws Exception{
		DealerRefundVO bean =  get(id);
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.DEALERREFUND.getCode());*/
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "经销商退费流转单", "/market/refund.do?method=findList", 1, 
						MessageTypeContant.DEALERREFUND.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.update(bean);
	}
	
}
