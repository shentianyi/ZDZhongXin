package com.zd.csms.marketing.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_supervise_arrears")
public class SuperviseArrearsVO implements Serializable {

	/**
	 * 欠费台账
	 */
	private static final long serialVersionUID = 8496981817508263107L;
	
	private int id;
	private int distribid;//经销商id
	private String financial_institution;//金融机构
	private Date arrears_begin;//欠费起始时间
	private Date arrears_end;//欠费结束时间
	private String arrears_money;//欠费金额
	private String arrears_user;//待付款联系人
	private String arrears_phone;//联系方式
	private Date follow_date;//跟进时间
	private String follow_user;//跟进人员
	private String follow_result;//跟进结果
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDistribid() {
		return distribid;
	}
	public void setDistribid(int distribid) {
		this.distribid = distribid;
	}
	public String getFinancial_institution() {
		return financial_institution;
	}
	public void setFinancial_institution(String financialInstitution) {
		financial_institution = financialInstitution;
	}
	public Date getArrears_begin() {
		return arrears_begin;
	}
	public void setArrears_begin(Date arrearsBegin) {
		arrears_begin = arrearsBegin;
	}
	public Date getArrears_end() {
		return arrears_end;
	}
	public void setArrears_end(Date arrearsEnd) {
		arrears_end = arrearsEnd;
	}
	public String getArrears_money() {
		return arrears_money;
	}
	public void setArrears_money(String arrearsMoney) {
		arrears_money = arrearsMoney;
	}
	public String getArrears_user() {
		return arrears_user;
	}
	public void setArrears_user(String arrearsUser) {
		arrears_user = arrearsUser;
	}
	public String getArrears_phone() {
		return arrears_phone;
	}
	public void setArrears_phone(String arrearsPhone) {
		arrears_phone = arrearsPhone;
	}
	public Date getFollow_date() {
		return follow_date;
	}
	public void setFollow_date(Date followDate) {
		follow_date = followDate;
	}
	public String getFollow_user() {
		return follow_user;
	}
	public void setFollow_user(String followUser) {
		follow_user = followUser;
	}
	public String getFollow_result() {
		return follow_result;
	}
	public void setFollow_result(String followResult) {
		follow_result = followResult;
	}
	
	

}
