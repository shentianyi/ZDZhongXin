package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 
 * 融资信息表
 */
@table(name="ZX_FINANCING")
public class Financing implements Serializable{

	private static final long serialVersionUID = 5609495847390714801L;

	private int fiId;//融资查询表主键
	private String fgLonentNo;//借款企业Id
	private String fgStDate;//融资起始日期 ，格式为YYYYMMDD
	private String fgEndDate;//融资结束日期，格式为YYYYMMDD
	private String fgLoanCode;//融资编号
	private String fgScftxNo;//放款批次号
	private BigDecimal fgLoanAmt;//融资金额
	private double fgBailRat;//保证金比例
	private double fgSlfcap;//自有资金比例
	private double fgFstblRat;//首付保证金可提货比例
	private String fgProcrt;//授信产品，银行承兑汇票
	private String fgBizMod;//业务模式，先票后货
	private String fgOperOrg;//经办行
	private Date fgCreateDate;//融资数据同步时间
	private Date fgUpdateDate;//融资同步更新时间
	
	public int getFiId() {
		return fiId;
	}
	public void setFiId(int fiId) {
		this.fiId = fiId;
	}
	public String getFgLonentNo() {
		return fgLonentNo;
	}
	public void setFgLonentNo(String fgLonentNo) {
		this.fgLonentNo = fgLonentNo;
	}
	public String getFgStDate() {
		return fgStDate;
	}
	public void setFgStDate(String fgStDate) {
		this.fgStDate = fgStDate;
	}
	public String getFgEndDate() {
		return fgEndDate;
	}
	public void setFgEndDate(String fgEndDate) {
		this.fgEndDate = fgEndDate;
	}
	public String getFgLoanCode() {
		return fgLoanCode;
	}
	public void setFgLoanCode(String fgLoanCode) {
		this.fgLoanCode = fgLoanCode;
	}
	public String getFgScftxNo() {
		return fgScftxNo;
	}
	public void setFgScftxNo(String fgScftxNo) {
		this.fgScftxNo = fgScftxNo;
	}
	public BigDecimal getFgLoanAmt() {
		return fgLoanAmt;
	}
	public void setFgLoanAmt(BigDecimal fgLoanAmt) {
		this.fgLoanAmt = fgLoanAmt;
	}
	public double getFgBailRat() {
		return fgBailRat;
	}
	public void setFgBailRat(double fgBailRat) {
		this.fgBailRat = fgBailRat;
	}
	public double getFgSlfcap() {
		return fgSlfcap;
	}
	public void setFgSlfcap(double fgSlfcap) {
		this.fgSlfcap = fgSlfcap;
	}
	public double getFgFstblRat() {
		return fgFstblRat;
	}
	public void setFgFstblRat(double fgFstblRat) {
		this.fgFstblRat = fgFstblRat;
	}
	public String getFgProcrt() {
		return fgProcrt;
	}
	public void setFgProcrt(String fgProcrt) {
		this.fgProcrt = fgProcrt;
	}
	public String getFgBizMod() {
		return fgBizMod;
	}
	public void setFgBizMod(String fgBizMod) {
		this.fgBizMod = fgBizMod;
	}
	public String getFgOperOrg() {
		return fgOperOrg;
	}
	public void setFgOperOrg(String fgOperOrg) {
		this.fgOperOrg = fgOperOrg;
	}
	public Date getFgCreateDate() {
		return fgCreateDate;
	}
	public void setFgCreateDate(Date fgCreateDate) {
		this.fgCreateDate = fgCreateDate;
	}
	public Date getFgUpdateDate() {
		return fgUpdateDate;
	}
	public void setFgUpdateDate(Date fgUpdateDate) {
		this.fgUpdateDate = fgUpdateDate;
	}
	
	@Override
	public String toString() {
		return "Financing [fiId=" + fiId + ", fgLonentNo=" + fgLonentNo + ", fgStDate=" + fgStDate + ", fgEndDate=" + fgEndDate + ", fgLoanCode="
				+ fgLoanCode + ", fgScftxNo=" + fgScftxNo + ", fgLoanAmt=" + fgLoanAmt + ", fgBailRat=" + fgBailRat + ", fgSlfcap=" + fgSlfcap
				+ ", fgFstblRat=" + fgFstblRat + ", fgProcrt=" + fgProcrt + ", fgBizMod=" + fgBizMod + ", fgOperOrg=" + fgOperOrg + ", fgCreateDate="
				+ fgCreateDate + ", fgUpdateDate=" + fgUpdateDate + "]";
	}
	
}
