package com.zd.csms.business.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.business.model.ComplaintInfoQueryVO;
import com.zd.csms.business.model.ComplaintInfoVO;

public class ComplaintForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private ComplaintInfoVO complaint = new ComplaintInfoVO();
	
	private ComplaintInfoQueryVO query = new ComplaintInfoQueryVO();
	
	private Integer[] phoneTypes;

	public Integer[] getPhoneTypes() {
		return phoneTypes;
	}

	public void setPhoneTypes(Integer[] phoneTypes) {
		this.phoneTypes = phoneTypes;
	}

	public ComplaintInfoVO getComplaint() {
		return complaint;
	}

	public void setComplaint(ComplaintInfoVO complaint) {
		this.complaint = complaint;
	}

	public ComplaintInfoQueryVO getQuery() {
		return query;
	}

	public void setQuery(ComplaintInfoQueryVO query) {
		this.query = query;
	}

}
