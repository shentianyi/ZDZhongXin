package com.zd.csms.supervisory.model;

public class CheckStockManageBean extends CheckStockManageVO{

	private static final long serialVersionUID = 1L;
	/**
	 * 经销商名称
	 */
	private String dealerName;
	/**
	 * 金融机构名称
	 */
	private String bankName;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 经销商属性
	 */
	private String dealerAttr;
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
	public String getDealerAttr() {
		return dealerAttr;
	}
	public void setDealerAttr(String dealerAttr) {
		this.dealerAttr = dealerAttr;
	}
}
