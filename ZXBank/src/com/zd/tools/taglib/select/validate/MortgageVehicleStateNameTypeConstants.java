package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum MortgageVehicleStateNameTypeConstants {
	/**
	 * 状?参数
	 * */
	STATE("state"),
	/**
	 * 车辆ID
	 */
	VEHICLE("vehicle"),
	/**
	 * 抵押车辆ID
	 */
	MORTGAGEVEHICLEID("mortgagevehicleid"),
	;
	private String code;
	MortgageVehicleStateNameTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 