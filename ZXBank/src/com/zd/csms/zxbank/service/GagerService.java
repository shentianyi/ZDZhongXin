package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.csms.zxbank.dao.IGagerDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class GagerService extends ServiceSupport {
	private IGagerDAO dao=ZXBankDAOFactory.getGagerDAO();
	
	public List<Gager> findBusinessList(Gager query,IThumbPageTools tools){
		return dao.findAllList(query, tools);
	}
	
	public Gager getGager(String id){
		return dao.getGager(Integer.parseInt(id));
	}
	
	public boolean addGager(Gager gager){
		gager.setGaState(1);
		gager.setGaCreatedate(DateUtil.getStringFormatByDate(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		return dao.add(gager);
	}
}
