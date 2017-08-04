package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.ReceivingDetail;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IReceivingDetailDAO extends IDAO {
	/**
	 * 分页查询
	 */
	public List<ReceivingDetail> firnAllAgList(ReceivingDetail query,IThumbPageTools tools);
	/**
	 * 查询所有
	 */
	public List<ReceivingDetail> findAll(String no);
	boolean add(ReceivingDetail receivingDetail);
//	boolean update(ReceivingDetail receivingDetail);
}
