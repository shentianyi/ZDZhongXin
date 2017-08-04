package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.supervisorymanagement.dao.IMailcostDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.MailcostMapper;
import com.zd.csms.supervisorymanagement.model.MailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.MailcostVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class MailcostOracleDAO extends DAOSupport implements IMailcostDAO {
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	public MailcostOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_mail_cost = "select * from t_mail_cost";

	private void formatSQL(StringBuffer sql,List<Object> params,MailcostQueryVO query){
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		//综合专员创建的信息需要判断读取自己创建的吗？？？
		/*if(query.getPromoter()>0){
			sql.append(" and promoter = ? ");
			params.add(query.getPromoter());
		}*/
		
		if(query.getFqdate()!=null){
			sql.append(" and fqdate = ? ");
			params.add(query.getFqdate());
		}
		
		if(query.getMailingitems()!=null && query.getMailingitems().length>0){
			sql.append(" and ( ");
			for(Integer mailItem:query.getMailingitems()){
				sql.append(" mailingitems like '%"+mailItem+"%' or ");
			}
			sql.delete(sql.length()-3,sql.length());
			sql.append(" ) ");
		}
		
		if(StringUtil.isNotEmpty(query.getTransportbegin())){
			sql.append(" and transportbegin like ? ");
			params.add("%"+query.getTransportbegin()+"%");
		}
		
		if(StringUtil.isNotEmpty(query.getTransportend())){
			sql.append(" and transportend like ? ");
			params.add("%"+query.getTransportend()+"%");
		}
		
		
		if(query.getMailperson()>0){
			sql.append(" and mailperson = ? ");
			params.add(query.getMailperson());
		}
		if(currRole>0){
			if(pageType == 1){//待审批
				if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
					sql.append(" and (approvalState=? or approvalState=? or approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (approvalState=? or approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else{
					sql.append(" and nextApproval = ?  and approvalState = ? ");
					params.add(currRole);
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				}
			}else if(pageType==2){//已审批
				if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){//如果角色是发起者
					sql.append(" and  approvalState=?");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and  approvalState=?");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else {
					sql.append(" and id in "
							+ "(select ma.approvalobjectid "
							+ " from market_approval ma "
							+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
					params.add(ApprovalTypeContant.POSTAGEREQUISITION.getCode());
					params.add(currRole);
				}
			}else if (pageType==-1 && currRole == RoleConstants.SUPERVISORY.getCode()) {
				sql.append(" and receiveid = ? ");
				params.add(query.getReceiveid());
			}
			
		}else{
			sql.append("and approvalState=? ");
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}
	}
	
	@Override
	public List<MailcostVO> searchMailcostList(MailcostQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(MailcostOracleDAO.select_mail_cost);
		if(query.getCurrRole() != null && query.getPageType() != null){
			formatSQL(sql, params,query);
		}
		List<MailcostVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new MailcostMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	//
	@Override
	public List<MailcostVO> searchMailcostListByPage(MailcostQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(MailcostOracleDAO.select_mail_cost);
		if(query.getCurrRole() != null && query.getPageType() != null){
			formatSQL(sql, params,query);
		}
		List<MailcostVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new MailcostMapper());
			System.out.println("searchMailcostListByPage sql:"+sql.toString());
			System.out.println("searchMailcostListByPage params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	
}
