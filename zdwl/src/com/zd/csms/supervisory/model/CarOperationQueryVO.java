package com.zd.csms.supervisory.model;

import java.util.Date;

public class CarOperationQueryVO {

	private int id;
	private String cars;//车辆集合
	private int userid;//操作人
	private int type;//操作类型1导入2在库3出库4移动
	private Date createtime;//创建时间
	private int nextApproval;//下一个审批角色
	private int approvalState;//审批状态
	private Integer currRole;//当前角色
	private Integer pageType;//页面审批状态
	private String remark;//备注
	private String ids;
	private int distribid;
	private String distribName;//经销商
	private String jrjg;//金融机构
	private String brand;//品牌
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCars() {
		return cars;
	}
	public void setCars(String cars) {
		this.cars = cars;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	public Integer getCurrRole() {
		return currRole;
	}
	public void setCurrRole(Integer currRole) {
		this.currRole = currRole;
	}
	public Integer getPageType() {
		return pageType;
	}
	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getDistribid() {
		return distribid;
	}
	public void setDistribid(int distribid) {
		this.distribid = distribid;
	}
	public String getDistribName() {
		return distribName;
	}
	public void setDistribName(String distribName) {
		this.distribName = distribName;
	}
	public String getJrjg() {
		return jrjg;
	}
	public void setJrjg(String jrjg) {
		this.jrjg = jrjg;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
}
