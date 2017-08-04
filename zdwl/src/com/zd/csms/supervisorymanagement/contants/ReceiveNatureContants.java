package com.zd.csms.supervisorymanagement.contants;


public enum ReceiveNatureContants {
	
	/**
	 * 轮岗
	 */
	ROTATE_POSTSOCIETY(1,"轮岗"),
	
	/**
	 * 新入职
	 */
	ENTRY(2,"新入职"),
	
	/**
	 * 二次上岗
	 */
	SECOND_MISSION(3,"二次上岗");

	private ReceiveNatureContants(int code, String name) {
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
	
	public static  ReceiveNatureContants getByCode(int code) {
		for(ReceiveNatureContants contant:ReceiveNatureContants.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
	/**
	 * 通过code获取name
	 * @param code
	 * @return String
	 * */
	public static String getNameByCode(int code){
		for(ReceiveNatureContants item : ReceiveNatureContants.values()){
			if(code==item.getCode()){
				return item.getName();
			}
		}
		return null;
	}
}
