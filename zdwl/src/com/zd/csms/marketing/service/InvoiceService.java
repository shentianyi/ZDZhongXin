package com.zd.csms.marketing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IInvoiceDAO;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.InvoiceQueryVO;
import com.zd.csms.marketing.model.InvoiceVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.InvoiceQueryBean;
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


public class InvoiceService extends ServiceSupport{
	private IInvoiceDAO dao = MarketFactory.getInvoiceDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	
	public InvoiceVO get(int id){
		return dao.get(InvoiceVO.class, id, new BeanPropertyRowMapper(InvoiceVO.class));
	}
	
	public boolean add(InvoiceVO bean) throws Exception{
		bean.setId(Util.getID(InvoiceVO.class));
		return dao.add(bean);
	}
	
	public boolean update(InvoiceVO bean) throws Exception{
		InvoiceVO vo = get(bean.getId());
		vo.setDealerId(bean.getDealerId());
		vo.setPaymentAmount(bean.getPaymentAmount());
		vo.setPaymentDate(bean.getPaymentDate());
		vo.setInvoiceType(bean.getInvoiceType());
		vo.setOffice(bean.getOffice());
		vo.setRemark(bean.getRemark());
		vo.setApplicant(bean.getApplicant());
		vo.setApplicantDate(bean.getApplicantDate());
		vo.setCompanyname(bean.getCompanyname());
		vo.setBankaccount(bean.getBankaccount());
		vo.setAccountnum(bean.getAccountnum());
		vo.setBanknum(bean.getBanknum());
		vo.setTaxpayernum(bean.getTaxpayernum());
		vo.setRegisteraddress(bean.getRegisteraddress());
		vo.setTelephone(bean.getTelephone());
		vo.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		vo.setLastModifyDate(bean.getLastModifyDate());
		vo.setLastModifyUserId(bean.getLastModifyUserId());
		return dao.update(vo);
	}
	
	public boolean delete(InvoiceVO bean) throws Exception{
		return dao.delete(InvoiceVO.class, bean.getId());
	}
	
	/**
	 * 监管费变更分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception 
	 */
	public List<InvoiceQueryBean> findList(InvoiceQueryVO query,IThumbPageTools tools) throws Exception{
		List<InvoiceQueryBean> list= dao.findList(query, tools);
		return list;
	}
	public List<InvoiceQueryBean> draftVoteList(InvoiceQueryVO query,IThumbPageTools tools) throws Exception{
		List<InvoiceQueryBean> list= dao.draftVoteList(query, tools);
		return list;
	}
	
	/**
	 * 市场专员-市场部经理--市场部部长--总监
	 * @param query
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean approval(InvoiceQueryVO query,UserSession user,int currRole) throws Exception{
		InvoiceVO bean = dao.get(InvoiceVO.class, query.getId(), new BeanPropertyRowMapper(InvoiceVO.class));
		
		UserVO userVO =  user.getUser();
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
				//财务部会计
				bean.setNextApproval(RoleConstants.FINANCE_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.FINANCE_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.FINANCE_AMALDAR.getCode(), currRole)){
				//财务部经理
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//总监
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完成
			}
		}
		dao.update(bean);
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.INVOICE.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "开票申请流转单", "/market/invoice.do?method=findList", 1, 
						MessageTypeContant.INVOICE.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "开票申请流转单审批未通过，请查看", "/market/invoice.do?method=findList&query.pageType=2",1, MessageTypeContant.INVOICE.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "开票申请流转单审批已通过，请查看", "/market/invoice.do?method=findList",1, MessageTypeContant.INVOICE.getCode(),bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean submit(int id) throws Exception{
		InvoiceVO bean =  get(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.INVOICE.getCode());
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "开票申请流转单", "/market/invoice.do?method=findList", 1, 
						MessageTypeContant.INVOICE.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.update(bean);
	}
}
