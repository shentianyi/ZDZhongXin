package com.zd.csms.payment.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

@table(name="T_PAYMENT")
public class PaymentVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5936974109777833358L;
	private int id;
	private Integer year;
	private Integer month;
	private Integer state;//0:未提交 1：编辑中 2：经理审核 3：部长审核4：财务审核 5：总监审核 6:审核通过
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
