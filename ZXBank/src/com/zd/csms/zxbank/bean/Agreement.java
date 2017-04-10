package com.zd.csms.zxbank.bean;

public class Agreement {
	private int ag_id ;//监管协议id主键
	private String ag_custno ; //客户号
	private String ag_loncpid ; //借款企业id
	private String ag_loncpname ;//借款企业名称
	private String ag_protocolno ;//系统监管协议编号
	private String ag_protocolcode ;//纸质监管协议编号
	private int ag_state ;//协议状态（0,失效,1,生效）
	private String ag_stdate ;//协议起始日
	private String ag_enddate ;//协议到期日
	private int ag_isonline ;//是否开通线上业务（0,否,1,是）
	private int ag_ismove  ;//是否允许移库（0,否,1,是）
	private String ag_operorg ;//经办行
	private int ag_totnum ;//总记录数
	private String ag_createdate ;//同步数据时间
	private String ag_updatedate ;//同步更新数据时间
	public int getAg_id() {
		return ag_id;
	}
	public void setAg_id(int ag_id) {
		this.ag_id = ag_id;
	}
	public String getAg_custno() {
		return ag_custno;
	}
	public void setAg_custno(String ag_custno) {
		this.ag_custno = ag_custno;
	}
	public String getAg_loncpid() {
		return ag_loncpid;
	}
	public void setAg_loncpid(String ag_loncpid) {
		this.ag_loncpid = ag_loncpid;
	}
	public String getAg_loncpname() {
		return ag_loncpname;
	}
	public void setAg_loncpname(String ag_loncpname) {
		this.ag_loncpname = ag_loncpname;
	}
	public String getAg_protocolno() {
		return ag_protocolno;
	}
	public void setAg_protocolno(String ag_protocolno) {
		this.ag_protocolno = ag_protocolno;
	}
	public String getAg_protocolcode() {
		return ag_protocolcode;
	}
	public void setAg_protocolcode(String ag_protocolcode) {
		this.ag_protocolcode = ag_protocolcode;
	}
	public int getAg_state() {
		return ag_state;
	}
	public void setAg_state(int ag_state) {
		this.ag_state = ag_state;
	}
	
	public int getAg_isonline() {
		return ag_isonline;
	}
	public void setAg_isonline(int ag_isonline) {
		this.ag_isonline = ag_isonline;
	}
	public int getAg_ismove() {
		return ag_ismove;
	}
	public void setAg_ismove(int ag_ismove) {
		this.ag_ismove = ag_ismove;
	}
	public String getAg_operorg() {
		return ag_operorg;
	}
	public void setAg_operorg(String ag_operorg) {
		this.ag_operorg = ag_operorg;
	}
	public int getAg_totnum() {
		return ag_totnum;
	}
	public void setAg_totnum(int ag_totnum) {
		this.ag_totnum = ag_totnum;
	}
	public String getAg_createdate() {
		return ag_createdate;
	}
	public void setAg_createdate(String ag_createdate) {
		this.ag_createdate = ag_createdate.substring(0,ag_createdate.length()-2);
	}
	public String getAg_updatedate() {
		return ag_updatedate;
	}
	public void setAg_updatedate(String ag_updatedate) {
		this.ag_updatedate = ag_updatedate.substring(0, ag_updatedate.length()-2);
	}
	public String getAg_stdate() {
		return ag_stdate;
	}
	public void setAg_stdate(String ag_stdate) {
		this.ag_stdate = ag_stdate;
	}
	public String getAg_enddate() {
		return ag_enddate;
	}
	public void setAg_enddate(String ag_enddate) {
		this.ag_enddate = ag_enddate;
	}
	@Override
	public String toString() {
		return "Agreement [ag_id=" + ag_id + ", ag_custno=" + ag_custno
				+ ", ag_loncpid=" + ag_loncpid + ", ag_loncpname="
				+ ag_loncpname + ", ag_protocolno=" + ag_protocolno
				+ ", ag_protocolcode=" + ag_protocolcode + ", ag_state="
				+ ag_state + ", ag_stdate=" + ag_stdate + ", ag_enddate="
				+ ag_enddate + ", ag_isonline=" + ag_isonline + ", ag_ismove="
				+ ag_ismove + ", ag_operorg=" + ag_operorg + ", ag_totnum="
				+ ag_totnum + ", ag_createdate=" + ag_createdate
				+ ", ag_updatedate=" + ag_updatedate + "]";
	}
	
	
	
}
