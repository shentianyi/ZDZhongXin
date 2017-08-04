package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="t_supervisor_basic_information")
public class SupervisorBaseInfoVO implements Serializable {
	
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
	private int registeredStatus;           
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
	private Date entryTime; 	
	/**
	 * 签字
	 */
	private String sign;
	/**
	 * 日期
	 */
	private Date currentTime;
	/**
	 * 创建人
	 */
	private int createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private int lastModifyUser;
	/**
	 * 最后修改时间
	 */
	private Date lastModifyTime;
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
	public int getRegisteredStatus() {
		return registeredStatus;
	}
	public void setRegisteredStatus(int registeredStatus) {
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
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
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
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(int lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
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
