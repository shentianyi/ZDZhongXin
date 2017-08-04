package com.zd.csms.message.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.message.model.SupMaMsgQueryVO;
import com.zd.csms.supervisorymanagement.dao.ISupMaMsgDAO;
import com.zd.csms.supervisorymanagement.dao.SupervisoryManagementDAOFactory;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class SupMaMsgService extends ServiceSupport {

	private ISupMaMsgDAO dao = SupervisoryManagementDAOFactory.getSupMaMsgDAO();
	
	/**
	 * 根据messageId查询messageInfo集合
	 * @param id
	 * @return
	 */
	public List<SupMaMsgInfoVO> searchMessageInfoList(int id) {
		return dao.searchMessageInfoList(id);
	}
	
	/**
	 * @param messageId 消息ID
	 * @param ismask  是否屏蔽
	 * @param userid  用户ID
	 * @return  
	 */
	public boolean searchMsgListByMsgIdAndIsmask(int infoHashCode,int ismask) {
		boolean flag=false;
		List<MessageVO> maskMessage=dao.searchMsgListByMsgIdAndIsmask(infoHashCode,ismask);
		if(maskMessage==null || maskMessage.size()==0){
			flag=true;
		}
		return flag;
	}
	/**
	 * 根据messageType和userId查询messageInfo集合
	 * @param msgType
	 * @param userid
	 * @param signDate
	 * @return
	 */
	public List<SupMaMsgInfoVO> searchMsgInfoList(SupMaMsgQueryVO query) {
		return dao.searchMsgInfoList(query);
	}
	/**
	 * 根据查询条件分页查询消息详情
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<SupMaMsgInfoVO> searchMessageInfoListByPage(SupMaMsgQueryVO query, IThumbPageTools tools) {
		return dao.searchMessageInfoListByPage(query,tools);
	}
	/**
	 * 查询未读消息数量
	 * @param id
	 * @return
	 */
	public int searchNotReadMessageInfoCount(int id) {
		return dao.searchNotReadMessageInfoList(id);
	}
	/**
	 * 将消息修改为已读
	 * @param id
	 * @param isRead
	 * @return
	 */
	public boolean updateMessageInfoIsread(int id, int isRead) {
		return dao.updateMessageInfoIsread(id,isRead);
	}
	/**
	 * 新增消息详情
	 * @param vo
	 * @return
	 */
	public boolean addSupervisorManagementMessageInfo(SupMaMsgInfoVO vo) {
		boolean flag=false;
		try {
			vo.setId(Util.getID(SupMaMsgInfoVO.class));
			flag=dao.add(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 修改消息为已屏蔽
	 * @param id
	 * @param ismask
	 * @return
	 */
	public boolean updateMessageInfoIsmask(int id, int ismask) {
		return dao.updateMessageInfoIsmask(id,ismask);
	}
	/*
	 * 全部已读
	*/
	public int updateMessageInfoIsreadByAll(SupMaMsgQueryVO query) {
		return dao.updateMessageInfoIsreadByAll(query);
	}
}
