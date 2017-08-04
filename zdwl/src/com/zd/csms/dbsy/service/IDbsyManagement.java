package com.zd.csms.dbsy.service;

import com.zd.csms.dbsy.model.DbsyVO;
import com.zd.csms.rbac.util.UserSession;

/**
 * 
* <p>版权所有:北京科码先锋软件技术有限公司</p>
* @作者: JIANGYE
* @日期: 2013-7-5 上午09:54:17
* @描述: [IDbsyServiceManagement]请在此简要描述类的功能
 */
public interface IDbsyManagement{
	
	/**
	 * 
	* <p>方法名称: search|描述: 根据业务条件拼装待办事宜对象</p>
	* @param loginUser
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-5 上午09:56:51
	*
	* @描述: 简要描述
	 */
	public DbsyVO getDbsyvoWithLoginUser(UserSession userSession);
	
	/**
	 * 
	* <p>方法名称: validateUserRole|描述: 根据登陆角色验证用户是否有指定节点访问权限</p>
	* @param userSession
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-8 上午09:32:14
	*
	* @描述: 简要描述
	 */
	public boolean validateUserRole(UserSession userSession);
}
