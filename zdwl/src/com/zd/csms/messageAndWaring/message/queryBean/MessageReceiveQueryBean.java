package com.zd.csms.messageAndWaring.message.queryBean;

import com.zd.csms.messageAndWaring.message.model.MessageReceiveVO;
//消息接收用户
public class MessageReceiveQueryBean extends MessageReceiveVO{
	private static final long serialVersionUID = -5505303075079141414L;
	private int userId ;
	private String signIdStr ;//屏蔽的信息标识
	private int num ;//接收消息数量;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSignIdStr() {
		return signIdStr;
	}
	public void setSignIdStr(String signIdStr) {
		this.signIdStr = signIdStr;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
