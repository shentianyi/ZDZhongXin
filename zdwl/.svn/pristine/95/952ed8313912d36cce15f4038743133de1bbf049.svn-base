package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IUnBindDealerDAO;
import com.zd.csms.marketing.model.UnBindDealerQueryVO;
import com.zd.csms.marketing.querybean.UnBindDealerQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商/金融机构拆绑信息
 * @author licheng
 *
 */
public class UnBindDealerDaoImpl extends DAOSupport implements IUnBindDealerDAO {

	public UnBindDealerDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 根据经销商主键ID和监管员ID删除DealerSupervisor
	 * @param dealerId
	 * @param bankId
	 * @return
	 */
	public boolean deleteDealerBank(int dealerId,int bankId){
		StringBuffer sql = new StringBuffer();
		sql.append("delete from market_dealer_supervisor t where t.dealerid = ? and t.repositoryid = ?");
		return super.update(sql.toString(), dealerId,bankId);
	}

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<UnBindDealerQueryBean> findList(UnBindDealerQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select t.id, " +
						"       t.dealerid, " + 
						"       bi.name, " + 
						"       md.dealername, " + 
						"       t.nextapproval, " + 
						"       t.approvalstate, " + 
						" cr.username as \"createUserName\",la.username as \"lastModifyUserName\","+
						" t.createDate,t.lastModifyDate,"+
						" 		(select wm_concat(b.bankfullname) "
						+ 	" from market_dealer_bank db "
						+ 	" left join t_bank b on db.bankId = b.id "
						+ 	" where db.dealerId = t.dealerId) as \"bankName\" "+
						"  from market_unbind_dealer t " + 
						"  left join market_dealer md " + 
						"    on t.dealerid = md.id " + 
						"  left join t_repository r " + 
						"    on t.repositoryId = r.id " + 
						"  left join t_supervisor_basic_information bi " + 
						"    on r.supervisorid = bi.id ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		List<Object> params = new ArrayList<Object>();
		List<UnBindDealerQueryBean> list = null;
		formatSQL(sql, params, query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(UnBindDealerQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(StringBuffer sql, List<Object> params, UnBindDealerQueryVO query) {
		int currRole = query.getCurrRole();
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
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
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
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ? ");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){ //综合专员
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.UNBINDDEALER.getCode());
				params.add(currRole);
			}

		}
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and md.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnBindDealerQueryBean> dealerFinancialInstitutionLedger(
			UnBindDealerQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select t.id, " +
						"       t.dealerid, " + 
						"       bi.name, " + 
						"       md.dealername, " + 
						"       t.nextapproval, " + 
						"       t.approvalstate, " + 
						" cr.username as \"createUserName\",la.username as \"lastModifyUserName\","+
						" t.createDate,t.lastModifyDate,"+
						" 		(select wm_concat(b.bankfullname) "
						+ 	" from market_dealer_bank db "
						+ 	" left join t_bank b on db.bankId = b.id "
						+ 	" where db.dealerId = t.dealerId) as \"bankName\" "+
						"  from market_unbind_dealer t " + 
						"  left join market_dealer md " + 
						"    on t.dealerid = md.id " + 
						"  left join t_repository r " + 
						"    on t.repositoryId = r.id " + 
						"  left join t_supervisor_basic_information bi " + 
						"    on r.supervisorid = bi.id ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		sql.append(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<UnBindDealerQueryBean> list = null;
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and md.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(UnBindDealerQueryBean.class));
			System.out.println("DealerFinancialInstitutionLedger sql:"+sql.toString());
			System.out.println("DealerFinancialInstitutionLedger params"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	

}
