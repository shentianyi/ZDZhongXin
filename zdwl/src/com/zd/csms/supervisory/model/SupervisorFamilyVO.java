package com.zd.csms.supervisory.model;

import java.io.Serializable;

import com.zd.core.annotation.table;
@table(name="T_SUPERVISOR_FAMILY")
public class SupervisorFamilyVO implements Serializable {
	
	private static final long serialVersionUID = 6348857295726046280L;
	/**
	 * 主键ID
	 */
	private int id;             
	/**
	 * 姓名
	 */
	private String name;        
	/**
	 * 职业
	 */
	private String profession;  
	/**
	 * 单位
	 */
	private String organization;
	/**
	 * 联系方式
	 */
	private String telephone;   
	/**
	 * 与该家庭成员关系
	 */
	private String relationship;
	/**
	 * 监管员ＩＤ
	 */
	private int supervisorId;
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
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	
	
}
