package com.zd.csms.marketing.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.marketing.model.ExpenseChangeQueryVO;
import com.zd.csms.marketing.model.ExpenseChangeVO;

public class ExpenseChangeForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ExpenseChangeVO ec = new ExpenseChangeVO();
	
	private ExpenseChangeQueryVO query = new ExpenseChangeQueryVO();

	public ExpenseChangeQueryVO getQuery() {
		return query;
	}

	public void setQuery(ExpenseChangeQueryVO query) {
		this.query = query;
	}

	public ExpenseChangeVO getEc() {
		return ec;
	}

	public void setEc(ExpenseChangeVO ec) {
		this.ec = ec;
	}
	
	
}
