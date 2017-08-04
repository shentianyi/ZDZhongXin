package com.zd.csms.supervisory.web.form.repairecostms;

import org.apache.struts.action.ActionForm;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageQueryVO;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageVO;

public class SupervisorRepairCostMessageFrom extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7453481289449472392L;
	
	private SupervisorRepairCostMessageVO sRepaircost = new SupervisorRepairCostMessageVO();
	private SupervisorRepairCostMessageQueryVO query = new SupervisorRepairCostMessageQueryVO();
	/**
	 * @return the sRepaircost
	 */
	public SupervisorRepairCostMessageVO getsRepaircost() {
		return sRepaircost;
	}
	/**
	 * @param sRepaircost the sRepaircost to set
	 */
	public void setsRepaircost(SupervisorRepairCostMessageVO sRepaircost) {
		this.sRepaircost = sRepaircost;
	}
	/**
	 * @return the query
	 */
	public SupervisorRepairCostMessageQueryVO getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(SupervisorRepairCostMessageQueryVO query) {
		this.query = query;
	}

}
