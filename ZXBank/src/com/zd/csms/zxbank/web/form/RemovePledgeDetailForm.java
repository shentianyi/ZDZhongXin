package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.RemovePledgeDetail;

public class RemovePledgeDetailForm extends ActionForm{

	private static final long serialVersionUID = 6660011977709748001L;
	
	private RemovePledgeDetail removepledgedetail = new RemovePledgeDetail();

	public RemovePledgeDetail getRemovepledgedetail() {
		return removepledgedetail;
	}

	public void setRemovepledgedetail(RemovePledgeDetail removepledgedetail) {
		this.removepledgedetail = removepledgedetail;
	}
	
}
