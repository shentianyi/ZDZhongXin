package com.zd.csms.repository.web.jsonaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.business.model.ComplaintInfoVO;
import com.zd.csms.business.service.ComplaintService;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.tools.StringUtil;

public class ContentExistJsonAction extends JSONAction{
	RepositoryService service = new RepositoryService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		int status = Integer.parseInt(request.getParameter("status"));
		int flag = 0;
		ComplaintService service = new ComplaintService();
		ComplaintInfoVO complaintInfoVO = service.get(id);
		if (null != complaintInfoVO) {
			String solution = complaintInfoVO.getSolution();//解决方案
			String trackCondition = complaintInfoVO.getTrackCondition();//跟踪情况
			if (status == 2 && StringUtil.isEmpty(solution)) {
				flag = 1;
			}
			if (status == 3 && StringUtil.isEmpty(trackCondition)) {
				flag = 1;
			}
			super.makeJSONObject(response, callback,flag);
		}
		return null;
	}
}
