package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IFinancingDAO extends IDAO {
	
	public String getDataSourceName();
	/**
	 * 根据条件查询融资信息
	 */
	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools);
	/**
	 * 查询总数
	 */
	public int getCountByOther(String code);
	/**
	 * 更新
	 */
	public boolean update(Financing financing);
	/**
	 * 添加
	 */
	public boolean add(Financing financing);
	/**
	 * Codo查询
	 */
	public Financing getFinancing(String code);
	
	public List<Financing> getFinancingList();
	
}
