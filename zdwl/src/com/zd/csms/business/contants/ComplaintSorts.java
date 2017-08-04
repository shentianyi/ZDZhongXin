package com.zd.csms.business.contants;

/**
 * 投诉问题分类
 * @author licheng
 *
 */
public enum ComplaintSorts {
	/**
	 * 监管员问题
	 */
	SUPERVISOR(1,"监管员问题"),
	
	/**
	 * 业务操作问题
	 */
	BUSINESS(2,"业务操作问题"),
	
	/**
	 * 监管费用问题
	 */
	SUPERVISEEXPENSE(3,"监管费用问题"),
	
	/**
	 * 监管公司人员问题
	 */
	PERSON(4,"监管公司人员问题"),
	
	/**
	 * 设备问题
	 */
	EQUIPMENT(5,"设备问题");
	
	
	private int code;
	private String name;
	private ComplaintSorts(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
