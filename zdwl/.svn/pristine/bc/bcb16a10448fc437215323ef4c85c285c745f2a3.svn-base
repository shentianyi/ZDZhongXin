package com.zd.csms.rbac.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.RoleVO;

public class RoleUtil {

	/**
	 * 验证是否拥有权限
	 * 
	 * @param clientType
	 * @return
	 */
	/*
	 * public static boolean roleValidate(int clientType,List<RoleVO> roles){
	 * for (RoleVO roleVO : roles) { if(clientType == roleVO.getClient_type()){
	 * return true; } } return false; }
	 */

	public static boolean roleValidate(int ValidateRole, int currRole) {
		return ValidateRole == currRole;
	}

	/**
	 * 获取当前角色 如果多个角色符合，返回第一个 如果没有符合的角色 返回-1
	 * 
	 * @param u
	 * @param roles
	 * @return
	 */
	public static int getCurrRole(UserSession user, int... roles) {
		if (roles == null) {
			return -1;
		}
		List<RoleVO> roleList = user.getRole();
		if (roleList == null || roleList.size() <= 0) {
			return -1;
		} else {
			for (RoleVO roleVO : roleList) {
				for (int role : roles) {
					if (role == roleVO.getId())
						return role;
				}
			}
		}
		return -1;
	}
	
	

	public static int getCurrRole(int[] roles, HttpServletRequest request, HttpServletResponse response) {
		int currRole = -1;
		if (roles != null) {
			UserSession user = UserSessionUtil.getUserSession(request);
			List<RoleVO> roleList = user.getRole();
			if (roleList != null && roleList.size() > 0) {
				for (RoleVO roleVO : roleList) {
					for (int role : roles) {
						if (role == roleVO.getId())
							currRole = role;
					}
				}
			}
		}
//		if(currRole == -1){
//			try {
//				response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		return currRole;
	}

	/*
	 * public static boolean roleValidate(List<RoleVO> roles,int... clientType){
	 * for (int i : clientType) { for (RoleVO roleVO : roles) {
	 * if(i==roleVO.getClient_type()){ return true; } } } return false; }
	 */
}
