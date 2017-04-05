package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.dao.IFinancingDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.model.FinancingQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class FinancingService extends ServiceSupport {

	private IFinancingDAO dao = ZXBankDAOFactory.getFinancingDAO();

	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools) {
		return dao.findByQuery(query, tools);
	}
}
