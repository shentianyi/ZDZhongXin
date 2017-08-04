package com.zd.csms.supervisory.dao.costmaill;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageQueryVO;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface SupervisorCostMailMessageDao extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<SupervisorCostMailMessageVO> searchCostMailList(SupervisorCostMailMessageQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<SupervisorCostMailMessageVO> searchCostMailListByPage(SupervisorCostMailMessageQueryVO query,IThumbPageTools tools);


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
