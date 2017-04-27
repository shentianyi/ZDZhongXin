package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.Agreement;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IAgreementDAO extends IDAO {
	/**
	 *分页数据查询
	 */
	public List<Agreement> firnAllAgList(Agreement query,IThumbPageTools tools);
	/**
	 * 根据客户号查询单个协议信息。
	 */
	public Agreement getAgreement(String LonentNo);
	/**
	 * 简单查询
	 * @return
	 */
	public List<Agreement> query();
	/**
	 * 更新
	 * @return
	 */
	public boolean update(Agreement agreement);
}
