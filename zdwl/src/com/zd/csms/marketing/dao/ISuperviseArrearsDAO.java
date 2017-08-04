package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.SuperviseArrearsQueryVO;
import com.zd.csms.marketing.model.SuperviseArrearsVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ISuperviseArrearsDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<SuperviseArrearsVO> searchSuperviseArrearsList(SuperviseArrearsQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<SuperviseArrearsVO> searchSuperviseArrearsListByPage(SuperviseArrearsQueryVO query,IThumbPageTools tools);
}
