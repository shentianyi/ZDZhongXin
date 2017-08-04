package com.zd.csms.rbac.web.action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.ActionSupport;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.model.DealerGroupVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.DealerGroupService;
import com.zd.csms.rbac.web.form.DealerGroupForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class DealerGroupAction extends ActionSupport {
	/**
	 * 经销商集团查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward groupList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DealerGroupService service=new DealerGroupService() ;
		DealerGroupForm groupFrom =(DealerGroupForm)form ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList", request);
		tools.saveQueryVO(groupFrom.getName());
		List<DealerGroupVO> list = service.findList(groupFrom.getName(),tools);
		request.setAttribute("list", list);
        return mapping.findForward("group_list");
	}

	/**
	 * 经销商集团页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward groupEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    DealerGroupForm groupFrom =(DealerGroupForm)form ;
		    DealerGroupService service=new DealerGroupService() ;
		    DealerGroupVO vo = service.getGroupById(groupFrom.getDealerGroup().getId());
		    if (vo != null) {
			   groupFrom.setDealerGroup(vo);
		    }
		   return mapping.findForward("group");
	}

	/**
	 * 新增/修改经销商集团
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
		    DealerGroupForm groupFrom =(DealerGroupForm)form ;
		    DealerGroupService service=new DealerGroupService() ;
		    boolean flag = false;
		    DealerGroupVO vo = service.getGroupById(groupFrom.getDealerGroup().getId());
		    if (vo!=null) {
		    	groupFrom.getDealerGroup().setCreateTime(vo.getCreateTime());
		    	flag=service.updateGroup(groupFrom.getDealerGroup());
		    }else{
		    	flag=service.addGroup(groupFrom.getDealerGroup());
		    }
		   
		   if (flag) {
			  return groupList(mapping, form, request, response);
		   }

		   return mapping.findForward("group");
	}

	/**
	 * 删除经销商集团
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
		    DealerGroupForm groupFrom =(DealerGroupForm)form ;
		    DealerGroupService service=new DealerGroupService() ;
		    service.delGroup(groupFrom.getDealerGroup().getId());
		    return groupList(mapping, form, request, response);
	}
	
	
	/**
	 * 已分配经销商页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward dealerEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DealerGroupForm groupFrom =(DealerGroupForm)form ;
		DealerGroupService service=new DealerGroupService() ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getDealerName());
		List<DealerVO> list = service.dealerList(groupFrom.getDealerName(),groupFrom.getDealerGroup().getId(),tools);
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getDealerGroup().getId());
		return mapping.findForward("group_dealer");
	}
	
	/**
	 * 分配新经销商页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addDealerEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 DealerGroupForm groupFrom =(DealerGroupForm)form ;
		 DealerGroupService service=new DealerGroupService() ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getDealerName());
		List<DealerVO> list = service.newDealerList(groupFrom.getDealerName(),groupFrom.getDealerGroup().getId(),tools);
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getDealerGroup().getId());
		return mapping.findForward("group_add_dealer");
	}
	
	/**
	 * 分配经销商
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addDealer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 DealerGroupForm groupFrom =(DealerGroupForm)form ;
		 DealerGroupService service=new DealerGroupService() ;
		 service.addDealer(groupFrom.getDealerGroup().getId(),groupFrom.getDealerIds());
		 return dealerEntry(mapping, form, request, response);
	}
	
	
	/**
	 * 删除经销商集团下分配的经销商
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delDealer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 DealerGroupForm groupFrom =(DealerGroupForm)form ;
		 DealerGroupService service=new DealerGroupService() ;
		 service.delDealer(groupFrom.getDealerGroup().getId(),groupFrom.getDealerIds());
		 return dealerEntry(mapping, form, request, response);
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
		DealerGroupForm groupFrom =(DealerGroupForm)form ;
		DealerGroupService service=new DealerGroupService() ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getUserName());
		List<UserVO> list = service.userList(groupFrom.getUserName(),groupFrom.getDealerGroup().getId(),tools);
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getDealerGroup().getId());
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
		DealerGroupForm groupFrom =(DealerGroupForm)form ;
		DealerGroupService service=new DealerGroupService() ;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pageList",request);
		tools.saveQueryVO(groupFrom.getUserName());
		int roleId=service.getRoleIdByClientType(ClientTypeConstants.DEALERMASTER.getCode());
		List<UserVO> list=null ;
		if(roleId>0){
			 list= service.newUserList(groupFrom.getUserName(),groupFrom.getDealerGroup().getId(),roleId,tools);
		}
		request.setAttribute("list", list);
		request.setAttribute("groupId", groupFrom.getDealerGroup().getId());
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
		 DealerGroupForm groupFrom =(DealerGroupForm)form ;
		 DealerGroupService service=new DealerGroupService() ;
		 service.addUser(groupFrom.getDealerGroup().getId(),groupFrom.getUserIds());
		 return userEntry(mapping, form, request, response);
	}

	
	/**
	 * 删除经销商集团下分配的账号
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		 DealerGroupForm groupFrom =(DealerGroupForm)form ;
		 DealerGroupService service=new DealerGroupService() ;
		 service.delUser(groupFrom.getDealerGroup().getId(),groupFrom.getUserIds());
		 return userEntry(mapping, form, request, response);
	}
}
