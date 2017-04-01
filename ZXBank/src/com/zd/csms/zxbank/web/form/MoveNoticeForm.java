package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.*;

public class MoveNoticeForm extends ActionForm{

	private static final long serialVersionUID = 6660011977709748001L;
	
	private MoveNotice movenotice = new MoveNotice();

	public MoveNotice getMovenotice() {
		return movenotice;
	}

	public void setMovenotice(MoveNotice movenotice) {
		this.movenotice = movenotice;
	}
}
