package com.zd.csms.marketing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IPaymentDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.model.PaymentQueryVO;
import com.zd.csms.marketing.model.PaymentVO;
import com.zd.csms.marketing.querybean.PaymentQueryBean;
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


public class PaymentService extends ServiceSupport{
	private IPaymentDAO dao = MarketFactory.getpaymentDAO();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private DealerService dealerService = new DealerService();
	
	public PaymentVO get(int id){
		return dao.get(PaymentVO.class, id, new BeanPropertyRowMapper(PaymentVO.class));
	}
	
	public boolean add(PaymentVO bean) throws Exception{
		bean.setId(Util.getID(PaymentVO.class));
		return dao.add(bean);
	}
	
	public boolean update(PaymentVO bean) throws Exception{
		PaymentVO vo = get(bean.getId());
		vo.setDealerId(bean.getDealerId());
		vo.setInputPerson(bean.getInputPerson());
		vo.setPosition(bean.getPosition());
		vo.setPaymentDate(bean.getPaymentDate());
		vo.setPaymentMoney(bean.getPaymentMoney());
		vo.setActualPaymentDate(bean.getActualPaymentDate());
		vo.setActualPaymentMoney(bean.getActualPaymentMoney());
		vo.setRemittanceAccount(bean.getRemittanceAccount());
		vo.setCollectionAccount(bean.getCollectionAccount());
		vo.setAccountBank(bean.getAccountBank());
		vo.setCollectionUnit(bean.getCollectionUnit());
		vo.setIsArrears(bean.getIsArrears());
		vo.setArrearsDate(bean.getArrearsDate());
		vo.setArrearsMoney(bean.getArrearsMoney());
		vo.setRemark(bean.getRemark());
		vo.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		vo.setLastModifyDate(bean.getLastModifyDate());
		vo.setLastModifyUserId(bean.getLastModifyUserId());
		return dao.update(vo);
	}
	
	public boolean delete(PaymentVO bean) throws Exception{
		return dao.delete(PaymentVO.class, bean.getId());
	}
	
	/**
	 * 监管费变更分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception 
	 */
	public List<PaymentQueryBean> findList(PaymentQueryVO query,IThumbPageTools tools) throws Exception{
		List<PaymentQueryBean> list= dao.findList(query, tools);
		return list;
	}
	
	/**
	 * 监管费变更分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception 
	 */
	public List<PaymentVO> findListByDealerId(PaymentQueryVO query,IThumbPageTools tools) throws Exception{
		List<PaymentVO> list= dao.findListByDealerId(query, tools);
		return list;
	}
	
	/**
	 * 市场专员-市场部经理--市场部部长--财务部会计--财务部经理--总监
	 * @param query
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean approval(PaymentQueryVO query,UserSession user,int currRole) throws Exception{
		PaymentVO bean = dao.get(PaymentVO.class, query.getId(), new BeanPropertyRowMapper(PaymentVO.class));
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
				//财务部会计 - 新增
				bean.setNextApproval(RoleConstants.FINANCE_AMALDAR.getCode());
			}else if(bean.getNextApproval()==RoleConstants.FINANCE_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.FINANCE_AMALDAR.getCode(), currRole)){
				//财务部经理
				bean.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
			}else if(bean.getNextApproval()==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
					&&RoleUtil.roleValidate(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), currRole)){
				//总监
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完成
				DealerVO dealer = dao.get(DealerVO.class, bean.getDealerId(), new BeanPropertyRowMapper(DealerVO.class));
				/*int amount = 0;
				int paymode = dealer.getPayMode();
				if(paymode == PayModeContant.PAYMODE_MONTH.getCode()){
					amount = 1;
				}else if(paymode == PayModeContant.PAYMODE_QUARTER.getCode()){
					amount = 3;
				}else if(paymode == PayModeContant.PAYMODE_SIXMONTH.getCode()){
					amount = 6;
				}else if(paymode == PayModeContant.PAYMODE_YEAR.getCode()){
					amount = 12;
				}
				dealer.setPayDate(DateUtil.addMonth(dealer.getPayDate(), amount));*/
				dealer.setBalance(dealer.getBalance()+Double.parseDouble(bean.getActualPaymentMoney()));
				dao.update(dealer);
			}
		}
		dao.update(bean);
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.PAYMENT.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		DealerVO dealer =  dealerService.get(bean.getDealerId());
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "监管费缴费记录单:"+dealer.getDealerName(), "/market/payment.do?method=findList", 1, 
						MessageTypeContant.PAYMENT.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "监管费缴费记录单审批未通过，请查看", "/market/payment.do?method=findList&query.pageType=2",1, MessageTypeContant.PAYMENT.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "监管费缴费记录单已通过，请查看", "/market/payment.do?method=findList&query.pageType=2",1, MessageTypeContant.PAYMENT.getCode(),bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean submit(int id) throws Exception{
		PaymentVO bean =  get(id);
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.PAYMENT.getCode());*/
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		DealerVO dealer =  dealerService.get(bean.getDealerId());
		if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
			MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "监管费缴费记录单:"+dealer.getDealerName(), "/market/payment.do?method=findList", 1, 
					MessageTypeContant.PAYMENT.getCode(), bean.getCreateUserId());
		}
		return dao.update(bean);
	}

}
