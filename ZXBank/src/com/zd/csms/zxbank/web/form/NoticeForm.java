package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.Notice;

public class NoticeForm extends ActionForm{

	private static final long serialVersionUID = -809931658866380210L;
	private Notice notice = new Notice();

	public Notice getNotice() {
		return notice;
	}

	public void setNoctice(Notice notice) {
		this.notice = notice;
	}
	
}
