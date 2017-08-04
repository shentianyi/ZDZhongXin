package com.zd.csms.supervisorymanagement.model;



public class ExtraworkApplyQueryVO extends ExtraworkApplyVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前角色
	 */
	private int currentRole;
	/**
	 * 页面状态  
	 */
	private int pageType;
	/**
	 * 审批人意见
	 */
	private String remark;
	/**
	 * 审批状态
	 */
	private int approvalState;
	/**
	 * 当前登录用户的储备库ID
	 */
	private int currentRepositoryId;
	//查询条件
	/**
	 * 申请人
	 */
	private int extraworkPerson;
	/**
	 * 经销商ID
	 */
	private int dealerId;
	/**
	 * 品牌ID
	 */
	private int brandId;
	/**
	 * 金融机构ID
	 */
	private int bankId;
	/**
	 * 替岗人姓名
	 */
	private int replaceName;
	/**
	 * 替岗人工号
	 */
	private int replaceStaffNo;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public int getExtraworkPerson() {
		return extraworkPerson;
	}
	public void setExtraworkPerson(int extraworkPerson) {
		this.extraworkPerson = extraworkPerson;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getCurrentRepositoryId() {
		return currentRepositoryId;
	}
	public void setCurrentRepositoryId(int currentRepositoryId) {
		this.currentRepositoryId = currentRepositoryId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
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
	
}
