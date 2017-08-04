package com.zd.csms.base.web.jsonaction;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;

public class ChangeDraftJsonAction extends JSONAction {
	private static DraftService ds = new DraftService();
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String callback = request.getParameter("callback");
		String draftNum = new String(request.getParameter("draftNum").getBytes("iso-8859-1"), "utf-8");
		DealerService dealerservice = new DealerService();
		
		DraftVO draft = ds.findDraftByDraftNum(draftNum);
		int distribid = draft.getDistribid();
		DealerVO dealer = dealerservice.get(distribid);
		
		String fi = dealerservice.getBankNameByDealerId(distribid);
		
		String dealername = dealer.getDealerName();
		
		Vector<String> set = new Vector<String>();
		set.add(dealername+"||"+fi);
		
		super.makeJSONObject(response, callback, set);
		
		return null;
	}
}
