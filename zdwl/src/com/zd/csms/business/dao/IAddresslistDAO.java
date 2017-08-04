package com.zd.csms.business.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.business.model.AddresslistQueryVO;
import com.zd.csms.business.model.AddresslistVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IAddresslistDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<AddresslistVO> searchAddressList(AddresslistQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<AddresslistVO> searchAddressListByPage(AddresslistQueryVO query,IThumbPageTools tools);
}
