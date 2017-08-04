package com.zd.csms.message.url;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;

public class BillingNoCarMessage extends ActionSupport {

	public ActionForward billno7(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String draft_num = request.getParameter("draftnum");
		DraftService ds = new DraftService();
		DraftQueryVO dquery = new DraftQueryVO();
		dquery.setDraft_num(draft_num);
		List<DraftVO> dList = ds.searchDraftList(dquery);
		if(dList != null && dList.size()>0){
			DraftVO draft = dList.get(0);
			request.setAttribute("draft", draft);
		}
		
		//返回列表页面
		return mapping.findForward("billno7");
	}
	
	public ActionForward billno15(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String draft_num = request.getParameter("draftnum");
		DraftService ds = new DraftService();
		DraftQueryVO dquery = new DraftQueryVO();
		dquery.setDraft_num(draft_num);
		List<DraftVO> dList = ds.searchDraftList(dquery);
		if(dList != null && dList.size()>0){
			DraftVO draft = dList.get(0);
			request.setAttribute("draft", draft);
		}
		
		//返回列表页面
		return mapping.findForward("billno15");
	}
}
