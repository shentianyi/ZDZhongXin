package com.zd.csms.supervisory.contants;

public enum CheckStockCarOperationContants {
	//私自移动、私自销售、在途销售、在途移动、信誉额度、展厅
	WH(1,"本库"),
	TWO_WH(2,"二库"),
	MOVE(3,"移动"),
	OUT_WH(4,"出库"),
	SZYD(5,"私自移动"),
	SZXS(6,"私自销售"),
	ZTXS(7,"在途销售 "),
	ZTYD(8,"在途移动"),
	RYED(9,"荣誉额度"),
	ZT(10,"展厅");
	
	
	
	private CheckStockCarOperationContants(int code, String name) {
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
	
	public static  CheckStockCarOperationContants getByCode(String code) {
		for(CheckStockCarOperationContants contant:CheckStockCarOperationContants.values()){
			if(code.equals(contant.getCode())){
				return contant;
			}
		}
		return null;
	}
	
	
	public static  String getName(int code) {
		for(CheckStockCarOperationContants contant:CheckStockCarOperationContants.values()){
			if(code==contant.getCode()){
				return contant.getName();
			}
		}
		return "";
	}
	
}
