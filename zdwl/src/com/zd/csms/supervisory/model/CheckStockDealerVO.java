package com.zd.csms.supervisory.model;

import com.zd.core.annotation.table;

@table(name="T_CHECK_STOCK_DEALER")
public class CheckStockDealerVO {
	private int id;
	private int dealerId;//经销商主键ID
	private int checkStockId;//查库频次主键ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getCheckStockId() {
		return checkStockId;
	}
	public void setCheckStockId(int checkStockId) {
		this.checkStockId = checkStockId;
	}
}
