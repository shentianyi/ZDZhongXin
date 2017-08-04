package com.zd.csms.attendance.model;

import java.io.Serializable;
import java.util.Date;

public class SignRecordCheckSpAll implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date morningStart;// 早上签到
	private Date morningEnd;// 中午签退
	private Date afternoonStart;// 下午签到
	private Date afternoonEnd;// 下午签退
	private Date createDate;
	private int createUserId;
	private Date updateDate;
	private int updateUserId;
	private int respId;// 对应的储备库Id
	private int isLate;// 是否迟到 0：否 1：是
	private int isEarly;// 是否早退 0：否 1：是
	private int isAbsenteeism;// 是否旷工0：否 1：是
	private int isNormal;// 是否正常出勤0：否 1：是
	private String workDays;// 1234567代表周几多个','隔开
	private String oldAttendance;// 记录历史出勤情况
	
	private int dealerId;
	private String dealersName;
	private String name;
	private String staffNo;
	private String banksName;
	private String province;
	private String city;
	private String provinceName;
	private String cityName;
	private int state;//审核状态 0:未审核状态 1:审核通过 2：审核不通过 3:正在审批
	private int nextApproval;
	private int currentRole;//当期角色
	private int year;
	private int month;
	private int time;
	
	private Date approveDate; //审批日期
	private String approveOpinion; // 审批意见
	
	private SignRecordCheckSpDays days = new SignRecordCheckSpDays();
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getNextApproval() {
		return nextApproval;
	}

	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getMorningStart() {
		return morningStart;
	}

	public void setMorningStart(Date morningStart) {
		this.morningStart = morningStart;
	}

	public Date getMorningEnd() {
		return morningEnd;
	}

	public void setMorningEnd(Date morningEnd) {
		this.morningEnd = morningEnd;
	}

	public Date getAfternoonStart() {
		return afternoonStart;
	}

	public void setAfternoonStart(Date afternoonStart) {
		this.afternoonStart = afternoonStart;
	}

	public Date getAfternoonEnd() {
		return afternoonEnd;
	}

	public void setAfternoonEnd(Date afternoonEnd) {
		this.afternoonEnd = afternoonEnd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public int getRespId() {
		return respId;
	}

	public void setRespId(int respId) {
		this.respId = respId;
	}

	public int getIsLate() {
		return isLate;
	}

	public void setIsLate(int isLate) {
		this.isLate = isLate;
	}

	public int getIsEarly() {
		return isEarly;
	}

	public void setIsEarly(int isEarly) {
		this.isEarly = isEarly;
	}

	public int getIsAbsenteeism() {
		return isAbsenteeism;
	}

	public void setIsAbsenteeism(int isAbsenteeism) {
		this.isAbsenteeism = isAbsenteeism;
	}

	public int getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(int isNormal) {
		this.isNormal = isNormal;
	}

	public String getWorkDays() {
		return workDays;
	}

	public void setWorkDays(String workDays) {
		this.workDays = workDays;
	}

	public String getOldAttendance() {
		return oldAttendance;
	}

	public void setOldAttendance(String oldAttendance) {
		this.oldAttendance = oldAttendance;
	}

	public int getDealerId() {
		return dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealersName() {
		return dealersName;
	}

	public void setDealersName(String dealersName) {
		this.dealersName = dealersName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getBanksName() {
		return banksName;
	}

	public void setBanksName(String banksName) {
		this.banksName = banksName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(int currentRole) {
		this.currentRole = currentRole;
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproveOpinion() {
		return approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	public SignRecordCheckSpDays getDays() {
		return days;
	}

	public void setDays(SignRecordCheckSpDays days) {
		this.days = days;
	}
	
	

}
