package com.zd.csms.bank.model;

import com.zd.core.annotation.table;

@table(name="t_bank_children_manager")
public class BankChildrenManagerVO {
	private int id;
	private int parentId;
	private int childrenId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(int childrenId) {
		this.childrenId = childrenId;
	}
	
}
