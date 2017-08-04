package com.zd.tools.validate.constants;

/**
 * 数据类型常量
 * */
public enum DataTypeConstants {
	
	/**
	 * 字符串
	 * */
	STRING("string"),
	/**
	 * 整数
	 * */
	NUMBER("number"),
	/**
	 * 正整数
	 * */
	POSITIVE_INTEGER("positive_number"),
	/**
	 * 日期(校验到日)
	 * */
	DATE("date"),
	/**
	 * 日期(提交时补零)
	 * */
	TIMESTAMP("timestamp");
	
	private String code;
	
	private DataTypeConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public String getCode(int a,int b){
		if(code.equals("number")){
			return code+"("+a+","+b+")";
		}
		return code;
	}
	
}
