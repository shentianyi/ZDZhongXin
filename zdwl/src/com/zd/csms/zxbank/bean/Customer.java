package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 
 * 客户信息表
 */
@table(name = "zx_customer")
public class Customer {
	private int custId;//客户查询表主键
	private String custNo;//客户号
	private String custOrganizationcode;//组织机构代码
	private String custName;//客户名称
	private int cusConnumber;//质物次数
	private Date custCreateDate;//数据同步创建时间
	private Date custUpdateDate;//数据同步更新时间

	public Customer() {
	}

	public Customer(String orgcode) {
		this.custOrganizationcode = orgcode;
	}

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

	public Date getCustCreateDate() {
		return custCreateDate;
	}

	public void setCustCreateDate(Date custCreateDate) {
		this.custCreateDate = custCreateDate;
	}

	public Date getCustUpdateDate() {
		return custUpdateDate;
	}

	public void setCustUpdateDate(Date custUpdateDate) {
		this.custUpdateDate = custUpdateDate;
	}

	public int getCusConnumber() {
		return cusConnumber;
	}

	public void setCusConnumber(int cusConnumber) {
		this.cusConnumber = cusConnumber;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custNo=" + custNo + ", custOrganizationcode=" + custOrganizationcode
				+ ", custName=" + custName + ", custCreateDate=" + custCreateDate + ", custUpdateDate="
				+ custUpdateDate + "]";
	}
}
