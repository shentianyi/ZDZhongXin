package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.RemovePledge;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知DAO层
 * @author duyong
 */
public interface IRemovePledgeDAO extends IDAO {

	/**
	 * 根据条件查询解除质押通知
	 */
	public List<RemovePledge> findByQuery(RemovePledge query, IThumbPageTools tools);

	/**
	 * 根据通知编号查询
	 */
	public RemovePledge fingByNo(String no);
	
	public List<String> getListNotice();

	boolean add(RemovePledge removePledge);

	boolean update(RemovePledge removePledge);
}
