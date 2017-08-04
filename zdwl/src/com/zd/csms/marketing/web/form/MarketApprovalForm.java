package com.zd.csms.marketing.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.MarketApprovalVO;

public class MarketApprovalForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MarketApprovalVO marketApproval;
	private MarketApprovalQueryVO query;

	public MarketApprovalVO getMarketApproval() {
		return marketApproval;
	}

	public void setMarketApproval(MarketApprovalVO marketApproval) {
		this.marketApproval = marketApproval;
	}

	public MarketApprovalQueryVO getQuery() {
		return query;
	}

	public void setQuery(MarketApprovalQueryVO query) {
		this.query = query;
	}
	
}
