package com.zd.csms.zxbank.bean;

public class ReceivingNotice {
	private int nyId;//--收货通知表主键id
	private String nyNo;//--通知书编号
	private String nyCorentnm;//--核心企业名称
	private String nySpventnm;//--(在库)监管企业名称
	private String nyOnwspvenm;//--（在途）监管企业名称
	private String nyTrsptentnm;//--运输企业名称
	private String nyLonentno;//--借款企业id
	private String nyLonentname;//--借款企业名称
	private String nyCsndate;//--发货日期
	private String nyEta;//--预计到港日期
	private String nyEpa;//--预计到库
	private String nyOfflnsatno;//--纸质监管协议编号
	private String nyNtcdate;//--通知书日期
	private double nyTtlcmdval;//--货物价值合计
	private String nyExcplace;//--交货地点
	private String nyRemark;//--备注
	private int nyTotnum;//--总记录数
	private String nyCreatedate;//创建时间
	private String nyUpdatedate;//更新时间
	public int getNyId() {
		return nyId;
	}
	public void setNyId(int nyId) {
		this.nyId = nyId;
	}
	public String getNyNo() {
		return nyNo;
	}
	public void setNyNo(String nyNo) {
		this.nyNo = nyNo;
	}
	public String getNyCorentnm() {
		return nyCorentnm;
	}
	public void setNyCorentnm(String nyCorentnm) {
		this.nyCorentnm = nyCorentnm;
	}
	public String getNySpventnm() {
		return nySpventnm;
	}
	public void setNySpventnm(String nySpventnm) {
		this.nySpventnm = nySpventnm;
	}
	public String getNyOnwspvenm() {
		return nyOnwspvenm;
	}
	public void setNyOnwspvenm(String nyOnwspvenm) {
		this.nyOnwspvenm = nyOnwspvenm;
	}
	public String getNyTrsptentnm() {
		return nyTrsptentnm;
	}
	public void setNyTrsptentnm(String nyTrsptentnm) {
		this.nyTrsptentnm = nyTrsptentnm;
	}
	public String getNyLonentno() {
		return nyLonentno;
	}
	public void setNyLonentno(String nyLonentno) {
		this.nyLonentno = nyLonentno;
	}
	public String getNyLonentname() {
		return nyLonentname;
	}
	public void setNyLonentname(String nyLonentname) {
		this.nyLonentname = nyLonentname;
	}
	public String getNyCsndate() {
		return nyCsndate;
	}
	public void setNyCsndate(String nyCsndate) {
		this.nyCsndate = nyCsndate;
	}
	public String getNyEta() {
		return nyEta;
	}
	public void setNyEta(String nyEta) {
		this.nyEta = nyEta;
	}
	public String getNyEpa() {
		return nyEpa;
	}
	public void setNyEpa(String nyEpa) {
		this.nyEpa = nyEpa;
	}
	public String getNyOfflnsatno() {
		return nyOfflnsatno;
	}
	public void setNyOfflnsatno(String nyOfflnsatno) {
		this.nyOfflnsatno = nyOfflnsatno;
	}
	public String getNyNtcdate() {
		return nyNtcdate;
	}
	public void setNyNtcdate(String nyNtcdate) {
		this.nyNtcdate = nyNtcdate;
	}
	public double getNyTtlcmdval() {
		return nyTtlcmdval;
	}
	public void setNyTtlcmdval(double nyTtlcmdval) {
		this.nyTtlcmdval = nyTtlcmdval;
	}
	public String getNyExcplace() {
		return nyExcplace;
	}
	public void setNyExcplace(String nyExcplace) {
		this.nyExcplace = nyExcplace;
	}
	public String getNyRemark() {
		return nyRemark;
	}
	public void setNyRemark(String nyRemark) {
		this.nyRemark = nyRemark;
	}
	public int getNyTotnum() {
		return nyTotnum;
	}
	public void setNyTotnum(int nyTotnum) {
		this.nyTotnum = nyTotnum;
	}
	public String getNyCreatedate() {
		return nyCreatedate;
	}
	public void setNyCreatedate(String nyCreatedate) {
		this.nyCreatedate = nyCreatedate;
	}
	public String getNyUpdatedate() {
		return nyUpdatedate;
	}
	public void setNyUpdatedate(String nyUpdatedate) {
		this.nyUpdatedate = nyUpdatedate;
	}
	@Override
	public String toString() {
		return "Notify [nyId=" + nyId + ", nyNo=" + nyNo + ", nyCorentnm="
				+ nyCorentnm + ", nySpventnm=" + nySpventnm + ", nyOnwspvenm="
				+ nyOnwspvenm + ", nyTrsptentnm=" + nyTrsptentnm
				+ ", nyLonentno=" + nyLonentno + ", nyLonentname="
				+ nyLonentname + ", nyCsndate=" + nyCsndate + ", nyEta="
				+ nyEta + ", nyEpa=" + nyEpa + ", nyOfflnsatno=" + nyOfflnsatno
				+ ", nyNtcdate=" + nyNtcdate + ", nyTtlcmdval=" + nyTtlcmdval
				+ ", nyExcplace=" + nyExcplace + ", nyRemark=" + nyRemark
				+ ", nyTotnum=" + nyTotnum + ", nyCreatedate=" + nyCreatedate
				+ ", nyUpdatedate=" + nyUpdatedate + ", getNyId()=" + getNyId()
				+ ", getNyNo()=" + getNyNo() + ", getNyCorentnm()="
				+ getNyCorentnm() + ", getNySpventnm()=" + getNySpventnm()
				+ ", getNyOnwspvenm()=" + getNyOnwspvenm()
				+ ", getNyTrsptentnm()=" + getNyTrsptentnm()
				+ ", getNyLonentno()=" + getNyLonentno()
				+ ", getNyLonentname()=" + getNyLonentname()
				+ ", getNyCsndate()=" + getNyCsndate() + ", getNyEta()="
				+ getNyEta() + ", getNyEpa()=" + getNyEpa()
				+ ", getNyOfflnsatno()=" + getNyOfflnsatno()
				+ ", getNyNtcdate()=" + getNyNtcdate() + ", getNyTtlcmdval()="
				+ getNyTtlcmdval() + ", getNyExcplace()=" + getNyExcplace()
				+ ", getNyRemark()=" + getNyRemark() + ", getNyTotnum()="
				+ getNyTotnum() + ", getNyCreatedate()=" + getNyCreatedate()
				+ ", getNyUpdatedate()=" + getNyUpdatedate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
