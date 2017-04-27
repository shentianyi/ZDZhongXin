package com.zd.csms.zxbank.web.bean;

public class AgreementFar {
	private String hostno ; //借款企业id
	private String lonnm;//借款企业名称
	private String spvagtid;//系统监管协议编号
	private String spvagtno;//纸质监管协议编号
	private String agtstt;//协议状态（0,失效,1,生效）
	private String startdate;//协议起始日
	private String enddate;//协议到期日
	private String isauth;//是否开通线上业务（0,否,1,是）
	private String ismv;//是否允许移库（0,否,1,是）
	private String operorg;//经办行
	public String getHostno() {
		return hostno;
	}
	public void setHostno(String hostno) {
		this.hostno = hostno;
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
	@Override
	public String toString() {
		return "AgreementFar [hostno=" + hostno + ", lonnm=" + lonnm
				+ ", spvagtid=" + spvagtid + ", spvagtno=" + spvagtno
				+ ", agtstt=" + agtstt + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", isauth=" + isauth + ", ismv="
				+ ismv + ", operorg=" + operorg + "]";
	}
	
}
