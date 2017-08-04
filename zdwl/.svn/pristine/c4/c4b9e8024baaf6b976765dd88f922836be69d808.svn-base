package com.zd.csms.supervisorymanagement.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisorymanagement.model.MailCostJsonVO;
import com.zd.tools.StringUtil;

public class MailCostJsonAction extends JSONAction {

	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		RosterService rservice = new RosterService();
		DealerSupervisorService dsservice = new DealerSupervisorService();
		DealerService dservice = new DealerService();
		String callback = request.getParameter("callback");
		String repositoryIdStr = request.getParameter("id");
		
		int repositoryId = 0;
		if(!StringUtil.isEmpty(repositoryIdStr)){
			repositoryId = Integer.parseInt(repositoryIdStr);
		}
		RosterQueryVO rqvo = new RosterQueryVO();
		rqvo.setRepositoryId(repositoryId);
		List<RosterVO> rList = rservice.searchRosterList(rqvo);
		
		//员工工号
		String gh = "";
		if(rList != null && rList.size()>0){
			RosterVO rvo = rList.get(0);
			gh =rvo.getStaffNo();
		}
		
		//经销商
		String jxs = "";
		String jrjg = "";
		DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
		dsquery.setSupervisorId(repositoryId);
		List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
		if(dsList != null && dsList.size()>0){
			for(DealerSupervisorVO ds:dsList){
				DealerVO dvo = dservice.loadDealerById( ds.getDealerId());
				if(dvo != null){
					jxs =jxs+" "+ dvo.getDealerName();
				}
				jrjg =jrjg+" "+ dservice.getBankNameByDealerId( ds.getDealerId());
			}
		}
		
		MailCostJsonVO mcvo = new MailCostJsonVO();
		mcvo.setGh(gh);
		mcvo.setJxs(jxs);
		mcvo.setJrjg(jrjg);
		
		super.makeJSONObject(response, callback, mcvo);
		return null;
	}
}
