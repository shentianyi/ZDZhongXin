package com.zd.csms.marketing.model;

import java.util.Date;

public class DealerQueryVO {
	private int id;
	private String dealerName;
	private Integer[] ids;
	private int cooperationState;//合作状态
	
	private String brand;//品牌名称
	private Date startInDate;//进店时间
	private Date endInDate;//进店时间
	
	private String dealerNature;//经销商性质
	private int payMode;//支付方式
	
	private String bankName;//银行名称
	
	private String userName;
	
	private int ddorbd;//1:绑定 2：单店
	
	private int isArrears; // 是否欠费1：是 2：不是
	
	private int currClientType;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public int getCooperationState() {
		return cooperationState;
	}

	public void setCooperationState(int cooperationState) {
		this.cooperationState = cooperationState;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getStartInDate() {
		return startInDate;
	}

	public void setStartInDate(Date startInDate) {
		this.startInDate = startInDate;
	}

	public Date getEndInDate() {
		return endInDate;
	}

	public void setEndInDate(Date endInDate) {
		this.endInDate = endInDate;
	}

	public String getDealerNature() {
		return dealerNature;
	}

	public void setDealerNature(String dealerNature) {
		this.dealerNature = dealerNature;
	}

	public int getPayMode() {
		return payMode;
	}

	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getDdorbd() {
		return ddorbd;
	}

	public void setDdorbd(int ddorbd) {
		this.ddorbd = ddorbd;
	}

	public int getIsArrears() {
		return isArrears;
	}

	public void setIsArrears(int isArrears) {
		this.isArrears = isArrears;
	}

	public int getCurrClientType() {
		return currClientType;
	}

	public void setCurrClientType(int currClientType) {
		this.currClientType = currClientType;
	}

	

}
