package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 质物入库商品表
 */
public class Commodity implements Serializable{

	private static final long serialVersionUID = -1582947405226599731L;

	private int cmId;//质物入库商品表主键id
	private String cmCmdcode;//商品代码
	private int cmStknum;//入库数量
	private BigDecimal cmIstkprc;//入库单价
	private String cmWhcode;//仓库代码
	private String cmVin;//车架号
	private String cmHgzno;//合格证编号
	private BigDecimal cmCarprice;//车价
	private String cmLoancode;// 融资编号
	private int cmGaid;//质物入库表ID
	public int getCmId() {
		return cmId;
	}
	public void setCmId(int cmId) {
		this.cmId = cmId;
	}
	public String getCmCmdcode() {
		return cmCmdcode;
	}
	public void setCmCmdcode(String cmCmdcode) {
		this.cmCmdcode = cmCmdcode;
	}
	public int getCmStknum() {
		return cmStknum;
	}
	public void setCmStknum(int cmStknum) {
		this.cmStknum = cmStknum;
	}
	public BigDecimal getCmIstkprc() {
		return cmIstkprc;
	}
	public void setCmIstkprc(BigDecimal cmIstkprc) {
		this.cmIstkprc = cmIstkprc;
	}
	public String getCmWhcode() {
		return cmWhcode;
	}
	public void setCmWhcode(String cmWhcode) {
		this.cmWhcode = cmWhcode;
	}
	public String getCmVin() {
		return cmVin;
	}
	public void setCmVin(String cmVin) {
		this.cmVin = cmVin;
	}
	public String getCmHgzno() {
		return cmHgzno;
	}
	public void setCmHgzno(String cmHgzno) {
		this.cmHgzno = cmHgzno;
	}
	public BigDecimal getCmCarprice() {
		return cmCarprice;
	}
	public void setCmCarprice(BigDecimal cmCarprice) {
		this.cmCarprice = cmCarprice;
	}
	public String getCmLoancode() {
		return cmLoancode;
	}
	public void setCmLoancode(String cmLoancode) {
		this.cmLoancode = cmLoancode;
	}
	public int getCmGaid() {
		return cmGaid;
	}
	public void setCmGaid(int cmGaid) {
		this.cmGaid = cmGaid;
	}
	@Override
	public String toString() {
		return "Commodity [cmId=" + cmId + ", cmCmdcode=" + cmCmdcode
				+ ", cmStknum=" + cmStknum + ", cmIstkprc=" + cmIstkprc
				+ ", cmWhcode=" + cmWhcode + ", cmVin=" + cmVin + ", cmHgzno="
				+ cmHgzno + ", cmCarprice=" + cmCarprice + ", cmLoancode="
				+ cmLoancode + ", cmGaid=" + cmGaid + "]";
	}
	
	
}
