package com.zd.csms.set.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.set.model.DistribsetQueryVO;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.model.ExtendDistribsetVO;

public class DistribsetForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6183603022278423099L;

	private DistribsetVO distribset = new DistribsetVO();
	private DistribsetQueryVO distribsetquery = new DistribsetQueryVO();
	private DealerVO dealer = new DealerVO();
	private DealerQueryVO dealerquery = new DealerQueryVO();
	private int move = 20;
	private ExtendDistribsetVO extendDistribset = new ExtendDistribsetVO();//经销商名录表-扩展参数实体
    
	public DistribsetVO getDistribset() {
		return distribset;
	}

	public void setDistribset(DistribsetVO distribset) {
		this.distribset = distribset;
	}

	public DistribsetQueryVO getDistribsetquery() {
		return distribsetquery;
	}

	public void setDistribsetquery(DistribsetQueryVO distribsetquery) {
		this.distribsetquery = distribsetquery;
	}

	public DealerVO getDealer() {
		return dealer;
	}

	public void setDealer(DealerVO dealer) {
		this.dealer = dealer;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public DealerQueryVO getDealerquery() {
		return dealerquery;
	}

	public void setDealerquery(DealerQueryVO dealerquery) {
		this.dealerquery = dealerquery;
	}

	public ExtendDistribsetVO getExtendDistribset() {
		return extendDistribset;
	}

	public void setExtendDistribset(ExtendDistribsetVO extendDistribset) {
		this.extendDistribset = extendDistribset;
	}

	
}
