package com.zd.csms.dbsy.constants;

import com.zd.csms.constants.StateConstants;

/**
 * 紧急程度
 * */
public enum UrgencyLevelConstants {

	LEVEL_0(0,"无"),

	LEVEL_1(1,"低"),

	LEVEL_2(2,"中"),

	LEVEL_3(3,"高");
	
	private int code;
	private String name;
	
	private UrgencyLevelConstants(int code, String name){
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
	 * @return int
	 * */
	public static int getCode(String name){
		for(StateConstants state : StateConstants.values()){
			if(name.equals(state.getName())){
				return state.getCode();
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
		for(StateConstants state : StateConstants.values()){
			if(code==state.getCode()){
				return state.getName();
			}
		}
		return null;
	}
}
