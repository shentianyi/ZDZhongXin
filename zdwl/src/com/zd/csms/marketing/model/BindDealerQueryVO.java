package com.zd.csms.marketing.model;

public class BindDealerQueryVO {
	
	private int approvalState;//审批状态1:同意 2：不同意
	private Integer currRole;//当期角色
	private String remark;//备注
	
	private Integer pageType;//页面类型 1：待审批 2：已审批
	
	private String dealerName;
	
	private int currUserId;
	private String bankName;

	public int getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	public Integer getCurrRole() {
		return currRole;
	}

	public void setCurrRole(Integer currRole) {
		this.currRole = currRole;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public int getCurrUserId() {
		return currUserId;
	}

	public void setCurrUserId(int currUserId) {
		this.currUserId = currUserId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
