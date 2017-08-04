package com.zd.csms.bank.web.action;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.bank.model.BankManagerVO;
import com.zd.csms.bank.service.BankManagerService;
import com.zd.csms.bank.web.form.BankManagerForm;

public class BankManagerAction extends ActionSupport{
	private BankManagerService service = new BankManagerService();
	
	
	/**
	 * 新增银行客户经理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}
	
	/**
	 * 新增银行客户经理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankManagerForm iform = (BankManagerForm) form;
		service.add(iform.getBankManager());
		return findList(mapping, iform, request, response);
	}
	
	
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankManagerForm iform = (BankManagerForm) form;
		iform.setBankManager(service.get(iform.getBankManager().getId()));
		
		
		return mapping.findForward("update");
	}
	
	/**
	 * 更新银行客户经理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankManagerForm iform = (BankManagerForm) form;
		service.update(iform.getBankManager());
		return findList(mapping, iform, request, response);
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankManagerForm iform = (BankManagerForm) form;
		service.delete(iform.getBankManager().getId());
		return findList(mapping, iform, request, response);
	}
	
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankManagerForm iform = (BankManagerForm) form;
		List<BankManagerVO> list = service.findList(iform.getQuery());
		request.setAttribute("list", list);
		return mapping.findForward("list");
	}
}
