package com.zd.csms.marketing.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.web.form.MarketApprovalForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 市场部审批业务
 * @author licheng
 *
 */
public class ApprovalAction extends ActionSupport{
	MarketApprovalSerivce service = new MarketApprovalSerivce();
	
	private MarketApprovalForm getMarketApprovalForm(ActionForm form){
		MarketApprovalForm f = (MarketApprovalForm) form;
		return f;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketApprovalVO marketApproval = getMarketApprovalForm(form).getMarketApproval();
		service.add(marketApproval);
		return null;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketApprovalVO marketApproval = getMarketApprovalForm(form).getMarketApproval();
		service.update(marketApproval);
		return null;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketApprovalVO marketApproval = getMarketApprovalForm(form).getMarketApproval();
		service.delete(marketApproval.getId());
		return null;
	}
	
	/**
	 * 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketApprovalQueryVO query = getMarketApprovalForm(form).getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("marketApprovalList", request);
		tools.saveQueryVO(query);
		request.setAttribute("marketApprovalList",service.findList(query, tools) ); 
		return mapping.findForward("");
	}
}
