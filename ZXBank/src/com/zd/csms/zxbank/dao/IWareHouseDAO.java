package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Warehouse;

public interface IWareHouseDAO extends IDAO {
	public List<Warehouse> findAllList();
}
