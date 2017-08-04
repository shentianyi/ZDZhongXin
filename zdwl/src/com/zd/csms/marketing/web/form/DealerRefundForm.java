package com.zd.csms.marketing.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.marketing.model.DealerRefundQueryVO;
import com.zd.csms.marketing.model.DealerRefundVO;

/**
 * 经销商退费流转单
 * @author licheng
 *
 */
public class DealerRefundForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DealerRefundVO refund = new DealerRefundVO();
	private DealerRefundQueryVO query = new DealerRefundQueryVO();

	public DealerRefundQueryVO getQuery() {
		return query;
	}
	public void setQuery(DealerRefundQueryVO query) {
		this.query = query;
	}
	public DealerRefundVO getRefund() {
		return refund;
	}
	public void setRefund(DealerRefundVO refund) {
		this.refund = refund;
	}
	
	
}
