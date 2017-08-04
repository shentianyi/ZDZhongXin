package com.zd.csms.business.web.excel;

import java.util.Date;

public class DraftImportBean {
	private String agreement;//质押协议号
	private String bail_num;//保证金账号
	private String dealerName;//经销商名称
	private String bankName;//金融机构
	private String brand;//品牌
	
	private String draftNum;//汇票号码
	private Date billing_date;//开票日
	private Date due_date;//到期日
	private String billing_money;//开票金额
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	public String getBail_num() {
		return bail_num;
	}
	public void setBail_num(String bail_num) {
		this.bail_num = bail_num;
	}
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDraftNum() {
		return draftNum;
	}
	public void setDraftNum(String draftNum) {
		this.draftNum = draftNum;
	}
	public Date getBilling_date() {
		return billing_date;
	}
	public void setBilling_date(Date billing_date) {
		this.billing_date = billing_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public String getBilling_money() {
		return billing_money;
	}
	public void setBilling_money(String billing_money) {
		this.billing_money = billing_money;
	}
}
