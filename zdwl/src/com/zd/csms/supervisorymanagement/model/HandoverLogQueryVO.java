package com.zd.csms.supervisorymanagement.model;

import java.util.Date;
public class HandoverLogQueryVO {

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
	 * 交付人申请时间
	 */
	private Date delivererApplicationDate;	
	/**
	 * 预计离岗时间
	 */
	private Date expectedDimissionDate;   	
	/**
	 * 交接性质：1：辞职，2：辞退，3：正常轮岗，4：投诉轮岗
	 */
	private int  handoverNature;          	
	/**
	 * 辞职原因：1：个人原因，2：换工作，3：工作地点原因
	 */
	private int  resignReason;           
	/**
	 * 离岗时间
	 */
	private Date  dimissionDate;         
	/**
	 * 接收人
	 */
	private int  receiver;               
	/**
	 * 接收性质：1：轮岗，2：新入职，3：二次上岗
	 */
	private int receiveNature;          
	/**
	 * 交接后属性：1：属地，2：外派
	 */
	private int afterHandoverNature;    
	/**
	 * 上岗时间
	 */
	private Date missionDate;            
	/**
	 * 交接时间
	 */
	private Date handoverDate;           
	/**
	 * 完成时间
	 */
	private Date finishTime;             
	/**
	 * 调配专员
	 */
	private int deployId;               
	/**
	 * 跟进情况
	 */
	private String fallowStatus;            
	/**
	 * 情况说明
	 */
	private String situationExplain;
	//审批页面查询条件
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
	private int approvalState;
	/**
	 * 当前储备库ID
	 */
	private int currentRepositoryId;
	/**
	 * 创建时间
	 */
	private Date createDate;
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
	public Date getDelivererApplicationDate() {
		return delivererApplicationDate;
	}
	public void setDelivererApplicationDate(Date delivererApplicationDate) {
		this.delivererApplicationDate = delivererApplicationDate;
	}
	public Date getExpectedDimissionDate() {
		return expectedDimissionDate;
	}
	public void setExpectedDimissionDate(Date expectedDimissionDate) {
		this.expectedDimissionDate = expectedDimissionDate;
	}
	public int getHandoverNature() {
		return handoverNature;
	}
	public void setHandoverNature(int handoverNature) {
		this.handoverNature = handoverNature;
	}
	public int getResignReason() {
		return resignReason;
	}
	public void setResignReason(int resignReason) {
		this.resignReason = resignReason;
	}
	public Date getDimissionDate() {
		return dimissionDate;
	}
	public void setDimissionDate(Date dimissionDate) {
		this.dimissionDate = dimissionDate;
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
	public int getAfterHandoverNature() {
		return afterHandoverNature;
	}
	public void setAfterHandoverNature(int afterHandoverNature) {
		this.afterHandoverNature = afterHandoverNature;
	}
	public Date getMissionDate() {
		return missionDate;
	}
	public void setMissionDate(Date missionDate) {
		this.missionDate = missionDate;
	}
	public Date getHandoverDate() {
		return handoverDate;
	}
	public void setHandoverDate(Date handoverDate) {
		this.handoverDate = handoverDate;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public int getDeployId() {
		return deployId;
	}
	public void setDeployId(int deployId) {
		this.deployId = deployId;
	}
	public String getFallowStatus() {
		return fallowStatus;
	}
	public void setFallowStatus(String fallowStatus) {
		this.fallowStatus = fallowStatus;
	}
	public String getSituationExplain() {
		return situationExplain;
	}
	public void setSituationExplain(String situationExplain) {
		this.situationExplain = situationExplain;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public int getCurrentRepositoryId() {
		return currentRepositoryId;
	}
	public void setCurrentRepositoryId(int currentRepositoryId) {
		this.currentRepositoryId = currentRepositoryId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}        

}
