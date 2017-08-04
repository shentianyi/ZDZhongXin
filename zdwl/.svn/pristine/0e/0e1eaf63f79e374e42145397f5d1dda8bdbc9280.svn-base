package com.zd.csms.supervisorymanagement.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.constants.Constants;
import com.zd.csms.supervisorymanagement.model.SystemManageQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemManageVO;
import com.zd.csms.supervisorymanagement.service.SystemManageService;
import com.zd.csms.supervisorymanagement.web.form.SystemManageForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SystemManageAction extends ActionSupport {

	public ActionForward systemManageListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return systemManageList(mapping, form,request, response);
	}
	
	
	public ActionForward systemManageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SystemManageForm smform = (SystemManageForm)form;
		SystemManageService service = new SystemManageService();
		
		SystemManageQueryVO smQuery = smform.getSystemManagequery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("systemManageList",request);
		thumbPageTools.saveQueryVO(smQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<SystemManageVO> list = service.searchSystemManageListByPage(smQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("system_manage_list");
	}
	
	public ActionForward addSystemManageEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("add_system_manage");
	}
	
	public ActionForward addSystemManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemManageForm smform = (SystemManageForm)form;
		SystemManageService service = new SystemManageService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addSystemManage(smform.getSystemManage());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return systemManageList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_system_manage");
	}
	
	public ActionForward updSystemManageEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemManageForm smform = (SystemManageForm)form;
		SystemManageService service = new SystemManageService();
		
		//根据id获取修改对象
		SystemManageVO vo = service.loadSystemManageById(smform.getSystemManage().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return systemManageList(mapping, form, request, response);
		}
		
		smform.setSystemManage(vo);
		
		//返回修改页面
		return mapping.findForward("upd_system_manage");
	}
	
	public ActionForward updSystemManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemManageForm smform = (SystemManageForm)form;
		SystemManageService service = new SystemManageService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updSystemManage(smform.getSystemManage());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return systemManageList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_system_manage");
	}
	
	public ActionForward delSystemManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemManageForm smform = (SystemManageForm)form;
		SystemManageService service = new SystemManageService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSystemManage(smform.getSystemManage().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return systemManageList(mapping, form, request, response);
	}
}
