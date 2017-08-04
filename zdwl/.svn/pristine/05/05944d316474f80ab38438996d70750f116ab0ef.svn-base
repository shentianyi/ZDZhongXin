package com.zd.csms.bank.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.bank.model.BankManagerQueryVO;
import com.zd.csms.bank.model.BankManagerVO;
import com.zd.csms.bank.service.BankManagerService;

/**
 *根据银行的主键Id获取对应的客户号
 */
public class BankManagerByBankId extends JSONAction{
	BankManagerService service = new BankManagerService();
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		String bankId = request.getParameter("bankId");
		BankManagerQueryVO query = new BankManagerQueryVO();
		query.setBankId(Integer.parseInt(bankId));
		List<BankManagerVO> list = service.findList(query);
		super.makeJSONObject(response, callback, list);
		return null;
	}
}
