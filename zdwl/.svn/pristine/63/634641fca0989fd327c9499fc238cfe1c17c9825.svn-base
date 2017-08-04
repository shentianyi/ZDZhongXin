package com.zd.csms.region.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.region.model.RegionQueryVO;
import com.zd.csms.region.model.RegionVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IRegionDAO extends IDAO{
	
	public String getDataSourceName();
	
	/**
	 * 查询集合
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	public List<RegionVO> findList(RegionQueryVO queryVO) throws Exception;
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<RegionVO> searchRegionListByPage(RegionQueryVO query, IThumbPageTools tools) throws Exception;

	public boolean updRegionState(int id, int status);

	public List<RegionVO> validationName(int parentId,String name);
}
