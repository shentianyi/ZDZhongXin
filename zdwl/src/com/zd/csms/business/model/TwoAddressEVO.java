package com.zd.csms.business.model;

import java.util.Date;

public class TwoAddressEVO extends TwoAddressVO {
	private String dealername;// 经销商
	private String brand;// 品牌
	private String bank;// 金融机构

	public String getDealername() {
		return dealername;
	}

	public void setDealername(String dealername) {
		this.dealername = dealername;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "TwoAddressEVO [dealername=" + dealername + ", brand=" + brand
				+ ", bank=" + bank + "]";
	}

	
	
}
