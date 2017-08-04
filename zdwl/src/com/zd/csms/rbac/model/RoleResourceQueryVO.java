package com.zd.csms.rbac.model;

public class RoleResourceQueryVO {

	private int id;
	private int resource_id;//资源
	private int role_id;//角色
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResource_id() {
		return resource_id;
	}
	public void setResource_id(int resourceId) {
		resource_id = resourceId;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int roleId) {
		role_id = roleId;
	}
	
	
}
