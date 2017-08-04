package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IDealerRefundDAO;
import com.zd.csms.marketing.model.DealerRefundQueryVO;
import com.zd.csms.marketing.querybean.RefundQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商退费流转单
 * @author licheng
 *
 */
public class DealerRefundDaoImpl extends DAOSupport implements IDealerRefundDAO{

	public DealerRefundDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<RefundQueryBean> findList(DealerRefundQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, t.dealerid, t.approvalState, t.nextApproval,md.dealername,"
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate,tb.bankfullname as \"bankName\" "+
				"  from market_dealer_refund t " + 
				"  inner join market_dealer md " + 
				"    on t.dealerid = md.id ");
		
		sql.append(" inner join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");

		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		List<Object> params = new ArrayList<Object>();
		List<RefundQueryBean> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RefundQueryBean.class));
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
	private void formatSQL(StringBuffer sql,List<Object> params,DealerRefundQueryVO query){
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
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else{
				sql.append(" and t.nextApproval = ? and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
			
		}else if(pageType==2){//已审批
			if(currRole==RoleConstants.MARKET_COMMISSIONER.getCode()){//如果角色是发起者
				sql.append(" and (t.approvalState=?) ");
/*				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
*/				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
/*				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
*/				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				//sql.append("and t.approvalState=?"+ApprovalContant.APPROVAL_PASS.getCode());
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.DEALERREFUND.getCode());
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
	public List<RefundQueryBean> superviseRefundList(DealerRefundQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, t.dealerid, t.approvalState, t.nextApproval,md.dealername,"
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate,tb.bankfullname as \"bankName\" "+
				"  from market_dealer_refund t " + 
				"  inner join market_dealer md " + 
				"    on t.dealerid = md.id ");
		
		sql.append(" inner join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");
		
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		List<Object> params = new ArrayList<Object>();
		List<RefundQueryBean> list = null;
		sql.append("where 1=1");
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
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RefundQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

