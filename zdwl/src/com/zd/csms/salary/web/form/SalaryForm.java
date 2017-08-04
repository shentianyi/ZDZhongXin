package com.zd.csms.salary.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.salary.model.BasePay;

public class SalaryForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BasePay basepay = new BasePay();
	public BasePay getBasepay() {
		return basepay;
	}
	public void setBasepay(BasePay basepay) {
		this.basepay = basepay;
	}
	
	
	
}
