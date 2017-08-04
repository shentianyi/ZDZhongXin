package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.dao.IRepairCostDAO;
import com.zd.csms.supervisory.dao.mapper.ExtRepairCostVOMapper;
import com.zd.csms.supervisory.dao.mapper.RepairCostMapper;
import com.zd.csms.supervisory.model.ExtRepairCostVO;
import com.zd.csms.supervisory.model.RepairCostQueryVO;
import com.zd.csms.supervisory.model.RepairCostVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RepairCostOracleDAO extends DAOSupport implements IRepairCostDAO {

	public RepairCostOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句、、
	private static String select_repair_cost = "select * from t_repair_cost";

	private void formatSQL(StringBuffer sql,List<Object> params,RepairCostQueryVO query){
		sql.append(" where 1=1 ");
		
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		if(currRole>0){
			if(pageType == 1){//待审批
				if(currRole == RoleConstants.SUPERVISORY.getCode()){
					if(query.getPromoter()>0){
						sql.append(" and promoter = ? ");
						params.add(query.getPromoter());
					}
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
				if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
					if(query.getPromoter()>0){
						sql.append(" and promoter = ? ");
						params.add(query.getPromoter());
					}
					sql.append(" and (approvalState=?) ");
/*					sql.append(" and (approvalState=? or approvalState=?) ");
*/					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					/*params.add(ApprovalContant.APPROVAL_NOPASS.getCode());*/
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (approvalState=?) ");
/*					sql.append(" and (approvalState=? or approvalState=?) ");
*/					params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else {
					sql.append(" and id in "
							+ "(select ma.approvalobjectid "
							+ " from market_approval ma "
							+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
					params.add(ApprovalTypeContant.REPAIRCOST.getCode());
					params.add(currRole);
				}
			}
		}else if(currRole == -1){
			if(query.getRepair_project()!=null&&!"".equals(query.getRepair_project().trim())){
				sql.append(" and repair_project = ?");
				params.add(query.getRepair_project());
			}
			
			if(query.getPromoter() > 0){
				sql.append(" and promoter = ?");
				params.add(query.getPromoter());
			}
			sql.append(" and approvalState <> ? ");
			params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		}else{
			sql.append(" and (approvalState=? or approvalState=?) ");
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}
	}

	private void leadgerFormatSQL(StringBuffer sql,List<Object> params,RepairCostQueryVO query){
	
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		if(currRole>0){
			if(pageType == 1){//待审批
				if(currRole == RoleConstants.SUPERVISORY.getCode()){
					if(query.getPromoter()>0){
						sql.append(" and promoter = ? ");
						params.add(query.getPromoter());
					}
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
				if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
					if(query.getPromoter()>0){
						sql.append(" and promoter = ? ");
						params.add(query.getPromoter());
					}
					sql.append(" and (approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
				}else {
					sql.append(" and id in "
							+ "(select ma.approvalobjectid "
							+ " from market_approval ma "
							+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
					params.add(ApprovalTypeContant.REPAIRCOST.getCode());
					params.add(currRole);
				}
			}
		}else if(currRole == -1){
			if(query.getRepair_project()!=null&&!"".equals(query.getRepair_project().trim())){
				sql.append(" and repair_project = ?");
				params.add(query.getRepair_project());
			}
			
			if(query.getPromoter() > 0){
				sql.append(" and promoter = ?");
				params.add(query.getPromoter());
			}
			sql.append(" and approvalState <> ? ");
			params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		}else{
			sql.append(" and (approvalState=? or approvalState=?) ");
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}
	}

	@Override
	public List<RepairCostVO> searchRepairCostList(RepairCostQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RepairCostOracleDAO.select_repair_cost);
		List<RepairCostVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RepairCostMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RepairCostVO> searchRepairCostListByPage(RepairCostQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RepairCostOracleDAO.select_repair_cost);
		if(query.getCurrRole() != null && query.getPageType() != null){
			formatSQL(sql, params,query);
		}
		List<RepairCostVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RepairCostMapper());
			System.out.println("设备维修费用申请searchRepairCostListByPage sql:"+sql.toString());
			System.out.println("设备维修费用申请searchRepairCostListByPage params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	//设备维修费用申请台账1
	@SuppressWarnings("unchecked")
	@Override
	public List<RepairCostVO> searchRepairCostListByPage(RepairCostQueryVO query, IThumbPageTools tools,HttpServletRequest request) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		UserSession user = UserSessionUtil.getUserSession(request);
		sql.append("select trc.id,trc.promoter,trc.repair_project,trc.problem,trc.money,trc.credatetime,trc.nextApproval,trc.approvalState,trc.createuserid,trc.createdate,trc.upduserid,trc.upddate from t_repair_cost ");
		sql.append(" trc join t_rbac_user tru on trc.promoter=tru.id ");
		if (user.getUser().getClient_type()==3) {//监管员
			sql.append(" where trc.promoter=?");
			params.add(user.getUser().getId());
		}/*else if (user.getUser().getClient_type()==2) {//监管管理部
			sql.append(" where trc.promoter=?");
			params.add(user.getUser().getId());
		}*/
		if(query.getCurrRole() != null && query.getPageType() != null){
			leadgerFormatSQL(sql, params,query);
		}
		List<RepairCostVO> result;
		System.out.println("设备维修费用申请台账searchRepairCostListByPage sql:"+sql.toString());
		System.out.println("设备维修费用申请台账searchRepairCostListByPage params:"+params);
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RepairCostMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题//
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			
			System.out.println("searchRepairCostListByPage :"+sql.toString());
			System.out.println("searchRepairCostListByPage :"+params);
			
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	@Override
	public List<RepairCostVO> searchRepairCostAllListByPage(RepairCostQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RepairCostOracleDAO.select_repair_cost);
		formatSQL(sql, params,query);
		List<RepairCostVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RepairCostMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ExtRepairCostVO> ExtRepairCostLedger(RepairCostQueryVO query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select tr.username as declareUser, tr1.username as createUser,tr2.username as updateUser,t.* ");
		sql.append(" from t_repair_cost t ");
		sql.append(" left join t_rbac_user tr on tr.id = t.promoter ");
		sql.append(" left join t_rbac_user tr1 on tr1.id = t.createuserid ");
		sql.append(" left join t_rbac_user tr2 on tr2.id = t.upduserid ");
		if(query.getCurrRole() != null && query.getPageType() != null){
			formatSQL(sql, params,query);
		}
		List<ExtRepairCostVO> result;
		try{
			result = getJdbcTemplate().query(sql.toString(), params.toArray(), new ExtRepairCostVOMapper());
			System.out.println("设备维修费用申请台账searchRepairCostListByPage sql:"+sql.toString());
			System.out.println("设备维修费用申请台账searchRepairCostListByPage params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
