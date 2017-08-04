package com.zd.csms.planandreport.model;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 视频检查计划详情表
 */
@table(name="t_video_plan")
public class VideoPlanVO {
	
	private int id;
	
	private Integer videoUserId;//视频专员Id
	private Integer dealerId;//经销商主键ID
	//private Integer brandId;//品牌Id
	private Integer stock;//库存数量
	private Integer checkMinute;//预计检查分钟
	private Date predictCheckDate;//预计检查日期
	
	//private Date practicalCheckTime;//实际检查时间
	private String recentCheckTime;//最近检查时间
	private String practicalCheckTime;//实际检查时间
	private String planCode;//计划编号
	private Date submitTime;//提交时间
	private Date submitReportTime ;// 视频检查报告提交时间
	private int  reportStatus=0;//视频检查报告状态 ：0：未编辑 1：已编辑  2：已提交
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public String getRecentCheckTime() {
		return recentCheckTime;
	}
	public void setRecentCheckTime(String recentCheckTime) {
		this.recentCheckTime = recentCheckTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Integer getVideoUserId() {
		return videoUserId;
	}
	public void setVideoUserId(Integer videoUserId) {
		this.videoUserId = videoUserId;
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
	public Integer getCheckMinute() {
		return checkMinute;
	}
	public void setCheckMinute(Integer checkMinute) {
		this.checkMinute = checkMinute;
	}
	public Date getPredictCheckDate() {
		return predictCheckDate;
	}
	public void setPredictCheckDate(Date predictCheckDate) {
		this.predictCheckDate = predictCheckDate;
	}
	public String getPracticalCheckTime() {
		return practicalCheckTime;
	}
	public void setPracticalCheckTime(String practicalCheckTime) {
		this.practicalCheckTime = practicalCheckTime;
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
	
	public Date getSubmitReportTime() {
		return submitReportTime;
	}
	public void setSubmitReportTime(Date submitReportTime) {
		this.submitReportTime = submitReportTime;
	}
	
	
}
