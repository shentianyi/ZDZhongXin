package com.zd.csms.rbac.model;

public class LoginUserVO {

	private String yiji;//一级
	private String erji;//二级
	private String sanji;//三级
	private String name;//姓名
	public String getYiji() {
		return yiji;
	}
	public void setYiji(String yiji) {
		this.yiji = yiji;
	}
	public String getErji() {
		return erji;
	}
	public void setErji(String erji) {
		this.erji = erji;
	}
	public String getSanji() {
		return sanji;
	}
	public void setSanji(String sanji) {
		this.sanji = sanji;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
