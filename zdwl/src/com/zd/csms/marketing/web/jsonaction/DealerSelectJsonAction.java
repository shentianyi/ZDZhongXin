package com.zd.csms.marketing.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.zd.core.JSONAction;
import com.zd.csms.bank.model.BankQueryVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.marketing.dao.IDealerDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.MarketProjectCirculationVO;
import com.zd.csms.marketing.querybean.DealerJsonBean;
import com.zd.csms.marketing.service.MarketProjectCirculationService;

public class DealerSelectJsonAction extends JSONAction{
	MarketProjectCirculationService service = new MarketProjectCirculationService();
	BankService bankService = new BankService();
	IDealerDAO dao = MarketFactory.getIDealerDAO();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int dealerId = Integer.parseInt(request.getParameter("dealerId"));
		String callback = request.getParameter("callback");
		MarketProjectCirculationVO bean = service.getByDealerId(dealerId);
		DealerJsonBean json = new DealerJsonBean();
		BeanUtils.copyProperties(bean, json);
		DealerQueryVO query = new DealerQueryVO();
		BankQueryVO bankQuery = new BankQueryVO();
		query.setIds(new Integer[]{bean.getDealerId()});
		List<DealerBankVO> bankList = dao.findBankListByIds(query);
		StringBuffer bankName = new StringBuffer();
		if(bankList!=null){
			for (DealerBankVO db : bankList) {
				int bankId = db.getBankId();
				bankQuery.setId(bankId);
				bankName.append(bankService.findBankNameById(bankQuery).replace(",", "/")+",");
			}
			bankName.deleteCharAt(bankName.length()-1);
		}
		json.setBankName(bankName.toString());
		super.makeJSONObject(response, callback, json);
		return null;
	}
}
