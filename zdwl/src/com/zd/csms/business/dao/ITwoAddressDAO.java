package com.zd.csms.business.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.business.model.ExtTwoAddressVO;
import com.zd.csms.business.model.TwoAddressEVO;
import com.zd.csms.business.model.TwoAddressQueryVO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ITwoAddressDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<TwoAddressVO> searchTwoAddressList(TwoAddressQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<TwoAddressVO> searchTwoAddressListByPage(TwoAddressQueryVO query,IThumbPageTools tools);
	/*
	 * 需求90 -- 导出监管场地
	 * @time 20170519
	*/
	public List<ExtTwoAddressVO> ExtSupervisionSite(TwoAddressQueryVO taQuery);
	
	public TwoAddressVO searchByCode(String code);
	
	//查找其他信息
	public TwoAddressEVO searchBydid(int did);
}
