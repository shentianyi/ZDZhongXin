package com.zd.csms.set.web.jsonAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.dao.SetDAOFactory;

public class DealerDockValidateAction extends JSONAction{
	private IDistribsetDAO dao = SetDAOFactory.getDistribsetDAO();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		String id = request.getParameter("id");
		boolean bool = dao.validateDealer(Integer.parseInt(id));
		super.makeJSONObject(response, callback, bool);
		return null;
	}
}
