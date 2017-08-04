package com.zd.csms.message.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.wsdl.Constants;
import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.NoteQueryVO;
import com.zd.csms.message.model.CountLastMessageVO;
import com.zd.csms.message.model.MessageQueryVO;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.NoticeQueryVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.message.service.NoticeService;
import com.zd.csms.message.web.form.MessageForm;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class MessageAction extends ActionSupport {

	public ActionForward messageListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return messageList(mapping, form,request, response);
	}
	
	
	public ActionForward messageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MessageForm mform = (MessageForm)form;
		MessageService service = new MessageService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		//预警
		
		MessageQueryVO yQuery = mform.getMessagequery();
		yQuery.setMsgtype(2);//预警类型
		yQuery.setUserid(user.getId());
		yQuery.setIsread(1);//未读
		//按条件查询分页数据
		List<MessageVO> ylist = service.searchMessageList(yQuery);
		request.setAttribute("ylist", ylist);
		
		//消息提醒  首页只查询100条，为了加快首页加载速度
		MessageQueryVO mQuery = new MessageQueryVO();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("a", request);
		tools.setPageSize(100);
		mQuery.setMsgtype(1);//消息类型
		mQuery.setUserid(user.getId());
		mQuery.setIsread(1);//已读
		//按条件查询分页数据
		List<MessageVO> mlist = service.searchMessageListByPage(mQuery, tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("mlist", mlist);
		
		//公告
		NoticeService nservice = new NoticeService();
		NoticeQueryVO query = new NoticeQueryVO();
		query.setUserId(user.getId());
		query.setUser(user);
	
		//按条件查询分页数据
		List<NoticeVO> nlist = nservice.searchNoticeList(query);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("nlist", nlist);
		
		//制度
		NoteQueryVO nQuery = new NoteQueryVO();
		nQuery.setUserid(UserSessionUtil.getUserSession(request).getUser().getId());
		
		//按条件查询分页数据
		List<NoticeVO> systemList = nservice.systemList(query);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("systemList", systemList);
		
		//返回列表页面
		return mapping.findForward("message_list");
	}
	
	public ActionForward warningList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MessageForm mform = (MessageForm)form;
		MessageService service = new MessageService();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		MessageQueryVO mQuery = mform.getMessagequery();
		mQuery.setMsgtype(2);
		mQuery.setUserid(user.getId());
		mQuery.setIsread(1);
		//按条件查询分页数据
		List<MessageVO> list = service.searchMessageList(mQuery);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("warning_list");
	}
	
	public ActionForward messagePageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MessageForm mform = (MessageForm)form;
		MessageService service = new MessageService();
		
		MessageQueryVO mQuery = mform.getMessagequery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		mQuery.setMsgtype(1);
		
		
		//如果不是超级角色账户  则只能看自己的提醒
		if(user.getClient_type() != ClientTypeConstants.SR.getCode()){
			mQuery.setUserid(user.getId());
		}else{
			mQuery.setUserid(-1);
		}
//   	mQuery.setUserid(203);
//		mQuery.setIsread(1);
//		if(mQuery.getIsread()<=0){
//			mQuery.setIsread(1);//默认为未读
//		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("messagePageList",request);
		thumbPageTools.saveQueryVO(mQuery);//记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(20);
		
		//按条件查询分页数据
		request.setAttribute("type", mQuery.getType());
		if(mQuery.getType() == 18){
			List<CountLastMessageVO> list = service.searchLastMessageAndCountByType(mQuery, thumbPageTools);
			//将查询结果设置request中，用于页面显示
			request.setAttribute("list", list);
			
		}else{
			List<MessageVO> list = service.searchMessageListByPage(mQuery, thumbPageTools);
			//将查询结果设置request中，用于页面显示
			request.setAttribute("list", list);
		}
		setOptions(request);
		//返回列表页面
		return mapping.findForward("message_page_list");
	}
	
	public ActionForward warningPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MessageForm mform = (MessageForm)form;
		MessageService service = new MessageService();
		
		MessageQueryVO mQuery = mform.getMessagequery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		mQuery.setMsgtype(2);
		
		if(user.getClient_type() != ClientTypeConstants.SR.getCode()){
			mQuery.setUserid(user.getId());
		}else{
			mQuery.setUserid(-1);
		}
//		if(mQuery.getIsread()<=0){
//			mQuery.setIsread(1);//默认为未读
//		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("warningPageList",request);
		thumbPageTools.saveQueryVO(mQuery);//记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(20);
		//按条件查询分页数据
		List<MessageVO> list = service.searchMessageListByPage(mQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("warning_page_list");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		
	}
	
	/**
	 * 制度列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward systemList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MessageForm mform = (MessageForm)form;
		NoticeService nservice = new NoticeService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		NoticeQueryVO query = new NoticeQueryVO();
		query.setUserId(user.getId());
		query.setUser(user);
		List<NoticeVO> list = nservice.systemList(query);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//返回列表页面
		return mapping.findForward("system_list");
	}
	
	/**
	 * 公告列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward noticeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MessageForm mform = (MessageForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		NoticeService nservice = new NoticeService();
		NoticeQueryVO query = new NoticeQueryVO();
		query.setUserId(user.getId());
		query.setUser(user);
		//按条件查询分页数据
		List<NoticeVO> list = nservice.searchNoticeList(query);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//返回列表页面
		return mapping.findForward("notice_list");
	}
	
	//	将选中标记已读 -- 20170510 -- 需求28
	public ActionForward newsReaded(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MessageService messageService = new MessageService();
		String[] ids = request.getParameterValues("ids");
		if(ids.length == 0){
			return null;
		}else{
			for (String id:ids) {
				int count = messageService.signReadMessageById(id);
				if (count > 0) {
					System.out.println("标记成功！");
					return messagePageList(mapping,form,request,response);
				}else {
					System.out.println("标记失败！");
				}
			}
			
		}
		return messagePageList(mapping,form,request,response);
	}
	/*
	 * 标记全部已读
	*/
	public ActionForward newsReadedAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MessageForm mform = (MessageForm)form;
		MessageService messageService = new MessageService();
		
		MessageQueryVO mQuery = mform.getMessagequery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		mQuery.setMsgtype(1);
		mQuery.setUserid(user.getId());
		System.out.println("================"+mQuery.getMsgtype()+"/"+mQuery.getUserid());
		int count = messageService.signReadMessageByAllIsNotRead(mQuery);
		if (count > 0) {
			System.out.println("标记成功！");
			return messagePageList(mapping,form,request,response);
		}else {
			System.out.println("标记失败！");
		}
		return messagePageList(mapping,form,request,response);
	}
	
	
	
	
}
