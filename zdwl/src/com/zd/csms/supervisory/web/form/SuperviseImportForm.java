package com.zd.csms.supervisory.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;

public class SuperviseImportForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2838292662917699936L;

	private SuperviseImportVO superviseImport = new SuperviseImportVO();
	private SuperviseImportQueryVO superviseImportquery = new SuperviseImportQueryVO();
	private FormFile ben;
	private FormFile excelfile;
	
	public SuperviseImportVO getSuperviseImport() {
		return superviseImport;
	}
	public void setSuperviseImport(SuperviseImportVO superviseImport) {
		this.superviseImport = superviseImport;
	}
	public SuperviseImportQueryVO getSuperviseImportquery() {
		return superviseImportquery;
	}
	public void setSuperviseImportquery(SuperviseImportQueryVO superviseImportquery) {
		this.superviseImportquery = superviseImportquery;
	}
	public FormFile getBen() {
		return ben;
	}
	public void setBen(FormFile ben) {
		this.ben = ben;
	}
	public FormFile getExcelfile() {
		return excelfile;
	}
	public void setExcelfile(FormFile excelfile) {
		this.excelfile = excelfile;
	}
}
