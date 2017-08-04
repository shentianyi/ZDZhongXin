package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum GroupNameIdTypeConstants {
	/**
	 * 评估组ID
	 */
	GROUPID("grouId"),
	/**
	 * 抵押车辆ID
	 */
	MORTGAGEVEHICLEID("mortgageVehicleId"),
	;
	
	private String code;
	GroupNameIdTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 