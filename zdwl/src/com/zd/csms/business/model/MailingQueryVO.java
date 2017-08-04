package com.zd.csms.business.model;

import java.util.Date;

public class MailingQueryVO {

	private int id;
	private int superviseid;//监管员id
	private String mailnature;//快递性质
	private Date mailtime;//邮寄时间
	private String express;//快递公司
	private String express_num;//单号
	private String des;//内容
	
	private Date mailtimebegin;
	private Date mailtimeend;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSuperviseid() {
		return superviseid;
	}
	public void setSuperviseid(int superviseid) {
		this.superviseid = superviseid;
	}
	public String getMailnature() {
		return mailnature;
	}
	public void setMailnature(String mailnature) {
		this.mailnature = mailnature;
	}
	public Date getMailtime() {
		return mailtime;
	}
	public void setMailtime(Date mailtime) {
		this.mailtime = mailtime;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpress_num() {
		return express_num;
	}
	public void setExpress_num(String expressNum) {
		express_num = expressNum;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getMailtimebegin() {
		return mailtimebegin;
	}
	public void setMailtimebegin(Date mailtimebegin) {
		this.mailtimebegin = mailtimebegin;
	}
	public Date getMailtimeend() {
		return mailtimeend;
	}
	public void setMailtimeend(Date mailtimeend) {
		this.mailtimeend = mailtimeend;
	}
	
	
}
