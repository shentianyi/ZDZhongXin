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
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.model.BusinessTransferQueryVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.querybean.BusinessTransferQueryBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.web.form.BusinessTransferForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class BusinessTransferAction extends ActionSupport{
	BusinessTransferService service = new BusinessTransferService();
	
	private BusinessTransferForm getForm(ActionForm form){
		return (BusinessTransferForm)form;
	}

	/**
	 * 流程角色:市场部专员->招聘专员->业务部专员
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.SR.getCode()};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	/**
	 * 跳转新增页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("dealers",OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_OUT.getCode()));
		initOption(request);
		return mapping.findForward("add");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int userId = UserSessionUtil.getUserSession(request).getUser().getId();
		BusinessTransferVO bean = getForm(form).getBusiness();
		bean.setCreateUserId(userId);
		bean.setCreateDate(new Date());
		service.add(bean);
		return findList(mapping, form, request, response);
	}
	
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole =  getCurrRole(request);
		if(currRole == -1){
			//权限不足
			return mapping.findForward("");
		}
		int id=getForm(form).getBusiness().getId();
		BusinessTransferVO btvo = service.get(id);
		getForm(form).setBusiness(btvo);
		
		
		initOption(request);
		if((RoleConstants.MARKET_COMMISSIONER.getCode()==currRole
				||currRole == RoleConstants.SR.getCode())
				&&(btvo.getApprovalState()==ApprovalContant.APPROVAL_NOT_SUBMIT.getCode()
				||btvo.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode())){
			request.setAttribute("dealers",OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_OUT.getCode()));
			return mapping.findForward("update");
		}else if ((RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode() ==currRole
				||currRole == RoleConstants.SR.getCode())
				&&btvo.getNextApproval()==RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()){
			if(StringUtil.isEmpty(btvo.getGqpx()))
				btvo.setGqpx("1");
			if(btvo.getSupervisorSource()==0)
				btvo.setSupervisorSource(1);
			List<OptionObject> yesorno = new ArrayList<OptionObject>();
			yesorno.add(new OptionObject("1", "是"));
			yesorno.add(new OptionObject("2", "否"));
			
			request.setAttribute("yesorno", yesorno);
			request.setAttribute("supervisor", OptionUtil.getRepository(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
			return mapping.findForward("update_zp");
		}else if((RoleConstants.BUSINESS_COMMISSIONER.getCode() ==currRole
				||currRole == RoleConstants.SR.getCode())
				&&btvo.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			if(StringUtil.isEmpty(btvo.getBusinessTraining()))
				btvo.setBusinessTraining("1");
			if(StringUtil.isEmpty(btvo.getWarrantProduction()))
				btvo.setWarrantProduction("1");
			if(StringUtil.isEmpty(btvo.getMandateLetterProduction()))
				btvo.setMandateLetterProduction("1");
			if(StringUtil.isEmpty(btvo.getTrainingEffect()))
				btvo.setTrainingEffect("1");
			
			List<OptionObject> yesorno = new ArrayList<OptionObject>();
			yesorno.add(new OptionObject("1", "是"));
			yesorno.add(new OptionObject("2", "否"));
			
			request.setAttribute("yesorno", yesorno);
			request.setAttribute("trainingEffect",OptionUtil.trainingEffect());
			return mapping.findForward("update_yw");
		}
			
		return null;
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole =  getCurrRole(request);
		if(currRole == -1){
			//权限不足
			return mapping.findForward("");
		}
		BusinessTransferForm btForm = getForm(form);
		
		service.update(btForm.getBusiness(),UserSessionUtil.getUserSession(request),currRole);
		return findList(mapping, form, request, response);
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		service.delete(getForm(form).getBusiness().getId());
		return findList(mapping, form, request, response);
	}
	
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BusinessTransferQueryVO query = getForm(form).getQuery();
		int currRole = getCurrRole(request);
		query.setCurrRole(currRole);
		if(query.getPageType()==null){
			query.setPageType(1);
		}
		int pageType = query.getPageType();
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("btlist", request);
		//tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<BusinessTransferQueryBean> list = service.findList(query, tools);
		request.setAttribute("list", list);
		
		if(pageType==1)
			return mapping.findForward("list_wait_approval");
		else
			return mapping.findForward("list_already_approval");

	}
	
	/**
	 * 流程控制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward approval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole = getCurrRole(request);
		if(currRole == -1){
			//权限不足
			mapping.findForward("");
		}
		UserSession user = UserSessionUtil.getUserSession(request);
		BusinessTransferVO bean = getForm(form).getBusiness();
		
		service.approval(user,bean,currRole);
		//getForm(form).getQuery().setPageType(2);
		return findList(mapping, form, request, response);
	}
	
	/**
	 * 发起者提交表单，进入流程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		BusinessTransferVO bean = getForm(form).getBusiness();
		service.submit(bean);
		return findList(mapping, form, request, response);
	}
	
	private void initOption(HttpServletRequest request){
		request.setAttribute("supervisorSources", OptionUtil.getSupervisorSources());
		request.setAttribute("supervisorAttributes", OptionUtil.getSupervisorAttrubutes());
		List<OptionObject> yesorno = new ArrayList<OptionObject>();
		yesorno.add(new OptionObject("1", "是"));
		yesorno.add(new OptionObject("2", "否"));
		request.setAttribute("yesorno", yesorno);
		request.setAttribute("trainingEffect",OptionUtil.trainingEffect());
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
		BusinessTransferVO bean = getForm(form).getBusiness();
		bean = service.get(bean.getId());
		getForm(form).setBusiness(bean);
		initOption(request);
		return mapping.findForward("detailPage");
	}
}
