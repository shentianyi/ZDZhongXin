package com.zd.csms.zxbank.bean;

import java.util.Date;

public class Agreement {
	
	private int agId ;//监管协议id主键
	private String agCustno ; //客户号
	private String agLoncpid ; //借款企业id（银行借款企业ECIF编号）
	private String agLoncpname ;//借款企业名称
	private String agProtocolno ;//系统监管协议编号
	private String agProtocolcode ;//纸质监管协议编号
	private String agState ;//协议状态（01-生效,02-失效）
	private String agStdate ;//协议起始日（长度 8）
	private String agEnddate ;//协议到期日（长度 8）
	private String agIsonline ;//是否开通线上业务（00-未开通,01-开通）
	private String agIsmove  ;//是否允许移库（00-不允许,01-允许）
	private String agOperorg ;//经办行
	private String agTotnum ;//总记录数
	private Date agCreatedate ;//数据创建时间
	private Date agUpdatedate ;//数据更新时间
	public int getAgId() {
		return agId;
	}
	public void setAgId(int agId) {
		this.agId = agId;
	}
	public String getAgCustno() {
		return agCustno;
	}
	public void setAgCustno(String agCustno) {
		this.agCustno = agCustno;
	}
	public String getAgLoncpid() {
		return agLoncpid;
	}
	public void setAgLoncpid(String agLoncpid) {
		this.agLoncpid = agLoncpid;
	}
	public String getAgLoncpname() {
		return agLoncpname;
	}
	public void setAgLoncpname(String agLoncpname) {
		this.agLoncpname = agLoncpname;
	}
	public String getAgProtocolno() {
		return agProtocolno;
	}
	public void setAgProtocolno(String agProtocolno) {
		this.agProtocolno = agProtocolno;
	}
	public String getAgProtocolcode() {
		return agProtocolcode;
	}
	public void setAgProtocolcode(String agProtocolcode) {
		this.agProtocolcode = agProtocolcode;
	}
	public String getAgState() {
		return agState;
	}
	public void setAgState(String agState) {
		this.agState = agState;
	}
	public String getAgStdate() {
		return agStdate;
	}
	public void setAgStdate(String agStdate) {
		this.agStdate = agStdate;
	}
	public String getAgEnddate() {
		return agEnddate;
	}
	public void setAgEnddate(String agEnddate) {
		this.agEnddate = agEnddate;
	}
	public String getAgIsonline() {
		return agIsonline;
	}
	public void setAgIsonline(String agIsonline) {
		this.agIsonline = agIsonline;
	}
	public String getAgIsmove() {
		return agIsmove;
	}
	public void setAgIsmove(String agIsmove) {
		this.agIsmove = agIsmove;
	}
	public String getAgOperorg() {
		return agOperorg;
	}
	public void setAgOperorg(String agOperorg) {
		this.agOperorg = agOperorg;
	}
	public String getAgTotnum() {
		return agTotnum;
	}
	public void setAgTotnum(String agTotnum) {
		this.agTotnum = agTotnum;
	}
	public Date getAgCreatedate() {
		return agCreatedate;
	}
	public void setAgCreatedate(Date agCreatedate) {
		this.agCreatedate = agCreatedate;
	}
	public Date getAgUpdatedate() {
		return agUpdatedate;
	}
	public void setAgUpdatedate(Date agUpdatedate) {
		this.agUpdatedate = agUpdatedate;
	}
	@Override
	public String toString() {
		return "Agreement [agId=" + agId + ", agCustno=" + agCustno
				+ ", agLoncpid=" + agLoncpid + ", agLoncpname=" + agLoncpname
				+ ", agProtocolno=" + agProtocolno + ", agProtocolcode="
				+ agProtocolcode + ", agState=" + agState + ", agStdate="
				+ agStdate + ", agEnddate=" + agEnddate + ", agIsonline="
				+ agIsonline + ", agIsmove=" + agIsmove + ", agOperorg="
				+ agOperorg + ", agTotnum=" + agTotnum + ", agCreatedate="
				+ agCreatedate + ", agUpdatedate=" + agUpdatedate + "]";
	}
	
	
}
