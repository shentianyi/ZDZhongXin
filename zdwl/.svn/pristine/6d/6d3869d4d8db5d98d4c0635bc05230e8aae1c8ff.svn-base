package com.zd.csms.messagequartz.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.messagequartz.model.InspectRemindInfoVO;
import com.zd.csms.messagequartz.model.InspectRemindVO;
import com.zd.csms.messagequartz.model.InspectRemindQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface InspectRemindDao extends IDAO {

	public String getDataSourceName();
	
	public InspectRemindInfoVO getInfoVO(int id);
	
	
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<InspectRemindVO> searchInspectRemindList(InspectRemindQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<InspectRemindVO> searchInspectRemindByPage(InspectRemindQueryVO query,IThumbPageTools tools);


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
	
	public boolean getReadStatus(int messageType, int userId);
	
	
}
