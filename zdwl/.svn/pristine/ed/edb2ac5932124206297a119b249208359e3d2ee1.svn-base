package com.zd.csms.message.approval;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.message.dao.MessageDAOFactory;

/**
 * 查询当前角色的全部需要审批的数量
 * @author licheng
 *
 */
public class ApprovalTotalCountJsonAction  extends JSONAction{
	private ApprovalDao dao = MessageDAOFactory.getApprovalDao();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int count = dao.getApprovalToalCount(request);
		super.makeJSONObject(response, callback, count);
		return null;
	}
}
