package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_resign_apply")
public class ResignApplyVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3873468058667644441L;
	/**
	 * 
	 */
	
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
	 * 身份证号
	 */
	private String idNumber;
	/**
	 * 属性
	 */
	private String attribute;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 期望离职时间
	 */
	private Date expectResignTime;
	/**
	 * 离职原因
	 */
	private String resignReason;
	/**
	 * 离职后手机号
	 */
	private String mobile;
	/**
	 * 离职后邮箱
	 */
	private String email;
	/**
	 * 上传图片ID
	 */
	private int pictureId;
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
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public Date getExpectResignTime() {
		return expectResignTime;
	}
	public void setExpectResignTime(Date expectResignTime) {
		this.expectResignTime = expectResignTime;
	}
	public String getResignReason() {
		return resignReason;
	}
	public void setResignReason(String resignReason) {
		this.resignReason = resignReason;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
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
