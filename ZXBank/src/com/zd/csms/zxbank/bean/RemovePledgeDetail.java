package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zd.core.annotation.table;

/**
 * 解除质押合同详情
 * @author duyong
 */
@table(name="zx_removepledgedetail")
public class RemovePledgeDetail implements Serializable{

	private static final long serialVersionUID = 1682831269544002616L;
	
	private int rdId;//序列ID 主键id
	private String rdNo;//通知书编号
	private String rdCmdcode;//商品代码
	private String rdCmdname;//商品名称
	private String rdUnit;//计量单位
	private String rdStknum;//库存数量
	private String rdRlsmgnum;//解除质押数量
	private String rdWhcode;//所在仓库编号
	private String rdScflonno;//SCF放款批次号
	private String rdChattelpdno;//动产质押担保合同编号
	private String rdNumber;//移库数量
	private String rdChassisno;//车架号
	private String rdCertificationno;//合格证编号
	private String rdCarprice;//车价
	//一下两个字段原本为rdmoprlst集合内的。当前暂时放入详情集合内，待测试。
	private String rdUsername;//赎货经办人姓名
	private String rdUsercardid;//赎货经办人身份证号码
	public int getRdId() {
		return rdId;
	}
	public void setRdId(int rdId) {
		this.rdId = rdId;
	}
	public String getRdNo() {
		return rdNo;
	}
	public void setRdNo(String rdNo) {
		this.rdNo = rdNo;
	}
	public String getRdCmdcode() {
		return rdCmdcode;
	}
	public void setRdCmdcode(String rdCmdcode) {
		this.rdCmdcode = rdCmdcode;
	}
	public String getRdCmdname() {
		return rdCmdname;
	}
	public void setRdCmdname(String rdCmdname) {
		this.rdCmdname = rdCmdname;
	}
	public String getRdUnit() {
		return rdUnit;
	}
	public void setRdUnit(String rdUnit) {
		this.rdUnit = rdUnit;
	}
	public String getRdStknum() {
		return rdStknum;
	}
	public void setRdStknum(String rdStknum) {
		this.rdStknum = rdStknum;
	}
	public String getRdRlsmgnum() {
		return rdRlsmgnum;
	}
	public void setRdRlsmgnum(String rdRlsmgnum) {
		this.rdRlsmgnum = rdRlsmgnum;
	}
	public String getRdWhcode() {
		return rdWhcode;
	}
	public void setRdWhcode(String rdWhcode) {
		this.rdWhcode = rdWhcode;
	}
	public String getRdScflonno() {
		return rdScflonno;
	}
	public void setRdScflonno(String rdScflonno) {
		this.rdScflonno = rdScflonno;
	}
	public String getRdChattelpdno() {
		return rdChattelpdno;
	}
	public void setRdChattelpdno(String rdChattelpdno) {
		this.rdChattelpdno = rdChattelpdno;
	}
	public String getRdNumber() {
		return rdNumber;
	}
	public void setRdNumber(String rdNumber) {
		this.rdNumber = rdNumber;
	}
	public String getRdChassisno() {
		return rdChassisno;
	}
	public void setRdChassisno(String rdChassisno) {
		this.rdChassisno = rdChassisno;
	}
	public String getRdCertificationno() {
		return rdCertificationno;
	}
	public void setRdCertificationno(String rdCertificationno) {
		this.rdCertificationno = rdCertificationno;
	}
	public String getRdCarprice() {
		return rdCarprice;
	}
	public void setRdCarprice(String rdCarprice) {
		this.rdCarprice = rdCarprice;
	}
	public String getRdUsername() {
		return rdUsername;
	}
	public void setRdUsername(String rdUsername) {
		this.rdUsername = rdUsername;
	}
	public String getRdUsercardid() {
		return rdUsercardid;
	}
	public void setRdUsercardid(String rdUsercardid) {
		this.rdUsercardid = rdUsercardid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "RemovePledgeDetail [rdId=" + rdId + ", rdNo=" + rdNo
				+ ", rdCmdcode=" + rdCmdcode + ", rdCmdname=" + rdCmdname
				+ ", rdUnit=" + rdUnit + ", rdStknum=" + rdStknum
				+ ", rdRlsmgnum=" + rdRlsmgnum + ", rdWhcode=" + rdWhcode
				+ ", rdScflonno=" + rdScflonno + ", rdChattelpdno="
				+ rdChattelpdno + ", rdNumber=" + rdNumber + ", rdChassisno="
				+ rdChassisno + ", rdCertificationno=" + rdCertificationno
				+ ", rdCarprice=" + rdCarprice + ", rdUsername=" + rdUsername
				+ ", rdUsercardid=" + rdUsercardid + "]";
	}
	
}
