package com.zd.csms.supervisory.model;

import java.util.Date;

public class DuedateQueryVO {

	private int id;
	private int superviseid;//监管员id
	private String dealerName;
	private String bankName;
	private String brandName;
	private int type;//日查库类型
	private int picid;//图片id
	private Date createtime;//上传时间
	private Integer currRole;//当前角色
	private Integer pageType;//页面审批状态
	private int approvalState;//审批状态 1：是 2：否
	private String remark;//备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSuperviseid() {
		return superviseid;
	}
	public void setSuperviseid(int superviseid) {
		this.superviseid = superviseid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPicid() {
		return picid;
	}
	public void setPicid(int picid) {
		this.picid = picid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
