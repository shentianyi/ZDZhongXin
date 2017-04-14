package com.zd.tools.taglib.form;

public enum FormTagConstants {

	KEY_SET_UPLOAD_SCRIPT("UploadScript"),
	KEY_SET_DYNAMIC_SCRIPT("DynamicScript"),
	KEY_SET_CALENDAR_SCRIPT("CalendarScript"),
	KEY_SET_EDITOR_SCRIPT("EditorScript"),
	KEY_SET_DHTMLX_COMMON_SCRIPT("DhtmlxcommonScript"),
	KEY_SET_DHTMLX_COMBO_SCRIPT("DhtmlxcomboScript"),
	
	VALUE_SET_SCRIPT("true");

	private String code;
	
	private FormTagConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
}
