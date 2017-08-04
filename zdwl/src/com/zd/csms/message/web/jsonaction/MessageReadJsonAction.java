package com.zd.csms.message.web.jsonaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.tools.StringUtil;

public class MessageReadJsonAction extends JSONAction{
	private MessageService messageService = new MessageService();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		String id = request.getParameter("id");
		MessageVO message = messageService.loadMessageById(Integer.parseInt(id));
		//如果是审批提醒则修改为已读
		if(message.getType()<40){
			message.setIsread(2);
			messageService.updMessage(message);
		}
		return null;
	}
}
