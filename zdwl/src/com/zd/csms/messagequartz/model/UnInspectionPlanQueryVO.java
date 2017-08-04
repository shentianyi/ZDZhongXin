package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * 未按风控巡检计划执行提醒
 */
public class UnInspectionPlanQueryVO extends MsgQueryVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String num;//巡检编号
	
	private String director;//风控专员
	
	private Date plandate;//预计开始时间
	/**
	 * 经销商名字
	 */
	private String merchantname;
	/**
	 * 金融机构的名字
	 */
	private String bankname;
	
	
	private String brandname;//品牌
	
	/**
	 * 消息类型
	 */
	private int messagetype;
	
	/**
	 * 1未读 2已读
	 */
	private int isread;
	/**
	 * 消息表ID
	 */
	private int messageId;
	/**
	 * 插入时间
	 */
	private Date createDate;
	
	private Integer currRole;//当前角色
	
	private Date beginTime;
	
	private Date endTime;

	/**
	 * @return the beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the currRole
	 */
	public Integer getCurrRole() {
		return currRole;
	}
	/**
	 * @param currRole the currRole to set
	 */
	public void setCurrRole(Integer currRole) {
		this.currRole = currRole;
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
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * @return the plandate
	 */
	public Date getPlandate() {
		return plandate;
	}
	/**
	 * @param plandate the plandate to set
	 */
	public void setPlandate(Date plandate) {
		this.plandate = plandate;
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
	 * @return the brandname
	 */
	public String getBrandname() {
		return brandname;
	}
	/**
	 * @param brandname the brandname to set
	 */
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	/**
	 * @return the 消息类型
	 */
	public int getMessagetype() {
		return messagetype;
	}
	/**
	 * @param 消息类型 the messagetype to set
	 */
	public void setMessagetype(int messagetype) {
		this.messagetype = messagetype;
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

	/**
	 * @return the 消息表ID
	 */
	public int getMessageId() {
		return messageId;
	}
	/**
	 * @param 消息表ID the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the 插入时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param 插入时间 the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
