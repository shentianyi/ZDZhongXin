package com.zd.csms.zxbank.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.csms.zxbank.dao.IReceivingNoticeDAO;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 收货通知Service
 */
public class ReceivingNoticeService extends ServiceSupport {
	private IReceivingNoticeDAO dao = ZXBankDAOFactory.getReceivingNoticeDAO();

	public List<ReceivingNotice> findBusinessList(ReceivingNotice query, IThumbPageTools tools) {
		return dao.firnAllAgList(query, tools);
	}

	public ReceivingNotice getNotify(String no) {
		return dao.getNotify(no);
	}
}
