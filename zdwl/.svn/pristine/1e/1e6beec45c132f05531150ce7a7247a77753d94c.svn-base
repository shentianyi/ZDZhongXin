package com.zd.csms.rbac.constants;


public enum PhoneTypeConstants {
	BUSINESSPROBLE(1,"业务问题"),
	MARKETPROBLE(2,"市场问题"),
	WINDPROBLE(3,"风控问题"),
	COMPLAINTSUPERVISE(4,"投诉监管员"),
	COMPLAINTHEADQUARTERS(5,"投诉总部人员"),
	ELSE(6,"其他");
	
	private int code;
	private String name;
	
	private PhoneTypeConstants(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * 通过name获取code
	 * @param name
	 * @return String
	 * */
	public static int getCode(String name){
		for(PhoneTypeConstants item : PhoneTypeConstants.values()){
			if(name.equals(item.getName())){
				return item.getCode();
			}
		}
		return -1;
	}
	
	/**
	 * 通过code获取name
	 * @param code
	 * @return String
	 * */
	public static String getName(int code){
		for(PhoneTypeConstants item : PhoneTypeConstants.values()){
			if(code == item.getCode()){
				return item.getName();
			}
		}
		return null;
	}
}
