package com.zd.csms.supervisory.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.CarManualQueryVO;
import com.zd.csms.supervisory.model.CarManualVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICarManualDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<CarManualVO> searchCarManualList(CarManualQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<CarManualVO> searchCarManualListByPage(CarManualQueryVO query,IThumbPageTools tools);
}
