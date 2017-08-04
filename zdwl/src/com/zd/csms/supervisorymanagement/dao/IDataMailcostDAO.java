package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.DataMailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostToExtVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IDataMailcostDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<DataMailcostVO> searchDataMailcostList(DataMailcostQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<DataMailcostVO> searchDataMailcostListByPage(DataMailcostQueryVO query,IThumbPageTools tools);
	/**
	 * 导出
	 * @time 20170514
	 */
	public List<DataMailcostToExtVO> searchDataMailcostListToExt(DataMailcostQueryVO query);
}
