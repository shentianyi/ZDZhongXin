package com.zd.csms.supervisory.dao.overtime;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.overtime.SupervisorOverTimeMessageQueryVO;
import com.zd.csms.supervisory.model.overtime.SupervisorOverTimeMessageVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface SupervisorOverTimeMessageDao extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<SupervisorOverTimeMessageVO> searchOverTimeList(SupervisorOverTimeMessageQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<SupervisorOverTimeMessageVO> searchOverTimeListByPage(SupervisorOverTimeMessageQueryVO query,IThumbPageTools tools);


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
	
	public boolean getReadStatus(String messageType,int userId);
	
	
}
