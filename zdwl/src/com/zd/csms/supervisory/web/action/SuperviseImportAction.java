package com.zd.csms.supervisory.web.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.web.excel.SuperviseImportRowMapper;
import com.zd.csms.supervisory.web.form.SuperviseImportForm;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.file.FileUtil;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SuperviseImportAction extends ActionSupport {
	private SuperviseImportService service = new SuperviseImportService();
	private static final Logger logger = Logger.getLogger(SuperviseImportAction.class);
	/**
	 * 流程角色:市场部专员->招聘专员->业务部专员、、
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.BANK_APPROVE.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
			};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}

	public ActionForward superviseImportListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return superviseImportList(mapping, form,request, response);
	}
	
	
	public ActionForward superviseImportList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm)form;
		
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setState(1);
		if(currRole==RoleConstants.BANK_APPROVE.getCode()){
			siQuery.setType(2);
			siQuery.setUserid(user.getClient_id());
		}else if(currRole==RoleConstants.SUPERVISORY.getCode()){
			siQuery.setType(1);
			siQuery.setUserid(user.getId());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			siQuery.setType(3);
			siQuery.setUserid(user.getId());
		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseImportList",request);
		thumbPageTools.saveQueryVO(siQuery);//记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(50);
		//按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		
		//返回列表页面
		return mapping.findForward("supervise_import_list");
	}
	
	public ActionForward addSuperviseImportEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		//返回新增页面
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		return mapping.findForward("add_supervise_import");
	}
	
	public ActionForward addSuperviseImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;

		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		SuperviseImportVO sivo = siform.getSuperviseImport();
		sivo.setImporttime(new Date());
		sivo.setCertificate_intime(new Date());
		sivo.setCreateuserid(user.getId());
		sivo.setCreatedate(new Date());
		sivo.setUpduserid(user.getId());
		sivo.setUpddate(new Date());
		//执行新增操作并获取操作结果
		boolean flag = service.addSuperviseImport(sivo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return superviseImportList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_supervise_import");
	}
	
	public ActionForward updSuperviseImportEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm)form;
		//根据id获取修改对象
		SuperviseImportVO vo = service.loadSuperviseImportById(siform.getSuperviseImport().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return superviseImportList(mapping, form, request, response);
		}
		siform.setSuperviseImport(vo);
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		//返回修改页面
		return mapping.findForward("upd_supervise_import");
	}
	
	public ActionForward updSuperviseImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SuperviseImportForm siform = (SuperviseImportForm)form;
		DraftService ds = new DraftService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		SuperviseImportVO sivo = siform.getSuperviseImport();
		sivo.setApply(0);
		sivo.setState(1);
		sivo.setUpduserid(user.getId());
		sivo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updSuperviseImport(sivo,2);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return superviseImportList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_supervise_import");
	}
	
	public ActionForward delSuperviseImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSuperviseImport(siform.getSuperviseImport().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return superviseImportList(mapping, form, request, response);
	}
	public void setOptions(HttpServletRequest request){
		request.setAttribute("superviseStateOptions", OptionUtil.superviseState());
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		
	}
	
	public ActionForward importExcelEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		return mapping.findForward("import_supervise");
	}
	
	public ActionForward importExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SuperviseImportForm siform = (SuperviseImportForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String draftNum = request.getParameter("draftNum");//汇票号码
		if(StringUtil.isEmpty(draftNum)){
			request.setAttribute("message", "汇票号码不能为空");
			return importExcelEntry(mapping, form, request, response);
		}
		FormFile file =  siform.getExcelfile();
		FileUtil.saveFile(file);//保存导入车辆信息，便于问题排查
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		List<String[]> values = (List<String[]>) importFile.readAll(0,new SuperviseImportRowMapper());
		if(values==null||(!validateExcel(values.get(0)))){//第一行肯定为标题
			request.setAttribute("message", "导入模板格式不正确");
			return importExcelEntry(mapping, form, request, response);
		}
		if(values.size()<=1){
			request.setAttribute("message", "导入的文件车辆信息不能为空");
			return importExcelEntry(mapping, form, request, response);
		}
		List<SuperviseImportVO> list = values2SuperviseImportVO(values);
		List<String> excelVins = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			SuperviseImportVO vo = list.get(i);
			if(StringUtil.isEmpty(vo.getVin())){
				request.setAttribute("message", "第"+(i+1)+"行的车架号不能为空");
				return importExcelEntry(mapping, form, request, response);
			}else{
				vo.setVin(vo.getVin().trim());
				if(17!=vo.getVin().length()){
					request.setAttribute("message","第"+(i+1)+"行的车架号位数不正确");
					return importExcelEntry(mapping, form, request, response);
				}
			}
			if(excelVins.contains(vo.getVin())){
				request.setAttribute("message", "第"+(i+1)+"行的车架号为"+vo.getVin()+"在表格中重复");
				return importExcelEntry(mapping, form, request, response);
			}else{
				excelVins.add(vo.getVin());
			}
			
			if(StringUtil.isEmpty(vo.getMoney())){
				request.setAttribute("message", "第"+(i+1)+"行的金额格式不正确");
				return importExcelEntry(mapping, form, request, response);
			}
		}
		
		boolean flag = service.addSuperviseImportList(list, draftNum, user);
		if(flag){
			request.setAttribute("message", "导入成功");
		}else{
			request.setAttribute("message", "导入失败，请联系管理员");
		}
		return superviseImportList(mapping, form, request, response);
	}
	
	/**
	 * 验证excel的表头格式是否正确
	 * @return
	 */
	private boolean validateExcel(String[] titles){
		String[] correctTiele = {"序号","合格证发证日期","合格证号","车辆型号","车身结构","排量","颜色","发动机号","车架号","钥匙号","金额","备注"};
		for (int i = 0; i < correctTiele.length; i++) {
			if(titles[i]==null || (!correctTiele[i].equals(titles[i].trim())))
				return false;
		}
		return true;
	}
	
	private List<SuperviseImportVO> values2SuperviseImportVO(List<String[]> values){
		DecimalFormat moneyDf = new DecimalFormat("0.00");
		DecimalFormat keyNumDf = new DecimalFormat("0");
		List<SuperviseImportVO> list = new ArrayList<SuperviseImportVO>();
		for (int i = 1; i < values.size(); i++) {
			if(values.get(i).length<11){//为了防止空行影响数据，不满11列的数据直接忽视
				continue;
			}
			SuperviseImportVO vo = new SuperviseImportVO();
			String[] value = values.get(i);
			String certificate_date = value[1];//合格证发放日期
			String certificateNum = value[2];//合格证号
			String carModel = value[3];//车辆型号
			String carStructure = value[4];//车身结构
			String displacement = value[5];//排量
			String color = value[6];//颜色
			String engine_num = value[7];//发动机号
			String vin = value[8];//车架号
			String keyNum = value[9];//钥匙号
			String money = value[10];//金额
			String des = value.length>11?value[11]:"";//备注
			if(StringUtil.isNotEmpty(certificate_date)){
				vo.setCertificate_date(DateUtil.getDateFormatByString(certificate_date, "yyyy-MM-dd"));
			}
			vo.setCertificate_num(certificateNum);
			vo.setCar_model(carModel);
			vo.setCar_structure(carStructure);
			vo.setDisplacement(displacement);
			vo.setColor(color);
			if(StringUtil.isNotEmpty(engine_num)){
				if(NumberUtils.isNumber(engine_num)){
					vo.setEngine_num(keyNumDf.format(Double.parseDouble(engine_num)));
				}else{
					vo.setEngine_num(engine_num);
				}
			}
			vo.setVin(vin);
			if(StringUtil.isNotEmpty(keyNum)){
				if(NumberUtils.isNumber(keyNum)){
					vo.setKey_num(keyNumDf.format(Double.parseDouble(keyNum)));
				}else{
					vo.setKey_num(keyNum);
				}
			}
			if(StringUtil.isNotEmpty(money)&&NumberUtils.isNumber(money)){
				vo.setMoney(moneyDf.format(Double.parseDouble(money)));
			}
			vo.setDes(des);
			list.add(vo);
		}
		return list;
	}
	
	//superviseImportList
	//车辆查询信息
	public ActionForward searchCarInfo(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) {
				
				SuperviseImportForm siform = (SuperviseImportForm)form;
				UserVO user = UserSessionUtil.getUserSession(request).getUser();
				int currRole = getCurrRole(request);
				SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
				//siQuery.setState(1);
				if(currRole==RoleConstants.BANK_APPROVE.getCode()){
					siQuery.setType(2);
					siQuery.setUserid(user.getClient_id());
				}else if(currRole==RoleConstants.SUPERVISORY.getCode()){
					siQuery.setType(1);
					siQuery.setUserid(user.getId());
				}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
					siQuery.setType(3);
					siQuery.setUserid(user.getId());
				}
				
				//初始化分页查询工具
				IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("searchCarInfo",request);
				thumbPageTools.saveQueryVO(siQuery);//记录查询条件,用于查询条件变更时返回第一页
				thumbPageTools.setPageSize(50);
				//按条件查询分页数据
				List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
				//将查询结果设置request中，用于页面显示
				request.setAttribute("list", list);
				setOptions(request);
				
				request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
				
				//返回列表页面
				return mapping.findForward("supervise_deleCarInfo");
			}
	
	/**
	 * 批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
		public ActionForward batchDelSuperviseImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		String ids = request.getParameter("ids");

		//执行删除操作并获取结果
		boolean flag = service.batchDeleteSuperviseImport(ids,request);
				
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//日志输出
		logger.info("删除操作人:"+user+"\t操作时间:"+System.currentTimeMillis()+"\t车架号:"+siform.getSuperviseImportquery().getVin());
		//返回列表页面
		return searchCarInfo(mapping, form, request, response);
	}
}
