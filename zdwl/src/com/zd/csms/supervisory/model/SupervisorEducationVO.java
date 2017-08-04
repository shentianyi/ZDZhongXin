package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="T_SUPERVISOR_EDUCATION")
public class SupervisorEducationVO implements Serializable {
	
	private static final long serialVersionUID = 6348857295726046280L;
	/**
	 *主键ID
	 */
	private int id ;     
	/**
	 * 起(教育时间)
	 */
	private Date educationStartTime; 
	/**
	 * 止(教育时间)
	 */
	private Date educationEndTime;   
	/**
	 * 学校名称
	 */
    private String schoolName;       
    /**
	 * 主修专业
	 */
	private String major;            
	/**
	 * 获得证书
	 */
	private String certificate;      
	/**
	 * 学位
	 */
	private String degree;           
	/**
	 * 监管员ID
	 */
	private int supervisorId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public Date getEducationStartTime() {
		return educationStartTime;
	}
	public void setEducationStartTime(Date educationStartTime) {
		this.educationStartTime = educationStartTime;
	}
	public Date getEducationEndTime() {
		return educationEndTime;
	}
	public void setEducationEndTime(Date educationEndTime) {
		this.educationEndTime = educationEndTime;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

}
