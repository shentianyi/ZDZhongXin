package com.zd.csms.supervisorymanagement.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.supervisorymanagement.model.ExtHandoverLogVO;
import com.zd.csms.supervisorymanagement.model.ExtHandoverPlanVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverPlanVO;
import com.zd.csms.supervisorymanagement.service.HandoverPlanService;
import com.zd.csms.supervisorymanagement.web.form.HandoverPlanForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;


public class HandoverPlanAction extends ActionSupport {
	
	HandoverPlanService service=new HandoverPlanService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	public ActionForward addHandoverPlanEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_handoverPlan");
	}
	
	public ActionForward addHandoverPlan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanVO handoverPlan=handoverPlanForm.getHandoverPlan();
		UserSession user = UserSessionUtil.getUserSession(request);
		handoverPlan.setIsEdit(0);
		handoverPlan.setCreateUser(user.getUser().getId());
		handoverPlan.setCreateTime(new Date());
		boolean flag=false;
		try {
			flag = service.add(handoverPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return handoverPlanPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addHandoverPlanEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updHandoverPlanEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) actionform;
		HandoverPlanVO handoverPlan=null;
		try {
			handoverPlan = service.load(handoverPlanForm.getHandoverPlan().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//对象不存在时返回列表页
		if(handoverPlan==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改内容不存在");
			return handoverPlanPageList(mapping,handoverPlanForm,request,response);
		}
		handoverPlanForm.setHandoverPlan(handoverPlan);
		setOptions(request);
		//返回新增页面
		return mapping.findForward("upd_handoverPlan");
	}
	
	public ActionForward updHandoverPlan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanVO handoverPlan=handoverPlanForm.getHandoverPlan();
		UserSession user = UserSessionUtil.getUserSession(request);
		handoverPlan.setLastModifyUser(user.getUser().getId());
		handoverPlan.setLastModifyTime(new Date());
		boolean flag=false;
		try {
			flag = service.update(handoverPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//修改成功返回列表页
			return handoverPlanPageList(mapping,form,request,response);
		}else{
			//修改失败返回修改页面
			return updHandoverPlanEntity(mapping,form,request,response);
		}
	}
	public ActionForward delHandoverPlan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		boolean flag=false;
		try {
			flag = service.delete(handoverPlanForm.getHandoverPlan().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//返回列表页
		return handoverPlanPageList(mapping,handoverPlanForm,request,response);
	}
	public ActionForward handoverPlanDetail(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) actionform;
		HandoverPlanVO handoverPlan=null;
		try {
			handoverPlan = service.load(handoverPlanForm.getHandoverPlan().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//对象不存在时返回列表页
		if(handoverPlan==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改内容不存在");
			return handoverPlanPageList(mapping,handoverPlanForm,request,response);
		}
		handoverPlanForm.setHandoverPlan(handoverPlan);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(handoverPlan.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERPLAN.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		setOptions(request);
		//返回新增页面
		return mapping.findForward("handoverPlan_detail");
	}
	/**
	 * 返回分页列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward handoverPlanPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanQueryVO handoverPlanQuery=handoverPlanForm.getQuery();
		if (handoverPlanQuery.getPageType() == 0) {
			handoverPlanQuery.setPageType(1);
		}
		int pageType=handoverPlanQuery.getPageType();
		int currRole = getCurrRole(request);
		handoverPlanQuery.setCurrentRole(currRole);
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("handoverPlanList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(handoverPlanQuery);
		//按条件查询分页数据
		List<HandoverPlanVO> handoverPlans = service.searchHandoverPlanListByPage(handoverPlanQuery, tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", handoverPlans);
		request.setAttribute("role", getCurrRole(request));
		setOptions(request);
		if(pageType==1){
			return mapping.findForward("list_wait_approval");
		}else if(pageType==2){
			return mapping.findForward("list_already_approval");
		}
		return null;
	}
	/**
	 * 返回列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public List<HandoverPlanVO> HandoverPlanList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanQueryVO handoverPlanQuery=handoverPlanForm.getQuery();
		//按条件查询分页数据
		List<HandoverPlanVO> list = service.searchHandoverPlanList(handoverPlanQuery);
		request.setAttribute("role", getCurrRole(request));
		return list;
	}
	public void setOptions(HttpServletRequest request){
		request.setAttribute("handoverNatures", OptionUtil.handoverNatureOptions());
		request.setAttribute("receiveNatures", OptionUtil.receiveNatureOptions());
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("dealerSupervisors", OptionUtil.getDealerRepository());
		request.setAttribute("supervisors", OptionUtil.getRepository(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
		request.setAttribute("brands", OptionUtil.getBrands());
	}
	/**
	 * 提交轮岗计划，进入审批流程
	 * @param hpic
	 * @return
	 */
	public ActionForward updHandoverPlanEditStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanVO handoverPlan=handoverPlanForm.getHandoverPlan();
		handoverPlan.setApproveStatus(ApprovalContant.APPROVAL_WAIT.getCode());
		handoverPlan.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
		//不可编辑
		handoverPlan.setIsEdit(1);
		boolean flag=false;
		try {
			flag = service.updHandoverPlanEditStatus(handoverPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//返回列表页
		return handoverPlanPageList(mapping,handoverPlanForm,request,response);
	}
	/**
	 * 轮岗计划审批页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		int id = handoverPlanForm.getHandoverPlan().getId();
		HandoverPlanVO handoverPlan=null;
		try {
			handoverPlan = service.load(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		handoverPlanForm.setHandoverPlan(handoverPlan);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(handoverPlan.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERPLAN.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		setOptions(request);
		return mapping.findForward("handoverPlan_approval");
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
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanQueryVO query = handoverPlanForm.getQuery();
		query.setCurrentRole(currRole);
		UserSession user = UserSessionUtil.getUserSession(request);
		boolean flag=false;
		flag=service.approval(query, user);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			return handoverPlanPageList(mapping,handoverPlanForm,request,response);
		}else{
			handoverPlanForm.getHandoverPlan().setId(query.getId());;
			return preApproval(mapping,handoverPlanForm,request,response);
		}
	}
	/**
	 * 审批角色
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
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
	 * 轮岗计划表台账 --需求57
	 * @time 20170517
	 */
	public ActionForward HandoverPlanLedgerList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanQueryVO handoverPlanQuery=handoverPlanForm.getQuery();
		handoverPlanQuery.setPageType(2);
		int currRole = getCurrRole(request);
		handoverPlanQuery.setCurrentRole(currRole);
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("HandoverPlanLedgerList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(handoverPlanQuery);
		//按条件查询分页数据
		List<HandoverPlanVO> handoverPlans = service.searchHandoverPlanListByPage(handoverPlanQuery, tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", handoverPlans);
		request.setAttribute("role", getCurrRole(request));
		setOptions(request);
		request.setAttribute("allSupervisors", OptionUtil.getAllSupervisors());
		return mapping.findForward("HandoverPlanLedgerList");
	}
	/**
	 * 导出轮岗计划表台账 --需求57
	 * @time 20170517
	 */
	public ActionForward ExtHandoverPlanLedgerList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) form;
		HandoverPlanQueryVO handoverPlanQuery=handoverPlanForm.getQuery();
		int currRole = getCurrRole(request);
		handoverPlanQuery.setCurrentRole(currRole);
		handoverPlanQuery.setPageType(2);
		
		String[] titles = {"序号","交付人姓名","交付人经销商名称","交付人交付性质","接收人姓名","接收人经销商名称",
					"接收人接收性质","接收人调入时间","创建人","创建时间","最后修改人","最后修改时间","审批状态","下一审批人"};
		String filename = "轮岗计划表台账";
		List<ExtHandoverPlanVO> list = service.ExtHandoverPlanLedgerList(handoverPlanQuery);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
		for (int i = 0; list != null && i < list.size(); i++){
			ExtHandoverPlanVO vo = list.get(i);
			tool.setCellValue(i+1, 0, i+1);
			
			tool.setCellValue(i+1, 1, vo.getDelivererNTT());
			
			tool.setCellValue(i+1, 2, vo.getDelivererDealer());
			
			if (vo.getHandoverNature() == 1) {
				tool.setCellValue(i+1, 3, "辞职");
			}else if (vo.getHandoverNature() == 2) {
				tool.setCellValue(i+1, 3, "辞退");
			}else if (vo.getHandoverNature() == 3) {
				tool.setCellValue(i+1, 3, "正常轮岗");
			}else if (vo.getHandoverNature() == 4) {
				tool.setCellValue(i+1, 3, "投诉轮岗");
			}
			
			tool.setCellValue(i+1, 4, vo.getReceiverNTT());
			
			tool.setCellValue(i+1, 5, vo.getReceiverDealer());
			
			if (vo.getReceiveNature() == 1) {
				tool.setCellValue(i+1, 6, "轮岗");
			}else if (vo.getReceiveNature() == 2) {
				tool.setCellValue(i+1, 6, "新入职");
			}else if (vo.getReceiveNature() == 3) {
				tool.setCellValue(i+1, 6, "二次上岗");
			}
			
			if (vo.getMissionDate() !=null) {
				String missionDate = format.format(vo.getMissionDate());
				tool.setCellValue(i+1, 7, missionDate);
			}else{
				tool.setCellValue(i+1, 7, "");
			}
			
			tool.setCellValue(i+1, 8, vo.getCreateUserNTT());
			
			if (vo.getCreateTime() != null) {
				String createTime = format.format(vo.getCreateTime());
				tool.setCellValue(i+1, 9, createTime);
			}else{
				tool.setCellValue(i+1, 9, "");
			}
			
			tool.setCellValue(i+1, 10, vo.getLastModifyUserNTT());
			
			if (vo.getLastModifyTime() !=null) {
				String lastModifyTime = format.format(vo.getLastModifyTime());
				tool.setCellValue(i+1, 11, lastModifyTime);
			}else{
				tool.setCellValue(i+1, 11, "");
			}
			
			if (vo.getApproveStatus() == 1) {
				tool.setCellValue(i+1, 12, "审批不通过");
			}else if(vo.getApproveStatus() == 2) {
				tool.setCellValue(i+1, 12, "审批通过"); 
			}
			
			tool.setCellValue(i+1, 13, vo.getNextApprovalNTT());
			tool.allAutoColumnWidth(i);
		}
		tool.writeStream(response, filename);
		return null;
	}
	/* 
	 * 轮岗计划台账详情
	*/
	public ActionForward HandoverPlanLedgerListDetail(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HandoverPlanForm handoverPlanForm =(HandoverPlanForm) actionform;
		HandoverPlanVO handoverPlan=null;
		try {
			handoverPlan = service.load(handoverPlanForm.getHandoverPlan().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//对象不存在时返回列表页
		if(handoverPlan==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改内容不存在");
			return handoverPlanPageList(mapping,handoverPlanForm,request,response);
		}
		handoverPlanForm.setHandoverPlan(handoverPlan);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(handoverPlan.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERPLAN.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		setOptions(request);
		//返回新增页面
		return mapping.findForward("handoverPlan_ledger_detail");
	}
	
}
