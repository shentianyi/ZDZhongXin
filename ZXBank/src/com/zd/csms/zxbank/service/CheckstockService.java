package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.csms.zxbank.bean.Checkstock;
import com.zd.csms.zxbank.bean.CheckstockVO;
import com.zd.csms.zxbank.dao.ICheckstockDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 质物入库Service
 */
public class CheckstockService {
	private ICheckstockDAO dao = ZXBankDAOFactory.getCheckstockDAO();

	public List<Checkstock> findBusinessList(Checkstock query, IThumbPageTools tools) {
		return dao.findAllList(query, tools);
	}

	public Checkstock getCheckstock(int loncpid) {
		return dao.getCheckstock(loncpid);
	}

	public List<CheckstockVO> findAllVOList(int id, IThumbPageTools tools) {
		return dao.findAllVOList(id, tools);
	}
}
