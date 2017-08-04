package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="T_TRANSFER_REPOSITORY")
public class TransferRepositoryVO implements Serializable {

	/**
	 * 经销商监管员调动列表表
	 */
	private static final long serialVersionUID = -7934939329442326385L;
	/**
	 * 主键ID
	 */
	private int id;                      	
	/**
	 * 经销商ID
	 */
	private int dealerId;       
	/**
	 * 监管员ID
	 */
	private int repositoryId;
	/**
	 * 调入时间
	 */
	private Date entryTime;
	/**
	 * 调入性质
	 */
	private String entryNature;
	/**
	 * 监管员来源   1：属地，2：外派
	 */
	private int supervisorSource;
	
	/* 离岗时间 */
	private Date leaveTime;
	
	/* 离岗性质 */
	private String leaveNature;
	
	
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getLeaveNature() {
		return leaveNature;
	}
	public void setLeaveNature(String leaveNature) {
		this.leaveNature = leaveNature;
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
	public int getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public String getEntryNature() {
		return entryNature;
	}
	public void setEntryNature(String entryNature) {
		this.entryNature = entryNature;
	}
	public int getSupervisorSource() {
		return supervisorSource;
	}
	public void setSupervisorSource(int supervisorSource) {
		this.supervisorSource = supervisorSource;
	}
	
}
