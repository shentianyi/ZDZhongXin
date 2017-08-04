package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IExtraworkApplyDAO extends IDAO {
	
	public String getDataSourceName();

	List<ExtraworkApplyVO> findPageList(ExtraworkApplyQueryVO query,
			IThumbPageTools tools);

	public boolean updateExtraworkApplyStatus(int id,int status,int approvalState,int nextApproval);

	public List<CurrentDealerVO> findCurrentDealerListByExtraworkApplyId(
			int extraworkApplyId, int type);

	public boolean deleteCurrentDealerByExtraworkApply(int extraworkApplyId,
			int code);
	
	/**
	 * 根据储备库ID和年月查询审批通过的加班申请列表
	 * @param repositoryId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<ExtraworkApplyVO> findExtraworkListByRepositoryIdAndMonth(
			int repositoryId, int year, int month);

}
