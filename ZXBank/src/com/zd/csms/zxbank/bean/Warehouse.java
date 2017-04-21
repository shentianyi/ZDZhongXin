package com.zd.csms.zxbank.bean;

import java.util.Date;

import com.zd.core.annotation.table;

/**
 * 
 * 仓库信息表
 */
@table(name="ZX_WAREHOUSE")
public class Warehouse {
	private int Whid;//仓库查询表主键
	private String custNo;//客户号
	private String whName;//仓库名字
	private String whCode;//仓库代码
	private String whLevel;//仓库级别
	private String whOperorg;//经办行
	private String loncpname;//借款企业名称  新增
	private String whAddress;//仓库地址
	private String phone;//电话
	private Date createDate;//数据同步时间
	private Date updateDate;//数据更新时间
	
	public Warehouse() {
		super();
	}
	
	public Warehouse(String custNo) {
		this.custNo = custNo;
	}
	public int getWhid() {
		return Whid;
	}
	public void setWhid(int whid) {
		Whid = whid;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	public String getWhCode() {
		return whCode;
	}
	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}
	public String getWhLevel() {
		return whLevel;
	}
	public void setWhLevel(String whLevel) {
		this.whLevel = whLevel;
	}
	public String getWhOperorg() {
		return whOperorg;
	}
	public void setWhOperorg(String whOperorg) {
		this.whOperorg = whOperorg;
	}
	public String getWhAddress() {
		return whAddress;
	}
	public void setWhAddress(String whAddress) {
		this.whAddress = whAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getLoncpname() {
		return loncpname;
	}
	public void setLoncpname(String loncpname) {
		this.loncpname = loncpname;
	}
	@Override
	public String toString() {
		return "Warehouse [Whid=" + Whid + ", custNo=" + custNo + ", whName="
				+ whName + ", whCode=" + whCode + ", whLevel=" + whLevel
				+ ", whOperorg=" + whOperorg + ", loncpname=" + loncpname
				+ ", whAddress=" + whAddress + ", phone=" + phone
				+ ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}
}
