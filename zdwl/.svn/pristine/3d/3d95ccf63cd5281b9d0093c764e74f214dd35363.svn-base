package com.zd.csms.bank.web.jsonaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.bank.service.BankService;

public class BankNameValidJsonAction extends JSONAction{
	private BankService bankService = new BankService();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String callback = request.getParameter("callback");
		String bankName = request.getParameter("bankName");
		int parentId = Integer.parseInt(request.getParameter("parentId"));
		boolean flag =bankService.findCountByBankName(bankName, parentId)>0;
		super.makeJSONObject(response, callback, flag);
		return null;
	}
}
