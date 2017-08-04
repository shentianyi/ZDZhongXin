package com.zd.csms.bank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.bank.model.BankManagerQueryVO;
import com.zd.csms.bank.model.BankManagerVO;

public class BankManagerForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BankManagerQueryVO query = new BankManagerQueryVO();
	private BankManagerVO bankManager = new BankManagerVO();
	public BankManagerQueryVO getQuery() {
		return query;
	}
	public void setQuery(BankManagerQueryVO query) {
		this.query = query;
	}
	public BankManagerVO getBankManager() {
		return bankManager;
	}
	public void setBankManager(BankManagerVO bankManager) {
		this.bankManager = bankManager;
	}
	
	
}
