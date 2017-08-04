package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.SystemManageQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemManageVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ISystemManageDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<SystemManageVO> searchSystemManageList(SystemManageQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<SystemManageVO> searchSystemManageListByPage(SystemManageQueryVO query,IThumbPageTools tools);
}
