package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.ReceivingDetail;
import com.zd.csms.zxbank.dao.IReceivingDetailDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ReceivingDetailService extends ServiceSupport{
	private IReceivingDetailDAO nodldao=ZXBankDAOFactory.getReceivingDetailDAO();
	public List<ReceivingDetail> findBusinessList(ReceivingDetail query,IThumbPageTools tools){
		return nodldao.firnAllAgList(query, tools);
	}
	
}
