package com.zd.csms.rbac.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.base.web.model.SelectUserQueryVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserDAO;
import com.zd.csms.rbac.dao.mapper.UserMapper;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 
 *
 * @author licheng
 * at 2016年7月13日 下午2:00:54
 * 
 * &copy;北京科码先锋软件技术有限公司 2016
 */
public class UserOracleDAO extends DAOSupport implements IUserDAO {

	public UserOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	//账户查询语句
	private static String SELECT_USER = "select * from t_rbac_user";
	
	private boolean formatUserWhereSQL(UserQueryVO userQuery, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(userQuery.getUserName())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("username like ?");
			params.add("%"+userQuery.getUserName()+"%");
			queryFlag = true;
		}
		
		if(!StringUtil.isEmpty(userQuery.getLoginid())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("loginid like ?");
			params.add("%"+userQuery.getLoginid()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(userQuery.getLgid())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("loginid = ?");
			params.add(userQuery.getLgid());
			queryFlag = true;
		}
		
		if(userQuery.getClientId()>0){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("client_id = ?");
			params.add(userQuery.getClientId());
			queryFlag = true;
		}
		if(userQuery.getClienttype()>0){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("client_type = ?");
			params.add(userQuery.getClienttype());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(userQuery.getEmail())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("email = ?");
			params.add(userQuery.getEmail());
			queryFlag = true;
		}
		if(userQuery.getStates()!=null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("(");
			for(int i=0; i<userQuery.getStates().length; i++){
				if(i>0){
					sql.append(" or ");
				}
				sql.append("state=?");
				params.add(userQuery.getStates()[i]);
			}
			sql.append(")");
			queryFlag = true;
		}
		return !queryFlag;

	}

	@Override
	public UserVO searchUserByLoginid(String loginid) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UserOracleDAO.SELECT_USER).append(" where loginid=?");
		params.add(loginid);
		
		List<UserVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		if(result == null || result.size() == 0){
			return null;
		}
		return (UserVO)result.get(0);
	}

	@Override
	public List<UserVO> searchUserList(UserQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UserOracleDAO.SELECT_USER);
		formatUserWhereSQL(query, sql, params);
		sql.append(" order by id desc ");
		List<UserVO> result;
		
		System.out.println("searchUserList sql: "+sql.toString());
		System.out.println("searchUserList params: "+params.toArray());
		
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	@Override
	public List<UserVO> findUserListByClientTypeAndClientId(int clientType,int clientId) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from t_rbac_user where client_type = ? and client_id = ?  ");
		params.add(clientType);
		params.add(clientId);
		List<UserVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	@Override
	public List<UserVO> searchUserListByPage(UserQueryVO vo, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UserOracleDAO.SELECT_USER);
		formatUserWhereSQL(vo,sql,params);
		sql.append(" order by id desc ");
		List<UserVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@Override
	public List<UserVO> searchUserListByPage(SelectUserQueryVO userQuery, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.* from t_rbac_user t "
				+ "left join t_rbac_user_role ur "
				+ "on ur.user_id = t.id "
				+ "where ur.role_id = " + RoleConstants.WINDCONRTOL_EXTERNAL.getCode());//查询角色为风控部外控专员的用户列表
		
		if(StringUtil.isNotEmpty(userQuery.getUserName())){
			sql.append(" and t.username like ?");
			params.add("%"+userQuery.getUserName()+"%");
		}
		
		if(StringUtil.isNotEmpty(userQuery.getMobilePhone())){
			sql.append(" and t.mobilephone like ?");
			params.add("%"+userQuery.getMobilePhone()+"%");
		}
		
		sql.append(" order by t.id desc ");
		List<UserVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<UserVO> searchUserListWithRole(UserQueryVO query, int roleId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(UserOracleDAO.SELECT_USER);
		formatUserWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id in (select user_id from t_rbac_user_role where role_id=?)");
		params.add(roleId);
		
		List<UserVO> list;
		try{
			list = tools.goPage(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public List<UserVO> searchUserListWithoutRole(UserQueryVO query, int roleId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		sql.append(UserOracleDAO.SELECT_USER);
		formatUserWhereSQL(query,sql,params);
		if(params.isEmpty()){
			sql.append(" where ");
		} else{
			sql.append(" and ");
		}
		sql.append("id not in (select user_id from t_rbac_user_role where role_id=?)");
		params.add(roleId);
		
		List<UserVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new UserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	
	/**
	 * 根据部门查询 用户id 不传参数查询所有部门
	 * @param clientTypeIds
	 * @return
	 */
	public String findAllIdByClientType(String...clientTypeIds){
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql= new StringBuffer(" select wm_concat(t.id) from t_rbac_user t ");
		if(clientTypeIds!=null&&clientTypeIds.length>0){
			sql.append(" where t.client_type in (");
			for (String string : clientTypeIds) {
				sql.append("?,");
				params.add(string);
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(") ");
		}
		
		return (String) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), String.class);
	}
	
	@Override
	public List<String> findAllIds() {
		return getJdbcTemplate().queryForList("select loginid from t_rbac_user", String.class);
	}
}
