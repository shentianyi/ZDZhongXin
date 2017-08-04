package com.zd.csms.supervisorymanagement.model;


public class ResignApplyBean extends ResignApplyVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 经销商名称
	 */
	private String dealerName;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 经销商ID
	 */
	private String dealerId;
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
}
