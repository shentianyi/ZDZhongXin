package com.zd.csms.marketing.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.UnBindDealerQueryVO;
import com.zd.csms.marketing.model.UnBindDealerVO;
import com.zd.csms.marketing.querybean.UnBindDealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.service.UnBindDealerService;
import com.zd.csms.marketing.web.form.UnBindDealerForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 经销商/监管员拆绑提醒（流转）
 * @author licheng
 *
 */
public class UnBindDealerAction extends ActionSupport{
	UnBindDealerService service = new UnBindDealerService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	private DealerService dealerService = new DealerService();
	
	/**
	 * 流程角色:市场部专员->市场部经理->市场部部长->招聘专员->业务部专员
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(),
			RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
			RoleConstants.SR.getCode()};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		initOption(request);
		return mapping.findForward("add");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = unbindForm.getUnbd();
		bean.setCreateDate(new Date());
		bean.setCreateUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		//变更监管费
		
		service.add(bean);
		return findList(mapping, unbindForm, request, response);
	}

	
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole = getCurrRole(request);
		if(currRole==-1){
			//权限不足
		}
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = service.get(unbindForm.getUnbd().getId(),request);
		unbindForm.setUnbd(bean);
		initOption(request);
		if((RoleConstants.MARKET_COMMISSIONER.getCode()==currRole
				||currRole == RoleConstants.SR.getCode())
				&&(bean.getApprovalState()==ApprovalContant.APPROVAL_NOT_SUBMIT.getCode()
				||bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode())){
			return mapping.findForward("update");
		}
		if((RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode() ==currRole
				||currRole == RoleConstants.SR.getCode())
				&&bean.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()){
			return mapping.findForward("update_zp");
		}
		if((RoleConstants.BUSINESS_COMMISSIONER.getCode() ==currRole
				||currRole == RoleConstants.SR.getCode())
				&&bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			List<OptionObject> yesorno = new ArrayList<OptionObject>();
			yesorno.add(new OptionObject("1", "是"));
			yesorno.add(new OptionObject("2", "否"));
			request.setAttribute("yesorno", yesorno);
			request.setAttribute("trainingEffect",OptionUtil.trainingEffect());
			
			if(StringUtil.isEmpty(bean.getBusinessDelegate()))
				bean.setBusinessDelegate("1");
			if(StringUtil.isEmpty(bean.getAuthorizationBook()))
				bean.setAuthorizationBook("1");
			if(StringUtil.isEmpty(bean.getAppointmentBook()))
				bean.setAppointmentBook("1");
			if(StringUtil.isEmpty(bean.getTraineffect()))
				bean.setTraineffect("1");
			return mapping.findForward("update_yw");
		}
		return null;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = unbindForm.getUnbd();
		int currRole = getCurrRole(request);
		bean.setLastModifyDate(new Date());
		bean.setLastModifyUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		service.update(bean,currRole,request);
		return findList(mapping, unbindForm, request, response);
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = unbindForm.getUnbd();
		service.delete(bean);
		return findList(mapping, unbindForm, request, response);
	}
	
	/**
	 * 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int currRole = getCurrRole(request);
		if(currRole == -1){
			//没有权限
			return mapping.findForward("");
		}
		
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerQueryVO query = unbindForm.getQuery();
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		query.setCurrRole(currRole);
		if(query.getPageType()==null){
			query.setPageType(1);
		}
		int pageType = query.getPageType();
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("unBindlist", request);
		query.setPageType(pageType);
		List<UnBindDealerQueryBean> list = service.findList(query, tools);
		
		
		request.setAttribute("list",list );
		if(pageType==1)
			return mapping.findForward("list_wait_approval");
		else
			return mapping.findForward("list_already_approval");
	}
	
	
	/**
	 * 跳转到流程页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward preApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		initOption(request);
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = service.get(unbindForm.getUnbd().getId(),request);
		unbindForm.setUnbd(bean);
		
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.UNBINDDEALER.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		
		
		return mapping.findForward("approval");
	}
	
	/**
	 * 流程控制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward approval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole = getCurrRole(request);
		if(currRole == -1){
			//没有权限
			return mapping.findForward("");
		}	
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = unbindForm.getUnbd();
		UserSession user = UserSessionUtil.getUserSession(request);
		UnBindDealerQueryVO query = unbindForm.getQuery();
		query.setCurrRole(currRole);

		service.approval(user,query,bean);
		initOption(request);
		//query.setPageType(2);
		return findList(mapping, unbindForm, request, response);
	}
	
	/**
	 * 初始化参数
	 * @param request
	 */
	private void initOption(HttpServletRequest request){
		List<OptionObject> yesorno = new ArrayList<OptionObject>();
		yesorno.add(new OptionObject("1", "是"));
		yesorno.add(new OptionObject("2", "否"));
		request.setAttribute("yesorno", yesorno);
		request.setAttribute("trainingEffect",OptionUtil.trainingEffect());
		request.setAttribute("supervisorSources", OptionUtil.getSupervisorSources());
		request.setAttribute("supervisorAttributes", OptionUtil.getSupervisorAttrubutes());
	}
	
	/**
	 * 申请单提交操作，提交后禁止修改和删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		int id = unbindForm.getUnbd().getId();
		service.submit(id);
		return findList(mapping, form, request, response);
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
	public ActionForward detailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		initOption(request);
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = service.get(unbindForm.getUnbd().getId(),request);
		unbindForm.setUnbd(bean);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.UNBINDDEALER.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		
		return mapping.findForward("detailPage");
	}
	
	/*
	 * 经销商/金融机构拆绑台账
	 * @time 20170621
	*/
	public ActionForward dealerFinancialInstitutionLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerQueryVO query = unbindForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("dealerFinancialInstitutionLedger", request);
		List<UnBindDealerQueryBean> list = service.dealerFinancialInstitutionLedger(query, tools);
		
		request.setAttribute("list",list );
		return mapping.findForward("dfi_ledger");
	}
	
	public ActionForward detailLedgerPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		initOption(request);
		UnBindDealerForm unbindForm = (UnBindDealerForm) form;
		UnBindDealerVO bean = service.get(unbindForm.getUnbd().getId(),request);
		unbindForm.setUnbd(bean);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.UNBINDDEALER.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		
		return mapping.findForward("detailLedgerPage");
	}
}
