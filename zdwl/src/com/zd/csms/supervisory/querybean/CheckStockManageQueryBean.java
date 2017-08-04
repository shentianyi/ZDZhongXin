package com.zd.csms.supervisory.querybean;

import java.util.Date;

public class CheckStockManageQueryBean {
	private int id;
	private int dealerId;//经销商ID
	private String bankName;//金融机构名称
	private String brandName;//品牌名称
	private String dealerAttr;//经销商属性
	private Date beginTime;//开始时间
	private Date endTime;//结束时间
	private int checkStockType;//查库方式 1：手工查库，2：设备查库，3：全部
	
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDealerAttr() {
		return dealerAttr;
	}

	public void setDealerAttr(String dealerAttr) {
		this.dealerAttr = dealerAttr;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getCheckStockType() {
		return checkStockType;
	}

	public void setCheckStockType(int checkStockType) {
		this.checkStockType = checkStockType;
	}
}
