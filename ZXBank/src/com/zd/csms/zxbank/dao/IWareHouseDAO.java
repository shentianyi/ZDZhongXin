package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.web.bean.WarehouseFar;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IWareHouseDAO extends IDAO {
	public List<Warehouse> findBusinessList(Warehouse query,
			IThumbPageTools tools);
	public boolean add(Warehouse warehouse);
	/**
	 * 更新仓库信息
	 * @param warehouseFar
	 * @param warehouse
	 * @return
	 */
	public boolean upadat(Warehouse warehouse);
	/**
	 * 简单查询所仓库户信息
	 * @return
	 */
	public List<Warehouse> query(String custno);
	
	/**
	 * 查出客户下仓库信息
	 * @param custNo 客户号
	 * @param whCode 仓库代码
	 * @return
	 */
	public Warehouse getWarehouse(String custNo,String whCode);
	
}
