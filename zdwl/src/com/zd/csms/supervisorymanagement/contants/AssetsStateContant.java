package com.zd.csms.supervisorymanagement.contants;

public enum AssetsStateContant {
	
	/**
	 * 使用中
	 */
	USEING(1,"使用中"),
	/**
	 * 闲置
	 */
	UNUSED(2,"闲置"),
	
	/**
	 * 报废
	 */
	SCRAP(3,"报废"),
	/**
	 * 待处置
	 */
	UNMANAGE(4,"待处置"),
	/**
	 * 损毁待报废
	 */
	DAMAGE(5,"损毁待报废");
	
	
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	private AssetsStateContant(int code, String name) {
		this.code = code;
		this.name = name;
	}
}
