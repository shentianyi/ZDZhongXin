package com.zd.tools.validate.constants;

//不可为空类型常量
public enum NotEmptyConstants {

	/**
	 * 不可为空（纯粹空字符串）
	 * */
	TRUE("true"),
	/**
	 * 可为空
	 * */
	FALSE("false"),
	/**
	 * 不可为空（包括纯空格）
	 * */
	SPACING("spacing"),
	/**
	 * 不可为空（包括0）
	 * */
	NUMBER("number");
	
	private String code;
	
	private NotEmptyConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
}
