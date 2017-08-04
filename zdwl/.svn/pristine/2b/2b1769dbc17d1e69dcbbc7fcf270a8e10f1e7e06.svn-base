package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_car_operation")
public class CarOperationVO implements Serializable {

	/**
	 * 车辆操作表
	 */
	private static final long serialVersionUID = 174917650798104470L;
	private int id;
	private String cars;//车辆集合
	private int userid;//操作人
	private int type;//操作类型1导入2在库3出库4移动
	private Date createtime;//创建时间
	private int nextApproval;//下一个审批角色
	private int approvalState;//审批状态
	private int distribid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCars() {
		return cars;
	}
	public void setCars(String cars) {
		this.cars = cars;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getNextApproval() {
		return nextApproval;
	}
	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public int getDistribid() {
		return distribid;
	}
	public void setDistribid(int distribid) {
		this.distribid = distribid;
	}
	
	

}
