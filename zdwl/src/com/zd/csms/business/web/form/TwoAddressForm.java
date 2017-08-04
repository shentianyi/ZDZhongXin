package com.zd.csms.business.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.business.model.TwoAddressQueryVO;
import com.zd.csms.business.model.TwoAddressVO;

public class TwoAddressForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1820363186496952026L;
	private TwoAddressVO twoAddress = new TwoAddressVO();
	private TwoAddressQueryVO twoAddressquery = new TwoAddressQueryVO();
	public TwoAddressVO getTwoAddress() {
		return twoAddress;
	}
	public void setTwoAddress(TwoAddressVO twoAddress) {
		this.twoAddress = twoAddress;
	}
	public TwoAddressQueryVO getTwoAddressquery() {
		return twoAddressquery;
	}
	public void setTwoAddressquery(TwoAddressQueryVO twoAddressquery) {
		this.twoAddressquery = twoAddressquery;
	}
	
	

}
