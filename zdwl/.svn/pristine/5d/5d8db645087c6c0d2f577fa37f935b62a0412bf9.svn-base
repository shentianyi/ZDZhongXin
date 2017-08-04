package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.UsernameManageQueryVO;
import com.zd.csms.supervisorymanagement.model.UsernameManageVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IUsernameManageDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<UsernameManageVO> searchUsernameManageList(UsernameManageQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<UsernameManageVO> searchUsernameManageListByPage(UsernameManageQueryVO query,IThumbPageTools tools);
}
