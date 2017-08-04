package com.zd.csms.rbac.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;

public interface IUserRoleDAO extends IDAO {

	public String getDataSourceName();
	
	//验证用户是否属于指定角色
	public boolean isContentUser(int userId,String roleIds);
	
	public List<UserRoleVO> searchUserRoleByUserid(int userid);
	
	public List<UserRoleVO> searchUserRoleList(UserRoleQueryVO query);
	
	/**
	 * 根据权限查询对应的用户Id，如果不传参，则查询所有有角色的userId
	 * @param roleIds
	 * @return
	 */
	public String findUsingUserIdByRole(String...roleIds);
	
	/**
	 * 根据权限查询对应的用户Id，如果不传参，则查询所有有角色的userId
	 * @param roleIds
	 * @return
	 */
	public String findAllUserIdByRole(String...roleIds);
}
