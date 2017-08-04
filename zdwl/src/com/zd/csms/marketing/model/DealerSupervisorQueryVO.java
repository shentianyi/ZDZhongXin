package com.zd.csms.marketing.model;

public class DealerSupervisorQueryVO {

	private int id;
	private int dealerId;//经销商Id
	private int supervisorId;//监管员ID
	private int bankId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	@Override
	public String toString() {
		return "DealerSupervisorQueryVO [id=" + id + ", dealerId=" + dealerId
				+ ", supervisorId=" + supervisorId + ", bankId=" + bankId + "]";
	}
	
	
	
	
}
