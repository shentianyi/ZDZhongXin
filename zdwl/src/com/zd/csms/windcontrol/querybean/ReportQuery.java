package com.zd.csms.windcontrol.querybean;


/**
 * 巡检报告列表查询条件实体
 * 
 * @author zhangzhicheng
 *
 */
public class ReportQuery{
    private Integer provinceId;// 省份Id
	private String provinceName;// 省份名称
	private Integer cityId;// 城市Id
	private String cityName;// 城市名称
	private Integer dealerId;// 经销商Id
	private String dealerName;// 经销商名称
	private String planCode;// 巡检编号
	private int flag=0; // 巡检报告完成情况(0:未完成 ,1:已完成)
	private int client_id = -1; // 记录操作员的ID号

	
	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
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

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
