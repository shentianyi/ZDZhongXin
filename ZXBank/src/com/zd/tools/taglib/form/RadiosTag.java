package com.zd.tools.taglib.form;

import javax.servlet.jsp.JspException;

public class RadiosTag extends CheckboxsTag {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8004534621284102924L;

	public void init() throws JspException {
		super.init();
		type = "radio";
	}

}
