package com.zd.csms.zxbank.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.zxbank.bean.DistribsetZX;

public interface IDistribsetDAO extends IDAO {
	
	List<DistribsetZX> findAll();
}
