package com.zd.csms.business.model;

import com.zd.core.annotation.table;

@table(name="t_yw_bank_brand")
public class YwBankBrandVO {
	private int id;
	private int ywBankId;
	private int brandId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYwBankId() {
		return ywBankId;
	}
	public void setYwBankId(int ywBankId) {
		this.ywBankId = ywBankId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}	
}
