package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BankInterfaceForm extends ActionForm {
	private FormFile importFile;

	public FormFile getImportFile() {
		return importFile;
	}

	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}

}
