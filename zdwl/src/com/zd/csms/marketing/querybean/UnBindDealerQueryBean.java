package com.zd.csms.marketing.querybean;

import java.util.Date;

public class UnBindDealerQueryBean {
	private int id;
	private int dealerId;
	private String dealerName;
	private String bankName;
	private String supervisorName;

	private int nextApproval;
	private int approvalState;
	
	private String createUserName;
	private Date createDate;
	private String lastModifyUserName;
	private Date lastModifyDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getNextApproval() {
		return nextApproval;
	}

	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}

	public int getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}

	public int getDealerId() {
		return dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

}
