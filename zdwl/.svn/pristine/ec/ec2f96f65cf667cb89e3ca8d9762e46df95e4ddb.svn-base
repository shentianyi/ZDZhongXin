package com.zd.csms.supervisory.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageQueryVO;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageVO;
import com.zd.csms.supervisory.service.repairecostms.SupervisorRepairCostMessageService;
import com.zd.csms.supervisory.web.form.repairecostms.SupervisorRepairCostMessageFrom;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SupervisorRepairCostMessageAction extends ActionSupport{

	
	private static int[] approvalRole = new int[] { 
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.SR.getCode()};
	
	
	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 * @throws IOException 
	 */
	private int getCurrRole(HttpServletRequest request,HttpServletResponse response) {
		return RoleUtil.getCurrRole(approvalRole, request, response);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SupervisorRepairCostMessageService service = new SupervisorRepairCostMessageService();
		int currRole = getCurrRole(request,response);
		
		SupervisorRepairCostMessageFrom sRCform = (SupervisorRepairCostMessageFrom)form;
		SupervisorRepairCostMessageQueryVO query = sRCform.getQuery();
		query.setCurrRole(currRole);

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("findList", request);
		tools.saveQueryVO(query);
		query.setUserId((UserSessionUtil.getUserSession(request).getUser().getId()));
		List<SupervisorRepairCostMessageVO> list = service.searchRepairCostListByPage(query, tools);
		request.setAttribute("list", list);
		
	    return mapping.findForward("message_page_list");
		
	}
	
	
	/**
	 * 更新已读状态
	 */
	
		public ActionForward submit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			SupervisorRepairCostMessageService service = new SupervisorRepairCostMessageService();
			UserSession user = UserSessionUtil.getUserSession(request);
			String ids = request.getParameter("idsRead");;//已读记录的勾选数据
			String[] num = ids.split(",");
			if (null != num && num.length > 0) {
				for (String id : num) {
					if (StringUtil.isNotEmpty(id)) {
						service.updateReadStatus(user.getUser().getId(),Integer.parseInt(id));
					}
				}
			}
			return findList(mapping, form, request, response);
		}
	
	
	
}
