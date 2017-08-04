package com.zd.csms.supervisory.web.form.overtime;

import org.apache.struts.action.ActionForm;

import com.zd.csms.supervisory.model.overtime.SupervisorOverTimeMessageQueryVO;
import com.zd.csms.supervisory.model.overtime.SupervisorOverTimeMessageVO;

public class SupervisorOverTimeMessageFrom extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7453481289449472392L;
	
	private SupervisorOverTimeMessageVO sOverTime = new SupervisorOverTimeMessageVO();
	private SupervisorOverTimeMessageQueryVO query = new SupervisorOverTimeMessageQueryVO();
	/**
	 * @return the sOverTime
	 */
	public SupervisorOverTimeMessageVO getSOverTime() {
		return sOverTime;
	}
	/**
	 * @param sOverTime the sOverTime to set
	 */
	public void setSOverTime(SupervisorOverTimeMessageVO sOverTime) {
		this.sOverTime = sOverTime;
	}
	/**
	 * @return the query
	 */
	public SupervisorOverTimeMessageQueryVO getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(SupervisorOverTimeMessageQueryVO query) {
		this.query = query;
	}


}
