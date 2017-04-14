package com.zd.csms.zxbank.web.bean;

/**
 * 移库详情
 * @author duyong
 *
 */
public class MoveDetail{

	private String mwCode;
	private String iwCode;
	private String cmdCode;
	private String stkNum;
	private String vin;
	private String hgzNo;
	private String carPrice;
	
	public String getMwCode() {
		return mwCode;
	}
	public void setMwCode(String mwCode) {
		this.mwCode = mwCode;
	}
	public String getIwCode() {
		return iwCode;
	}
	public void setIwCode(String iwCode) {
		this.iwCode = iwCode;
	}
	public String getCmdCode() {
		return cmdCode;
	}
	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}
	public String getStkNum() {
		return stkNum;
	}
	public void setStkNum(String stkNum) {
		this.stkNum = stkNum;
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
	
	@Override
	public String toString() {
		return "MoveDetail [mwCode=" + mwCode + ", iwCode=" + iwCode + ", cmdCode=" + cmdCode + ", stkNum=" + stkNum
				+ ", vin=" + vin + ", hgzNo=" + hgzNo + ", carPrice=" + carPrice + "]";
	}
}
