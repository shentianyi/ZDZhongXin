package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IFixedAssetListDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<FixedAssetListVO> searchFixedAssetList(FixedAssetListQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<FixedAssetListVO> searchFixedAssetListByPage(FixedAssetListQueryVO query,IThumbPageTools tools);
	
	/**
	 * 
	 * @number:	
	 * @author:		sxs
	 * @describe:
	 * @param:
	 * @return:
	 */
	public List<FixedAssetListVO> searchfixedAssetListByClientId(int client_id);
	public List<FixedAssetListVO> searchfixedAssetListByUserName(int username);
	
}
