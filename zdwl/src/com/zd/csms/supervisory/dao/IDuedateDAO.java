package com.zd.csms.supervisory.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.DuedateQueryVO;
import com.zd.csms.supervisory.model.DuedateVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IDuedateDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<DuedateVO> searchDuedateList(DuedateQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<DuedateVO> searchDuedateListByPage(DuedateQueryVO query,IThumbPageTools tools);
}
