package com.zd.csms.roster.web.jsonaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.roster.model.RosterQueryBean;
import com.zd.csms.roster.service.RosterService;

/**
 * 根据花名册id查询对应的监管员信息
 * @author licheng
 *
 */
public class RosterJsonAction extends JSONAction{
	private RosterService service = new RosterService();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		RosterQueryBean bean = service.findRosterById(id);
		super.makeJSONObject(response, callback, bean);
		return null;
	}
}
