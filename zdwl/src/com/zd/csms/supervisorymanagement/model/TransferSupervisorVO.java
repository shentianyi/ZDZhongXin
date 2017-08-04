package com.zd.csms.supervisorymanagement.model;

import java.util.Date;
/**
 * 调动表监管员信息
 * @author macongcong
 *
 */
public class TransferSupervisorVO  {

	/**
	 * 姓名
	 */
	private String name;                      	
	/**
	 * 性别
	 */
	private String gender;       
	/**
	 * 联系电话
	 */
	private String contactNumber;
	/**
	 * 员工工号
	 */
	private String staffNo;
	/**
	 * 调入日期
	 */
	private Date entryDate;
	/**
	 * 调入性质
	 */
	private String entryNature;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 工服情况
	 */
	private String workCondition;
	
	/* 调离日期 */
	private Date leaveDate;
	
	/* 调离性质 */
	private String leaveNature;
	
	
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getLeaveNature() {
		return leaveNature;
	}
	public void setLeaveNature(String leaveNature) {
		this.leaveNature = leaveNature;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getEntryNature() {
		return entryNature;
	}
	public void setEntryNature(String entryNature) {
		this.entryNature = entryNature;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWorkCondition() {
		return workCondition;
	}
	public void setWorkCondition(String workCondition) {
		this.workCondition = workCondition;
	}
	
}
