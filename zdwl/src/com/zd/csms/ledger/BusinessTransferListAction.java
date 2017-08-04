package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.model.BusinessTransferQueryVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.querybean.BusinessTransferQueryBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.web.form.BusinessTransferForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

import com.zd.core.util.ExcelTool;

public class BusinessTransferListAction extends ActionSupport{
	BusinessTransferService service = new BusinessTransferService();
	
	private BusinessTransferForm getForm(ActionForm form){
		return (BusinessTransferForm)form;
	}
	
	/*
	 * 导出数据到Excel
	 */
	public ActionForward exportDataToExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BusinessTransferQueryVO query = getForm(form).getQuery();
		int currRole = getCurrRole(request);
		query.setCurrRole(currRole);
		
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<BusinessTransferQueryBean> list = service.findAllList(query);
		String[] titles = {"经销商名称","金融机构","审批状态","下一审批人","创建人","创建时间","修改人","修改时间"};
		
		for(BusinessTransferQueryBean btqb:list){
			ApprovalContant[] types  = ApprovalContant.values();
			for (ApprovalContant bc : types) {
				if(bc.getCode()==btqb.getApprovalState()){
					btqb.setApprovalStringNTT(bc.getName());
					break;
				}
			}
			
			for (RoleConstants role : RoleConstants.values()) {
				if(role.getCode()==btqb.getNextApproval()){
					btqb.setNextApprovalStringNTT(role.getName());
				}
			}
		}
		
		ExcelTool excel = new ExcelTool(list.size()+1,titles.length);
		excel.setTitle(titles, 0);
		excel.setRowHeight(0, 25);
		BusinessTransferQueryBean btq;
		for(int i=0; i<list.size(); i++){
			btq = list.get(i);
			excel.setCellValue(i+1, 0, btq.getDealerName());
			excel.setCellValue(i+1, 1, btq.getBankName());
			excel.setCellValue(i+1, 2, btq.getApprovalStringNTT());
			excel.setCellValue(i+1, 3, btq.getNextApprovalStringNTT());
			excel.setCellValue(i+1, 4, btq.getCreateUserName());
			excel.setCellValue(i+1, 5, btq.getCreateDate());
			excel.setCellValue(i+1, 6, btq.getLastModifyUserName());
			excel.setCellValue(i+1, 7, btq.getLastModifyDate());
		}
		excel.allAutoColumnWidth(8);
		//excel.writeStream(response, "BusinessTransferList");
		excel.writeStream(response, "业务流转单");
	    
	    return null;
	}
	
	public ActionForward findAllList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BusinessTransferQueryVO query = getForm(form).getQuery();
		int currRole = getCurrRole(request);
		query.setCurrRole(currRole);
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("btlist", request);
		tools.saveQueryVO(query);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<BusinessTransferQueryBean> list = service.findAllList(query,tools);
		request.setAttribute("list", list);
		
		return mapping.findForward("list_all");

	}
	
	/**
	 * 角色:市场专员、市场经理、视频专员、视频经理、内控专员、外控专员、风控经理、风险管理部部长、业务专员、业务经理、招聘专员、考勤专员、调配专员、培训专员、监管员管理部经理、薪酬专员、综合专员
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.SR.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(),
			RoleConstants.VIDEO_COMMISSIONER.getCode(),
			RoleConstants.VIDEO_AMALDAR.getCode(),
			RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
			RoleConstants.WINDCONRTOL_EXTERNAL.getCode(),
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
			RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	/**
	 * 详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BusinessTransferVO bean = getForm(form).getBusiness();
		bean = service.get(bean.getId());
		getForm(form).setBusiness(bean);
		initOption(request);
		return mapping.findForward("detailPage");
	}
	
	private void initOption(HttpServletRequest request){
		request.setAttribute("supervisorSources", OptionUtil.getSupervisorSources());
		request.setAttribute("supervisorAttributes", OptionUtil.getSupervisorAttrubutes());
		List<OptionObject> yesorno = new ArrayList<OptionObject>();
		yesorno.add(new OptionObject("1", "是"));
		yesorno.add(new OptionObject("2", "否"));
		request.setAttribute("yesorno", yesorno);
		request.setAttribute("trainingEffect",OptionUtil.trainingEffect());
	}

}
