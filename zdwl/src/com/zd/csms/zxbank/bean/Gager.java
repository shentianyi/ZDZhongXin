package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 
 * 质物入库表
 */
@table(name = "ZX_GAGER")
public class Gager implements Serializable {

	private static final long serialVersionUID = 7756611904828286632L;
	private int gaId;//质物入库id主键
	private String gaLonentno;//借款企业id
	private String gaLonentname;//借款企业名称
	private String gaOprtname;//操作人名称
	private String gaOrderno;//交易流水号
	private String gaPcgrtntno;//纸质担保合同编号
	private String gaCmgrtcntno;//动产质押担保合同编号 
	private String gawhCode;//仓库代码（必填项）
	private String gaRemark;//备注
	private String gaConfirmno;//质物监管确认书编号
	private int gaCount;//总记录数
	private int gaState;//质物入库状态
	private Date gaCreatedate;//数据创建时间

	public int getGaId() {
		return gaId;
	}

	public void setGaId(int gaId) {
		this.gaId = gaId;
	}

	public String getGaLonentno() {
		return gaLonentno;
	}

	public void setGaLonentno(String gaLonentno) {
		this.gaLonentno = gaLonentno;
	}

	public String getGaLonentname() {
		return gaLonentname;
	}

	public void setGaLonentname(String gaLonentname) {
		this.gaLonentname = gaLonentname;
	}

	public String getGaOprtname() {
		return gaOprtname;
	}

	public void setGaOprtname(String gaOprtname) {
		this.gaOprtname = gaOprtname;
	}

	public String getGaOrderno() {
		return gaOrderno;
	}

	public void setGaOrderno(String gaOrderno) {
		this.gaOrderno = gaOrderno;
	}

	public String getGaPcgrtntno() {
		return gaPcgrtntno;
	}

	public void setGaPcgrtntno(String gaPcgrtntno) {
		this.gaPcgrtntno = gaPcgrtntno;
	}

	public String getGaCmgrtcntno() {
		return gaCmgrtcntno;
	}

	public void setGaCmgrtcntno(String gaCmgrtcntno) {
		this.gaCmgrtcntno = gaCmgrtcntno;
	}

	public String getGaConfirmno() {
		return gaConfirmno;
	}

	public void setGaConfirmno(String gaConfirmno) {
		this.gaConfirmno = gaConfirmno;
	}

	public String getGaRemark() {
		return gaRemark;
	}

	public void setGaRemark(String gaRemark) {
		this.gaRemark = gaRemark;
	}

	public String getGawhCode() {
		return gawhCode;
	}

	public void setGawhCode(String gawhCode) {
		this.gawhCode = gawhCode;
	}

	public Date getGaCreatedate() {
		return gaCreatedate;
	}

	public void setGaCreatedate(Date gaCreatedate) {
		this.gaCreatedate = gaCreatedate;
	}

	public int getGaState() {
		return gaState;
	}

	public void setGaState(int gaState) {
		this.gaState = gaState;
	}

	public int getGaCount() {
		return gaCount;
	}

	public void setGaCount(int gaCount) {
		this.gaCount = gaCount;
	}

	@Override
	public String toString() {
		return "Gager [gaId=" + gaId + ", gaLonentno=" + gaLonentno + ", gaLonentname=" + gaLonentname
				+ ", gaOprtname=" + gaOprtname + ", gaOrderno=" + gaOrderno + ", gaPcgrtntno=" + gaPcgrtntno
				+ ", gaCmgrtcntno=" + gaCmgrtcntno + ", gawhCode=" + gawhCode + ", gaRemark=" + gaRemark
				+ ", gaConfirmno=" + gaConfirmno + ", gaCount=" + gaCount + ", gaState=" + gaState + ", gaCreatedate="
				+ gaCreatedate + "]";
	}

}
