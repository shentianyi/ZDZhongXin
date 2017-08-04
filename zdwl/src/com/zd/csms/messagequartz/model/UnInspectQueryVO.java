package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * 未按风控巡检计划执行提醒
 */
public class UnInspectQueryVO extends MsgQueryVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String director;//风控专员
	
    private Date lastModified;//巡检报告上传修改时间
    private Date endLastModified;//巡检报告上传修改时间
	
	private String address; //上次巡店报告上传地址
	
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
	
	private int provinceId;//省id
	private int cityId;//市
	private int areaId;//区

	/**
	 * @return the provinceId
	 */
	public int getProvinceId() {
		return provinceId;
	}

	/**
	 * @param provinceId the provinceId to set
	 */
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the areaId
	 */
	public int getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the endLastModified
	 */
	public Date getEndLastModified() {
		return endLastModified;
	}

	/**
	 * @param endLastModified the endLastModified to set
	 */
	public void setEndLastModified(Date endLastModified) {
		this.endLastModified = endLastModified;
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
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	
	
}
