package com.zd.tools.taglib.table.constants;

public enum TableTagConstants {
	
	TABLEMODEL("TABLEMODEL"),
	ROWMODEL("ROWMODEL"),
	ROWNUMBER("ROWNUMBER");
	
	private String code;
	TableTagConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}

}
