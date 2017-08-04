package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_bank_approve")
public class BankApproveVO implements Serializable {

	/**
	 * 银行审批表
	 */
	private static final long serialVersionUID = -2902063690019423673L;
	private int id;
	private int sid;//车id
	private int state;//1.待审批2.审核通过3.审核不通过
	private Date createtime;//创建时间
	private Date approvetime;//审批时间
	private int type;//车辆状态:1导入2在库3出库4移动
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	

}
