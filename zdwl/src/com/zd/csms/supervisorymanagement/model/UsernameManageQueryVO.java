package com.zd.csms.supervisorymanagement.model;

public class UsernameManageQueryVO {

	private int id;
	private int distribid;//经销商id
	private String bankname;//合作机构
	private String supervisory_name;//监管员姓名
	private String loginid;//账号
	private String password;//密码
	private String des;//备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDistribid() {
		return distribid;
	}
	public void setDistribid(int distribid) {
		this.distribid = distribid;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getSupervisory_name() {
		return supervisory_name;
	}
	public void setSupervisory_name(String supervisoryName) {
		supervisory_name = supervisoryName;
	}
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
