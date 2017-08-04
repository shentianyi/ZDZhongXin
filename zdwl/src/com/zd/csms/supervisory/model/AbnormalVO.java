package com.zd.csms.supervisory.model;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 质押监管异常数据统计表
 * @author licheng
 *
 */
@table(name="T_SUPERVISOR_ABNORMAL")
public class AbnormalVO {
	private int id;
	private int business;//业务专员Id
	private int dealerId;//经销商主键ID
	private String totalStock;//总库存
	private String jyAnomalies;//经营异常
	
	private String shCarNumber;//私售_车架号
	private String shCarMoney;//私售_金额
	private String shContinuedDay;//私售_持续天数
	
	private String xsCarNumber;//销售未还款_车架号
	private String xsCarMoney;//销售未还款_金额
	private String xsContinuedDay;//销售未还款_持续天数
	
	private String syCarNumber;//私移_车架号
	private String syCarMoney;//私移_金额
	private String syContinuedDay;//私移_持续天数
	
	private String ycproportion;//异常占比
	private Date earliestInvoice;// 最早开票日
	private String amountnotfilled;//未压满金额
	
	private Date earliestExpire;//最早到期日
	private String outstandingAmount;//未结清金额
	
	private Date gjDate;//跟进时间
	private String progress;//进展
	private String remark;//备注
	
	private int createUser;//创建人用户Id
	private Date createDate;//创建时间
	private int approvalState;//审批状态
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusiness() {
		return business;
	}
	public void setBusiness(int business) {
		this.business = business;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(String totalStock) {
		this.totalStock = totalStock;
	}
	public String getJyAnomalies() {
		return jyAnomalies;
	}
	public void setJyAnomalies(String jyAnomalies) {
		this.jyAnomalies = jyAnomalies;
	}
	public String getShCarNumber() {
		return shCarNumber;
	}
	public void setShCarNumber(String shCarNumber) {
		this.shCarNumber = shCarNumber;
	}
	public String getShCarMoney() {
		return shCarMoney;
	}
	public void setShCarMoney(String shCarMoney) {
		this.shCarMoney = shCarMoney;
	}
	public String getShContinuedDay() {
		return shContinuedDay;
	}
	public void setShContinuedDay(String shContinuedDay) {
		this.shContinuedDay = shContinuedDay;
	}
	public String getXsCarNumber() {
		return xsCarNumber;
	}
	public void setXsCarNumber(String xsCarNumber) {
		this.xsCarNumber = xsCarNumber;
	}
	public String getXsCarMoney() {
		return xsCarMoney;
	}
	public void setXsCarMoney(String xsCarMoney) {
		this.xsCarMoney = xsCarMoney;
	}
	public String getXsContinuedDay() {
		return xsContinuedDay;
	}
	public void setXsContinuedDay(String xsContinuedDay) {
		this.xsContinuedDay = xsContinuedDay;
	}
	public String getSyCarNumber() {
		return syCarNumber;
	}
	public void setSyCarNumber(String syCarNumber) {
		this.syCarNumber = syCarNumber;
	}
	public String getSyCarMoney() {
		return syCarMoney;
	}
	public void setSyCarMoney(String syCarMoney) {
		this.syCarMoney = syCarMoney;
	}
	public String getSyContinuedDay() {
		return syContinuedDay;
	}
	public void setSyContinuedDay(String syContinuedDay) {
		this.syContinuedDay = syContinuedDay;
	}
	public String getYcproportion() {
		return ycproportion;
	}
	public void setYcproportion(String ycproportion) {
		this.ycproportion = ycproportion;
	}
	public Date getEarliestInvoice() {
		return earliestInvoice;
	}
	public void setEarliestInvoice(Date earliestInvoice) {
		this.earliestInvoice = earliestInvoice;
	}
	public String getAmountnotfilled() {
		return amountnotfilled;
	}
	public void setAmountnotfilled(String amountnotfilled) {
		this.amountnotfilled = amountnotfilled;
	}
	public Date getEarliestExpire() {
		return earliestExpire;
	}
	public void setEarliestExpire(Date earliestExpire) {
		this.earliestExpire = earliestExpire;
	}
	public String getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public Date getGjDate() {
		return gjDate;
	}
	public void setGjDate(Date gjDate) {
		this.gjDate = gjDate;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
