package com.zd.csms.bank.contants;

public enum BankContants {
	
	/**
	 * 银行类型：主行
	 */
	MAIN_BANK(1,"总行"),
	
	/**
	 * 银行类型：分行
	 */
	BRANCH_BANK(2,"分行"),
	
	/**
	 * 银行类型：支行
	 */
	SUBBRANCH_BANK(3,"支行");
	
	
	private BankContants(int code, String name) {
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
	

	
}
