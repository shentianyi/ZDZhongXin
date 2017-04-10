package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;
import com.zd.csms.zxbank.bean.DistribsetZX;

public class DistribsetForm extends ActionForm {
	private static final long serialVersionUID = 6183603022278423099L;

	private DistribsetZX distribset = new DistribsetZX();

	public DistribsetZX getDistribset() {
		return distribset;
	}

	public void setDistribset(DistribsetZX distribset) {
		this.distribset = distribset;
	}
}
