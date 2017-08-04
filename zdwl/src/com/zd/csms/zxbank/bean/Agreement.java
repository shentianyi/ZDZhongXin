package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

@table(name="ZX_AGREEMENT")
public class Agreement {
	
	private int agId ;//监管协议id主键
	private String hostno  ; //客户号
	private String agloncpid  ; //借款企业id（银行借款企业ECIF编号）
	private String lonnm  ;//借款企业名称
	private String spvagtid  ;//系统监管协议编号
	private String spvagtno  ;//纸质监管协议编号
	private String agtstt  ;//协议状态（01-生效,02-失效）
	private String startdate  ;//协议起始日（长度 8）
	private String enddate  ;//协议到期日（长度 8）
	private String isauth  ;//是否开通线上业务（00-未开通,01-开通）
	private String ismv   ;//是否允许移库（00-不允许,01-允许）
	private String operorg  ;//经办行
	private String totnum  ;//总记录数
	private Date agcreatedate  ;//数据创建时间
	private Date agupdatedate  ;//数据更新时间
	
	public Agreement() {
	}
	public Agreement(String hostno) {
		super();
		this.hostno = hostno;
	}
	public int getAgId() {
		return agId;
	}
	public void setAgId(int agId) {
		this.agId = agId;
	}
	public String getHostno() {
		return hostno;
	}
	public void setHostno(String hostno) {
		this.hostno = hostno;
	}
	public String getAgloncpid() {
		return agloncpid;
	}
	public void setAgloncpid(String agloncpid) {
		this.agloncpid = agloncpid;
	}
	public String getLonnm() {
		return lonnm;
	}
	public void setLonnm(String lonnm) {
		this.lonnm = lonnm;
	}
	public String getSpvagtid() {
		return spvagtid;
	}
	public void setSpvagtid(String spvagtid) {
		this.spvagtid = spvagtid;
	}
	public String getSpvagtno() {
		return spvagtno;
	}
	public void setSpvagtno(String spvagtno) {
		this.spvagtno = spvagtno;
	}
	public String getAgtstt() {
		return agtstt;
	}
	public void setAgtstt(String agtstt) {
		this.agtstt = agtstt;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getIsauth() {
		return isauth;
	}
	public void setIsauth(String isauth) {
		this.isauth = isauth;
	}
	public String getIsmv() {
		return ismv;
	}
	public void setIsmv(String ismv) {
		this.ismv = ismv;
	}
	public String getOperorg() {
		return operorg;
	}
	public void setOperorg(String operorg) {
		this.operorg = operorg;
	}
	public String getTotnum() {
		return totnum;
	}
	public void setTotnum(String totnum) {
		this.totnum = totnum;
	}
	public Date getAgcreatedate() {
		return agcreatedate;
	}
	public void setAgcreatedate(Date agcreatedate) {
		this.agcreatedate = agcreatedate;
	}
	public Date getAgupdatedate() {
		return agupdatedate;
	}
	public void setAgupdatedate(Date agupdatedate) {
		this.agupdatedate = agupdatedate;
	}
	@Override
	public String toString() {
		return "Agreement [agId=" + agId + ", hostno=" + hostno
				+ ", agloncpid=" + agloncpid + ", lonnm=" + lonnm
				+ ", spvagtid=" + spvagtid + ", spvagtno=" + spvagtno
				+ ", agtstt=" + agtstt + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", isauth=" + isauth + ", ismv="
				+ ismv + ", operorg=" + operorg + ", totnum=" + totnum
				+ ", agcreatedate=" + agcreatedate + ", agupdatedate="
				+ agupdatedate + "]";
	}
	
}
