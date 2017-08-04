package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.DealerRefundQueryVO;
import com.zd.csms.marketing.querybean.RefundQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商退费流转单
 * @author licheng
 *
 */
public interface IDealerRefundDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<RefundQueryBean> findList(DealerRefundQueryVO query,IThumbPageTools tools);
	public List<RefundQueryBean> superviseRefundList(DealerRefundQueryVO query,IThumbPageTools tools);
}
