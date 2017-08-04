package com.zd.csms.supervisorymanagement.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.supervisorymanagement.model.UsernameManageQueryVO;
import com.zd.csms.supervisorymanagement.model.UsernameManageVO;
import com.zd.csms.supervisorymanagement.service.UsernameManageService;
import com.zd.csms.supervisorymanagement.web.form.UsernameManageForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class UsernameManageAction extends ActionSupport {

	
	public ActionForward usernameManageListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return usernameManageList(mapping, form,request, response);
	}
	
	
	public ActionForward usernameManageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		UsernameManageForm umform = (UsernameManageForm)form;
		UsernameManageService service = new UsernameManageService();
		
		UsernameManageQueryVO umQuery = umform.getUsernameManageQuery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("usernameManageList",request);
		thumbPageTools.saveQueryVO(umQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<UsernameManageVO> list = service.searchUsernameManageListByPage(umQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("username_manage_list");
	}
	
	public ActionForward addUsernameManageEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_username_manage");
	}
	
	public ActionForward addUsernameManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsernameManageForm umform = (UsernameManageForm)form;
		UsernameManageService service = new UsernameManageService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addUsernameManage(umform.getUsernameManage());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return usernameManageList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_username_manage");
	}
	
	public ActionForward updUsernameManageEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsernameManageForm umform = (UsernameManageForm)form;
		UsernameManageService service = new UsernameManageService();
		
		//根据id获取修改对象
		UsernameManageVO vo = service.loadUsernameManageById(umform.getUsernameManage().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return usernameManageList(mapping, form, request, response);
		}
		
		umform.setUsernameManage(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_username_manage");
	}
	
	public ActionForward updUsernameManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsernameManageForm umform = (UsernameManageForm)form;
		UsernameManageService service = new UsernameManageService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updUsernameManage(umform.getUsernameManage());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return usernameManageList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_username_manage");
	}
	
	public ActionForward delUsernameManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsernameManageForm umform = (UsernameManageForm)form;
		UsernameManageService service = new UsernameManageService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteUsernameManage(umform.getUsernameManage().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return usernameManageList(mapping, form, request, response);
	}
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		
	}
}
