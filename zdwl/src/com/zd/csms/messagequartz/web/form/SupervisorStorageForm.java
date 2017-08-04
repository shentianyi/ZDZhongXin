package com.zd.csms.messagequartz.web.form;

import org.apache.struts.action.ActionForm;
import com.zd.csms.messagequartz.model.SupervisorVO;

public class SupervisorStorageForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8388496110464108477L;
	
	private SupervisorVO supervisorVO = new SupervisorVO();

	/**
	 * @return the supervisorVO
	 */
	public SupervisorVO getSupervisorVO() {
		return supervisorVO;
	}

	/**
	 * @param supervisorVO the supervisorVO to set
	 */
	public void setSupervisorVO(SupervisorVO supervisorVO) {
		this.supervisorVO = supervisorVO;
	}

	
}