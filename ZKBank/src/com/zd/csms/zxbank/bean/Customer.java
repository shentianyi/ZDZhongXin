package com.zd.csms.zxbank.bean;
/**
 * 
 * 客户信息表
 */
public class Customer {
	private int custId;//客户查询表主键
	private String custNo;//客户号
	private String custOrganizationcode;//组织机构代码
	private String custName;//客户名称
	private String custCreateDate;//数据同步时间
	private String custUpdateDate;//数据同步更新时间
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustOrganizationcode() {
		return custOrganizationcode;
	}
	public void setCustOrganizationcode(String custOrganizationcode) {
		this.custOrganizationcode = custOrganizationcode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustCreateDate() {
		return custCreateDate;
	}
	public void setCustCreateDate(String custCreateDate) {
		this.custCreateDate = custCreateDate.substring(0,custCreateDate.length()-2);
	}
	public String getCustUpdateDate() {
		return custUpdateDate;
	}
	public void setCustUpdateDate(String custUpdateDate) {
		this.custUpdateDate = custUpdateDate.substring(0,custUpdateDate.length()-2);
	}
	
	
}
