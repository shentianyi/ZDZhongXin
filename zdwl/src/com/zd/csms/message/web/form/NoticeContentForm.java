package com.zd.csms.message.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.message.model.NoticeContentQueryVO;
import com.zd.csms.message.model.NoticeContentVO;

public class NoticeContentForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NoticeContentQueryVO query = new NoticeContentQueryVO();
	private NoticeContentVO noticeContent = new NoticeContentVO();
	private String[] department;

	public NoticeContentQueryVO getQuery() {
		return query;
	}

	public void setQuery(NoticeContentQueryVO query) {
		this.query = query;
	}

	public NoticeContentVO getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(NoticeContentVO noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String[] getDepartment() {
		return department;
	}

	public void setDepartment(String[] department) {
		this.department = department;
	}



}
