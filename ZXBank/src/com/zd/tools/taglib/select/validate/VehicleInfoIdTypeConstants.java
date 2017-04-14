package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum VehicleInfoIdTypeConstants {
	/**
	 * 抵押车辆ID
	 */
	MORTGAGEVEHICLEID("mortgageVehicleId"),
	/**
	 * 解押ID
	 */
	RELEASE("release"),
	/**
	 * 车辆ID
	 */
	VEHICELID("vehicleId"),
	
	
	/**
	 * 发动机号
	 */
	ENGINE("engine");
	;
	
	private String code;
	VehicleInfoIdTypeConstants(String code){
		this.code = code;
	}
	public String getCode(){
		return code;
	}
}
 