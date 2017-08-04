package com.zd.csms.rbac.constants;

public enum StatusConstants {
	/**
	 * 发起人发起已提交
	 */
	UNCOMMIT("1","未提交"),
	/**
	 * 发起人发起已提交
	 */
	ALREADYSEND("2","已发送"),
	/**
	 * 目标提交
	 */
	CORRECTION("3","已修正"),
	/**
	 * 发起人再次提交 流程结束
	 */
	SUCCESS("4","已完成");
	
	private String code;
	private String name;
	
	private StatusConstants(String code, String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode(){
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
	public static String getCode(String name){
		for(StatusConstants item : StatusConstants.values()){
			if(name.equals(item.getName())){
				return item.getCode();
			}
		}
		return null;
	}
	
	/**
	 * 通过code获取name
	 * @param code
	 * @return String
	 * */
	public static String getName(String code){
		for(StatusConstants item : StatusConstants.values()){
			if(code.equals(item.getCode())){
				return item.getName();
			}
		}
		return null;
	}
}
