package com.zd.csms.payment.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="T_PAYMENT_INFO")
public class PaymentInfoVO implements Serializable{

	/**
	 * 薪酬
	 */
	private static final long serialVersionUID = 3235078866843820042L;
	
	//----------------21------------------
	private int id;//主键ID
	
	private int paymentId;//薪酬表主键
	private Integer status;//审批状态 (不通过:0,通过:1)
	private String staffNo;//员工工号
	private String staffName;//员工姓名
	private String cardNo;//身份证号
	
	private String dealerNames;//店名多个用,隔开
	private Integer provinceId;//省份Id
	private String provinceName;//省份名称
	private Integer cityId;//城市Id
	private String cityName;//城市名称
	
	
	private Integer cityType;//城市类型
	private Integer isFormal;//是否转正(-1:未转正 0：半转正 1：转正)
	private Integer isFar;//是否远疆(0:否 1:是)
	private Integer stationedPro;//驻派属性
	private Double actualAttendance;//实际出勤天数
	
	private Double shouldAttendance;//应出勤天数
	private Integer isFullTime;//当月是否全勤(0：否  1：是)
	private Date entryDate;//入职日期
	private Integer companyAge=0;//司龄
	private Double overtimeDays=0.0;//加班天数
	
	//--------------------以下为核算项 18--------------------
	
	private Double basicSalary=0.0;//基本工资(固定)
	private Double basePay=0.0;//基本工资
	
	private Double companyAgePay=0.0;//司龄工资
	private Double mealSubsidy=0.0;//餐补
	private Double phoneSubsidy=0.0;//话补
	private Double trafficSubsidy=0.0;//交通补
	private Double houseSubsidy=0.0;//房补
	
	private Double manySubsidy=0.0;//多店多行补助
	private Double farSubsidy=0.0;//远疆补助
	private Double fullTimeSubsidy=0.0;//全勤奖
	private Double settleCost=0.0;//安家费
	private Double replaceCost=0.0;//替岗费
	
	private Double overtimeCost=0.0;//加班费
	
	private Double elseSubsidy=0.0;//异常补助
	private Double subsidyOne=0.0;//核算补款一
	private Double subsidyTwo=0.0;//核算补款二
	private Double deductOne=0.0;//核算扣款一
	private Double deductTwo=0.0;//核算扣款二
	
	//--------------------以下为合计项 10--------------------
	
	private Double shouldMoney=0.0;//应发金额
	private Double revenue=0.0;//个人所得税
	private Double subsidyOneTotal=0.0;//合计补款一
	private Double subsidyTwoTotal=0.0;//合计补款二
	private Double deductOneTotal=0.0;//合计扣款一
	
	private Double deductTwoTotal=0.0;//合计扣款二
	private Double praticleMoney=0.0;//实发金额
	private String remark;//备注
	private String bankCardNo;//银行卡号
	private String openBankName;//开户行
	
	//--------------------以下为审批特有--------------------
	private Double annuity;//养老金
	private Double medical;//医疗
	private Double jobInjury;//工伤
	private Double unemployment;//失业
	private Double bearing;//生育
	private Double subsidyMedical;//补充医疗
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getDealerNames() {
		return dealerNames;
	}
	public void setDealerNames(String dealerNames) {
		this.dealerNames = dealerNames;
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
	public Integer getCityType() {
		return cityType;
	}
	public void setCityType(Integer cityType) {
		this.cityType = cityType;
	}
	public Integer getIsFormal() {
		return isFormal;
	}
	public void setIsFormal(Integer isFormal) {
		this.isFormal = isFormal;
	}
	public Integer getIsFar() {
		return isFar;
	}
	public void setIsFar(Integer isFar) {
		this.isFar = isFar;
	}
	public Integer getStationedPro() {
		return stationedPro;
	}
	public void setStationedPro(Integer stationedPro) {
		this.stationedPro = stationedPro;
	}
	public Double getActualAttendance() {
		return actualAttendance;
	}
	public void setActualAttendance(Double actualAttendance) {
		this.actualAttendance = actualAttendance;
	}
	public Double getShouldAttendance() {
		return shouldAttendance;
	}
	public void setShouldAttendance(Double shouldAttendance) {
		this.shouldAttendance = shouldAttendance;
	}
	public Integer getIsFullTime() {
		return isFullTime;
	}
	public void setIsFullTime(Integer isFullTime) {
		this.isFullTime = isFullTime;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public Double getOvertimeDays() {
		return overtimeDays;
	}
	public void setOvertimeDays(Double overtimeDays) {
		this.overtimeDays = overtimeDays;
	}
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public Double getBasePay() {
		return basePay;
	}
	public void setBasePay(Double basePay) {
		this.basePay = basePay;
	}
	public Double getCompanyAgePay() {
		return companyAgePay;
	}
	public void setCompanyAgePay(Double companyAgePay) {
		this.companyAgePay = companyAgePay;
	}
	public Double getMealSubsidy() {
		return mealSubsidy;
	}
	public void setMealSubsidy(Double mealSubsidy) {
		this.mealSubsidy = mealSubsidy;
	}
	public Double getPhoneSubsidy() {
		return phoneSubsidy;
	}
	public void setPhoneSubsidy(Double phoneSubsidy) {
		this.phoneSubsidy = phoneSubsidy;
	}
	public Double getTrafficSubsidy() {
		return trafficSubsidy;
	}
	public void setTrafficSubsidy(Double trafficSubsidy) {
		this.trafficSubsidy = trafficSubsidy;
	}
	public Double getHouseSubsidy() {
		return houseSubsidy;
	}
	public void setHouseSubsidy(Double houseSubsidy) {
		this.houseSubsidy = houseSubsidy;
	}
	public Double getManySubsidy() {
		return manySubsidy;
	}
	public void setManySubsidy(Double manySubsidy) {
		this.manySubsidy = manySubsidy;
	}
	public Double getFarSubsidy() {
		return farSubsidy;
	}
	public void setFarSubsidy(Double farSubsidy) {
		this.farSubsidy = farSubsidy;
	}
	public Double getFullTimeSubsidy() {
		return fullTimeSubsidy;
	}
	public void setFullTimeSubsidy(Double fullTimeSubsidy) {
		this.fullTimeSubsidy = fullTimeSubsidy;
	}
	public Double getSettleCost() {
		return settleCost;
	}
	public void setSettleCost(Double settleCost) {
		this.settleCost = settleCost;
	}
	public Double getReplaceCost() {
		return replaceCost;
	}
	public void setReplaceCost(Double replaceCost) {
		this.replaceCost = replaceCost;
	}
	public Double getOvertimeCost() {
		return overtimeCost;
	}
	public void setOvertimeCost(Double overtimeCost) {
		this.overtimeCost = overtimeCost;
	}
	public Double getElseSubsidy() {
		return elseSubsidy;
	}
	public void setElseSubsidy(Double elseSubsidy) {
		this.elseSubsidy = elseSubsidy;
	}
	public Double getSubsidyOne() {
		return subsidyOne;
	}
	public void setSubsidyOne(Double subsidyOne) {
		this.subsidyOne = subsidyOne;
	}
	public Double getSubsidyTwo() {
		return subsidyTwo;
	}
	public void setSubsidyTwo(Double subsidyTwo) {
		this.subsidyTwo = subsidyTwo;
	}
	public Double getDeductOne() {
		return deductOne;
	}
	public void setDeductOne(Double deductOne) {
		this.deductOne = deductOne;
	}
	public Double getDeductTwo() {
		return deductTwo;
	}
	public void setDeductTwo(Double deductTwo) {
		this.deductTwo = deductTwo;
	}
	public Double getShouldMoney() {
		return shouldMoney;
	}
	public void setShouldMoney(Double shouldMoney) {
		this.shouldMoney = shouldMoney;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public Double getSubsidyOneTotal() {
		return subsidyOneTotal;
	}
	public void setSubsidyOneTotal(Double subsidyOneTotal) {
		this.subsidyOneTotal = subsidyOneTotal;
	}
	public Double getSubsidyTwoTotal() {
		return subsidyTwoTotal;
	}
	public void setSubsidyTwoTotal(Double subsidyTwoTotal) {
		this.subsidyTwoTotal = subsidyTwoTotal;
	}
	public Double getDeductOneTotal() {
		return deductOneTotal;
	}
	public void setDeductOneTotal(Double deductOneTotal) {
		this.deductOneTotal = deductOneTotal;
	}
	public Double getDeductTwoTotal() {
		return deductTwoTotal;
	}
	public void setDeductTwoTotal(Double deductTwoTotal) {
		this.deductTwoTotal = deductTwoTotal;
	}
	public Double getPraticleMoney() {
		return praticleMoney;
	}
	public void setPraticleMoney(Double praticleMoney) {
		this.praticleMoney = praticleMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getOpenBankName() {
		return openBankName;
	}
	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}
	public Double getAnnuity() {
		return annuity;
	}
	public void setAnnuity(Double annuity) {
		this.annuity = annuity;
	}
	public Double getMedical() {
		return medical;
	}
	public void setMedical(Double medical) {
		this.medical = medical;
	}
	public Double getJobInjury() {
		return jobInjury;
	}
	public void setJobInjury(Double jobInjury) {
		this.jobInjury = jobInjury;
	}
	public Double getUnemployment() {
		return unemployment;
	}
	public void setUnemployment(Double unemployment) {
		this.unemployment = unemployment;
	}
	public Double getBearing() {
		return bearing;
	}
	public void setBearing(Double bearing) {
		this.bearing = bearing;
	}
	public Double getSubsidyMedical() {
		return subsidyMedical;
	}
	public void setSubsidyMedical(Double subsidyMedical) {
		this.subsidyMedical = subsidyMedical;
	}
	public Integer getCompanyAge() {
		return companyAge;
	}
	public void setCompanyAge(Integer companyAge) {
		this.companyAge = companyAge;
	}
	
}
