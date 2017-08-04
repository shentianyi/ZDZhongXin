package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.SupervisePayQueryVO;
import com.zd.csms.marketing.model.SupervisePayVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ISupervisePayDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<SupervisePayVO> searchSupervisePayList(SupervisePayQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<SupervisePayVO> searchSupervisePayListByPage(SupervisePayQueryVO query,IThumbPageTools tools);
}
