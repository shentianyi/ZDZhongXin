package com.zd.csms.attendance.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

/**
 * 考勤时间设置
 * 根据经销商设置
 */
@table(name="T_ATTENDANCE_TIME")
public class AttendanceTime implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//经销商Id
	private String morningStart;//早上签到
	private String morningEnd;//中午签退
	private String afternoonStart;//下午签到
	private String afternoonEnd;//下午签退
	private String systemContent;//制度说明
	
	private String workDays;//1234567代表周几多个','隔开
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMorningStart() {
		return morningStart;
	}
	public void setMorningStart(String morningStart) {
		this.morningStart = morningStart;
	}
	public String getMorningEnd() {
		return morningEnd;
	}
	public void setMorningEnd(String morningEnd) {
		this.morningEnd = morningEnd;
	}
	public String getAfternoonStart() {
		return afternoonStart;
	}
	public void setAfternoonStart(String afternoonStart) {
		this.afternoonStart = afternoonStart;
	}
	public String getAfternoonEnd() {
		return afternoonEnd;
	}
	public void setAfternoonEnd(String afternoonEnd) {
		this.afternoonEnd = afternoonEnd;
	}
	public String getSystemContent() {
		return systemContent;
	}
	public void setSystemContent(String systemContent) {
		this.systemContent = systemContent;
	}
	public String getWorkDays() {
		return workDays;
	}
	public void setWorkDays(String workDays) {
		this.workDays = workDays;
	}
}
