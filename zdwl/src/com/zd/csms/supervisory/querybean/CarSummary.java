package com.zd.csms.supervisory.querybean;

/**
 * 车辆信息汇总类
 *
 */
public class CarSummary {
	private Integer count;//车辆数量
	private Double carMoney;//车辆总金额
	private Double carBond;//总保证金
	private Double carPaymentAmount;//总回款金额
	
	public Double getCarMoney() {
		return carMoney;
	}
	public void setCarMoney(Double carMoney) {
		this.carMoney = carMoney;
	}
	public Double getCarBond() {
		return carBond;
	}
	public void setCarBond(Double carBond) {
		this.carBond = carBond;
	}
	public Double getCarPaymentAmount() {
		return carPaymentAmount;
	}
	public void setCarPaymentAmount(Double carPaymentAmount) {
		this.carPaymentAmount = carPaymentAmount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
