package com.zd.csms.supervisory.model;

import java.io.Serializable;
import java.util.Date;

public class ExtRepairCostVO implements Serializable{
	private static final long serialVersionUID = -1484965636212845084L;
	private int id;
	private int promoter;//发起人id
	private String repair_project;//维修项目
	private String problem;//具体问题
	private String money;//维修费用
	private Date credatetime;//创建时间
	private int nextApproval;//
	private int approvalState;//
	private int createuserid;//创建人
	private Date createdate;//创建时间
	private int upduserid;//修改人
	private Date upddate;//修改时间
	private String declareUser; //申报人
	private String createUser;
	private String updateUser;
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
	public void setRepair_project(String repair_project) {
		this.repair_project = repair_project;
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
	public int getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getUpduserid() {
		return upduserid;
	}
	public void setUpduserid(int upduserid) {
		this.upduserid = upduserid;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	public String getDeclareUser() {
		return declareUser;
	}
	public void setDeclareUser(String declareUser) {
		this.declareUser = declareUser;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
}
