package com.zd.tools.thumbPage.constants;

/**
 * 翻页标签常量类
 * */
public enum ThumbPageConstants {


	//翻页工具条类型
	/**
	 * 表头
	 * */
	TOOLSTYPE_HEAD("head"),
	/**
	 * 表尾
	 * */
	TOOLSTYPE_FOOT("foot"),
	
	//内存记录属性KEY前缀
	/**
	 * 翻页表格名称
	 * */
	THUMBPAGE_TABLENAME("thumbpage_tablename"),
	/**
	 * 翻页参数（当前页等）
	 * */
	THUMBPAGE_PARAM("thumbpage_param"),
	/**
	 * 翻页查询条件
	 * */
	THUMBPAGE_QUERY("thumbpage_query"),
	
	//加载连接中的参数名
	/**
	 * 是否将页面参数设置到内存中
	 * */
	PARAM_REMEMBER("param_remember"),
	/**
	 * 是否将页面参数设置到内存中(是)
	 * */
	PARAM_REMEMBER_TRUE("param_remember_true"),
	
	//加载连接中的参数名
	/**
	 * 是否将查询条件设置到内存中
	 * */
	PARAM_QUERY("param_query"),
	/**
	 * 是否将查询条件设置到内存中(是)
	 * */
	PARAM_QUERY_TRUE("param_query_true"),
	
	//翻页工具栏样式名
	/**
	 * 默认样式，适用于非页签类页面
	 * */
	CLASSNAME_DEFAULT("dividePage"),
	/**
	 * 页签式页面工具栏样式
	 * */
	CLASSNAME_TAGBAR("dividePageTagbar");
	
	private String code;
	
	ThumbPageConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}

}
