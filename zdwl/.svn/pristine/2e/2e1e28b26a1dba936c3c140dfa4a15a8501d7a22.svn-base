package com.zd.csms.marketing.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.service.DealerSupervisorService;

public class getDealerByRepositoryId extends JSONAction{
	private DealerSupervisorService dsService = new DealerSupervisorService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		//DealerSupervisorVO dsVO=null;
		DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
		dsQuery.setSupervisorId(id);
		List<DealerSupervisorVO> dsList = dsService.searchDealerSupervisorList(dsQuery);
		/*if(dsList!=null&&dsList.size()>0){
			dsVO= dsList.get(0);
		}*/
		super.makeJSONObject(response, callback,dsList );
		return null;
	}
}
