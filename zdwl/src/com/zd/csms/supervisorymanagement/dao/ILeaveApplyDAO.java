package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyVO;
import com.zd.csms.supervisorymanagement.model.LeaveReplaceVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ILeaveApplyDAO extends IDAO {
	
	public String getDataSourceName();

	List<LeaveApplyVO> findPageList(LeaveApplyQueryVO query,
			IThumbPageTools tools);

	List<LeaveReplaceVO> findLeaveReplaceListByLeaveApplyId(int leaveApplyId);

	public boolean deleteLeaveReplaceListByLeaveApplyId(int leaveApplyId);

	public boolean updateLeaveApplyStatus(int id,int status,int approvalState,int nextApproval);
	
	/**
	 * 根据userid查询请假信息
	 * @param userid
	 * @return
	 */
	public List<LeaveApplyVO> findLeaveReplaceListByUserId(
			int userid,int year,int month);

	public List<LeaveReplaceVO> findLeaveReplaceListByRepositoryIdAndMonth(
			int repositoryId, int year, int month);

}
