package com.zd.csms.marketing.model;

import java.util.Date;

import com.zd.core.annotation.table;

@table(name="MARKET_DEALER_SUPERVISOR")
public class DealerSupervisorVO {
	private int id;
	private int dealerId;//经销商Id
	private int repositoryId;//储备库Id
	private int bankId;
	private String qq;//qq
	private String qqPwd;//qq密码
	private int supervisorAttribute;//监管员属性
	private int supervisorSource;//监管员来源
	private Date bindtime; //绑定时间
	
	
	public Date getBindtime() {
		return bindtime;
	}
	public void setBindtime(Date bindtime) {
		this.bindtime = bindtime;
	}
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
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getQqPwd() {
		return qqPwd;
	}
	public void setQqPwd(String qqPwd) {
		this.qqPwd = qqPwd;
	}
	public int getSupervisorAttribute() {
		return supervisorAttribute;
	}
	public void setSupervisorAttribute(int supervisorAttribute) {
		this.supervisorAttribute = supervisorAttribute;
	}
	public int getSupervisorSource() {
		return supervisorSource;
	}
	public void setSupervisorSource(int supervisorSource) {
		this.supervisorSource = supervisorSource;
	}
	public int getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
}
