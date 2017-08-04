package com.zd.csms.supervisory.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisory.model.CarOperationQueryVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;

public class CarOperationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 512295823497812846L;

	private CarOperationVO carOperation = new CarOperationVO();
	private CarOperationQueryVO carOperationquery = new CarOperationQueryVO();
	private SuperviseImportVO superviseImport = new SuperviseImportVO();
	private SuperviseImportQueryVO superviseImportquery = new SuperviseImportQueryVO();
	public CarOperationVO getCarOperation() {
		return carOperation;
	}
	public void setCarOperation(CarOperationVO carOperation) {
		this.carOperation = carOperation;
	}
	public CarOperationQueryVO getCarOperationquery() {
		return carOperationquery;
	}
	public void setCarOperationquery(CarOperationQueryVO carOperationquery) {
		this.carOperationquery = carOperationquery;
	}
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
	
	
}
