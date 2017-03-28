package com.zd.csms.zxbank.bean;

import java.util.Date;

/**
 * 
 * 中信银行经销商参数表
 */
public class DistribsetZX {
	private int id;//序列号
	private String moveperc;//监管物移动百分比
	private int distribid;//经销商ID
	private int bankdocktype;//对接银行类型 0不对接1浙商银行2中信银行
	private String contract;//合同编号
	private String organizationcode;//组织机构代码
	private Date createdate;//表中数据创建时间
	private Date updatedate;//表中数据修改时间
	private String createuser;//表中数据创建人
	
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
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	
}
