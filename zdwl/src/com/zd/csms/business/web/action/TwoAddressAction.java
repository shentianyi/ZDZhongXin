package com.zd.csms.business.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.ExtTwoAddressVO;
import com.zd.csms.business.model.TwoAddressQueryVO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.service.TwoAddressService;
import com.zd.csms.business.web.excel.TwoAddressRowMapper;
import com.zd.csms.business.web.form.TwoAddressForm;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class TwoAddressAction extends ActionSupport {

	/**
	 * 列表页需要使用的角色
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			//品牌集团
			RoleConstants.BRANDMASTER.getCode(),
			RoleConstants.DEALERMASTER.getCode(),
			RoleConstants.DEALERMASTERA.getCode(),
			RoleConstants.SR.getCode(),
			RoleConstants.BANK_APPROVE.getCode()};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	public ActionForward twoAddressListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return twoAddressList(mapping, form,request, response);
	}
	
	
	public ActionForward twoAddressList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		TwoAddressForm taform = (TwoAddressForm)form;
		TwoAddressService service = new TwoAddressService();
		TwoAddressQueryVO taQuery = taform.getTwoAddressquery();
		int role=getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		try {
			//监管员
			if(role == RoleConstants.SUPERVISORY.getCode()){
				taQuery.setTypes(1);
				taQuery.setUserid(user.getClient_id());
			}else if(role == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
				taQuery.setTypes(2);
				taQuery.setUserid(user.getId());
			}else if(role == RoleConstants.BRANDMASTER.getCode()){
				taQuery.setTypes(3);
				taQuery.setUserid(user.getId());
			}else if(role == RoleConstants.BANK_APPROVE.getCode()){
				taQuery.setClient_id(user.getClient_id());
				taQuery.setCurrentRole(role);
			}else if (role == RoleConstants.DEALERMASTER.getCode() || role == RoleConstants.DEALERMASTERA.getCode()) {
				taQuery.setUserid(user.getId());
				taQuery.setCurrentRole(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("twoAddressList",request);
		thumbPageTools.saveQueryVO(taQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<TwoAddressVO> list = service.searchTwoAddressListByPage(taQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//request.setAttribute("dealersOptions", OptionUtil.ActualAddressDealers(user)); //原
		//需求139 -- 显示与品牌集团合作的经销商
		if(role == RoleConstants.BRANDMASTER.getCode()){
			request.setAttribute("dealersOptions", OptionUtil.getBrandMasterDealers(user.getId()));
		}else{
//			需求90 -- 显示全部合作中的经销商
			request.setAttribute("dealersOptions", OptionUtil.getDealers(1));
		}
		request.setAttribute("role",role);
		//返回列表页面
		return mapping.findForward("two_address_list");
	}
	
	public ActionForward addTwoAddressEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		request.setAttribute("dealersOptions", OptionUtil.ActualAddressDealers(user));
		//返回新增页面
		return mapping.findForward("add_two_address");
	}
	
	public ActionForward addTwoAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TwoAddressForm taform = (TwoAddressForm)form;
		TwoAddressService service = new TwoAddressService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		TwoAddressVO tavo = taform.getTwoAddress();
		tavo.setCreateuserid(user.getId());
		tavo.setCreatedate(new Date());
	/*	tavo.setUpduserid(user.getId());
		tavo.setUpddate(new Date());*/
		//执行新增操作并获取操作结果
		boolean flag = service.addTwoAddress(tavo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return twoAddressList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_two_address");
	}
	
	public ActionForward updTwoAddressEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TwoAddressForm taform = (TwoAddressForm)form;
		TwoAddressService service = new TwoAddressService();
		
		//根据id获取修改对象
		TwoAddressVO vo = service.loadTwoAddressById(taform.getTwoAddress().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return twoAddressList(mapping, form, request, response);
		}
		
		taform.setTwoAddress(vo);
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		request.setAttribute("dealersOptions", OptionUtil.ActualAddressDealers(user));
		//返回修改页面
		return mapping.findForward("upd_two_address");
	}
	
	public ActionForward updTwoAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TwoAddressForm taform = (TwoAddressForm)form;
		TwoAddressService service = new TwoAddressService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		TwoAddressVO tavo = taform.getTwoAddress();
		tavo.setUpduserid(user.getId());
		tavo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updTwoAddress(tavo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return twoAddressList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_two_address");
	}
	
	public ActionForward delTwoAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TwoAddressForm taform = (TwoAddressForm)form;
		TwoAddressService service = new TwoAddressService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteTwoAddress(taform.getTwoAddress().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return twoAddressList(mapping, form, request, response);
	}
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("twoAddressTypeOptions", OptionUtil.twoAddressType());
		
	}
	
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TwoAddressForm taform = (TwoAddressForm)form;
		TwoAddressService service = new TwoAddressService();
		TwoAddressQueryVO taQuery = taform.getTwoAddressquery();
		
		int role=getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		try {
			//监管员
			if(role == RoleConstants.SUPERVISORY.getCode()){
				taQuery.setTypes(1);
				taQuery.setUserid(user.getClient_id());
			}else if(role == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
				taQuery.setTypes(2);
				taQuery.setUserid(user.getId());
			}else if(role == RoleConstants.BRANDMASTER.getCode()){
				taQuery.setTypes(3);
				taQuery.setUserid(user.getId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("twoAddressList",request);
		thumbPageTools.setPageSize(9999999);
		
		//按条件查询分页数据 
		List<TwoAddressVO> list = service.searchTwoAddressListByPage(taQuery, thumbPageTools);
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new TwoAddressRowMapper(), export.createDefaultFileName("监管场地管理"),"监管场地管理", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回列表页面
		return null;
	}
	
	/*
	 * 需求90 -- 导出监管场地
	 * @time 20170519
	*/
	public ActionForward ExtSupervisionSite(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		TwoAddressForm taform = (TwoAddressForm)form;
		TwoAddressService service = new TwoAddressService();
		TwoAddressQueryVO taQuery = taform.getTwoAddressquery();
		int role=getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		try {
			//监管员
			if(role == RoleConstants.SUPERVISORY.getCode()){
				taQuery.setTypes(1);
				taQuery.setUserid(user.getClient_id());
			//业务部专员
			}else if(role == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
				taQuery.setTypes(2);
				taQuery.setUserid(user.getId());
			//品牌集团	
			}else if(role == RoleConstants.BRANDMASTER.getCode()){
				taQuery.setTypes(3);
				taQuery.setUserid(user.getId());
			}else {
				request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "没有导出权限！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<ExtTwoAddressVO> list = service.ExtSupervisionSite(taQuery);
		String[] titles ={"序号","经销商","品牌","金融机构","类别","名称","详细地址","联系人","联系电话","距离（公里）"};
		String filename = "监管场地管理"; //需求159 --导出名称更改
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	ExtTwoAddressVO vo = list.get(i);
	    	tool.setCellValue(i+1, 0, i+1);
	    	tool.setCellValue(i+1, 1, vo.getDistribNTT());
	    	tool.setCellValue(i+1, 2, vo.getBrandNTT());
	    	tool.setCellValue(i+1, 3, vo.getBankfullnameNTT());
	    	if (vo.getType() == 1) {
	    		tool.setCellValue(i+1, 4, "本库");
			}else if (vo.getType() == 2) {
	    		tool.setCellValue(i+1, 4, "二库");
			}else if (vo.getType() == 3) {
	    		tool.setCellValue(i+1, 4, "二网");
			}
	    	
	    	tool.setCellValue(i+1, 5, vo.getTwo_name());
	    	tool.setCellValue(i+1, 6, vo.getTwo_address());
	    	tool.setCellValue(i+1, 7, vo.getTwo_username());
	    	tool.setCellValue(i+1, 8, vo.getPhone());
	    	tool.setCellValue(i+1, 9, vo.getDistance());
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
	    return null;
	}
	
}
