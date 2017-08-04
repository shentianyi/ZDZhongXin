package com.zd.csms.marketing.querybean;

import java.util.Date;

public class ApprovalQueryBean {
	private int id;
	private String roleName;
	private String remark;
	private String userName;
	private Date remarkDate;
	private Integer approvalResult;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRemarkDate() {
		return remarkDate;
	}
	public void setRemarkDate(Date remarkDate) {
		this.remarkDate = remarkDate;
	}
	public Integer getApprovalResult() {
		return approvalResult;
	}
	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}
}
