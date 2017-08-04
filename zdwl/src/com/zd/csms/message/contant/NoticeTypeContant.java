package com.zd.csms.message.contant;

public enum NoticeTypeContant {
	
	/**
	 * 手写公告  可以指定部门 或者全部的人都可以看到，后来的人看不到
	 */
	CONTENT(1,"手写公告"),
	
	/**
	 * 系统的一些操作产生的公告 
	 */
	URL(2,"系统公告"),
	
	/**
	 * 制度文件，一旦生成永不消失 
	 */
	SYSTEM(3,"制度公告");
	
	private int code;
	private String name;
	private NoticeTypeContant(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
