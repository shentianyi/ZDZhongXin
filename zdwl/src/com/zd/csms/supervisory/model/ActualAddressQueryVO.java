package com.zd.csms.supervisory.model;

public class ActualAddressQueryVO {

	private int id;
	private int distribid;//经销商
	private String agreement_address;//协议地址
	private String actual_address;//实际监管地址
	private String relationship;//关系
	private int isreport;//是否上报
	private String distance;//距离
	private String des;//备注
	private int type;
	private int userid;
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
	public String getAgreement_address() {
		return agreement_address;
	}
	public void setAgreement_address(String agreementAddress) {
		agreement_address = agreementAddress;
	}
	public String getActual_address() {
		return actual_address;
	}
	public void setActual_address(String actualAddress) {
		actual_address = actualAddress;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public int getIsreport() {
		return isreport;
	}
	public void setIsreport(int isreport) {
		this.isreport = isreport;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
}
