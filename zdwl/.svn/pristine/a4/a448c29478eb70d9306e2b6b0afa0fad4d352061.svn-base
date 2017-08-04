package com.zd.csms.roster.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.tools.StringUtil;

/**
 * 根据部门id查询对应部门人员信息
 */
public class ProcessingJsonAction extends JSONAction{
	private UserService service = new UserService();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		if(StringUtil.isNotEmpty(request.getParameter("id"))){
			int clienttypeId = Integer.parseInt(request.getParameter("id"));
			UserQueryVO uq = new UserQueryVO();
			uq.setClienttype(clienttypeId);
			List<UserVO> userList = service.searchUserList(uq);
			super.makeJSONObject(response, callback, userList);
		}
		return null;
	}
}
