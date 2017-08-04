package com.zd.csms.region.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="t_region")
public class RegionVO implements Serializable {

	private static final long serialVersionUID = 6348857295726046280L;
	
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父级ID
	 */
	private int parentId;
	/**
	 * 创建人
	 */
	private int createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改人
	 */
	private int lastModifiedUser;
	/**
	 * 最后修改时间
	 */
	private Date lastModifiedTime;
	/**
	 *状态
	 */
	private int status;
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(int lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRegionLevel() {
		return regionLevel;
	}
	public void setRegionLevel(int regionLevel) {
		this.regionLevel = regionLevel;
	}
	
}
