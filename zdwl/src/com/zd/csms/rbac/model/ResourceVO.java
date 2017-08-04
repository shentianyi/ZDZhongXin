package com.zd.csms.rbac.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

@table(name="t_rbac_resource")
public class ResourceVO implements Serializable {
	/**
	 * 系统资源表
	 */
	private static final long serialVersionUID = 3384194847857848655L;
	private int id;
	private int state = -1;//启用状态
	private int resource_level = -1;//资源级别
	private int node_type = -1;//节点类型
	private int parent_id = -1;//上级资源
	private String resource_index;//资源顺序
	private String resource_name;//资源名称
	private String resource_shortname;//资源简称
	private String resource_url;//资源路径
	private String des;//资源描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getResource_level() {
		return resource_level;
	}
	public void setResource_level(int resourceLevel) {
		resource_level = resourceLevel;
	}
	public int getNode_type() {
		return node_type;
	}
	public void setNode_type(int nodeType) {
		node_type = nodeType;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parentId) {
		parent_id = parentId;
	}
	public String getResource_index() {
		return resource_index;
	}
	public void setResource_index(String resourceIndex) {
		resource_index = resourceIndex;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resourceName) {
		resource_name = resourceName;
	}
	public String getResource_shortname() {
		return resource_shortname;
	}
	public void setResource_shortname(String resourceShortname) {
		resource_shortname = resourceShortname;
	}
	public String getResource_url() {
		return resource_url;
	}
	public void setResource_url(String resourceUrl) {
		resource_url = resourceUrl;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	

}
