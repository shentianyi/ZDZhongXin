package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum FinancingCreditInfoConstants {
	/**
	 * 主键ID
	 */
	ID("id"),
	/**
	 * 抵押车辆ID
	 */
	MORTGAGEVEHICLEID("mortgageVehicleId"),
	/**
	 * 解押车辆ID
	 */
	RELEASEID("releaseId"),
	
	/**
	 * 授信额度
	 */
	CREDITNUM("creditNum"),
	/**
	 * 抵押额度
	 */
	MORTGAGENUM("mortgageNum"),
	/**
	 * 已用抵押额度
	 */
	OCCUPANCYMLNUM("occupancymlNum"),
	/**
	 * 授信期限
	 */
	CREDITVALID("creditValid"),
	/**
	 * 可用额度 抵押额度-已用额度
	 */
	AVAILABLE("available"),
	/**
	 * 可用额度 抵押额度-已用额度
	 */
	NEEDPAY("needpay"),
	;
	
	private String code;
	FinancingCreditInfoConstants(String code){
		this.code = code;
	}
	public String getCode(){
		return code;
	}
}
 