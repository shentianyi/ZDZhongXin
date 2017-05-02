package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 通知推送
 * @author caizhuo
 *
 */
@table(name="ZX_NOTICE")
public class Notice {
	private int nid;//--通知推送表主键
	private String ntcno;//--通知书编号
	private int ntctp;//--通知书类型1、收货	2、移库   3、解除质押 	4、质物与融资关系变更通知
	private String ntbranchid;//--分行id 新增
	private Date ntcdate;//--通知书接收时间
	private int nttotnum;//--总记录数
	private int ntfailflag;// --0回执失败，1读取，2读取成功
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNtcno() {
		return ntcno;
	}
	public void setNtcno(String ntcno) {
		this.ntcno = ntcno;
	}
	public int getNtctp() {
		return ntctp;
	}
	public void setNtctp(int ntctp) {
		this.ntctp = ntctp;
	}
	public String getNtbranchid() {
		return ntbranchid;
	}
	public void setNtbranchid(String ntbranchid) {
		this.ntbranchid = ntbranchid;
	}
	public Date getNtcdate() {
		return ntcdate;
	}
	public void setNtcdate(Date ntcdate) {
		this.ntcdate = ntcdate;
	}
	public int getNttotnum() {
		return nttotnum;
	}
	public void setNttotnum(int nttotnum) {
		this.nttotnum = nttotnum;
	}
	public int getNtfailflag() {
		return ntfailflag;
	}
	public void setNtfailflag(int ntfailflag) {
		this.ntfailflag = ntfailflag;
	}
	@Override
	public String toString() {
		return "Notice [nid=" + nid + ", ntcno=" + ntcno + ", ntctp=" + ntctp
				+ ", ntbranchid=" + ntbranchid + ", ntcdate=" + ntcdate
				+ ", nttotnum=" + nttotnum + ", ntfailflag=" + ntfailflag + "]";
	}
	
}
