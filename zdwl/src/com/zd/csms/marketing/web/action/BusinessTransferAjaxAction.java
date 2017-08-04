package com.zd.csms.marketing.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.JSONAction;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.service.BusinessTransferService;

public class BusinessTransferAjaxAction extends JSONAction{
	BusinessTransferService service = new BusinessTransferService();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		BusinessTransferVO businessTransferVO = service.get(id);
		super.makeJSONObject(response, callback,businessTransferVO);
		return null;
	}

	
}
