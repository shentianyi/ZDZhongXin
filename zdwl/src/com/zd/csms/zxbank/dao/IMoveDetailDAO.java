package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.*;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 移库通知详情DAO层接口
 * @author duyong
 */
public interface IMoveDetailDAO extends IDAO {

	/**
	 * 根据条件查询移库通知详情
	 */
	public List<MoveDetail> findByQuery(MoveDetail query, IThumbPageTools tools);
	/**
	 * 查询所有
	 */
	public List<MoveDetail> findAll(String no);
	boolean add(MoveDetail moveDetail);
}
