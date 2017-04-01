package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.DistribsetZX;

public interface IDistribsetDAO extends IDAO {
	

	/**
	 * 按条件查询
	 * @param query
	 * @return
	 */
	List<DistribsetZX> searchDistribsetList(DistribsetZX query);

	List<DistribsetZX> findorg(String org);

}
