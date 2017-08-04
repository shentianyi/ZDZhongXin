package com.zd.csms.attendance.quartz;

public class TranInfoBean {
	private int year;
	private int month;
	private int start;
	private int end;
	private int dealer;
	private String dealerName;
	private int supervisorSource;//监管员来源
	
	private double actualAttendance;//实际出勤
	private double shouldAttendance;//应该出勤
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getDealer() {
		return dealer;
	}
	public void setDealer(int dealer) {
		this.dealer = dealer;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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
	public double getActualAttendance() {
		return actualAttendance;
	}
	public void setActualAttendance(double actualAttendance) {
		this.actualAttendance = actualAttendance;
	}
	public double getShouldAttendance() {
		return shouldAttendance;
	}
	public void setShouldAttendance(double shouldAttendance) {
		this.shouldAttendance = shouldAttendance;
	}
	public int getSupervisorSource() {
		return supervisorSource;
	}
	public void setSupervisorSource(int supervisorSource) {
		this.supervisorSource = supervisorSource;
	}

}
