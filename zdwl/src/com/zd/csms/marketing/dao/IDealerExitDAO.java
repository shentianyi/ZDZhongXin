package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.DealerExitQueryVO;
import com.zd.csms.marketing.querybean.DealerExitQueryBean;
import com.zd.csms.marketing.querybean.SupervisorQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商撤店信息流转
 * @author licheng
 *
 */
public interface IDealerExitDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<DealerExitQueryBean> findList(DealerExitQueryVO query,IThumbPageTools tools);
	
	/**
	 * 根据储备库ID获取监管员信息
	 * @param id
	 * @return
	 */
	public SupervisorQueryBean getSupervisor(int id) throws Exception;

	public List<DealerExitQueryBean> DealerWithdrawalLedger(
			DealerExitQueryVO query, IThumbPageTools tools);
}
