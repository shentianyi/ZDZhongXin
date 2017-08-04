package com.zd.csms.marketing.querybean;

public class SupervisorJsonBean {
	private int id;//主键ID
	private String idNumber;//身份证号
	private String name;//姓名
	private String selfTelephone;//本人联系电话
	private String gender;//性别
	private String liveAddress;//现居住详细地址
	private String  educationBackground;//学历
	private String interviewee;//面试人
	private String recommenderName;//推介人
	private String staffNo;//工号

	private String qq;//qq
	private String qqPwd;//qq密码
	private int supervisorAttribute;//监管员属性
	private int supervisorSource;//监管员来源
	
	//监管员绑定的经销商
	private String dealerName;
	//监管员绑定的经销商绑定的银行
	private String bankName;
	//监管员绑定的经销商绑定品牌
	private String brandName;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelfTelephone() {
		return selfTelephone;
	}

	public void setSelfTelephone(String selfTelephone) {
		this.selfTelephone = selfTelephone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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


	public String getInterviewee() {
		return interviewee;
	}

	public void setInterviewee(String interviewee) {
		this.interviewee = interviewee;
	}

	public String getRecommenderName() {
		return recommenderName;
	}

	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getQqPwd() {
		return qqPwd;
	}

	public void setQqPwd(String qqPwd) {
		this.qqPwd = qqPwd;
	}

	public int getSupervisorAttribute() {
		return supervisorAttribute;
	}

	public void setSupervisorAttribute(int supervisorAttribute) {
		this.supervisorAttribute = supervisorAttribute;
	}

	public int getSupervisorSource() {
		return supervisorSource;
	}

	public void setSupervisorSource(int supervisorSource) {
		this.supervisorSource = supervisorSource;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
}
