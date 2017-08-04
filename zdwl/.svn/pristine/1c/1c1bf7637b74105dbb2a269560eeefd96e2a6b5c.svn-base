package com.zd.csms.message.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.message.dao.IMessageDAO;
import com.zd.csms.message.dao.MessageDAOFactory;
import com.zd.csms.message.dao.mapper.MessageMapper;
import com.zd.csms.message.model.CountLastMessageVO;
import com.zd.csms.message.model.MessageInfoVO;
import com.zd.csms.message.model.MessageQueryVO;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class MessageService extends ServiceSupport {

	private IMessageDAO dao = MessageDAOFactory.getMessageDAO();
	
	public boolean addMessage(MessageVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(MessageVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	public int addMessages(MessageVO vo) throws Exception {
		int id = 0;
		vo.setId(Util.getID(MessageVO.class));
		dao.add(vo);
		if(vo.getId()>0){
			id = vo.getId();
		}
		return id;
	}
	public int addMessageInfo(MessageInfoVO vo) throws Exception {
		int id = 0;
		vo.setId(Util.getID(MessageInfoVO.class));
		dao.add(vo);
		if(vo.getId()>0){
			id = vo.getId();
		}
		return id;
	}
	public boolean updMessage(MessageVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public MessageVO loadMessageById(int id) throws Exception{
		MessageVO message = dao.get(MessageVO.class, id,new MessageMapper());
		return message;
	}
	public MessageVO loadMsgByUserAndType(int id, int type) throws Exception{
		MessageVO message = dao.loadMsgByUserAndType(id, type);
		return message;
	}
	
	public List<MessageVO> searchMessageList(MessageQueryVO query){
		return dao.searchMessageList(query);
	}
	
	public List<MessageVO> searchMessageListByPage(MessageQueryVO vo, IThumbPageTools tools){
		return dao.searchMessageListByPage(vo, tools);
	}
	
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	public boolean addBatch(final List<MessageVO> list){
		return dao.addBatch(list);
	}
	/**
	 * 批量新增 (添加新增的部门名称字段)
	 * @param list
	 * @return
	 */
	public boolean addMessageBatch(final List<MessageVO> list){
		return dao.addMessageBatch(list);
	}
	/**
	 * 批量标记已读（更改阅读状态）-- 20170510  -- 需求28
	 * @param list
	 * @return
	 */
	public int signReadMessageById(String ids) {
		return dao.signReadMessageById(ids);
	}
	public List<CountLastMessageVO> searchLastMessageAndCountByType(MessageQueryVO mQuery,
			IThumbPageTools thumbPageTools) {
		return dao.searchLastMessageAndCountByType(mQuery, thumbPageTools);
	}
	/*
	 * 标记全部已读
	*/
	public int signReadMessageByAllIsNotRead(MessageQueryVO mQuery) {
		return dao.signReadMessageByAllIsNotRead(mQuery);
	}

}
