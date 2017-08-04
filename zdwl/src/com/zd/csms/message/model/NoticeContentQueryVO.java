package com.zd.csms.message.model;

import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_notice_content")
public class NoticeContentQueryVO {
	private int id;
	private String title;
	private String content;
	private int createUserId;
	private Date createDate;
	
	private int currRole;
	private int approvalState;
	private int pageType;
	private int currUserId;
	
	private int contentType;//1:公告 2：制度
	private String remark;//备注
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getCurrRole() {
		return currRole;
	}
	public void setCurrRole(int currRole) {
		this.currRole = currRole;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public int getPageType() {
		return pageType;
	}
	public void setPageType(int pageType) {
		this.pageType = pageType;
	}
	public int getCurrUserId() {
		return currUserId;
	}
	public void setCurrUserId(int currUserId) {
		this.currUserId = currUserId;
	}
	public int getContentType() {
		return contentType;
	}
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
