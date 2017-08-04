package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum CensorDayInfoTypeConstants {
	/**
	 * 日查库ID
	 */
	ID("id"),
	/**
	 * 客户名称
	 */
	DISTRIBUTOR("distributor"),
	/**
	 * 银行名称
	 */
	BANK("bank"),
	/**
	 * 监管员姓?
	 */
	SUPERVISOR("supervisor"),
	/**
	 * 查库日期
	 */
	CENSORDAY("censorday"),
	
	;
	
	
	
	

	private String code;
	CensorDayInfoTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 