package com.zd.csms.messagequartz.model;

import java.io.Serializable;
import com.zd.core.annotation.table;

@table(name="t_msg_movestorage_user")
public class MsgMoveStorageUserVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1307187406574470178L;
	
	private int id;
	private Integer userId;//用户id
	private Integer type;//消息类型
	//private Integer isRead;//是否已读
	private Integer msgType;//消息分类(1:预警   2:提醒)
	private Integer msgInfoId;//已读消息id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMsgInfoId() {
		return msgInfoId;
	}
	public void setMsgInfoId(Integer msgInfoId) {
		this.msgInfoId = msgInfoId;
	}
	
}
