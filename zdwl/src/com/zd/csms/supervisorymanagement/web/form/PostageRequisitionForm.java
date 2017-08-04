package com.zd.csms.supervisorymanagement.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisorymanagement.model.MailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.MailcostVO;

public class PostageRequisitionForm extends ActionForm {

	private static final long serialVersionUID = 6242291237705593366L;
	private MailcostVO postageRequisition=new MailcostVO();
	private MailcostQueryVO query=new MailcostQueryVO();
	public MailcostVO getPostageRequisition() {
		return postageRequisition;
	}
	public void setPostageRequisition(MailcostVO postageRequisition) {
		this.postageRequisition = postageRequisition;
	}
	public MailcostQueryVO getQuery() {
		return query;
	}
	public void setQuery(MailcostQueryVO query) {
		this.query = query;
	}
	
}
