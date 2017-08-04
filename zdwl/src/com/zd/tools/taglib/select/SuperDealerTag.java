package com.zd.tools.taglib.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;

@SuppressWarnings("serial")
public class SuperDealerTag extends TagSupport {

	private Object bid;

	public int doStartTag() throws JspException {
		
		int id = (Integer)this.bid;
		
		String name = "";
		
		try {
			UserService us = new UserService();
			UserVO user = us.loadUserById(id);
			
			DealerSupervisorService dss = new DealerSupervisorService();
			DealerService ds = new DealerService();
			DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
			dsquery.setSupervisorId(user.getClient_id());
			List<DealerSupervisorVO> dsList = dss.searchDealerSupervisorList(dsquery);
			if(dsList != null && dsList.size()>0){
				DealerSupervisorVO dsvo = dsList.get(0);
				int dealerid = dsvo.getDealerId();
				DealerVO dealer = ds.loadDealerById(dealerid);
				name = dealer.getDealerName();
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
