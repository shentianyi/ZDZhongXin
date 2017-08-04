package com.zd.csms.supervisorymanagement.contants;

public enum HandoverNatureContants {
	
	/**
	 * 辞职
	 */
	RESIGN(1,"辞职"),
	
	/**
	 * 辞退
	 */
	DISMISS(2,"辞退"),
	
	/**
	 * 正常轮岗
	 */
	NORMAL_ROTATE_POSTSOCIETY(3,"正常轮岗"),

	/**
	 * 投诉轮岗
	 */
	COMPLAIN_RECRUITMENT(4,"投诉轮岗"),
	
	/*
	 * 需求46 -- 交接性质增加“撤店”选项
	*/
	DismantLingShop(5,"撤店");
	
	private HandoverNatureContants(int code, String name) {
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
	
	public static  HandoverNatureContants getByCode(int code) {
		for(HandoverNatureContants contant:HandoverNatureContants.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
	
}
