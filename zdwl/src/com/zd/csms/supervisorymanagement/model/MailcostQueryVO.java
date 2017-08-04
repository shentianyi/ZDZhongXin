package com.zd.csms.supervisorymanagement.model;

import java.util.Date;

public class MailcostQueryVO {
	
	private int id;
	private int promoter;//发起人id
	private Date fqdate;//发起时间
	private Integer[] mailingitems;//邮寄项目
	private String parts;//配件
	private String other;//其它
	private int mailperson;//邮寄人
	private String express;//快递公司
	private String money;//预计金额
	private String transportbegin;//运输城市起
	private String transportend;//运输城市止
	private int receiveid;//接收人
	private String des;//时间描述
	private int nextApproval;//下一个审批角色
	private int approvalState;//审批状态
	private Integer currRole;//当前角色
	private Integer pageType;//页面审批状态
	private String remark;//备注
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
	public Date getFqdate() {
		return fqdate;
	}
	public void setFqdate(Date fqdate) {
		this.fqdate = fqdate;
	}
	
	public Integer[] getMailingitems() {
		return mailingitems;
	}
	public void setMailingitems(Integer[] mailingitems) {
		this.mailingitems = mailingitems;
	}
	public String getParts() {
		return parts;
	}
	public void setParts(String parts) {
		this.parts = parts;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public int getMailperson() {
		return mailperson;
	}
	public void setMailperson(int mailperson) {
		this.mailperson = mailperson;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getTransportbegin() {
		return transportbegin;
	}
	public void setTransportbegin(String transportbegin) {
		this.transportbegin = transportbegin;
	}
	public String getTransportend() {
		return transportend;
	}
	public void setTransportend(String transportend) {
		this.transportend = transportend;
	}
	public int getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(int receiveid) {
		this.receiveid = receiveid;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
