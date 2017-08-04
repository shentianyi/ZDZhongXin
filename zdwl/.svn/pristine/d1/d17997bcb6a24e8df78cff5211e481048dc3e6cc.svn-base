package com.zd.csms.region.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.region.model.RegionVO;
import com.zd.csms.region.service.RegionService;

public class FindRegionJsonByParentIdAction extends JSONAction{
	RegionService service = new RegionService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
 		int parentId = Integer.parseInt(request.getParameter("parentId"));
		List<RegionVO> regions=service.findRegionByParentId(parentId);
		super.makeJSONObject(response, callback,regions );
		return null;
	}
}
