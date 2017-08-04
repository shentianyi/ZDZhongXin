package com.zd.csms.base.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.web.form.SelectUserForm;
import com.zd.csms.base.web.model.SelectUserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SelectOutControlUserListAction extends ActionSupport {

	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SelectUserForm sform = (SelectUserForm)form;
		UserService service = new UserService();
		
		SelectUserQueryVO query = sform.getSelectUserQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("userList",request);
		tools.saveQueryVO(query);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		try {
			List<UserVO> list = service.searchUserListByPage(query, tools);
			//将查询结果设置request中，用于页面显示
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回列表页面
		return mapping.findForward("selectOutControlUser_list");
	}
}
