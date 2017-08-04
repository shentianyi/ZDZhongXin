package com.zd.csms.business.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.business.model.FlowQueryVO;
import com.zd.csms.business.model.FlowVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IFlowDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<FlowVO> searchFlowList(FlowQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<FlowVO> searchFlowListByPage(FlowQueryVO query,IThumbPageTools tools);
}
