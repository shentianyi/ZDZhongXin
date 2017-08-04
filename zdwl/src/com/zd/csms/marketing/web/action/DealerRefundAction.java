package com.zd.csms.marketing.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.finance.model.RefundInvoiceVO;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.model.DealerRefundQueryVO;
import com.zd.csms.marketing.model.DealerRefundVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.RefundQueryBean;
import com.zd.csms.marketing.service.DealerRefundService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.web.form.DealerRefundForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 经销商退费流转单
 * @author licheng
 *
 */
public class DealerRefundAction extends ActionSupport{
	private DealerRefundService service = new DealerRefundService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();

	/**
	 * 流程角色:市场部专员-市场部经理-市场部部长-财务部会计-财务部经理-物流金融中心总监
	 */
	private static int[] approvalRole = new int[] { 
			RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(), 
			RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.FINANCE_ACCOUNTING.getCode(),
			RoleConstants.FINANCE_AMALDAR.getCode(), 
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

	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundVO vo = dealerRefundForm.getRefund();
		vo.setIsInvoice(1);//默认选中开发票
		initOption(request);
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_EXIT.getCode()));
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundVO bean = dealerRefundForm.getRefund();
		bean.setCreateUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		bean.setCreateDate(new Date());
		service.add(bean);
		return findList(mapping, dealerRefundForm, request, response);
	}

	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		initOption(request);
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundVO bean = service.get(dealerRefundForm.getRefund().getId());
		dealerRefundForm.setRefund(bean);
		
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_EXIT.getCode()));
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundVO bean = dealerRefundForm.getRefund();
		bean.setLastModifyDate(new Date());
		bean.setLastModifyUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		service.update(bean);
		return findList(mapping, dealerRefundForm, request, response);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundVO bean = dealerRefundForm.getRefund();
		service.delete(bean);
		return findList(mapping, dealerRefundForm, request, response);
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
		int currRole = getCurrRole(request,response);
		
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundQueryVO query = dealerRefundForm.getQuery();
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("refundList", request);
		//tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<RefundQueryBean> list = service.findList(query, tools);

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
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundVO bean = service.get(dealerRefundForm.getRefund().getId());
		dealerRefundForm.setRefund(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DEALERREFUND.getCode());
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
		int currRole = getCurrRole(request,response);
		initOption(request);
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundQueryVO query = dealerRefundForm.getQuery();
		UserSession user = UserSessionUtil.getUserSession(request);
		query.setCurrRole(currRole);
		service.approval(query, user);
		//query.setPageType(2);
		return findList(mapping, dealerRefundForm, request, response);
	}

	private void initOption(HttpServletRequest request) {
		request.setAttribute("yesorno", OptionUtil.yesorno());
		
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
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		int id = dealerRefundForm.getRefund().getId();
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
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundVO bean = service.get(dealerRefundForm.getRefund().getId());

		dealerRefundForm.setRefund(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DEALERREFUND.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		return mapping.findForward("detailPage");
	}
	
	/**
	 * 监管费退费管理台账
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward superviseRefund(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request,response);
		
		DealerRefundForm dealerRefundForm = (DealerRefundForm) form;
		DealerRefundQueryVO query = dealerRefundForm.getQuery();
		query.setCurrRole(currRole);

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("refundList", request);
		tools.setPageSize(20);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<RefundQueryBean> list = service.superviseRefundList(query, tools);

		request.setAttribute("list", list);
			return mapping.findForward("supervise_refund_list");
	}
}
