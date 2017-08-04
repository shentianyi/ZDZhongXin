package com.zd.csms.business.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.business.model.AddresslistQueryVO;
import com.zd.csms.business.model.AddresslistVO;
import com.zd.csms.business.service.AddresslistService;
import com.zd.csms.business.web.form.AddresslistForm;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class AddressSuperviselistAction extends ActionSupport {

	public ActionForward addressSuperviseListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return addressSuperviseList(mapping, form,request, response);
	}
	
	
	public ActionForward addressSuperviseList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		AddresslistForm alform = (AddresslistForm)form;
		AddresslistService service = new AddresslistService();
		
		AddresslistQueryVO alQuery = alform.getAddresslistquery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(user != null){
			alQuery.setLoginid(user.getId());
		}
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("addressSuperviseList",request);
		thumbPageTools.saveQueryVO(alQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<AddresslistVO> list = service.searchAddressListByPage(alQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("address_supervise_list");
	}
	
	public ActionForward addAddressSuperviselistEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("add_addressSuperviselist");
	}
	
	public ActionForward addAddressSuperviselist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AddresslistForm alform = (AddresslistForm)form;
		AddresslistService service = new AddresslistService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(user != null){
			alform.getAddresslist().setLoginid(user.getId());
		}
		//执行新增操作并获取操作结果
		boolean flag = service.addAddresslist(alform.getAddresslist());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return addressSuperviseList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_addressSuperviselist");
	}
	
	public ActionForward updAddressSuperviselistEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AddresslistForm alform = (AddresslistForm)form;
		AddresslistService service = new AddresslistService();
		
		//根据id获取修改对象
		AddresslistVO vo = service.loadAddresslistById(alform.getAddresslist().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return addressSuperviseList(mapping, form, request, response);
		}
		
		alform.setAddresslist(vo);
		
		//返回修改页面
		return mapping.findForward("upd_addressSuperviselist");
	}
	
	public ActionForward updAddressSuperviselist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AddresslistForm alform = (AddresslistForm)form;
		AddresslistService service = new AddresslistService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updAddresslist(alform.getAddresslist());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return addressSuperviseList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_addressSuperviselist");
	}
	
	public ActionForward delAddressSuperviselist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AddresslistForm alform = (AddresslistForm)form;
		AddresslistService service = new AddresslistService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteAddresslist(alform.getAddresslist().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return addressSuperviseList(mapping, form, request, response);
	}
}
