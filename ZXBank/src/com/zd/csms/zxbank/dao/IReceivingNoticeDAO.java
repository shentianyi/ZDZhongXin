package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IReceivingNoticeDAO extends IDAO  {
	public List<ReceivingNotice> firnAllAgList(ReceivingNotice query,IThumbPageTools tools);
	public ReceivingNotice getNotify(String no);
}
