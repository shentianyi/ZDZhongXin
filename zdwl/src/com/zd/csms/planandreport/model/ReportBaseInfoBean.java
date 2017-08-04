package com.zd.csms.planandreport.model;

/**
 * 视频检查报告基本信息
 *
 */
public class ReportBaseInfoBean {
	private String dealerName;//经销商信息
	private String bankName;//金融机构名称
	private String brand;//品牌
	private String supervisorName;//监管员
	private String staffNo;//工号
	private String supervisionMode;//操作模式
	private	Integer stockNum;//库存数
	private Integer wayNum;//在途
	private Integer certificationNum;//合格证数量
	private Integer keyNum;//钥匙数
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public Integer getStockNum() {
		return stockNum;
	}
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	public Integer getCertificationNum() {
		return certificationNum;
	}
	public void setCertificationNum(Integer certificationNum) {
		this.certificationNum = certificationNum;
	}
	public Integer getKeyNum() {
		return keyNum;
	}
	public void setKeyNum(Integer keyNum) {
		this.keyNum = keyNum;
	}
	public String getSupervisionMode() {
		return supervisionMode;
	}
	public void setSupervisionMode(String supervisionMode) {
		this.supervisionMode = supervisionMode;
	}
	public Integer getWayNum() {
		return wayNum;
	}
	public void setWayNum(Integer wayNum) {
		this.wayNum = wayNum;
	}
	
	
	
}
