package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 通知推送表
 * @author yixiangyang
 *
 */
@table(name="ZX_NOTICE")
public class Notice {
	private int ntId;//通知推送表主键
	private int ntType;//通知书类型1收货2移库3质押
	private String ntNo;//通知书编号
	private String ntLonentid;//借款企业id 新增
	private String ntBranchid;//分行id 新增
	private Date ntStdate;//通知发送时间
	private Date ntEnddate;//通知书更新时间
	private int ntFailflag;//1回执失败2读取失败3读取成功
	public int getNtId() {
		return ntId;
	}
	public void setNtId(int ntId) {
		this.ntId = ntId;
	}
	public int getNtType() {
		return ntType;
	}
	public void setNtType(int ntType) {
		this.ntType = ntType;
	}
	public String getNtNo() {
		return ntNo;
	}
	public void setNtNo(String ntNo) {
		this.ntNo = ntNo;
	}
	
	
	public Date getNtStdate() {
		return ntStdate;
	}
	public void setNtStdate(Date ntStdate) {
		this.ntStdate = ntStdate;
	}
	public Date getNtEnddate() {
		return ntEnddate;
	}
	public void setNtEnddate(Date ntEnddate) {
		this.ntEnddate = ntEnddate;
	}
	public int getNtFailflag() {
		return ntFailflag;
	}
	public void setNtFailflag(int ntFailflag) {
		this.ntFailflag = ntFailflag;
	}
	public String getNtLonentid() {
		return ntLonentid;
	}
	public void setNtLonentid(String ntLonentid) {
		this.ntLonentid = ntLonentid;
	}
	public String getNtBranchid() {
		return ntBranchid;
	}
	public void setNtBranchid(String ntBranchid) {
		this.ntBranchid = ntBranchid;
	}
	@Override
	public String toString() {
		return "Notice [ntId=" + ntId + ", ntType=" + ntType + ", ntNo=" + ntNo
				+ ", ntLonentid=" + ntLonentid + ", ntBranchid=" + ntBranchid
				+ ", ntStdate=" + ntStdate + ", ntEnddate=" + ntEnddate
				+ ", ntFailflag=" + ntFailflag + "]";
	}
	
	
}
