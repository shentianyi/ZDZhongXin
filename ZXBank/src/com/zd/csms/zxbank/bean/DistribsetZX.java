package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 
 * 中信银行经销商参数表
 */
@table(name="ZX_DISTRIBSET")
public class DistribsetZX {
	private int zx_did;//经销商ID
	private String zx_moveperc;//监管物移动百分比
	private int zx_bankdocktype;//对接银行类型 0不对接1浙商银行2中信银行
	private String contractno;//合同编号
	private String organizationcode;//组织机构代码
	private Date zx_createdate;//表中数据创建时间
	private Date zx_updatedate;//表中数据修改时间
	private String zx_createuser;//表中数据创建人
	
	public int getZx_did() {
		return zx_did;
	}
	public void setZx_did(int zx_did) {
		this.zx_did = zx_did;
	}
	public String getZx_moveperc() {
		return zx_moveperc;
	}
	public void setZx_moveperc(String zx_moveperc) {
		this.zx_moveperc = zx_moveperc;
	}
	public int getZx_bankdocktype() {
		return zx_bankdocktype;
	}
	public void setZx_bankdocktype(int zx_bankdocktype) {
		this.zx_bankdocktype = zx_bankdocktype;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
	public String getOrganizationcode() {
		return organizationcode;
	}
	public void setOrganizationcode(String organizationcode) {
		this.organizationcode = organizationcode;
	}
	public Date getZx_createdate() {
		return zx_createdate;
	}
	public void setZx_createdate(Date zx_createdate) {
		this.zx_createdate = zx_createdate;
	}
	public Date getZx_updatedate() {
		return zx_updatedate;
	}
	public void setZx_updatedate(Date zx_updatedate) {
		this.zx_updatedate = zx_updatedate;
	}
	public String getZx_createuser() {
		return zx_createuser;
	}
	public void setZx_createuser(String zx_createuser) {
		this.zx_createuser = zx_createuser;
	}
	@Override
	public String toString() {
		return "DistribsetZX [zx_did=" + zx_did + ", zx_moveperc="
				+ zx_moveperc + ", zx_bankdocktype=" + zx_bankdocktype
				+ ", contractno=" + contractno + ", organizationcode="
				+ organizationcode + ", zx_createdate=" + zx_createdate
				+ ", zx_updatedate=" + zx_updatedate + ", zx_createuser="
				+ zx_createuser + "]";
	}
	
	
	/*private int id;//序列号
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
	
	@Override
	public String toString() {
		return "DistribsetZX [id=" + id + ", moveperc=" + moveperc
				+ ", distribid=" + distribid + ", bankdocktype=" + bankdocktype
				+ ", contract=" + contract + ", organizationcode="
				+ organizationcode + ", createdate=" + createdate
				+ ", updatedate=" + updatedate + ", createuser=" + createuser
				+ "]";
	}*/
	
}
