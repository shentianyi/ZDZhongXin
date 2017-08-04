package com.zd.csms.windcontrol.web.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.zd.csms.rbac.model.UserVO;

/**
 * 巡检报告台账列表查询条件实体
 * 
 * @author zhangzhicheng
 *
 */
public class InspectionLedgerForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5182062314129806486L;
	private Integer oneBankId;// 总行/金融机构
	private Integer twoBankId;// 分行/分支机构
	private Integer threeBankId;// 支行
	private Integer brandId;// 品牌Id
	private String brandName;// 品牌名称
	private Integer provinceId;// 省份Id
	private String provinceName;// 省份名称
	private Integer cityId;// 城市Id
	private String cityName;// 城市名称
	private Integer dealerId;// 经销商Id
	private String dealerName;// 经销商名称
	private String planCode;// 巡检编号
	private int danger_level;// 风险等级 :4无 1：A级 2:B级 3:C级
	private int dealer_level;// 经销店等级：4无 1：A级 2:B级 3:C级
	private int supervisor_level;// 监管员等级：4无 1：A级 2:B级 3:C级
	private Date checkTimeBegin; // 检查日期开始
	private Date checkTimeEnd; // 检查日期结束
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public int getDanger_level() {
		return danger_level;
	}

	public void setDanger_level(int danger_level) {
		this.danger_level = danger_level;
	}

	public int getDealer_level() {
		return dealer_level;
	}

	public void setDealer_level(int dealer_level) {
		this.dealer_level = dealer_level;
	}

	public int getSupervisor_level() {
		return supervisor_level;
	}

	public void setSupervisor_level(int supervisor_level) {
		this.supervisor_level = supervisor_level;
	}

	public Date getCheckTimeBegin() {
		return checkTimeBegin;
	}

	public void setCheckTimeBegin(Date checkTimeBegin) {
		this.checkTimeBegin = checkTimeBegin;
	}

	public Date getCheckTimeEnd() {
		return checkTimeEnd;
	}

	public void setCheckTimeEnd(Date checkTimeEnd) {
		this.checkTimeEnd = checkTimeEnd;
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
