package com.zd.csms.planandreport.querybean;

import java.util.Date;
import java.util.Map;

import com.zd.tools.StringUtil;

/**
 * 视频检查计划查询对象
 * 
 * @author wdc
 *
 */
public class VideoPlanQueryBean {
	private int id;
	private String planCode;// 计划编号
	private Integer videoUserId;// 视频专员Id
	private String videoUserName;// 视频专员名称
	private Integer dealerId;// 经销商Id
	private String dealerName;// 经销商名称
	private Integer provinceId;// 省份Id
	private String provinceName;// 省份名称
	private Integer cityId;// 城市Id
	private String cityName;// 城市名称
	private Integer bankId;// 银行Id
	private String bankFullName;// 银行全名
	private String oneBankName;// 总行/金融机构
	private String twoBankName;// 分行/分支机构
	private String threeBankName;// 支行
	private Integer brandId;// 品牌Id
	private String brandName;// 品牌名称
	private Integer stock;// 库存数量
	private String practicalCheckTime;// 实际检查时间
	private Date predictCheckDate;// 预计检查时间
	private Integer checkMinute;// 预计检查分钟
	private Date submitTime;// 提交时间
	private Date submitReportTime;// 视频检查报告提交时间
	private int editStatus;// 编辑状态 0：未编辑 1：已编辑 2:提交

	private String recentCheckTime;// 最近检查时间
	private int planCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecentCheckTime() {
		return recentCheckTime;
	}

	public void setRecentCheckTime(String recentCheckTime) {
		this.recentCheckTime = recentCheckTime;
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

	public String getVideoUserName() {
		return videoUserName;
	}

	public void setVideoUserName(String videoUserName) {
		this.videoUserName = videoUserName;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankFullName() {
		return bankFullName;
	}

	public void setBankFullName(String bankFullName) {
		if (StringUtil.isNotEmpty(bankFullName)) {
			String[] bankNames = bankFullName.split("/");
			if (bankNames != null && bankNames.length == 2) {
				oneBankName = bankNames[0];
				twoBankName = bankNames[1];

			} else if (bankNames != null && bankNames.length == 3) {
				oneBankName = bankNames[0];
				twoBankName = bankNames[1];
				threeBankName = bankNames[2];
			}
		}
		this.bankFullName = bankFullName;
	}

	public String getOneBankName() {
		return oneBankName;
	}

	public void setOneBankName(String oneBankName) {
		this.oneBankName = oneBankName;
	}

	public String getTwoBankName() {
		return twoBankName;
	}

	public void setTwoBankName(String twoBankName) {
		this.twoBankName = twoBankName;
	}

	public String getThreeBankName() {
		return threeBankName;
	}

	public void setThreeBankName(String threeBankName) {
		this.threeBankName = threeBankName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPracticalCheckTime() {
		return practicalCheckTime;
	}

	public void setPracticalCheckTime(String practicalCheckTime) {
		this.practicalCheckTime = practicalCheckTime;
	}

	public Date getPredictCheckDate() {
		return predictCheckDate;
	}

	public void setPredictCheckDate(Date predictCheckDate) {
		this.predictCheckDate = predictCheckDate;
	}

	public Integer getCheckMinute() {
		return checkMinute;
	}

	public void setCheckMinute(Integer checkMinute) {
		this.checkMinute = checkMinute;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getSubmitReportTime() {
		if (editStatus < 2) {
			submitReportTime = null;
		}

		return submitReportTime;
	}

	public void setSubmitReportTime(Date submitReportTime) {
		this.submitReportTime = submitReportTime;
	}

	public int getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(int editStatus) {
		this.editStatus = editStatus;
	}

	public int getPlanCount() {
		return planCount;
	}

	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}

	

}
