package com.zd.csms.supervisory.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.model.ActualAddressQueryVO;
import com.zd.csms.supervisory.model.ActualAddressVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IActualAddressDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<ActualAddressVO> searchActualAddressList(ActualAddressQueryVO query);
	
	public List<String> findAllIds(UserVO user,int type);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<ActualAddressVO> searchActualAddressListByPage(ActualAddressQueryVO query,IThumbPageTools tools);
}
