package com.zd.csms.zxbank.web.bean;
/**
 * 
 * 仓库信息表
 */
public class WarehouseFar {
	private String whName;//仓库名字
	private String bkwhCode;//仓库代码
	private String whLevel;//仓库级别
	private String operOrg;//经办行
	private String lonNm;//借款企业名称  新增
	private String address;//仓库地址
	private String phone;//电话
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	public String getBkwhCode() {
		return bkwhCode;
	}
	public void setBkwhCode(String bkwhCode) {
		this.bkwhCode = bkwhCode;
	}
	public String getWhLevel() {
		return whLevel;
	}
	public void setWhLevel(String whLevel) {
		this.whLevel = whLevel;
	}
	public String getOperOrg() {
		return operOrg;
	}
	public void setOperOrg(String operOrg) {
		this.operOrg = operOrg;
	}
	public String getLonNm() {
		return lonNm;
	}
	public void setLonNm(String lonNm) {
		this.lonNm = lonNm;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "仓库名字:" + whName + "\n仓库代码:" + bkwhCode
				+ "\n仓库级别:" + whLevel + "\n经办行:" + operOrg + "\n借款企业名称:"
				+ lonNm + "\n仓库地址:" + address + "\n电话:" + phone;
	}
	
	
}
