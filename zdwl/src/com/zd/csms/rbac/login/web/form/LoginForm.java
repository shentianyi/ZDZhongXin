package com.zd.csms.rbac.login.web.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6825819753165400964L;

	private String loginid;
	private String password;
	private String lastPassword;
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastPassword() {
		return lastPassword;
	}
	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}
	
	
}
