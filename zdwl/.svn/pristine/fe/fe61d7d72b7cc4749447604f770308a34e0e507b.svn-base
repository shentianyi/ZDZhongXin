package com.zd.csms.message.url;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.AbnormalService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.DateUtil;

public class Move30dayMessage extends ActionSupport {

	public ActionForward move30day(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String dealerid = request.getParameter("dealerid");
			DealerService ds = new DealerService();
			SuperviseImportService siservice = new SuperviseImportService();
			DraftService drservice = new DraftService();
			AbnormalService aservice = new AbnormalService();
			DealerQueryVO dquery = new DealerQueryVO();
			dquery.setId(Integer.parseInt(dealerid));
			
			List<DealerQueryBean> querBeanList = ds.findDealerList(dquery);
			DealerQueryBean dqb = querBeanList.get(0);
			
			request.setAttribute("dealer", dqb);
			
			int yc = aservice.findCountByDealerId(Integer.parseInt(dealerid));
			request.setAttribute("yc", yc);
			
			DraftQueryVO drquery = new DraftQueryVO();
			drquery.setDistribid(Integer.parseInt(dealerid));
			List<DraftVO> drList = drservice.searchDraftList(drquery);
			
			List<SuperviseImportVO> ydList = new ArrayList<SuperviseImportVO>();
			List<SuperviseImportVO> sumList = new ArrayList<SuperviseImportVO>();
			
			if(drList != null && drList.size()>0){
				for(DraftVO drvo : drList){
					SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
					siquery.setDraft_num(drvo.getDraft_num());
					siquery.setState(4);
					List<SuperviseImportVO> siList = siservice.searchSuperviseImportList(siquery);
					if(siList != null && siList.size()>0){
						for(SuperviseImportVO sivo : siList){
							sumList.add(sivo);
							int day = DateUtil.daysBetween(sivo.getMovetime(), new Date());
							if(day > 30){
								ydList.add(sivo);
							}
						}
					}
				}
			}
			request.setAttribute("yds", ydList.size());
			
			BigDecimal yd = new BigDecimal(ydList.size());
			BigDecimal sum = new BigDecimal(sumList.size());
			request.setAttribute("bl", yd.divide(sum,2,RoundingMode.HALF_UP).toString());
			
		} catch (Exception e) {
		}
		//返回列表页面
		return mapping.findForward("move30day");
	}
}
