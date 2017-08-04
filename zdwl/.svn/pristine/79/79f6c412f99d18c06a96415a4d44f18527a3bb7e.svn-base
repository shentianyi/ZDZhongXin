package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.message.model.SupMaMsgQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ISupMaMsgDAO extends IDAO {
	
	public String getDataSourceName();

	/**
	 * 根据messageId查询messageInfo集合
	 * @param id
	 * @return
	 */
	public List<SupMaMsgInfoVO> searchMessageInfoList(int id);
	
	public List<SupMaMsgInfoVO> searchMessageInfoListByPage(SupMaMsgQueryVO query, IThumbPageTools tools);
	/**
	 * 将消息修改为已读
	 * @param id
	 * @param isRead
	 * @return
	 */
	public boolean updateMessageInfoIsread(int id, int isRead);
	/**
	 * 将消息修改为屏蔽
	 * @param id
	 * @param ismask
	 * @return
	 */
	public boolean updateMessageInfoIsmask(int id, int ismask);
	/**
	 * 根据messageId查询未读messageInfo集合
	 * @param id
	 * @return
	 */
	public int searchNotReadMessageInfoList(int id);
	/**
	 * 根据messageID和是否屏蔽查询messageInfo列表
	 * @param messageId
	 * @param ismask
	 * @return
	 */
	public List<MessageVO> searchMsgListByMsgIdAndIsmask(int infoHashCode, int ismask);
	/**
	 * 根据查询条件查询消息
	 * @param query
	 * @return
	 */
	public List<SupMaMsgInfoVO> searchMsgInfoList(SupMaMsgQueryVO query);

	/*
	 * 全部已读
	*/
	public int updateMessageInfoIsreadByAll(SupMaMsgQueryVO query);

}
