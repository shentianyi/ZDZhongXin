package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum BankNameIdTypeConstants {
	/**
	 * 银行ID
	 * */
	BANK("bankid"),
	/**
	 * 经销商id
	 */
	DISTRIB("distribid"),
	/**
	 * 授信额度ID
	 */
	CREDIT("creditid"),
	/**
	 * 抵押车辆ID
	 */
	MORTGAGEVEHICLEID("mortgagevehicleid"),
	/**
	 * 银行与经?关系ID
	 */
	FINANCECLIENT("financeclientid"),
	
	/**
	 * 银行与监管机构关系ID
	 */
	BANKSUPERVISORY("banksupervisoryid");
	
	private String code;
	BankNameIdTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 