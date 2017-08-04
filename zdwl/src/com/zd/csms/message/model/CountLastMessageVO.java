

package com.zd.csms.message.model;

import java.io.Serializable;
import com.zd.core.annotation.table;

@table(name="t_message")
public class CountLastMessageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 消息提醒
	 */
	
	private int id;
	private int userid;//用户id
	private String name;//消息名称
	private String url;//
	private int isread;//是否读取1.未读2.已读
	private int msgtype;//消息类型:1.信息2.预警
	private int type;//消息类别 分辨不同的消息类别 （各种流转单不同的消息，审批什么之类的）
	private String deptName;//部门名称
	
	private int num;
	
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public int getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(int msgtype) {
		this.msgtype = msgtype;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	public CountLastMessageVO() {
		super();
	}
	public CountLastMessageVO(int id, int userid, String name, String url,
			int isread, int msgtype, int type, String deptName, int num) {
		super();
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.url = url;
		this.isread = isread;
		this.msgtype = msgtype;
		this.type = type;
		this.deptName = deptName;
		this.num = num;
	}

	
	
}

