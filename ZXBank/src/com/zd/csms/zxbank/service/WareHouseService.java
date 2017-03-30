package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.IWareHouseDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.model.WarHouseQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class WareHouseService extends ServiceSupport {
	private IWareHouseDAO wdao=ZXBankDAOFactory.getWareHouseDAO();
	public List<Warehouse> findBusinessList(WarHouseQueryVO query,
			IThumbPageTools tools){
		return wdao.findBusinessList(query, tools);
	}
}
