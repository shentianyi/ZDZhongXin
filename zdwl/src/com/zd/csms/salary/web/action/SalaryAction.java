package com.zd.csms.salary.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.salary.model.BasePay;
import com.zd.csms.salary.service.SalaryService;
import com.zd.csms.salary.web.form.SalaryForm;

public class SalaryAction extends ActionSupport{

	public SalaryService service = new SalaryService();
	
	public ActionForward getSalary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BasePay basepay = service.getBasePay();
		request.setAttribute("basepay", basepay);
		return mapping.findForward("updatebasepay");
	}
	
	public ActionForward updateSalary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SalaryForm ifrom = (SalaryForm) form;
		boolean bool = service.updateBasetPay(ifrom.getBasepay());
		if(bool){
			request.setAttribute("message", "更新成功！");
		}
		return getSalary(mapping, form, request, response);
	}
}
