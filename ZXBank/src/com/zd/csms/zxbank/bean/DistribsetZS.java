package com.zd.csms.zxbank.bean;

/**
 * 
 * 浙商银行经销商参数表
 */
public class DistribsetZS {
	private int id;//序列号
	private String moveperc;//监管物移动百分比
	private int distribid;//经销商ID
	private int bankdocktype;//对接银行类型 0不对接1浙商银行2中信银行
	private String zscustno;//浙商银行客户号
	private String contract;//合同编号
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMoveperc() {
		return moveperc;
	}
	public void setMoveperc(String moveperc) {
		this.moveperc = moveperc;
	}
	public int getDistribid() {
		return distribid;
	}
	public void setDistribid(int distribid) {
		this.distribid = distribid;
	}
	public int getBankdocktype() {
		return bankdocktype;
	}
	public void setBankdocktype(int bankdocktype) {
		this.bankdocktype = bankdocktype;
	}
	public String getZscustno() {
		return zscustno;
	}
	public void setZscustno(String zscustno) {
		this.zscustno = zscustno;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	
	
}
