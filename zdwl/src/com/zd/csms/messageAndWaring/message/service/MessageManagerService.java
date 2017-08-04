package com.zd.csms.messageAndWaring.message.service;
import com.zd.core.ServiceSupport;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.messageAndWaring.message.contant.MsgAttributesContants;
import com.zd.csms.messageAndWaring.message.dao.IMessageManagerDAO;
import com.zd.csms.messageAndWaring.message.dao.MessageDAOFactory;
import com.zd.csms.messageAndWaring.message.web.form.MessageManagerForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
/**
 * 信息提醒和预警管理(查询,已读,屏蔽等操作)
 * @author zhangzhicheng
 *
 */
public abstract class MessageManagerService extends ServiceSupport {
	private IMessageManagerDAO dao = MessageDAOFactory.getMessageManagerDAO();
	
	// 提交信息提醒
	public  boolean submitMessage(MessageManagerForm form)
			throws Exception {
		if(StringUtil.isBlank(form.getIdsRead()))
		 return false ;
		boolean fag = readMessage(form.getIdsRead(), form.getUserId());
		if(fag){
			fag=dao.isAllReadMessage(form.getUserId(), form.getMsgType());
			if (fag) {
				MessageVO vo =getMessageGroup(form) ;
				if(vo!=null){
				 vo.setIsread(MsgAttributesContants.READ.getCode());
				 fag = dao.update(vo);
			    }
			}
		}
		
		return fag;
	}
	
	// 提交信息预警
	public  boolean submitWaring(MessageManagerForm form)
			throws Exception {
		 boolean fag=false ;
		 if(!StringUtil.isBlank(form.getIdsRead())){
			fag = readWaring(form.getIdsRead(), form.getUserId());
		 }
		 
		 if(!StringUtil.isBlank(form.getIdsShield())){
			 shieldWaring(form.getIdsShield(), form.getUserId());
		 }
		 
		 if(fag){
			fag=dao.isAllReadWaring(form.getUserId(), form.getMsgType());
			if (fag) {
				MessageVO vo = getMessageGroup(form) ;
				if(vo!=null){
					vo.setIsread(MsgAttributesContants.READ.getCode());
					fag = dao.update(vo);
				}
				
			}
		}
		
		return fag;
	}
	
	// 已读消息
	private boolean readMessage(String idstr, int userId) throws Exception {
		return dao.readMessage(userId, idstr);
	}

	// 已读预警
	private boolean readWaring(String idstr, int userId) throws Exception {
		return dao.readWaring(userId, idstr);
	}

	// 屏蔽信息预警
	private boolean shieldWaring(String idstr, int userId) throws Exception {
		return dao.shieldWaring(userId, idstr);

	}

	
	private MessageVO getMessageGroup(MessageManagerForm form){
		return dao.getMessageGroupByUserId(form.getUserId(), form.getMsgType());
	}
	

	public abstract Object  findMessageList(MessageManagerForm remindForm, IThumbPageTools tools) ;

	public abstract Object findWaringList(MessageManagerForm remindForm,IThumbPageTools tools) ;

	
}
