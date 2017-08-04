package com.zd.csms.rbac.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_rbac_user")
public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6348857295726046280L;
	private int id;
	private String userName;//用户名
	private String loginid;//登录名
	private String password;//密码
	private String password_random;//
	private String mobilephone;//手机
	private String email;//邮箱
	private int state;//状态
	private int client_type;//所属部门
	private int client_id;//角色id 监管员：储备库主键id
	private Date createtime;//创建时间
	private int createuserid;//创建人
	private int upduserid;//修改人
	private Date upddate;//修改时间
	
	
	
	public int getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	public int getUpduserid() {
		return upduserid;
	}
	public void setUpduserid(int upduserid) {
		this.upduserid = upduserid;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getPassword_random() {
		return password_random;
	}
	public void setPassword_random(String passwordRandom) {
		password_random = passwordRandom;
	}
	public int getClient_type() {
		return client_type;
	}
	public void setClient_type(int clientType) {
		client_type = clientType;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int clientId) {
		client_id = clientId;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", userName=" + userName + ", loginid="
				+ loginid + ", password=" + password + ", password_random="
				+ password_random + ", mobilephone=" + mobilephone + ", email="
				+ email + ", state=" + state + ", client_type=" + client_type
				+ ", client_id=" + client_id + ", createtime=" + createtime
				+ "]";
	}
}
