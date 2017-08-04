package com.zd.csms.finance.model;

import java.util.Date;

public class OpenInvoiceQueryVO {

	private int id;
	private int distribid;//经销商id
	private String financial_institution;//金融机构
	private String bank;//合作银行
	private String brand;//品牌
	private Date intime;//进店时间
	private String supervisionfee_standard;//监管费标准
	private String payment;//付费方式
	private String pay_standard;//缴费标准
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDistribid() {
		return distribid;
	}
	public void setDistribid(int distribid) {
		this.distribid = distribid;
	}
	public String getFinancial_institution() {
		return financial_institution;
	}
	public void setFinancial_institution(String financialInstitution) {
		financial_institution = financialInstitution;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public String getSupervisionfee_standard() {
		return supervisionfee_standard;
	}
	public void setSupervisionfee_standard(String supervisionfeeStandard) {
		supervisionfee_standard = supervisionfeeStandard;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPay_standard() {
		return pay_standard;
	}
	public void setPay_standard(String payStandard) {
		pay_standard = payStandard;
	}
	
	
	
}
