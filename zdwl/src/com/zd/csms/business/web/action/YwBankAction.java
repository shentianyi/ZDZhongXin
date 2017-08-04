package com.zd.csms.business.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.business.model.YwBankBrandVO;
import com.zd.csms.business.model.YwBankQueryBean;
import com.zd.csms.business.model.YwBankQueryVO;
import com.zd.csms.business.model.YwBankVO;
import com.zd.csms.business.service.YwBankService;
import com.zd.csms.business.web.form.YwBankForm;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class YwBankAction extends ActionSupport{
	private YwBankService service = new YwBankService();
	
	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankVO bean = ywBankForm.getYwBank();
		service.add(bean);
		return findList(mapping, ywBankForm, request, response);
	}

	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankVO bean = service.get(ywBankForm.getYwBank().getId());
		ywBankForm.setYwBank(bean);

		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankVO bean = ywBankForm.getYwBank();

		service.update(bean);
		return findList(mapping, ywBankForm, request, response);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankVO bean = ywBankForm.getYwBank();
		service.delete(bean.getId());
		return findList(mapping, ywBankForm, request, response);
	}
	
	

	/**
	 * 分页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankQueryVO query = ywBankForm.getQuery();
		int userId = query.getUserId();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("ywBankList", request);
		tools.saveQueryVO(query);
		query.setUserId(userId);
		List<YwBankQueryBean> list = service.findList(query, tools);
		request.setAttribute("list", list);
		return mapping.findForward("list");
	}
	
	
	public ActionForward userList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankQueryVO query = ywBankForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("ywUserList", request);
		tools.saveQueryVO(query);
		List<UserVO> list = service.ywUserList(query, tools);
		request.setAttribute("list", list);
		return mapping.findForward("ywUserList");
	}
	
	public ActionForward brandList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankQueryVO query = ywBankForm.getQuery();
		int userId = query.getUserId();
		int brandType = query.getBrandType();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("ywbrandList", request);
		tools.saveQueryVO(query);
		query.setUserId(userId);
		query.setBrandType(brandType);
		List<BrandVO> list = service.brandList(query, tools);
		request.setAttribute("list", list);
		
		if(brandType==1){
			return mapping.findForward("brandList");
		}else{
			return mapping.findForward("brandoutList");
		}
		
	}
	
	public ActionForward addBrands(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankQueryVO query = ywBankForm.getQuery();
		String[] ids = request.getParameterValues("brandIds");
		if(ids!=null&&ids.length>0)
			for (String brandId : ids) {
				YwBankBrandVO vo = new YwBankBrandVO();
				vo.setYwBankId(query.getId());
				vo.setBrandId(Integer.parseInt(brandId));
				service.addBrand(vo);
			}
		query.setBrandType(2);
		return brandList(mapping, ywBankForm, request, response);
	}
	
	public ActionForward deleteBrands(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YwBankForm ywBankForm = (YwBankForm) form;
		YwBankQueryVO query = ywBankForm.getQuery();
		String[] ids = request.getParameterValues("brandIds");
		if(ids!=null)
			service.deleteBrand(ids, query.getId());
		query.setBrandType(1);
		return brandList(mapping, ywBankForm, request, response);
	}
	
}
