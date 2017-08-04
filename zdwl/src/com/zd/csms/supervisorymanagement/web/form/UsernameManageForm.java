package com.zd.csms.supervisorymanagement.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisorymanagement.model.UsernameManageQueryVO;
import com.zd.csms.supervisorymanagement.model.UsernameManageVO;

public class UsernameManageForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7939329643174648809L;
	
	UsernameManageVO usernameManage = new UsernameManageVO();
	UsernameManageQueryVO usernameManageQuery = new UsernameManageQueryVO();
	
	public UsernameManageVO getUsernameManage() {
		return usernameManage;
	}
	public void setUsernameManage(UsernameManageVO usernameManage) {
		this.usernameManage = usernameManage;
	}
	public UsernameManageQueryVO getUsernameManageQuery() {
		return usernameManageQuery;
	}
	public void setUsernameManageQuery(UsernameManageQueryVO usernameManageQuery) {
		this.usernameManageQuery = usernameManageQuery;
	}

	
}
