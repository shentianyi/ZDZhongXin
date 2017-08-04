package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="t_supervisor_basic_information")
public class SupervisorBaseInfoJsonVO implements Serializable {
	
	private static final long serialVersionUID = 6348857295726046280L;
	/**
	 *主键ID
	 */
	private int id ;     
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
	private String birthdayStr ;    
	/**
	 * 年龄
	 */
	private int age;
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
	 * 毕业院校
	 */
	private String graduateSchool;
	/**
	 * 专业
	 */
	private String major;
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
	private String registeredStatusStr;           
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
	 * 籍贯（省）
	 */
	private String nativePlaceProvince;
	/**
	 * 籍贯（市）
	 */
	private String nativePlaceCity;    
	/**
	 * 籍贯（区/县）
	 */
	private String nativePlaceCounty;  
	/**
	 * 现住址（省）
	 */
	private String liveAddressProvince;
	/**
	 * 现住址（市）
	 */
	private String liveAddressCity;    
	/**
	 * 现住址（区/县）
	 */
	private String liveAddressCounty;
	/**
	 * 紧急状况联系人
	 */
	private String emergencyContactor   ;      
	/**
	 * 紧急状况联系电话
	 */
	private String emergencyContactNumber ;      
	/**
	 * 与紧急状况联系人关系
	 */
	private String emergencyContactRelationship;
	/**
	 * 图片ID
	 */
	private int pictureId;   
	/**
	 * 应聘岗位
	 */
	private String job;		
	/**
	 * 入职时间
	 */
	private String entryTimeStr; 	
	/**
	 * 签字
	 */
	private String sign;
	/**
	 * 日期
	 */
	private Date currentTime;
	/**
	 * 招聘渠道
	 */
	private String recommendChannel;
	/**
	 * 推荐人姓名
	 */
	private String recommenderName;         
	/**
	 * 推荐人职位
	 */
	private String recommenderPosition;  
	/**
	 * 面试人
	 */
	private String interviewee;
	/**
	 * 员工工号
	 */
	private String staffNo;
	/**
	 * 推荐人联系方式
	 */
	private String recommenderPhone;   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
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
	public String getEducationBackground() {
		return educationBackground;
	}
	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
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
	public String getRegisteredStatusStr() {
		return registeredStatusStr;
	}
	public void setRegisteredStatusStr(String registeredStatusStr) {
		this.registeredStatusStr = registeredStatusStr;
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
	public String getEmergencyContactor() {
		return emergencyContactor;
	}
	public void setEmergencyContactor(String emergencyContactor) {
		this.emergencyContactor = emergencyContactor;
	}
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}
	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}
	public String getEmergencyContactRelationship() {
		return emergencyContactRelationship;
	}
	public void setEmergencyContactRelationship(String emergencyContactRelationship) {
		this.emergencyContactRelationship = emergencyContactRelationship;
	}
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEntryTimeStr() {
		return entryTimeStr;
	}
	public void setEntryTimeStr(String entryTimeStr) {
		this.entryTimeStr = entryTimeStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getRecommendChannel() {
		return recommendChannel;
	}
	public void setRecommendChannel(String recommendChannel) {
		this.recommendChannel = recommendChannel;
	}
	public String getRecommenderName() {
		return recommenderName;
	}
	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}
	public String getRecommenderPosition() {
		return recommenderPosition;
	}
	public void setRecommenderPosition(String recommenderPosition) {
		this.recommenderPosition = recommenderPosition;
	}
	public String getInterviewee() {
		return interviewee;
	}
	public void setInterviewee(String interviewee) {
		this.interviewee = interviewee;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getRecommenderPhone() {
		return recommenderPhone;
	}
	public void setRecommenderPhone(String recommenderPhone) {
		this.recommenderPhone = recommenderPhone;
	}
	public String getNativePlaceProvince() {
		return nativePlaceProvince;
	}
	public void setNativePlaceProvince(String nativePlaceProvince) {
		this.nativePlaceProvince = nativePlaceProvince;
	}
	public String getNativePlaceCity() {
		return nativePlaceCity;
	}
	public void setNativePlaceCity(String nativePlaceCity) {
		this.nativePlaceCity = nativePlaceCity;
	}
	public String getNativePlaceCounty() {
		return nativePlaceCounty;
	}
	public void setNativePlaceCounty(String nativePlaceCounty) {
		this.nativePlaceCounty = nativePlaceCounty;
	}
	public String getLiveAddressProvince() {
		return liveAddressProvince;
	}
	public void setLiveAddressProvince(String liveAddressProvince) {
		this.liveAddressProvince = liveAddressProvince;
	}
	public String getLiveAddressCity() {
		return liveAddressCity;
	}
	public void setLiveAddressCity(String liveAddressCity) {
		this.liveAddressCity = liveAddressCity;
	}
	public String getLiveAddressCounty() {
		return liveAddressCounty;
	}
	public void setLiveAddressCounty(String liveAddressCounty) {
		this.liveAddressCounty = liveAddressCounty;
	}
	
}
