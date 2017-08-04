package com.zd.tools.taglib.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.service.DealerSupervisorService;

@SuppressWarnings("serial")
public class BankNameTag extends TagSupport {

	private Object bid;

	public int doStartTag() throws JspException {
		
		int id = (Integer)this.bid;
		
		String name = "";
		
		try {
			
			DealerSupervisorService dss = new DealerSupervisorService();
			BankService bs = new BankService();
			DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
			dsquery.setDealerId(id);
			List<DealerSupervisorVO> dsList = dss.searchDealerSupervisorList(dsquery);
			if(dsList != null && dsList.size()>0){
				DealerSupervisorVO dsvo = dsList.get(0);
				if(dsvo != null){
					int bankid = dsvo.getBankId();
					BankVO bvo = bs.get(bankid);
					if(bvo != null){
						name = bvo.getBankFullName();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try{
			pageContext.getOut().write(name);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}

	public Object getBid() {
		return bid;
	}

	public void setBid(Object bid) throws JspException {
		this.bid = (Integer)ExpressionEvaluatorManager.evaluate("bid", bid.toString(), Integer.class, this, pageContext); 
	}
	
}
