package com.zd.csms.supervisory.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.CheckStockDealerVO;
import com.zd.csms.supervisory.model.CheckStockQueryVO;
import com.zd.csms.supervisory.model.CheckStockVO;
import com.zd.csms.supervisory.querybean.CheckStockDealerQueryBean;
import com.zd.csms.supervisory.querybean.CheckStockQueryBean;
import com.zd.csms.supervisory.service.CheckStockService;
import com.zd.csms.supervisory.web.form.CheckStockForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class CheckStockAction extends ActionSupport{
	private CheckStockService service = new CheckStockService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	private UserService userService = new UserService();
	/**
	 * 流程角色:业务部专员-业务部经理-运营管理部部长-物流金融中心总监
	 */
	private static int[] approvalRole = new int[] { 
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(), };

	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request) {
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}

	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		initOption(request);
		request.setAttribute("currDate",new Date());
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserSession user = UserSessionUtil.getUserSession(request);
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockVO bean = checkStockForm.getCheckStock();
		bean.setCreateUserId(user.getUser().getId());
		bean.setCreateDate(new Date());
		service.add(bean);
		return findList(mapping, checkStockForm, request, response);
	}

	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 权限不足
			return mapping.findForward("");
		}
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockVO bean = service.get(checkStockForm.getCheckStock().getId());
		checkStockForm.setCheckStock(bean);
		initOption(request);
		
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);

		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockVO bean = checkStockForm.getCheckStock();

		service.update(bean);
		return findList(mapping, checkStockForm, request, response);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockVO bean = checkStockForm.getCheckStock();
		service.delete(bean);
		return findList(mapping, checkStockForm, request, response);
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
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 没有权限
			return mapping.findForward("");
		}

		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockQueryVO query = checkStockForm.getQuery();
		UserSession user = UserSessionUtil.getUserSession(request);
		query.setUser(user.getUser());
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		List<CheckStockQueryBean> list = service.findList(query, tools);

		request.setAttribute("list", list);
		if (pageType == 1)
			return mapping.findForward("list_wait_approval");
		else
			return mapping.findForward("list_already_approval");
	}

	/**
	 * 跳转到流程页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initOption(request);
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockQueryVO query = checkStockForm.getQuery();
		CheckStockVO bean = service.get(query.getId());
		request.setAttribute("createUser", userService.get(bean.getCreateUserId()));
		checkStockForm.setCheckStock(bean);
		request.setAttribute("dealerList", service.findDealerList(query));

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.CHECKSTOCK.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		return mapping.findForward("approval");
	}

	/**
	 * 流程控制
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward approval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 没有权限
			return mapping.findForward("");
		}
		CheckStockForm checkStockForm = (CheckStockForm) form;
		UserSession user = UserSessionUtil.getUserSession(request);
		CheckStockQueryVO query = checkStockForm.getQuery();
		query.setCurrRole(currRole);

		service.approval(query, user);
		return findList(mapping, checkStockForm, request, response);
	}

	/**
	 * 初始化参数
	 * 
	 * @param request
	 */
	private void initOption(HttpServletRequest request) {

		
	}

	/**
	 * 申请单提交操作，提交后禁止修改和删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockForm checkStockForm = (CheckStockForm) form;
		int id = checkStockForm.getCheckStock().getId();
		service.submit(id);
		return findList(mapping, form, request, response);
	}

	/**
	 * 详情页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initOption(request);
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockQueryVO query = checkStockForm.getQuery();
		CheckStockVO bean = service.get(query.getId());
		checkStockForm.setCheckStock(bean);
		request.setAttribute("createUser", userService.get(bean.getCreateUserId()));
		checkStockForm.setCheckStock(bean);
		request.setAttribute("dealerList", service.findDealerList(query));

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.CHECKSTOCK.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		return mapping.findForward("detailPage");
	}
	
	
	public ActionForward preAddDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		return mapping.findForward("addDealer");
	}
	
	public ActionForward addDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockDealerVO csd = checkStockForm.getCsd();
		CheckStockQueryVO query = checkStockForm.getQuery();
		csd.setCheckStockId(query.getId());
		service.addDealer(csd);
		return dealerList(mapping, checkStockForm, request, response);
	}
	
	public ActionForward dealerList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 没有权限
			return mapping.findForward("");
		}
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockQueryVO query = checkStockForm.getQuery();
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		tools.saveQueryVO(query);
		List<CheckStockDealerQueryBean> list = service.findDealerList(query, tools);

		request.setAttribute("list", list);
		return mapping.findForward("dealer_list");
	}
	
	public ActionForward deleteDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockForm checkStockForm = (CheckStockForm) form;
		CheckStockQueryVO query = checkStockForm.getQuery();
		service.deleteDealer(checkStockForm.getCsd());
		return dealerList(mapping, checkStockForm, request, response);
	}
}
