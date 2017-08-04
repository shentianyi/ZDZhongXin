package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_actual_address")
public class ActualAddressVO implements Serializable {

	/**
	 * 实际监管地址
	 */
	private static final long serialVersionUID = -3505414763301327658L;

	private int id;
	private int distribid;//经销商
	private String agreement_address;//协议地址
	private String actual_address;//实际监管地址
	private String relationship;//关系
	private int isreport;//是否上报
	private String distance;//距离
	private String des;//备注
	private int createuserid;//创建人
	private Date createdate;//创建时间
	private int upduserid;//修改人
	private Date upddate;//修改时间
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
	
	
}
