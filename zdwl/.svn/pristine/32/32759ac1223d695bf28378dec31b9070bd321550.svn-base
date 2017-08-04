package com.zd.csms.rbac.web.action;

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
import com.zd.csms.rbac.model.ResourceQueryVO;
import com.zd.csms.rbac.model.ResourceVO;
import com.zd.csms.rbac.service.ResourceService;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.web.form.ResourceForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ResourceAction extends ActionSupport {

	/**
	 * 资源查询列表入口（为默认不执行查询操作服务）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward resourceListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return resourceList(mapping, form,request, response);
	}
	
	/**
	 * 资源查询列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward resourceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ResourceForm resForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		ResourceQueryVO resQuery = resForm.getResourceQuery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("resourceList",request);
		thumbPageTools.saveQueryVO(resQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<ResourceVO> list = service.searchResourceListByPage(resQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//初始化页面Option
		initOption(request);
		
		//返回列表页面
		return mapping.findForward("resource_list");
	}
	
	public ActionForward resourceListByParent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ResourceService service = new ResourceService();
		
		String idStr = request.getParameter("id");
		int id = -1;
		if(!StringUtil.isEmpty(idStr)){
			id = Integer.parseInt(idStr);
		}
		
		//按条件查询分页数据
		List<ResourceVO> list = service.searchResourceListByParent(id);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//初始化页面Option
		initOption(request);
		
		//返回列表页面
		return mapping.findForward("resource_node_list");
	}
	
	
	/**
	 * 新增资源入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addResourceEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ResourceForm resForm = (ResourceForm)form;
		//初始化资源级别为1
		resForm.getResource().setResource_level(1);
		
		//初始化页面Option
		initOption(request);
		
		//返回新增页面
		return mapping.findForward("add_resource");
	}
	
	/**
	 * 新增下级资源入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward addChildResourceEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResourceForm resForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		//根据资源id查询上级资源数据
		ResourceVO parent;
		parent = service.loadResourceById(resForm.getResource().getId());
		//将上级资源设置到request用于页面显示
		request.setAttribute("parent", parent);
		
		//初始化新增资源上级id和资源级别
		resForm.getResource().setParent_id(parent.getId());
		resForm.getResource().setResource_level(parent.getResource_level()+1);
		
		//初始化页面Option
		initOption(request);
		//返回新增页面
		return mapping.findForward("add_resource");
	}
	
	/**
	 * 新增资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResourceForm resForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addResource(resForm.getResource());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return resourceList(mapping, form, request, response);
		}
		
		//初始化页面Option
		initOption(request);
		
		//新增失败时返回新增页面
		return mapping.findForward("add_resource");
	}
	
	/**
	 * 修改资源入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updResourceEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResourceForm resForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		//根据id获取修改对象
		ResourceVO vo = service.loadResourceById(resForm.getResource().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return resourceList(mapping, form, request, response);
		}
		
		//设置修改对象
		resForm.setResource(vo);
		
		//根据上级id获取上级资源对象，用于页面显示
		ResourceVO parent = service.loadResourceById(vo.getParent_id());
		request.setAttribute("parent", parent);
		
		//初始化页面Option
		initOption(request);
		
		//返回修改页面
		return mapping.findForward("upd_resource");
	}
	
	/**
	 * 修改资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResourceForm resForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updResource(resForm.getResource());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return resourceList(mapping, form, request, response);
		}
		
		//根据上级id获取上级资源对象，用于页面显示
		ResourceVO parent = service.loadResourceById(resForm.getResource().getParent_id());
		request.setAttribute("parent", parent);
		
		//初始化页面Option
		initOption(request);
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_resource");
	}
	
	/**
	 * 删除资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward delResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResourceForm resForm = (ResourceForm)form;
		ResourceService service = new ResourceService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteResource(resForm.getResource().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return resourceList(mapping, form, request, response);
	}
	
	/**
	 * 修改资源状态
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updResourceState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ResourceForm resForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		//创建资源操作对象，用于修改状态使用
		ResourceVO vo = service.loadResourceById(resForm.getResource().getId());
		vo.setState(resForm.getResource().getState());	//设置修改资源状态

		//执行修改操作并获取操作结果
		boolean flag = service.updResource(vo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());

		//返回列表页面
		return resourceList(mapping, form, request, response);
	}
	
	/**
	 * 通过角色id查询与角色有关的资源列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward resourceListWithRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ResourceForm resourceForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		ResourceQueryVO query = resourceForm.getResourceQuery();
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("resourceListWithRole", request);
		thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<ResourceVO> list = service.searchResourceWithRole(query, roleId, thumbPageTools);

		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("roleId", roleId);
		
		//返回基于角色的资源列表页面
		return mapping.findForward("resource_list_with_role");
	}
	
	/**
	 * 通过角色id查询与角色无关的资源列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward searchResourceWithoutRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ResourceForm resourceForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		ResourceQueryVO query = resourceForm.getResourceQuery();
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("searchResourceWithoutRole",request);
		thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<ResourceVO> list = service.searchResourceWithoutRole(query, roleId, thumbPageTools);

		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("roleId", roleId);
		
		//返回基于角色的资源分配页面
		return mapping.findForward("resource_list_without_role");
	}
	
	/**
	 * 通过角色id查询与角色有关的资源列表(服务于账户管理)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward resourceListWithRoleUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ResourceForm resourceForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		ResourceQueryVO query = resourceForm.getResourceQuery();
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("resourceListWithRoleUser", request);
		thumbPageTools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<ResourceVO> list = service.searchResourceWithRole(query, roleId, thumbPageTools);

		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("roleId", roleId);
		request.setAttribute("role", new RoleService().loadRoleById(roleId));
		
		//初始化页面Option
		initOption(request);
		
		//返回基于角色的资源列表页面
		return mapping.findForward("resource_list_with_role_user");
	}
	
	/**
	 * 为角色分配新资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addResourceRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ResourceForm resourceForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);
		

		//执行新增操作并获取操作结果
		boolean flag = service.addRoleResource(new int[]{roleId},resourceForm.getResourceIds());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("resourceListWithRole", request);
		//resourceForm.setResourceQuery((ResourceQueryVO)thumbPageTools.getQueryVO());
		
		//返回基于角色的资源列表页面
		return resourceListWithRole(mapping, form, request, response);
	}
	
	/**
	 * 删除角色下分配的资源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delRoleResource(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		ResourceForm resourceForm = (ResourceForm)form;
		ResourceService service = new ResourceService();
		
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);
		
		//执行删除操作并获取操作结果
		boolean flag = service.delRoleResource(new int[]{roleId},resourceForm.getResourceIds());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("resourceListWithRole", request);
		//resourceForm.setResourceQuery((ResourceQueryVO)thumbPageTools.getQueryVO());

		//返回基于角色的资源列表页面
		return resourceListWithRole(mapping, form, request, response);
	}
	
	/**
	 * 初始化Option
	 * @param request
	 * @return void
	 */
	private void initOption(HttpServletRequest request){
		
		//初始化资源状态复选框
		List<OptionObject> stateOptions = OptionUtil.stateOptions();
		request.setAttribute("stateOptions", stateOptions);
		
		//初始化节点类型复选框
		List<OptionObject> nodeTypeOptions = OptionUtil.resourceTypeOptions();
		request.setAttribute("nodeTypeOptions", nodeTypeOptions);
	}
}
