package com.zd.csms.rbac.util;

import java.io.Serializable;
import java.util.List;

import com.zd.csms.rbac.login.model.MenuNodeVO;
import com.zd.csms.rbac.model.ResourceVO;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.model.UserVO;


public class UserSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5938854381107422672L;

	private int loginState = -1;			//登录状态
	private UserVO user;					//登录账户信息
	private List<RoleVO> role;				//账户分配角色集合
    private List<MenuNodeVO> meunTree;		//账户菜单项集合（树形）
    private List<ResourceVO> resourceList;	//账户分配资源集合
    private String sessionId;				//会话id
    private String ip;						//会话登录ip
	public int getLoginState() {
		return loginState;
	}
	public void setLoginState(int loginState) {
		this.loginState = loginState;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public List<RoleVO> getRole() {
		return role;
	}
	public void setRole(List<RoleVO> role) {
		this.role = role;
	}
	public List<MenuNodeVO> getMeunTree() {
		return meunTree;
	}
	public void setMeunTree(List<MenuNodeVO> meunTree) {
		this.meunTree = meunTree;
	}
	public List<ResourceVO> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<ResourceVO> resourceList) {
		this.resourceList = resourceList;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
    
    
}
