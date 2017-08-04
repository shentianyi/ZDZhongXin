package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum AreaIdTypeConstants {
	/**
	 * 区域ID
	 */
	AREAID("areaid"),
	/**
	 * 经销商ID
	 */
	DISTRIBUTORID("distributorid"),
	/**
	 * 额度ID
	 */
	CREDITID("creditid"),
	/**
	 * 评估业务组ID
	 */
	GROUPID("groupid"),
	/**
	 * 抵押车辆ID
	 */
	MORTGAGEID("mortgageid"),
	/**
	 * 解押车辆ID
	 */
	RELEASEID("releaseid"),
	
	;
	
	private String code;
	AreaIdTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 