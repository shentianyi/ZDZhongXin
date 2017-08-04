package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.PaymentQueryVO;
import com.zd.csms.marketing.model.PaymentVO;
import com.zd.csms.marketing.querybean.PaymentQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IPaymentDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<PaymentQueryBean> findList(PaymentQueryVO query,IThumbPageTools tools);
	
	/**
	 * 缴费详情
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<PaymentVO> findListByDealerId(PaymentQueryVO query,IThumbPageTools tools);

}
