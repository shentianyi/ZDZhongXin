package com.zd.csms.supervisory.model.supervisorrepair;

import java.io.Serializable;

import com.zd.core.annotation.table;

@table(name="T_SUPERVISOR_REPAIRCOST")
public class SupervisorRepairCostMessageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 设备维修申请   提醒
	 */
	
	private int id;
	/**
	 * 监管员name
	 */
	private String name;//监管员name

	/**
	 * 经销商名字
	 */
	private String merchantname;
	/**
	 * 金融机构的名字
	 */
	private String bankname;
	
	/**
	 * 工号
	 */
	private String jobnumber;
	/**
	 * 维修项目
	 */
	private String maintenanceitems;
	/**
	 * 消息类型
	 */
	private String messagetype;
	/**
	 * 维修费用
	 */
	private String cost;
	/**
	 * 监管员Id
	 */
	private int supervisorid;
	/**
	 * 1未读 2已读
	 */
	private int isread;
	/**
	 * 用户Id
	 */
	private int userId;
	
	/**
	 * 
	 */
	private int messageId;
	
	
	/**
	 * @return the 
	 */
	public int getMessageId() {
		return messageId;
	}
	/**
	 * @param  the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the 用户Id
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param 用户Id the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the 金融机构的名字
	 */
	public String getBankname() {
		return bankname;
	}
	/**
	 * @param 金融机构的名字 the bankname to set
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the 监管员name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param 监管员name the 监管员name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the 经销商名字
	 */
	public String getMerchantname() {
		return merchantname;
	}
	/**
	 * @param 经销商名字 the merchantname to set
	 */
	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}
	/**
	 * @return the 工号
	 */
	public String getJobnumber() {
		return jobnumber;
	}
	/**
	 * @param 工号 the jobnumber to set
	 */
	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}
	/**
	 * @return the 维修项目
	 */
	public String getMaintenanceitems() {
		return maintenanceitems;
	}
	/**
	 * @param 维修项目 the maintenanceitems to set
	 */
	public void setMaintenanceitems(String maintenanceitems) {
		this.maintenanceitems = maintenanceitems;
	}
	/**
	 * @return the 消息类型
	 */
	public String getMessagetype() {
		return messagetype;
	}
	/**
	 * @param 消息类型 the messagetype to set
	 */
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	/**
	 * @return the 维修费用
	 */
	public String getCost() {
		return cost;
	}
	/**
	 * @param 维修费用 the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}
	/**
	 * @return the 监管员Id
	 */
	public int getSupervisorid() {
		return supervisorid;
	}
	/**
	 * @param 监管员Id the supervisorid to set
	 */
	public void setSupervisorid(int supervisorid) {
		this.supervisorid = supervisorid;
	}
	/**
	 * @return the 1未读2已读
	 */
	public int getIsread() {
		return isread;
	}
	/**
	 * @param 1未读2已读 the isread to set
	 */
	public void setIsread(int isread) {
		this.isread = isread;
	}
	
	
}
