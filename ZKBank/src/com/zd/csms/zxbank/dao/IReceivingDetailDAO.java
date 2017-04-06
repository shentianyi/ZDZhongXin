package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.ReceivingDetail;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IReceivingDetailDAO extends IDAO {
	public List<ReceivingDetail> firnAllAgList(ReceivingDetail query,IThumbPageTools tools);
	
	public List<ReceivingDetail> firnAll(ReceivingDetail query);
}
