package com.zd.csms.supervisorymanagement.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisorymanagement.model.SystemSuperviseQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemSuperviseVO;

public class SystemSuperviseForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5730102815865539872L;

	private SystemSuperviseVO systemSupervise = new SystemSuperviseVO();
	private SystemSuperviseQueryVO systemSupervisequery = new SystemSuperviseQueryVO();
	public SystemSuperviseVO getSystemSupervise() {
		return systemSupervise;
	}
	public void setSystemSupervise(SystemSuperviseVO systemSupervise) {
		this.systemSupervise = systemSupervise;
	}
	public SystemSuperviseQueryVO getSystemSupervisequery() {
		return systemSupervisequery;
	}
	public void setSystemSupervisequery(SystemSuperviseQueryVO systemSupervisequery) {
		this.systemSupervisequery = systemSupervisequery;
	}
	
	
}
