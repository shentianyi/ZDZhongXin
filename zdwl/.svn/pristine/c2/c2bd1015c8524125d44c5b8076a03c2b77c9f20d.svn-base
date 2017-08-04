package com.zd.csms.marketing.web.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.PaymentQueryVO;
import com.zd.csms.marketing.model.PaymentVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.ExpenseTotalQueryBean;
import com.zd.csms.marketing.querybean.SelectDealerBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.PaymentService;
import com.zd.csms.marketing.web.excel.DealerRowMapper;
import com.zd.csms.marketing.web.form.DealerForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisorymanagement.model.DataMailcostQueryVO;
import com.zd.csms.supervisorymanagement.web.form.DataMailcostForm;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class DealerAction extends ActionSupport{
	DealerService service = new DealerService();
	BankService bankService = new BankService();
	PaymentService paymentService = new PaymentService();
	
	private DealerForm getForm(ActionForm form){
		return (DealerForm) form;
	}
	
	/**
	 * 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		int currClientType = UserSessionUtil.getUserSession(request).getUser().getClient_type();
		query.setCurrClientType(currClientType);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("dealerList", request);
		tools.setPageSize(10);
		List<DealerQueryBean> querBeanList = service.findList(query, tools);
		request.setAttribute("list", querBeanList);
		request.setAttribute("cooperationState", OptionUtil.getCooperationState());
		request.setAttribute("currClientType", currClientType);
		return mapping.findForward("dealerList");
	}
	public ActionForward detailInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		DealerQueryBean bean = service.detailInfo(query.getId(),UserSessionUtil.getUserSession(request).getUser().getClient_type());
		request.setAttribute("dealer", bean);
		 
		return mapping.findForward("detailInfo");
	}
	
	/**
	 * 监管费用统计 -- 更改于20170513 -- 需求64
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward expenseTotalList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		RegionService regionService=new RegionService();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("expenseTotalList", request);
		List<ExpenseTotalQueryBean> querBeanList = service.expenseTotalList(query, tools);
		for (int i = 0; i < querBeanList.size(); i++) {
			querBeanList.get(i).setProvince(regionService.getNameById(StringUtil.intValue(querBeanList.get(i).getProvince(), 0)));
			querBeanList.get(i).setCity(regionService.getNameById(StringUtil.intValue(querBeanList.get(i).getCity(), 0)));
		}
		request.setAttribute("list", querBeanList);
		request.setAttribute("isArrears", OptionUtil.yesorno());
		return mapping.findForward("expenseTotalList");
	}
	
	/**
	 * 监管费用统计详情页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward paymentDetailList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		PaymentQueryVO paymentQuery = new PaymentQueryVO();
		paymentQuery.setDealerId(query.getId());
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("paymentDetailList", request);
		tools.saveQueryVO(paymentQuery);
		List<PaymentVO> querBeanList = paymentService.findListByDealerId(paymentQuery, tools);
		request.setAttribute("list", querBeanList);
		return mapping.findForward("paymentDetailList");
	}
	
//	导出经销商名录表
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DealerQueryVO query = getForm(form).getQuery();
		
		try {
			IThumbPageTools tools = ToolsFactory.getThumbPageTools("dealerList", request);
			tools.setPageSize(10);
			List<DealerQueryBean> querBeanList = service.findList(query, tools);
			
			IExportFile export = new ExportFileExcelImpl();
			export.export(querBeanList, new DealerRowMapper(), export.createDefaultFileName("经销商名录表"),"经销商名录表", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回列表页面
		return null;
	}
	
	
	public ActionForward selectDealer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("selectDealerList", request);
		tools.setPageSize(5);
		List<SelectDealerBean> resultList = service.selectDealerList(query, tools);
		request.setAttribute("list", resultList);
		
		return mapping.findForward("selectDealerList");
	}
	
	/*
	 * 需求38 -- 导出监管费管理台账
	 * @time 20170519
	*/
	public ActionForward ExtExpenseTotalList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		List<ExpenseTotalQueryBean> list = service.ExtExpenseTotalList(query);
		String[] titles = {"序号","经销商全称","经销商所在省","经销商所在市","经销商具体地址","金融机构","品牌","监管费责任人",
				"进驻时间",	"经销商联系人",	"经销商电话","金融机构联系人","金融机构电话","是否绑定店","绑定信息","监管费标准/年",
				"付费方式","付费金额","缴费时间","余额","费用到期时间","费用到期欠费金额"};
		String filename = "监管费管理台账";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
		for (int i = 0; list != null && i < list.size(); i++) {
			ExpenseTotalQueryBean vo = list.get(i);
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getDealerName());
	    	
	    	tool.setCellValue(i+1, 2, vo.getProvince());
	    	
	    	tool.setCellValue(i+1, 3, vo.getCity());
	    	
	    	tool.setCellValue(i+1, 4, vo.getAddress());
	    	
	    	tool.setCellValue(i+1, 5, vo.getBank());
	    	
	    	tool.setCellValue(i+1, 6, vo.getBrand());
	    	
	    	tool.setCellValue(i+1, 7, vo.getCreateUserName());
	    	
	    	if (vo.getInDate() !=null) {
	    		String inDate = format.format(vo.getInDate());
	    		tool.setCellValue(i+1, 8, inDate);
			}else {
				tool.setCellValue(i+1, 8, "");
			}
	    	
	    	tool.setCellValue(i+1, 9, vo.getContact());
	    	
	    	tool.setCellValue(i+1, 10, vo.getContactPhone());
	    	
	    	tool.setCellValue(i+1, 11, vo.getBankContact());
	    	
	    	tool.setCellValue(i+1, 12, vo.getBankPhone());
	    	
	    	if (vo.getDdorbd() == 1) {
	    		tool.setCellValue(i+1, 13, "绑定");
			}else if (vo.getDdorbd() == 2) {
				tool.setCellValue(i+1, 13, "单店");
			}
	    	
	    	tool.setCellValue(i+1, 14, vo.getBindInfo());
	    	
	    	tool.setCellValue(i+1, 15, vo.getSuperviseMoney());
	    	
	    	tool.setCellValue(i+1, 16, vo.getPayMode());
	    	
	    	tool.setCellValue(i+1, 17, vo.getActualPaymentMoney());
	    	
	    	if (vo.getActualPaymentDate() !=null) {
	    		String actualPaymentDate = format.format(vo.getActualPaymentDate());
	    		tool.setCellValue(i+1, 18, actualPaymentDate);
			}else if (vo.getDdorbd() == 2) {
				tool.setCellValue(i+1, 18, "");
			}
	    	
	    	tool.setCellValue(i+1, 19, vo.getBalance());
	    	
	    	if (vo.getArrearsDate() !=null) {
	    		String arrearsDate = format.format(vo.getArrearsDate());
	    		tool.setCellValue(i+1, 20, arrearsDate);
			}else {
				tool.setCellValue(i+1, 20, "");
			}
	    	
	    	tool.setCellValue(i+1, 21, vo.getArrearsMoney());
	    	
	    	tool.allAutoColumnWidth(i);
	    }
		tool.writeStream(response, filename);
		return null;
	}
}
