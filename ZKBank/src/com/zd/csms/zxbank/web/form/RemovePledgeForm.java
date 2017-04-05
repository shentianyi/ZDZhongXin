package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.RemovePledge;

public class RemovePledgeForm extends ActionForm{

	private static final long serialVersionUID = 6660011977709748001L;
	
	private RemovePledge removepledge = new RemovePledge();

	public RemovePledge getRemovepledge() {
		return removepledge;
	}

	public void setRemovepledge(RemovePledge removepledge) {
		this.removepledge = removepledge;
	}
	
	
}
