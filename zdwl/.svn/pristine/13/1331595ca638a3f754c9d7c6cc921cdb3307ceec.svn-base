package com.zd.csms.constants;

/**
 * 业务状态常量枚举
 * 业务状态四种常量值及对应的中文名
 * */
public enum StateConstants {
	
	/**
	 * 未启用
	 * */
	UNDONE(0,"未启用"),
	/**
	 * 启用
	 * */
	USING(1,"启用"),
	/**
	 * 停用
	 * */
	UNUSED(2,"停用"),
	/**
	 * 注销
	 * */
	DELETE(3,"注销");

	
	
	
	private int code;
	private String name;
	
	private StateConstants(int code, String name){
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
