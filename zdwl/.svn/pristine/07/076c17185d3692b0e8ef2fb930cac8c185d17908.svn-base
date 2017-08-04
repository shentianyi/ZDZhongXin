package com.zd.csms.attendance.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="T_ATTENDANCE")
public class Attendance implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int year;
	private int month;
	private int state;//0:未审核 1：正在审核 2：已审核 3:
	private Date currentMonth;//当前日期的Date类型，用于台账查询
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(Date currentMonth) {
		this.currentMonth = currentMonth;
	}
	
}
