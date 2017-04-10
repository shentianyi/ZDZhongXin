package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.ReceivingNotice;

public class ReceivingNoticeForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4145786219711276827L;
	private ReceivingNotice receivingnotice=new ReceivingNotice();
	public ReceivingNotice getReceivingnotice() {
		return receivingnotice;
	}
	public void setReceivingnotice(ReceivingNotice receivingnotice) {
		this.receivingnotice = receivingnotice;
	}
	
	
	
}
