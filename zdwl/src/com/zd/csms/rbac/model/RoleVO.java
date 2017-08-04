package com.zd.csms.rbac.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

@table(name="t_rbac_role")
public class RoleVO implements Serializable {

	/**
	 * 系统角色表
	 */
	private static final long serialVersionUID = 22358289207056749L;
	private int id;
	private int state;
	private String role_name;
	private int client_type;
	private String des;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String roleName) {
		role_name = roleName;
	}
	public int getClient_type() {
		return client_type;
	}
	public void setClient_type(int clientType) {
		client_type = clientType;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	
}
