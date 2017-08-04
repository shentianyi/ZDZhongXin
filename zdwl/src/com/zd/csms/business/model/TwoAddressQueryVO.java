package com.zd.csms.business.model;

public class TwoAddressQueryVO {

	private int id;
	private int distribid;//经销商
	private String two_name;//二网名称
	private String two_address;//二网地址
	private String two_username;//二网联系人
	private String phone;//联系方式
	private String distance;//距离
	private String des;//备注
	private String distribids;
	private String ids;
	private int userid;
	private int types;
	private int type;
	
	private int client_id;
	private int currentRole;//当期角色
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
	public String getTwo_name() {
		return two_name;
	}
	public void setTwo_name(String twoName) {
		two_name = twoName;
	}
	public String getTwo_address() {
		return two_address;
	}
	public void setTwo_address(String twoAddress) {
		two_address = twoAddress;
	}
	public String getTwo_username() {
		return two_username;
	}
	public void setTwo_username(String twoUsername) {
		two_username = twoUsername;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getDistribids() {
		return distribids;
	}
	public void setDistribids(String distribids) {
		this.distribids = distribids;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(int currentRole) {
		this.currentRole = currentRole;
	}
	
	
}
