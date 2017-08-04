package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisory.dao.IDuedateDAO;
import com.zd.csms.supervisory.model.DuedateQueryVO;
import com.zd.csms.supervisory.model.DuedateVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class DuedateOracleDAO extends DAOSupport implements IDuedateDAO {

	public DuedateOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_duedate = "select * from t_duedate where 1=1 ";

	private boolean formatDuedateWhereSQL(DuedateQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getSuperviseid() > 0){
			sql.append(" and superviseid = ?");
			params.add(vo.getSuperviseid());
			queryFlag = true;
		}
		
		if(vo.getType() > 0){
			sql.append("and type = ?");
			params.add(vo.getType());
			queryFlag = true;
		}
		if(vo.getApprovalState() > 0){
			sql.append("and approvalState = ?");
			params.add(vo.getApprovalState());
			queryFlag = true;
		}
		if(StringUtil.isNotEmpty(vo.getDealerName())){
			sql.append("and dealerName like ?");
			params.add("%"+vo.getDealerName()+"%");
			queryFlag = true;
		}
		if(StringUtil.isNotEmpty(vo.getBankName())){
			sql.append("and bankName like ?");
			params.add("%"+vo.getBankName()+"%");
			queryFlag = true;
		}
		if(StringUtil.isNotEmpty(vo.getBrandName())){
			sql.append("and brandName like ?");
			params.add("%"+vo.getBrandName()+"%");
			queryFlag = true;
		}
		return !queryFlag;
	}
	
	private void formatSQL(StringBuffer sql,List<Object> params,DuedateQueryVO query){
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.SUPERVISORY.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (approvalState=? or approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}/*else{
				sql.append(" and nextApproval = ?  and approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}*/
		}/*else if(pageType==2){//已审批
			if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
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
				params.add(ApprovalTypeContant.BENDUEDATE.getCode());
				params.add(currRole);
			}

		}*/
	}

	@Override
	public List<DuedateVO> searchDuedateList(DuedateQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DuedateOracleDAO.select_duedate);
		formatDuedateWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<DuedateVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(DuedateVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<DuedateVO> searchDuedateListByPage(DuedateQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DuedateOracleDAO.select_duedate);
		formatDuedateWhereSQL(query,sql,params);
		if(query.getCurrRole() != null && query.getPageType() != null){
			formatSQL(sql, params,query);
		}
		sql.append(" order by createdate desc ");
		List<DuedateVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(DuedateVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
