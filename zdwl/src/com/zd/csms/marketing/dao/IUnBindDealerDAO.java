package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.UnBindDealerQueryVO;
import com.zd.csms.marketing.model.UnBindDealerVO;
import com.zd.csms.marketing.querybean.UnBindDealerQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IUnBindDealerDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<UnBindDealerQueryBean> findList(UnBindDealerQueryVO query,IThumbPageTools tools);
	
	/**
	 * 根据经销商主键ID和监管员ID删除DealerSupervisor
	 * @param dealerId
	 * @param bankId
	 * @return
	 */
	public boolean deleteDealerBank(int dealerId,int bankId);

	/*
	 * 经销商/金融机构拆绑台账
	 * @time 20170621
	*/
	public List<UnBindDealerQueryBean> dealerFinancialInstitutionLedger(
			UnBindDealerQueryVO query, IThumbPageTools tools);
}
