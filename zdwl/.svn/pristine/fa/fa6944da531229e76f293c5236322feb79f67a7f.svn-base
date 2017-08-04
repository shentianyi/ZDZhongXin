package com.zd.csms.messagequartz.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.ActionSupport;
import com.zd.csms.messagequartz.model.InspectionVO;
import com.zd.csms.messagequartz.service.InspectionSupervisorService;
import com.zd.csms.messagequartz.web.form.InspectionSupervisorForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 *每月20日提交巡店检查计划提醒
 *
 */
public class InspectionSupervisorAction extends ActionSupport{

	
	InspectionSupervisorService service = new InspectionSupervisorService();
	/**
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession user = UserSessionUtil.getUserSession(request);
		InspectionSupervisorForm isfrForm = (InspectionSupervisorForm) form;
		InspectionVO query = isfrForm.getInspectionVO();
		
		//查询条件
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<InspectionVO> list = service.findListInspections(query, tools,user.getUser().getId());
		tools.saveQueryVO(query);
		
		request.setAttribute("list", list);
		
		return mapping.findForward("message_inspection_list");
		
	}
	
	//更新已读状态
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ids = request.getParameter("idsRead");//已读记录的勾选数据
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
	
    /** 2)	每月20日提交视频检查计划提醒
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListInVideo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserSession user = UserSessionUtil.getUserSession(request);
		InspectionSupervisorForm isfrForm = (InspectionSupervisorForm)form;
		InspectionVO query = isfrForm.getInspectionVO();
		
		//查询条件
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<InspectionVO> list = service.findListInspectionVideo(query, tools,user.getUser().getId());
		tools.saveQueryVO(query);
		
		request.setAttribute("list", list);
		
		return mapping.findForward("message_inspectionvideo_list");
		
	}
	
	/**
	 * 更新2  每月20日提交视频检查计划   已读提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception 
	 */
	public ActionForward submitInVe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserSession user = UserSessionUtil.getUserSession(request);
		String ids = request.getParameter("idsRead");;//已读记录的勾选数据
		String[] num = ids.split(",");
		if (null != num && num.length > 0) {
			for (String id : num) {
				if (StringUtil.isNotEmpty(id)) {
					service.updateInVideoStatus(user.getUser().getId(),Integer.parseInt(id));
				}
			}
		}
		return findListInVideo(mapping, form, request, response);
	}
	
}
