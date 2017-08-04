package com.zd.csms.supervisorymanagement.model;


import java.util.Date;

public class HandoverPlanQueryVO  {

	/**
	 * 主键ID
	 */
	private int id;                      	
	/**
	 * 经销商ID
	 */
	private int dealerId;                	
	/**
	 * 店方要求
	 */
	private String merchantDemand;    
	/**
	 * 绑定店
	 */
	private String bindMerchant;            
	/**
	 * 绑定行
	 */
	private String bindBank;                
	/**
	 * 交付人ID(监管员ID)
	 */
	private int deliverer;                	
	/**
	 * 交接性质：1：辞职，2：辞退，3：正常轮岗，4：投诉轮岗
	 */
	private int  handoverNature;          	
	/**
	 * 接收人
	 */
	private int  receiver;     
	/**
	 * 调入时间
	 */
	private Date  missionDate;     
	/**
	 * 接收性质：1：轮岗，2：新入职，3：二次上岗
	 */
	private int receiveNature;    
	/**
	 * 金融机构名称
	 */
	private String bankName;
	/**
	 * 品牌
	 */
	private int brandId;
	
	//审批页面条件
	/**
	 * 当前角色
	 */
	private int currentRole;
	/**
	 * 页面审批状态  
	 */
	private int pageType;
	/**
	 * 审批人意见
	 */
	private String remark;
	/**
	 * 审批状态
	 */
	private int approvalState;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getMerchantDemand() {
		return merchantDemand;
	}
	public void setMerchantDemand(String merchantDemand) {
		this.merchantDemand = merchantDemand;
	}
	public String getBindMerchant() {
		return bindMerchant;
	}
	public void setBindMerchant(String bindMerchant) {
		this.bindMerchant = bindMerchant;
	}
	public String getBindBank() {
		return bindBank;
	}
	public void setBindBank(String bindBank) {
		this.bindBank = bindBank;
	}
	public int getDeliverer() {
		return deliverer;
	}
	public void setDeliverer(int deliverer) {
		this.deliverer = deliverer;
	}
	public int getHandoverNature() {
		return handoverNature;
	}
	public void setHandoverNature(int handoverNature) {
		this.handoverNature = handoverNature;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public int getReceiveNature() {
		return receiveNature;
	}
	public void setReceiveNature(int receiveNature) {
		this.receiveNature = receiveNature;
	}
	public Date getMissionDate() {
		return missionDate;
	}
	public void setMissionDate(Date missionDate) {
		this.missionDate = missionDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(int currentRole) {
		this.currentRole = currentRole;
	}
	public int getPageType() {
		return pageType;
	}
	public void setPageType(int pageType) {
		this.pageType = pageType;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

}
