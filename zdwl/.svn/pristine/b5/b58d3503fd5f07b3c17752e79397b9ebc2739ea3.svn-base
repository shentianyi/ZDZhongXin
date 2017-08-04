package com.zd.csms.planandreport.model;

import java.util.Date;

import com.zd.csms.rbac.model.UserVO;

/**
 * 视频检查计划详情页查询对象
 * 也适用于视频检查报告及台账列表查询条件实体
 * @author wdc
 *
 */
public class VideoPlanQueryVO {
	
	private Integer oneBankId;//总行/金融机构
	private Integer twoBankId;//分行/分支机构
	private Integer threeBankId;//支行
	private Integer brandId;//品牌Id
	private String brandName;//品牌名称
	private Integer provinceId;//省份Id
	private String provinceName;//省份名称
	private Integer cityId;//城市Id
	private String cityName;//城市名称
	private Integer dealerId;//经销商Id
	private String dealerName;//经销商名称
	private Integer videoUserId;
	private String planCode;
	private String videoUserName;
	//查询标识(1:审批  未审核 列表  )
	//此字段也适用于视频检查报告及台账  (0:未完成 ,1:已完成)
	private Integer flag; 
	private Date submitTimeBegin; //提交时间开始
	private Date submitTimeEnd; //提交时间结束
	
	private int currRole;//当前角色
	private int userId;//用户ID
	
	private UserVO userVO;
	
	
	/**
	 * @return the userVO
	 */
	public UserVO getUserVO() {
		return userVO;
	}

	/**
	 * @param userVO the userVO to set
	 */
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public Date getSubmitTimeBegin() {
		return submitTimeBegin;
	}

	public void setSubmitTimeBegin(Date submitTimeBegin) {
		this.submitTimeBegin = submitTimeBegin;
	}

	public Date getSubmitTimeEnd() {
		return submitTimeEnd;
	}

	public void setSubmitTimeEnd(Date submitTimeEnd) {
		this.submitTimeEnd = submitTimeEnd;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getVideoUserName() {
		return videoUserName;
	}

	public void setVideoUserName(String videoUserName) {
		this.videoUserName = videoUserName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public Integer getVideoUserId() {
		return videoUserId;
	}

	public void setVideoUserId(Integer videoUserId) {
		this.videoUserId = videoUserId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getOneBankId() {
		return oneBankId;
	}

	public void setOneBankId(Integer oneBankId) {
		this.oneBankId = oneBankId;
	}

	public Integer getTwoBankId() {
		return twoBankId;
	}

	public void setTwoBankId(Integer twoBankId) {
		this.twoBankId = twoBankId;
	}

	public Integer getThreeBankId() {
		return threeBankId;
	}

	public void setThreeBankId(Integer threeBankId) {
		this.threeBankId = threeBankId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public int getCurrRole() {
		return currRole;
	}

	public void setCurrRole(int currRole) {
		this.currRole = currRole;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
