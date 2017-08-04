package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.ApprovalQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IMarketApprovalDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<MarketApprovalVO> findList(MarketApprovalQueryVO query,IThumbPageTools tools);
	
	/**
	 * 根据表单类型和目标ID查询审批记录
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalQueryBean> findListByApprovalType(MarketApprovalQueryVO query) throws Exception;
	
	/**
	 * 根据目标对象Id和类型删除所有审批纪律
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean deleteApprovalByDealerId(int id,int type) throws Exception;
}
