package com.zd.csms.bank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.bank.model.BankManagerQueryVO;
import com.zd.csms.bank.model.BankManagerVO;

public interface IBankManagerDao extends IDAO {
	/**
	 * 列表查询
	 * @param query
	 * @return
	 */
	public List<BankManagerVO> findList(BankManagerQueryVO query);
}
