package com.zd.csms.supervisorymanagement.model;

import java.util.Date;

public class LeaveApplyQueryVO extends LeaveApplyVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 申请人
	 */
	private int leavePerson;
	/**
	 * 经销商ID
	 */
	private int dealerId;
	/**
	 * 品牌ID
	 */
	private int brandId;
	/**
	 * 银行ID
	 */
	private int bankId;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 当前角色
	 */
	private int currentRole;
	/**
	 * 页面类型
	 */
	private int pageType;
	/**
	 * 审批人意见
	 */
	private String remark;
	/**
	 * 当前登录用户储备库ID
	 */
	private int currentRepositoryId;
	/**
	 * 主键ID
	 */
	private int id;                 
	/**
	 * 员工号
	 */
	private String leavePersonStaffNo; 
	/**
	 * 经销商ID
	 */
	/*private String dealerId;*/
	/**
	 * 请假类型  1：事假，2：病假，3：倒休，4：正休
	 */
	private int leaveType;
	/**
	 * 请假原因
	 */
	private String leaveReason;
	/**
	 * 请假开始时间
	 */
	private Date leaveStartTime;
	/**
	 * 请假结束时间
	 */
	private Date leaveEndTime;
	/**
	 * 审批状态 1：审批通过，2：审批不通过，3：正在审批，0：未提交 
	 */
	private int approvalState;
	/**
	 * 替岗人姓名
	 */
	private int replaceName;
	/**
	 * 替岗人员工号
	 */
	private int replaceStaffNo;
	public int getLeavePerson() {
		return leavePerson;
	}
	public void setLeavePerson(int leavePerson) {
		this.leavePerson = leavePerson;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public int getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(int currentRole) {
		this.currentRole = currentRole;
	}
	public int getPageType() {
		return pageType;
	}
	public void setPageType(int pageType) {
		this.pageType = pageType;
	}
	public int getCurrentRepositoryId() {
		return currentRepositoryId;
	}
	public void setCurrentRepositoryId(int currentRepositoryId) {
		this.currentRepositoryId = currentRepositoryId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLeavePersonStaffNo() {
		return leavePersonStaffNo;
	}
	public void setLeavePersonStaffNo(String leavePersonStaffNo) {
		this.leavePersonStaffNo = leavePersonStaffNo;
	}
	public int getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(int leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public Date getLeaveStartTime() {
		return leaveStartTime;
	}
	public void setLeaveStartTime(Date leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}
	public Date getLeaveEndTime() {
		return leaveEndTime;
	}
	public void setLeaveEndTime(Date leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public int getReplaceName() {
		return replaceName;
	}
	public void setReplaceName(int replaceName) {
		this.replaceName = replaceName;
	}
	public int getReplaceStaffNo() {
		return replaceStaffNo;
	}
	public void setReplaceStaffNo(int replaceStaffNo) {
		this.replaceStaffNo = replaceStaffNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
