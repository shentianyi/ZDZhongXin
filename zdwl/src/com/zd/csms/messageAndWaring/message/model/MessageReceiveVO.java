package com.zd.csms.messageAndWaring.message.model;

import java.io.Serializable;
import com.zd.core.annotation.table;
/**
 * 消息接收人
 * @author zhangzhicheng
 *
 */
@table(name = "t_msg_receive")
public class MessageReceiveVO implements Serializable {
	private static final long serialVersionUID = 7057180856533092073L;
	private String depName;// 发出部门名称
	private int type;//提醒,预警
	private int msgType;// 消息类别
	private Integer roleId;// 角色ID;
	private Integer depId;// 部门;
	private int receiveType ;//1:角色 , 2:部门
	private String typeUrl;// 信息类别页面
	
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}


	public String getTypeUrl() {
		return typeUrl;
	}

	public void setTypeUrl(String typeUrl) {
		this.typeUrl = typeUrl;
	}



	public int getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(int receiveType) {
		this.receiveType = receiveType;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
}
