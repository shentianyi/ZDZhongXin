package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.BindDealerQueryVO;
import com.zd.csms.marketing.model.BindDealerVO;
import com.zd.csms.marketing.querybean.BindDealerQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IBindDealerDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BindDealerQueryBean> findList(BindDealerQueryVO query,IThumbPageTools tools);

	/*
	 * 经销商/金融机构绑定台账
	 * @time 20170621
	*/
	public List<BindDealerQueryBean> dealerFinancialInstitutionBindingLedger(
			BindDealerQueryVO query, IThumbPageTools tools);
}
