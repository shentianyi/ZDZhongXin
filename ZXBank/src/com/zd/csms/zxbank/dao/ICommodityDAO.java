package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Commodity;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICommodityDAO extends IDAO {
	public List<Commodity> findAllList(Commodity query,IThumbPageTools tools);
	
	public boolean add(Commodity com);
}
