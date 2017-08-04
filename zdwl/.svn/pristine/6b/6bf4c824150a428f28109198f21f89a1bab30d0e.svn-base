package com.zd.csms.message.url;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.supervisory.querybean.CarOperationBean;
import com.zd.csms.supervisory.service.AbnormalService;
import com.zd.csms.supervisory.service.CarOperationService;

public class Continuity3outMessage extends ActionSupport {

	public ActionForward continuity3out(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String dealerid = request.getParameter("dealerid");
			DealerService ds = new DealerService();
			DealerSupervisorService dsservice = new DealerSupervisorService();
			CarOperationService coservice = new CarOperationService();
			AbnormalService aservice = new AbnormalService();
			DealerQueryVO dquery = new DealerQueryVO();
			dquery.setId(Integer.parseInt(dealerid));
			
			List<DealerQueryBean> querBeanList = ds.findDealerList(dquery);
			DealerQueryBean dqb = querBeanList.get(0);
			
			request.setAttribute("dealer", dqb);
			
			DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
			dsquery.setDealerId(Integer.parseInt(dealerid));
			List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
			DealerSupervisorVO dsvo = dsList.get(0);
			int userid = dsvo.getRepositoryId();
			List<CarOperationBean> shangyueList = coservice.searchshangyue(userid,3);
			if(shangyueList != null && shangyueList.size()>0){
				for(CarOperationBean sy : shangyueList){
					int sum = sy.getSum();
					BigDecimal yue = new BigDecimal(30);
					BigDecimal bsum = new BigDecimal(sum);
					BigDecimal ypj = bsum.divide(yue,0,BigDecimal.ROUND_HALF_EVEN);
					request.setAttribute("ypj", ypj.toString());
				}
			}
			List<CarOperationBean> benList = coservice.searchday(userid,3);
			int ben = benList.size();
			List<CarOperationBean> zuoList = coservice.searchzuoday(userid,3);
			int zuo = zuoList.size();
			List<CarOperationBean> qianList = coservice.searchqianday(userid,3);
			int qian = qianList.size();
			
			int zj = ben+zuo+qian;
			BigDecimal three = new BigDecimal(3);
			BigDecimal threesum = new BigDecimal(zj);
			BigDecimal threepj = threesum.divide(three,0,BigDecimal.ROUND_HALF_EVEN);
			request.setAttribute("threepj", threepj.toString());
			
			int yc = aservice.findCountByDealerId(Integer.parseInt(dealerid));
			request.setAttribute("yc", yc);
		} catch (Exception e) {
		}
		//返回列表页面
		return mapping.findForward("continuity3out");
	}
}
