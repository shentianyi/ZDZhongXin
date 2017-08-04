package com.zd.csms.messageAndWaring.message.model;

import java.io.Serializable;
import com.zd.core.annotation.table;
/**
 * 用户接受的信息提醒
 * @author zhangzhicheng
 *
 */
@table(name = "t_msg_info_user")
public class MessageInfoUserVO implements Serializable {
	
	private static final long serialVersionUID = -7272713964159202447L;
	private int userId;// userId
	private int msgInfoId;// 已读消息id
	private int msgType;//消息类别
	private int read;// 是否读取1：未读 ，2：已读
	private Integer signId ;//信息标识 
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMsgInfoId() {
		return msgInfoId;
	}
	public void setMsgInfoId(int msgInfoId) {
		this.msgInfoId = msgInfoId;
	}
	
	
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public Integer getSignId() {
		return signId;
	}
	public void setSignId(Integer signId) {
		this.signId = signId;
	}
	
	
}
