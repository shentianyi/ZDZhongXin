package com.zd.csms.rbac.dao.oracle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zd.core.DAOSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.mapper.UserRoleMapper;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;

public class UserRoleOracleDAO extends DAOSupport implements IUserRoleDAO {
	
	//账户查询语句
	private static String SELECT_USER_ROLE = "select * from t_rbac_user_role ";

	public UserRoleOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public boolean isContentUser(int userId,String roleIds) {
		
		boolean flag =false;
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UserRoleOracleDAO.SELECT_USER_ROLE).append(" where user_id =? and role_id in (?)");
		params.add(userId);
		params.add(roleIds);
		
		try{
			List<Map<String, Object>> data = this.getJdbcTemplate().queryForList(sql.toString(), params.toArray());
			if(data != null && data.size() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			return flag;
		}
		return flag;
	}

	@Override
	public List<UserRoleVO> searchUserRoleByUserid(int userid) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UserRoleOracleDAO.SELECT_USER_ROLE).append(" where user_id =? ");
		params.add(userid);
		List<UserRoleVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserRoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<UserRoleVO> searchUserRoleList(UserRoleQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UserRoleOracleDAO.SELECT_USER_ROLE);
		formatUserRoleWhereSQL(query, sql, params);
		
		List<UserRoleVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserRoleMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	private boolean formatUserRoleWhereSQL(UserRoleQueryVO query, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		if(!params.isEmpty()){
			queryFlag = true;
		}
		
		if(!StringUtil.isEmpty(query.getUserids())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("user_id in( ");
			sql.append(query.getUserids());
			sql.append(" ) ");
			queryFlag = true;
		}
		if(query.getUser_id() != 0){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" user_id=? ");
			params.add(query.getUser_id());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(query.getRoleids())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("role_id in( ");
			sql.append(query.getRoleids());
			sql.append(" ) ");
			queryFlag = true;
		}
		if(query.getRole_id() != 0){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("role_id=? ");
			params.add(query.getRole_id());
			queryFlag = true;
		}
		return !queryFlag;

	}
	
	/**
	 * 根据权限查询对应的用户Id，如果不传参，则查询所有有角色的userId
	 * @param roleIds
	 * @return
	 */
	public String findUsingUserIdByRole(String...roleIds){
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql= new StringBuffer(" select wm_concat( distinct t.user_id) from t_rbac_user_role t ");
		sql.append(" inner join t_rbac_user u on u.id=t.user_id ");
		sql.append(" where u.state = ? ");
		params.add(StateConstants.USING.getCode());
		if(roleIds!=null&&roleIds.length>0){
			sql.append(" and t.role_id in (");
			for (String string : roleIds) {
				sql.append("?,");
				params.add(Integer.parseInt(string));
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(") ");
		}
		return (String) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), String.class);
	}
	
	/**
	 * 根据权限查询对应的用户Id，如果不传参，则查询所有有角色的userId
	 * @param roleIds
	 * @return
	 */
	public String findAllUserIdByRole(String...roleIds){
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql= new StringBuffer(" select wm_concat( distinct t.user_id) from t_rbac_user_role t ");
		if(roleIds!=null&&roleIds.length>0){
			sql.append(" where t.role_id in (");
			for (String string : roleIds) {
				sql.append("?,");
				params.add(Integer.parseInt(string));
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(") ");
		}
		
		return (String) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), String.class);
	}
}
