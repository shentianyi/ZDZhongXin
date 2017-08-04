package com.zd.csms.rbac.web.action;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.SystemProperty;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.SysLog;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.rbac.web.form.UserForm;
import com.zd.tools.DigestUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class UserAction extends ActionSupport {
	private BankService bankService = new BankService();

	/**
	 * 账户查询列表入口（为默认不执行查询操作服务）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward userListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return userList(mapping,form,request,response);
	}
	
	/**
	 * 账户查询列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward userList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService(); 
		/*
		 * 1.给招聘专员、监管员管理部经理、运营管理部部长、物流金融中心总监分配账号管理菜单权限。
		 * 2、扩展账号管理功能，招聘专员、监管员管理部经理、运营管理部部长、物流金融中心总监登录时，新增、修改页面客户类型下拉列表只显示‘监管员’，
		 * 保证这些角色只能配置监管员账号。
		 * 3、系统添加日志，记录创建人、创建时间、修改人、修改时间等信息。


		 */
		
		UserQueryVO query = userForm.getUserQuery();
		List<RoleVO> role = UserSessionUtil.getUserSession(request).getRole();
		int[] roleIds = {4,9,28,29};
		for (RoleVO r : role) {
			for (int i : roleIds) {
				if(i == r.getId()){
					query.setClienttype(ClientTypeConstants.SUPERVISORY.getCode());
					query.setFlag(1);
					break;
				}
			}
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("userList",request);
		tools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<UserVO> list = service.searchUserListByPage(query,tools);
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//初始化页面Option
		initOption(request);
		
		if(query.getFlag() == 1){
			List<OptionObject> oo = (List<OptionObject>) request.getAttribute("clientTypeOptions");
			for(int i = 0; i < oo.size();i++){
				Integer value = (Integer) oo.get(i).getValue();
				if(!(value == 3)){
					oo.remove(i);
						i--;
				}
			}
		}
		
		

		//返回列表页面
		return mapping.findForward("user_list");
	}
	
	
	/**
	 * 新增账户入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addUserEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserForm userForm = (UserForm)form;
		UserQueryVO query = userForm.getUserQuery();
		//初始化页面Option
		initOption(request);
		
		if(query.getFlag() == 1){
			List<OptionObject> oo = (List<OptionObject>) request.getAttribute("clientTypeOptions");
			for(int i = 0; i < oo.size();i++){
				Integer value = (Integer) oo.get(i).getValue();
				if(!(value == 3)){
					oo.remove(i);
						i--;
				}
			}
		}

		//返回新增页面
		return mapping.findForward("add_user");
	}
	
	/**
	 * 新增账户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSession userSession = UserSessionUtil.getUserSession(request);
		UserForm userForm = (UserForm)form;
		//如果用户是平台注册则自动绑定平台ID
		if(userForm.getUser().getClient_type()==6){
			userForm.getUser().setClient_id(1);
		}
		Integer[] roles = null; 
		String yhqx[] = request.getParameterValues("yhqx");
		String zhqx[] = request.getParameterValues("zhqx");
		
		if(yhqx !=null && zhqx == null){
			StringBuffer yhqxs = new StringBuffer();
			for (int i = 0; i < yhqx.length; i++) {
				yhqxs.append(yhqx[i]+",");
			}
			String yhqxStr = yhqxs.toString().substring(0,yhqxs.toString().length()-1);
			roles = new Integer[yhqx.length];
			StringTokenizer toKenizer = new StringTokenizer(yhqxStr, ",");
			int i = 0;
			while (toKenizer.hasMoreElements()) {
				roles[i++] = Integer.valueOf(toKenizer.nextToken());

			}
		}else if(yhqx ==null && zhqx !=null){
			StringBuffer zhqxs = new StringBuffer();
			for (int i = 0; i < zhqx.length; i++) {
				zhqxs.append(zhqx[i]+",");
			}
			String zhqxStr = zhqxs.toString().substring(0,zhqxs.toString().length()-1);
			
			roles = new Integer[zhqx.length];
			StringTokenizer toKenizer = new StringTokenizer(zhqxStr, ",");
			int i = 0;
			while (toKenizer.hasMoreElements()) {
				roles[i++] = Integer.valueOf(toKenizer.nextToken());

			}
		}
		
		userForm.setRoles(roles);
		
		
		UserService service = new UserService();
		UserVO uservo = userForm.getUser();
		String loginid = uservo.getLoginid();
		UserQueryVO uq = new UserQueryVO();
		uq.setLgid(loginid);
		List<UserVO> userList = service.searchUserList(uq);
		if(userList.size()>0){
			request.setAttribute("message", "登录名已存在");
			//新增失败时返回新增页面
			return addUserEntry(mapping, form, request, response);
		}
		
		String password = uservo.getPassword().toUpperCase();
		uservo.setPassword(password);
		
		//MD5加密过程不可逆。用户原始密码用于邮件展示
		String passwork=uservo.getPassword();
		
		//添加User创建人ID 创建时间
		uservo.setCreateuserid(userSession.getUser().getId());
		uservo.setCreatetime(new Date());
		
		
		//执行新增操作并获取操作结果
		boolean flag = service.addUser(uservo);
		
		if(flag){
			SysLog.info("loginid:"+userSession.getUser().getLoginid()+"("+userSession.getUser().getId()+") Object:"+uservo.toString());
		}
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return userList(mapping, form, request, response);
		}
		//新增失败时返回新增页面
		return addUserEntry(mapping, form, request, response);
	}
	
	/**
	 * 修改账户入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updUserEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();

		//根据id获取修改对象
		UserVO vo = service.loadUserById(userForm.getUser().getId());
		if(vo.getClient_type()==ClientTypeConstants.BANK.getCode()){
			BankVO bank = bankService.get(vo.getClient_id());
			if(bank!=null){
				request.setAttribute("bank", bank);
			}
		}
		
		//对象不存在时返回列表页
		if(vo == null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return userList(mapping, form, request, response);
		}

		//设置修改对象
		userForm.setUser(vo);
		
		//初始化页面Option
		initOption(request);
		if(userForm.getUserQuery().getFlag() == 1){
			List<OptionObject> oo = (List<OptionObject>) request.getAttribute("clientTypeOptions");
			for(int i = 0; i < oo.size();i++){
				Integer value = (Integer) oo.get(i).getValue();
				if(!(value == 3)){
					oo.remove(i);
					i--;
				}
			}
			
		}

		//返回修改页面
		return mapping.findForward("upd_user");
	}

	
	/**
	 * 修改账户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();
		UserSession userSession = UserSessionUtil.getUserSession(request);
		boolean flag = false;
		
		UserVO user1 = userForm.getUser();
		
		UserVO user = service.loadUserById(userForm.getUser().getId());
		
		String newloginid = user1.getLoginid();
		String oldloginid = user.getLoginid();
		
		if(!newloginid.equals(oldloginid)){
			List<String> ids = service.findAllIds();
			if(ids.contains(newloginid)){
				request.setAttribute("message", "用户名已存在");
				//新增失败时返回新增页面
				return addUserEntry(mapping, form, request, response);
			}
			
		}
		
		//修改User  添加修改UserId和时间
		user.setUpduserid(userSession.getUser().getId());
		user.setUpddate(new Date());
		
		if(!StringUtil.isEmpty(userForm.getUser().getPassword())){
			String pass = userForm.getUser().getPassword().toUpperCase();
			user.setPassword(pass);
			user.setEmail(user1.getEmail());
			user.setClient_type(user1.getClient_type());
			user.setLoginid(user1.getLoginid());
			user.setUserName(user1.getUserName());
			user.setMobilephone(user1.getMobilephone());
			user.setClient_id(user1.getClient_id());
			user.setState(user1.getState());
			
			encryptPassword(user);
			flag = service.updUser(user);
		}else{
			user1.setPassword(user.getPassword());
			user1.setPassword_random(user.getPassword_random());
			flag = service.updUser(user1);
		}
		
		if(flag){
			SysLog.info("loginid:"+userSession.getUser().getLoginid()+"("+userSession.getUser().getId()+") Object:"+user.toString());
		}
		
		//执行修改操作并获取操作结果
		
			
			
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return userList(mapping, form, request, response);
		}
		initOption(request);
		//操作失败时返回修改 页面
		return mapping.findForward("upd_user");
	}
	
	/**
	 * 删除账户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteUser(userForm.getUser().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return userList(mapping, form, request, response);
	}
	
	/**
	 * 修改账户状态
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updUserState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();

		//创建账户操作对象，用于修改状态使用
		UserVO vo = service.get(userForm.getUser().getId());
		//vo.setId(userForm.getUser().getId());		//设置操作账户id
		vo.setState(userForm.getUser().getState());	//设置修改账户状态
		

		//执行删除操作并获取操作结果
		boolean flag = service.updUser(vo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());

		//返回列表页面
		return userList(mapping, form, request, response);
	}
	
	/**
	 * 通过角色id查询与账户有关的账户列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward userListWithRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();
		
		UserQueryVO query = userForm.getUserQuery();
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);

		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("userListWithRole",request);
		tools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<UserVO> list = service.searchUserListWithRole(query, roleId, tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("roleId", roleId);
		
		//初始化页面Option
		initOption(request);
		
		//返回基于角色的账户列表页面
		return mapping.findForward("user_list_with_role");
	}
	
	/**
	 * 通过角色id查询与账户无关的账户列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward userListWithoutRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();
		
		UserQueryVO query = userForm.getUserQuery();
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);

		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("userListWithoutRole",request);
		tools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<UserVO> list =  service.searchUserListWithoutRole(query,roleId,tools);
		request.setAttribute("list", list);
		request.setAttribute("roleId", roleId);

		//初始化页面Option
		initOption(request);
		
		//返回基于角色的账户分配页面
		return mapping.findForward("user_list_without_role");
	}
	
	/**
	 * 为角色分配新账户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserForm userForm = (UserForm)form;
		RoleService service = new RoleService();
		
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);

		//执行新增操作并获取操作结果
		boolean flag = service.addUserRole(userForm.getUserIds(), new int[]{roleId});
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("userListWithRole", request);
		//userForm.setUserQuery((UserQueryVO)thumbPageTools.getQueryVO());

		//返回基于角色的账户列表页面
		return userListWithRole(mapping, form, request, response);
		
	}
	
	/**
	 * 删除角色下分配的账户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserForm userForm = (UserForm)form;
		RoleService service = new RoleService();
		
		int roleId = StringUtil.intValue(request.getParameter("roleId"), -1);

		//执行删除操作并获取操作结果
		boolean flag = service.delUserRole(userForm.getUserIds(), new int[]{roleId});
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//重置原始查询条件
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("userListWithRole", request);
		//userForm.setUserQuery((UserQueryVO)thumbPageTools.getQueryVO());

		//返回基于角色的账户列表页面
		return userListWithRole(mapping, form, request, response);
	}
	/**
	 * 
	* <p>方法名称: showRegisterUser|描述: 显示注册信息页</p>
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return
	 * @throws Exception 
	* @作者: JIANGYE
	* @日期: 2013-7-24 下午03:44:45
	*
	* @描述: 简要描述
	 */
	public ActionForward showRegisterUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();
		String password = userForm.getUser().getPassword();
		UserVO user = userForm.getUser();
		user = service.loadUserById(user.getId());
		if(user!=null){
			Object encode =  request.getParameter("encode");
			String updPassWordUrl=SystemProperty.getPropertyValue("system.properties","staticPath") + "/register/updpawbyemail.do?method=changePasswordByEmailEntry" + "&user.id=" + user.getId();
			if(encode!=null){
				updPassWordUrl += "&encode="+encode.toString();
			}
			request.setAttribute("updPassWordUrl", updPassWordUrl);
			user.setPassword(password);
			userForm.setUser(user);
		}
		//返回修改页面
		return mapping.findForward("show_user_email");
	}
	
	public ActionForward checkRegisterUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		if (userName == null || userName.trim().length() <= 0) {
			return null;
		}
		UserService userService = new UserService();
		UserVO user = userService.searchUserByLoginid(userName);
		String result = user!=null ? "1" : "2";
		
		request.setAttribute("result", result);
		//返回列表页面
		return mapping.findForward("check_user_result");
	}
	
	private void encryptPassword(UserVO vo){
		vo.setPassword_random(Math.random()+"");
		String ps = vo.getPassword() + vo.getPassword_random();
		vo.setPassword(DigestUtil.MD5(ps));
	}
	
	/**
	 * 初始化Option
	 * @param request
	 * @return void
	 */
	private void initOption(HttpServletRequest request){
		//初始化人员状态列表
		request.setAttribute("userStateOptions", OptionUtil.fullStateOptions());
		//客户类型下拉列表
		request.setAttribute("clientTypeOptions", OptionUtil.clientTypeOptions());
		
	}
	
	public ActionForward jgyuserList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService(); 
		
		UserQueryVO query = userForm.getUserQuery();
		query.setClienttype(ClientTypeConstants.SUPERVISORY.getCode());
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("jgyuserList",request);
		tools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页

		//按条件查询分页数据
		List<UserVO> list = service.searchUserListByPage(query,tools);
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//初始化页面Option
		initOption(request);

		//返回列表页面
		return mapping.findForward("jgy_user_list");
	}
	
	public ActionForward addjgyUserEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//初始化页面Option
		initOption(request);

		//返回新增页面
		return mapping.findForward("add_jgy_user");
	}
	
	/**
	 * 新增账户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward addjgyUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		//如果用户是平台注册则自动绑定平台ID
		if(userForm.getUser().getClient_type()==6){
			userForm.getUser().setClient_id(1);
		}
		Integer[] roles = null; 
		String yhqx[] = request.getParameterValues("yhqx");
		String zhqx[] = request.getParameterValues("zhqx");
		
		if(yhqx !=null && zhqx == null){
			StringBuffer yhqxs = new StringBuffer();
			for (int i = 0; i < yhqx.length; i++) {
				yhqxs.append(yhqx[i]+",");
			}
			String yhqxStr = yhqxs.toString().substring(0,yhqxs.toString().length()-1);
			roles = new Integer[yhqx.length];
			StringTokenizer toKenizer = new StringTokenizer(yhqxStr, ",");
			int i = 0;
			while (toKenizer.hasMoreElements()) {
				roles[i++] = Integer.valueOf(toKenizer.nextToken());

			}
		}else if(yhqx ==null && zhqx !=null){
			StringBuffer zhqxs = new StringBuffer();
			for (int i = 0; i < zhqx.length; i++) {
				zhqxs.append(zhqx[i]+",");
			}
			String zhqxStr = zhqxs.toString().substring(0,zhqxs.toString().length()-1);
			
			roles = new Integer[zhqx.length];
			StringTokenizer toKenizer = new StringTokenizer(zhqxStr, ",");
			int i = 0;
			while (toKenizer.hasMoreElements()) {
				roles[i++] = Integer.valueOf(toKenizer.nextToken());

			}
		}
		
		userForm.setRoles(roles);
		
		
		UserService service = new UserService();
		UserVO uservo = userForm.getUser();
		String loginid = uservo.getLoginid();
		uservo.setClient_type(ClientTypeConstants.SUPERVISORY.getCode());
		UserQueryVO uq = new UserQueryVO();
		uq.setLoginid(loginid);
		List<UserVO> userList = service.searchUserList(uq);
		if(userList.size()>0){
			request.setAttribute("message", "用户名已存在");
			//新增失败时返回新增页面
			return addUserEntry(mapping, form, request, response);
		}
		
		String password = uservo.getPassword().toUpperCase();
		uservo.setPassword(password);
		
		//MD5加密过程不可逆。用户原始密码用于邮件展示
		String passwork=uservo.getPassword();
		
		
		
		//执行新增操作并获取操作结果
		int id = service.addjgyUser(uservo);
		RoleService rs = new RoleService();
		UserRoleVO vo = new UserRoleVO();
		vo.setUser_id(id);
		vo.setRole_id(10);
		rs.addUserRole(vo);
		
		boolean flag = false;
		if(id>0){
			flag = true;
		}
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return jgyuserList(mapping, form, request, response);
		}
		//新增失败时返回新增页面
		return addjgyUserEntry(mapping, form, request, response);
	}
	
	public ActionForward updjgyUserEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();

		//根据id获取修改对象
		UserVO vo = service.loadUserById(userForm.getUser().getId());
		if(vo.getClient_type()==ClientTypeConstants.BANK.getCode()){
			BankVO bank = bankService.get(vo.getClient_id());
			if(bank!=null){
				request.setAttribute("bank", bank);
			}
		}
		
		//对象不存在时返回列表页
		if(vo == null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return userList(mapping, form, request, response);
		}

		//设置修改对象
		userForm.setUser(vo);
		
		//初始化页面Option
		initOption(request);

		//返回修改页面
		return mapping.findForward("upd_jgy_user");
	}

	
	/**
	 * 修改账户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updjgyUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();
		
		boolean flag = false;
		
		UserVO user1 = userForm.getUser();
		
		UserVO user = service.loadUserById(userForm.getUser().getId());
		
		String newloginid = user1.getLoginid();
		String oldloginid = user.getLoginid();
		
		if(!newloginid.equals(oldloginid)){
			List<String> ids = service.findAllIds();
			if(ids.contains(newloginid)){
				request.setAttribute("message", "用户名已存在");
				//新增失败时返回新增页面
				return addUserEntry(mapping, form, request, response);
			}
			
		}
		
		if(!StringUtil.isEmpty(userForm.getUser().getPassword())){
			String pass = userForm.getUser().getPassword().toUpperCase();
			user.setPassword(pass);
			user.setEmail(user1.getEmail());
			user.setClient_type(user1.getClient_type());
			user.setLoginid(user1.getLoginid());
			user.setUserName(user1.getUserName());
			user.setMobilephone(user1.getMobilephone());
			user.setClient_id(user1.getClient_id());
			user.setState(user1.getState());
			
			encryptPassword(user);
			flag = service.updUser(user);
		}else{
			user1.setClient_type(ClientTypeConstants.SUPERVISORY.getCode());
			user1.setPassword(user.getPassword());
			user1.setPassword_random(user.getPassword_random());
			flag = service.updUser(user1);
		}
		
		//执行修改操作并获取操作结果
		
			
			
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return jgyuserList(mapping, form, request, response);
		}
		initOption(request);
		//操作失败时返回修改 页面
		return mapping.findForward("upd_jgy_user");
	}
	public ActionForward deljgyUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteUser(userForm.getUser().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return jgyuserList(mapping, form, request, response);
	}
	
	/**
	 * 修改账户状态
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward updjgyUserState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm userForm = (UserForm)form;
		UserService service = new UserService();

		//创建账户操作对象，用于修改状态使用
		UserVO vo = service.get(userForm.getUser().getId());
		//vo.setId(userForm.getUser().getId());		//设置操作账户id
		vo.setState(userForm.getUser().getState());	//设置修改账户状态
		

		//执行删除操作并获取操作结果
		boolean flag = service.updUser(vo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());

		//返回列表页面
		return jgyuserList(mapping, form, request, response);
	}
}
