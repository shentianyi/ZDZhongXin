package com.zd.csms.region.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.region.contants.RegionLevelContant;
import com.zd.csms.region.model.RegionQueryVO;
import com.zd.csms.region.model.RegionVO;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.region.web.form.RegionForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RegionAction extends ActionSupport{
	
	RegionService regionService=new RegionService();
	
	public ActionForward addRegionEntity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RegionForm regionForm =(RegionForm) form;
		RegionVO region=regionForm.getRegion();
		//将查询结果设置request中，用于页面显示
		RegionVO parent=regionService.load(region.getParentId());
		request.setAttribute("parent", parent);
		if(parent!=null){
			request.setAttribute("parentId", parent.getId());
			request.setAttribute("parentLevel", parent.getRegionLevel()+1);
		}else{
			request.setAttribute("parentId", 0);
			request.setAttribute("parentLevel", 0);
		}
		//返回新增页面
		return mapping.findForward("add_region");
	}
	
	public ActionForward addRegion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RegionForm regionForm =(RegionForm) form;
		RegionVO region=regionForm.getRegion();
		UserSession user = UserSessionUtil.getUserSession(request);
		region.setCreateUser(user.getUser().getId());
		region.setCreateTime(new Date());
		region.setStatus(StateConstants.USING.getCode());
		if(region.getParentId()==0 || region.getParentId()==-1){
			region.setRegionLevel(RegionLevelContant.FIRST_LEVEL.getCode());
		}else{
			RegionVO parentRegion=regionService.load(region.getParentId());
			if(parentRegion.getRegionLevel()==RegionLevelContant.FIRST_LEVEL.getCode()){
				region.setRegionLevel(RegionLevelContant.SECOND_LEVEL.getCode());
			}else if(parentRegion.getRegionLevel()==RegionLevelContant.SECOND_LEVEL.getCode()){
				region.setRegionLevel(RegionLevelContant.THIRD_LEVEL.getCode());
			}
		}
		boolean flag=regionService.add(region);
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), regionService.getExecuteMessage());
		if(flag){
			regionForm.getQuery().setParentId(region.getParentId());
			regionForm.getQuery().setRegionLevel(region.getRegionLevel());
			//新增成功返回列表页
			return regionPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addRegionEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updRegionEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RegionForm regionForm =(RegionForm) actionform;
		RegionVO region=regionService.load(regionForm.getRegion().getId());
		//对象不存在时返回列表页
		if(region==null){
			return regionPageList(mapping,regionForm,request,response);
		}
		regionForm.setRegion(region);
		RegionVO parent=regionService.load(region.getParentId());
		request.setAttribute("parent", parent);
		if(parent!=null){
			request.setAttribute("parentId", parent.getId());
			request.setAttribute("parentLevel", parent.getRegionLevel()+1);
		}else{
			request.setAttribute("parentId", 0);
			request.setAttribute("parentLevel", 0);
		}
		request.setAttribute("region", region);
		//返回新增页面
		return mapping.findForward("upd_region");
	}
	
	public ActionForward updRegion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RegionForm regionForm =(RegionForm) form;
		
		RegionVO region=regionForm.getRegion();
		region.setLastModifiedTime(new Date());
		UserSession user = UserSessionUtil.getUserSession(request);
		region.setLastModifiedUser(user.getUser().getId());
		boolean flag= regionService.update(region);
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), regionService.getExecuteMessage());
		if(flag){
			regionForm.getQuery().setParentId(region.getParentId());
			regionForm.getQuery().setRegionLevel(region.getRegionLevel());
			//新增成功返回列表页
			return regionPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addRegionEntity(mapping,form,request,response);
		}
	}
	public ActionForward delRegion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RegionForm regionForm =(RegionForm) form;
		int id=regionForm.getRegion().getId();
		RegionVO region=regionService.load(id);
		boolean flag=regionService.delete(id);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), regionService.getExecuteMessage());
		regionForm.getQuery().setParentId(region.getParentId());
		regionForm.getQuery().setRegionLevel(region.getRegionLevel());
		//返回列表页
		return regionPageList(mapping,regionForm,request,response);
	}
	public ActionForward updRegionState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RegionForm regionForm =(RegionForm) form;
		int id=regionForm.getRegion().getId();
		RegionVO region=regionService.load(id);
		boolean flag=regionService.updRegionState(id,regionForm.getRegion().getStatus());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), regionService.getExecuteMessage());
		regionForm.getQuery().setParentId(region.getParentId());
		regionForm.getQuery().setRegionLevel(region.getRegionLevel());
		//返回列表页
		return regionPageList(mapping,regionForm,request,response);
	}
	public ActionForward regionPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RegionForm regionForm =(RegionForm) form;
		RegionQueryVO regionQuery=regionForm.getQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("regionList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		//tools.saveQueryVO(regionQuery);
		if(regionQuery.getParentId()==0){
			regionQuery.setParentId(-1);
		}
		if(regionQuery.getRegionLevel()==0){
			regionQuery.setRegionLevel(RegionLevelContant.FIRST_LEVEL.getCode());
		}
		if(StringUtil.isNotEmpty(regionQuery.getName())|| StringUtil.isNotEmpty(regionQuery.getParentName()) || regionQuery.getStatus()!=0 ){
			regionQuery.setRegionLevel(-1);
		}
		//按条件查询分页数据
		List<RegionVO> list = regionService.searchPageList(regionQuery,tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		RegionVO parent=regionService.load(regionQuery.getParentId());
		if(parent!=null){
			request.setAttribute("grandparentId", parent.getParentId());
			request.setAttribute("grandparentLevel", parent.getRegionLevel());
		}else{
			request.setAttribute("grandparentId", 0);
			request.setAttribute("grandparentLevel", 0);
		}
		//初始化页面Option
		initOption(request);
		//返回列表页面
		return mapping.findForward("region_list");
	}
	public ActionForward findChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RegionForm regionForm =(RegionForm) form;
		RegionQueryVO regionQuery=regionForm.getQuery();
		//int parentId=regionQuery.getParentId();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("regionList", request);
		//regionQuery.setParentId(parentId);
		//按条件查询分页数据
		List<RegionVO> list = regionService.searchPageList(regionQuery,tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		RegionVO parent=regionService.load(regionQuery.getParentId());
		if(parent!=null){
			request.setAttribute("grandparentId", parent.getParentId());
			request.setAttribute("grandparentLevel", parent.getRegionLevel());
		}else{
			request.setAttribute("grandparentId", 0);
			request.setAttribute("grandparentLevel", 0);
		}
		//初始化页面Option
		initOption(request);
		//返回列表页面
		return mapping.findForward("region_list");
	}
	/**
	 * 返回地区列表，用于选择地区
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public List<RegionVO> RegionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RegionForm regionForm =(RegionForm) form;
		RegionQueryVO regionQuery=regionForm.getQuery();
		
		//按条件查询分页数据
		List<RegionVO> list = regionService.searchList(regionQuery);
		
		return list;
	}

	/**
	 * 初始化Option
	 * @param request
	 * @return void
	 */
	private void initOption(HttpServletRequest request){
		
		//初始化资源状态复选框
		List<OptionObject> stateOptions = OptionUtil.stateOptions();
		request.setAttribute("stateOptions", stateOptions);
		request.setAttribute("regionLevel", OptionUtil.regionLevel());
		
	}
	
}
