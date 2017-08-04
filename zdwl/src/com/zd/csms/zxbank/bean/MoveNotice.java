package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 移库通知
 * @author duyong
 * 
 */
@table(name="zx_movenotice")
public class MoveNotice implements Serializable{

	private static final long serialVersionUID = 4059920180306182093L;
	
	private int id;//序列ID
	private String mnNo;//通知书编号
	private String mnOperorg;//经办行
	private String mnSupervisename;//监管企业名称
	private String mnMovedate;//移库申请日期
	private String mnLoncpname;//借款企业名称
	private String mnNoticedate;//通知日期
	private String mnTotnum;//总记录数
	private Date mnCreatedate;//同步时间
	private Date mnUpdatedate;//更新数据时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMnNo() {
		return mnNo;
	}
	public void setMnNo(String mnNo) {
		this.mnNo = mnNo;
	}
	public String getMnOperorg() {
		return mnOperorg;
	}
	public void setMnOperorg(String mnOperorg) {
		this.mnOperorg = mnOperorg;
	}
	public String getMnSupervisename() {
		return mnSupervisename;
	}
	public void setMnSupervisename(String mnSupervisename) {
		this.mnSupervisename = mnSupervisename;
	}
	public String getMnLoncpname() {
		return mnLoncpname;
	}
	public void setMnLoncpname(String mnLoncpname) {
		this.mnLoncpname = mnLoncpname;
	}
	public Date getMnCreatedate() {
		return mnCreatedate;
	}
	
	public String getMnTotnum() {
		return mnTotnum;
	}
	public void setMnTotnum(String mnTotnum) {
		this.mnTotnum = mnTotnum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setMnCreatedate(Date mnCreatedate) {
		this.mnCreatedate = mnCreatedate;
	}
	public Date getMnUpdatedate() {
		return mnUpdatedate;
	}
	public void setMnUpdatedate(Date mnUpdatedate) {
		this.mnUpdatedate = mnUpdatedate;
	}
	
	public String getMnMovedate() {
		return mnMovedate;
	}
	public void setMnMovedate(String mnMovedate) {
		this.mnMovedate = mnMovedate;
	}
	public String getMnNoticedate() {
		return mnNoticedate;
	}
	public void setMnNoticedate(String mnNoticedate) {
		this.mnNoticedate = mnNoticedate;
	}
	@Override
	public String toString() {
		return "Movenotice [id=" + id + ", mnNo=" + mnNo + ", mnOperorg=" + mnOperorg + ", mnSupervisename=" + mnSupervisename + ", mnMovedate="
				+ mnMovedate + ", mnLoncpname=" + mnLoncpname + ", mnNoticedate=" + mnNoticedate + ", mnTotnum=" + mnTotnum + ", mnCreatedate="
				+ mnCreatedate + ", mnUpdatedate=" + mnUpdatedate + "]";
	}
	
}
