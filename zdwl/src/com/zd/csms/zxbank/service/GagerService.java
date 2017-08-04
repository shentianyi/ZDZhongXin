package com.zd.csms.zxbank.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.csms.zxbank.dao.IGagerDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 质物入库Service
 */
public class GagerService extends ServiceSupport {
	private IGagerDAO dao = ZXBankDAOFactory.getGagerDAO();
	private static Log log = LogFactory.getLog(GagerService.class);
	public List<Gager> findBusinessList(Gager query, IThumbPageTools tools) {
		return dao.findAllList(query, tools);
	}

	public Gager getGager(String id) {
		return dao.getGager(Integer.parseInt(id));
	}

	public boolean addGager(Gager gager) {
		log.info("入库信息保存");
		gager.setGaId(SqlUtil.getID(Gager.class));
		gager.setGaCreatedate(new Date());
		return dao.add(gager);
	}
}
