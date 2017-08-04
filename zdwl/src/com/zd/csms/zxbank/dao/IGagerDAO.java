package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IGagerDAO extends IDAO {
	/**
	 * 分页查询
	 */
	public List<Gager> findAllList(Gager query,IThumbPageTools tools);
	/**
	 * 根据ID查询
	 */
	public Gager getGager(int id);
}
