package com.zd.csms.repository.dao;

import java.util.Date;
import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryQueryVO;
import com.zd.csms.repository.model.RepositorySelectBean;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IRepositoryDAO extends IDAO{
	
	public String getDataSourceName();
	public List<RepositoryDispatchCityVO> getDispatchCityByRepositoryId(int repositoryId );
	public List<RepositoryVO> searchList(RepositoryQueryVO repositoryQueryVO);
	public List<RepositoryVO> searchPageList(
			RepositoryQueryVO repositoryQueryVO, IThumbPageTools tools);
	public boolean updateRepositoryStatus(int id, int status,int reason);
	public RepositoryVO loadRepositoryBySupervisorId(int supervisorId);
	public RepositoryTrainVO getRepositoryTrainByRepositoryId(
			int id);
	
	/**
	 * 选择框查询sql
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<RepositorySelectBean> selectRepositoryList(RepositoryQueryVO query,IThumbPageTools tools);
	
	public SupervisorBaseInfoVO getSupervisorBaseInfoByRepositoryId(int repositoryId);
	/**
	 * 根据省市区ID获取可驻派城市List 
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 * @return
	 */
	public List<RepositoryDispatchCityVO> findDispatchCityList(String provinceId, String cityId, String countyId);
	/**
	 * 今天监管员培训考核不通过列表
	 * @param today
	 * @return
	 */
	public List<SupMaMsgInfoVO> supAssessNotPassList(Date today);
	/**
	 * 根据状态查询储备库
	 * @param status
	 * @return
	 */
	public List<RepositoryVO> findRepositoryByStatus(int status);
}
