package com.zd.csms.business.model;

import java.util.Date;

public class ComplaintQueryVO {
	private int id;
	private int dealerId;//经销商主键ID
	private String complainantName;//投诉人姓名
	private String complainantPosition;//投诉人职位
	private String complainantPhone;//投诉人电话
	private int resterId;//花名册主键Id
	private int complaintSorts;//投诉问题分类
	private String complaintContent;//投诉内容
	private int processingDepartment;//处理部门
	private String returnResult;//反馈结果
	private Date returnDate;//反馈时间
	private Date createDate;//创建时间
	private String treatmentOpinion;//处理意见
	private String bzOpinion;//部长意见
	
	private Integer approvalState;//审批状态 1：审批通过 2：审批不通过
	private String remark;//审批备注
	private int currRole;//当前角色
	private String dealerName;
	private Integer pageType;//页面类型：1：未审批 2：已审批
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getComplainantName() {
		return complainantName;
	}
	public void setComplainantName(String complainantName) {
		this.complainantName = complainantName;
	}
	public String getComplainantPosition() {
		return complainantPosition;
	}
	public void setComplainantPosition(String complainantPosition) {
		this.complainantPosition = complainantPosition;
	}
	public String getComplainantPhone() {
		return complainantPhone;
	}
	public void setComplainantPhone(String complainantPhone) {
		this.complainantPhone = complainantPhone;
	}
	public int getResterId() {
		return resterId;
	}
	public void setResterId(int resterId) {
		this.resterId = resterId;
	}
	public int getComplaintSorts() {
		return complaintSorts;
	}
	public void setComplaintSorts(int complaintSorts) {
		this.complaintSorts = complaintSorts;
	}
	public String getComplaintContent() {
		return complaintContent;
	}
	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}
	public int getProcessingDepartment() {
		return processingDepartment;
	}
	public void setProcessingDepartment(int processingDepartment) {
		this.processingDepartment = processingDepartment;
	}
	public String getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getTreatmentOpinion() {
		return treatmentOpinion;
	}
	public void setTreatmentOpinion(String treatmentOpinion) {
		this.treatmentOpinion = treatmentOpinion;
	}
	public String getBzOpinion() {
		return bzOpinion;
	}
	public void setBzOpinion(String bzOpinion) {
		this.bzOpinion = bzOpinion;
	}
	public Integer getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(Integer approvalState) {
		this.approvalState = approvalState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCurrRole() {
		return currRole;
	}
	public void setCurrRole(int currRole) {
		this.currRole = currRole;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Integer getPageType() {
		return pageType;
	}
	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}
	
}
