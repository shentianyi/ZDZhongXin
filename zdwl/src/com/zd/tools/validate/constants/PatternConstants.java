package com.zd.tools.validate.constants;

/**
 * 日期类型格式常量（只针对timestamp类型日期）
 * */
public enum PatternConstants {
	
	/**
	 * 日期格式（精确到小时）
	 * */
	TIMESTAMP_HH("yyyy-MM-dd hh"),
	
	/**
	 * 日期格式（精确到分）
	 * */
	TIMESTAMP_MM("yyyy-MM-dd hh:mm"),
	
	/**
	 * 日期格式（精确到秒）
	 * */
	TIMESTAMP_SS("yyyy-MM-dd hh:mm:ss"),
	
	/**
	 * 日期格式（精确到天）
	 * */
	TIMESTAMP("yyyy-MM-dd");

	private String code;
	
	private PatternConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
}
