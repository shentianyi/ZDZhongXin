package com.zd.csms.finance.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.finance.model.SupervisionfeeQueryVO;
import com.zd.csms.finance.model.SupervisionfeeVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ISupervisionfeeDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<SupervisionfeeVO> searchSupervisionfeeList(SupervisionfeeQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<SupervisionfeeVO> searchSupervisionfeeListByPage(SupervisionfeeQueryVO query,IThumbPageTools tools);
}
