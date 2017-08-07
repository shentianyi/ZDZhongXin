package com.zd.csms.business.web.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.queryBean.DraftQueryBean;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.business.web.excel.DraftRowMapper;
import com.zd.csms.business.web.form.DraftForm;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class DraftAction extends ActionSupport {
	private DraftService service = new DraftService();
	private DealerService dealerService = new DealerService();
	private BrandService brandService = new BrandService();

	
	private static int[] roles = new int[]{
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.BANK_APPROVE.getCode(),
			RoleConstants.DEALERMASTER.getCode(),
			RoleConstants.DEALERMASTERA.getCode(),
			RoleConstants.SR.getCode()
			};
	
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, roles);
	}

	public ActionForward draftListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		return draftList(mapping, form,request, response);
	}
	
	
	public ActionForward draftList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole = getCurrRole(request);
		if(currRole==-1){
			return null;//权限不足
		}
		DraftForm dform = (DraftForm)form;
		DraftQueryVO dQuery = dform.getDraftquery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		dQuery.setCurrRole(currRole);
		dQuery.setCurrUser(user);
		/**
		 * 银行角色的clientId存的是银行的主键id
		 * 监管员角色的clientId存的是储备库主键Id
		 */
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("draftList",request);
		thumbPageTools.saveQueryVO(dQuery);//记录查询条件,用于查询条件变更时返回第一页
		List<DraftQueryBean> list = service.searchDraftListByPage(dQuery, thumbPageTools);
			
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		int roleId = RoleUtil.getCurrRole(UserSessionUtil.getUserSession(request), roles);
		
		request.setAttribute("dealersOptions", OptionUtil.getDealersByYW(user.getId(),roleId));

		//返回列表页面
		return mapping.findForward("draft_list");
	}
	
	
	public ActionForward addDraftEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int roleId = RoleUtil.getCurrRole(UserSessionUtil.getUserSession(request), roles);
		
		if (roleId == RoleConstants.BANK_APPROVE.getCode()) {
			request.setAttribute("dealersOptions", OptionUtil.getDealersByClientId(user.getClient_id()));
		}else if (roleId == RoleConstants.SUPERVISORY.getCode()) {
			request.setAttribute("dealersOptions", OptionUtil.getDealersBySUPERVISOR(user.getClient_id()));
		}else{
			request.setAttribute("dealersOptions", OptionUtil.getDealersByYW(user.getId(),roleId));
		}
		
		return mapping.findForward("add_draft");
	}
	
	public ActionForward addDraft(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DraftForm dform = (DraftForm)form;
		DraftService service = new DraftService();
		
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int roleId = RoleUtil.getCurrRole(UserSessionUtil.getUserSession(request), roles);
		
		
		request.setAttribute("dealersOptions", OptionUtil.getDealersByYW(user.getId(),roleId));
		
		
		String draftnum = dform.getDraft().getDraft_num();
		
		boolean bool = service.validateDraftIsRepeat(draftnum);
		
		if(bool){
			request.setAttribute("message", "汇票号重复");
			return mapping.findForward("add_draft");
		}
		
		DraftVO dvo = dform.getDraft();
		dvo.setCreateuserid(user.getId());
		dvo.setCreatedate(new Date());
		
		if(StringUtil.isEmpty(dvo.getBailscale())){
			//设置保证金比例为-
			dvo.setBailscale("-");
		}		
		//执行新增操作并获取操作结果
		boolean flag = service.addDraft(dvo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return draftList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_draft");
	}
	
	public ActionForward updDraftEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DraftForm dform = (DraftForm)form;
		DraftService service = new DraftService();
		
		//根据id获取修改对象
		DraftVO vo = service.loadDraftById(dform.getDraft().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return draftList(mapping, form, request, response);
		}
		
		dform.setDraft(vo);
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int roleId = RoleUtil.getCurrRole(UserSessionUtil.getUserSession(request), roles);
		
		//request.setAttribute("dealersOptions", OptionUtil.getDealersByYW(user.getId(),roleId));
		if (roleId == RoleConstants.BANK_APPROVE.getCode()) {
			request.setAttribute("dealersOptions", OptionUtil.getDealersByClientId(user.getClient_id()));
		}else if (roleId == RoleConstants.SUPERVISORY.getCode()) {
			request.setAttribute("dealersOptions", OptionUtil.getDealersBySUPERVISOR(user.getClient_id()));
		}else{
			request.setAttribute("dealersOptions", OptionUtil.getDealersByYW(user.getId(),roleId));
		}
		request.setAttribute("jj", vo.getDistribid());
		//返回修改页面
		return mapping.findForward("upd_draft");
	}
	
	public ActionForward updDraft(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DraftForm dform = (DraftForm)form;
		DraftService service = new DraftService();
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		DraftVO dvo = dform.getDraft();
		dvo.setUpduserid(user.getId());
		dvo.setUpddate(new Date());
		
		//执行修改操作并获取操作结果
		boolean flag = service.updDraft(dvo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return draftList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_draft");
	}
	
	public ActionForward delDraft(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DraftForm dform = (DraftForm)form;
		DraftService service = new DraftService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteDraft(dform.getDraft().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return draftList(mapping, form, request, response);
	}
	
	public ActionForward importExcelEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("import_draft");
	}
	
	public ActionForward importExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DecimalFormat moneyDf = new DecimalFormat("0.00");
		int currRole = getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		DraftForm dform = (DraftForm)form;
		FormFile file =  dform.getExcelfile();
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		List<String[]> values = (List<String[]>) importFile.readAll(0,new DraftRowMapper());
		if(values==null||(!validateExcel(values.get(0)))){//第一行肯定为标题
			request.setAttribute("message", "导入模板格式不正确");
			return importExcelEntry(mapping, form, request, response);
		}
		if(values.size()<=1){
			request.setAttribute("message", "导入的文件汇票信息不能为空");
			return importExcelEntry(mapping, form, request, response);
		}
		List<DraftVO> draftList = new ArrayList<DraftVO>();
		List<String> ids = service.findDistribIds(user.getId());//业务专员对应的经销商id
		List<String> excelDraftNums = new ArrayList<String>();
		for (int i = 1; i < values.size(); i++) {
			DraftVO vo = new DraftVO();
			String[] value = values.get(i);
			String agreement = value[1];
			String bail_num = value[2];
			String dealerName = value[3];
			String bankName = value[4];
			String brand = value[5];
			String draftNum = value[6];
			String billing_date = value[7];
			String due_date = value[8];
			String billing_money = value[9];
			String bailscale = value[10];
			Date billingDate=null;
			Date dueDate = null;
			int dealerId = 0;
			
			if(StringUtil.isEmpty(dealerName)){
				request.setAttribute("message", "第"+(i+1)+"行经销商名称不能为空");
				return importExcelEntry(mapping, form, request, response);
			}
			if(StringUtil.isEmpty(bailscale) || !NumberUtils.isNumber(bailscale)){
				request.setAttribute("message", "第"+(i+1)+"行保证金比例不能为空或者格式不对");
				return importExcelEntry(mapping, form, request, response);
			}
			
			Double bas = Double.valueOf(bailscale);
			if (bas>100||bas<0) {
				request.setAttribute("message", "第"+(i+1)+"行保证金比例有误请核实");
				return importExcelEntry(mapping, form, request, response);
			}
			
			if(StringUtil.isEmpty(bankName)){
				request.setAttribute("message", "第"+(i+1)+"行金融机构名称不能为空");
				return importExcelEntry(mapping, form, request, response);
			}
			if(StringUtil.isEmpty(brand)){
				request.setAttribute("message", "第"+(i+1)+"行品牌名称不能为空");
				return importExcelEntry(mapping, form, request, response);
			}
			if(StringUtil.isEmpty(draftNum)){
				request.setAttribute("message", "第"+(i+1)+"行汇票号码不能为空");
				return importExcelEntry(mapping, form, request, response);
			}else{
				draftNum = draftNum.trim();
				if(excelDraftNums.contains(draftNum)){
					request.setAttribute("message", "第"+(i+1)+"行汇票号码在当前表格重复");
					return importExcelEntry(mapping, form, request, response);
				}else{
					excelDraftNums.add(draftNum);
				}
			}
			if(service.validateDraftIsRepeat(draftNum)){//验证汇票在数据库中是否重复
				request.setAttribute("message", "第"+(i+1)+"行汇票号码在系统中已存在");
				return importExcelEntry(mapping, form, request, response);
			}
			if(StringUtil.isEmpty(billing_date)){
				request.setAttribute("message", "第"+(i+1)+"行开票日不能为空");
				return importExcelEntry(mapping, form, request, response);
			}else{
				billingDate = DateUtil.getDateFormatByString(billing_date, "yyyy-MM-dd");
				if(billingDate==null){
					request.setAttribute("message", "第"+(i+1)+"行开票日格式不正确");
					return importExcelEntry(mapping, form, request, response);
				}
			}
			if(StringUtil.isEmpty(due_date)){
				request.setAttribute("message", "第"+(i+1)+"行到期日不能为空");
				return importExcelEntry(mapping, form, request, response);
			}else{
				dueDate = DateUtil.getDateFormatByString(due_date, "yyyy-MM-dd");
				if(dueDate==null){
					request.setAttribute("message", "第"+(i+1)+"行到期日格式不正确");
					return importExcelEntry(mapping, form, request, response);
				}
			}
			
			if (billingDate != null && dueDate != null) {
				int dates = billingDate.compareTo(dueDate);
				if (dates >= 0) {
					request.setAttribute("message", "第"+(i+1)+"行开票日等于或晚于到期日");
					return importExcelEntry(mapping, form, request, response);
				}
			}
			
			if(StringUtil.isEmpty(billing_money)){
				request.setAttribute("message", "第"+(i+1)+"行开票金额不能为空");
				return importExcelEntry(mapping, form, request, response);
			}else{
				if(!NumberUtils.isNumber(billing_money)){
					request.setAttribute("message", "第"+(i+1)+"行开票金额格式不正确");
					return importExcelEntry(mapping, form, request, response);
				}
			}
			
			DealerQueryVO dquery = new DealerQueryVO();
			dquery.setDealerName(dealerName);
			List<DealerVO> dealerList = dealerService.searchDealerList(dquery);//根据经销商名称查询经销商
			if(dealerList!=null&&dealerList.size()>0){
				boolean bankIsHave = false;
				boolean brandIsHave = false;
				for (DealerVO dealerVO : dealerList) {
					dealerId = dealerVO.getId();
					String dealerBankName = dealerService.getBankNameByDealerId(dealerVO.getId());
					if(dealerBankName!=null&&dealerBankName.equals(bankName)){
						bankIsHave = true;
						BrandVO brandvo = brandService.loadBrandById(dealerVO.getBrandId());
						if(brandvo!=null&&brandvo.getName()!=null&&brandvo.getName().equals(brand)){
							brandIsHave = true;
							break;
						}
					}
				}
				if(!bankIsHave){
					request.setAttribute("message", "第"+(i+1)+"行金融机构名称不正确");
					return importExcelEntry(mapping, form, request, response);
				}
				if(!brandIsHave){
					request.setAttribute("message", "第"+(i+1)+"行品牌名称不正确");
					return importExcelEntry(mapping, form, request, response);
				}
			}else{
				request.setAttribute("message", "第"+(i+1)+"行经销商名称不正确");
				return importExcelEntry(mapping, form, request, response);
			}
			//如果当前角色是业务专员
			if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&(ids==null||ids.size()==0||!ids.contains(dealerId+""))){
				request.setAttribute("message", "第"+(i+1)+"行，您的权限不足");
				return importExcelEntry(mapping, form, request, response);
			}
			
			vo.setAgreement(agreement);
			vo.setBailscale(bailscale);
			vo.setBail_num(bail_num);
			vo.setDistribid(dealerId);
			vo.setFinancial_institution(bankName);
			vo.setBrand(brand);
			vo.setDraft_num(draftNum);
			vo.setBilling_date(billingDate);
			vo.setDue_date(dueDate);
			vo.setBilling_money(moneyDf.format(Double.parseDouble(billing_money)));
			vo.setCreatedate(new Date());
			vo.setCreateuserid(user.getId());
			vo.setState(2);//未清票
			draftList.add(vo);
		}
		boolean flag = service.addDraftList(draftList);
		if(flag){
			request.setAttribute("message", "汇票导入成功");
		}else{
			request.setAttribute("message", "汇票导入失败");
		}
		
		return importExcelEntry(mapping, form, request, response);
	}
	
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole = getCurrRole(request);
		if(currRole==-1){
			return null;//权限不足
		}
		DraftForm dform = (DraftForm)form;
		DraftQueryVO dQuery = dform.getDraftquery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		dQuery.setCurrRole(currRole);
		dQuery.setCurrUser(user);
		

		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("draftList",request);
		thumbPageTools.setPageSize(999999);
		List<DraftQueryBean> list = service.searchDraftListByPage(dQuery, thumbPageTools);

		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new DraftRowMapper(), export.createDefaultFileName("汇票"),"汇票", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回列表页面
		return null;
	}
	
	public ActionForward draftSuperviseList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DraftForm dform = (DraftForm)form;
		DraftService service = new DraftService();
		DealerSupervisorService dss = new DealerSupervisorService();
		
		DraftQueryVO dQuery = dform.getDraftquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(user.getClient_type() == ClientTypeConstants.BANK.getCode()){
			
			StringBuffer ids = new StringBuffer();
			DealerSupervisorQueryVO dssquery = new DealerSupervisorQueryVO();
			dssquery.setBankId(user.getClient_id());
			List<DealerSupervisorVO> dssList = dss.searchDealerSupervisorList(dssquery);
			if(dssList != null && dssList.size() > 0){
				for(DealerSupervisorVO dsvo : dssList){
					ids.append(dsvo.getDealerId()+",");
				}
				dQuery.setDistribids(ids.substring(0, ids.length() - 1));
			}else{
				dQuery.setDistribids("0");
			}
		}else if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
			StringBuffer ids = new StringBuffer();
			List<DraftVO> dList = dss.searchDraftSupervisorList(user.getId());
			if(dList != null && dList.size() > 0){
				for(DraftVO dvo : dList){
					ids.append(dvo.getDistribid()+",");
				}
				dQuery.setDistribids(ids.substring(0, ids.length() - 1));
			}else{
				dQuery.setDistribids("0");
			}
		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("draftSuperviseList",request);
		thumbPageTools.saveQueryVO(dQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<DraftQueryBean> list = service.searchDraftListByPage(dQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("draft_supervise_list");
	}
	
	   //汇票信息查看列表
		public ActionForward draftViewList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			DraftForm dform = (DraftForm)form;
			DraftQueryVO dQuery = dform.getDraftquery();
			UserVO user = UserSessionUtil.getUserSession(request).getUser();
			dQuery.setCurrUser(user);
			//初始化分页查询工具
			IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("draftViewList",request);
			thumbPageTools.saveQueryVO(dQuery);//记录查询条件,用于查询条件变更时返回第一页
			List<DraftQueryBean> list = service.searchDraftListByPage(dQuery, thumbPageTools);
			//将查询结果设置request中，用于页面显示
			request.setAttribute("list", list);
			setOptions(request);
			//返回列表页面
			return mapping.findForward("draft_viewList");
		}
		
	//汇票信息查看
	public ActionForward draftDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DraftForm dform = (DraftForm)form;
		DraftService service = new DraftService();
		DraftVO vo = service.loadDraftById(dform.getDraft().getId());
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "查询数据不存在");
			return draftViewList(mapping, form, request, response);
		}
		
		dform.setDraft(vo);
		setOptions(request);
		request.setAttribute("jj", vo.getDistribid());
		return mapping.findForward("draft_detail");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("isClearTicket", OptionUtil.isClearTicket());
	}
	
	private boolean validateExcel(String[] titles){
		String[] correctTiele = {"序号","质押协议号","保证金账号","经销商","金融机构","品牌","汇票号码","开票日","到日期","开票金额（元）"};
		for (int i = 0; i < correctTiele.length; i++) {
			if(titles[0]==null || (!correctTiele[i].equals(titles[i].trim())))
				return false;
		}
		return true;
	}
}
