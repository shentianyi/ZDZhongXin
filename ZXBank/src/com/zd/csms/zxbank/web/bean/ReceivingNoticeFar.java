package com.zd.csms.zxbank.web.bean;
/**
 * 发货通知信息
 * @author caizhuo
 *
 */
public class ReceivingNoticeFar {
//	private String retcode;//返回代码
	private String rcvcmdntcNo;//收货通知书编号
	private String corentNm;//核心企业名称
	private String spventNm;//（在库）监管企业名称
	private String onwspveNm;//（在途）监管企业名称
	private String trsptentNm;//运输企业名称
	private String lonentId;//借款企业id
	private String lonentNm;//借款企业名称
	private String csnDate;//发货日期
	private String etaDate;//预计到港(库)日期
	private String epa;//预计到港(库) 
	private String offlnsatNo;//纸质监管协议编号
	private String ntcDate;//通知书日期
	private String ttlcmdval;//-货物价值合计
	private String excPlace;//交货地点
	private String remark;//-备注
	private String totnum;//总记录数
	public String getRcvcmdntcNo() {
		return rcvcmdntcNo;
	}
	public void setRcvcmdntcNo(String rcvcmdntcNo) {
		this.rcvcmdntcNo = rcvcmdntcNo;
	}
	public String getCorentNm() {
		return corentNm;
	}
	public void setCorentNm(String corentNm) {
		this.corentNm = corentNm;
	}
	public String getSpventNm() {
		return spventNm;
	}
	public void setSpventNm(String spventNm) {
		this.spventNm = spventNm;
	}
	public String getOnwspveNm() {
		return onwspveNm;
	}
	public void setOnwspveNm(String onwspveNm) {
		this.onwspveNm = onwspveNm;
	}
	public String getTrsptentNm() {
		return trsptentNm;
	}
	public void setTrsptentNm(String trsptentNm) {
		this.trsptentNm = trsptentNm;
	}
	public String getLonentId() {
		return lonentId;
	}
	public void setLonentId(String lonentId) {
		this.lonentId = lonentId;
	}
	public String getLonentNm() {
		return lonentNm;
	}
	public void setLonentNm(String lonentNm) {
		this.lonentNm = lonentNm;
	}
	public String getCsnDate() {
		return csnDate;
	}
	public void setCsnDate(String csnDate) {
		this.csnDate = csnDate;
	}
	public String getEtaDate() {
		return etaDate;
	}
	public void setEtaDate(String etaDate) {
		this.etaDate = etaDate;
	}
	public String getEpa() {
		return epa;
	}
	public void setEpa(String epa) {
		this.epa = epa;
	}
	public String getOfflnsatNo() {
		return offlnsatNo;
	}
	public void setOfflnsatNo(String offlnsatNo) {
		this.offlnsatNo = offlnsatNo;
	}
	public String getNtcDate() {
		return ntcDate;
	}
	public void setNtcDate(String ntcDate) {
		this.ntcDate = ntcDate;
	}
	public String getTtlcmdval() {
		return ttlcmdval;
	}
	public void setTtlcmdval(String ttlcmdval) {
		this.ttlcmdval = ttlcmdval;
	}
	public String getExcPlace() {
		return excPlace;
	}
	public void setExcPlace(String excPlace) {
		this.excPlace = excPlace;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTotnum() {
		return totnum;
	}
	public void setTotnum(String totnum) {
		this.totnum = totnum;
	}
	
	@Override
	public String toString() {
		return "ReceivingNotice [rcvcmdntcNo=" + rcvcmdntcNo + ", corentNm=" + corentNm + ", spventNm=" + spventNm
				+ ", onwspveNm=" + onwspveNm + ", trsptentNm=" + trsptentNm + ", lonentId=" + lonentId + ", lonentNm="
				+ lonentNm + ", csnDate=" + csnDate + ", etaDate=" + etaDate + ", epa=" + epa + ", offlnsatNo="
				+ offlnsatNo + ", ntcDate=" + ntcDate + ", ttlcmdval=" + ttlcmdval + ", excPlace=" + excPlace
				+ ", remark=" + remark + ", totnum=" + totnum + "]";
	}
}
