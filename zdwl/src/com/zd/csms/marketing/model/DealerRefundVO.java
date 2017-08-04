package com.zd.csms.marketing.model;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 经销商退费流转单
 * @author licheng
 *
 */
@table(name="market_dealer_refund")
public class DealerRefundVO {
	private int id;
	private int dealerId;//经销商主键ID
	private String actualPayment;//实收监管费
	private Date actualPaymentDate;//实收监管费缴费时间
	private String refundMoney;//退还监管费（元/年）
	private Date refundDate;//退还时间
	private int isInvoice;//是否开发票 1：是 2：不开
	private Date invoiceDate;//开票时间
	private String invoiceType;//发票类型
	private String invoiceCompany;//开票单位
	private String refundResource;//退费原因
	private Date applyDate;//申请时间
	private int nextApproval;//下一个审批角色
	private int approvalState;//审批状态
	
	private Integer createUserId;//创建人id
	private Integer lastModifyUserId;//最后修改人id
	private Date createDate;//创建时间
	private Date lastModifyDate;//最后修改时间
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
	public String getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public int getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(int isInvoice) {
		this.isInvoice = isInvoice;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceCompany() {
		return invoiceCompany;
	}
	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}
	public String getRefundResource() {
		return refundResource;
	}
	public void setRefundResource(String refundResource) {
		this.refundResource = refundResource;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public int getNextApproval() {
		return nextApproval;
	}
	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public String getActualPayment() {
		return actualPayment;
	}
	public void setActualPayment(String actualPayment) {
		this.actualPayment = actualPayment;
	}
	public Date getActualPaymentDate() {
		return actualPaymentDate;
	}
	public void setActualPaymentDate(Date actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}

	public Integer getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(Integer lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}

	
	
}
