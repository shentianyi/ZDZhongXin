package com.zd.csms.messageAndWaring.message.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.zd.core.ServiceSupport;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.messageAndWaring.message.contant.MsgAttributesContants;
import com.zd.csms.messageAndWaring.message.dao.IMessageManagerDAO;
import com.zd.csms.messageAndWaring.message.dao.MessageDAOFactory;
import com.zd.csms.messageAndWaring.message.model.MessageInfoUserVO;
import com.zd.csms.messageAndWaring.message.model.MessageInfoVO;
import com.zd.csms.messageAndWaring.message.model.WaringInfoUserVO;
import com.zd.csms.messageAndWaring.message.model.WaringInfoVO;
import com.zd.csms.messageAndWaring.message.queryBean.MessageReceiveQueryBean;
import com.zd.csms.util.Util;
/**
 * 信息提醒和预警发送操作
 * @author zhangzhicheng
 *
 */
public class MessageSendService extends ServiceSupport{
	private IMessageManagerDAO dao = MessageDAOFactory.getMessageManagerDAO();
	private Class<? extends MessageInfoVO> msgClassType ;
	private Class<? extends WaringInfoVO>  waringClassType ;
	private List<MessageReceiveQueryBean>  receiveUsers;
	private Map<String,List<MessageReceiveQueryBean>> receiveUserMaps ;
	public MessageSendService(Class<? extends MessageInfoVO> msgClassType, Class<? extends WaringInfoVO> waringClassType) {
		super();
		this.msgClassType = msgClassType;
		this.waringClassType = waringClassType;
	}

	public void setReceiveUsers(List<MessageReceiveQueryBean> receiveUsers) {
		this.receiveUsers = receiveUsers;
	}


	public List<MessageReceiveQueryBean> getReceiveUsers() {
		return receiveUsers;
	}

	public void setReceiveUserMaps(
			Map<String, List<MessageReceiveQueryBean>> receiveUserMaps) {
		this.receiveUserMaps = receiveUserMaps;
	}

	public boolean addMessage(List<? extends MessageInfoVO> msgInfos, int msgType) throws Exception {
		boolean fag=false ;
		if(!CollectionUtils.isEmpty(msgInfos)){
		   for(MessageInfoVO info:msgInfos){
			     info.setType(msgType);
	        	 info.setSignId(JSON.toJSONString(info).hashCode());
	        	 info.setId(Util.getID(msgClassType));
	        	 info.setCreateDate(new Date());
		   }
		   
		   try {
			   fag=dao.addMessageBath(msgInfos);
			   if(fag)
			   fag=sendMessage(msgInfos,msgType);
		    } catch (Exception e) {
			  fag=false;
			  e.printStackTrace();
		    }
		  
		}
		return fag ;
	}


	public boolean addWaring(List<? extends WaringInfoVO> wringInfos, int msgType) throws Exception {
		boolean fag=false ;
		if(!CollectionUtils.isEmpty(wringInfos)){
			 for(WaringInfoVO info:wringInfos){
			     info.setType(msgType);
	        	 info.setSignId(JSON.toJSONString(info).hashCode());
	        	 info.setId(Util.getID(waringClassType));
	        	 info.setCreateDate(new Date());
		    }
		   
		   try {
			   fag=dao.addMessageBath(wringInfos);
			   if(fag)
			   fag=sendWaring(wringInfos,msgType);
		    } catch (Exception e) {
			  fag=false;
			  e.printStackTrace();
		    }
		  
		}
		return fag ;
	}
	
	
	// 发送提醒
	private boolean sendMessage(List<? extends MessageInfoVO> vos ,int msgType) throws Exception {
		boolean fag = false;
		 List<MessageInfoUserVO> msgUsers = new ArrayList<MessageInfoUserVO>();
		 for(MessageInfoVO vo : vos){
			 msgUsers.clear();
			 List<MessageReceiveQueryBean> beans=receiveUserMaps.get(vo.getDealerId()+"") ;
			 for (MessageReceiveQueryBean messageReceive : beans) {
				  msgUsers.add(getMessageUser(vo, messageReceive.getUserId()));
				  messageReceive.setNum(messageReceive.getNum()+1);
			 }
			 
			 if(!CollectionUtils.isEmpty(msgUsers)){
				 fag=dao.addMessageUserBatch(msgUsers);
				 if(fag){
					 
				 }
			 }
			 
		 }
		 fag=changeMessageGroup(receiveUsers);
		 return fag;
	}
	
	
	// 发送预警
	private  boolean sendWaring(List<? extends WaringInfoVO> vos , int msgType) throws Exception {
		boolean fag = false;
		
		 List<WaringInfoUserVO> msgUsers = new ArrayList<WaringInfoUserVO>();
		 for(WaringInfoVO vo : vos){
			 msgUsers.clear();
			 List<MessageReceiveQueryBean> beans=receiveUserMaps.get(vo.getDealerId()+"") ;
			 receiveUsers.addAll(beans);
			 for (MessageReceiveQueryBean messageReceive : receiveUsers) {
				  msgUsers.add(getWaringUser(vo, messageReceive.getUserId()));
				  messageReceive.setNum(messageReceive.getNum()+1);
			 }
			 
			 if(!CollectionUtils.isEmpty(msgUsers)){
				 fag=dao.addWaringUserBatch(msgUsers);
			 }
			 
		 }
		 fag=changeMessageGroup(receiveUsers);
     	 return fag;
	}
	
	// 更新用户消息群组(信息数量)
	private boolean changeMessageGroup(List<MessageReceiveQueryBean> beans)
			throws Exception {
		boolean fag = false;
		for(MessageReceiveQueryBean  messageReceive:beans){
			MessageVO mvo = findMessageGroupByUserId(messageReceive.getUserId(),messageReceive.getMsgType());
			if (mvo == null) {
				mvo = new MessageVO();
				mvo.setId(Util.getID(MessageVO.class));
				mvo.setUserid(messageReceive.getUserId());
				mvo.setName("" + messageReceive.getNum());
				mvo.setUrl(messageReceive.getTypeUrl());
				mvo.setIsread(MsgAttributesContants.NOREAD.getCode());
				mvo.setMsgtype(messageReceive.getType());
				mvo.setType(messageReceive.getMsgType());
				mvo.setDeptName(messageReceive.getDepName());
				fag = dao.add(mvo);
			} else {
				mvo.setUrl(messageReceive.getTypeUrl());
				mvo.setName(Integer.valueOf(mvo.getName())+messageReceive.getNum()+"");
				mvo.setIsread(MsgAttributesContants.NOREAD.getCode());
				fag = dao.update(mvo);
			}
		}
		

		return fag;
	}

	//获取用户的某个信息群组
	private MessageVO findMessageGroupByUserId(int userId, int msgType) {
		return dao.getMessageGroupByUserId(userId, msgType);
	}

	//用户与提醒绑定
	private  MessageInfoUserVO getMessageUser(MessageInfoVO vo, int userId) {
		MessageInfoUserVO infoUserVO = new MessageInfoUserVO();
		infoUserVO.setUserId(userId);
		infoUserVO.setMsgInfoId(vo.getId());
		infoUserVO.setMsgType(vo.getType());
		infoUserVO.setRead(MsgAttributesContants.NOREAD.getCode());
		infoUserVO.setSignId(vo.getSignId());
		return infoUserVO ;
	}
	
	//用户与预警绑定
	private  WaringInfoUserVO getWaringUser(WaringInfoVO vo, int userId){
		WaringInfoUserVO infoUserVO = new WaringInfoUserVO();
		infoUserVO.setUserId(userId);
		infoUserVO.setMsgInfoId(vo.getId());
		infoUserVO.setMsgType(vo.getType());
		infoUserVO.setRead(MsgAttributesContants.NOREAD.getCode());
		infoUserVO.setShield(MsgAttributesContants.NOSHIELD.getCode());
		infoUserVO.setSignId(vo.getSignId());
		return infoUserVO;
	}

	
}
