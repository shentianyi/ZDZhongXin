package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.csms.zxbank.dao.IAgreementDao;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

public class AgreementService extends ServiceSupport {
	private IAgreementDao dao=ZXBankDAOFactory.getAgreementDao();
	public List<Agreement> findBusinessList(Agreement query,IThumbPageTools tools){
		return dao.firnAllAgList(query, tools);
	}
}
