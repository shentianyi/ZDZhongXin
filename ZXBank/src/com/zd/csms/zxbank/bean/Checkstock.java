package com.zd.csms.zxbank.bean;
/**
 * 
 * 盘库信息 
 * @author caizhuo
 *
 */
public class Checkstock {
	private int csId;//--盘库表id
	private String csLoncpid;//--借款企业id
	private String csProtocolno;//--系统监管协议编号
	private String csProtocolcode;//--纸质监管协议编号
	private String csUserno;//--操作人编号
	private String csUsername;//--操作人名称
	private String csTradeid;//--交易流水号
	private String csPlandate;//--计划盘库日期
	private String csFactdate;//--实际盘库日期
	private String csErrorreport;// --差错报告
	private String csRemark;//--备注
	private String csCreatedate;// --创建时间
	public int getCsId() {
		return csId;
	}
	public void setCsId(int csId) {
		this.csId = csId;
	}
	public String getCsLoncpid() {
		return csLoncpid;
	}
	public void setCsLoncpid(String csLoncpid) {
		this.csLoncpid = csLoncpid;
	}
	public String getCsProtocolno() {
		return csProtocolno;
	}
	public void setCsProtocolno(String csProtocolno) {
		this.csProtocolno = csProtocolno;
	}
	public String getCsProtocolcode() {
		return csProtocolcode;
	}
	public void setCsProtocolcode(String csProtocolcode) {
		this.csProtocolcode = csProtocolcode;
	}
	public String getCsUserno() {
		return csUserno;
	}
	public void setCsUserno(String csUserno) {
		this.csUserno = csUserno;
	}
	public String getCsUsername() {
		return csUsername;
	}
	public void setCsUsername(String csUsername) {
		this.csUsername = csUsername;
	}
	public String getCsTradeid() {
		return csTradeid;
	}
	public void setCsTradeid(String csTradeid) {
		this.csTradeid = csTradeid;
	}
	public String getCsPlandate() {
		return csPlandate;
	}
	public void setCsPlandate(String csPlandate) {
		this.csPlandate = csPlandate;
	}
	public String getCsFactdate() {
		return csFactdate;
	}
	public void setCsFactdate(String csFactdate) {
		this.csFactdate = csFactdate;
	}
	public String getCsErrorreport() {
		return csErrorreport;
	}
	public void setCsErrorreport(String csErrorreport) {
		this.csErrorreport = csErrorreport;
	}
	public String getCsRemark() {
		return csRemark;
	}
	public void setCsRemark(String csRemark) {
		this.csRemark = csRemark;
	}
	public String getCsCreatedate() {
		return csCreatedate;
	}
	public void setCsCreatedate(String csCreatedate) {
		this.csCreatedate = csCreatedate;
	}
	@Override
	public String toString() {
		return "Checkstock [csId=" + csId + ", csLoncpid=" + csLoncpid
				+ ", csProtocolno=" + csProtocolno + ", csProtocolcode="
				+ csProtocolcode + ", csUserno=" + csUserno + ", csUsername="
				+ csUsername + ", csTradeid=" + csTradeid + ", csPlandate="
				+ csPlandate + ", csFactdate=" + csFactdate
				+ ", csErrorreport=" + csErrorreport + ", csRemark=" + csRemark
				+ ", csCreatedate=" + csCreatedate + "]";
	}
}
