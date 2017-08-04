package com.zd.tools.dynamic;

/**
 * 动态新增行状态属性常量类
 * */
public enum DynamicStateFlagConstants {
	
	/**
	 * 默认状态
	 * */
	NORMAL(0),
	/**
	 * 新增状态
	 * */
	ADD(1),
	/**
	 * 修改状态
	 * */
	UPDATE(2),
	/**
	 * 删除状态
	 * */
	DELETE(3),
	/**
	 * 模板行
	 * */
	TEMPLET(4);
	
	private int code;
	
	private DynamicStateFlagConstants(int code){
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}

}
