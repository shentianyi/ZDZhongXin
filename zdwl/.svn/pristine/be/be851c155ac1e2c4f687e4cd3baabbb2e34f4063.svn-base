package com.zd.csms.rbac.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.model.brand.BrandGroupVO;
import com.zd.csms.rbac.service.BrandGroupService;
import com.zd.csms.rbac.web.form.BrandGroupForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class BrandGroupAction extends ActionSupport {
	/**
	 * 品牌集体查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward groupList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BrandGroupService service=new BrandGroupService() ;
		BrandGroupForm groupFrom =(BrandGroupForm)form ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList", request);
		tools.saveQueryVO(groupFrom.getName());
		List<BrandGroupVO> list = service.findList(groupFrom.getName(),tools);
		request.setAttribute("list", list);
        return mapping.findForward("group_list");
	}

	/**
	 * 品牌集体页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward groupEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    BrandGroupForm groupFrom =(BrandGroupForm)form ;
		    BrandGroupService service=new BrandGroupService() ;
		    BrandGroupVO vo = service.getGroupById(groupFrom.getBrandGroup().getId());
		    if (vo != null) {
			   groupFrom.setBrandGroup(vo);
		    }
		   return mapping.findForward("group");
	}

	/**
	 * 新增/修改品牌集体
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addAndUpdGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    BrandGroupForm groupFrom =(BrandGroupForm)form ;
		    BrandGroupService service=new BrandGroupService() ;
		    boolean flag = false;
		    BrandGroupVO vo = service.getGroupById(groupFrom.getBrandGroup().getId());
		    if (vo!=null) {
		    	groupFrom.getBrandGroup().setCreateTime(vo.getCreateTime());
		    	flag=service.updateGroup(groupFrom.getBrandGroup());
		    }else{
		    	flag=service.addGroup(groupFrom.getBrandGroup());
		    }
		   
		   if (flag) {
			  return groupList(mapping, form, request, response);
		   }

		   return mapping.findForward("group");
	}

	/**
	 * 删除品牌集体
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward delGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    BrandGroupForm groupFrom =(BrandGroupForm)form ;
		    BrandGroupService service=new BrandGroupService() ;
		    service.delGroup(groupFrom.getBrandGroup().getId());
		    return groupList(mapping, form, request, response);
	}
	
	
	/**
	 * 已分配品牌页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward brandEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BrandGroupForm groupFrom =(BrandGroupForm)form ;
		BrandGroupService service=new BrandGroupService();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getBrandName());
		List<BrandVO> list = service.brandList(groupFrom.getBrandName(),groupFrom.getBrandGroup().getId(),tools);
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getBrandGroup().getId());
		return mapping.findForward("group_brand");
	}
	
	/**
	 * 分配新品牌页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addBrandEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 BrandGroupForm groupFrom =(BrandGroupForm)form ;
		 BrandGroupService service=new BrandGroupService() ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getBrandName());
		List<BrandVO> list = service.newBrandList(groupFrom.getBrandName(),groupFrom.getBrandGroup().getId(),tools);
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getBrandGroup().getId());
		return mapping.findForward("group_add_brand");
	}
	
	/**
	 * 分配品牌
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addBrand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 BrandGroupForm groupFrom =(BrandGroupForm)form;
		 BrandGroupService service=new BrandGroupService();
		 service.addBrand(groupFrom.getBrandGroup().getId(),groupFrom.getBrandIds());
		 return brandEntry(mapping, form, request, response);
	}
	
	
	/**
	 * 删除品牌集体下分配的品牌
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delDealer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 BrandGroupForm groupFrom =(BrandGroupForm)form ;
		 BrandGroupService service=new BrandGroupService() ;
		 service.delDealer(groupFrom.getBrandGroup().getId(),groupFrom.getBrandIds());
		 return brandEntry(mapping, form, request, response);
	}
	
	/**
	 * 已分配账号页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward userEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BrandGroupForm groupFrom =(BrandGroupForm)form ;
		BrandGroupService service=new BrandGroupService() ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getUserName());
		List<UserVO> list = service.userList(groupFrom.getUserName(),groupFrom.getBrandGroup().getId(),tools);
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getBrandGroup().getId());
		return mapping.findForward("group_user");
	}
	
	
	/**
	 * 分配新账号页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addUserEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BrandGroupForm groupFrom =(BrandGroupForm)form ;
		BrandGroupService service=new BrandGroupService() ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getUserName());
		int roleId=service.getRoleIdByClientType(ClientTypeConstants.BRANDMASTER.getCode());
		List<UserVO> list=null ;
		if(roleId>0){
			 list= service.newUserList(groupFrom.getUserName(),groupFrom.getBrandGroup().getId(),roleId,tools);
		}
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getBrandGroup().getId());
		return mapping.findForward("group_add_user");
	}
	
	/**
	 * 分配账号
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		 BrandGroupForm groupFrom =(BrandGroupForm)form ;
		 BrandGroupService service=new BrandGroupService() ;
		 service.addUser(groupFrom.getBrandGroup().getId(),groupFrom.getUserIds());
		 return userEntry(mapping, form, request, response);
	}

	
	/**
	 * 删除品牌集体下分配的账号
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		 BrandGroupForm groupFrom =(BrandGroupForm)form ;
		 BrandGroupService service=new BrandGroupService() ;
		 service.delUser(groupFrom.getBrandGroup().getId(),groupFrom.getUserIds());
		 return userEntry(mapping, form, request, response);
	}
}
