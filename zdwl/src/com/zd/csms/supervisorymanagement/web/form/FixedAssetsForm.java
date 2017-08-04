package com.zd.csms.supervisorymanagement.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;

public class FixedAssetsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6242291237705593366L;
	
	private FixedAssetsVO fixedAssets = new FixedAssetsVO();
	private FixedAssetsQueryVO fixedAssetsquery = new FixedAssetsQueryVO();
	private FormFile excelfile;
	public FixedAssetsVO getFixedAssets() {
		return fixedAssets;
	}
	public void setFixedAssets(FixedAssetsVO fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
	public FixedAssetsQueryVO getFixedAssetsquery() {
		return fixedAssetsquery;
	}
	public void setFixedAssetsquery(FixedAssetsQueryVO fixedAssetsquery) {
		this.fixedAssetsquery = fixedAssetsquery;
	}
	public FormFile getExcelfile() {
		return excelfile;
	}
	public void setExcelfile(FormFile excelfile) {
		this.excelfile = excelfile;
	}

	
}
