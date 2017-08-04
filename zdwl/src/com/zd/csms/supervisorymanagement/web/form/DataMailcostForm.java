package com.zd.csms.supervisorymanagement.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisorymanagement.model.DataMailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostVO;

public class DataMailcostForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4575946697795405253L;

	private DataMailcostVO dataMailcost = new DataMailcostVO();
	private DataMailcostQueryVO dataMailcostquery = new DataMailcostQueryVO();
	private Integer[] mailItems;
	
	public DataMailcostVO getDataMailcost() {
		return dataMailcost;
	}
	public void setDataMailcost(DataMailcostVO dataMailcost) {
		this.dataMailcost = dataMailcost;
	}
	public DataMailcostQueryVO getDataMailcostquery() {
		return dataMailcostquery;
	}
	public void setDataMailcostquery(DataMailcostQueryVO dataMailcostquery) {
		this.dataMailcostquery = dataMailcostquery;
	}
	public Integer[] getMailItems() {
		return mailItems;
	}
	public void setMailItems(Integer[] mailItems) {
		this.mailItems = mailItems;
	}
	
	
}
