package com.zd.csms.repository.web.jsonaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.zd.core.JSONAction;
import com.zd.csms.repository.model.RepositoryJsonVO;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.util.DateUtil;

public class RepositoryBySupervisorIdJsonAction extends JSONAction{
	RepositoryService service = new RepositoryService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		RepositoryJsonVO json=new RepositoryJsonVO();
		RepositoryVO bean = service.loadRepositoryBySupervisorId(id);
		if(bean!=null){
			BeanUtils.copyProperties(bean, json);
			RepositoryTrainVO train=service.loadRepositoryTrainByRepositoryId(bean.getId());
			if(train!=null){
				BeanUtils.copyProperties(train, json);
				json.setIsTrain("是");
				json.setStartTimeStr(DateUtil.getStringFormatByDate(train.getStartTime(),"yyyy-MM-dd"));
				json.setEndTimeStr(DateUtil.getStringFormatByDate(train.getEndTime(),"yyyy-MM-dd"));
			}
		}
		json.setIsTrain("否");
		RosterService rosterService=new RosterService();
		RosterVO roster=rosterService.getRosterBySupervisorId(id);
		if(roster!=null){
			json.setStaffNo(roster.getStaffNo());
		}
		super.makeJSONObject(response, callback,json );
		return null;
	}
}
