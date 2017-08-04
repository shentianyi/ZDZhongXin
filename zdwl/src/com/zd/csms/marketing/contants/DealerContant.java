package com.zd.csms.marketing.contants;

public enum DealerContant {
	/**
	 * 合作中
	 */
	COOPERATIONSTATE_IN(1,"合作中"),
	
	/**
	 * 未进店
	 */
	COOPERATIONSTATE_OUT(2,"未进店"),
	
	/**
	 * 撤店
	 */
	COOPERATIONSTATE_EXIT(3,"撤店"),
	
	
	ISCHANGESUPERVISEMONEY_YES(1,"是"),
	
	ISCHANGESUPERVISEMONEY_NO(2,"否");
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	private DealerContant(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
}
