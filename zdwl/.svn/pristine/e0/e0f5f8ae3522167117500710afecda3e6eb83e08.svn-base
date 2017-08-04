package com.zd.csms.marketing.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.marketing.querybean.DealerBankQueryBean;
import com.zd.csms.marketing.service.DealerService;

public class findDealerBankByRepId extends JSONAction{
	private DealerService ds = new DealerService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		List<DealerBankQueryBean> dsList = ds.findDealerListByRepId(id);
		super.makeJSONObject(response, callback,dsList );
		return null;
	}
}
