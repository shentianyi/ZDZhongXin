package com.zd.csms.messageAndWaring.message.web.form.business;


import java.util.Date;

import com.zd.csms.messageAndWaring.message.web.form.MessageManagerForm;

/**
 * 业务部-信息提醒和预警表单
 * 
 * @author zhangzhicheng
 *
 */
public class BusinessMessageForm extends MessageManagerForm {
	private static final long serialVersionUID = 5515875865289251097L;
	
	private Integer dealerId;//经销商Id
	private String dealerName;//经销商名称
	private Integer oneBankId;//总行/金融机构
	private Integer twoBankId;//分行/分支机构
	private Integer threeBankId;//支行
	private Integer brandId;//品牌Id
	private String brandName;//品牌名称
	
	private String draft_num;//汇票号
	private Date billing_date_begin;//开票日开始
	private Date billing_date_end;//开票日结束
	private Date due_date_begin;//到期日开始
	private Date due_date_end;//到期日结束
	private Date move_date_begin;//移动时间开始
	private Date move_date_end;//移动时间结束
	private String vin;// 车架号
	
	
	public Integer getDealerId() {
		return dealerId;
	}
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Integer getOneBankId() {
		return oneBankId;
	}
	public void setOneBankId(Integer oneBankId) {
		this.oneBankId = oneBankId;
	}
	public Integer getTwoBankId() {
		return twoBankId;
	}
	public void setTwoBankId(Integer twoBankId) {
		this.twoBankId = twoBankId;
	}
	public Integer getThreeBankId() {
		return threeBankId;
	}
	public void setThreeBankId(Integer threeBankId) {
		this.threeBankId = threeBankId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getDraft_num() {
		return draft_num;
	}
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}
	public Date getBilling_date_begin() {
		return billing_date_begin;
	}
	public void setBilling_date_begin(Date billing_date_begin) {
		this.billing_date_begin = billing_date_begin;
	}
	public Date getBilling_date_end() {
		return billing_date_end;
	}
	public void setBilling_date_end(Date billing_date_end) {
		this.billing_date_end = billing_date_end;
	}
	public Date getDue_date_begin() {
		return due_date_begin;
	}
	public void setDue_date_begin(Date due_date_begin) {
		this.due_date_begin = due_date_begin;
	}
	public Date getDue_date_end() {
		return due_date_end;
	}
	public void setDue_date_end(Date due_date_end) {
		this.due_date_end = due_date_end;
	}
	public Date getMove_date_begin() {
		return move_date_begin;
	}
	public void setMove_date_begin(Date move_date_begin) {
		this.move_date_begin = move_date_begin;
	}
	public Date getMove_date_end() {
		return move_date_end;
	}
	public void setMove_date_end(Date move_date_end) {
		this.move_date_end = move_date_end;
	}
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
}