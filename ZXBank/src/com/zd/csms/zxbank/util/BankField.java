package com.zd.csms.zxbank.util;

public class BankField {
	private String name;
	private String value;
	private int type;//字段类型
	private int length;//需求长度
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public BankField(String name, String value, int type, int length) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
		this.length = length;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
}
