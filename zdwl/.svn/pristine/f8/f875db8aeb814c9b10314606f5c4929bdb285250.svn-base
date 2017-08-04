package com.zd.csms.message.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.message.model.NoticeQueryVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.message.service.NoticeService;
import com.zd.csms.message.web.form.NoticeForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class NoticeAction extends ActionSupport {
	private NoticeService service = new NoticeService();
	
	public ActionForward noticeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NoticeForm nForm = (NoticeForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		NoticeQueryVO query = nForm.getQuery();
		query.setUserId(user.getId());
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("noticeList", request);
		tools.saveQueryVO(query);
		//按条件查询分页数据
		List<NoticeVO> list = service.searchNoticeListByPage(query, tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("notice_list");
	}
	
	public ActionForward noticesList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NoticeForm nForm = (NoticeForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		NoticeQueryVO query = nForm.getQuery();
		query.setUserId(user.getId());
		//按条件查询分页数据
		List<NoticeVO> list = service.searchNoticeList(query);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("notices_list");
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
		NoticeForm nForm = (NoticeForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		NoticeQueryVO query = nForm.getQuery();
		query.setUserId(user.getId());
		//按条件查询分页数据
		List<NoticeVO> list = service.systemList(query);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("notice_list");
	}
	
	public void setOptions(HttpServletRequest request){
				
	}
}
