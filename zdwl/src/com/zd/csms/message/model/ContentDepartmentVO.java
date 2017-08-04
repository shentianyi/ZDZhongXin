package com.zd.csms.message.model;

import com.zd.core.annotation.table;

@table(name="T_CONTENT_DEPARTMENT")
public class ContentDepartmentVO {
	private int id;
	private int contentId;//内容Id
	private int clientTypeId;//部门Id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getClientTypeId() {
		return clientTypeId;
	}
	public void setClientTypeId(int clientTypeId) {
		this.clientTypeId = clientTypeId;
	}
	
	
}
