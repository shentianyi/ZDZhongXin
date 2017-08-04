package com.zd.csms.supervisorymanagement.dao;

import java.util.Date;
import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IResignApplyDAO extends IDAO {
	
	public String getDataSourceName();

	List<ResignApplyVO> findPageList(ResignApplyQueryVO query,
			IThumbPageTools tools);
	
	List<ResignApplyVO> findList(ResignApplyQueryVO query);
	
	public boolean updateResignApplyStatus(int id,int status,int approvalState,int nextApproval);

	public List<CurrentDealerVO> findCurrentDealerListByResignApplyId(
			int resignApplyId, int type);

	public boolean deleteCurrentDealerByResignApply(int resignApplyId,
			int code);

	public ResignApplyVO getByResignPerson(int resignPerson);

	public List<ResignApplyVO> findListByExpectResignTime(Date expectResignTime);

}
