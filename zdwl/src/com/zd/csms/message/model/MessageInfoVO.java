package com.zd.csms.message.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

@table(name="t_message_info")
public class MessageInfoVO implements Serializable {

	/**
	 * 消息提醒内容
	 */
	private static final long serialVersionUID = -2529738599974714287L;
	private int id;
	private int messageId;//消息ID
	private String content;//消息内容
	private int isread;//是否读取1.未读2.已读
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	
}
