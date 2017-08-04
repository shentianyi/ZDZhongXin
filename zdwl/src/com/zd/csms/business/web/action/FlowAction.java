package com.zd.csms.business.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.business.model.FlowQueryVO;
import com.zd.csms.business.model.FlowVO;
import com.zd.csms.business.service.FlowService;
import com.zd.csms.business.web.form.FlowForm;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class FlowAction extends ActionSupport {

	FlowService service = new FlowService();
	public ActionForward flowListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return flowList(mapping, form,request, response);
	}
	
	
	public ActionForward flowList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		FlowForm fform = (FlowForm)form;
		FlowService service = new FlowService();
		
		FlowQueryVO fQuery = fform.getFlowquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
			request.setAttribute("isyw", 2);
		}else{
			request.setAttribute("isyw", 1);
		}
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("flowList",request);
		thumbPageTools.saveQueryVO(fQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<FlowVO> list = service.searchFlowListByPage(fQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//返回列表页面
		return mapping.findForward("flow_list");
	}
	
	public ActionForward addFlowEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("add_flow");
	}
	
	public ActionForward addFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FlowForm fform = (FlowForm)form;
		FlowService service = new FlowService();
		
		boolean flag = false;
		
		FlowVO fvo = fform.getFlow();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		fvo.setUploadid(user.getId());
		fvo.setCreatetime(new Date());
		flag = service.addFlow(fvo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return flowList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_flow");
	}
	public ActionForward updFlowEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		FlowForm fform = (FlowForm)form;
		fform.setFlow(service.loadFlowById(fform.getFlow().getId()));
		request.setAttribute("content", fform.getFlow().getContent());
		//返回修改页面
		return mapping.findForward("upd_flow");
	}
	

	public ActionForward updFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FlowForm fform = (FlowForm)form;
		
		//执行修改操作并获取操作结果
		boolean flag = service.updFlow(fform.getFlow());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return flowList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_flow");
	}
	
	public ActionForward delFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FlowForm fform = (FlowForm)form;
		FlowService service = new FlowService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteFlow(fform.getFlow().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return flowList(mapping, form, request, response);
	}
	
	public ActionForward detailFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FlowForm fform = (FlowForm)form;
		FlowService service = new FlowService();
		
		//根据id获取修改对象
		FlowVO vo = service.loadFlowById(fform.getFlow().getId());
		
		fform.setFlow(vo);
		request.setAttribute("content", vo.getContent());
		//返回修改页面
		return mapping.findForward("detail_flow");
	}
	
	public ActionForward superviseflowList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		FlowForm fform = (FlowForm)form;
		FlowService service = new FlowService();
		
		FlowQueryVO fQuery = fform.getFlowquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseflowList",request);
		thumbPageTools.saveQueryVO(fQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<FlowVO> list = service.searchFlowListByPage(fQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//返回列表页面
		return mapping.findForward("supervise_flow_list");
	}
}
