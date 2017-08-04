package com.zd.csms.base.web.jsonaction;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.service.DealerSupervisorService;

public class BankNameByDealerIdJsonAction extends JSONAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String callback = request.getParameter("callback");
		
		String dealerid = request.getParameter("dealerid");
		
		String bankname = "";
		
		DealerSupervisorService dss = new DealerSupervisorService();
		BankService bs = new BankService();
		
		DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
		dsquery.setDealerId(Integer.parseInt(dealerid));
		List<DealerSupervisorVO> dsList = dss.searchDealerSupervisorList(dsquery);
		if(dsList != null && dsList.size()>0){
			DealerSupervisorVO dsvo = dsList.get(0);
			int bankid = dsvo.getBankId();
			BankVO bank = bs.get(bankid);
			if(bank != null){
				bankname = bank.getBankFullName();
			}
			
		}
		
		
		Vector<String> set = new Vector<String>();
		set.add(bankname);
		
		super.makeJSONObject(response, callback, set);
		
		return null;
	}
}
