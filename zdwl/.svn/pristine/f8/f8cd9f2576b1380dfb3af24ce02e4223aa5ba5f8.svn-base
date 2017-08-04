package com.zd.csms.marketing.contants;


public enum ApprovalContant {
	/**
	 * 审批通过
	 */
	APPROVAL_PASS(1,"审批通过"),
	
	/**
	 * 审批不通过
	 */
	APPROVAL_NOPASS(2,"审批不通过"),
	
	/**
	 * 正在审批
	 */
	APPROVAL_WAIT(3,"正在审批"),
	
	/**
	 * 未提交
	 */
	APPROVAL_NOT_SUBMIT(0,"未提交"),
	
	
	/**
	 * 未保存
	 */
	APPROVAL_NOT_SAVE(-1,"未通知");
	
	
	
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	private ApprovalContant(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static  ApprovalContant getByCode(int code) {
		for(ApprovalContant contant:ApprovalContant.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
}
