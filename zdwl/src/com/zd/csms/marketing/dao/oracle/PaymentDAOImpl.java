package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IPaymentDAO;
import com.zd.csms.marketing.model.PaymentQueryVO;
import com.zd.csms.marketing.model.PaymentVO;
import com.zd.csms.marketing.querybean.PaymentQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class PaymentDAOImpl extends DAOSupport implements IPaymentDAO{

	public PaymentDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<PaymentQueryBean> findList(PaymentQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, t.dealerid, t.approvalState, md.dealername,t.nextApproval," +
				 " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","+
				 " t.createDate,t.lastModifyDate,tb.bankfullname as \"bankName\" "+
						"  from market_payment t " + 
						"  inner join market_dealer md " + 
						"    on t.dealerid = md.id ");
		sql.append(" inner join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		List<Object> params = new ArrayList<Object>();
		List<PaymentQueryBean> list = null;
		formatExpenseChangeSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PaymentQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 监管费单 查询条件
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatExpenseChangeSQL(StringBuffer sql,List<Object> params,PaymentQueryVO query){
		int currRole = query.getCurrRole().intValue();
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else{
				sql.append(" and t.nextApproval = ?  and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
			
		}else if(pageType==2){//已审批
			if(currRole==RoleConstants.MARKET_COMMISSIONER.getCode()){//如果角色是发起者
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.PAYMENT.getCode());
				params.add(currRole);
			}

		}
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and md.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankfullname like ? ");
			params.add("%"+query.getBankName().trim()+"%");
		}
		if(query.getCreateDateStart()!=null){
			sql.append(" and to_char(t.createDate,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCreateDateStart(), "yyyyMMdd"));
		}
		if(query.getCreateDateEnd()!=null){
			sql.append(" and to_char(t.createDate,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCreateDateEnd(), "yyyyMMdd"));
		}
	}
	
	/**
	 * 缴费详情
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<PaymentVO> findListByDealerId(PaymentQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.actualpaymentdate, t.actualpaymentmoney " +
						"  from market_payment t " + 
						" where to_char(t.actualpaymentdate, 'yyyy') = to_char(sysdate, 'yyyy') " + 
						"   and t.dealerid = ? " + 
						"   and t.approvalstate = ? ");
		sql.append(" order by t.actualpaymentdate desc ");
		
		params.add(query.getDealerId());
		params.add(ApprovalContant.APPROVAL_PASS.getCode());
		List<PaymentVO> list = null;
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PaymentVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
