package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.ReceivingNotice;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IReceivingNoticeDAO extends IDAO {
	/**
	 * 分页查询
	 */
	public List<ReceivingNotice> firnAllAgList(ReceivingNotice query, IThumbPageTools tools);

	/**
	 * 查询单个根据NO
	 */
	public ReceivingNotice getNotify(String no);
	
	public List<String> getListNotice();

	boolean add(ReceivingNotice receivingNotice);

	boolean update(ReceivingNotice receivingNotice);
	
}
