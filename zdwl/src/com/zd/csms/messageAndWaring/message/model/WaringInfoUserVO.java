package com.zd.csms.messageAndWaring.message.model;

import java.io.Serializable;
import com.zd.core.annotation.table;
/**
 * 用户接受的信息预警
 * @author zhangzhicheng
 *
 */
@table(name = "t_waring_info_user")
public class WaringInfoUserVO implements Serializable {
	
	private static final long serialVersionUID = -7272713964159202447L;
	private int userId;// 用户id 
	private int msgInfoId;// 已读消息id 
	private int msgType;//消息类别 
	private int read;// 是否读取1：未读 ，2：已读 
	private int shield ; //是否屏蔽  1："未屏蔽" 2：已屏蔽
	private Integer signId ;//屏蔽的信息标识
	
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
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public Integer getSignId() {
		return signId;
	}
	public void setSignId(Integer signId) {
		this.signId = signId;
	}
	
	
	
}
