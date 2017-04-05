package com.zd.csms.constants;

/**
 * 系统常用枚举
 * 约定了操作消息及部分通用参数常量
 * */
public enum Constants {
	
	//操作信息
	/**
	 * 操作结果
	 * */
	OPERATE_FLAG("flag"),
	/**
	 * 操作消息
	 * */
	OPERATE_MESSAGE("message"),
	/**
	 * 操作成功提示消息
	 * */
	OPERATE_MESSAGE_SUCCEEDED("操作成功"),
	/**
	 * 操作失败提示消息
	 * */
	OPERATE_MESSAGE_FAILED("操作失败"),
	
	//功能清单入口
	/**
	 * 功能清单入口参数名
	 * */
	PARAM_MENU_ENTRY("param_menu_entry"),
	/**
	 * 功能清单入口参数值
	 * */
	VALUE_MENU_ENTRY_TRUE("value_menu_entry_true");
	
	private String code;
	
	private Constants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
}
 