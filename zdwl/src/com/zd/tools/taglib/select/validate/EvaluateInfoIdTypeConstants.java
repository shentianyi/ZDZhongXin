package com.zd.tools.taglib.select.validate;

/**
 * 常量?
 */
public enum EvaluateInfoIdTypeConstants {
	/**
	 * 评估价格
	 */
	PRICE("price"),
	/**
	 * 评估到期?
	 */
	VALID("valid"),
	/**
	 * 评估日期
	 */
	DATE("date"),
	
	/**
	 * 费率
	 */
	RATES("rates"),
	/**
	 * 评估费用
	 */
	EVALUATEPRICE("evaluateprice"),
	VALIDDATE("validdate"),
	RELEASEPRICE("releaseprice"),
	BANKPRICE("bankprice"),
	;
	
	private String code;
	EvaluateInfoIdTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 