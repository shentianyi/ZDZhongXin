package com.zd.csms.finance.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_refund_invoice")
public class RefundInvoiceVO implements Serializable {

	/**
	 * 退款发票管理
	 */
	private static final long serialVersionUID = 2086469556669032307L;
	
	private int id;
	private int distribid;//经销商id
	private String financial_institution;//金融机构
	private String bank;//合作银行
	private String brand;//品牌
	private Date intime;//进店时间
	private String supervisionfee_standard;//监管费标准
	private String payment;//付费方式
	private String pay_standard;//缴费标准
	private Date pay_time;//缴费时间
	private String pay_money;//缴费金额
	private Date refund_time;//退费时间
	private String refund_money;//退费金额
	private String refund_des;//退费原因
	private int	isinvoice;//是否收到退款发票
	private Date invoice_time;//收票时间
	private String invoice_company;//开票单位
	private String invoice_type;//发票类型
	private String des;//备注
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
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date payTime) {
		pay_time = payTime;
	}
	public String getPay_money() {
		return pay_money;
	}
	public void setPay_money(String payMoney) {
		pay_money = payMoney;
	}
	public Date getRefund_time() {
		return refund_time;
	}
	public void setRefund_time(Date refundTime) {
		refund_time = refundTime;
	}
	public String getRefund_money() {
		return refund_money;
	}
	public void setRefund_money(String refundMoney) {
		refund_money = refundMoney;
	}
	public String getRefund_des() {
		return refund_des;
	}
	public void setRefund_des(String refundDes) {
		refund_des = refundDes;
	}
	public int getIsinvoice() {
		return isinvoice;
	}
	public void setIsinvoice(int isinvoice) {
		this.isinvoice = isinvoice;
	}
	public Date getInvoice_time() {
		return invoice_time;
	}
	public void setInvoice_time(Date invoiceTime) {
		invoice_time = invoiceTime;
	}
	public String getInvoice_company() {
		return invoice_company;
	}
	public void setInvoice_company(String invoiceCompany) {
		invoice_company = invoiceCompany;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoiceType) {
		invoice_type = invoiceType;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
