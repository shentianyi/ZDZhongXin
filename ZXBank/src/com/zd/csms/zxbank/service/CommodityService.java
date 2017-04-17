package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Commodity;
import com.zd.csms.zxbank.dao.ICommodityDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 盘库详情Service
 */
public class CommodityService extends ServiceSupport {
	private ICommodityDAO dao = ZXBankDAOFactory.getCommodityDAO();

	public List<Commodity> findBusinessList(Commodity query, IThumbPageTools tools) {
		return dao.findAllList(query, tools);
	}

	public boolean addList(List<Commodity> lists, int gagerId) {
		for (int i = 0; i < lists.size(); i++) {
			Commodity com = lists.get(i);
			com.setCmId(SqlUtil.getID(Commodity.class));
			com.setCmGaid(gagerId);
			dao.add(com);
		}
		return false;
	}
}
