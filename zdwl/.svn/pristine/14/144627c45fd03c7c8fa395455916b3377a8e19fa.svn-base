package com.zd.csms.repository.constants;


public enum RepositoryStatus {
	/**
	 * 已上岗
	 */
	ALREADYPOST(1,"已上岗"),
	/**
	 * 有效
	 */
	VALID(2,"有效"),
	/**
	 * 无效
	 */
	INVALID(3,"无效");
	private RepositoryStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public static  String getNameByCode(int code) {
		for(RepositoryStatus contant:RepositoryStatus.values()){
			if(code==contant.getCode()){
				return contant.getName();
			}
		}
		return null;
	}
}
