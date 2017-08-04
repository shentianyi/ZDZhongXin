package com.zd.csms.messagequartz.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.messagequartz.model.InspectionVO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 
 *
 */
public interface InspectionSupervisorDao extends IDAO{

	
	
	/**
	 * 提交巡店检查计划提醒列表
	 * @param query
	 * @param tools
	 * @param userId
	 * @return
	 */
	public List<InspectionVO> findListInspections(InspectionVO query,
			IThumbPageTools tools,int userId);
	
	
	/**
	 * 提交巡店检查计划提醒   状态
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateReadStatus(int userId,int id);
	
	public boolean getReadStatus(int messageType,int userId);
	

	/**
	 * 提交视频检查计划提醒
	 */
	List<InspectionVO> findListInspectionVideo(InspectionVO query,
			IThumbPageTools tools, int userId);

    /**
	 * 更改 视频检查计划提醒 状态
	 * @param userId
	 * @param id
	 * @return
	 */
	boolean updateInVideoStatus(int userId, int id);
	

	
}
