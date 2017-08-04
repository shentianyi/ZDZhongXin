package com.zd.csms.zxbank.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.zxbank.bean.FinancingQueryVO;

public class FinancingForm extends ActionForm {
	private static final long serialVersionUID = 3669948099916919341L;
	private FinancingQueryVO financingVO = new FinancingQueryVO();

	public FinancingQueryVO getFinancingVO() {
		return financingVO;
	}

	public void setFinancingVO(FinancingQueryVO financingVO) {
		this.financingVO = financingVO;
	}

}
