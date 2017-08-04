package com.zd.csms.supervisory.model;

import com.zd.csms.rbac.model.UserVO;

public class CheckStockQueryVO {
	private int id;
	private Integer approvalState;//审批状态 1：审批通过 2：审批不通过
	private String remark;//审批备注
	private int currRole;//当前角色
	private String dealerName;
	private Integer pageType;//页面类型：1：未审批 2：已审批
	
	private UserVO user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
}
