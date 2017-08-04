package com.zd.csms.finance.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_open_invoice_list")
public class OpenInvoiceListVO implements Serializable {

	/**
	 * 开票管理列表
	 */
	private static final long serialVersionUID = 1834088678073214199L;
	private int id;
	private int open_invoice_id;//开票管理id
	private Date pay_time;//缴费时间
	private String pay_money;//缴费金额
	private int isinvoice;//是否开票
	private String invoice_type;//开票方式
	private int isTransfer;//是否交接
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
	public int getOpen_invoice_id() {
		return open_invoice_id;
	}
	public void setOpen_invoice_id(int openInvoiceId) {
		open_invoice_id = openInvoiceId;
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
	public int getIsinvoice() {
		return isinvoice;
	}
	public void setIsinvoice(int isinvoice) {
		this.isinvoice = isinvoice;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoiceType) {
		invoice_type = invoiceType;
	}
	public int getIsTransfer() {
		return isTransfer;
	}
	public void setIsTransfer(int isTransfer) {
		this.isTransfer = isTransfer;
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
