package com.zd.csms.message.url;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;

public class NostockNodraftMessage extends ActionSupport {

	public ActionForward nostocknodraft(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String dealerid = request.getParameter("dealerid");
			DealerService ds = new DealerService();
			DealerQueryVO dquery = new DealerQueryVO();
			dquery.setId(Integer.parseInt(dealerid));
			
			List<DealerQueryBean> querBeanList = ds.findDealerList(dquery);
			DealerQueryBean dqb = querBeanList.get(0);
			
			request.setAttribute("dealer", dqb);
		} catch (Exception e) {
		}
		//返回列表页面
		return mapping.findForward("nostocknodraft");
	}
}
