package com.zd.csms.rbac.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;

public class UserForm extends ActionForm {

	
	private static final long serialVersionUID = -8564571356027231103L;

	private UserVO user = new UserVO();					//账户操作对象（用于增删改操作）
	private UserQueryVO userQuery = new UserQueryVO();	//账户查询对象（用于列表查询）
	private int[] userIds;								//账户id数组（用于列表操作）
	private Integer[] roles;							//经销商添加用户分配的角色
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public UserQueryVO getUserQuery() {
		return userQuery;
	}
	public void setUserQuery(UserQueryVO userQuery) {
		this.userQuery = userQuery;
	}
	public int[] getUserIds() {
		return userIds;
	}
	public void setUserIds(int[] userIds) {
		this.userIds = userIds;
	}
	public Integer[] getRoles() {
		return roles;
	}
	public void setRoles(Integer[] roles) {
		this.roles = roles;
	}

	
	
}
