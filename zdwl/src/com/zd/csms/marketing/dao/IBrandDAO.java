package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.BrandQueryVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IBrandDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<BrandVO> searchBrandList(BrandQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<BrandVO> searchBrandListByPage(BrandQueryVO query,IThumbPageTools tools);
	
	/**
	 * 验证品牌名是否存在，返回true为不存在，返回false为存在
	 * @param name
	 * @return
	 */
	public boolean validateBrandName(String name);
}
