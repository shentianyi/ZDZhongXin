package com.zd.csms.marketing.model;

import java.util.Date;

public class AgreementSendQueryVO {

	private int id;
	private int distribid;//经销商id
	private String financial_institution;//金融机构
	private String agreement_num;//协议编号
	private Date agreement_date;//协议邮寄时间
	private String financial_user;//金融机构联系人
	private String financial_phone;//联系方式
	private String send_address;//邮寄地址
	private Date back_date;//预计回收时间
	
	private int brandid;//品牌
	private String province;//省
	private String city;//市
	private String county;//区
	
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
	public String getAgreement_num() {
		return agreement_num;
	}
	public void setAgreement_num(String agreementNum) {
		agreement_num = agreementNum;
	}
	public Date getAgreement_date() {
		return agreement_date;
	}
	public void setAgreement_date(Date agreementDate) {
		agreement_date = agreementDate;
	}
	public String getFinancial_user() {
		return financial_user;
	}
	public void setFinancial_user(String financialUser) {
		financial_user = financialUser;
	}
	public String getFinancial_phone() {
		return financial_phone;
	}
	public void setFinancial_phone(String financialPhone) {
		financial_phone = financialPhone;
	}
	public String getSend_address() {
		return send_address;
	}
	public void setSend_address(String sendAddress) {
		send_address = sendAddress;
	}
	public Date getBack_date() {
		return back_date;
	}
	public void setBack_date(Date backDate) {
		back_date = backDate;
	}
	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	
	
}
