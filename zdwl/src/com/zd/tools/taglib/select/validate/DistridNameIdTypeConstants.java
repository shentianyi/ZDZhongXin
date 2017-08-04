package com.zd.tools.taglib.select.validate;

/**
 * entityType常量类
 */
public enum DistridNameIdTypeConstants {
	/**
	 * 经销商ID
	 * */
	DISTRIB("distribid"),
	/**
	 * 授信额度ID
	 */
	CREDIT("creditid"),
	
	/**
	 * 银行与经销商关系ID
	 */
	FINANCECLIENT("financeclientid"),
	/**
	 * 经销商名称
	 */
	DISTRIBNAME("distribname"),
	/**
	 * 客户类型
	 */
	DISTRIBTYPE("distribType"),
	/**
	 * 联系方式（总经理电话）
	 */
	TEL("tel"),
	/**
	 * 授信资质表ID
	 */
	QUALITY("qualityid"),
	/**
	 * 抵押车辆ID
	 */
	MORTGAGEVEHICLEID("mortgageVehicleId"),
	/**
	 * 抵押额度
	 */
	MORTGAGENUM("mortgageNum"),
	/**
	 * 银行认可价
	 */
	BANKMORTGAGENUM("bankmortgageNum"),
	/**
	 * 解押车辆ID
	 */
	RELEASEID("releaseId"),
	;
	
	
	
	

	private String code;
	DistridNameIdTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 