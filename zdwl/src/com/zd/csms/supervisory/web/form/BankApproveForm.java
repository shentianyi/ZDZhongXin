package com.zd.csms.supervisory.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisory.model.BankApproveQueryVO;
import com.zd.csms.supervisory.model.BankApproveVO;

public class BankApproveForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032766705231450017L;
	private BankApproveVO bankApprove = new BankApproveVO();
	private BankApproveQueryVO bankApprovequery = new BankApproveQueryVO();
	public BankApproveVO getBankApprove() {
		return bankApprove;
	}
	public void setBankApprove(BankApproveVO bankApprove) {
		this.bankApprove = bankApprove;
	}
	public BankApproveQueryVO getBankApprovequery() {
		return bankApprovequery;
	}
	public void setBankApprovequery(BankApproveQueryVO bankApprovequery) {
		this.bankApprovequery = bankApprovequery;
	}
	
	
}
