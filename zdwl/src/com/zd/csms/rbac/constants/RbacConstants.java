package com.zd.csms.rbac.constants;

/**
 * RbacConstants常量类
 */
public enum RbacConstants {
	
	//节点类型
	/**
	 * 节点类型-目录
	 * */
	RESOURCE_NODE_TYPE_MENU(0),
	
	/**
	 * 节点类型-节点
	 * */
	RESOURCE_NODE_TYPE_NODE(1);
	
	private int code;
	
	private RbacConstants(int code){
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
}
 