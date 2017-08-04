package com.zd.csms.message.dao;

import java.util.List;
import com.zd.core.IDAO;
import com.zd.csms.message.dao.mapper.CountLastMessageMapper;
import com.zd.csms.message.model.CountLastMessageVO;
import com.zd.csms.message.model.MessageQueryVO;
import com.zd.csms.message.model.MessageVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IMessageDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<MessageVO> searchMessageList(MessageQueryVO query);
	
	public List<MessageVO> searchMessageListByPage(MessageQueryVO vo, IThumbPageTools tools);
	
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	public boolean addBatch(final List<MessageVO> list);
	
	public MessageVO loadMsgByUserAndType(int id, int type);
	/**
	 * 批量新增 (添加新增的部门名称字段)
	 * @param list
	 * @return
	 */
	public boolean addMessageBatch(final List<MessageVO> list);
	
	/**
	 * 批量标记已阅
	 * @time 20170510
	 * @param int
	 * @return
	 */
	public int signReadMessageById(String ids);
	
	
	/**
	 * 按用户以及类型查询最后一条消息 并统计 每组类型记录数
	 * @number:	
	 * @author:		sxs
	 * @describe:
	 * @param:
	 * @return:
	 */
	public List<CountLastMessageVO> searchLastMessageAndCountByType(
			MessageQueryVO vo, IThumbPageTools tools);
	
	public int signReadMessageByAllIsNotRead(MessageQueryVO mQuery);
}
