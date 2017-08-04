package com.zd.csms.supervisory.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.CheckStockQueryVO;
import com.zd.csms.supervisory.querybean.CheckStockDealerQueryBean;
import com.zd.csms.supervisory.querybean.CheckStockQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICheckStockDAO extends IDAO{
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockQueryBean> findList(CheckStockQueryVO query, IThumbPageTools tools);
	
	/**
	 * 查询查库频次申请列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockDealerQueryBean> findDealerList(CheckStockQueryVO query, IThumbPageTools tools);
	
	/**
	 * 查询查库频次申请列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockDealerQueryBean> findDealerList(CheckStockQueryVO query);
}
