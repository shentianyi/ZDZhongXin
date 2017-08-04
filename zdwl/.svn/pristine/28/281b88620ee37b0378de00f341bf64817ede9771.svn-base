package com.zd.csms.planandreport.model;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 视频检查计划列表
 */
@table(name="t_video_planinfo")
public class VideoPlanInfoVO {
	
	private int id;
	
	private String planCode;//计划编号
	private Integer videoUserId;//视频专员Id
	private Integer stores;//涉及店数
	private String planExecuteTime;//计划执行时间
	//private Date planExecuteTime;//计划执行时间
	private Double checkHoursAmount;//检查小时合计
	private Integer stockAmount;//库存合计
	private Integer status;//状态
	private String unCheckPassCause;//审批不通过原因
	private Date createDate;//创建时间
	private Integer nextApproval;//下一审批人
	
	public Integer getNextApproval() {
		return nextApproval;
	}
	public void setNextApproval(Integer nextApproval) {
		this.nextApproval = nextApproval;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUnCheckPassCause() {
		return unCheckPassCause;
	}
	public void setUnCheckPassCause(String unCheckPassCause) {
		this.unCheckPassCause = unCheckPassCause;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public Integer getVideoUserId() {
		return videoUserId;
	}
	public void setVideoUserId(Integer videoUserId) {
		this.videoUserId = videoUserId;
	}
	public Integer getStores() {
		return stores;
	}
	public void setStores(Integer stores) {
		this.stores = stores;
	}
	public String getPlanExecuteTime() {
		return planExecuteTime;
	}
	public void setPlanExecuteTime(String planExecuteTime) {
		this.planExecuteTime = planExecuteTime;
	}
	public Double getCheckHoursAmount() {
		return checkHoursAmount;
	}
	public void setCheckHoursAmount(Double checkHoursAmount) {
		this.checkHoursAmount = checkHoursAmount;
	}
	public Integer getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(Integer stockAmount) {
		this.stockAmount = stockAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
