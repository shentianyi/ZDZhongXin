package com.zd.csms.zxbank.web.bean;

public class AgreementFar {
	private String hostNo; //客户号
	private String lonNm;//借款企业名称
	private String spvAgtId;//系统监管协议编号
	private String spvAgtNo;//纸质监管协议编号
	private String agtStt;//协议状态（0,失效,1,生效）
	private String startDate;//协议起始日
	private String endDate;//协议到期日
	private String isAuth;//是否开通线上业务（0,否,1,是）
	private String isMv;//是否允许移库（0,否,1,是）
	private String operOrg;//经办行


	public String getHostNo() {
		return hostNo;
	}

	public void setHostNo(String hostNo) {
		this.hostNo = hostNo;
	}

	public String getLonNm() {
		return lonNm;
	}

	public void setLonNm(String lonNm) {
		this.lonNm = lonNm;
	}

	public String getSpvAgtId() {
		return spvAgtId;
	}

	public void setSpvAgtId(String spvAgtId) {
		this.spvAgtId = spvAgtId;
	}

	public String getSpvAgtNo() {
		return spvAgtNo;
	}

	public void setSpvAgtNo(String spvAgtNo) {
		this.spvAgtNo = spvAgtNo;
	}

	public String getAgtStt() {
		return agtStt;
	}

	public void setAgtStt(String agtStt) {
		this.agtStt = agtStt;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}

	public String getIsMv() {
		return isMv;
	}

	public void setIsMv(String isMv) {
		this.isMv = isMv;
	}

	public String getOperOrg() {
		return operOrg;
	}

	public void setOperOrg(String operOrg) {
		this.operOrg = operOrg;
	}
	

}
