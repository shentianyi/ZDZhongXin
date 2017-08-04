package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Commodity;
import com.zd.csms.zxbank.dao.ICommodityDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 质物入库商品详情Service
 */
public class CommodityService extends ServiceSupport {
	private ICommodityDAO dao = ZXBankDAOFactory.getCommodityDAO();

	public List<Commodity> findBusinessList(Commodity query, IThumbPageTools tools) {
		return dao.findAllList(query, tools);
	}

	public boolean addList(List<Commodity> lists, int gagerId) {
		boolean flg=true;
		for (int i = 0; i < lists.size(); i++) {
			Commodity com = lists.get(i);
			com.setCmId(SqlUtil.getID(Commodity.class));
			com.setCmGaid(gagerId);
			if(!dao.add(com)){
				flg=false;
			}
		}
		return flg;
	}
}
