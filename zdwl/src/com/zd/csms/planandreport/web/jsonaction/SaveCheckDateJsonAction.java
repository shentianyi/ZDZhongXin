package com.zd.csms.planandreport.web.jsonaction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.planandreport.model.VideoPlanVO;
import com.zd.csms.planandreport.service.VideoPlanService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

public class SaveCheckDateJsonAction extends JSONAction{
	VideoPlanService service = new VideoPlanService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = 0;
		String callback = request.getParameter("callback");
		String videoPlanId = request.getParameter("id");
		String date = request.getParameter("date");
		if (StringUtil.isNotEmpty(videoPlanId)) {
			id = Integer.parseInt(videoPlanId);
		}
		if (StringUtil.isNotEmpty(date) && id > 0) {
			VideoPlanVO videoPlanVO = service.get(id);
			Date checkDate = DateUtil.StringToDate(date);
			if (null != videoPlanVO) {
				videoPlanVO.setPredictCheckDate(checkDate);//更新  videoPlan 表的预计检查日期
				service.update(videoPlanVO);
			}
		}
		super.makeJSONObject(response, callback,1 );
		return null;
	}
}
