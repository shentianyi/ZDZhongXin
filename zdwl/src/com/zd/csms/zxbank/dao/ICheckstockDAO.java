package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Checkstock;
import com.zd.csms.zxbank.bean.CheckstockVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICheckstockDAO extends IDAO {
	/**
	 *  分页查询
	 */
	public List<Checkstock> findAllList(Checkstock query, IThumbPageTools tools);

	/**
	 * 根据融资编号查询
	 */
	public Checkstock getCheckstock(int csid);

	/**
	 * 分页根据ID
	 */
	public List<CheckstockVO> findAllVOList(int id, IThumbPageTools tools);
}
