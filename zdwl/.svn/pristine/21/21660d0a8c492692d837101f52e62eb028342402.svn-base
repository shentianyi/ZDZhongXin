package com.zd.csms.supervisorymanagement.contants;

public enum AfterHandoverNatureContants {
	
	/**
	 * 属地
	 */
	NATIVE_PLACE(1,"属地"),
	
	/**
	 * 外派
	 */
	DISPATCH(2,"外派");
	
	private AfterHandoverNatureContants(int code, String name) {
		this.code = code;
		this.name = name;
	}
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static  AfterHandoverNatureContants getByCode(int code) {
		for(AfterHandoverNatureContants contant:AfterHandoverNatureContants.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
	
}
