package com.zd.csms.supervisory.model;

import java.util.Date;

public class RepairCostQueryVO {

	private int id;
	private int promoter;//发起人id
	private String repair_project;//维修项目
	private String problem;//具体问题
	private String money;//维修费用
	private Date credatetime;//创建时间
	private int nextApproval;//下一个审批角色
	private int approvalState;//审批状态
	private Integer currRole;//当前角色
	private Integer pageType;//页面审批状态
	private String remark;//备注
	
/*	private String returnURL;
	
	
	
	public String getReturnURL() {
		return returnURL;
	}
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPromoter() {
		return promoter;
	}
	public void setPromoter(int promoter) {
		this.promoter = promoter;
	}
	public String getRepair_project() {
		return repair_project;
	}
	public void setRepair_project(String repairProject) {
		repair_project = repairProject;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Date getCredatetime() {
		return credatetime;
	}
	public void setCredatetime(Date credatetime) {
		this.credatetime = credatetime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
