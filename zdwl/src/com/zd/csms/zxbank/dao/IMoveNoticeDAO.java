package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.*;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 移库通知DAO层接口
 * @author duyong
 */
public interface IMoveNoticeDAO extends IDAO {

	/**
	 * 根据条件查询移库通知
	 */
	public List<MoveNotice> findByQuery(MoveNotice query, IThumbPageTools tools);

	/**
	 * 根据通知编号查询
	 */
	public MoveNotice fingByNo(String no);

	public List<String> getListNotice();

	boolean add(MoveNotice moveNotice);

	boolean update(MoveNotice moveNotice);
	
}
