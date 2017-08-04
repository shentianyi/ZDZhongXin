package com.zd.csms.marketing.web.action;

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
import com.zd.csms.marketing.model.PaymentQueryVO;
import com.zd.csms.marketing.model.PaymentVO;
import com.zd.csms.marketing.querybean.PaymentQueryBean;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.service.PaymentService;
import com.zd.csms.marketing.web.form.PaymentForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 监管费标准单
 *
 */
public class PaymentAction extends ActionSupport {
	private PaymentService service = new PaymentService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();

	/**
	 * 流程角色:市场部专员-市场部经理、市场部部长-财务部会计-财务部经理-物流金融中心总监
	 */
	private static int[] approvalRole = new int[] { RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(), RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.FINANCE_ACCOUNTING.getCode(),
			RoleConstants.FINANCE_AMALDAR.getCode(), RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.SR.getCode()};

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
		PaymentForm paymentForm = (PaymentForm) form;
		PaymentVO payment = paymentForm.getPayment();
		payment.setIsArrears(1);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PaymentForm paymentForm = (PaymentForm) form;
		PaymentVO bean = paymentForm.getPayment();
		bean.setCreateUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		bean.setCreateDate(new Date());
		service.add(bean);
		return findList(mapping, paymentForm, request, response);
	}

	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		initOption(request);
		PaymentForm paymentForm = (PaymentForm) form;
		PaymentVO bean = service.get(paymentForm.getPayment().getId());
		paymentForm.setPayment(bean);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PaymentForm PaymentForm = (PaymentForm) form;
		PaymentVO bean = PaymentForm.getPayment();
		bean.setLastModifyDate(new Date());
		bean.setLastModifyUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		service.update(bean);
		return findList(mapping, PaymentForm, request, response);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PaymentForm PaymentForm = (PaymentForm) form;
		PaymentVO bean = PaymentForm.getPayment();
		service.delete(bean);
		return findList(mapping, PaymentForm, request, response);
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
		PaymentForm PaymentForm = (PaymentForm) form;
		PaymentQueryVO query = PaymentForm.getQuery();
		int currRole = getCurrRole(request);
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("paymentlist", request);
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<PaymentQueryBean> list = service.findList(query, tools);

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
		PaymentForm paymentForm = (PaymentForm) form;
		PaymentVO bean = service.get(paymentForm.getPayment().getId());
		paymentForm.setPayment(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.PAYMENT.getCode());
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
			// 权限不足
			return mapping.findForward("");
		}
		initOption(request);
		PaymentForm paymentForm = (PaymentForm) form;
		PaymentQueryVO query = paymentForm.getQuery();
		UserSession user = UserSessionUtil.getUserSession(request);
		service.approval(query, user, currRole);
		//query.setPageType(2);
		return findList(mapping, paymentForm, request, response);
	}

	private void initOption(HttpServletRequest request) {
		request.setAttribute("yesorno", OptionUtil.yesorno());
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
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
		PaymentForm paymentForm = (PaymentForm) form;
		int id = paymentForm.getPayment().getId();
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
		PaymentForm paymentForm = (PaymentForm) form;
		PaymentVO bean = service.get(paymentForm.getPayment().getId());

		paymentForm.setPayment(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.PAYMENT.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		return mapping.findForward("detailPage");
	}
}
