package com.zd.csms.ledger;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.querybean.ExportCarInfoBean;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.web.excel.SuperviseImportRowMapper;
import com.zd.csms.supervisory.web.form.SuperviseImportForm;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 车辆监管台账
 * @author ly
 *
 */
public class SuperviseImportActon extends ActionSupport {

	/**
	 * 流程角色:市场部专员->招聘专员->业务部专员
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.BANK_APPROVE.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.DEALERMASTER.getCode(),
			RoleConstants.DEALERMASTERA.getCode(),
			RoleConstants.BRANDMASTER.getCode()
			};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	public ActionForward superviseLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm)form;
		SuperviseImportService service = new SuperviseImportService();
		
		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setApply(-1);
		siQuery.setDraft_num(siQuery.getDraft_num());
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(currRole==RoleConstants.BANK_APPROVE.getCode()){
			siQuery.setType(2);
			siQuery.setUserid(user.getClient_id());
		}else if(currRole==RoleConstants.SUPERVISORY.getCode()){
			siQuery.setType(1);
			siQuery.setUserid(user.getId());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			siQuery.setType(3);
			siQuery.setUserid(user.getId());
		}else if(currRole == RoleConstants.BRANDMASTER.getCode()){
			siQuery.setType(4);
			siQuery.setUserid(user.getId());
		}else if (currRole == RoleConstants.DEALERMASTER.getCode() ||
				currRole == RoleConstants.DEALERMASTERA.getCode()) {
			siQuery.setType(5);
			siQuery.setUserid(user.getId());
		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseLedger",request);
		//thumbPageTools.saveQueryVO(siQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		try {
			request.setAttribute("summary", service.getSummaryByBank(siQuery));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		if (currRole == RoleConstants.SUPERVISORY.getCode()) {
			request.setAttribute("dealersName", OptionUtil.getDealerByRepositoryId(user.getClient_id()));
		}else if (currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode() || currRole == RoleConstants.BUSINESS_AMALDAR.getCode()) {
			request.setAttribute("dealersName", OptionUtil.getDealersByYW(user.getId(), currRole));
		}else if (currRole == RoleConstants.BANK_APPROVE.getCode()) {
			request.setAttribute("dealersName", OptionUtil.getDealersByClientId(user.getClient_id()));
		}else {
			request.setAttribute("dealersName", OptionUtil.getDealers());
		}
		
		request.setAttribute("brandsOptions", OptionUtil.getBrandsByPinYin());
		//request.setAttribute("draftNum", siQuery.getDraft_num());
		
		//返回列表页面
		return mapping.findForward("supervise_ledger");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		System.out.println("--------------------start:"+new Date().getTime());
		SuperviseImportForm siform = (SuperviseImportForm)form;
		SuperviseImportService service = new SuperviseImportService();
		
		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setApply(-1);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(currRole==RoleConstants.BANK_APPROVE.getCode()){
			siQuery.setType(2);
			siQuery.setUserid(user.getClient_id());
		}else if(currRole==RoleConstants.SUPERVISORY.getCode()){
			siQuery.setType(1);
			siQuery.setUserid(user.getId());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			siQuery.setType(3);
			siQuery.setUserid(user.getId());
		}else if(currRole == RoleConstants.BRANDMASTER.getCode()){
			siQuery.setType(4);
			siQuery.setUserid(user.getId());
		}
		
		//按条件查询分页数据
		List<ExportCarInfoBean> list = service.exportCarInfo(siQuery);
		System.out.println("--------------------connectEnd:"+new Date().getTime());
		//将查询结果设置request中，用于页面显示
		//返回列表页面
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new SuperviseImportRowMapper(), export.createDefaultFileName("质押监管电子总账"),"质押监管电子总账", response);
			System.out.println("--------------------exportEnd:"+new Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
//		List<OptionObject> option = (List<OptionObject>) request.getAttribute("dealersOptions");
//		option
		
		//返回列表页面
		return null;
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("superviseStateOptions", OptionUtil.superviseState());
		request.setAttribute("draftOptions", OptionUtil.draftOptions());
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
	}
	
	/*
	 * 需求61 -- 异步加载票号
	*/
	public ActionForward draftNumBox(ActionMapping mappig,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONArray ja = new JSONArray();
		String dealerId = request.getParameter("dealerId");
		List<OptionObject> draftNumList= new ArrayList<OptionObject>();
		draftNumList = OptionUtil.getDraftNums(Integer.valueOf(dealerId));
		ja.add(draftNumList);
		 response.setCharacterEncoding("UTF-8"); 
		 PrintWriter writer = null;
		 System.out.println("根据经销商ID获取票号："+ja.toString());
		try {
			writer = response.getWriter();
			writer.print(ja.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			writer.flush();
			writer.close();
		}
		
		return null;
	}
	
	
}
