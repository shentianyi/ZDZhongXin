package com.zd.csms.windcontrol.constants;
/**
 * @author zhangzhicheng
 *巡检报告-检查过程中发现的问题及监管员优/缺点
 */
public enum RecordTypeConstants {

	/**
	 * 发现问题
	 */
	PROBLEM(1, "发现问题"),

	/**
	 * 监管员优点/缺点
	 */
	MERITSANDDEMERITS(2, "监管员优点/缺点");


	private int code;
	private String name;

	private RecordTypeConstants(int code, String name) {
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
	 * 通过name获取code
	 * 
	 * @param name
	 * @return int
	 */
	public static int getCode(String name) {
		for (RecordTypeConstants item : RecordTypeConstants.values()) {
			if (name.equals(item.getName())) {
				return item.getCode();
			}
		}
		return -1;
	}

	/**
	 * 通过code获取name
	 * 
	 * @param code
	 * @return String
	 */
	public static String getName(int code) {
		for (RecordTypeConstants item : RecordTypeConstants.values()) {
			if (code == item.getCode()) {
				return item.getName();
			}
		}
		return null;
	}

}
