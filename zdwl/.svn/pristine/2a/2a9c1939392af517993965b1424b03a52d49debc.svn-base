package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.InvoiceQueryVO;
import com.zd.csms.marketing.querybean.InvoiceQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IInvoiceDAO extends IDAO{
	/**
	 * 开票申请
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InvoiceQueryBean> findList(InvoiceQueryVO query,IThumbPageTools tools);
	public List<InvoiceQueryBean> draftVoteList(InvoiceQueryVO query,IThumbPageTools tools);
}
