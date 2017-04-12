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
import com.zd.csms.zxbank.web.excel.MoveDetailRowMapper;
import com.zd.csms.zxbank.web.excel.MoveRowMapper;
import com.zd.csms.zxbank.web.excel.ReceivingDetailRowMapper;
import com.zd.csms.zxbank.web.excel.ReceivingRowMapper;
import com.zd.csms.zxbank.web.excel.RemoveDetailRowMapper;
import com.zd.csms.zxbank.web.excel.RemoveRowMapper;
import com.zd.csms.zxbank.web.form.*;
import com.zd.csms.zxbank.web.mapper.CheckstockVOImportRowMapper;
import com.zd.csms.zxbank.web.mapper.CommodityImportRowMapper;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.IImportRowMapper;
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
	/**
	 * 通知推送
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
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
		String no;
		no = request.getParameter("rdno");
		if(no==null){
			no=request.getAttribute("rdno").toString();
		}
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
		//获得导出数据
		String no = request.getParameter("rdno");
		RemovePledge rp = rps.fingByNo(no);
		List<RemovePledgeDetail> list = rpds.findAll(no);

		//创建导出内容
		IExportFile export = new ExportFileExcelImpl();

		//处理需求内容
		List<RemovePledge> rps = new ArrayList<RemovePledge>();
		rps.add(rp);
		List<List<?>> dataLists = new ArrayList<List<?>>();
		List<IImportRowMapper> mappers = new ArrayList<IImportRowMapper>();
		IImportRowMapper mapper = new RemoveRowMapper();
		IImportRowMapper mapperDetail = new RemoveDetailRowMapper();
		dataLists.add(rps);
		dataLists.add(list);
		mappers.add(mapper);
		mappers.add(mapperDetail);

		try {
			//下载
			export.export(dataLists, mappers, export.createDefaultFileName("解除质押通知"), "解除质押通知", response);
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
		String no;
		no = request.getParameter("mdno");
		if(no==null){
			no=request.getAttribute("mdno").toString();
		}
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
	 * 移库通知书详情导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward movedetailOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String no = request.getParameter("mdno");
		List<MoveDetail> list = mds.findAll(no);
		MoveNotice mn = mns.fingByNo(no);

		//创建导出内容
		IExportFile export = new ExportFileExcelImpl();

		//处理需求内容
		List<MoveNotice> mns = new ArrayList<MoveNotice>();
		mns.add(mn);
		List<List<?>> dataLists = new ArrayList<List<?>>();
		List<IImportRowMapper> mappers = new ArrayList<IImportRowMapper>();
		IImportRowMapper mapper = new MoveRowMapper();
		IImportRowMapper mapperDetail = new MoveDetailRowMapper();
		dataLists.add(mns);
		dataLists.add(list);
		mappers.add(mapper);
		mappers.add(mapperDetail);

		try {
			//下载
			export.export(dataLists, mappers, export.createDefaultFileName("移库通知"), "移库通知", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		String no;
		no = request.getParameter("nyNo");
		if(no==null){
			no=request.getAttribute("nyNo").toString();
		}
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
		String no = request.getParameter("nyno");
		List<ReceivingDetail> list = rds.findAll(no);
		ReceivingNotice receiving = rns.getNotify(no);

		//创建导出内容
		IExportFile export = new ExportFileExcelImpl();

		//处理需求内容
		List<ReceivingNotice> rns = new ArrayList<ReceivingNotice>();
		rns.add(receiving);
		List<List<?>> dataLists = new ArrayList<List<?>>();
		List<IImportRowMapper> mappers = new ArrayList<IImportRowMapper>();
		IImportRowMapper mapper = new ReceivingRowMapper();
		IImportRowMapper mapperDetail = new ReceivingDetailRowMapper();
		dataLists.add(rns);
		dataLists.add(list);
		mappers.add(mapper);
		mappers.add(mapperDetail);

		try {
			//下载
			export.export(dataLists, mappers, export.createDefaultFileName("收货通知"), "收货通知", response);
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
	/**
	 * 通知推送
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward noticepush(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int ntType = Integer.parseInt(request.getParameter("ntType"));
		String ntNo = request.getParameter("ntNo");
		if(ntType==1){
			request.setAttribute("nyNo",ntNo);
			return receivingdetail(mapping, form, request, response);
		}else if(ntType==2){
			request.setAttribute("mdno", ntNo);
			return this.movedetail(mapping, form, request, response);
		}else if(ntType==3){
			request.setAttribute("rdno", ntNo);
			return removepledgedetail(mapping, form, request, response);
		}
		return mapping.findForward(""); 
	}
	
}
