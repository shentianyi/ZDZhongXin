package com.zd.csms.message.web.action;

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
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.contant.NoticeTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.NoticeContentQueryVO;
import com.zd.csms.message.model.NoticeContentVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.message.service.NoticeContentService;
import com.zd.csms.message.web.form.NoticeContentForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class NoticeContentAction extends ActionSupport{
	private NoticeContentService service = new NoticeContentService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();

	//参与的角色
	private static int[] approvalRole = new int []{

			//监管员管理部
			RoleConstants.SUPERVISORYMANAGEMENT_DATA.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			//RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),

			//市场部
			RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.MARKET_DATA.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(),
			RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),

			//业务部
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_DATA.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
		
			//运营部部长
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			
			//视频
		//	RoleConstants.VIDEO_COMMISSIONER.getCode(),
			RoleConstants.VIDEO_DATA.getCode(),
			RoleConstants.VIDEO_AMALDAR.getCode(),
			
			//风控
			RoleConstants.WINDCONRTOL_DATA.getCode(),
			RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
			RoleConstants.WINDCONRTOL_EXTERNAL.getCode(),
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
			
			//风险管理部部长
			RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),
			
			//总监
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.ADMINISTRATOR.getCode(),
			RoleConstants.SR.getCode()
	};
	
	/**
	 * 获取当前用户的权限,权限不足获取-1
	 * 
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request) {
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}

		
	public ActionForward noticeContentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NoticeContentForm nform = (NoticeContentForm)form;
		NoticeContentQueryVO nQuery = nform.getQuery();
		nQuery.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		nQuery.setCurrRole(getCurrRole(request));
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("NoticeContentList",request);
		if(nQuery.getPageType()==0)
			nQuery.setPageType(1);
		//按条件查询分页数据
		List<NoticeContentVO> list = service.searchNoticeContentListByPage(nQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		if (nQuery.getPageType() == 1)
			return mapping.findForward("list_wait_approval");
		else
			return mapping.findForward("list_already_approval");
		
	}
	
	public ActionForward addNoticeContentEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NoticeContentForm nform = (NoticeContentForm)form;
		request.setAttribute("depts", OptionUtil.clientTypeOptions());
		List<OptionObject> types = new ArrayList<OptionObject>();
		types.add(new OptionObject("1", "全部"));
		types.add(new OptionObject("2", "部门"));
		request.setAttribute("types",types);
		nform.getNoticeContent().setType(2);
		//返回新增页面
		return mapping.findForward("add_NoticeContent");
	}
	
	public ActionForward addNoticeContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeContentForm nform = (NoticeContentForm)form;
		NoticeContentQueryVO query = nform.getQuery();
		int userId = UserSessionUtil.getUserSession(request).getUser().getId();
		NoticeContentVO nc = nform.getNoticeContent();
		nc.setCreateDate(new Date());
		nc.setCreateUserId(userId);
		nc.setContentType(query.getContentType());
		//执行新增操作并获取操作结果
		boolean flag = service.add(nc,request);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return noticeContentList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_NoticeContent");
	}
	
	public ActionForward updNoticeContentEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("depts", OptionUtil.clientTypeOptions());
		List<OptionObject> types = new ArrayList<OptionObject>();
		types.add(new OptionObject("1", "全部"));
		types.add(new OptionObject("2", "部门"));
		request.setAttribute("types",types);
		NoticeContentForm nform = (NoticeContentForm)form;
		nform.setNoticeContent(service.get(nform.getNoticeContent().getId()));
		String deps= service.findDeptByContentId(nform.getNoticeContent().getId());
		if(nform.getNoticeContent().getType()==2&&deps!=null){
			nform.setDepartment(deps.split(","));
		}
		request.setAttribute("content", nform.getNoticeContent().getContent());
		//返回修改页面
		return mapping.findForward("upd_noticeContent");
	}
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoticeContentForm nform = (NoticeContentForm)form;
		nform.setNoticeContent(service.get(nform.getNoticeContent().getId()));
		request.setAttribute("content", nform.getNoticeContent().getContent());
		//返回修改页面
		return mapping.findForward("detail");
	}
	
	public ActionForward updNoticeContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoticeContentForm nform = (NoticeContentForm)form;
		NoticeContentService service = new NoticeContentService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.update(nform.getNoticeContent(), request);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return noticeContentList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_NoticeContent");
	}
	
	public ActionForward delNoticeContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoticeContentForm nform = (NoticeContentForm)form;

		//执行删除操作并获取操作结果
		boolean flag = service.delete(nform.getNoticeContent().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return noticeContentList(mapping, form, request, response);
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
		
		NoticeContentForm ncForm = (NoticeContentForm) form;
		NoticeContentVO bean = service.get(ncForm.getNoticeContent().getId());
		ncForm.setNoticeContent(bean);
		NoticeContentQueryVO query = ncForm.getQuery();

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		request.setAttribute("content", ncForm.getNoticeContent().getContent());
		if(query.getContentType()==1){
			approvalQuery.setObjectType(ApprovalTypeContant.GONGGAO.getCode());
		}else if(query.getContentType()==2){
			approvalQuery.setObjectType(ApprovalTypeContant.ZHIDU.getCode());
		}
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
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 没有权限
			return mapping.findForward("");
		}
		NoticeContentForm ncForm = (NoticeContentForm) form;
		NoticeContentQueryVO query = ncForm.getQuery();
		query.setCurrRole(getCurrRole(request));
		query.setCurrUserId(user.getId());
		NoticeContentVO nc = ncForm.getNoticeContent();
		
		service.approval(UserSessionUtil.getUserSession(request),query,nc);
		nc = service.get(nc.getId());
		if(nc.getContentType() == NoticeTypeContant.CONTENT.getCode()){//手写公告
			MessageService ms = new MessageService();
			MessageVO msg = ms.loadMsgByUserAndType(user.getId(), MessageTypeContant.APPROVALNOTICE.getCode());
			//审批状态 1：审批成功 2：审批失败 3：待审批 4:未提交 
			int num = service.selectMessageCountByUserAndType(currRole,NoticeTypeContant.CONTENT.getCode(),3);
			msg.setName(num+"");
			ms.updMessage(msg);
		}
		//query.setPageType(2);
		return noticeContentList(mapping, form, request, response);
	}

	
	/**
	 * 申请单提交操作，提交后禁止修改和删除
	 * 
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
		NoticeContentForm ncForm = (NoticeContentForm) form;
		int id = ncForm.getNoticeContent().getId();
		int currRole = getCurrRole(request);
		service.submit(id,currRole);
		return noticeContentList(mapping, form, request, response);
	}
}
