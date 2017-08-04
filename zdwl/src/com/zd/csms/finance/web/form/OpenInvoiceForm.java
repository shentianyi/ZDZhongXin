package com.zd.csms.finance.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.finance.model.OpenInvoiceQueryVO;
import com.zd.csms.finance.model.OpenInvoiceVO;

public class OpenInvoiceForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4290057369223880009L;
	
	private OpenInvoiceVO openInvoice = new OpenInvoiceVO();
	private OpenInvoiceQueryVO openInvoicequery = new OpenInvoiceQueryVO();
	public OpenInvoiceVO getOpenInvoice() {
		return openInvoice;
	}
	public void setOpenInvoice(OpenInvoiceVO openInvoice) {
		this.openInvoice = openInvoice;
	}
	public OpenInvoiceQueryVO getOpenInvoicequery() {
		return openInvoicequery;
	}
	public void setOpenInvoicequery(OpenInvoiceQueryVO openInvoicequery) {
		this.openInvoicequery = openInvoicequery;
	}
	
	

}
