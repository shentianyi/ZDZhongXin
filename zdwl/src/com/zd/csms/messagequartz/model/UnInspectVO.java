package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

/**
 * @author
 * 巡店报告上传3日未上传新报告提醒
 */
@table(name="T_SUPERVISOR_UNINSPECT")
public class UnInspectVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String director;//风控专员
	
	private String content;//内容

	private Date lastModified;//巡检报告上传修改时间
	
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
	 * 用户Id
	 */
	private int userId;
	/**
	 * 消息表ID
	 */
	private int messageId;
	/**
	 * 插入时间
	 */
	private Date createDate;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
