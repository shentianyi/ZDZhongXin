package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_extrawork_apply")
public class ExtraworkApplyVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6173534339245880897L;
	/**
	 * 主键ID
	 */
	private int id;
	/**
	 * 申请人  储备库ID
	 */
	private int repositoryId;
	/**
	 * 申请人姓名
	 */
	private String name;
	/**
	 * 申请人员工号
	 */
	private String staffNo;
	/**
	 * 申请人所在省
	 */
	private String province;
	/**
	 * 申请人所在市
	 */
	private String city;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 加班天数
	 */
	private double extraWorkDays;
	/**
	 * 加班开始时间
	 */
	private Date startTime;
	/**
	 * 加班结束时间
	 */
	private Date endTime;
	/**
	 * 审批部门  1:监管员管理部,2:业务部,3:风控部,4:视频部,5:市场部
	 *
	 * private int approvalDepartment;
	 * /
	/**
	 * 加班事由
	 */
	private String reason;
	/**
	 * 状态  0：可编辑，1：不可编辑
	 */
	private int status;
	/**
	 * 下一个审批角色
	 */
	private int nextApproval;
	/**
	 * 审批状态 1：审批通过，2：审批不通过，3：正在审批，0：未提交 
	 */
	private int approvalState;
	/**
	 * 创建人
	 */
	private int createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private int lastModifiedUser;
	/**
	 * 最后修改时间
	 */
	private Date lastModifiedTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(int repositoryId) {
		this.repositoryId = repositoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
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
	public double getExtraWorkDays() {
		return extraWorkDays;
	}
	public void setExtraWorkDays(double extraWorkDays) {
		this.extraWorkDays = extraWorkDays;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(int lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

}
