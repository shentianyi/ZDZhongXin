package com.zd.csms.dbsy.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.BeanManager;
import com.zd.core.cache.CacheConstants;
import com.zd.csms.dbsy.model.DbsyVO;
import com.zd.csms.dbsy.service.DefaultDbsyManagement;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;

public class DbsyAction extends ActionSupport{
	
	/**
	 * 账户查询列表入口（为默认不执行查询操作服务）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward dbsyListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return dbsyList(mapping,form,request,response);
	}
	
	/**
	 * 
	* <p>方法名称: dbsyList|描述: </p>
	* @param mapping
	* @param form
	* @param request
	* @param response
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-4 下午04:46:38
	*
	* @描述: 简要描述
	 */
	public ActionForward dbsyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<DbsyVO> list = null;
		UserSession userSession = UserSessionUtil.getUserSession(request);
		if(userSession!=null){
			DefaultDbsyManagement dbsyManagement = this.getDbsyManagement();
			list = dbsyManagement.searchDbsyvoWithLoginUser(userSession);
		}
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("dbsy_list");
	}
	
	public static List<DbsyVO> checkdbsyList(UserSession u){
		List<DbsyVO> list = null;
		if(u!=null){
			DefaultDbsyManagement dbsyManagement = (DefaultDbsyManagement)BeanManager.getBean(CacheConstants.DBSYMANAGEMENT.getCode());
			list = dbsyManagement.searchDbsyvoWithLoginUser(u);
		}
		return list;
	}
	
	public ActionForward dbsyListNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<DbsyVO> list = null;
		int num=0;
		UserSession userSession = UserSessionUtil.getUserSession(request);
		if(userSession!=null){
			DefaultDbsyManagement dbsyManagement = this.getDbsyManagement();
			list = dbsyManagement.searchDbsyvoWithLoginUser(UserSessionUtil.getUserSession(request));
			if(list!=null){
				for(DbsyVO dbsyVO: list){
					num+=dbsyVO.getNum();
				}
			}
		}
		request.setAttribute("result", num);
		//返回列表页面
		return mapping.findForward("dbsy_list_num");
	}
	/**
	 * 
	* <p>方法名称: getDbsyManagement|描述: 获得待办事宜管理工具</p>
	* @return
	* @作者: JIANGYE
	* @日期: 2013-7-5 下午03:28:03
	*
	* @描述: 简要描述
	 */
	private DefaultDbsyManagement getDbsyManagement(){
		DefaultDbsyManagement dbsyManagement = (DefaultDbsyManagement)BeanManager.getBean(CacheConstants.DBSYMANAGEMENT.getCode());
		return dbsyManagement;
	}
}
 