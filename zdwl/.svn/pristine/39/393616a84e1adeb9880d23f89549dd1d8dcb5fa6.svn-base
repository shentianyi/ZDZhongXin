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
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.tools.StringUtil;

public class SupervisorJsonAction extends JSONAction{
	private BusinessTransferService service = new BusinessTransferService();
	private DealerSupervisorService dsService = new DealerSupervisorService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		SupervisorJsonBean bean = service.getSupervisorByRepositoryId(id);
		
		String dealerId = request.getParameter("dealerId");
		if(StringUtil.isNotEmpty(dealerId)){
			DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
			dsQuery.setDealerId(Integer.parseInt(dealerId));
			dsQuery.setSupervisorId(id);
			List<DealerSupervisorVO> dsList = dsService.searchDealerSupervisorList(dsQuery);
			if(dsList!=null&&dsList.size()>0){
				DealerSupervisorVO dsVO = dsList.get(0);
				bean.setQq(dsVO.getQq());
				bean.setQqPwd(dsVO.getQqPwd());
				bean.setSupervisorAttribute(dsVO.getSupervisorAttribute());
				bean.setSupervisorSource(dsVO.getSupervisorSource());
			}
			
		}
		DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
		DealerService dealerService=new DealerService();
		dsQuery.setSupervisorId(id);
		String dealerName="";
		String bankName="";
		String brandName="";
		List<DealerSupervisorVO> dsList = dsService.searchDealerSupervisorList(dsQuery);
		if(dsList!=null && dsList.size()>0){
			for(DealerSupervisorVO ds:dsList){
				DealerQueryBean dealer=dealerService.detailInfo(ds.getDealerId(),ClientTypeConstants.SUPERVISORY.getCode());
				dealerName=dealerName+" "+dealer.getDealerName();
				bankName=bankName+" "+dealer.getBankName();
				brandName=brandName+" "+dealer.getBrand();
			}
		}
		bean.setDealerName(dealerName);
		bean.setBankName(bankName);
		bean.setBrandName(brandName);
		super.makeJSONObject(response, callback,bean );
		return null;
	}
}
