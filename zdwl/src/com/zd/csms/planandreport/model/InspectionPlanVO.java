package com.zd.csms.planandreport.model;

import java.util.Date;
import com.zd.core.annotation.table;

/**
 * 巡检计划详情表
 */
@table(name="t_inspection_plan")
public class InspectionPlanVO {
	
	private int id;
	
	private String planCode;//巡检计划编号
	
	private Integer outControlUserId;//外控专员Id
	private String escortUserInfo;//陪同人员信息
	private Integer dealerId;//经销商主键ID
	private Integer stock;//库存数量
	
	private Double predictCheckdays;//预计检查天数
	private Date predictBeginDate;//预计开始时间
	private Double predictCost;//预计产生费用
	private String recentCheckTime;//最近检查时间
	private String remark;//备注
	private int reportStatus=0;//巡检报告完成情况 :0未编辑 1：已编辑 2：已提交;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getPredictCheckdays() {
		return predictCheckdays;
	}
	public void setPredictCheckdays(Double predictCheckdays) {
		this.predictCheckdays = predictCheckdays;
	}
	public Integer getOutControlUserId() {
		return outControlUserId;
	}
	public void setOutControlUserId(Integer outControlUserId) {
		this.outControlUserId = outControlUserId;
	}
	public String getEscortUserInfo() {
		return escortUserInfo;
	}
	public void setEscortUserInfo(String escortUserInfo) {
		this.escortUserInfo = escortUserInfo;
	}
	public Date getPredictBeginDate() {
		return predictBeginDate;
	}
	public void setPredictBeginDate(Date predictBeginDate) {
		this.predictBeginDate = predictBeginDate;
	}
	public Double getPredictCost() {
		return predictCost;
	}
	public void setPredictCost(Double predictCost) {
		this.predictCost = predictCost;
	}
	public String getRecentCheckTime() {
		return recentCheckTime;
	}
	public void setRecentCheckTime(String recentCheckTime) {
		this.recentCheckTime = recentCheckTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDealerId() {
		return dealerId;
	}
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public int getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}
	
}
