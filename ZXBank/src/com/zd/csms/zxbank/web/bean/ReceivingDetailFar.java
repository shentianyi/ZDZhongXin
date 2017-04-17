package com.zd.csms.zxbank.web.bean;

public class ReceivingDetailFar {
	private String idtplanNo;
	private String idtplanNm;
	private String cmdCode;
	private String cmdName;
	private String csnNum;
	private String unit;
	private String csnprc;
	private String reqcsnval;
	private String scflonNo;
	private String mtgcntNo;
	private String remark;
	private String vin;
	private String hgzNo;
	private String carPrice;
	private String loanCode;
	
	public String getIdtplanNo() {
		return idtplanNo;
	}
	public void setIdtplanNo(String idtplanNo) {
		this.idtplanNo = idtplanNo;
	}
	public String getIdtplanNm() {
		return idtplanNm;
	}
	public void setIdtplanNm(String idtplanNm) {
		this.idtplanNm = idtplanNm;
	}
	public String getCmdCode() {
		return cmdCode;
	}
	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}
	public String getCmdName() {
		return cmdName;
	}
	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}
	public String getCsnNum() {
		return csnNum;
	}
	public void setCsnNum(String csnNum) {
		this.csnNum = csnNum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCsnprc() {
		return csnprc;
	}
	public void setCsnprc(String csnprc) {
		this.csnprc = csnprc;
	}
	public String getReqcsnval() {
		return reqcsnval;
	}
	public void setReqcsnval(String reqcsnval) {
		this.reqcsnval = reqcsnval;
	}
	public String getScflonNo() {
		return scflonNo;
	}
	public void setScflonNo(String scflonNo) {
		this.scflonNo = scflonNo;
	}
	public String getMtgcntNo() {
		return mtgcntNo;
	}
	public void setMtgcntNo(String mtgcntNo) {
		this.mtgcntNo = mtgcntNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getHgzNo() {
		return hgzNo;
	}
	public void setHgzNo(String hgzNo) {
		this.hgzNo = hgzNo;
	}
	public String getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	
	@Override
	public String toString() {
		return "ReceivingDetail [idtplanNo=" + idtplanNo + ", idtplanNm=" + idtplanNm + ", cmdCode=" + cmdCode
				+ ", cmdName=" + cmdName + ", csnNum=" + csnNum + ", unit=" + unit + ", csnprc=" + csnprc
				+ ", reqcsnval=" + reqcsnval + ", scflonNo=" + scflonNo + ", mtgcntNo=" + mtgcntNo + ", remark="
				+ remark + ", vin=" + vin + ", hgzNo=" + hgzNo + ", carPrice=" + carPrice + ", loanCode=" + loanCode
				+ "]";
	}
}
