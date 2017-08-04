package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.windcontrol.querybean.InspectionLedgerQueryBean;
import com.zd.csms.windcontrol.service.InspectionService;
import com.zd.csms.windcontrol.web.form.InspectionLedgerForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class InspectionReportAction extends ActionSupport {
	private InspectionService inspecService = new InspectionService();
	
	/**
	 * 列表页需要使用的角色
	 */
	private static int[] approvalRole = new int[]{
			//品牌集团
			RoleConstants.BRANDMASTER.getCode(),
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
			//经销商集团
			RoleConstants.DEALERMASTER.getCode(),
			RoleConstants.DEALERMASTERA.getCode(),
	        RoleConstants.BANK_APPROVE.getCode()};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	// 巡检报告台账：风控经理
	public ActionForward reportLedgerList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InspectionLedgerForm query = (InspectionLedgerForm) form;
		int currRole=getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		query.setCurrRole(currRole);
		query.setUserId(user.getId());
		query.setUserVO(user);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pagelist",
				request);

		tools.saveQueryVO(query);
		List<InspectionLedgerQueryBean> list = inspecService
				.findReportLedgerList(query, tools);
		
		request.setAttribute("list", list);
		return mapping.findForward("reportLedger");

	}
	

	/*
	 * 需求38 -- 导出巡检报告台账
	 * ExtInspecReportLenger
	 * @time 20170518
	 */
	public ActionForward ExtInspecReportLenger(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InspectionLedgerForm query = (InspectionLedgerForm) form;
		int currRole=getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		query.setCurrRole(currRole);
		query.setUserId(user.getId());
		query.setUserVO(user);
		
		List<InspectionLedgerQueryBean> list = inspecService.ExtInspecReportLenger(query);
		String[] titles = {"序号","巡检编号","外控专员","经销商","品牌","总行/金融机构","分行/分支机构","支行","省","市",
				"检查日期","检查用时","检查类型","风险等级","经销店等级","监管员等级"};
		String filename = "巡检报告台账";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
		for (int i = 0; list != null && i < list.size(); i++){
			InspectionLedgerQueryBean vo = list.get(i);
			tool.setCellValue(i+1, 0, i+1);
			
	    	tool.setCellValue(i+1, 1, vo.getPlanCode());
	    	
	    	tool.setCellValue(i+1, 2, vo.getOutControlUserName());
	    	
	    	tool.setCellValue(i+1, 3, vo.getDealerName());
	    	
	    	tool.setCellValue(i+1, 4, vo.getBrandName());
	    	
	    	tool.setCellValue(i+1, 5, vo.getOneBankName());
	    	
	    	tool.setCellValue(i+1, 6, vo.getTwoBankName());
	    	
	    	tool.setCellValue(i+1, 7, vo.getThreeBankName());
	    	
	    	tool.setCellValue(i+1, 8, vo.getProvinceName());
	    	
	    	tool.setCellValue(i+1, 9, vo.getCityName());
	    	
	    	if (vo.getCheck_time()!=null) {
				String checkTime = format.format(vo.getCheck_time());
				tool.setCellValue(i+1, 10, checkTime);
			}else{
				tool.setCellValue(i+1, 10, "");
			}
	    	
	    	tool.setCellValue(i+1, 11, vo.getCheck_period());
	    	
	    	tool.setCellValue(i+1, 12, vo.getCheckTypeName());
	    	
	    	tool.setCellValue(i+1, 13, vo.getDangerLevelName());
	    	
	    	tool.setCellValue(i+1, 14, vo.getDealerLevelName());
	    	
	    	tool.setCellValue(i+1, 15, vo.getSupervisorLevelName());
	    	
	    	tool.allAutoColumnWidth(i);
		}
		tool.writeStream(response, filename);
		return null;
		
	}
	
}
