package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.RemovePledgeDetail;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知详情DAO层
 * @author duyong
 *
 */
public interface IRemovePledgeDetailDAO extends IDAO {

	/**
	 * 根据条件查询解除质押通知
	 */
	public List<RemovePledgeDetail> findByQuery(RemovePledgeDetail query, IThumbPageTools tools);

	/**
	 * 根据NO查询
	 */
	public List<RemovePledgeDetail> findAll(String no);

	boolean add(RemovePledgeDetail removePledgeDetail);

}
