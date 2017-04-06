package com.zd.csms.zxbank.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PRTokeniser;
import com.sun.script.javascript.JSAdapter;
import com.zd.core.ActionSupport;
import com.zd.core.JSONAction;
import com.zd.core.SystemProperty;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.dao.*;
import com.zd.csms.zxbank.dao.oracle.*;
import com.zd.csms.zxbank.model.*;
import com.zd.csms.zxbank.service.*;
import com.zd.csms.zxbank.util.ExcelUtil;
import com.zd.csms.zxbank.util.SocketClient;
import com.zd.csms.zxbank.util.ZhongXinBankUtil;
import com.zd.csms.zxbank.web.excel.RemoveRowMapper;
import com.zd.csms.zxbank.web.form.*;
import com.zd.csms.zxbank.web.mapper.CheckstockVOImportRowMapper;
import com.zd.csms.zxbank.web.mapper.CommodityImportRowMapper;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;
import com.zd.tools.thumbPage.impl.ThumbPageToolsForOracle;

public class ZXBankInterfaceAction extends ActionSupport {

	/*private static final String host;
	private static final int port;
	private static final String currentCustNo;
	private static final String custType;
	private static final int pageSize = 10;
	private static final int pageMaxNum = 50;*/

	/*
	 * private static final int[] roles = new int[]{
	 * RoleConstants.SUPERVISORY.getCode()ZXBankDockDao };
	 */
	// 相关用户登录的缓存信息
	/*
	 * private int getCurrRole(HttpServletRequest request){ UserSession user =
	 * UserSessionUtil.getUserSession(request); return
	 * RoleUtil.getCurrRole(user, roles); }
	 */

	/*static {
		host = SystemProperty.getPropertyValue("bankdock.properties", "zs.host");
		port = Integer.parseInt(SystemProperty.getPropertyValue("bankdock.properties", "zs.port"));
		currentCustNo = SystemProperty.getPropertyValue("bankdock.properties", "zs.currentCustNo");
		custType = SystemProperty.getPropertyValue("bankdock.properties", "zs.custType");
	}*/

	/* private ZXIBankDockDao dao = ZXBankDAOFactory.getBankDockDAO(); */

	// 客户接口
	private CustomerService cs = new CustomerService();
	// 经销商参数接口
	private DistribsetService dis = new DistribsetService();

	private WareHouseService whs = new WareHouseService();
	// 通知推送接口
	private NoticeService ns = new NoticeService();
	// 融资信息业务
	private FinancingService fs = new FinancingService();
	// 监管协议查询
	private AgreementService as = new AgreementService();
	// 解除质押通知
	private RemovePledgeService rps = new RemovePledgeService();
	// 解除质押通知详情
	private RemovePledgeDetailService rpds = new RemovePledgeDetailService();
	// 移库通知
	private MoveNoticeService mns = new MoveNoticeService();
	// 移库通知详情
	private MoveDetailService mds = new MoveDetailService();
	// 发货通知
	private ReceivingNoticeService rns = new ReceivingNoticeService();
	// 发货通知详情
	private ReceivingDetailService rds = new ReceivingDetailService();
	//质物入库查询
	private GagerService gds = new GagerService();
	//质物入库详情信息。
	private CommodityService cds = new CommodityService();
	//盘库信息查询 详情查询
	private CheckstockService ckds = new CheckstockService();

	public ActionForward distribset(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("cuslist");
	}

	// 经销商 参数ajax查询经销商参数的
	public ActionForward disajax(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		List<DistribsetZX> distri = dis.findorg(request.getParameter("custinput"));
		JSONArray json = JSONArray.fromObject(distri);
		try {
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分页查询数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ActionForward customer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		CustomerForm cust = (CustomerForm) form;
		Customer dquery = cust.getCustomer();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Customer", request);
		tools.setPageSize(3);
		tools.saveQueryVO(dquery);
		List<Customer> list = cs.findcustallList(dquery, tools);

		/* List<DistribsetZX> list1=dis.findorg(); */
		/* request.setAttribute("list1", list1); */
		request.setAttribute("list", list);
		return mapping.findForward("cuslist");
	}

	/**
	 * 仓库信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward warehouse(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			WarehouseForm WarHouseform = (WarehouseForm) form;
			Warehouse query = WarHouseform.getWarehouse();// 查询条件获取
			IThumbPageTools tools = ToolsFactory.getThumbPageTools("Warehouse", request);// 获取分页模板
			tools.setPageSize(2);// 设置当页面显示条数
			List<Warehouse> list = whs.findBusinessList(query, tools);// 获取分页后的数据list
			request.setAttribute("list", list);
			request.setAttribute("cusNo", query.getCustNo());
			request.setAttribute("whName", query.getWhName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("warehouse");
	}

	public ActionForward findnotice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NoticeForm not = (NoticeForm) form;
		Notice dquery = not.getNotice();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Notice", request);
		tools.setPageSize(3);
		tools.saveQueryVO(dquery);
		List<Notice> type = ns.findnoticetype();
		List<Notice> list = ns.findNotice(dquery, tools);
		request.setAttribute("list1", type);
		request.setAttribute("list", list);
		return mapping.findForward("noticelist");
	}

	/**
	 * 融资信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward financing(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			FinancingForm form = (FinancingForm) actionform;
			FinancingQueryVO query = form.getFinancingVO();
			IThumbPageTools tools = ToolsFactory.getThumbPageTools("Financing", request);

			// 设置每页显示几条数据
			tools.setPageSize(2);
			// 从数据库查询
			List<Financing> list = fs.findByQuery(query, tools);
			System.out.println(list.get(0));
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("financing");
	}

	/**
	 * 监管协议查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward agreement(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AgreementForm agreementfrom = (AgreementForm) form;
		Agreement query = agreementfrom.getAgreement();// 获取查询条件
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Agreement", request);// 分页模板获取
		tools.setPageSize(2);
		List<Agreement> list = as.findBusinessList(query, tools);// 分页数据查询
		request.setAttribute("list", list);
		request.setAttribute("loncpname", query.getAg_loncpname());
		request.setAttribute("custno", query.getAg_custno());
		return mapping.findForward("agreement");
	}

	/**
	 * 解除质押通知查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removepledge(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得页面查询数据
		RemovePledgeForm form = (RemovePledgeForm) actionform;
		RemovePledge query = form.getRemovepledge();
		// 分页工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("RemovePledge", request);
		// 设置分页条数
		tools.setPageSize(2);
		// 获得数据
		List<RemovePledge> list = rps.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("list", list);
		return mapping.findForward("removepledge");
	}

	/**
	 * 解除质押通知详情查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removepledgedetail(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String no = request.getParameter("rdno");
		// 获得页面查询数据
		RemovePledgeDetail query = new RemovePledgeDetail();
		query.setRdNo(no);
		// 分页工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("RemovePledgeDetail", request);
		// 设置分页条数
		tools.setPageSize(2);
		// 获得数据
		RemovePledge rp = rps.fingByNo(no);
		List<RemovePledgeDetail> list = rpds.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("list", list);
		request.setAttribute("rp", rp);

		return mapping.findForward("removepledgedetail");
	}

	/**
	 * 解除质押通知书详情导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removepledgedetailOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String no = request.getParameter("rdno");
		RemovePledge rp = rps.fingByNo(no);
		List<RemovePledgeDetail> list = rpds.findAll(no);

		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new RemoveRowMapper(), export.createDefaultFileName("解除质押通知详情"), "解除质押通知详情", response);
			System.out.println("come here");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 移库通知查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward movenotice(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获得页面查询数据
		MoveNoticeForm form = (MoveNoticeForm) actionform;
		MoveNotice query = form.getMovenotice();
		// 分页工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("MoveNotice", request);
		// 设置分页条数
		tools.setPageSize(2);
		// 获得数据
		List<MoveNotice> list = mns.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("list", list);

		return mapping.findForward("movenotice");
	}

	/**
	 * 移库通知详情查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward movedetail(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String no = request.getParameter("mdno");

		// 获得页面查询数据
		MoveDetail query = new MoveDetail();
		query.setMdNo(no);
		// 分页工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("MoveDetail", request);
		// 设置分页条数
		tools.setPageSize(2);
		// 获得数据
		MoveNotice mn = mns.fingByNo(no);
		List<MoveDetail> list = mds.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("list", list);
		request.setAttribute("mn", mn);

		return mapping.findForward("movedetail");
	}

	/**
	 * 收货通知书
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward receivingnotice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReceivingNoticeForm notifyrom = (ReceivingNoticeForm) form;
		ReceivingNotice query = notifyrom.getReceivingnotice();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("ReceivingNotice", request);
		tools.setPageSize(2);
		List<ReceivingNotice> list = rns.findBusinessList(query, tools);
		request.setAttribute("list", list);
		request.setAttribute("nyNo", query.getNyNo());
		request.setAttribute("nyLonentname", query.getNyLonentname());
		return mapping.findForward("receivingnotice");
	}

	/**
	 * 收货通知书详情
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward receivingdetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String no = request.getParameter("nyNo");
		ReceivingDetail query = new ReceivingDetail();
		query.setNdNo(no);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("ReceivingDetail", request);
		tools.setPageSize(2);
		List<ReceivingDetail> list = rds.findBusinessList(query, tools);

		ReceivingNotice receiving = rns.getNotify(no);
		request.setAttribute("list", list);
		request.setAttribute("receiving", receiving);

		return mapping.findForward("receivingdetail");
	}

	/**
	 * 收货通知书详情导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward receivingdetailOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String no = request.getParameter("nyNo");
		ReceivingDetail query = new ReceivingDetail();
		query.setNdNo(no);

		List<ReceivingDetail> list = rds.findAll(query);

		ReceivingNotice receiving = rns.getNotify(no);
		request.setAttribute("list", list);
		request.setAttribute("receiving", receiving);

		//生成EXCEL
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
		HSSFSheet sheet = wb.createSheet("收货通知");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中  
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

		//收货通知
		String[] values = { "收货通知书编号", "核心企业名称", "(在库)监管企业名称", "(在途)监管企业名称", "运输企业名称", "借款企业ID", "借款企业名称", "发货日期",
				"预计到港(库)日期", "预计到港(库)", "纸质监管协议编号", "通知书日期", "货物价值合计", "交货地点", "总记录", "备注" };
		ExcelUtil.setHead(row, values, style);

		//写入数据
		row = sheet.createRow(1);
		row.createCell(0).setCellValue(receiving.getNyNo());
		row.createCell(1).setCellValue(receiving.getNyCorentnm());
		row.createCell(2).setCellValue(receiving.getNySpventnm());
		row.createCell(3).setCellValue(receiving.getNyOnwspvenm());
		row.createCell(4).setCellValue(receiving.getNyTrsptentnm());
		row.createCell(5).setCellValue(receiving.getNyLonentno());
		row.createCell(6).setCellValue(receiving.getNyLonentname());
		row.createCell(7).setCellValue(receiving.getNyCreatedate());
		row.createCell(8).setCellValue(receiving.getNyEta());
		row.createCell(9).setCellValue(receiving.getNyEpa());
		row.createCell(10).setCellValue(receiving.getNyOfflnsatno());
		row.createCell(11).setCellValue(receiving.getNyNtcdate());
		row.createCell(12).setCellValue(receiving.getNyTtlcmdval());
		row.createCell(13).setCellValue(receiving.getNyExcplace());
		row.createCell(14).setCellValue(receiving.getNyTotnum());
		row.createCell(15).setCellValue(receiving.getNyRemark());

		//详情
		String[] details = { "实际订单纸质编号", "实际订单名称", "商品代码", "商品名称", "发货数量", "计量单位", "发货单价", "发货价值", "SCF放款批次号",
				"质押合同编号", "融资编号", "合格证编号", "车架号", "车价", "备注" };
		row = sheet.createRow(3);
		ExcelUtil.setHead(row, values, style);

		// 第五步，写入实体数据 
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 4);
			ReceivingDetail rd = list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(rd.getNdIdtplanno());
			row.createCell(1).setCellValue(rd.getNdIdtplannm());
			row.createCell(2).setCellValue(rd.getNdCmdcode());
			row.createCell(3).setCellValue(rd.getNdCmdname());
			row.createCell(4).setCellValue(rd.getNdCsnnum());
			row.createCell(5).setCellValue(rd.getNdUnit());
			row.createCell(6).setCellValue(rd.getNdCsnprc());
			row.createCell(7).setCellValue(rd.getNdReqcsnval());
			row.createCell(8).setCellValue(rd.getNdScflonno());
			row.createCell(9).setCellValue(rd.getNdMtgcntno());
			row.createCell(10).setCellValue(rd.getNdLoancode());
			row.createCell(11).setCellValue(rd.getNdHgzno());
			row.createCell(12).setCellValue(rd.getNdVin());
			row.createCell(13).setCellValue(rd.getNdCarprice());
			row.createCell(14).setCellValue(rd.getNdRemark());
		}

		// 第六步，将文件存到指定位置  
		try {
			FileOutputStream fout = new FileOutputStream("D:/收货通知.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 质物入库导入文件
	 * 
	 * @throws FileNotFoundException
	 */
	public ActionForward pergager(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setOptions(request);

		// 获得form
		BankInterfaceForm bForm = (BankInterfaceForm) form;

		Gager gager = bForm.getGager();
		// 设置其他必填参数
		FormFile file = bForm.getImportFile();

		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);

		@SuppressWarnings("unchecked")
		List<Commodity> list = (List<Commodity>) importFile.readAll(1, new CommodityImportRowMapper());

		if (list == null || list.size() == 0) {
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("gagerApp");
		}
		request.setAttribute("list", list);
		request.setAttribute("gager", gager);
		return mapping.findForward("gagerApp");
	}

	/**
	 * 质物入库
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gagerApp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setOptions(request);

		// 获得form
		BankInterfaceForm bForm = (BankInterfaceForm) form;
		// 设置其他必填参数
		Gager gager = bForm.getGager();

		if (StringUtil.isEmpty(gager.getGaLonentno()) || StringUtil.isEmpty(gager.getGaOprtname())
				|| StringUtil.isEmpty(gager.getGaOrderno()) || gager.getGaCount() <= 0) {
			return mapping.findForward("gagerApp");
		}

		FormFile file = bForm.getImportFile();

		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);

		@SuppressWarnings("unchecked")
		List<Commodity> list = (List<Commodity>) importFile.readAll(1, new CommodityImportRowMapper());

		// 测试查询XLSX数据
		for (Commodity commodity : list) {
			System.out.println(commodity);
		}

		if (list == null || list.size() == 0) {
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("gagerApp");
		}

		/*
		 for (int i = 0; i < list.size(); i++) {
			Gyl016VO gyl016 = list.get(i);
			if(StringUtil.isEmpty(gyl016.getModel())){
				request.setAttribute("message", "第"+(i+2)+"行押品规格型号不能为空");
				return mapping.findForward("pregyl016");
			}
			if(StringUtil.isEmpty(gyl016.getQuantity())){
				request.setAttribute("message", "第"+(i+2)+"行数量/重量不能为空");
				return mapping.findForward("pregyl016");
			}
			if(StringUtil.isEmpty(gyl016.getMortgageUnits())){
				request.setAttribute("message", "第"+(i+2)+"行数量/重量 单位不能为空");
				return mapping.findForward("pregyl016");
			}
			if(StringUtil.isEmpty(gyl016.getPrice())){
				request.setAttribute("message", "第"+(i+2)+"行买入单价不能为空");
				return mapping.findForward("pregyl016");
			}
			if(StringUtil.isEmpty(gyl016.getChassisNo())){
				request.setAttribute("message", "第"+(i+2)+"行车架号不能为空");
				return mapping.findForward("pregyl016");
			}
		}
		 */
		request.setAttribute("list", list);
		// request.getSession().setAttribute("gyl016List", list);
		return mapping.findForward("gagerApp");
	}

	/**
	 * 质物入库信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gager(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BankInterfaceForm gform = (BankInterfaceForm) form;
		Gager query = gform.getGager();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Gager", request);
		tools.setPageSize(2);
		List<Gager> list = gds.findBusinessList(query, tools);
		request.setAttribute("gaLonentno", query.getGaLonentno());
		request.setAttribute("list", list);
		return mapping.findForward("gager");
	}

	/**
	 * 质物入库详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward commodity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Commodity query = new Commodity();
		query.setCmGaid(Integer.parseInt(request.getParameter("gaId")));

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Commodity", request);
		tools.setPageSize(2);
		List<Commodity> list = cds.findBusinessList(query, tools);
		request.setAttribute("list", list);
		Gager gager = gds.getGager(Integer.parseInt(request.getParameter("gaId")), tools);
		request.setAttribute("Gager", gager);
		return mapping.findForward("commodity");
	}

	public ActionForward initstock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("stocktaking");
	}

	/**
	 * 盘库信息导入文件
	 * 
	 * @throws FileNotFoundException
	 */
	public ActionForward perstock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setOptions(request);

		// 获得form
		BankInterfaceForm bForm = (BankInterfaceForm) form;

		//Gager gager = bForm.getGager();
		// 设置其他必填参数
		FormFile file = bForm.getImportFile();

		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);

		@SuppressWarnings("unchecked")
		List<CheckstockVO> list = (List<CheckstockVO>) importFile.readAll(1, new CheckstockVOImportRowMapper());

		List<Checkwarehouse> wlist = new ArrayList<Checkwarehouse>();
		List<Check> clist = new ArrayList<Check>();
		for (CheckstockVO csVO : list) {
			Boolean flag = false;
			String whcode = csVO.getWhcode();
			if (wlist.size() > 0) {
				for (Checkwarehouse cwt : wlist) {
					if (cwt.getChWhcode().equals(whcode)) {
						int num = cwt.getChNum();
						cwt.setChNum(++num);
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				Checkwarehouse cw = new Checkwarehouse();
				cw.setChWhcode(csVO.getWhcode());
				cw.setChWhlevel(csVO.getWhlevel());
				cw.setChWhaddr(csVO.getWhaddr());
				cw.setChNum(1);
				wlist.add(cw);
			}

			Check c = new Check();
			c.setCkSpvwhcode(whcode);
			c.setCkCmcode(csVO.getCmcode());
			c.setCkCstkcmdnum(Integer.parseInt(csVO.getNum()));
			c.setCkCmgrtcntno(csVO.getCmgrtcntno());
			c.setCkVin(csVO.getVin());
			clist.add(c);
			flag = false;
		}

		if (list == null || list.size() == 0) {
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("stocktaking");
		}
		request.setAttribute("list", list);
		//request.setAttribute("gager", gager);
		return mapping.findForward("stocktaking");
	}
	
	/**
	 * 盘库信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkstock(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		BankInterfaceForm bform=(BankInterfaceForm)form;
		Checkstock query=bform.getCheckstock();
		IThumbPageTools tools=ToolsFactory.getThumbPageTools("Checkstock",request);
		tools.setPageSize(2);
		List<Checkstock> list=ckds.findBusinessList(query, tools);
		request.setAttribute("list", list);
		for (Checkstock checkstock : list) {
			System.out.println(checkstock.toString());
		}
		request.setAttribute("csLoncpid",query.getCsLoncpid());
		return mapping.findForward("checkstock");
	}
	/**
	 * 盘库详情信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkstockDetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int id=Integer.parseInt(request.getParameter("csid"));
		int loncpid=Integer.parseInt(request.getParameter("loncpid"));
		
		IThumbPageTools tools=ToolsFactory.getThumbPageTools("CheckstockVO",request);
		tools.setPageSize(1);
		Checkstock checkstock=ckds.getCheckstock(loncpid);//获取共同盘库信息部分
		List<CheckstockVO> list=ckds.findAllVOList(id, tools);//获取详情信息列表
		
		request.setAttribute("checkstock",checkstock);
		request.setAttribute("list", list);
		return mapping.findForward("checkstockDetail");
	}

	/*private boolean commonRequest(ActionMapping mapping, HttpServletRequest request, String listName,
			Class<?> resultClassType, String... field) throws Exception, DocumentException {
		Document doc = null;
		return commonRequest(mapping, request, doc, listName, resultClassType, field);
	}

	private boolean commonRequest(ActionMapping mapping, HttpServletRequest request, Document doc, String listName,
			Class<?> resultClassType, String... field) throws Exception, DocumentException {
		String methodName = request.getParameter("method");
		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, request, field);

		SocketClient socket = new SocketClient(host, port);
		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList(methodName, "");
		String xml = socket.send(head, body);

		xml = xml.substring(2);
		// 拼接返回值
		doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZhongXinBankUtil.getRetCode(ap.element("head"))) {
			return false;
		}

		Element bodyNode = ap.element("body");
		if (!bodyNode.hasContent()) {
			return false;
		}
		int totalCount = Integer.parseInt(bodyNode.element("totalCount").getText());
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPageSize", Integer.parseInt(bodyNode.element("currentPageSize").getText()));
		if (totalCount <= 0)
			return false;

		Element list = bodyNode.element(listName + "List");
		List infos = list.elements(listName + "Info");
		List resultList = new ArrayList();
		if (infos != null)
			for (Iterator it = infos.iterator(); it.hasNext();) {
				Element info = (Element) it.next();
				Object bean = ZhongXinBankUtil.getBean(resultClassType, info);
				resultList.add(bean);
			}
		request.setAttribute("list", resultList);
		request.setAttribute("doc", doc);
		return true;
	}*/
}
