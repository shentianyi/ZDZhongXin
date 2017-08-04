package com.zd.csms.base.web.jsonaction;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;

public class SuperviserJsonAction extends JSONAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String callback = request.getParameter("callback");
		
		String supervisorId = request.getParameter("supervisorId");
		
		SupervisoryService service = new SupervisoryService();
		
		RepositoryService rservice = new RepositoryService();
		
		RepositoryVO rvo = rservice.get(Integer.parseInt(supervisorId));
		
		Vector<String> set = new Vector<String>();
		
		if(rvo != null){
			SupervisorBaseInfoVO bean = service.getBaseInfo(rvo.getSupervisorId());
			
			set.add(bean.getName());
		}
		
		super.makeJSONObject(response, callback, set);
		return null;
	}
}
