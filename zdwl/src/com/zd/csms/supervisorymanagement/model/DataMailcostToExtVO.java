package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

public class DataMailcostToExtVO implements Serializable{
	/**
	 * 资料邮寄费用台账导出
	 */
	private static final long serialVersionUID = 3189925283074005962L;
	private int id;
	private int promoter;//发起人id
	private Date fqDate;//发起时间
	private String mailItems;//邮寄项目
	private String parts;//配件
	private String other;//其它
	private int mailPerson;//邮寄人
	private String mailPersonStaffNo;//邮寄人员工工号
	private int mailPersonDealer;//邮寄人经销商
	private String express;//快递公司
	private String money;//预计金额
	private String transportBegin;//运输城市起
	private String transportEnd;//运输城市止
	private int receiverType;//接收人类型  
	private String receiveid;//接收人
	
	private int businessOfficer;//业务部专员
	private int dealerId;//经销商ID
	private int supervisoryId;//监管员ID
	private int headBank;//接受类型为金融机构时，总行
	private int branch;//接受类型为金融机构时，分行
	private int subbranch;//接受类型为金融机构时，支行
	
	private String des;//事件描述
	private int status;//状态  0：可编辑，1：不可编辑
	private int nextApproval;//下一个审批角色
	private int approvalState;//审批状态
	private int createuserid;//创建人
	private Date createdate;//创建时间
	private int upduserid;//修改人
	private Date upddate;//修改时间
	
	private String mailPersonNTT; //邮寄人名称
	private String mailPersonDealerNTT; // 邮寄人经销商名称
	private String receiverTypeNTT;//接收人
	private String createuserName;//创建人名称
	private String upduserName;//修改人名称
	private String nextApprovalName; //下一个审批角色
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
	public Date getFqDate() {
		return fqDate;
	}
	public void setFqDate(Date fqDate) {
		this.fqDate = fqDate;
	}
	public String getMailItems() {
		return mailItems;
	}
	public void setMailItems(String mailItems) {
		this.mailItems = mailItems;
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
	public int getMailPerson() {
		return mailPerson;
	}
	public void setMailPerson(int mailPerson) {
		this.mailPerson = mailPerson;
	}
	public String getMailPersonStaffNo() {
		return mailPersonStaffNo;
	}
	public void setMailPersonStaffNo(String mailPersonStaffNo) {
		this.mailPersonStaffNo = mailPersonStaffNo;
	}
	public int getMailPersonDealer() {
		return mailPersonDealer;
	}
	public void setMailPersonDealer(int mailPersonDealer) {
		this.mailPersonDealer = mailPersonDealer;
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
	public String getTransportBegin() {
		return transportBegin;
	}
	public void setTransportBegin(String transportBegin) {
		this.transportBegin = transportBegin;
	}
	public String getTransportEnd() {
		return transportEnd;
	}
	public void setTransportEnd(String transportEnd) {
		this.transportEnd = transportEnd;
	}
	public int getReceiverType() {
		return receiverType;
	}
	public void setReceiverType(int receiverType) {
		this.receiverType = receiverType;
	}
	public String getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}
	public int getBusinessOfficer() {
		return businessOfficer;
	}
	public void setBusinessOfficer(int businessOfficer) {
		this.businessOfficer = businessOfficer;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getSupervisoryId() {
		return supervisoryId;
	}
	public void setSupervisoryId(int supervisoryId) {
		this.supervisoryId = supervisoryId;
	}
	public int getHeadBank() {
		return headBank;
	}
	public void setHeadBank(int headBank) {
		this.headBank = headBank;
	}
	public int getBranch() {
		return branch;
	}
	public void setBranch(int branch) {
		this.branch = branch;
	}
	public int getSubbranch() {
		return subbranch;
	}
	public void setSubbranch(int subbranch) {
		this.subbranch = subbranch;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getMailPersonNTT() {
		return mailPersonNTT;
	}
	public void setMailPersonNTT(String mailPersonNTT) {
		this.mailPersonNTT = mailPersonNTT;
	}
	public String getMailPersonDealerNTT() {
		return mailPersonDealerNTT;
	}
	public void setMailPersonDealerNTT(String mailPersonDealerNTT) {
		this.mailPersonDealerNTT = mailPersonDealerNTT;
	}
	public String getReceiverTypeNTT() {
		return receiverTypeNTT;
	}
	public void setReceiverTypeNTT(String receiverTypeNTT) {
		this.receiverTypeNTT = receiverTypeNTT;
	}
	public String getCreateuserName() {
		return createuserName;
	}
	public void setCreateuserName(String createuserName) {
		this.createuserName = createuserName;
	}
	public String getUpduserName() {
		return upduserName;
	}
	public void setUpduserName(String upduserName) {
		this.upduserName = upduserName;
	}
	public String getNextApprovalName() {
		return nextApprovalName;
	}
	public void setNextApprovalName(String nextApprovalName) {
		this.nextApprovalName = nextApprovalName;
	}
	
	
	
	
	
}
