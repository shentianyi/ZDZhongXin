package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.model.WarHouseQueryVO;

public class WarehouseForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3669948099916919341L;
	private WarHouseQueryVO quer=new WarHouseQueryVO();

	public WarHouseQueryVO getQuer() {
		return quer;
	}

	public void setQuer(WarHouseQueryVO quer) {
		this.quer = quer;
	}
}
