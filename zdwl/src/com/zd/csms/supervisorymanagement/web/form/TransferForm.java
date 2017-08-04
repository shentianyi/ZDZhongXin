package com.zd.csms.supervisorymanagement.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisorymanagement.model.TransferQueryVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;

public class TransferForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6242291237705593366L;
	private TransferVO transfer=new TransferVO();
	private TransferQueryVO query=new TransferQueryVO();
	public TransferVO getTransfer() {
		return transfer;
	}
	public void setTransfer(TransferVO transfer) {
		this.transfer = transfer;
	}
	public TransferQueryVO getQuery() {
		return query;
	}
	public void setQuery(TransferQueryVO query) {
		this.query = query;
	}

}
