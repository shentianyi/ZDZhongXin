package com.zd.csms.supervisory.contants;

public enum RegisteredStatusContants {
	
	/**
	 * 户口性质：农业户口
	 */
	AGRICULTURE(1,"农业户口"),
	
	/**
	 *  户口性质：城镇户口
	 */
	TOWN(2,"城镇户口");
	
	
	private RegisteredStatusContants(int code, String name) {
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
	
	public static  String getNameByCode(int code) {
		for(RegisteredStatusContants contant:RegisteredStatusContants.values()){
			if(code==contant.getCode()){
				return contant.getName();
			}
		}
		return "";
	}
	
}
