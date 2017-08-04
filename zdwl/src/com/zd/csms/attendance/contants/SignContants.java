package com.zd.csms.attendance.contants;

/**
 * 签到状态
 *
 */
public enum SignContants {

	LDATE(1, "迟到"),
	LEAVE_EARLY(2, "早退"),
	ABSENTEEISM(3, "旷工"),
	NORMAL(4, "正常出勤");
	private int code;
	private String name;

	private SignContants(int code, String name) {
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
