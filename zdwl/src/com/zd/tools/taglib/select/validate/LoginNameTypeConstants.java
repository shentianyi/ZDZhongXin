package com.zd.tools.taglib.select.validate;

/**
 * entityType常量?
 */
public enum LoginNameTypeConstants {
	/**
	 * 用户?
	 */
	USERNAME("username"),
	/**
	 * 登陆?
	 */
	LOGINNAME("loginname"),
	;
	
	
	
	

	private String code;
	LoginNameTypeConstants(String code){
		this.code = code;
	} 
	public String getCode(){
		return code;
	}
}
 