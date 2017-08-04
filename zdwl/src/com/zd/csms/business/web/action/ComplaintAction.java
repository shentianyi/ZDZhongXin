package com.zd.csms.business.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.ComplaintInfoQueryBean;
import com.zd.csms.business.model.ComplaintInfoQueryVO;
import com.zd.csms.business.model.ComplaintInfoVO;
import com.zd.csms.business.service.ComplaintService;
import com.zd.csms.business.web.excel.ComplaintRowMapper;
import com.zd.csms.business.web.form.ComplaintForm;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.constants.StatusConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ComplaintAction extends ActionSupport {
	private ComplaintService service = new ComplaintService();
	private UserService uservice = new UserService();
	private BankService bankService = new BankService();

	/**
	 * 角色:根据不同的部门分开判断 (各部门经理   --- 9,13,16,20,23,25  /  各部门部长   --- 26,27,28,29)
	 */
	private static int[] approvalRole = new int[] {
			
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
			RoleConstants.VIDEO_AMALDAR.getCode(),
			RoleConstants.FINANCE_AMALDAR.getCode(),
			
			RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),
			RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
	};
	

	private UserVO getUserVO(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return user.getUser();
	}
	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request) {
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
	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserVO user = getUserVO(request);
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoVO bean = complaintForm.getComplaint();
		initOption(request);
		bean.setProcessingDepartment(ClientTypeConstants.BUSINESS.getCode());
		bean.setCreateUserName(user.getUserName());
		bean.setTreatmentProcessName(user.getUserName());
		bean.setCreateUserDept(ClientTypeConstants.getName(user.getClient_type()));
		bean.setCreateDate(new Date());
		bean.setTreatmentProcessTime(new Date());
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		request.setAttribute("reps", OptionUtil.getRepository(RepositoryStatus.ALREADYPOST.getCode()));
		request.setAttribute("telephoneTypes", OptionUtil.getTelephoneTypes());
		return mapping.findForward("add");
	}
	
	/**
	 * 新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 权限不足
			return mapping.findForward("");
		}
		ComplaintForm complaintForm = (ComplaintForm) form;
		Integer[] phoneTypes = complaintForm.getPhoneTypes();
		UserVO user = getUserVO(request);
		StringBuffer sb = new StringBuffer();
		if(phoneTypes != null && phoneTypes.length > 0){
			for(int i = 0; i< phoneTypes.length;i++){
				sb.append(phoneTypes[i]+",");
			}
			complaintForm.getComplaint().setPhoneType(sb.substring(0, sb.length() - 1).toString());
		}
		ComplaintInfoVO bean = complaintForm.getComplaint();
		bean.setStatus(StatusConstants.UNCOMMIT.getCode());//设置状态(未提交)
		bean.setCreateUserId(user.getId());
		bean.setTreatmentProcessId(user.getId());
		bean.setProcessingName(user.getUserName());
		service.add(bean);
		return findList(mapping, complaintForm, request, response);
		
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
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = getUserVO(request);
		int currRole = getCurrRole(request);
		
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoQueryVO query = complaintForm.getQuery();
		//查询条件
		query.setCurrRole(currRole);
		query.setCreateUserId(user.getId());
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//tools.saveQueryVO(query);
		List<ComplaintInfoQueryBean> list = service.findList(query, tools);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		request.setAttribute("complaintObjs", OptionUtil.complaintObjs());
		request.setAttribute("telephoneTypes", OptionUtil.getTelephoneTypes());
		return mapping.findForward("list_wait_approval");
		
	}
	
	/**
	 * 跳转更新页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = getUserVO(request);
		initOption(request);
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoVO bean = service.getComplaintInfoVO(complaintForm.getComplaint().getId());
		Integer processingId = bean.getProcessingId();
		if (null != processingId) {
			UserVO userVO = uservice.get(processingId);
			if(null != userVO){
				request.setAttribute("userName", userVO.getUserName());
			}
		}
		initOption(request);
		if(bean.getFinanceId()>0){
			BankVO bank = bankService.get(bean.getFinanceId());
			request.setAttribute("bank", bank);
		}
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		request.setAttribute("reps", OptionUtil.getRepository(RepositoryStatus.ALREADYPOST.getCode()));
		request.setAttribute("telephoneTypes", OptionUtil.getTelephoneTypes());
		request.setAttribute("rosterId", bean.getRosterId());
		request.setAttribute("storeId", bean.getStoreId());
		request.setAttribute("processingIdinit", bean.getProcessingId());
		
		complaintForm.setComplaint(bean);
		//当前登录用户为-记录-处理人  且状态为已发送
		if (null != user && bean.getStatus() != null) {
			if (null != bean.getProcessingId() && user.getId() == bean.getProcessingId() && 
					bean.getStatus().equals(StatusConstants.ALREADYSEND.getCode())) {//已发送
				bean.setSolutionOperatorName(user.getUserName());
				bean.setSolutionProcessTime(new Date());
				return mapping.findForward("updateComplaintSend");
			}else if(bean.getStatus().equals(StatusConstants.CORRECTION.getCode())){//已修正
				bean.setTrackOperatorName(user.getUserName());
				bean.setTrackProcessTime(new Date());
				return mapping.findForward("updateComplaintCorrection");
			}else{
				return mapping.findForward("updateComplaint");
			}
			
		}
		return null;
		
	}
	
	
	/**
	 * 更新操做
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = getUserVO(request);
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoVO bean = complaintForm.getComplaint();
		ComplaintInfoVO complaintInfoVO = service.get(bean.getId());
		
		if(null != user && null != complaintInfoVO && null != complaintInfoVO.getStatus()){
			//当前登录用户为-记录-处理人  且状态为-已发送
			if (null != complaintInfoVO.getProcessingId() && user.getId() == complaintInfoVO.getProcessingId() && 
					complaintInfoVO.getStatus().equals(StatusConstants.ALREADYSEND.getCode())) {//处理人填写解决方案   发起人已提交 处理人未提交
				complaintInfoVO.setSolution(bean.getSolution());//解决方案
				complaintInfoVO.setSolutionOperatorId(user.getId());//解决方案经办人Id
				complaintInfoVO.setSolutionOperatorName(bean.getSolutionOperatorName());//解决方案经办人
				complaintInfoVO.setSolutionProcessTime(bean.getSolutionProcessTime());//解决方案填写时间
				service.update(complaintInfoVO);
			//当前登录用户为-记录-发起人  且状态为已修正
			}else if(user.getId() == complaintInfoVO.getCreateUserId() &&
					complaintInfoVO.getStatus().equals(StatusConstants.CORRECTION.getCode())){//处理人已提交    发起人未提交   发起人填写跟踪情况
				complaintInfoVO.setTrackCondition(bean.getTrackCondition());//跟踪情况
				complaintInfoVO.setTrackOperatorId(user.getId());//跟踪情况经办人Id
				complaintInfoVO.setTrackOperatorName(bean.getSolutionOperatorName());//跟踪情况经办人姓名
				complaintInfoVO.setTrackProcessTime(bean.getSolutionProcessTime());//跟踪情况填写时间
				service.update(complaintInfoVO);
			//当前登录用户为-记录-发起人  且状态为未提交
			}else if(user.getId() == complaintInfoVO.getCreateUserId() &&
					complaintInfoVO.getStatus().equals(StatusConstants.UNCOMMIT.getCode())){//发起人未提交
				StringBuffer sb = new StringBuffer();
				Integer[] phoneTypes = complaintForm.getPhoneTypes();
				if(phoneTypes != null && phoneTypes.length > 0){
					for(int i = 0; i< phoneTypes.length;i++){
						sb.append(phoneTypes[i]+",");
					}
					bean.setPhoneType(sb.substring(0, sb.length() - 1).toString());
				}
				bean.setCreateUserId(complaintInfoVO.getCreateUserId());
				bean.setTreatmentProcessId(complaintInfoVO.getTreatmentProcessId());//处理意见经办人Id
				bean.setProcessingName(complaintInfoVO.getProcessingName());//处理人员姓名
				service.update(bean);
			}
		}
		
		return findList(mapping, complaintForm, request, response);
	}

	/**
	 * 删除操做
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoVO bean = complaintForm.getComplaint();
		service.delete(bean);
		return findList(mapping, complaintForm, request, response);
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
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoVO bean = service.getComplaintInfoVO(complaintForm.getComplaint().getId());
		Integer processingId = bean.getProcessingId();
		if (null != processingId) {
			UserVO userVO = uservice.get(processingId);
			if(null != userVO){
				request.setAttribute("userName", userVO.getUserName());
			}
		}
		initOption(request);
		if(bean.getFinanceId()>0){
			BankVO bank = bankService.get(bean.getFinanceId());
			request.setAttribute("bank", bank);
		}
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		request.setAttribute("reps", OptionUtil.getRepository(RepositoryStatus.ALREADYPOST.getCode()));
		request.setAttribute("telephoneTypes", OptionUtil.getTelephoneTypes());
		request.setAttribute("rosterId", bean.getRosterId());
		request.setAttribute("storeId", bean.getStoreId());
		request.setAttribute("processingIdinit", bean.getProcessingId());
		
		
		complaintForm.setComplaint(bean);
	
		return mapping.findForward("detailPages");
	}
	
	
	/**
	 * 初始化参数
	 * 
	 * @param request
	 */
	private void initOption(HttpServletRequest request) {

		request.setAttribute("complaintSorts", OptionUtil.complaintSorts());
		request.setAttribute("processingDepartment", OptionUtil.processingDepartment());
		request.setAttribute("complaintObjs", OptionUtil.complaintObjs());
		
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
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoVO complaintInfoVO = service.get(complaintForm.getComplaint().getId());
		String status = complaintInfoVO.getStatus();
		if (StringUtil.isNotEmpty(status) && status.equals(StatusConstants.UNCOMMIT.getCode())) {
			complaintInfoVO.setStatus(StatusConstants.ALREADYSEND.getCode());//已发送
			service.update(complaintInfoVO);
			//向处理人发送消息提醒
			MessageUtil.addMsg(complaintInfoVO.getProcessingId(), "投诉信息", "/complaint.do?method=findList", 1,
					MessageTypeContant.COMPLAINTINFO.getCode(),complaintInfoVO.getCreateUserId());
			int processingDepartment = complaintInfoVO.getProcessingDepartment();
			if (processingDepartment > 0 && (processingDepartment == ClientTypeConstants.BUSINESS.getCode()
					|| processingDepartment == ClientTypeConstants.SUPERVISORYMANAGEMENT.getCode())) {
				//向运营管理部部长发送消息提醒
				MessageUtil.sendMsg(new String[]{RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+""}, 
						"投诉信息", "/complaint.do?method=findList", 1, MessageTypeContant.COMPLAINTINFO.getCode(), complaintInfoVO.getCreateUserId());
			
			}else if (processingDepartment > 0 && processingDepartment == ClientTypeConstants.MARKET.getCode()) {
				//向市场管理部部长发送消息提醒
				MessageUtil.sendMsg(new String[]{RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+""}, 
						"投诉信息", "/complaint.do?method=findList", 1, MessageTypeContant.COMPLAINTINFO.getCode(), complaintInfoVO.getCreateUserId());
			
			}else if (processingDepartment > 0 && processingDepartment == ClientTypeConstants.WINDCONRTOL.getCode()) {
				//向风控部部长发送消息提醒
				MessageUtil.sendMsg(new String[]{RoleConstants.WINDCONRTOL_AMALDAR.getCode()+""}, 
						"投诉信息", "/complaint.do?method=findList", 1, MessageTypeContant.COMPLAINTINFO.getCode(), complaintInfoVO.getCreateUserId());
	
			}else if (processingDepartment > 0 && processingDepartment == ClientTypeConstants.VIDEO.getCode()) {
			//向视频部部长发送消息提醒
				MessageUtil.sendMsg(new String[]{RoleConstants.VIDEO_AMALDAR.getCode()+""}, 
						"投诉信息", "/complaint.do?method=findList", 1, MessageTypeContant.COMPLAINTINFO.getCode(), complaintInfoVO.getCreateUserId());
		
			}
		}else if(StringUtil.isNotEmpty(status) && status.equals(StatusConstants.ALREADYSEND.getCode())) {
			complaintInfoVO.setStatus(StatusConstants.CORRECTION.getCode());//已修正
			service.update(complaintInfoVO);
			//向发起人发送消息
			MessageUtil.addMsg(complaintInfoVO.getCreateUserId(), "投诉信息", "/complaint.do?method=findList", 1,
					MessageTypeContant.COMPLAINTINFO.getCode(),complaintInfoVO.getCreateUserId());
		}else if(StringUtil.isNotEmpty(status) && status.equals(StatusConstants.CORRECTION.getCode())) {
			complaintInfoVO.setStatus(StatusConstants.SUCCESS.getCode());//已完成
			service.update(complaintInfoVO);
		}
		
		return findList(mapping, form, request, response);
	}
	
	/**
	 * 投诉记录信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserVO user = getUserVO(request);
		int currRole = getCurrRole(request);
		
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoQueryVO query = complaintForm.getQuery();
		//查询条件
		query.setCurrRole(currRole);
		query.setCreateUserId(user.getId());
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		
		//按条件查询分页数据
		IExportFile export = new ExportFileExcelImpl();
		try {
			List<ComplaintInfoQueryBean> list = service.findList(query, tools);
			export.export(list, new ComplaintRowMapper(), export.createDefaultFileName("投诉记录信息"),"投诉记录信息", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回列表页面
		return null;
	}
	
}
