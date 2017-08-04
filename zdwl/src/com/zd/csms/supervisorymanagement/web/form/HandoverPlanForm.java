package com.zd.csms.supervisorymanagement.web.form;

import org.apache.struts.action.ActionForm;
import com.zd.csms.supervisorymanagement.model.HandoverPlanQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanVO;

public class HandoverPlanForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6242291237705593366L;
	private HandoverPlanVO handoverPlan=new HandoverPlanVO();
	private HandoverPlanQueryVO query=new HandoverPlanQueryVO();
	public HandoverPlanVO getHandoverPlan() {
		return handoverPlan;
	}
	public void setHandoverPlan(HandoverPlanVO handoverPlan) {
		this.handoverPlan = handoverPlan;
	}
	public HandoverPlanQueryVO getQuery() {
		return query;
	}
	public void setQuery(HandoverPlanQueryVO query) {
		this.query = query;
	}

}
