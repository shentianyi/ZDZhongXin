package com.zd.csms.marketing.model;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 监管费变更流转单
 * @author licheng
 *
 */
@table(name="market_OperationExpenseChange")
public class ExpenseChangeVO {
	private int id ;
	private int dealerId;//经销商主键ID
	private String changemoney;//变更后监管费（元/年）
	private Date changeDate;//变更日期
	private String remark;//备注
	private int nextApproval;//下一个审批角色
	private int approvalState;//是否审批通过 1：是 2：否 3:待审批 4:未提交
	
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

	public String getChangemoney() {
		return changemoney;
	}
	public void setChangemoney(String changemoney) {
		this.changemoney = changemoney;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
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
	public int getApprovalState() {
		return approvalState;
	}
	public void setApprovalState(int approvalState) {
		this.approvalState = approvalState;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
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
