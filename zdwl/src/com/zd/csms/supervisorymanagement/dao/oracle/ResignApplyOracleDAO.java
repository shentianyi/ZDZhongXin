package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.dao.IResignApplyDAO;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyVO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ResignApplyOracleDAO extends DAOSupport implements IResignApplyDAO{

	public ResignApplyOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<ResignApplyVO> findPageList(ResignApplyQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct r.* from t_resign_apply r left join t_current_Dealer c on r.id=c.client_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<ResignApplyVO> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by r.lastmodifiedtime desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ResignApplyVO.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	@Override
	public List<ResignApplyVO> findList(ResignApplyQueryVO query) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct r.* from t_resign_apply r left join t_current_Dealer c on r.id=c.client_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<ResignApplyVO> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by r.lastmodifiedtime desc ");
		System.out.println(sql.toString());
		try {
			list =this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ResignApplyVO.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	private void formatSQL(StringBuffer sql,List<Object> params,ResignApplyQueryVO query){
		int currRole = query.getCurrentRole();
		int pageType = query.getPageType();
		
		if(query.getApplyTime()!=null){
			sql.append(" and r.applyTime = ? ");
			params.add(query.getApplyTime());
		}
		//
		if(query.getBrandId()>0){
			sql.append(" and c.brandId = ? and c.type = ? ");
			params.add(query.getBrandId());
			params.add(CurrentDealerTypeContants.RESIGNAPPLY.getCode());
		}
		
		if(query.getDealerId()>0){
			sql.append(" and c.dealerId = ? and c.type = ? ");
			params.add(query.getDealerId());
			params.add(CurrentDealerTypeContants.RESIGNAPPLY.getCode());
		}
		if(query.getBankId()>0){
			sql.append(" and c.bankId like ? and c.type = ? ");
			params.add("%,"+query.getBankId()+",%");
			params.add(CurrentDealerTypeContants.RESIGNAPPLY.getCode());
		}
		
		if(query.getResignPerson()>0){
			sql.append(" and r.repositoryId = ? ");
			params.add(query.getResignPerson());
		}
		if(query.getApprovalState()>0){
			sql.append(" and r.approvalState = ? ");
			params.add(query.getApprovalState());
		}
		if(StringUtil.isNotEmpty(query.getName())){
			sql.append(" and r.name like ? ");
			params.add("%"+query.getName()+"%");
		}
		if(StringUtil.isNotEmpty(query.getStaffNo())){
			sql.append(" and r.staffNo like ? ");
			params.add("%"+query.getStaffNo()+"%");
		}
		if(query.getExpectResignTime()!=null){
			sql.append(" and to_char(r.expectResignTime,'yyyyMMdd') = ? ");
			params.add(DateUtil.getStringFormatByDate(query.getExpectResignTime(), "yyyyMMdd"));
		}
		if(currRole>0){
			if(pageType == 1){//待审批
				if(currRole == RoleConstants.SUPERVISORY.getCode()){
					if(query.getCurrentRepositoryId()>0){
						sql.append(" and r.repositoryId = ? ");
						params.add(query.getCurrentRepositoryId());
					}
					sql.append(" and (r.approvalState=? or r.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (r.approvalState=? or r.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}/*else if(currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){//业务经理
					sql.append("and r.nextApproval = ? and (r.approvalState=? or r.approvalState=?)  ");
					//params.add(currRole);
					params.add(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode());
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){//业务专员
					sql.append("and r.nextApproval = ? and (l.approvalState=? or r.approvalState=?)  ");
					//params.add(currRole);
					params.add(RoleConstants.BUSINESS_AMALDAR.getCode());
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()){//招聘专员
					sql.append("and r.nextApproval = ? and (r.approvalState=? or r.approvalState=?)  ");
					//params.add(currRole);
					params.add(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){//招聘专员
					sql.append("and r.nextApproval = ? and (r.approvalState=? or r.approvalState=?)  ");
					//params.add(currRole);
					params.add(currRole);
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
					params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				}*/else{
					sql.append(" and r.nextApproval = ?  and r.approvalState = ? ");
					params.add(currRole);
					params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				}
			}else if(pageType==2){//已审批
				if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
					if(query.getCurrentRepositoryId()>0){
						sql.append(" and r.repositoryId = ? ");
						params.add(query.getCurrentRepositoryId());
					}
					sql.append(" and (r.approvalState=? or r.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}else if(currRole == RoleConstants.SR.getCode()){
					sql.append(" and (r.approvalState=? or r.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}/*else if(currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){
					sql.append(" and (r.approvalState=? or r.approvalState=?) ");
					params.add(ApprovalContant.APPROVAL_PASS.getCode());
					params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				}*/else {
					sql.append(" and r.id in "
							+ "(select ma.approvalobjectid "
							+ " from market_approval ma "
							+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
					params.add(ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
					params.add(currRole);
				}
			}
		}else{
			sql.append("and (r.approvalState=? or r.approvalState=?)");
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}
	}

	@Override
	public boolean updateResignApplyStatus(int id, int status,int approvalState,int nextApproval) {
		String sql=" update t_resign_apply set status = ? , approvalState=? , nextApproval=?  where id = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{status,approvalState,nextApproval,id})>=0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CurrentDealerVO> findCurrentDealerListByResignApplyId(
			int resignApplyId, int type) {
		String sql=" select * from t_current_Dealer where client_id = ? and type= ? ";
		return this.getJdbcTemplate().query(sql.toString(),new Object[]{resignApplyId,type}, new BeanPropertyRowMapper(CurrentDealerVO.class));
	}

	@Override
	public boolean deleteCurrentDealerByResignApply(int resignApplyId,
			int code) {
		String sql=" delete  from t_current_Dealer where client_id = ? and type= ?  ";
		return this.getJdbcTemplate().update(sql, new Object[]{resignApplyId,code})>=0;
	}

	@Override
	public ResignApplyVO getByResignPerson(int resignPerson) {
		String sql=" select * from t_resign_apply where repositoryId = ? ";
		return (ResignApplyVO) this.getJdbcTemplate().queryForObject(sql.toString(),new Object[]{resignPerson}, new BeanPropertyRowMapper(ResignApplyVO.class));
	}

	@Override
	public List<ResignApplyVO> findListByExpectResignTime(Date expectResignTime) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select distinct r.* from t_resign_apply r left join t_current_Dealer c on r.id=c.client_id where 1=1 ");
		if(expectResignTime!=null){
			sql.append(" and r.expectResignTime <= ? ");
			params.add(expectResignTime);
		}
		List<ResignApplyVO> list = null;
		sql.append(" order by r.lastmodifiedtime desc ");
		System.out.println(sql.toString());
		try {
			list =this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ResignApplyVO.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

}
