package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.zxbank.bean.Gager;

public class BankInterfaceForm extends ActionForm {
	private static final long serialVersionUID = -8302069280424430582L;
	
	private FormFile importFile;
	private Gager gager = new Gager();
	
	public FormFile getImportFile() {
		return importFile;
	}

	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}

	public Gager getGager() {
		return gager;
	}

	public void setGager(Gager gager) {
		this.gager = gager;
	}

	
}
