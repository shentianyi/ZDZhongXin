package com.zd.csms.zxbank.bean;

/**
 * 通知推送表
 * @author yixiangyang
 *
 */
public class Notice {
	private int ntId;//通知推送表主键
	private int ntType;//通知书类型1收货2移库3质押
	private String ntNo;//通知书编号
	private String ntStdate;//通知接收时间
	private String ntEnddate;//通知书跟新时间
	private int ntFailflag;//0接收失败1已接收
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
	public String getNtStdate() {
		return ntStdate;
	}
	public void setNtStdate(String ntStdate) {
		this.ntStdate = ntStdate.substring(0,ntStdate.length()-2);
	}
	public String getNtEnddate() {
		return ntEnddate;
	}
	public void setNtEnddate(String ntEnddate) {
		this.ntEnddate = ntEnddate.substring(0,ntEnddate.length()-2);
	}
	public int getNtFailflag() {
		return ntFailflag;
	}
	public void setNtFailflag(int ntFailflag) {
		this.ntFailflag = ntFailflag;
	}
	
	
}
