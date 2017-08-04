package com.zd.csms.windcontrol.constants;

/**
 * @author zhangzhicheng 编辑状态
 */
public enum EditStatusConstants {
	ADD(0, "未编辑"), EDIT(1, "已编辑"), SUBMIT(2, "已提交");

	private int code;
	private String name;

	private EditStatusConstants(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	
	/**
	 * 通过code获取name
	 * 
	 * @param code
	 * @return String
	 */
	public static String getName(int code) {
		for (EditStatusConstants item : EditStatusConstants.values()) {
			if (code == item.getCode()) {
				return item.getName();
			}
		}
		return null;
	}

}
