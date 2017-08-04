package com.zd.csms.ledger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.CheckStockCarBean;
import com.zd.csms.supervisory.model.CheckStockManageBean;
import com.zd.csms.supervisory.model.CheckStockManageQueryVO;
import com.zd.csms.supervisory.service.CheckStockManageService;
import com.zd.csms.supervisory.web.form.CheckStockManageForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class CheckStockManageAction extends ActionSupport{
	private CheckStockManageService service = new CheckStockManageService();
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
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		CheckStockManageQueryVO query = checkStockManageForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		query.setSubmitflag(2);//查看已提交的
		List<CheckStockManageBean> list = service.findCheckStockManageList(query, tools,request);
		tools.saveQueryVO(query);
		request.setAttribute("list", list);
		initOption(request);
		return mapping.findForward("checkStock_manage_leger");
	}
	/**
	 * 详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		int checkstockId=checkStockManageForm.getCheckStock().getId();
		CheckStockManageBean checkStockBean = service.getCheckStockManageBranById(checkstockId);
		List<CheckStockCarBean> checkStockCarBeans=service.findCheckStockCarList(checkstockId);
		request.setAttribute("checkStockCarBeans", checkStockCarBeans);
		request.setAttribute("checkStockCarBeansSize", checkStockCarBeans.size());
		checkStockManageForm.setCheckStockBean(checkStockBean);
		checkStockManageForm.setCheckStockCarBeans(checkStockCarBeans);
		initOption(request);
		return mapping.findForward("detailPage");
	}
	/**
	 * 初始化参数
	 * 
	 * @param request
	 */
	private void initOption(HttpServletRequest request) {
		int currRole=getCurrRole(request);
		if (currRole == RoleConstants.SUPERVISORY.getCode()) {
			//监管员监管经销商
			request.setAttribute("dealers", OptionUtil.getDealersBySUPERVISOR(UserSessionUtil.getUserSession(request).getUser().getClient_id()));
		}else{
			//所有经销商
			request.setAttribute("dealers", OptionUtil.getDealers());
		}
		//初始化车辆状态复选框
		List<OptionObject> statusOptions = OptionUtil.carStatusOptions();
		request.setAttribute("carStatusOptions", statusOptions);
		request.setAttribute("checkStockResults", OptionUtil.checkStockResults());
		request.setAttribute("checkStockCarActualstate", OptionUtil.checkStockCarActualstate());
	}
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private static int[] approvalRole = new int[]{
		RoleConstants.SUPERVISORY.getCode()
	};
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}


}
