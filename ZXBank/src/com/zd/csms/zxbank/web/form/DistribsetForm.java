package com.zd.csms.zxbank.web.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.DistribsetZX;

public class DistribsetForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6183603022278423099L;

	/*private DistribsetZX distribset = new DistribsetZX();

	public DistribsetZX getDistribset() {
		return distribset;
	}

	public void setDistribset(DistribsetZX distribset) {
		this.distribset = distribset;
	}*/
	
	private int did;//序列号
	private String moveperc;//监管物移动百分比
	private int distribid;//经销商ID
	private int bankdocktype;//对接银行类型 0不对接1浙商银行2中信银行
	private String contract;//合同编号
	private String organizationcode;//组织机构代码
	
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
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
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getOrganizationcode() {
		return organizationcode;
	}
	public void setOrganizationcode(String organizationcode) {
		this.organizationcode = organizationcode;
	}
}
