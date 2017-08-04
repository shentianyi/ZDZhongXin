package com.zd.csms.planandreport.model;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 巡检计划表
 */
@table(name="t_inspection_planinfo")
public class InspectionPlanInfoVO {
	
	private int id;
	
	private String planCode;//计划编号
	private Integer inControlUserId;//内控专员Id
	private Integer mkTableUserId;//制表人
	
	private Integer provinceAmount;//省数
	private Integer cityAmount;//市数
	private Integer stores;//涉及店数
	
	private Integer bankAmount;//银行数
	private Integer brandAmount;//品牌数
	private Integer stockAmount;//库存合计
	
	private Double predictCheckdays;//预计检查天数
	private String planBeginTime;//计划开始时间
	private String planSubmitTime;//计划提交时间
	private Date predictFinishDate;//预计完成日期
	
	private Integer status;//状态
	private String unCheckPassCause;//审批意见
	private Date createDate;//创建时间
	private Date lastUpdateDate;//最后更新时间
	private Integer nextApproval;//下一审批人
	
	public Integer getNextApproval() {
		return nextApproval;
	}
	public void setNextApproval(Integer nextApproval) {
		this.nextApproval = nextApproval;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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
	public Integer getInControlUserId() {
		return inControlUserId;
	}
	public void setInControlUserId(Integer inControlUserId) {
		this.inControlUserId = inControlUserId;
	}
	public Integer getMkTableUserId() {
		return mkTableUserId;
	}
	public void setMkTableUserId(Integer mkTableUserId) {
		this.mkTableUserId = mkTableUserId;
	}
	public Integer getProvinceAmount() {
		return provinceAmount;
	}
	public void setProvinceAmount(Integer provinceAmount) {
		this.provinceAmount = provinceAmount;
	}
	public Integer getCityAmount() {
		return cityAmount;
	}
	public void setCityAmount(Integer cityAmount) {
		this.cityAmount = cityAmount;
	}
	public Integer getBankAmount() {
		return bankAmount;
	}
	public void setBankAmount(Integer bankAmount) {
		this.bankAmount = bankAmount;
	}
	public Integer getBrandAmount() {
		return brandAmount;
	}
	public void setBrandAmount(Integer brandAmount) {
		this.brandAmount = brandAmount;
	}
	public Double getPredictCheckdays() {
		return predictCheckdays;
	}
	public void setPredictCheckdays(Double predictCheckdays) {
		this.predictCheckdays = predictCheckdays;
	}
	public String getPlanBeginTime() {
		return planBeginTime;
	}
	public void setPlanBeginTime(String planBeginTime) {
		this.planBeginTime = planBeginTime;
	}
	public String getPlanSubmitTime() {
		return planSubmitTime;
	}
	public void setPlanSubmitTime(String planSubmitTime) {
		this.planSubmitTime = planSubmitTime;
	}
	public Date getPredictFinishDate() {
		return predictFinishDate;
	}
	public void setPredictFinishDate(Date predictFinishDate) {
		this.predictFinishDate = predictFinishDate;
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
	public Integer getStores() {
		return stores;
	}
	public void setStores(Integer stores) {
		this.stores = stores;
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
