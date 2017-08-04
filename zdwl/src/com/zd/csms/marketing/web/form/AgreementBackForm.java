package com.zd.csms.marketing.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.model.AgreementBackVO;

public class AgreementBackForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4664341006317755129L;

	private AgreementBackVO agreementBack = new AgreementBackVO();
	private AgreementBackQueryVO agreementBackquery = new AgreementBackQueryVO();
	public AgreementBackVO getAgreementBack() {
		return agreementBack;
	}
	public void setAgreementBack(AgreementBackVO agreementBack) {
		this.agreementBack = agreementBack;
	}
	public AgreementBackQueryVO getAgreementBackquery() {
		return agreementBackquery;
	}
	public void setAgreementBackquery(AgreementBackQueryVO agreementBackquery) {
		this.agreementBackquery = agreementBackquery;
	}
	
	
}
