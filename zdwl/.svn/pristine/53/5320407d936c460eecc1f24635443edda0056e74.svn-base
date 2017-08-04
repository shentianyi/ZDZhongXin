package com.zd.csms.rbac.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.rbac.dao.IRoleDAO;
import com.zd.csms.rbac.dao.mapper.RoleMapper;
import com.zd.csms.rbac.model.RoleQueryVO;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RoleOracleDAO extends DAOSupport implements IRoleDAO {

	public RoleOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	private static String select_role = "select * from t_rbac_role";
	
	private boolean formatRoleWhereSQL(RoleQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(vo.getRoleName())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("role_name like ?");
			params.add("%"+vo.getRoleName()+"%");
			queryFlag = true;
		}
		
		if(vo.getClientType()>-1){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("client_type = ?");
			params.add(vo.getClientType());
			queryFlag = true;
		}
		
		if(vo.getStates()!=null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("(");
			for(int i=0; i<vo.getStates().length; i++){
				if(i>0){
					sql.append(" or ");
				}
				sql.append("state=?");
				params.add(vo.getStates()[i]);
			}
			sql.append(")");
			queryFlag = true;
		}
		return !queryFlag;
	}
	
	@Override
	public List<RoleVO> searchRoleList(RoleQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		
		List<RoleVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RoleVO> searchRoleListByPage(RoleQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		
		List<RoleVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RoleVO> searchRoleListWithObject(RoleQueryVO query, int objectId) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id in (select role_id from t_rbac_user_role where user_id in (select id from t_rbac_user where client_type=? and client_id=?))");
		params.add(query.getClientType());
		params.add(objectId);
		
		List<RoleVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RoleVO> searchRoleListWithResource(RoleQueryVO query, int resourceId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id in (select role_id from t_rbac_role_resource where resource_id=?)");
		params.add(resourceId);
		List<RoleVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RoleVO> searchRoleListWithUser(RoleQueryVO query, int userId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id in (select role_id from t_rbac_user_role where user_id=?)");
		params.add(userId);
		
		List<RoleVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RoleVO> searchRoleListWithUser(RoleQueryVO query, int userId) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id in (select role_id from t_rbac_user_role where user_id=?)");
		params.add(userId);
		
		List<RoleVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RoleVO> searchRoleListWithoutResource(RoleQueryVO query, int resourceId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id not in (select role_id from t_rbac_role_resource where resource_id=?)");
		params.add(resourceId);
		
		List<RoleVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<RoleVO> searchRoleListWithoutUser(RoleQueryVO query, int userId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(RoleOracleDAO.select_role);
		formatRoleWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id not in (select role_id from t_rbac_user_role where user_id=?)");
		params.add(userId);
		
		List<RoleVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new RoleMapper());
		}catch(Exception e){
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	
}
