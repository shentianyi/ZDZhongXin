package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Checkstock;
import com.zd.csms.zxbank.bean.CheckstockVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICheckstockDAO extends IDAO {
	public List<Checkstock> findAllList(Checkstock query,IThumbPageTools tools);
	public Checkstock getCheckstock(int loncpid);
	public List<CheckstockVO> findAllVOList(int id,IThumbPageTools tools);
}
