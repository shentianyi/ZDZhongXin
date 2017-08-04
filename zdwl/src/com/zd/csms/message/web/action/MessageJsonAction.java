package com.zd.csms.message.web.action;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.rbac.login.common.UserSessionUtil;

public class MessageJsonAction extends JSONAction{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String callback = request.getParameter("callback");
		int infoNum = MessageUtil.num(UserSessionUtil.getUserSession(request).getUser().getId(),1);//信息提醒
		int warNum = MessageUtil.num(UserSessionUtil.getUserSession(request).getUser().getId(),2);//预警提醒
		List<String> list = new ArrayList<String>();
		list.add(infoNum+"");
		list.add(warNum+"");
		super.makeJSONObject(response, callback, list);
		return null;
	}
}
