package com.zd.csms.supervisory.contants;

public enum CarOperationContants {
	
	APPROVE_STATUS_ON("1","审批中"),
	APPROVE_STATUS_YES("2","审批通过"),
	APPROVE_STATUS_NOT("3","审批未通过"),
	APPLY_TYPE_IN("2116","入库"),
	APPLY_TYPE_OUT("2122","出库");
	
	private CarOperationContants(String code, String name) {
		this.code = code;
		this.name = name;
	}
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	
	public static  CarOperationContants getByCode(String code) {
		for(CarOperationContants contant:CarOperationContants.values()){
			if(code.equals(contant.getCode())){
				return contant;
			}
		}
		return null;
	}
	
}
