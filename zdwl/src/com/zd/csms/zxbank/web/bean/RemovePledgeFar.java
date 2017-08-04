package com.zd.csms.zxbank.web.bean;

/**
 * 解除质押通知书
 * @author duyong
 */
public class RemovePledgeFar {
	private String rlsmgntcNo;//解除质押通知书编号
	private String operOrg;//经办行
	private String mtgpsnNm;//出质人名称 
	private String loaentId;//借款企业id
	private String lonentNm;//借款企业名称
	private String spventNm;//监管企业名称
	private String corentNm;//核心企业名称
	private String rlsmgDate;//-解除质押日期
	private String ostkrsn;//出库原因
	private String ntcDate;//通知书日期

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
