package com.zd.csms.rbac.util;

import java.util.List;

import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.model.LoginUserVO;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;

public class LoginUserUtil {

	public static LoginUserVO loginUser(int userid){
		
		RoleService rs = new RoleService();
		LoginUserVO lu = new LoginUserVO();
		UserService us = new UserService();
		BankService bs = new BankService();
		try {
			UserVO user = us.loadUserById(userid);
			UserRoleQueryVO urquery = new UserRoleQueryVO();
			urquery.setUser_id(userid);
			List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
			if(urList != null && urList.size()>0){
				RoleVO rvo = rs.loadRoleById(urList.get(0).getRole_id());
				if(rvo != null){
					String role = rvo.getRole_name();
					lu.setYiji(ClientTypeConstants.getName(user.getClient_type()));
					lu.setErji(role);
					lu.setSanji(user.getUserName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return lu;
	}
}
