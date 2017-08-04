package com.zd.csms.rbac.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.rbac.model.ResourceQueryVO;
import com.zd.csms.rbac.model.ResourceVO;

public class ResourceForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3191495262929491310L;
	private ResourceVO resource = new ResourceVO();					//资源操作对象（用于增删改操作）
	private ResourceQueryVO resourceQuery = new ResourceQueryVO();	//资源查询对象（用于列表查询）
	private int[] resourceIds;										//资源id数组（用于列表操作）
	public ResourceVO getResource() {
		return resource;
	}
	public void setResource(ResourceVO resource) {
		this.resource = resource;
	}
	public ResourceQueryVO getResourceQuery() {
		return resourceQuery;
	}
	public void setResourceQuery(ResourceQueryVO resourceQuery) {
		this.resourceQuery = resourceQuery;
	}
	public int[] getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(int[] resourceIds) {
		this.resourceIds = resourceIds;
	}

	
}
