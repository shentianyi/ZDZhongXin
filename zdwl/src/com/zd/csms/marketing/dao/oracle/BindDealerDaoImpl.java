package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IBindDealerDAO;
import com.zd.csms.marketing.model.BindDealerQueryVO;
import com.zd.csms.marketing.querybean.BindDealerQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class BindDealerDaoImpl extends DAOSupport implements IBindDealerDAO {

	public BindDealerDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BindDealerQueryBean> findList(BindDealerQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id,t.dealername,t.approvalState,t.dealerid,t.nextApproval,t.bankName,"
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate "
				+ " from market_bind_dealer t ");
		
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		List<Object> params = new ArrayList<Object>();
		List<BindDealerQueryBean> list = null;
		formatSQL(sql, params, query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BindDealerQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(StringBuffer sql, List<Object> params, BindDealerQueryVO query) {
		int currRole = query.getCurrRole().intValue();
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ? ");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.SR.getCode() || currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
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
				sql.append(" and (t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ? ");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){ //综合专员
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.BINDDEALER.getCode());
				params.add(currRole);
			}

		}
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and t.bankName like ? ");
			params.add("%"+query.getBankName().trim()+"%");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BindDealerQueryBean> dealerFinancialInstitutionBindingLedger(
			BindDealerQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id,t.dealername,t.approvalState,t.dealerid,t.nextApproval,t.bankName,"
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate "
				+ " from market_bind_dealer t ");
		
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		sql.append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and t.bankName like ? ");
			params.add("%"+query.getBankName().trim()+"%");
		}
		List<BindDealerQueryBean> list = null;
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BindDealerQueryBean.class));
			System.out.println("DealerFinancialInstitutionBindingLedger sql:"+sql.toString());
			System.out.println("DealerFinancialInstitutionBindingLedger params"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
