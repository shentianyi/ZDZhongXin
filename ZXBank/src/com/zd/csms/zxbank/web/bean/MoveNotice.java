package com.zd.csms.zxbank.web.bean;

/**
 * 移库通知
 * @author duyong
 * 
 */
public class MoveNotice{
	private String operOrg;
	private String spventnm;
	private String mwapyDate;
	private String lonentNm;
	private String mwntcNo;
	private String ntcDate;
	private String totnum;
	
	public String getOperOrg() {
		return operOrg;
	}
	public void setOperOrg(String operOrg) {
		this.operOrg = operOrg;
	}
	public String getSpventnm() {
		return spventnm;
	}
	public void setSpventnm(String spventnm) {
		this.spventnm = spventnm;
	}
	public String getMwapyDate() {
		return mwapyDate;
	}
	public void setMwapyDate(String mwapyDate) {
		this.mwapyDate = mwapyDate;
	}
	public String getLonentNm() {
		return lonentNm;
	}
	public void setLonentNm(String lonentNm) {
		this.lonentNm = lonentNm;
	}
	public String getMwntcNo() {
		return mwntcNo;
	}
	public void setMwntcNo(String mwntcNo) {
		this.mwntcNo = mwntcNo;
	}
	public String getNtcDate() {
		return ntcDate;
	}
	public void setNtcDate(String ntcDate) {
		this.ntcDate = ntcDate;
	}
	public String getTotnum() {
		return totnum;
	}
	public void setTotnum(String totnum) {
		this.totnum = totnum;
	}

	@Override
	public String toString() {
		return "MoveNotice [operOrg=" + operOrg + ", spventnm=" + spventnm + ", mwapyDate=" + mwapyDate + ", lonentNm="
				+ lonentNm + ", mwntcNo=" + mwntcNo + ", ntcDate=" + ntcDate + ", totnum=" + totnum + "]";
	}
	
}
