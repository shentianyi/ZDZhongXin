package com.zd.csms.rbac.constants;

public enum ComplaintObjConstants {
	DEALER(1,"经销商"),
	FINANCIAL(2,"金融机构"),
	SUPERVISE(3,"监管员");
	
	private int code;
	private String name;
	
	private ComplaintObjConstants(int code, String name){
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
		for(ComplaintObjConstants item : ComplaintObjConstants.values()){
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
		for(ComplaintObjConstants item : ComplaintObjConstants.values()){
			if(code == item.getCode()){
				return item.getName();
			}
		}
		return null;
	}
}
