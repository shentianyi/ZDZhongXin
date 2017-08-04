package com.zd.csms.marketing.model;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 操作模式变更流转单
 * @author licheng
 *
 */
@table(name="market_operationmodechange")
public class ModeChangeVO {
	private int id;
	private String changeOperationMode;//变更监管操作模式
	private Date changeDate;//变更时间
	private int moneyIsChange;//费用是否变更 1：是 2：否
	
	private String remark;//备注
	private int nextApproval;//下一审批角色
	private int approvalState;//审批状态1：通过 2：不通过 3：正在审批 4：未提交
	private int dealerId;//经销商主键ID
	
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
	
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public int getMoneyIsChange() {
		return moneyIsChange;
	}
	public void setMoneyIsChange(int moneyIsChange) {
		this.moneyIsChange = moneyIsChange;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getNextApproval() {
		return nextApproval;
	}
	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getChangeOperationMode() {
		return changeOperationMode;
	}
	public void setChangeOperationMode(String changeOperationMode) {
		this.changeOperationMode = changeOperationMode;
	}
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}

	public Integer getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(Integer lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
}
