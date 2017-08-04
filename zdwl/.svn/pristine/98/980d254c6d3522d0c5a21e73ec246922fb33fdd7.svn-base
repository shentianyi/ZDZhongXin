package com.zd.csms.rbac.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.model.RoleQueryVO;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.service.ResourceService;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.web.form.RoleForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RoleAction extends ActionSupport {

	/**
	 * 角色查询列表入口（为默认不执行查询操作服务）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward roleListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return roleList(mapping,form,request,response);
	}
	
	/**
	 * 角色查询列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward roleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();
		
		RoleQueryVO query = roleForm.getRoleQuery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleList",request);
		thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<RoleVO> list = service.searchRoleListByPage(query, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//初始化页面Option
		initOption(request);

		//返回列表页面
		return mapping.findForward("role_list");
	}
	
	/**
	 * 新增角色入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addRoleEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//初始化页面Option
		initOption(request);

		//返回新增页面
		return mapping.findForward("add_role");
	}
	
	/**
	 * 新增角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();

		//执行新增操作并获取操作结果
		
		boolean flag = service.addRole(roleForm.getRole());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return roleList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return addRoleEntry(mapping, form, request, response);
	}
	
	/**
	 * 修改角色入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward updRoleEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();

		//根据id获取修改对象
		RoleVO vo;
		try {
			vo = service.loadRoleById(roleForm.getRole().getId());
			//对象不存在时返回列表页
			if(vo == null){
				request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
				return roleList(mapping, form, request, response);
			}
			
			//设置修改对象
			roleForm.setRole(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//初始化页面Option
		initOption(request);

		//返回修改页面
		return mapping.findForward("upd_role");
	}
	
	/**
	 * 修改角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward updRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();

		//执行修改操作并获取操作结果
		boolean flag = false;
		try {
			flag = service.updRole(roleForm.getRole());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());

		if(flag){
			//操作成功时返回列表页面
			return roleList(mapping, form, request, response);
		}

		//操作失败时返回修改 页面
		return mapping.findForward("upd_role");
	}
	
	/**
	 * 删除角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward delRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();
		
		//执行删除操作并获取操作结果
		boolean flag = false;
		try {
			flag = service.deleteRole(roleForm.getRole().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return roleList(mapping, form, request, response);
	}
	
	/**
	 * 修改角色状态
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward updRoleState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();

		boolean flag = false;
		//创建角色操作对象，用于修改状态使用
		
		try {
			RoleVO vo = service.loadRoleById(roleForm.getRole().getId());
		
			vo.setState(roleForm.getRole().getState());

			//执行删除操作并获取操作结果
		
			flag = service.updRole(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());

		//返回列表页面
		return roleList(mapping, form, request, response);
	}
	
	/**
	 * 通过账户id查询与账户有关的角色列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward roleListWithUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();
		
		RoleQueryVO query = roleForm.getRoleQuery();
		int userId = StringUtil.intValue(request.getParameter("userId"), -1);

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithUser",request);
		//thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<RoleVO> list = service.searchRoleListWithUser(query, userId, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("userId", userId);
		
		//返回基于账户的角色列表页面
		return mapping.findForward("role_list_with_user");
	}
	
	/**
	 * 通过账户id查询与账户无关的角色列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward roleListWithoutUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();
		
		RoleQueryVO query = roleForm.getRoleQuery();
		int userId = StringUtil.intValue(request.getParameter("userId"), -1);

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithoutUser",request);
		//thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<RoleVO> list = service.searchRoleListWithoutUser(query, userId, thumbPageTools);
		request.setAttribute("list", list);
		request.setAttribute("userId", userId);
		
		//返回基于账户的角色分配页面
		return mapping.findForward("role_list_without_user");
	}
	
	/**
	 * 为账户分配新角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();
		
		int userId = StringUtil.intValue(request.getParameter("userId"), -1);
		
		//执行新增操作并获取操作结果
		boolean flag = service.addUserRole(new int[]{userId}, roleForm.getRoleIds());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithUser", request);
		//roleForm.setRoleQuery((RoleQueryVO)thumbPageTools.getQueryVO());

		//返回基于账户的角色列表页面
		return roleListWithUser(mapping, form, request, response);
	}
	
	/**
	 * 删除账户下分配的角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delRoleUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();
		
		int userId = StringUtil.intValue(request.getParameter("userId"), -1);

		//执行删除操作并获取操作结果
		boolean flag = service.delUserRole(new int[]{userId},roleForm.getRoleIds());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithUser", request);
		//roleForm.setRoleQuery((RoleQueryVO)thumbPageTools.getQueryVO());

		//返回基于账户的角色列表页面
		return roleListWithUser( mapping,  form, request,  response);
				
	}
	
	/**
	 * 通过资源id查询与角色有关的角色列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward roleListWithResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();

		RoleQueryVO query = roleForm.getRoleQuery();
		int resourceId = StringUtil.intValue(request.getParameter("resourceId"), -1);

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithResource",request);
		//thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<RoleVO> list = service.searchRoleListWithResource(query, resourceId, thumbPageTools);
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("resourceId", resourceId);

		//返回基于资源的角色列表页面
		return mapping.findForward("role_list_with_resource");
	}
	
	/**
	 * 通过资源id查询与角色无关的角色列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward roleListWithoutResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		RoleService service = new RoleService();

		RoleQueryVO query = roleForm.getRoleQuery();
		int resourceId = StringUtil.intValue(request.getParameter("resourceId"), -1);

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithoutResource",request);
		//thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<RoleVO> list = service.searchRoleListWithoutResource(query, resourceId, thumbPageTools);
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("resourceId", resourceId);

		//返回基于资源的角色分配页面
		return mapping.findForward("role_list_without_resource");
	}
	
	/**
	 * 为资源分配新角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addRoleResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RoleForm roleForm = (RoleForm)form;
		ResourceService service = new ResourceService();
		
		int resourceId = StringUtil.intValue(request.getParameter("resourceId"), -1);

		
		//执行新增操作并获取操作结果
		boolean flag = service.addRoleResource(roleForm.getRoleIds(), new int[]{resourceId});
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithResource", request);
		//roleForm.setRoleQuery((RoleQueryVO)thumbPageTools.getQueryVO());

		//返回基于资源的角色列表页面
		return roleListWithResource( mapping, form, request, response);
	}
	
	/**
	 * 删除资源下分配的角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delRoleResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RoleForm roleForm = (RoleForm)form;
		ResourceService service = new ResourceService();
		
		int resourceId = StringUtil.intValue(request.getParameter("resourceId"), -1);

		//执行删除操作并获取操作结果
		boolean flag = service.delRoleResource(roleForm.getRoleIds(), new int[]{resourceId});
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("roleListWithResource", request);
		//roleForm.setRoleQuery((RoleQueryVO)thumbPageTools.getQueryVO());

		//返回基于资源的角色列表页面
		return roleListWithResource(mapping, form, request, response);
	}
	
	/**
	 * 初始化Option
	 * @param request
	 * @return void
	 */
	private void initOption(HttpServletRequest request){
		//初始化角色状态下拉列表
		request.setAttribute("stateOptions", OptionUtil.stateOptions());
		//客户类型下拉列表
		request.setAttribute("clientTypeOptions", OptionUtil.clientTypeOptions());
	}
}
