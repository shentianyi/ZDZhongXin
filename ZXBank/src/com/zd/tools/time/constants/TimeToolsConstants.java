package com.zd.tools.time.constants;

public enum TimeToolsConstants {
	
	DB("db"),
	SYSTEM("system");
	
	private String code;
	
	private TimeToolsConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}

}
