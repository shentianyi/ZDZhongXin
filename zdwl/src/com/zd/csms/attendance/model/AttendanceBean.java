package com.zd.csms.attendance.model;

import java.util.Date;

public class AttendanceBean extends AttendanceInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int attendanceId;
	private int year;
	private int month;
	private int attendanceState;//0:未审核 1：正在审核 2：已审核
	private Date currentMonth;//当前日期的Date类型，用于台账查询
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
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
	public int getAttendanceState() {
		return attendanceState;
	}
	public void setAttendanceState(int attendanceState) {
		this.attendanceState = attendanceState;
	}
	public Date getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(Date currentMonth) {
		this.currentMonth = currentMonth;
	}
	
}
