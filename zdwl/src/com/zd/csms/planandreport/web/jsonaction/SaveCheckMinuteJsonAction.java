package com.zd.csms.planandreport.web.jsonaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.planandreport.model.VideoPlanVO;
import com.zd.csms.planandreport.service.VideoPlanService;
import com.zd.tools.StringUtil;

/**
 * 更新预计检查分钟
 * @author wdc
 *
 */
public class SaveCheckMinuteJsonAction extends JSONAction{
	VideoPlanService service = new VideoPlanService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = 0;
		int minuteNum = 0;
		String callback = request.getParameter("callback");
		String videoPlanId = request.getParameter("id");
		String minutes = request.getParameter("num");
		if (StringUtil.isNotEmpty(videoPlanId)) {
			id = Integer.parseInt(videoPlanId);
		}
		if (StringUtil.isNotEmpty(minutes)) {
			minuteNum = Integer.parseInt(minutes);
		}
		if (id > 0 && minuteNum >= 0) {
			VideoPlanVO videoPlanVO = service.get(id);
			if (null != videoPlanVO) {
				videoPlanVO.setCheckMinute(minuteNum);
				service.update(videoPlanVO);
			}
		}
		
		super.makeJSONObject(response, callback,1 );
		return null;
	}
}
