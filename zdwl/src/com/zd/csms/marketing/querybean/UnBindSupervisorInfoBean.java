package com.zd.csms.marketing.querybean;

public class UnBindSupervisorInfoBean {
	private int repositoryId;// 储备库主键Id
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证号
	 */
	private String idNumber;

	private String staffNo;// 工号

	/**
	 * 本人联系电话
	 */
	private String selfTelephone;
	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 学历
	 */
	private String educationBackground;
	
	private String qq;//qq
	
	private String qqPwd;//qq密码
	
	private int supervisorAttribute;//监管员属性
	
	private int supervisorSource;//监管员来源

	public int getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
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

	public String getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
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

	
}
