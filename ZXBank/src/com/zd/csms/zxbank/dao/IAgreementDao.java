package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IAgreementDao extends IDAO {
	public List<Agreement> firnAllAgList(Agreement query,IThumbPageTools tools);
}
