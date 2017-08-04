package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.dao.oracle.DealerDAOImpl;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisorymanagement.dao.IHandoverPlanDAO;
import com.zd.csms.supervisorymanagement.model.ExtHandoverPlanVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class HandoverPlanOracleDAO extends DAOSupport implements IHandoverPlanDAO{
	IDealerDAO dealerDao = MarketFactory.getIDealerDAO();
	public HandoverPlanOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HandoverPlanVO> searchHandoverPlanList(HandoverPlanQueryVO query) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_HANDOVER_PLAN where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<HandoverPlanVO> list = null;
		formatSQL(sql, params,query);
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(HandoverPlanVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HandoverPlanVO> searchHandoverPlanListByPage(
			HandoverPlanQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_HANDOVER_PLAN where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<HandoverPlanVO> list = null;
		formatSQL(sql, params,query);
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(HandoverPlanVO.class));
			System.out.println("searchHandoverPlanListByPage sql:"+sql.toString());
			System.out.println("searchHandoverPlanListByPage params"+params);
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	private void formatSQL(StringBuffer sql,List<Object> params,HandoverPlanQueryVO query){
		int currRole = query.getCurrentRole();
		int pageType = query.getPageType();
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode() ){
				sql.append(" and (approveStatus=? or approveStatus=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (approveStatus=? or approveStatus=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				sql.append(" and approveStatus=? and nextApproval = ?");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(currRole);
			}else{
				sql.append(" and nextApproval = ?  and approveStatus = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
		}else if(pageType==2){//已审批
			if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode() ){//如果角色是发起者
				sql.append(" and (approveStatus=? or approveStatus=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (approveStatus=? or approveStatus=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()){
				//调配专员
				sql.append(" and (approveStatus=? or approveStatus=?) ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}/*else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				//监管员管理部经理
				sql.append(" and approveStatus != ?");
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				sql.append(" and (approveStatus=? or approveStatus=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}*/else {
				sql.append(" and id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.HANDOVERPLAN.getCode());
				params.add(currRole);
			}

		}
		if(StringUtils.isNotEmpty(query.getBindMerchant())){
			sql.append(" and bindMerchant = ? ");
			params.add(query.getBindMerchant());
		}
		if(query.getDeliverer()>0){
			sql.append(" and deliverer = ? ");
			params.add(query.getDeliverer());
		}
		if(query.getReceiver()>0){
			sql.append(" and receiver = ? ");
			params.add(query.getReceiver());
		}
		if(query.getMissionDate()!=null){
			sql.append(" and missionDate = ? ");
			params.add(query.getMissionDate());
		}
		if(query.getDealerId()>0){
			sql.append(" and delivererDealerID like ? ");
			params.add("%"+query.getDealerId()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			List<DealerBankVO> dealerBanks=dealerDao.getDealerListByBankName(query.getBankName());
			if(dealerBanks!=null && dealerBanks.size()>0){
				sql.append(" and ( ");
				for(DealerBankVO dealerBank:dealerBanks){
					sql.append(" delivererDealerID like ? or");
					params.add("%"+dealerBank.getDealerId()+"%");
				}
				sql.delete(sql.length()-2,sql.length());
				sql.append(" ) ");
			}
		}
		if(query.getBrandId()>0){
			List<DealerVO> dealers=dealerDao.getDealerListByBrandId(query.getBrandId());
			if(dealers!=null && dealers.size()>0){
				sql.append(" and ( ");
				for(DealerVO dealer:dealers){
					sql.append(" delivererDealerID like ? or");
					params.add("%"+dealer.getId()+"%");
				}
				sql.delete(sql.length()-2,sql.length());
				sql.append(" ) ");
			}
		}
		if(query.getApprovalState()>0){
			sql.append(" and approveStatus=? ");
			params.add(query.getApprovalState());
		}
		
	}

	@Override
	public boolean updHandoverPlanEditStatus(HandoverPlanVO handoverPlan) {
		StringBuffer sql= new StringBuffer();
		sql.append(" update T_HANDOVER_PLAN set isEdit = ?,approveStatus = ?,nextApproval = ? where id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(handoverPlan.getIsEdit());
		params.add(handoverPlan.getApproveStatus());
		params.add(handoverPlan.getNextApproval());
		params.add(handoverPlan.getId());
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	}

	/**
	 * 导出轮岗计划表台账 --需求57
	 * @time 20170517
	 */
	@SuppressWarnings("unchecked")
	public List<ExtHandoverPlanVO> ExtHandoverPlanLedgerList(HandoverPlanQueryVO handoverPlanQuery){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		int currRole = handoverPlanQuery.getCurrentRole();
		
		sql.append("select h.*, tsb.name as delivererNTT, tsi.name as receiverNTT,tu.username as nextapprovalNTT, tu1.username as createuserNTT, tu2.username as lastmodifyuserNTT ");
		sql.append(" from T_HANDOVER_PLAN h ");
		sql.append(" left join t_repository tr on h.deliverer = tr.id ");
		sql.append(" left join t_repository tr1 on h.receiver = tr1.id ");
		sql.append(" left join t_supervisor_basic_information tsb on tr.supervisorid = tsb.id ");
		sql.append(" left join t_supervisor_basic_information tsi on tr1.supervisorid = tsi.id ");
		sql.append(" left join t_rbac_user tu on tu.id = h.nextapproval ");
		sql.append(" left join t_rbac_user tu1 on tu1.id = h.createuser ");
		sql.append(" left join t_rbac_user tu2 on tu2.id = h.lastmodifyuser ");
		sql.append(" where 1=1 ");
		
		formatSQL(sql, params, handoverPlanQuery);
		
		List<ExtHandoverPlanVO> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ExtHandoverPlanVO.class));
			System.out.println("ExtHandoverPlanLedgerList sql:"+sql.toString());
			System.out.println("ExtHandoverPlanLedgerList params"+params);
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

}
