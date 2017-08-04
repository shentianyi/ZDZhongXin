package com.zd.csms.dbsy.service;

import java.util.ArrayList;
import java.util.List;

import com.zd.csms.dbsy.model.DbsyVO;
import com.zd.csms.rbac.util.UserSession;


public class DefaultDbsyManagement{
	List<IDbsyManagement> dbsyManagements;

	/**
	 * 
	* <p>方法名称: searchDbsyvoWithLoginUser|描述: 根据角色权限，查询用户可处理的待办事宜</p>
	* @param userSession
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-5 下午03:28:33
	*
	* @描述: 简要描述
	 */
	public List<DbsyVO> searchDbsyvoWithLoginUser(UserSession userSession){
		List<DbsyVO> dbsyList=new ArrayList<DbsyVO>();
		for(IDbsyManagement dbsyManagement: dbsyManagements){
			DbsyVO dbsyVO = dbsyManagement.getDbsyvoWithLoginUser(userSession);
			if(dbsyVO!=null){
				dbsyList.add(dbsyVO);
			}
		}
		return dbsyList;
	}
	
	public List<IDbsyManagement> getDbsyManagements(){
		return dbsyManagements;
	}
	
	public void setDbsyManagements(List<IDbsyManagement> dbsyManagements){
		this.dbsyManagements = dbsyManagements;
	}
	
}
