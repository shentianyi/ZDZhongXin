package com.zd.csms.windcontrol.querybean;

/**
 * 巡检报告列表集合实体
 */
import java.util.Date;

import com.zd.tools.StringUtil;

public class InspectionListRowMapper {
	private int id;
	private String planCode;// 巡检编号
	private Integer outControlUserId;// 外控专员Id
	private String outControlUserName;// 外控专员名称
	private Integer dealerId;// 经销商Id
	private String dealerName;// 经销商名称
	private Integer provinceId;// 省份Id
	private String provinceName;// 省份名称
	private Integer cityId;// 城市Id
	private String cityName;// 城市名称
	private Integer bankId;// 银行Id
	private String bankFullName;// 银行全名
	private String oneBankName;// 总行/金融机构
	private Integer brandId;// 品牌Id
	private String brandName;// 品牌名称
	private Date predictBeginDate;// 预计检查日期
	private Integer predictCheckdays;// 检查天数
	private Double predictCost;// 预计产生费用
	private int completeProcess;// 完成情况

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public Integer getOutControlUserId() {
		return outControlUserId;
	}

	public void setOutControlUserId(Integer outControlUserId) {
		this.outControlUserId = outControlUserId;
	}

	public String getOutControlUserName() {
		return outControlUserName;
	}

	public void setOutControlUserName(String outControlUserName) {
		this.outControlUserName = outControlUserName;
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
			if (null != bankNames && bankNames.length >= 1) {
				oneBankName = bankNames[0];

			}
		}
		this.bankFullName = bankFullName;
	}

	public String getOneBankName() {
		 return oneBankName;
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

	public Date getPredictBeginDate() {
		return predictBeginDate;
	}

	public void setPredictBeginDate(Date predictBeginDate) {
		this.predictBeginDate = predictBeginDate;
	}

	public Integer getPredictCheckdays() {
		return predictCheckdays;
	}

	public void setPredictCheckdays(Integer predictCheckdays) {
		this.predictCheckdays = predictCheckdays;
	}

	public Double getPredictCost() {
		return predictCost;
	}

	public void setPredictCost(Double predictCost) {
		this.predictCost = predictCost;
	}

	public int getCompleteProcess() {
		return completeProcess;
	}

	public void setCompleteProcess(int completeProcess) {
		this.completeProcess = completeProcess;
	}

}
