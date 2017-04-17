package com.zd.csms.zxbank.web.bean;

/**
 * 
 * 融资信息表
 */
public class FinancingFar {
	private String loanstDate;//融资起始日期 ，格式为YYYYMMDD loanstDate
	private String loanendDate;//融资结束日期，格式为YYYYMMDD loanendDate
	private String loanCode;//融资编号loanCode
	private String scftxNo;//放款批次号scftxNo
	private String loanAmt;//融资金额loanAmt
	private String bailRat;//保证金比例bailRat
	private String slfcapRat;//自有资金比例slfcapRat
	private String fstblRat;//首付保证金可提货比例fstblRat
	private String procrt;//授信产品，银行承兑汇票 procrt
	private String bizMod;//业务模式，先票后货bizMod
	private String operOrg;//经办行 operOrg

	public String getLoanstDate() {
		return loanstDate;
	}

	public void setLoanstDate(String loanstDate) {
		this.loanstDate = loanstDate;
	}

	public String getLoanendDate() {
		return loanendDate;
	}

	public void setLoanendDate(String loanendDate) {
		this.loanendDate = loanendDate;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getScftxNo() {
		return scftxNo;
	}

	public void setScftxNo(String scftxNo) {
		this.scftxNo = scftxNo;
	}

	public String getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

	public String getBailRat() {
		return bailRat;
	}

	public void setBailRat(String bailRat) {
		this.bailRat = bailRat;
	}

	public String getSlfcapRat() {
		return slfcapRat;
	}

	public void setSlfcapRat(String slfcapRat) {
		this.slfcapRat = slfcapRat;
	}

	public String getFstblRat() {
		return fstblRat;
	}

	public void setFstblRat(String fstblRat) {
		this.fstblRat = fstblRat;
	}

	public String getProcrt() {
		return procrt;
	}

	public void setProcrt(String procrt) {
		this.procrt = procrt;
	}

	public String getBizMod() {
		return bizMod;
	}

	public void setBizMod(String bizMod) {
		this.bizMod = bizMod;
	}

	public String getOperOrg() {
		return operOrg;
	}

	public void setOperOrg(String operOrg) {
		this.operOrg = operOrg;
	}

	@Override
	public String toString() {
		return "Financing [loanstDate=" + loanstDate + ", loanendDate=" + loanendDate + ", loanCode=" + loanCode
				+ ", scftxNo=" + scftxNo + ", loanAmt=" + loanAmt + ", bailRat=" + bailRat + ", slfcapRat=" + slfcapRat
				+ ", fstblRat=" + fstblRat + ", procrt=" + procrt + ", bizMod=" + bizMod + ", operOrg=" + operOrg + "]";
	}

}
