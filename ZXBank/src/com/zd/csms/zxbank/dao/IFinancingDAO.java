package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IFinancingDAO extends IDAO {

	/**
	 * 根据条件查询融资信息
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools);

	public int getCountByOther(String code);
	
	public boolean update(Financing financing);
	
	public boolean add(Financing financing);
	
	public Financing getFinancing(String code);
}
