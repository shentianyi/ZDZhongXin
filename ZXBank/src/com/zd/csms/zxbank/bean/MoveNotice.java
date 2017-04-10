package com.zd.csms.zxbank.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 移库通知
 * @author duyong
 * 
 */
public class MoveNotice implements Serializable{

	private static final long serialVersionUID = 4059920180306182093L;
	
	private int mnId;//序列ID
	private String mnNo;//通知书编号
	private String mnOperorg;//经办行
	private String mnSupervisename;//监管企业名称
	private String mnMovedate;//移库申请日期
	private String mnLoncpname;//借款企业名称
	private String mnNoticedate;//通知日期
	private int mnTotnum;//总记录数
	private Date mnCreatedate;//同步时间
	private Date mnUpdatedate;//更新数据时间
	public int getMnId() {
		return mnId;
	}
	public void setMnId(int mnId) {
		this.mnId = mnId;
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
	public String getMnMovedate() {
		return mnMovedate;
	}
	public void setMnMovedate(String mnMovedate) {
		this.mnMovedate = mnMovedate;
	}
	public String getMnLoncpname() {
		return mnLoncpname;
	}
	public void setMnLoncpname(String mnLoncpname) {
		this.mnLoncpname = mnLoncpname;
	}
	public String getMnNoticedate() {
		return mnNoticedate;
	}
	public void setMnNoticedate(String mnNoticedate) {
		this.mnNoticedate = mnNoticedate;
	}
	public int getMnTotnum() {
		return mnTotnum;
	}
	public void setMnTotnum(int mnTotnum) {
		this.mnTotnum = mnTotnum;
	}
	public Date getMnCreatedate() {
		return mnCreatedate;
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
	
	@Override
	public String toString() {
		return "Movenotice [mnId=" + mnId + ", mnNo=" + mnNo + ", mnOperorg=" + mnOperorg + ", mnSupervisename=" + mnSupervisename + ", mnMovedate="
				+ mnMovedate + ", mnLoncpname=" + mnLoncpname + ", mnNoticedate=" + mnNoticedate + ", mnTotnum=" + mnTotnum + ", mnCreatedate="
				+ mnCreatedate + ", mnUpdatedate=" + mnUpdatedate + "]";
	}
	
}
