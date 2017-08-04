package com.zd.csms.marketing.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.IDAO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.querybean.DealerSupervisorBean;

public interface IDealerSupervisorDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<DealerSupervisorVO> searchDealerSupervisorList(DealerSupervisorQueryVO query);
	
	public List<DraftVO> searchDraftSupervisorList(int id);
	
	/**
	 * @param dealerId
	 * @param repoId
	 * @return
	 */
	public boolean updateRepoByDealerId(int dealerId,int repoId);
	
	public DealerSupervisorBean getSupervisorNameByDealerId(int dealerId);
	
	/**
	 * 删除关联
	 * @param dealerId
	 * @return
	 */
	public boolean deleteByRepoIdanddealerId(int dealerId);
}
