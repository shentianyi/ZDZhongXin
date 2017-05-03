package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zd.core.annotation.table;

/**
 * 移库详情
 * @author duyong
 *
 */
@table(name="zx_movedetail")
public class MoveDetail implements Serializable{

	private static final long serialVersionUID = -1438252117901030215L;
	
	private int mdId;//序列ID
	private String mdNo;//通知编号
	private String mdRemoveoutno;//移出仓库编号
	private String mdRemoveinno;//移入仓库编号
	private String mdWareno;//商品代码
	private String mdMovenumber;//移库数量
	private String mdChassisno;//车架号
	private String mdCertificationno;//合格证编号
	private String mdCarprice;//车价
	
	public int getMdId() {
		return mdId;
	}
	public void setMdId(int mdId) {
		this.mdId = mdId;
	}
	public String getMdNo() {
		return mdNo;
	}
	public void setMdNo(String mdNo) {
		this.mdNo = mdNo;
	}
	public String getMdRemoveoutno() {
		return mdRemoveoutno;
	}
	public void setMdRemoveoutno(String mdRemoveoutno) {
		this.mdRemoveoutno = mdRemoveoutno;
	}
	public String getMdRemoveinno() {
		return mdRemoveinno;
	}
	public void setMdRemoveinno(String mdRemoveinno) {
		this.mdRemoveinno = mdRemoveinno;
	}
	public String getMdWareno() {
		return mdWareno;
	}
	public void setMdWareno(String mdWareno) {
		this.mdWareno = mdWareno;
	}
	public String getMdChassisno() {
		return mdChassisno;
	}
	public void setMdChassisno(String mdChassisno) {
		this.mdChassisno = mdChassisno;
	}
	public String getMdCertificationno() {
		return mdCertificationno;
	}
	public void setMdCertificationno(String mdCertificationno) {
		this.mdCertificationno = mdCertificationno;
	}
	
	public String getMdMovenumber() {
		return mdMovenumber;
	}
	public void setMdMovenumber(String mdMovenumber) {
		this.mdMovenumber = mdMovenumber;
	}
	public String getMdCarprice() {
		return mdCarprice;
	}
	public void setMdCarprice(String mdCarprice) {
		this.mdCarprice = mdCarprice;
	}
	@Override
	public String toString() {
		return "MoveDetail [mdId=" + mdId + ", mdNo=" + mdNo
				+ ", mdRemoveoutno=" + mdRemoveoutno + ", mdRemoveinno="
				+ mdRemoveinno + ", mdWareno=" + mdWareno + ", mdMovenumber="
				+ mdMovenumber + ", mdChassisno=" + mdChassisno
				+ ", mdCertificationno=" + mdCertificationno + ", mdCarprice="
				+ mdCarprice + "]";
	}
	
	
}
