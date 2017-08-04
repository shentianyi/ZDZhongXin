package com.zd.csms.attendance.model;

import java.util.Date;

public class AttendanceQueryVO extends AttendanceInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int year;
	private int yearEnd;
	private int month;
	private int monthEnd;
	private Date startTime;
	private Date endTime;
	private int approvalState;//0:未审核 1：正在审核 2：已审核
	private int currentRole;//当期角色
	private int dealerId;
	private int bankId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public int getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(int currentRole) {
		this.currentRole = currentRole;
	}
	public int getYearEnd() {
		return yearEnd;
	}
	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}
	public int getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(int monthEnd) {
		this.monthEnd = monthEnd;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
}
