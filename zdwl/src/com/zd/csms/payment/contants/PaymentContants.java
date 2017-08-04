package com.zd.csms.payment.contants;

import com.zd.csms.base.code.constants.BusinessCodeConstants;

/**
 * 薪酬审批状态
 */
public enum PaymentContants {

	UNCOMMIT(0, "未提交"),
	COMPILE(1,"编辑中"),
	UNCHECK(2, "经理审核"),
	MINISTER_CHECK(3, "部长审核"),
	FINANCE_ACCOUNTING_CHECK(7, "财务会计审核"),
	FINANCE_CHECK(4, "财务审核"),
	MAJORDOMO_CHECK(5, "总监审核"),
	CHECK_PASS(6, "审核通过");
	
	
	private int code;
	private String name;

	private PaymentContants(int code, String name) {
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
	
	/**
	 * 通过name获取code
	 * @param name
	 * @return int
	 * */
	public static int getCode(String name){
		for(BusinessCodeConstants businessCode : BusinessCodeConstants.values()){
			if(name.equals(businessCode.getName())){
				return businessCode.getCode();
			}
		}
		return -1;
	}
	
	/**
	 * 通过code获取name
	 * @param code
	 * @return String
	 * */
	public static String getName(int code){
		for(BusinessCodeConstants businessCode : BusinessCodeConstants.values()){
			if(code==businessCode.getCode()){
				return businessCode.getName();
			}
		}
		return null;
	}
}
