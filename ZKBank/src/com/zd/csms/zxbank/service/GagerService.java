package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.csms.zxbank.dao.IGagerDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

public class GagerService extends ServiceSupport {
	private IGagerDAO dao=ZXBankDAOFactory.getGagerDAO();
	public List<Gager> findBusinessList(Gager query,IThumbPageTools tools){
		return dao.findAllList(query, tools);
	}
	public Gager getGager(int gaid,IThumbPageTools tools){
		return dao.getGager(gaid, tools);
	}
}
