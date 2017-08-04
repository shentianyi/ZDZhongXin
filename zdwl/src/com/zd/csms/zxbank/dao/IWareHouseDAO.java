package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IWareHouseDAO extends IDAO {
	public String getDataSourceName();
	/**
	 * 分页查询
	 */
	public List<Warehouse> findBusinessList(Warehouse query, IThumbPageTools tools);

	public boolean add(Warehouse warehouse);

	/**
	 * 更新仓库信息
	 */
	public boolean update(Warehouse warehouse);

	/**
	 * 简单查询所仓库户信息
	 */
	public List<Warehouse> query(String custno);

	/**
	 * 查出客户下仓库信息
	 */
	public Warehouse getWarehouse(String custNo, String whCode);

	/**
	 * 根据仓库代码查询
	 */
	public Warehouse getWarehouse(String whCode);

	/**
	 * 根据客户号查询
	 */
	public List<Warehouse> findByCustNo(String custNo);

}
