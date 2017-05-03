package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 
 * 融资信息表
 */
@table(name="ZX_FINANCING")
public class Financing {
	private int fiId;//融资查询表主键
	private String fgLonentNo;//借款企业Id
	private String fgLoncpName;//借款企业名称  
	private String fgStDate;//融资起始日 ，格式为YYYYMMDD
	private String fgEndDate;//融资到期日，格式为YYYYMMDD
	private String fgLoanCode;//融资编号
	private String fgScftxNo;//放款批次号
	private String fgLoanAmt;//融资金额
	private String fgBailRat;//首付保证金比例
	private String fgSlfcap;//自有资金比例
	private String fgFstblRat;//首付保证金可提货比例
	private String fgProcrt;//授信产品，银行承兑汇票
	private String fgBizMod;//业务模式，先票后货
	private String fgOperOrg;//经办行
	private String fgagtid;//协议编号
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
	public String getFgLoanAmt() {
		return fgLoanAmt;
	}
	public void setFgLoanAmt(String fgLoanAmt) {
		this.fgLoanAmt = fgLoanAmt;
	}
	public String getFgBailRat() {
		return fgBailRat;
	}
	public void setFgBailRat(String fgBailRat) {
		this.fgBailRat = fgBailRat;
	}
	public String getFgSlfcap() {
		return fgSlfcap;
	}
	public void setFgSlfcap(String fgSlfcap) {
		this.fgSlfcap = fgSlfcap;
	}
	public String getFgFstblRat() {
		return fgFstblRat;
	}
	public void setFgFstblRat(String fgFstblRat) {
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
	public String getFgLoncpName() {
		return fgLoncpName;
	}
	public void setFgLoncpName(String fgLoncpName) {
		this.fgLoncpName = fgLoncpName;
	}
	public String getFgagtid() {
		return fgagtid;
	}
	public void setFgagtid(String fgagtid) {
		this.fgagtid = fgagtid;
	}
	@Override
	public String toString() {
		return "Financing [fiId=" + fiId + ", fgLonentNo=" + fgLonentNo
				+ ", fgLoncpName=" + fgLoncpName + ", fgStDate=" + fgStDate
				+ ", fgEndDate=" + fgEndDate + ", fgLoanCode=" + fgLoanCode
				+ ", fgScftxNo=" + fgScftxNo + ", fgLoanAmt=" + fgLoanAmt
				+ ", fgBailRat=" + fgBailRat + ", fgSlfcap=" + fgSlfcap
				+ ", fgFstblRat=" + fgFstblRat + ", fgProcrt=" + fgProcrt
				+ ", fgBizMod=" + fgBizMod + ", fgOperOrg=" + fgOperOrg
				+ ", fgagtid=" + fgagtid + ", fgCreateDate=" + fgCreateDate
				+ ", fgUpdateDate=" + fgUpdateDate + "]";
	}
	
	
	
}
