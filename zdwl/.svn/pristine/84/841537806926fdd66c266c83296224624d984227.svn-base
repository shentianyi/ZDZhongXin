package com.zd.csms.supervisorymanagement.contants;

public enum DataMailcostReceiverTypeContants {
	
	/**
	 * 业务专员
	 */
	BUSINESSOFFICER(1,"业务专员"),
	
	/**
	 * 金融机构
	 */
	BANK(2,"金融机构"),
	
	/**
	 * 经销商
	 */
	DEALER(3,"经销商"),
	
	/**
	 * 监管员
	 */
	SUPERVISORY(4,"监管员");
	
	private DataMailcostReceiverTypeContants(int code, String name) {
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
	
	public static  DataMailcostReceiverTypeContants getByCode(int code) {
		for(DataMailcostReceiverTypeContants contant:DataMailcostReceiverTypeContants.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
	
}
