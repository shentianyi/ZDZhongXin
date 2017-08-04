package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.Agreement;

public class AgreementForm extends ActionForm {
	private static final long serialVersionUID = -4465868497614904389L;

	private Agreement agreement = new Agreement();

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

}
