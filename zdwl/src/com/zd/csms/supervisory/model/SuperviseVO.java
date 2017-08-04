package com.zd.csms.supervisory.model;

import java.util.Date;

public class SuperviseVO {

	private int id;//
	private String job;		// 应聘岗位
	private Date entryTime; //	入职时间
	private String name;//姓名
	private String gender;//性别
	private Date birthday;//出生日期
	private String idNumber;//身份证号
	private String nation;//民族
	private String educationBackground;//学历
	private String nativePlace;//籍贯
	private String politicsStatus;//政治面貌
	private int registeredStatus;//户口性质
	private String selfTelephone;//本人联系电话
	private String homeTelephone;//家庭电话
	private String fertilityState;//婚姻状况
	private String registeredAddress;//户口所在地
	private String liveAddress;//现居住详细地址	
	private String nativePlaceProvince;//籍贯（省）
	private String nativePlaceCity; // 籍贯（市）
	private String nativePlaceCounty;  //籍贯（区/县）
	private String liveAddressProvince; //现住址（省）
	private String liveAddressCity; //现住址（市）
	private String liveAddressCounty; //现住址（区/县）
	private String emergencyContactor;//紧急状况联系人
	private String emergencyContactNumber;//紧急状况联系电话
	private String emergencyContactRelationship;//与紧急状况联系人关系
	private Date educationStartTime;//教育时间(起)
	private Date educationEndTime;//教育时间(止)
	private String schoolName;//学校名称
	private String major;//主修专业
	private String certificate;//获得证书
	private String degree;//学位
	private Date workStartTime;//工作经历起
	private Date workEndTime;//工作经历止
	private String serviceOrganization;//服务机构
	private String position;//职位
	private String compensation;//薪资
	private String leaveReason;//离职原因
	private String certifier;//证明人
	private String contactNumber;//联系方式
	private String fname;//父亲姓名
	private String fprofession;//父亲职业
	private String forganization;//父亲单位
	private String ftelephone;//父亲电话
	private String frelationship;//关系父亲
	private String mname;//母亲姓名
	private String mprofession;//母亲职业
	private String morganization;//母亲单位
	private String mtelephone;//母亲电话
	private String mrelationship;//关系母亲
	private String xname;//兄弟姓名
	private String xprofession;//兄弟职业
	private String xorganization;//兄弟单位
	private String xtelephone;//兄弟电话
	private String xrelationship;//关系兄弟
	private String zname;//子女姓名
	private String zprofession;//子女职业
	private String zorganization;//子女单位
	private String ztelephone;//子女电话
	private String zrelationship;//关系子女
	private String isInsideRecommend;//是否内部人员推荐
	private int otherChannel;//其它渠道
	private String recommenderName;//推荐人姓名
	private String recommenderPosition;//推荐人职位
	private String recommenderPhone;//推荐人联系方式
	private String recommenderRelationship;//与推荐人关系
	private String recommenderDepartment;//推荐人所在部门或4S店名称
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
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
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
	public Date getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(Date workStartTime) {
		this.workStartTime = workStartTime;
	}
	public Date getWorkEndTime() {
		return workEndTime;
	}
	public void setWorkEndTime(Date workEndTime) {
		this.workEndTime = workEndTime;
	}
	public String getServiceOrganization() {
		return serviceOrganization;
	}
	public void setServiceOrganization(String serviceOrganization) {
		this.serviceOrganization = serviceOrganization;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCompensation() {
		return compensation;
	}
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getCertifier() {
		return certifier;
	}
	public void setCertifier(String certifier) {
		this.certifier = certifier;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFprofession() {
		return fprofession;
	}
	public void setFprofession(String fprofession) {
		this.fprofession = fprofession;
	}
	public String getForganization() {
		return forganization;
	}
	public void setForganization(String forganization) {
		this.forganization = forganization;
	}
	public String getFtelephone() {
		return ftelephone;
	}
	public void setFtelephone(String ftelephone) {
		this.ftelephone = ftelephone;
	}
	public String getFrelationship() {
		return frelationship;
	}
	public void setFrelationship(String frelationship) {
		this.frelationship = frelationship;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMprofession() {
		return mprofession;
	}
	public void setMprofession(String mprofession) {
		this.mprofession = mprofession;
	}
	public String getMorganization() {
		return morganization;
	}
	public void setMorganization(String morganization) {
		this.morganization = morganization;
	}
	public String getMtelephone() {
		return mtelephone;
	}
	public void setMtelephone(String mtelephone) {
		this.mtelephone = mtelephone;
	}
	public String getMrelationship() {
		return mrelationship;
	}
	public void setMrelationship(String mrelationship) {
		this.mrelationship = mrelationship;
	}
	public String getXname() {
		return xname;
	}
	public void setXname(String xname) {
		this.xname = xname;
	}
	public String getXprofession() {
		return xprofession;
	}
	public void setXprofession(String xprofession) {
		this.xprofession = xprofession;
	}
	public String getXorganization() {
		return xorganization;
	}
	public void setXorganization(String xorganization) {
		this.xorganization = xorganization;
	}
	public String getXtelephone() {
		return xtelephone;
	}
	public void setXtelephone(String xtelephone) {
		this.xtelephone = xtelephone;
	}
	public String getXrelationship() {
		return xrelationship;
	}
	public void setXrelationship(String xrelationship) {
		this.xrelationship = xrelationship;
	}
	public String getZname() {
		return zname;
	}
	public void setZname(String zname) {
		this.zname = zname;
	}
	public String getZprofession() {
		return zprofession;
	}
	public void setZprofession(String zprofession) {
		this.zprofession = zprofession;
	}
	public String getZorganization() {
		return zorganization;
	}
	public void setZorganization(String zorganization) {
		this.zorganization = zorganization;
	}
	public String getZtelephone() {
		return ztelephone;
	}
	public void setZtelephone(String ztelephone) {
		this.ztelephone = ztelephone;
	}
	public String getZrelationship() {
		return zrelationship;
	}
	public void setZrelationship(String zrelationship) {
		this.zrelationship = zrelationship;
	}
	public String getIsInsideRecommend() {
		return isInsideRecommend;
	}
	public void setIsInsideRecommend(String isInsideRecommend) {
		this.isInsideRecommend = isInsideRecommend;
	}
	public int getOtherChannel() {
		return otherChannel;
	}
	public void setOtherChannel(int otherChannel) {
		this.otherChannel = otherChannel;
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
	public String getRecommenderPhone() {
		return recommenderPhone;
	}
	public void setRecommenderPhone(String recommenderPhone) {
		this.recommenderPhone = recommenderPhone;
	}
	public String getRecommenderRelationship() {
		return recommenderRelationship;
	}
	public void setRecommenderRelationship(String recommenderRelationship) {
		this.recommenderRelationship = recommenderRelationship;
	}
	public String getRecommenderDepartment() {
		return recommenderDepartment;
	}
	public void setRecommenderDepartment(String recommenderDepartment) {
		this.recommenderDepartment = recommenderDepartment;
	}
	
	
}
