package com.zd.csms.supervisory.querybean;

import java.util.Date;

public class CheckStockQueryBean {
	private int id;
	private int createUserId;//创建人ID
	private Date createDate;//创建时间
	private String reason;//申请原因
	private int count;//查库频次
	private int nextApproval;//下一个审批角色
	private int approvalState;//审批状态
	
	private String dealerName;//经销商名称
	private String userName;//用户名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
