package com.zd.csms.windcontrol.constants;

/**
 * @author zhangzhicheng 报告列表完成状态
 */
public enum CompleteStatusConstants {
	PROGRESS(0, "未完成"), COMPLETE(1, "已完成");

	private int code;
	private String name;

	private CompleteStatusConstants(int code, String name) {
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
		for (CompleteStatusConstants item : CompleteStatusConstants.values()) {
			if (code == item.getCode()) {
				return item.getName();
			}
		}
		return null;
	}

}
