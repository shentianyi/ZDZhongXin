package com.zd.csms.supervisorymanagement.contants;

public enum AssetsTypeContant {
	
	/**
	 * 电子设备
	 */
	ELECTRONICS(1,"电子设备"),
	/**
	 * 办公设备
	 */
	WORK(2,"办公设备"),
	
	/**
	 * 其它
	 */
	OTHER(3,"其它");
	
	
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	private AssetsTypeContant(int code, String name) {
		this.code = code;
		this.name = name;
	}
}
