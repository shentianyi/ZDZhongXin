package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.model.WarHouseQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IWareHouseDAO extends IDAO {
	public List<Warehouse> findBusinessList(WarHouseQueryVO query,
			IThumbPageTools tools);
}
