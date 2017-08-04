package com.zd.csms.rbac.login.common;

public class LoginConstants {
	
	/**
	 * 登录用户信息
	 * */
	public static final String USERSESSION = "userMessage";

	/**
	 * 登录状态成功 0
	 * */
	public final static int LOGIN_STATE_SUCCESS = 0;

	/**
	 * 登录状态异常(系统) 1
	 * */
	public final static int LOGIN_STATE_ERROR = 1;

	/**
	 * 登录状态账户名错误  2
	 * */
	public final static int LOGIN_STATE_LOGINIDERROR = 2;

	/**
	 * 登录状态密码错误  3
	 * */
	public final static int LOGIN_STATE_PASSWORDERROR = 3;

	/**
	 * 登录状态账户非在用状态  4
	 * */
	public final static int LOGIN_STATE_STATEERROR = 4;


}
