package com.zd.csms.supervisorymanagement.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.service.HandoverLogService;

/**
 * 根据经销商ID查询交接记录表信息
 * @author macongcong
 *
 */
public class getHandoverLogByDealerIdJsonAction extends JSONAction{
	private HandoverLogService service = new HandoverLogService();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		List<HandoverLogVO> bean = service.getHandoverLogByDealerId(id);
		super.makeJSONObject(response, callback, bean);
		return null;
	}
}
