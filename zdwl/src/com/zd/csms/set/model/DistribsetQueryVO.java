package com.zd.csms.set.model;

public class DistribsetQueryVO {

	private int id;
	private String movePerc;//监管物移动百分比
	private int distribid;//经销商id
	private int bankDockType;//对接银行类型
	private String zsCustNo;//浙商银行客户号
	private String zxOrgCode;//中信组织机构代码
	private String contractNo;//合同编号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovePerc() {
		return movePerc;
	}
	public void setMovePerc(String movePerc) {
		this.movePerc = movePerc;
	}
	public int getDistribid() {
		return distribid;
	}
	public void setDistribid(int distribid) {
		this.distribid = distribid;
	}
	public int getBankDockType() {
		return bankDockType;
	}
	public void setBankDockType(int bankDockType) {
		this.bankDockType = bankDockType;
	}
	public String getZsCustNo() {
		return zsCustNo;
	}
	public void setZsCustNo(String zsCustNo) {
		this.zsCustNo = zsCustNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getZxOrgCode() {
		return zxOrgCode;
	}
	public void setZxOrgCode(String zxOrgCode) {
		this.zxOrgCode = zxOrgCode;
	}
	
	
}
