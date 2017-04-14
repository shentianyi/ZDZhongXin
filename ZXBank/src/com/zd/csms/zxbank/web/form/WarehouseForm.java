package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.Warehouse;

public class WarehouseForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3669948099916919341L;
	private Warehouse warehouse=new Warehouse();
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
