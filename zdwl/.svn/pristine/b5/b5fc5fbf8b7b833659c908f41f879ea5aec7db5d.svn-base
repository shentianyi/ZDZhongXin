package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IFixedAssetsDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<FixedAssetsVO> searchFixedAssetsList(FixedAssetsQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<FixedAssetsVO> searchFixedAssetsListByPage(FixedAssetsQueryVO query,IThumbPageTools tools);
	/**
	 * 修改固定资产使用人
	 * @param fixedAssets
	 * @param sendee
	 * @return
	 */
	public boolean updateFixedAssetsSendee(int fixedAssetsId, int sendee);
	
	/**
	 * 根据用户Id查询当前使用资产列表
	 * @param id
	 * @return List<FixedAssetsVO>
	 */
	public List<FixedAssetsVO> searchFixedAssetsSendeeByUserId(int id);
	
	
}
