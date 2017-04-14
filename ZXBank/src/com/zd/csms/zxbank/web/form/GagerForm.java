package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.Gager;

public class GagerForm extends ActionForm {
	private Gager gager=new Gager();

	public Gager getGager() {
		return gager;
	}

	public void setGager(Gager gager) {
		this.gager = gager;
	}
	
}
