package com.zd.csms.supervisory.web.form.outstock;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisory.model.outstock.SupervisorOutStockMessageQueryVO;
import com.zd.csms.supervisory.model.outstock.SupervisorOutStockMessageVO;


public class SupervisorOutStockMessageFrom extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private SupervisorOutStockMessageVO sOutStock = new SupervisorOutStockMessageVO();
	private SupervisorOutStockMessageQueryVO query = new SupervisorOutStockMessageQueryVO();
	/**
	 * @return the sOutStock
	 */
	public SupervisorOutStockMessageVO getSOutStock() {
		return sOutStock;
	}
	/**
	 * @param sOutStock the sOutStock to set
	 */
	public void setSOutStock(SupervisorOutStockMessageVO sOutStock) {
		this.sOutStock = sOutStock;
	}
	/**
	 * @return the query
	 */
	public SupervisorOutStockMessageQueryVO getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(SupervisorOutStockMessageQueryVO query) {
		this.query = query;
	}


}
