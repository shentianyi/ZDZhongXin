package com.zd.csms.business.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;

public class DraftForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7972188204567388006L;

	private DraftVO draft = new DraftVO();
	private DraftQueryVO draftquery = new DraftQueryVO();
	private FormFile excelfile;
	
	
	public DraftVO getDraft() {
		return draft;
	}
	public void setDraft(DraftVO draft) {
		this.draft = draft;
	}
	public DraftQueryVO getDraftquery() {
		return draftquery;
	}
	public void setDraftquery(DraftQueryVO draftquery) {
		this.draftquery = draftquery;
	}
	public FormFile getExcelfile() {
		return excelfile;
	}
	public void setExcelfile(FormFile excelfile) {
		this.excelfile = excelfile;
	}
	
	
	
}
