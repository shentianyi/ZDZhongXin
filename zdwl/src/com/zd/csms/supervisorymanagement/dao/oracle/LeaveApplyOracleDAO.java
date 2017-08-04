package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.dao.ILeaveApplyDAO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyVO;
import com.zd.csms.supervisorymanagement.model.LeaveReplaceVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class LeaveApplyOracleDAO extends DAOSupport implements ILeaveApplyDAO{

	public LeaveApplyOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<LeaveApplyVO> findPageList(LeaveApplyQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct l.* from t_supervisory_leaveApply l "
				+ " left join t_current_Dealer c on l.id=c.client_id "
				+ " left join t_leave_replace r on l.id=r.leaveApplyId "
				+ " where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<LeaveApplyVO> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by lastmodifiedtime desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(LeaveApplyVO.class));
			System.out.println("请假申请："+sql.toString());
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	private void formatSQL(StringBuffer sql,List<Object> params,LeaveApplyQueryVO query){
		int currRole = query.getCurrentRole();
		int pageType = query.getPageType();
		
		if(query.getApplyTime()!=null){
			sql.append(" and l.applyTime = ? ");
			params.add(query.getApplyTime());
		}
		if(query.getBrandId()>0){
			sql.append(" and c.brandId = ? and c.type = ? ");
			params.add(query.getBrandId());
			params.add(CurrentDealerTypeContants.LEAVEAPPLY.getCode());
		}
		if(query.getBankId()>0){
			sql.append(" and c.bankId like ? and c.type = ? ");
			params.add("%,"+query.getBrandId()+",%");
			params.add(CurrentDealerTypeContants.LEAVEAPPLY.getCode());
		}
		if(query.getDealerId()>0){
			sql.append(" and c.dealerId = ? and c.type = ? ");
			params.add(query.getDealerId());
			params.add(CurrentDealerTypeContants.LEAVEAPPLY.getCode());
		}
		if(query.getLeavePerson()>0){
			sql.append(" and l.leavePerson = ? ");
			params.add(query.getLeavePerson());
		}
		if(query.getLeaveType()>0){
			sql.append(" and l.leaveType = ? ");
			params.add(query.getLeaveType());
		}
		if(StringUtil.isNotEmpty(query.getLeavePersonStaffNo())){
			sql.append(" and l.leavePersonStaffNo like ? ");
			params.add("%"+query.getLeavePersonStaffNo()+"%");
		}
		if(query.getLeaveStartTime()!=null){
			sql.append(" and l.leaveStartTime >= ? ");
			params.add(query.getLeaveStartTime());
		}
		if(query.getLeaveEndTime()!=null){
			sql.append(" and l.leaveEndTime <= ? ");
			params.add(query.getLeaveEndTime());
		}
		if(query.getReplaceName()>0){
			sql.append(" and r.replaceSupervisory = ? ");
			params.add(query.getReplaceName());
		}
		
		if(query.getReplaceStaffNo()>0){
			sql.append(" and r.replaceSupervisory = ? ");
			params.add(query.getReplaceStaffNo());
		}
		if(currRole>0){
			if(pageType == 1){//待审批
				if(currRole == RoleConstants.SUPERVISORY.getCode()){
					if(query.getCurrentRepositoryId()>0){
						sql.append(" and l.leavePerson = ? ");
						params.add(query.getCurrentRepositoryId());
					}
					sql.append(" and (l.approvalState=? or l.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (l.approvalState=? or l.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else{
					sql.append(" and l.nextApproval = ?  and l.approvalState = ? ");
					params.add(currRole);
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				}
			}else if(pageType==2){//已审批
				if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
					if(query.getCurrentRepositoryId()>0){
						sql.append(" and l.leavePerson = ? ");
						params.add(query.getCurrentRepositoryId());
					}
					sql.append(" and (l.approvalState=? or l.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (l.approvalState=? or l.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else {
					sql.append(" and l.id in "
							+ "(select ma.approvalobjectid "
							+ " from market_approval ma "
							+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
					params.add(ApprovalTypeContant.SUPERVISORYLEAVEAPPLY.getCode());
					params.add(currRole);
				}
			}
			
		}else{
			sql.append("and (approvalState=? or approvalState=?)");
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveReplaceVO> findLeaveReplaceListByLeaveApplyId(
			int leaveApplyId) {
		String sql=" select * from t_leave_replace where leaveApplyId = ? ";
		return this.getJdbcTemplate().query(sql.toString(),new Object[]{leaveApplyId}, new BeanPropertyRowMapper(LeaveReplaceVO.class));
	}

	@Override
	public boolean deleteLeaveReplaceListByLeaveApplyId(int leaveApplyId) {
		String sql=" delete  from t_leave_replace where leaveApplyId = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{leaveApplyId})>=0;
	}

	@Override
	public boolean updateLeaveApplyStatus(int id, int status,int approvalState,int nextApproval) {
		String sql=" update  t_supervisory_leaveApply set status = ?,approvalState=?,nextApproval=?  where id = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{status,approvalState,nextApproval,id})>=0;
	}
	
	/**
	 * 根据userid查询请假信息
	 * @param userid
	 * @return
	 */
	public List<LeaveApplyVO> findLeaveReplaceListByUserId(int userid,int year,int month) {
		String sql=" select * from t_supervisory_leaveApply where createuser = ? and (to_char(leaveStartTime,'yyyymm')=? or to_char(leaveEndTime,'yyyymm')=?) and approvalState=1 ";
		return this.getJdbcTemplate().query(sql.toString(),new Object[]{userid,year+(month<10?"0"+month:""+month),year+(month<10?"0"+month:""+month)}, new BeanPropertyRowMapper(LeaveApplyVO.class));
	}

	@Override
	public List<LeaveReplaceVO> findLeaveReplaceListByRepositoryIdAndMonth(
			int repositoryId, int year, int month) {
		String sql=" select * from t_leave_replace r inner join t_supervisory_leaveApply l on r.leaveApplyId=l.id"
				+ " where r.replaceSupervisory = ? and (to_char(r.replaceStartTime,'yyyymm')=? or to_char(r.replaceStartTime,'yyyymm')=?) and l.approvalState=1 ";
		return this.getJdbcTemplate().query(sql.toString(),new Object[]{repositoryId,year+(month<10?"0"+month:""+month),year+(month<10?"0"+month:""+month)}, new BeanPropertyRowMapper(LeaveReplaceVO.class));
	}

}
