package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IDealerExitDAO;
import com.zd.csms.marketing.model.DealerExitQueryVO;
import com.zd.csms.marketing.querybean.DealerExitQueryBean;
import com.zd.csms.marketing.querybean.SupervisorQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商撤店信息流转
 * @author licheng
 *
 */
public class DealerExitDaoImpl extends DAOSupport implements IDealerExitDAO{

	public DealerExitDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<DealerExitQueryBean> findList(DealerExitQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.id, t.approvalstate, t.dealerid, t.nextapproval, md.dealername,");
		sql.append(" cr.username as \"createUserName\",la.username as \"lastModifyUserName\",");
		sql.append(" t.createDate,t.lastModifyDate,tb.bankfullname as \"bankName\" ");
		sql.append(" from market_dealer_exit t ");
		sql.append(" inner join market_dealer md ");
		sql.append(" on t.dealerid = md.id ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		
		sql.append(" inner join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");

		List<Object> params = new ArrayList<Object>();
		List<DealerExitQueryBean> list = null;	
		formatSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerExitQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();           
		}
		return list;
	}
	
	/**
	 * 查询条件
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatSQL(StringBuffer sql,List<Object> params,DealerExitQueryVO query){
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode() ||currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){//业务专员到属于自己银行的
				sql.append(" and exists (select 1 from t_yw_bank yb where yb.userid = ? and yb.bankId = mdb.bankId )");
				params.add(query.getCurrUserId());
				sql.append(" and t.nextApproval = ?  and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
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
				sql.append(" and (t.approvalState=? ");
/*				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
*/				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){//业务流转单 市场部专员只看到属于自己银行的
				sql.append(" and exists (select 1 from t_yw_bank yb where yb.userid = ? and yb.bankId = mdb.bankId )");
				params.add(query.getCurrUserId());
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.DEALEREXIT.getCode());
				params.add(currRole);
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and t.approvalState=?  ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.DEALEREXIT.getCode());
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
	 * 根据储备库ID获取监管员信息
	 * @param id
	 * @return
	 */
	public SupervisorQueryBean getSupervisor(int id) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select bc.name, " +
						"       r.staffno, " + 
						"       bc.gender, " + 
						"       r.systemaccount, " + 
						"       r.systempassword, " + 
						"       bc.SELFTELEPHONE as \"Contactnumber\", " + 
						" 		bc.id as \"supervisorBasicId\" "+
						"  from t_roster r " + 
						"  left join t_repository t " + 
						"    on r.repositoryid = t.id " + 
						"  left join t_supervisor_basic_information bc " + 
						"    on r.supervisorid = bc.id " + 
						" where t.id = ?");
		params.add(id);
		List<SupervisorQueryBean> list = getJdbcTemplate()
				.query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(SupervisorQueryBean.class));
		if(list != null&&list.size()>0)
			return list.get(0);
		else 
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DealerExitQueryBean> DealerWithdrawalLedger(
			DealerExitQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.id, t.approvalstate, t.dealerid, t.nextapproval, md.dealername,");
		sql.append(" cr.username as \"createUserName\",la.username as \"lastModifyUserName\",");
		sql.append(" t.createDate,t.lastModifyDate,tb.bankfullname as \"bankName\" ");
		sql.append(" from market_dealer_exit t ");
		sql.append(" inner join market_dealer md ");
		sql.append(" on t.dealerid = md.id ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		
		sql.append(" inner join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");

		sql.append(" where 1=1 ");
		
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
		sql.append(" order by t.id desc ");
		List<DealerExitQueryBean> list = null;	
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DealerExitQueryBean.class));
			System.out.println("经销商撤店台账 SQL："+sql.toString());
			System.out.println("经销商撤店台账 params:"+params);
		} catch (Exception e) {
			e.printStackTrace();           
		}
		return list;
	}
}
