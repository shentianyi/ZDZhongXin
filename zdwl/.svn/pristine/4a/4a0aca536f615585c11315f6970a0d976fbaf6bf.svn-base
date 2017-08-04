package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.supervisorymanagement.dao.IDataMailcostDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.MailcostMapper;
import com.zd.csms.supervisorymanagement.model.DataMailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostToExtVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class DataMailcostOracleDAO extends DAOSupport implements IDataMailcostDAO {
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	public DataMailcostOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	//资源查询语句
	private static String select_mail_cost = "select * from t_data_mail_cost";

	private void formatSQL(StringBuffer sql,List<Object> params,DataMailcostQueryVO query){
		int currRole = query.getCurrRole();
		String userid = userRoleDao.findAllUserIdByRole(currRole+"");
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		
		if(pageType == 1){//待审批
			/*if(currRole ==RoleConstants.SUPERVISORY.getCode()){
				if(query.getPromoter()>0){
					sql.append(" and promoter = ? ");
					params.add(query.getPromoter());
				}
				sql.append(" and  createuserid in ( ");
				if(StringUtil.isNotEmpty(userid)){
					String[] userids = userid.split(",");
					for (String uid : userids) {
						sql.append("?,");
						params.add(uid);
					}
				}
				sql.deleteCharAt(sql.length()-1);
				//sql.append(" ) and (approvalState=? or approvalState=? or approvalState=?) and nextapproval=? ");
				sql.append(" ) and (approvalState=? or approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				//params.add(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
			
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
				//sql.append(" and (approvalState=? or approvalState=?) and nextapproval=? ");
				sql.append(" and (approvalState=? or approvalState=?)");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				//params.add(RoleConstants.BUSINESS_COMMISSIONER.getCode());
			}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) and nextapproval=? ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(RoleConstants.BUSINESS_AMALDAR.getCode());
			}else if(currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) and nextapproval=? ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			}else if(currRole == RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) and nextapproval=? ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
			}else if(currRole == RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode());
			}
			else{
				sql.append(" and nextApproval = ?  and approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}*/
			if(currRole ==RoleConstants.SUPERVISORY.getCode()){
				if(query.getPromoter()>0){
					sql.append(" and promoter = ? ");
					params.add(query.getPromoter());
				}
				sql.append(" and  createuserid in ( ");
				if(StringUtil.isNotEmpty(userid)){
					String[] userids = userid.split(",");
					for (String uid : userids) {
						sql.append("?,");
						params.add(uid);
					}
				}
				sql.deleteCharAt(sql.length()-1);
				sql.append(" ) and (approvalState=? or approvalState=? or approvalState = ?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}
			else{
				sql.append(" and nextApproval = ?  and approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
		}else if(pageType==2){//已审批
			/*if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
				if(query.getPromoter()>0){
					sql.append(" and promoter = ? ");
					params.add(query.getPromoter());
				}
				sql.append(" and (approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.DATAMAILPOSTAGEREQUISITION.getCode());
				params.add(currRole);
			}*/
			if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
				if(query.getPromoter()>0){
					sql.append(" and promoter = ? ");
					params.add(query.getPromoter());
				}
				sql.append(" and (approvalState=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}/*else if(currRole == RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()){
				sql.append(" and (approvalState=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}else if(currRole == RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()){
				sql.append(" and (approvalState=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
				sql.append(" and (approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()){
				sql.append(" and (approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}else if(currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){
				sql.append(" and (approvalState=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
				sql.append(" and (approvalState=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}*/else {
				sql.append(" and id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.DATAMAILPOSTAGEREQUISITION.getCode());
				params.add(currRole);
			}

		}
	}
	
	@Override
	public List<DataMailcostVO> searchDataMailcostList(DataMailcostQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DataMailcostOracleDAO.select_mail_cost);
		List<DataMailcostVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DataMailcostVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<DataMailcostVO> searchDataMailcostListByPage(DataMailcostQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DataMailcostOracleDAO.select_mail_cost);
		if(query.getCurrRole() != null && query.getPageType() != null){
			formatSQL(sql, params,query);
		}
		List<DataMailcostVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DataMailcostVO.class));
			System.out.println("searchDataMailcostListByPage sql:"+sql.toString());
			System.out.println("searchDataMailcostListByPage params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<DataMailcostToExtVO> searchDataMailcostListToExt(DataMailcostQueryVO query){
		int currRole = query.getCurrRole();
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t. * , ");
		sql.append(" tsbi.name as mailPersonNTT, md.dealername as mailPersonDealerNTT, tru.username as createuserName, tru.username as upduserName, trr.role_name as nextApprovalName ");
		sql.append(" from t_data_mail_cost t  ");
		sql.append(" left join t_repository tr on tr.id = t.mailperson ");
		sql.append(" left join t_supervisor_basic_information tsbi on tr.supervisorid = tsbi.id ");
		sql.append(" left join market_dealer md on t.mailpersondealer = md.id ");
		sql.append(" left join t_rbac_user tru on t.createuserid =  tru.id ");
		sql.append(" left join t_rbac_role trr on t.receivertype = trr.id ");
		sql.append(" where 1=1 ");
		
		formatSQL(sql, params, query);
		
		List<DataMailcostToExtVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DataMailcostToExtVO.class));
			System.out.println("searchDataMailcostListToExt SQL:"+sql.toString());
			System.out.println("searchDataMailcostListToExt Params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	
}
