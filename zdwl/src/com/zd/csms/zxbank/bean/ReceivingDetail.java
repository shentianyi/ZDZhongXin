package com.zd.csms.zxbank.bean;

import java.io.Serializable;

import com.zd.core.annotation.table;

/**
 * 收货通知书详情
 * @author caizhuo
 *
 */
@table(name = "zx_notifydetail")
public class ReceivingDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -221065540432214722L;
	private int id;//--收货质物明细表主键id
	private String ndNo;//--通知书编号
	private String ndIdtplanno;//--实际纸质订单编号
	private String ndIdtplannm;//--实际订单名称
	private String ndCmdcode;//--商品代码
	private String ndCmdname;//--商品名称
	private String ndCsnnum;//--发货数量
	private String ndUnit;//--计量单位
	private String ndCsnprc;//--发货单价
	private String ndReqcsnval;//--发货价值
	private String ndScflonno;//--SCF放款批次号
	private String ndMtgcntno;//--质押合同编号
	private String ndRemark;//--备注
	private String ndVin;//--车架号
	private String ndHgzno;//合格证编号
	private String ndCarprice;//--车价
	private String ndLoancode;//--融资编号

	public String getNdCarprice() {
		return ndCarprice;
	}

	public void setNdCarprice(String ndCarprice) {
		this.ndCarprice = ndCarprice;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNdNo() {
		return ndNo;
	}

	public void setNdNo(String ndNo) {
		this.ndNo = ndNo;
	}

	public String getNdIdtplanno() {
		return ndIdtplanno;
	}

	public void setNdIdtplanno(String ndIdtplanno) {
		this.ndIdtplanno = ndIdtplanno;
	}

	public String getNdIdtplannm() {
		return ndIdtplannm;
	}

	public void setNdIdtplannm(String ndIdtplannm) {
		this.ndIdtplannm = ndIdtplannm;
	}

	public String getNdCmdcode() {
		return ndCmdcode;
	}

	public void setNdCmdcode(String ndCmdcode) {
		this.ndCmdcode = ndCmdcode;
	}

	public String getNdCmdname() {
		return ndCmdname;
	}

	public void setNdCmdname(String ndCmdname) {
		this.ndCmdname = ndCmdname;
	}

	public String getNdMtgcntno() {
		return ndMtgcntno;
	}

	public void setNdMtgcntno(String ndMtgcntno) {
		this.ndMtgcntno = ndMtgcntno;
	}

	public String getNdRemark() {
		return ndRemark;
	}

	public void setNdRemark(String ndRemark) {
		this.ndRemark = ndRemark;
	}

	public String getNdVin() {
		return ndVin;
	}

	public void setNdVin(String ndVin) {
		this.ndVin = ndVin;
	}

	public String getNdHgzno() {
		return ndHgzno;
	}

	public void setNdHgzno(String ndHgzno) {
		this.ndHgzno = ndHgzno;
	}

	public String getNdLoancode() {
		return ndLoancode;
	}

	public void setNdLoancode(String ndLoancode) {
		this.ndLoancode = ndLoancode;
	}

	public String getNdCsnnum() {
		return ndCsnnum;
	}

	public void setNdCsnnum(String ndCsnnum) {
		this.ndCsnnum = ndCsnnum;
	}

	public String getNdUnit() {
		return ndUnit;
	}

	public void setNdUnit(String ndUnit) {
		this.ndUnit = ndUnit;
	}

	public String getNdCsnprc() {
		return ndCsnprc;
	}

	public void setNdCsnprc(String ndCsnprc) {
		this.ndCsnprc = ndCsnprc;
	}

	public String getNdReqcsnval() {
		return ndReqcsnval;
	}

	public void setNdReqcsnval(String ndReqcsnval) {
		this.ndReqcsnval = ndReqcsnval;
	}

	public String getNdScflonno() {
		return ndScflonno;
	}

	public void setNdScflonno(String ndScflonno) {
		this.ndScflonno = ndScflonno;
	}

	@Override
	public String toString() {
		return "ReceivingDetail [id=" + id + ", ndNo=" + ndNo + ", ndIdtplanno=" + ndIdtplanno + ", ndIdtplannm="
				+ ndIdtplannm + ", ndCmdcode=" + ndCmdcode + ", ndCmdname=" + ndCmdname + ", ndCsnnum=" + ndCsnnum
				+ ", ndUnit=" + ndUnit + ", ndCsnprc=" + ndCsnprc + ", ndReqcsnval=" + ndReqcsnval + ", ndScflonno="
				+ ndScflonno + ", ndMtgcntno=" + ndMtgcntno + ", ndRemark=" + ndRemark + ", ndVin=" + ndVin
				+ ", ndHgzno=" + ndHgzno + ", ndCarprice=" + ndCarprice + ", ndLoancode=" + ndLoancode + "]";
	}

}
