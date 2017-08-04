package com.zd.csms.supervisory.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.supervisory.model.ActualAddressQueryVO;
import com.zd.csms.supervisory.model.ActualAddressVO;
import com.zd.csms.supervisory.service.ActualAddressService;
import com.zd.csms.supervisory.web.form.ActualAddressForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ActualAddressAction extends ActionSupport {

	public ActionForward actualAddressListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return actualAddressList(mapping, form,request, response);
	}
	
	
	public ActionForward actualAddressList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ActualAddressForm aaform = (ActualAddressForm)form;
		ActualAddressService service = new ActualAddressService();
		
		ActualAddressQueryVO aaQuery = aaform.getActualAddressquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		try {
			//监管员
			if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
				aaQuery.setType(1);
				aaQuery.setUserid(user.getClient_id());
			}else{
				RoleService rs = new RoleService();
				UserRoleQueryVO urquery = new UserRoleQueryVO();
				urquery.setUser_id(user.getId());
				List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
				if(urList != null && urList.size()>0){
					for(UserRoleVO urvo : urList){
						if(urvo.getRole_id() == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
							aaQuery.setType(2);
							aaQuery.setUserid(user.getId());
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("actualAddressList",request);
		thumbPageTools.saveQueryVO(aaQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<ActualAddressVO> list = service.searchActualAddressListByPage(aaQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		request.setAttribute("dealersOptions", OptionUtil.ActualAddressDealers(user));
		
		//返回列表页面
		return mapping.findForward("actual_address_list");
	}
	
	public ActionForward addActualAddressEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		request.setAttribute("dealersOptions", OptionUtil.ActualAddressDealers(user));
		//返回新增页面
		return mapping.findForward("add_actual_address");
	}
	
	public ActionForward addActualAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActualAddressForm aaform = (ActualAddressForm)form;
		ActualAddressService service = new ActualAddressService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		ActualAddressVO aavo = aaform.getActualAddress();
		aavo.setCreateuserid(user.getId());
		aavo.setCreatedate(new Date());
		//执行新增操作并获取操作结果
		boolean flag = service.addActualAddress(aavo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return actualAddressList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_actual_address");
	}
	
	public ActionForward updActualAddressEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActualAddressForm aaform = (ActualAddressForm)form;
		ActualAddressService service = new ActualAddressService();
		
		//根据id获取修改对象
		ActualAddressVO vo = service.loadActualAddressById(aaform.getActualAddress().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return actualAddressList(mapping, form, request, response);
		}
		aaform.setActualAddress(vo);
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		request.setAttribute("dealersOptions", OptionUtil.ActualAddressDealers(user));
		//返回修改页面
		return mapping.findForward("upd_actual_address");
	}
	
	public ActionForward updActualAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActualAddressForm aaform = (ActualAddressForm)form;
		ActualAddressService service = new ActualAddressService();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		ActualAddressVO aavo = aaform.getActualAddress();
		aavo.setUpduserid(user.getId());
		aavo.setUpddate(new Date());
		
		//执行修改操作并获取操作结果
		boolean flag = service.updActualAddress(aavo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return actualAddressList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_actual_address");
	}
	
	public ActionForward delActualAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActualAddressForm aaform = (ActualAddressForm)form;
		ActualAddressService service = new ActualAddressService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteActualAddress(aaform.getActualAddress().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return actualAddressList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
	}
}
