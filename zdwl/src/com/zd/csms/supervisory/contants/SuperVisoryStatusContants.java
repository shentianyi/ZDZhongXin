package com.zd.csms.supervisory.contants;

public enum SuperVisoryStatusContants {
	
	ON_WAY(1,"在途"),
	IN_STORE(2,"在库"),
	SALE_NO_PAY(3,"销售未还款"),
	OUT_STORE(4,"出库"),
	PRIVATE_SALE(5,"私自售卖车辆"),
	PRIVATE_MOVE(6,"私自移动车辆"),;
	
	private SuperVisoryStatusContants(int code, String name) {
		this.code = code;
		this.name = name;
	}
	private int code;
	private String name;
	public String getName() {
		return name;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

}
