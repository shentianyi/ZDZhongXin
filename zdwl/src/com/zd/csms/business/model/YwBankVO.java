package com.zd.csms.business.model;

import com.zd.core.annotation.table;

@table(name="t_yw_bank")
public class YwBankVO {
	private int id;
	private int userId;
	private int bankId;
	private int isBindBrand;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public int getIsBindBrand() {
		return isBindBrand;
	}
	public void setIsBindBrand(int isBindBrand) {
		this.isBindBrand = isBindBrand;
	}
	
	
}
