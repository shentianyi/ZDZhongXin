package com.zd.csms.supervisory.model;

import java.util.Date;
public class SupervisorQueryVO {
	/**
	 * 姓名
	 */
	private String name; 
	/**
	 * 性别
	 */
	private String gender;     
	/**
	 * 出生日期
	 */
	private Date birthday ;    
	/**
	 * 身份证号
	 */
	private String  idNumber  ;                
	/**
	 * 民族
	 */
	private String nation   ;                 
	/**
	 * 学历
	 */
	private String  educationBackground  ;    
	/**
	 * 籍贯
	 */
	private String  nativePlace     ;         
	/**
	 * 政治面貌
	 */
	private String   politicsStatus ;          
	/**
	 * 户口性质
	 */
	private String registeredStatus;           
	/**
	 * 本人联系电话
	 */
	private String selfTelephone  ;            
	/**
	 * 家庭电话
	 */
	private String homeTelephone  ;            
	/**
	 * 婚姻状况
	 */
	private String fertilityState ;            
	/**
	 * 户口所在地
	 */
	private String registeredAddress  ;        
	/**
	 * 现居住详细地址
	 */
	private String liveAddress   ;             
	/**
	 * 应聘岗位
	 */
	private String job;		
	/**
	 * 入职时间
	 */
	private Date entryTime; 	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public String getRegisteredStatus() {
		return registeredStatus;
	}
	public void setRegisteredStatus(String registeredStatus) {
		this.registeredStatus = registeredStatus;
	}
	public String getSelfTelephone() {
		return selfTelephone;
	}
	public void setSelfTelephone(String selfTelephone) {
		this.selfTelephone = selfTelephone;
	}
	public String getHomeTelephone() {
		return homeTelephone;
	}
	public void setHomeTelephone(String homeTelephone) {
		this.homeTelephone = homeTelephone;
	}
	public String getFertilityState() {
		return fertilityState;
	}
	public void setFertilityState(String fertilityState) {
		this.fertilityState = fertilityState;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	public String getEducationBackground() {
		return educationBackground;
	}
	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	
}
