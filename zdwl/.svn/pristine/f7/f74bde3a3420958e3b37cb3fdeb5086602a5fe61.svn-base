package com.zd.csms.supervisorymanagement.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.constants.Constants;
import com.zd.csms.supervisorymanagement.model.SystemSuperviseQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemSuperviseVO;
import com.zd.csms.supervisorymanagement.service.SystemSuperviseService;
import com.zd.csms.supervisorymanagement.web.form.SystemSuperviseForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SystemSuperviseAction extends ActionSupport {

	public ActionForward systemSuperviseListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return systemSuperviseList(mapping, form,request, response);
	}
	
	
	public ActionForward systemSuperviseList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SystemSuperviseForm ssform = (SystemSuperviseForm)form;
		SystemSuperviseService service = new SystemSuperviseService();
		
		SystemSuperviseQueryVO ssQuery = ssform.getSystemSupervisequery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("systemSuperviseList",request);
		thumbPageTools.saveQueryVO(ssQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<SystemSuperviseVO> list = service.searchSystemSuperviseListByPage(ssQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("system_supervise_list");
	}
	
	public ActionForward addSystemSuperviseEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("add_system_supervise");
	}
	
	public ActionForward addSystemSupervise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemSuperviseForm ssform = (SystemSuperviseForm)form;
		SystemSuperviseService service = new SystemSuperviseService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addSystemSupervise(ssform.getSystemSupervise());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return systemSuperviseList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_system_supervise");
	}
	
	public ActionForward updSystemSuperviseEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemSuperviseForm ssform = (SystemSuperviseForm)form;
		SystemSuperviseService service = new SystemSuperviseService();
		
		//根据id获取修改对象
		SystemSuperviseVO vo = service.loadSystemSuperviseById(ssform.getSystemSupervise().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return systemSuperviseList(mapping, form, request, response);
		}
		
		ssform.setSystemSupervise(vo);
		
		//返回修改页面
		return mapping.findForward("upd_system_supervise");
	}
	
	public ActionForward updSystemSupervise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemSuperviseForm ssform = (SystemSuperviseForm)form;
		SystemSuperviseService service = new SystemSuperviseService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updSystemSupervise(ssform.getSystemSupervise());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return systemSuperviseList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_system_supervise");
	}
	
	public ActionForward delSystemSupervise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SystemSuperviseForm ssform = (SystemSuperviseForm)form;
		SystemSuperviseService service = new SystemSuperviseService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSystemSupervise(ssform.getSystemSupervise().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return systemSuperviseList(mapping, form, request, response);
	}
}
