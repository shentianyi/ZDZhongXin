package com.zd.csms.supervisorymanagement.contants;

public enum ResignReasonContants {
	
	/**
	 * 个人原因
	 */
	PERSONAL_REASON(1,"个人原因"),
	
	/**
	 * 换工作
	 */
	CHANGE_JOB(2,"换工作"),
	
	/**
	 * 工作地点原因
	 */
	COMPANY_ADDRESS(3,"工作地点原因");

	
	private ResignReasonContants(int code, String name) {
		this.code = code;
		this.name = name;
	}
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static  ResignReasonContants getByCode(int code) {
		for(ResignReasonContants contant:ResignReasonContants.values()){
			if(code==contant.getCode()){
				return contant;
			}
		}
		return null;
	}
	
}
