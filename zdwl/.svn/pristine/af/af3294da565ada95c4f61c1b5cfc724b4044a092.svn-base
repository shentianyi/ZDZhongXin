package com.zd.csms.message.model;

import com.zd.core.annotation.table;

@table(name="t_notice")
public class NoticeVO {
	private int id;
	private String title;//标题
	private int objectId;//目标Id
	private int type;//类型  1:手动填写公告 2：系统自动生成 3:制度
	private int userId;//用户id 公告都有用户id  制度文件没有userid  userid都是0
	private String url;//地址
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	
	
}
