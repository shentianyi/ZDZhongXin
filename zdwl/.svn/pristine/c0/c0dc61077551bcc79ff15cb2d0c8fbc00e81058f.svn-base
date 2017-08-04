package com.zd.csms.ledger;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.junit.Test;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.model.PaymentQueryVO;
import com.zd.csms.marketing.model.PaymentVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.ExpenseTotalQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.PaymentService;
import com.zd.csms.marketing.web.excel.DealerRowMapper;
import com.zd.csms.marketing.web.form.DealerForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 经销商名录表
 * 
 * @author ly
 *
 */
public class DealerAction extends ActionSupport {

	DealerService service = new DealerService();
	PaymentService paymentService = new PaymentService();

	private DealerForm getForm(ActionForm form) {
		return (DealerForm) form;
	}

	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DealerQueryVO query = getForm(form).getQuery();
		int currClientType = UserSessionUtil.getUserSession(request).getUser()
				.getClient_type();
		query.setCurrClientType(currClientType);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("dealerList",
				request);
		tools.setPageSize(10);
		List<DealerQueryBean> querBeanList = service.findList(query, tools);
		request.setAttribute("list", querBeanList);
		request.setAttribute("cooperationState",
				OptionUtil.getCooperationState());
		request.setAttribute("currClientType", currClientType);
		return mapping.findForward("dealer_ledger");
	}

	public ActionForward dealerDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DealerQueryVO query = getForm(form).getQuery();
		DealerQueryBean bean = service.detailInfo(query.getId(),
				UserSessionUtil.getUserSession(request).getUser()
						.getClient_type());
		request.setAttribute("dealer", bean);

		return mapping.findForward("dealer_detail");
	}

	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DealerQueryVO query = getForm(form).getQuery();

		try {
			IThumbPageTools tools = ToolsFactory.getThumbPageTools(
					"dealerList", request);
			List<DealerQueryBean> querBeanList = service.findListForExcel(query);

			IExportFile export = new ExportFileExcelImpl();
			export.export(querBeanList, new DealerRowMapper(),
					export.createDefaultFileName("经销商名录表"), "经销商名录表", response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回列表页面
		return null;
	}

	public ActionForward expenseTotalLedger(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DealerQueryVO query = getForm(form).getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools(
				"expenseTotalLedger", request);
		List<ExpenseTotalQueryBean> querBeanList = service.expenseTotalList(
				query, tools);
		request.setAttribute("list", querBeanList);
		request.setAttribute("isArrears", OptionUtil.yesorno());
		return mapping.findForward("expense_total_ledger");
	}

	public ActionForward paymentDetailList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DealerQueryVO query = getForm(form).getQuery();
		PaymentQueryVO paymentQuery = new PaymentQueryVO();
		paymentQuery.setDealerId(query.getId());
		IThumbPageTools tools = ToolsFactory.getThumbPageTools(
				"paymentDetailList", request);
		tools.saveQueryVO(paymentQuery);
		List<PaymentVO> querBeanList = paymentService.findListByDealerId(
				paymentQuery, tools);
		request.setAttribute("list", querBeanList);
		return mapping.findForward("paymentDetailList");
	}
   
	//经销商名录表：业务部 
	public ActionForward findBusinessList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DealerQueryVO query = getForm(form).getQuery();
		int currClientType = UserSessionUtil.getUserSession(request).getUser()
				.getClient_type();
		query.setCurrClientType(currClientType);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("dealerList",
				request);
		tools.setPageSize(10);
		List<DealerQueryBean> querBeanList = service.findBusinessList(query,
				tools);
		request.setAttribute("list", querBeanList);
		request.setAttribute("cooperationState",
				OptionUtil.getCooperationState());
		return mapping.findForward("dealer_business_ledger");
	}
	
    //导出经销商名录表：业务部 
	public ActionForward extBusinessExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DealerQueryVO query = getForm(form).getQuery();

		try {
			IThumbPageTools tools = ToolsFactory.getThumbPageTools(
					"dealerList", request);
			List<DealerQueryBean> querBeanList = service.findBusinessForExcel(query);
            IExportFile export = new ExportFileExcelImpl();
			export.export(querBeanList, new DealerRowMapper(9),
					export.createDefaultFileName("经销商名录表"), "经销商名录表", response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回列表页面
		return null;
	}
	
	/*
	 * 需求53 -- 20170511- 修改经销商名录表台账（业务）修改入口 dealerModify
	*/
	public ActionForward dealerModify(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		DealerForm dForm = (DealerForm) form;
		DealerQueryBean bean = service.detailInfo(query.getId(),
				UserSessionUtil.getUserSession(request).getUser()
						.getClient_type());
		dForm.setDealerQ(bean);
		//品牌
		List<OptionObject> brandList = new ArrayList<OptionObject>();
		brandList = OptionUtil.getBrands();
		request.setAttribute("brandList", brandList);
		//省
		List<OptionObject> provinceList = new ArrayList<OptionObject>();
		provinceList = OptionUtil.getRegionProvince();
		request.setAttribute("provinceList", provinceList);
		//市
		List<OptionObject> cityList = new ArrayList<OptionObject>();
		cityList = OptionUtil.getRegionCity();
		request.setAttribute("cityList", cityList);
		request.setAttribute("dealer", bean);
		return mapping.findForward("dealer_modify");
	}
	
	/*
	 * 根据省id获取市
	 * @return json
	 * @time 20170512
	*/
	public ActionForward searchCity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JSONArray ja = new JSONArray();
		DealerForm dForm = (DealerForm) form;
		DealerQueryBean query = dForm.getDealerQ(); 
		List<OptionObject> brandList= new ArrayList<OptionObject>();
		brandList = OptionUtil.getCitys(query.getProvince());
		ja.add(brandList);
		 response.setCharacterEncoding("UTF-8"); 
		 PrintWriter writer = null;
		 System.out.println(ja.toString());
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
	
	/*
	 * 修改经销商名录表台账（业务）
	 * @params:
	 * @return boolean
	 * @time 20170512
	*/
	public ActionForward UpdateDealer(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception{
		DealerForm dForm = (DealerForm) form;
		DealerQueryBean query = dForm.getDealerQ(); 
		if (query.getProvince() !=null && query.getCity()!=null) {
			query.setProvince(String.valueOf(Integer.parseInt(query.getProvince())));
			query.setCity(String.valueOf(Integer.parseInt(query.getCity())));
		}
		int count1 = service.updateBankManagerInfo(query);
		int count = service.updateDealer(query);
		if (count > 0 && count1 > 0) {
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改成功！");
			System.out.println("修改成功！");
		}
		return findBusinessList(mapping, form, request, response);
	}
	
	/*
	 * 修改经销商名录表台账（业务）-- 修改合作状态
	 * @time 20170513
	*/
	public ActionForward StopCooperationState(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		DealerQueryBean query = getForm(form).getDealerQ();
		//得到long类型当前时间
		long l = System.currentTimeMillis();
		//new日期对象
		Date date = new Date(l);
		//转换提日期输出格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date d = new Date();
		d = dateFormat.parse(dateFormat.format(date));
		query.setExitDate(d);
		boolean flag = service.updateStopCooperationState(query);
		if (flag) {
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "停用成功！");
			System.out.println("停用成功！");
		}
		return findBusinessList(mapping, form, request, response);
		
	}
	
	/*
	 * 需求38 -- 导出监管费管理台账
	 * ExtexpenseTotalLedger
	 * @time 20170518
	*/
	public ActionForward ExtexpenseTotalLedger (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		List<ExpenseTotalQueryBean> list = service.ExtexpenseTotalLedger(query);
		String[] titles ={"序号","经销商全称","金融机构","品牌","监管费责任人","进驻时间","监管费标准/年","付费方式",
				"合计收费","欠费时间","欠费金额"};
		String filename = "监管费管理台账";
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
		for (int i = 0; list != null && i < list.size(); i++) {
			ExpenseTotalQueryBean vo =  list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getDealerName());
	    	
	    	tool.setCellValue(i+1, 2, vo.getBank());
	    	
	    	tool.setCellValue(i+1, 3, vo.getBrand());
	    	
	    	tool.setCellValue(i+1, 4, vo.getCreateUserName());
	    	
	    	if (vo.getInDate() !=null) {
				String inDate = format.format(vo.getInDate());
				tool.setCellValue(i+1, 5, inDate);
			}else{
				tool.setCellValue(i+1, 5, "");
			}
	    	
	    	tool.setCellValue(i+1, 6, vo.getSuperviseMoney());
	    	
	    	tool.setCellValue(i+1, 7, vo.getPayMode());
	    	
	    	tool.setCellValue(i+1, 8, vo.getTotalMoney());
	    	
	    	if (vo.getArrearsDate() !=null) {
				String arrearsDate = format.format(vo.getArrearsDate());
				tool.setCellValue(i+1, 9,arrearsDate);
			}else {
				tool.setCellValue(i+1, 9, "");
			}

	    	tool.setCellValue(i+1, 10, vo.getArrearsMoney());
	    	tool.allAutoColumnWidth(i);
	    }
		tool.writeStream(response, filename);
		return null;
	}
	
	/*
	 * 修改经销商名录表台账（市场）-- 修改合作状态
	 * @time 20170513
	*/
	public ActionForward StopCooperationStateMarket(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		DealerQueryBean query = getForm(form).getDealerQ();
		//得到long类型当前时间
		long l = System.currentTimeMillis();
		//new日期对象
		Date date = new Date(l);
		//转换提日期输出格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date d = new Date();
		d = dateFormat.parse(dateFormat.format(date));
		query.setExitDate(d);
		boolean flag = service.updateStopCooperationState(query);
		if (flag) {
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "停用成功！");
			System.out.println("停用成功！");
		}
		return findList(mapping, form, request, response);
	}
	
	
	public ActionForward dealerModeChangeLog(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		DealerQueryVO query = getForm(form).getQuery();
		DealerQueryBean bean = service.detailInfo(query.getId(),
				UserSessionUtil.getUserSession(request).getUser()
						.getClient_type());
		request.setAttribute("dealer", bean);
		if(query.getId() == 0){
			
			request.setAttribute("list", new ArrayList<ModeChangeLogVO>());
			return mapping.findForward("modeChange_log");
		}
		
		List<ModeChangeLogVO> list = service.findModeChangeLogVOById(query.getId());
		request.setAttribute("list", list);
		return mapping.findForward("modeChange_log");
	}
	
	
}
