package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Gager;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IGagerDAO extends IDAO {
	public List<Gager> findAllList(Gager query,IThumbPageTools tools);
	public Gager getGager(int gaid,IThumbPageTools tools);
}
