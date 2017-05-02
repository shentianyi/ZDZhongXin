package com.zd.csms.zxbank.bean;
import java.util.Date;

import com.zd.core.annotation.table;
/**
 * 
 * 仓库信息表
 */
@table(name="ZX_WAREHOUSE")
public class Warehouse {
	private int whid;//仓库查询表主键
	private String custNo;//客户号
	private String whlonentnm;//借款企业名称
	private String whName;//仓库名称
	private String whCode;//仓库代码 	银行端仓库代码（监管协议获取，需要去重）
	private int whLevel;//仓库级别（1:一级仓库，2:二级仓库）
	private String whOperorg;//经办行
	private String whAddress;//仓库地址
	private String phone;//电话
	private String lonentid;//借款企业id，ecif号
	private String whdistance;//二级监管仓库距离本库公里数
	private String whContacts;//监管仓库联系人
	private Date createDate;//数据创建时间
	private Date updateDate;//数据更新时间
	public Warehouse() {
		super();
	}
	public Warehouse(String custNo) {
		super();
		this.custNo = custNo;
	}
	public int getWhid() {
		return whid;
	}
	public void setWhid(int whid) {
		this.whid = whid;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getWhlonentnm() {
		return whlonentnm;
	}
	public void setWhlonentnm(String whlonentnm) {
		this.whlonentnm = whlonentnm;
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
	public int getWhLevel() {
		return whLevel;
	}
	public void setWhLevel(int whLevel) {
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
	public String getLonentid() {
		return lonentid;
	}
	public void setLonentid(String lonentid) {
		this.lonentid = lonentid;
	}
	public String getWhdistance() {
		return whdistance;
	}
	public void setWhdistance(String whdistance) {
		this.whdistance = whdistance;
	}
	public String getWhContacts() {
		return whContacts;
	}
	public void setWhContacts(String whContacts) {
		this.whContacts = whContacts;
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
	@Override
	public String toString() {
		return "Warehouse [whid=" + whid + ", custNo=" + custNo
				+ ", whlonentnm=" + whlonentnm + ", whName=" + whName
				+ ", whCode=" + whCode + ", whLevel=" + whLevel
				+ ", whOperorg=" + whOperorg + ", whAddress=" + whAddress
				+ ", phone=" + phone + ", lonentid=" + lonentid
				+ ", whdistance=" + whdistance + ", whContacts=" + whContacts
				+ ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}
	
}
