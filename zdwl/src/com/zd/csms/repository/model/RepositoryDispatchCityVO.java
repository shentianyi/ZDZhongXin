package com.zd.csms.repository.model;

import java.io.Serializable;

import com.zd.core.annotation.table;
@table(name="T_REPOSITORY_DISPATCHCITY")
public class RepositoryDispatchCityVO implements Serializable {
	
	private static final long serialVersionUID = 6348857295726046280L;
	/**
	 * 主键ID
	 */
	private int id;     
	/**
	 * 可派驻城市（省）
	 */
	private String province; 
	/**
	 * 可派驻城市（市）
	 */
	private String  city;     
	/**
	 * 可派驻城市（区/县）
	 */
	private String county;    
	/**
	 * 储备库ID
	 */
	private int repositoryId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public int getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(int repositoryid) {
		this.repositoryId = repositoryid;
	} 
	
	
}	
