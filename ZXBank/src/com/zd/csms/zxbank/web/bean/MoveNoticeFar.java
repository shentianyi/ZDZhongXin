package com.zd.csms.zxbank.web.bean;

/**
 * 移库通知
 * @author duyong
 * 
 */
public class MoveNoticeFar{
	private String operOrg;//经办行
	private String spventnm;//监管企业名称
	private String mwapyDate;//移库申请日期
	private String lonentNm;//借款企业名称
	private String mwntcNo;//移库通知编号
	private String ntcDate;//通知日期
	private String totnum;// 总记录数
	
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
