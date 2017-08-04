package com.zd.csms.region.model;


public class RegionQueryVO {

	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父级名称
	 */
	private String parentName;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 父级ID
	 */
	private int parentId;
	/**
	 * 级别
	 */
	private int regionLevel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getRegionLevel() {
		return regionLevel;
	}
	public void setRegionLevel(int regionLevel) {
		this.regionLevel = regionLevel;
	}
	
}
