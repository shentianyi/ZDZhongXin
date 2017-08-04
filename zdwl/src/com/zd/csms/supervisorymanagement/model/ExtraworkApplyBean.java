package com.zd.csms.supervisorymanagement.model;


public class ExtraworkApplyBean extends ExtraworkApplyVO{
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
	
}
