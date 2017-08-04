package com.zd.csms.marketing.contants;

import com.zd.csms.rbac.constants.RoleConstants;

public enum SupervisorAttributeContant {
	/**
	 * 监管员属性 1：新上岗  
	 */
	SUPERVISORATTRIBUTE_XSG(1,"新上岗"),
	/**
	 * 监管员属性   2： 轮岗 
	 */
	SUPERVISORATTRIBUTE_LG(2," 轮岗"),
	/**
	 * 监管员属性 3： 绑定
	 */
	SUPERVISORATTRIBUTE_BD(3,"绑定"),
	/**
	 * 监管员属性 4：二次上岗 
	 */
	SUPERVISORATTRIBUTE_ECSG(4,"二次上岗"),
	/**
	 * 监管员属性 5：暂代
	 */
	SUPERVISORATTRIBUTE_TD(5,"暂代");
	
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	private SupervisorAttributeContant(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	/**
	 * 通过code获取name
	 * @param code
	 * @return String
	 * */
	public static String getName(int code){
		for(SupervisorAttributeContant item : SupervisorAttributeContant.values()){
			if(code==item.getCode()){
				return item.getName();
			}
		}
		return null;
	}
}
