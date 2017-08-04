package com.zd.csms.finance.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_open_invoice")
public class OpenInvoiceVO implements Serializable {

	/**
	 * 开票管理
	 */
	private static final long serialVersionUID = -3857099377357791376L;

	private int id;
	private int distribid;//经销商id
	private String financial_institution;//金融机构
	private String bank;//合作银行
	private String brand;//品牌
	private Date intime;//进店时间
	private String supervisionfee_standard;//监管费标准
	private String payment;//付费方式
	private String pay_standard;//缴费标准
	private int createuserid;//创建人
	private Date createdate;//创建时间
	private int upduserid;//修改人
	private Date upddate;//修改时间
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
	public int getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getUpduserid() {
		return upduserid;
	}
	public void setUpduserid(int upduserid) {
		this.upduserid = upduserid;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	
	
}
