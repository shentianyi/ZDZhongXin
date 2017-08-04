package com.zd.csms.supervisorymanagement.contants;

public enum CurrentDealerTypeContants {
	
	/**
	 * 请假申请
	 */
	LEAVEAPPLY(1,"请假申请"),
	
	/**
	 * 加班申请
	 */
	EXTRAWORKAPPLY(2,"加班申请"),
	
	/**
	 * 辞职申请
	 */
	RESIGNAPPLY(3,"辞职申请");


	
	private CurrentDealerTypeContants(int code, String name) {
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
	
	public static  CurrentDealerTypeContants getByCode(int code) {
		for(CurrentDealerTypeContants contant:CurrentDealerTypeContants.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
	
}
