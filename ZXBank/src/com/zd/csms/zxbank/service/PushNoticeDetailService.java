package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.PushNoticeDetail;
import com.zd.csms.zxbank.dao.IPushNoticeDetailDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

public class PushNoticeDetailService extends ServiceSupport{
	IPushNoticeDetailDAO pndd=ZXBankDAOFactory.getPushNoticeDetailDAO();
	
	public List<PushNoticeDetail> findNotice(PushNoticeDetail query,IThumbPageTools tools){
		return pndd.findNotice(query, tools);
	}
	
	
	public boolean add(PushNoticeDetail pnd){
		return pndd.add(pnd);
	}
}
