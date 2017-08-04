package com.zd.csms.messagequartz.contants;

public enum MsgIsContants {
	
	NOREAD(1,"未读"),
	READ(2,"已读"),
	NOSHIELD(1,"未屏蔽"),
	SHIELD(2,"已屏蔽"),
	INFO(1,"信息提醒"),
	WARNING(2,"信息预警");
	
	private int code;
	private String name;
	private MsgIsContants(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
