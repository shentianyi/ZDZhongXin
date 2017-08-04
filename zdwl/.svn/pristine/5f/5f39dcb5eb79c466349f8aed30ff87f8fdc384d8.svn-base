package com.zd.csms.roster.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterQueryBean;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IRosterDAO extends IDAO{
	public String getDataSourceName();
	public List<PostChangeVO> getPostChangeByRosterId(int rosterId);
	public List<RosterVO> searchList(RosterQueryVO rosterQueryVO);
	public List<RosterVO> searchPageList(
			RosterQueryVO rosterQueryVO, IThumbPageTools tools);
	
	/**
	 * 根据花名册的主键Id获取监管员信息
	 * @param rosterId
	 * @return
	 */
	public RosterQueryBean findRosterById(int rosterId);
	public String  getLoginidList(int REPOSITORYID);
	public List<RosterVO> searchRosterByRepositoryId(int repositoryId);
	public RosterVO getRosterBySupervisorId(int supervisorId);
	public BusinessTransferVO loadBusinessTransferByRId(int repositoryId);
	public DealerVO loadDealerByRId(int repositoryId);
	public RosterVO loadById(int id);
}
