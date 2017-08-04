package com.zd.csms.messagequartz.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.messagequartz.model.SupervisorVO;
import com.zd.csms.messagequartz.service.MessageQuartzService;
import com.zd.csms.messagequartz.web.form.SupervisorStorageForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;


/**
 * 监管员定时任务监管员生日提醒
 *
 */
public class DealerSupervisorAction extends ActionSupport{

	
	MessageQuartzService service = new MessageQuartzService();
	/**
	 * 生日提醒分页查询()
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession user = UserSessionUtil.getUserSession(request);
		SupervisorStorageForm msgForm = (SupervisorStorageForm) form;
		SupervisorVO query = msgForm.getSupervisorVO();
		
		//查询条件
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<SupervisorVO> list = service.findList(query, tools,user.getUser().getId());
		tools.saveQueryVO(query);
		
		request.setAttribute("list", list);
		
		return mapping.findForward("message_page_list");
		
	}
	
	//更新生日提醒已读状态
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ids = request.getParameter("ids");//已读记录的勾选数据
		String[] num = ids.split(",");
		UserSession user = UserSessionUtil.getUserSession(request);
		if (null != num && num.length > 0) {
			for (String id : num) {
				if (StringUtil.isNotEmpty(id)) {
					service.updateReadStatus(user.getUser().getId(),Integer.parseInt(id));
				}
			}
		}
		return findList(mapping, form, request, response);
	}
	
	/**
	 * 监管员入职满一年 提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListYearAndDay(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession user = UserSessionUtil.getUserSession(request);
		SupervisorStorageForm msgForm = (SupervisorStorageForm) form;
		SupervisorVO query = msgForm.getSupervisorVO();
		
		//查询条件
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<SupervisorVO> list = service.findListSupervisorsYear(query, tools,user.getUser().getId());
		tools.saveQueryVO(query);
		
		request.setAttribute("list", list);
		
		return mapping.findForward("message_yearpage_list");
		
	}
	
	/**
	 * 更新监管员入职满一年 提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception 
	 */
	public ActionForward submitYear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ids = request.getParameter("ids");//已读记录的勾选数据
		String[] num = ids.split(",");
		UserSession user = UserSessionUtil.getUserSession(request);
		if (null != num && num.length > 0) {
			for (String id : num) {
				if (StringUtil.isNotEmpty(id)) {
					service.updateYearReadStatus(user.getUser().getId(),Integer.parseInt(id));
				}
			}
		}
		return findListYearAndDay(mapping, form, request, response);
	}
	
}
