package com.zd.csms.attendance.quartz;

public class LeaveInfoBean {
	private double leaveCount;//请假天数
	private int leaveType;//请假类型
	public double getLeaveCount() {
		return leaveCount;
	}
	public void setLeaveCount(double leaveCount) {
		this.leaveCount = leaveCount;
	}
	public int getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(int leaveType) {
		this.leaveType = leaveType;
	}
	public LeaveInfoBean(double leaveCount, int leaveType) {
		super();
		this.leaveCount = leaveCount;
		this.leaveType = leaveType;
	}
	
	
}
