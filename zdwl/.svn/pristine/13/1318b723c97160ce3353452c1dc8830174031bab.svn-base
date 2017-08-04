package com.zd.csms.repository.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.service.RepositoryService;

/**
 * 根据储备库ID查询可驻派城市列表，给花名册选择驻派地区使用
 * @author licheng
 *
 */
public class FindRepositoryDispatchCityJsonByRepositoryIdAction extends JSONAction{
	
	RepositoryService service = new RepositoryService();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int repositoryId = Integer.parseInt(request.getParameter("repositoryId"));
		List<RepositoryDispatchCityVO> bean = service.getRepositoryDispatchCityByRepositoryId(repositoryId);
		super.makeJSONObject(response, callback, bean);
		return null;
	}
}
