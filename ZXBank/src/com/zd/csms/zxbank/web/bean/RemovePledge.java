package com.zd.csms.zxbank.web.bean;


/**
 * 解除质押通知书
 * @author duyong
 */
public class RemovePledge {
	private String rlsmgntcNo;
	private String operOrg;
	private String mtgpsnNm;
	private String loaentId;
	private String lonentNm;
	private String spventNm;
	private String corentNm;
	private String rlsmgDate;
	private String ostkrsn;
	private String ntcDate;
	
	public String getRlsmgntcNo() {
		return rlsmgntcNo;
	}
	public void setRlsmgntcNo(String rlsmgntcNo) {
		this.rlsmgntcNo = rlsmgntcNo;
	}
	public String getOperOrg() {
		return operOrg;
	}
	public void setOperOrg(String operOrg) {
		this.operOrg = operOrg;
	}
	public String getMtgpsnNm() {
		return mtgpsnNm;
	}
	public void setMtgpsnNm(String mtgpsnNm) {
		this.mtgpsnNm = mtgpsnNm;
	}
	public String getLoaentId() {
		return loaentId;
	}
	public void setLoaentId(String loaentId) {
		this.loaentId = loaentId;
	}
	public String getLonentNm() {
		return lonentNm;
	}
	public void setLonentNm(String lonentNm) {
		this.lonentNm = lonentNm;
	}
	public String getSpventNm() {
		return spventNm;
	}
	public void setSpventNm(String spventNm) {
		this.spventNm = spventNm;
	}
	public String getCorentNm() {
		return corentNm;
	}
	public void setCorentNm(String corentNm) {
		this.corentNm = corentNm;
	}
	public String getRlsmgDate() {
		return rlsmgDate;
	}
	public void setRlsmgDate(String rlsmgDate) {
		this.rlsmgDate = rlsmgDate;
	}
	public String getOstkrsn() {
		return ostkrsn;
	}
	public void setOstkrsn(String ostkrsn) {
		this.ostkrsn = ostkrsn;
	}
	public String getNtcDate() {
		return ntcDate;
	}
	public void setNtcDate(String ntcDate) {
		this.ntcDate = ntcDate;
	}
	
	@Override
	public String toString() {
		return "RemovePledge [rlsmgntcNo=" + rlsmgntcNo + ", operOrg=" + operOrg + ", mtgpsnNm=" + mtgpsnNm
				+ ", loaentId=" + loaentId + ", lonentNm=" + lonentNm + ", spventNm=" + spventNm + ", corentNm="
				+ corentNm + ", rlsmgDate=" + rlsmgDate + ", ostkrsn=" + ostkrsn + ", ntcDate=" + ntcDate + "]";
	}
}
