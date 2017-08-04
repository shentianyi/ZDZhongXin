package com.zd.csms.marketing.model;

import java.util.Date;

public class ModeChangeQueryVO {
	private int id;
	private String remark;//审批备注
	private int approvalState;//审批状态 1：是 2：否
	private Integer currRole;//角色
	private Integer pageType;//页面类型 1：待审批 2：已审批
	private String dealerName;
	private int currUserId;
	
	private String bankName;
	private Date createDateStart;
	private Date createDateEnd;

	public int getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCurrRole() {
		return currRole;
	}

	public void setCurrRole(Integer currRole) {
		this.currRole = currRole;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
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

	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}


	
}
