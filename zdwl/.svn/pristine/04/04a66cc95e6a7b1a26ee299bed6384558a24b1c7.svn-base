package com.zd.csms.marketing.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


import com.zd.core.annotation.table;

@table(name="t_modechange_log")
public class ModeChangeLogVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8491168354257718974L;

	
	private int id;		//主键
	private int dealerId;	//经销商ID
	private String beforeOperationMode;	//变更前操作模式
	private String lastOperationMode;		//变更后操作模式
	private String beforeSuperviseMoneyDate;	//变更前标准费用
	private String lastSuperviseMoneyDate;	//变更后标准费用
	private Date modifyTime;			//操作模式变更时间
	private Date changeSuperviseMoneyDate;	//监管费变更时间
	private Integer createUserId;//创建人id
	private Integer lastModifyUserId;//最后修改人id
	private Date createDate;//创建时间
	private Date lastModifyDate;//最后修改时间
	
	
	
	
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
	public String getBeforeOperationMode() {
		return beforeOperationMode;
	}
	public void setBeforeOperationMode(String beforeOperationMode) {
		this.beforeOperationMode = beforeOperationMode;
	}
	public String getLastOperationMode() {
		return lastOperationMode;
	}
	public void setLastOperationMode(String lastOperationMode) {
		this.lastOperationMode = lastOperationMode;
	}
	public String getBeforeSuperviseMoneyDate() {
		return beforeSuperviseMoneyDate;
	}
	public void setBeforeSuperviseMoneyDate(String beforeSuperviseMoneyDate) {
		this.beforeSuperviseMoneyDate = beforeSuperviseMoneyDate;
	}
	public String getLastSuperviseMoneyDate() {
		return lastSuperviseMoneyDate;
	}
	public void setLastSuperviseMoneyDate(String lastSuperviseMoneyDate) {
		this.lastSuperviseMoneyDate = lastSuperviseMoneyDate;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
	
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(Integer lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public Date getChangeSuperviseMoneyDate() {
		return changeSuperviseMoneyDate;
	}
	public void setChangeSuperviseMoneyDate(Date changeSuperviseMoneyDate) {
		this.changeSuperviseMoneyDate = changeSuperviseMoneyDate;
	}
	public ModeChangeLogVO() {
		super();
	}
	public ModeChangeLogVO(int id, int dealerId, String beforeOperationMode,
			String lastOperationMode, String beforeSuperviseMoneyDate,
			String lastSuperviseMoneyDate, Date modifyTime,
			Date changeSuperviseMoneyDate, Integer createUserId,
			Integer lastModifyUserId, Date createDate, Date lastModifyDate) {
		super();
		this.id = id;
		this.dealerId = dealerId;
		this.beforeOperationMode = beforeOperationMode;
		this.lastOperationMode = lastOperationMode;
		this.beforeSuperviseMoneyDate = beforeSuperviseMoneyDate;
		this.lastSuperviseMoneyDate = lastSuperviseMoneyDate;
		this.modifyTime = modifyTime;
		this.changeSuperviseMoneyDate = changeSuperviseMoneyDate;
		this.createUserId = createUserId;
		this.lastModifyUserId = lastModifyUserId;
		this.createDate = createDate;
		this.lastModifyDate = lastModifyDate;
	}
	@Override
	public String toString() {
		return "ModeChangeLogVO [id=" + id + ", dealerId=" + dealerId
				+ ", beforeOperationMode=" + beforeOperationMode
				+ ", lastOperationMode=" + lastOperationMode
				+ ", beforeSuperviseMoneyDate=" + beforeSuperviseMoneyDate
				+ ", lastSuperviseMoneyDate=" + lastSuperviseMoneyDate
				+ ", modifyTime=" + modifyTime + ", changeSuperviseMoneyDate="
				+ changeSuperviseMoneyDate + ", createUserId=" + createUserId
				+ ", lastModifyUserId=" + lastModifyUserId + ", createDate="
				+ createDate + ", lastModifyDate=" + lastModifyDate + "]";
	}
	
	
}
