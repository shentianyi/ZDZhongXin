package com.zd.csms.zxbank.web.bean;

public class CustomerFar {
	private String ecifCode;// 客户id
	private String custName;//客户名称
	public String getEcifCode() {
		return ecifCode;
	}
	public void setEcifCode(String ecifCode) {
		this.ecifCode = ecifCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	@Override
	public String toString() {
		return "cus [ecifCode=" + ecifCode + ", custName=" + custName + "]";
	}
	
	
}
