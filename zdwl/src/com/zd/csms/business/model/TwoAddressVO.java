package com.zd.csms.business.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_two_address")
public class TwoAddressVO implements Serializable {

	/**
	 * 二网地址
	 */
	private static final long serialVersionUID = 5529964947146257313L;

	private int id;
	private int distribid;//经销商
	private String two_name;//二网名称
	private String two_address;//二网地址
	private String two_username;//二网联系人
	private String phone;//联系方式
	private String distance;//距离
	private String des;//备注
	private int createuserid;//创建人
	private Date createdate;//创建时间
	private int upduserid;//修改人
	private Date upddate;//修改时间
	private int type;//类型 1：本库 2：二库 3：二网
	private String bkwhcode;
	
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
	public int getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getBkwhcode() {
		return bkwhcode;
	}
	public void setBkwhcode(String bkwhcode) {
		this.bkwhcode = bkwhcode;
	}
	@Override
	public String toString() {
		return "TwoAddressVO [id=" + id + ", distribid=" + distribid
				+ ", two_name=" + two_name + ", two_address=" + two_address
				+ ", two_username=" + two_username + ", phone=" + phone
				+ ", distance=" + distance + ", des=" + des + ", createuserid="
				+ createuserid + ", createdate=" + createdate + ", upduserid="
				+ upduserid + ", upddate=" + upddate + ", type=" + type
				+ ", bkwhcode=" + bkwhcode + "]";
	}
}
