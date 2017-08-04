package com.zd.csms.messagequartz.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.messagequartz.model.SupervisorVO;
import com.zd.csms.messagequartz.model.UnInspectionPlanQueryVO;
import com.zd.csms.messagequartz.model.UnInspectionPlanVO;
import com.zd.csms.planandreport.model.InspectionPlanVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface UnInspectionPlanDao extends IDAO {

	public String getDataSourceName();
	
	/**
	 * 定时任务 未按风控巡检计划执行提醒
	 * @return
	 */
	public List<InspectionPlanVO> findList();
	
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<UnInspectionPlanVO> searchUnInspectionPlanList(UnInspectionPlanQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<UnInspectionPlanVO> searchUnInspectionPlanByPage(UnInspectionPlanQueryVO query,IThumbPageTools tools);


	/**
	 * 更新 已读状态
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateReadStatus(int userId,int id);
	
	/**
	 * 判断此类消息 是否还有未读状态
	 */
	
	public boolean getReadStatus(int messageType,int userId);
	
	
}
