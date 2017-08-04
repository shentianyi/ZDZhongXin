package com.zd.csms.rbac.model;

public class UserRoleQueryVO {

	private int user_id;
	private int role_id;
	private String roleids;
	private String userids;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int userId) {
		user_id = userId;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int roleId) {
		role_id = roleId;
	}
	public String getRoleids() {
		return roleids;
	}
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	
	
}
