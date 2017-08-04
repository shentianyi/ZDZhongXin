package com.zd.csms.business.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_mailing")
public class MailingVO implements Serializable {

	/**
	 * 监管员邮寄明细
	 */
	private static final long serialVersionUID = 2878476310954969660L;
	private int id;
	private int superviseid;//监管员id
	private String mailnature;//快递性质
	private Date mailtime;//邮寄时间
	private String express;//快递公司
	private String express_num;//单号
	private String des;//内容
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
	
	
	
}
