package com.zd.csms.rbac.login.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.login.common.LoginConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.login.web.form.LoginForm;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.rbac.web.form.UserForm;
import com.zd.tools.StringUtil;

public class LoginAction extends ActionSupport {
	private static Log log = LogFactory.getLog(LoginAction.class);
	/**
	 * 登录入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward loginEntry(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		//返回登录页面
		return mapping.findForward("login");
	}
	
	/**
	 * 登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward login(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginForm form = (LoginForm)actionform;
		//通过util执行登录方法，获取登录会话对象
		if(!StringUtil.isEmpty(form.getPassword())){
			String password = form.getPassword().toUpperCase();
			form.setPassword(password);
		}
		UserSession u = UserSessionUtil.login(form.getLoginid().trim(), form.getPassword().trim(), request);
		
		//当登录状态不为成功时对登录错误信息进行处理
		if(u.getLoginState()!=LoginConstants.LOGIN_STATE_SUCCESS){
			String msg = null;
			if(u.getLoginState()==LoginConstants.LOGIN_STATE_LOGINIDERROR
			|| u.getLoginState()==LoginConstants.LOGIN_STATE_PASSWORDERROR){
				//如账户名或密码输入错误，统一提示为账户名或密码错误
				msg = "账户名或密码错误";
			} else if(u.getLoginState()==LoginConstants.LOGIN_STATE_STATEERROR){
				//账户状态错误时提示用户非在用状态
				msg = "账户非在用状态";
			} else{
				//除预定错误状态外提示用户系统错误
				msg = "系统出现错误";
			}
			//设置错误提示信息，并返回登录页面
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), msg);
			return loginEntry(mapping,actionform,request,response);
		}
		log.info(u.getUser().getUserName()+" 登录成功！");
			//登录成功，返回主窗口
			return mapping.findForward("main");
	}
	
	/**	
	 * 注销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		//通过util执行注销方法
		UserSessionUtil.logout(request);
		//返回登录界面
		return loginEntry(mapping, actionform, request, response) ;
	}
	
	/**
	 * 退出系统
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward systemClear(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		//通过util执行注销方法
		UserSessionUtil.logout(request);
		//退出系统后无返回页面
		return null ;
	}
	
	/**
	 * 修改密码入口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward changePasswordEntry(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		//进入修改密码页面
		return mapping.findForward("change_password");
	}
	
	/**
	 * 修改密码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward changePassword(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginForm form = (LoginForm)actionform;
		UserService service = new UserService();
		
		if(UserSessionUtil.getUserSession(request)==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "登录已超时，请重新登录");
			return loginEntry(mapping,actionform,request,response);
		}
		
		//执行修改密码操作并获取操作结果
		boolean flag = service.updPassword(UserSessionUtil.getUserSession(request).getUser().getId(),
				form.getPassword(), form.getLastPassword());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//进入修改密码页面
		return changePasswordEntry(mapping, actionform, request, response);
	}
	/**
	 * 修改密码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward changePasswordByEmail(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserForm form = (UserForm)actionform;
		UserService service = new UserService();
		
		//执行修改密码操作并获取操作结果
		boolean flag = service.updUser(form.getUser());
		if(flag){
			UserSessionUtil.logout(request);
			//操作成功，返回登陆页
			return mapping.findForward("logout");
		}else{
			//操作失败，返回修改页
			return mapping.findForward("change_password");
		}

	}
}
