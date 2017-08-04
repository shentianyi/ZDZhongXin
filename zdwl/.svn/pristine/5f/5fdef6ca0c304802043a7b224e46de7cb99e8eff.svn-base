package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="T_HANDOVER_LOG")
public class HandoverLogVO implements Serializable {

	/**
	 * 交接记录表
	 */
	private static final long serialVersionUID = -7934939329442326385L;
	/**
	 * 主键ID
	 */
	private int id;     
	
	/**
	 * 交接类型：1：绑定/拆绑交接；2：轮岗交接
	 */
	private int handoverType;
	
	/**
	 * 经销商ID
	 */
	private int dealerId;                	
	/**
	 * 店方要求
	 */
	private String merchantDemand;    
	/**
	 * 住宿提供方
	 */
	private String accommodationProvider;
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
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 工服情况
	 */
	private String workCondition;
	/**
	 * 是否可编辑 0:可编辑，1：不可编辑
	 */
	private int isEdit;
	/**
	 * 下一审批人
	 */
	private int nextApproval;
	/**
	 * 审批状态-1:未保存  0：未审批，1：正在审批，3：审批完成
	 */
	private int approveStatus;
	/**
	 * 发起人
	 */
	private int createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private int lastModifyUser;
	/**
	 * 最后修改时间
	 */
	private Date lastModifyTime;
	/**
	 * 接收方监管公司名称 -- 需求46
	 */
	private String recipientCompanyName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getHandoverType() {
		return handoverType;
	}
	public void setHandoverType(int handoverType) {
		this.handoverType = handoverType;
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
	public String getAccommodationProvider() {
		return accommodationProvider;
	}
	public void setAccommodationProvider(String accommodationProvider) {
		this.accommodationProvider = accommodationProvider;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWorkCondition() {
		return workCondition;
	}
	public void setWorkCondition(String workCondition) {
		this.workCondition = workCondition;
	}
	public int getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}
	public int getNextApproval() {
		return nextApproval;
	}
	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}
	public int getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(int approveStatus) {
		this.approveStatus = approveStatus;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(int lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getRecipientCompanyName() {
		return recipientCompanyName;
	}
	public void setRecipientCompanyName(String recipientCompanyName) {
		this.recipientCompanyName = recipientCompanyName;
	}
}
