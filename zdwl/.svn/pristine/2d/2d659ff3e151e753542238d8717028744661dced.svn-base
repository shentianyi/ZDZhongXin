package com.zd.csms.marketing.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;

/**
 * 经销商名录表
 * @author licheng
 *
 */
public class DealerJsonAction extends JSONAction {
	private DealerService serivce = new DealerService();

	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		DealerQueryVO query = new DealerQueryVO();
		List<DealerVO> list = serivce.findList(query);
		super.makeJSONObject(response, callback, list);
		return null;
	}
}
