package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.dao.IExtraworkApplyDAO;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ExtraworkApplyOracleDAO extends DAOSupport implements IExtraworkApplyDAO{

	public ExtraworkApplyOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<ExtraworkApplyVO> findPageList(ExtraworkApplyQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct e.* from t_extrawork_apply e left join t_current_Dealer c on e.id=c.client_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<ExtraworkApplyVO> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by e.lastmodifiedtime desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ExtraworkApplyVO.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	private void formatSQL(StringBuffer sql,List<Object> params,ExtraworkApplyQueryVO query){
		int currRole = query.getCurrentRole();
		int pageType = query.getPageType();
		
		if(query.getApplyTime()!=null){
			sql.append(" and e.applyTime = ? ");
			params.add(query.getApplyTime());
		}
		
		//
		if(query.getBrandId()>0){
			sql.append(" and c.brandId = ? and c.type = ? ");
			params.add(query.getBrandId());
			params.add(CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		}
		
		if(query.getBankId()>0){
			sql.append(" and c.bankId like ? and c.type = ? ");
			params.add("%,"+query.getBrandId()+",%");
			params.add(CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		}
		if(query.getDealerId()>0){
			sql.append(" and c.dealerId = ? and c.type = ? ");
			params.add(query.getDealerId());
			params.add(CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		}
		
		if(query.getExtraworkPerson()>0){
			sql.append(" and e.repositoryId = ? ");
			params.add(query.getExtraworkPerson());
		}
		if(StringUtil.isNotEmpty(query.getName())){
			sql.append(" and e.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getStaffNo())){
			sql.append(" and e.staffNo like ? ");
			params.add("%"+query.getStaffNo()+"%");
		}
		if(StringUtil.isNotEmpty(query.getProvince())){
			sql.append(" and e.province like ? ");
			params.add("%"+query.getProvince()+"%");
		}
		if(StringUtil.isNotEmpty(query.getCity())){
			sql.append(" and e.city like ? ");
			params.add("%"+query.getCity()+"%");
		}
		if(query.getExtraWorkDays()>0){
			sql.append(" and e.extraWorkDays = ? ");
			params.add(query.getExtraWorkDays());
		}
		if(query.getStartTime()!=null){
			sql.append(" and e.startTime >= ? ");
			params.add(query.getStartTime());
		}
		if(query.getEndTime()!=null){
			sql.append(" and e.endTime <= ? ");
			params.add(query.getEndTime());
		}
		
		if(currRole>0){
			if(pageType == 1){//待审批
				if(currRole == RoleConstants.SUPERVISORY.getCode()){
					if(query.getCurrentRepositoryId()>0){
						sql.append(" and e.repositoryId = ? ");
						params.add(query.getCurrentRepositoryId());
					}
					sql.append(" and (e.approvalState=? or e.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (e.approvalState=? or e.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}
				else{
					sql.append(" and e.nextApproval = ?  and e.approvalState = ? ");
					params.add(currRole);
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				}
			}else if(pageType==2){//已审批
				if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
					if(query.getCurrentRepositoryId()>0){
						sql.append(" and e.repositoryId = ? ");
						params.add(query.getCurrentRepositoryId());
					}
					sql.append(" and (e.approvalState=? or e.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (e.approvalState=? or e.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else {
					sql.append(" and e.id in "
							+ "(select ma.approvalobjectid "
							+ " from market_approval ma "
							+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
					params.add(ApprovalTypeContant.SUPERVISORYEXTRAWORKAPPLY.getCode());
					params.add(currRole);
				}
			}
			
		}else{
			sql.append("and (e.approvalState=? or e.approvalState=?)");
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}
	}

	@Override
	public boolean updateExtraworkApplyStatus(int id, int status,int approvalState,int nextApproval) {
		String sql=" update t_extrawork_apply set status = ? , approvalState=? , nextApproval=?  where id = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{status,approvalState,nextApproval,id})>=0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CurrentDealerVO> findCurrentDealerListByExtraworkApplyId(
			int extraworkApplyId, int type) {
		String sql=" select * from t_current_Dealer where client_id = ? and type= ? ";
		return this.getJdbcTemplate().query(sql.toString(),new Object[]{extraworkApplyId,type}, new BeanPropertyRowMapper(CurrentDealerVO.class));
	}

	@Override
	public boolean deleteCurrentDealerByExtraworkApply(int extraworkApplyId,
			int code) {
		String sql=" delete  from t_current_Dealer where client_id = ? and type= ?  ";
		return this.getJdbcTemplate().update(sql, new Object[]{extraworkApplyId,code})>=0;
	}

	/**
	 * 根据储备库ID和年月查询审批通过的加班申请列表
	 * @param repositoryId
	 * @param year
	 * @param month
	 * @return
	 */
	@Override
	public List<ExtraworkApplyVO> findExtraworkListByRepositoryIdAndMonth(
			int repositoryId, int year, int month) {
		String sql=" select * from t_extrawork_apply "
				+ " where repositoryId = ? and (to_char(startTime,'yyyymm')=? or to_char(endTime,'yyyymm')=?) and approvalState = 1 ";
		return this.getJdbcTemplate().query(sql.toString(),new Object[]{repositoryId,year+(month<10?"0"+month:""+month),year+(month<10?"0"+month:""+month)}, new BeanPropertyRowMapper(ExtraworkApplyVO.class));
	}
}
