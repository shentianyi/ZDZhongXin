package com.zd.csms.zxbank.web.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zd.core.ActionSupport;
import com.zd.core.SystemProperty;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.service.*;
import com.zd.csms.zxbank.util.SocketClient;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.util.ZhongXinBankUtil;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.csms.zxbank.web.bean.FinancingFar;
import com.zd.csms.zxbank.web.bean.WarehouseFar;
import com.zd.csms.zxbank.web.excel.MoveDetailRowMapper;
import com.zd.csms.zxbank.web.excel.MoveRowMapper;
import com.zd.csms.zxbank.web.excel.ReceivingDetailRowMapper;
import com.zd.csms.zxbank.web.excel.ReceivingRowMapper;
import com.zd.csms.zxbank.web.excel.RemoveDetailRowMapper;
import com.zd.csms.zxbank.web.excel.RemoveRowMapper;
import com.zd.csms.zxbank.web.form.*;
import com.zd.csms.zxbank.web.mapper.CheckstockVOImportRowMapper;
import com.zd.csms.zxbank.web.mapper.CommodityImportRowMapper;
import com.zd.csms.zxbank.web.server.ReturnReceiptServer;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.IImportRowMapper;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ZXBankInterfaceAction extends ActionSupport {
	private static final String host;
	private static final int port;
	private static final String orgCode;

	static {
		host = SystemProperty.getPropertyValue("bankdock.properties", "zx.host");
		port = Integer.parseInt(SystemProperty.getPropertyValue("bankdock.properties", "zx.port"));
		orgCode=SystemProperty.getPropertyValue("bankdock.properties", "zx.orgCode");
	}

	// 客户接口
	//private CustomerService cs = new CustomerService();
	// 经销商参数接口
	//private DistribsetService dis = new DistribsetService();
	// 仓库信息接口
	//private WareHouseService whs = new WareHouseService();
	// 通知推送接口
	//private NoticeService ns = new NoticeService();
	// 融资信息业务
	//private FinancingService fs = new FinancingService();
	// 监管协议查询
	//private AgreementService as = new AgreementService();
	// 解除质押通知
	//private RemovePledgeService rps = new RemovePledgeService();
	// 解除质押通知详情
	//private RemovePledgeDetailService rpds = new RemovePledgeDetailService();
	// 移库通知
	//private MoveNoticeService mns = new MoveNoticeService();
	// 移库通知详情
	//private MoveDetailService mds = new MoveDetailService();
	// 发货通知
	//private ReceivingNoticeService rns = new ReceivingNoticeService();
	// 发货通知详情
	//private ReceivingDetailService rds = new ReceivingDetailService();
	//质物入库查询
	//private GagerService gds = new GagerService();
	//质物入库详情信息。
	//private CommodityService cds = new CommodityService();
	//盘库信息查询 详情查询
	//private CheckstockService ckds = new CheckstockService();

	/*public ActionForward distribset(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
*/
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
		NoticeService ns = new NoticeService();
		BankInterfaceForm not = (BankInterfaceForm) form;
		Notice dquery = not.getNotice();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Notice", request);
		tools.setPageSize(3);
		tools.saveQueryVO(dquery);
		List<Notice> types = ns.findnoticetype();
		List<Notice> list = ns.findNotice(dquery, tools);
		request.setAttribute("types", types);
		request.setAttribute("list", list);
		
		return mapping.findForward("noticelist");
	}

	/**
	 * 通知推送跳转
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
		if (ntType == 1) {
			request.setAttribute("nyNo", ntNo);
			return receivingdetail(mapping, form, request, response);
		} else if (ntType == 2) {
			request.setAttribute("mdno", ntNo);
			return this.movedetail(mapping, form, request, response);
		} else if (ntType == 3) {
			request.setAttribute("rdno", ntNo);
			return removepledgedetail(mapping, form, request, response);
		}
		return null;
	}

	public ActionForward noticeReread(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnReceiptServer rrs = new ReturnReceiptServer();
		int ntType = Integer.parseInt(request.getParameter("ntType"));
		String ntcno = request.getParameter("ntcno");
		String ntctp = null;
		if (ntType == 1) {
			ntctp = "DLCDRGNQ";
		} else if (ntType == 2) {
			ntctp = "DLCDTWNQ";
		} else if (ntType == 3) {
			ntctp = "DLCDUINQ";
		}
		if (ntctp != null) {
			rrs.FarQuery(ntcno, ntctp);
		} else {
			System.out.println("通知类型出错");
		}
		return findnotice(mapping, form, request, response);
	}

	/**
	 * 用户信息查询数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ActionForward customer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CustomerService cs = new CustomerService();
		//获取form表单数据
		BankInterfaceForm cust = (BankInterfaceForm) form;
		Customer query = cust.getCustomer();
		//查询方式
		int queryType = 0;
		if (request.getParameter("queryType") != null) {
			try {
				queryType = Integer.parseInt(request.getParameter("queryType"));
			} catch (Exception e) {
			}
		}
		System.out.println("客户查询方式：queryType:" + queryType);
		//远程查询返回数据
		if (queryType == 2) {
			System.out.println("--远程查询--");
			request.setAttribute("action", "DLCDCMLQ");
			//userName 需要等到整合时将Session中用户保存
			request.setAttribute("userName", "");
			request.setAttribute("orgCode", orgCode);
			boolean flg = commonRequest(mapping, request, "", CustomerFar.class, new String[] { "action", "userName",
					"orgCode" });
			if (flg) {
				System.out.println("--远程查询到的数据列表--");
				//保存或更新数据
				List resultList = (List) request.getAttribute("resultList");
				cs.autoUpdateCust(resultList);
			}
		}
		System.out.println("--开始本地查询--");
		//本地查询
		//根据类名获取分页工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Customer", request);
		//设置分页数据显示数量
		//tools.setPageSize(3);
		//保存查询条件
		tools.saveQueryVO(query);
		//调用本地查询方法传入条件及分页工具。返回数据
		List<Customer> list = cs.findcustallList(query, tools);
		//放入request会话域中
		request.setAttribute("list", list);
		request.setAttribute("customer", query);
		//返回视图名称
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
		WareHouseService whs = new WareHouseService();
		try {
			BankInterfaceForm WarHouseform = (BankInterfaceForm) form;
			Warehouse query = WarHouseform.getWarehouse();// 查询条件获取
			//查询方式
			int queryType = 0;
			if (request.getParameter("queryType") != null) {
				queryType = Integer.parseInt(request.getParameter("queryType"));
			}
			System.out.println("客户查询方式：queryType:" + queryType);
			//远程查询返回数据
			if (queryType == 2) {
				System.out.println("--远程查询--");//
				request.setAttribute("action", "DLCDWMLQ");//请求代码
				request.setAttribute("userName", "");//登录名
				request.setAttribute("hostNo", query.getCustNo());//- ECIF客户号
				boolean flg = commonRequest(mapping, request, "", WarehouseFar.class, new String[] { "action",
						"userName", "hostNo" });
				if (flg) {
					//保存或更新数据
					List resultList = (List) request.getAttribute("resultList");
					whs.autoUpdateWare(resultList, query);
				}
			}
			System.out.println("--开始本地查询--");
			//--开始本地查询--
			IThumbPageTools tools = ToolsFactory.getThumbPageTools("Warehouse", request);// 获取分页模板
			List<Warehouse> list = whs.findBusinessList(query, tools);// 获取分页后的数据list
			request.setAttribute("list", list);
			request.setAttribute("warehouse", query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("warehouse");
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
		FinancingService fs = new FinancingService();
		try {
			BankInterfaceForm form = (BankInterfaceForm) actionform;
			FinancingQueryVO query = form.getFinancingVO();

			//查询方式
			String choose = "";
			if (request.getParameter("queryType") != null) {
				choose = request.getParameter("queryType");
				System.out.println(choose);
			}

			if (choose.equals("2")) {
				request.setAttribute("action", "DLCDLMLQ");
				request.setAttribute("userName", "ceshi");
				request.setAttribute("loncpId", query.getFgLonentNo());
				request.setAttribute("loanstDate", query.getFgStDateStart());
				//远程查询
				boolean flg = commonRequest(mapping, request, "", FinancingFar.class, new String[] { "action",
						"userName", "loncpId", "loanstDate" });
				//处理远程查询并保存到本地服务器
				if (flg) {
					@SuppressWarnings("unchecked")
					List<FinancingFar> resultList = (List<FinancingFar>) request.getAttribute("resultList");
					System.out.println(resultList);
					fs.addOrUpdate(resultList, query);
				}
			}

			IThumbPageTools tools = ToolsFactory.getThumbPageTools("Financing", request);

			// 设置每页显示几条数据
			tools.setPageSize(2);
			// 从数据库查询
			List<Financing> list = fs.findByQuery(query, tools);
			request.setAttribute("list", list);
			request.setAttribute("financingVO", query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("financing");
	}

	/**
	 * 监管协议查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward agreement(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AgreementService as = new AgreementService();
		BankInterfaceForm agreementfrom = (BankInterfaceForm) form;
		Agreement query = agreementfrom.getAgreement();// 获取查询条件
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Agreement", request);// 分页模板获取
		tools.setPageSize(2);
		List<Agreement> list = as.findBusinessList(query, tools);// 分页数据查询
		request.setAttribute("list", list);
		request.setAttribute("agreement", query);
		return mapping.findForward("agreement");
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
		ReceivingNoticeService rns = new ReceivingNoticeService();
		BankInterfaceForm notifyrom = (BankInterfaceForm) form;
		ReceivingNotice query = notifyrom.getReceivingnotice();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("ReceivingNotice", request);
		tools.setPageSize(2);
		List<ReceivingNotice> list = rns.findBusinessList(query, tools);
		request.setAttribute("list", list);
		request.setAttribute("receivingnotice", query);
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
		ReceivingNoticeService rns = new ReceivingNoticeService();
		ReceivingDetailService rds = new ReceivingDetailService();
		
		String no = request.getParameter("nyNo");
		if (no == null) {
			no = request.getAttribute("nyNo").toString();
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
	public ActionForward receivingdetailOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReceivingNoticeService rns = new ReceivingNoticeService();
		ReceivingDetailService rds = new ReceivingDetailService();
		
		String no = request.getParameter("nyno");
		List<ReceivingDetail> list = rds.findAll(no);
		ReceivingNotice receiving = rns.getNotify(no);

		//创建导出内容
		IExportFile export = new ExportFileExcelImpl();

		//处理需求内容
		List<ReceivingNotice> rnLists = new ArrayList<ReceivingNotice>();
		rnLists.add(receiving);
		List<List<?>> dataLists = new ArrayList<List<?>>();
		List<IImportRowMapper> mappers = new ArrayList<IImportRowMapper>();
		IImportRowMapper mapper = new ReceivingRowMapper();
		IImportRowMapper mapperDetail = new ReceivingDetailRowMapper();
		dataLists.add(rnLists);
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
		RemovePledgeService rps = new RemovePledgeService();
		// 获得页面查询数据
		BankInterfaceForm form = (BankInterfaceForm) actionform;
		RemovePledge query = form.getRemovepledge();
		// 分页工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("RemovePledge", request);
		// 设置分页条数
		tools.setPageSize(2);
		// 获得数据
		List<RemovePledge> list = rps.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("list", list);
		request.setAttribute("removepledge", query);
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
		RemovePledgeService rps = new RemovePledgeService();
		RemovePledgeDetailService rpds = new RemovePledgeDetailService();
		
		String no = request.getParameter("rdno");
		if (no == null) {
			no = request.getAttribute("rdno").toString();
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
		RemovePledgeService rps = new RemovePledgeService();
		RemovePledgeDetailService rpds = new RemovePledgeDetailService();
		
		//获得导出数据
		String no = request.getParameter("rdno");
		RemovePledge rp = rps.fingByNo(no);
		List<RemovePledgeDetail> list = rpds.findAll(no);

		//创建导出内容
		IExportFile export = new ExportFileExcelImpl();

		//处理需求内容
		List<RemovePledge> rpLists = new ArrayList<RemovePledge>();
		rpLists.add(rp);
		List<List<?>> dataLists = new ArrayList<List<?>>();
		List<IImportRowMapper> mappers = new ArrayList<IImportRowMapper>();
		IImportRowMapper mapper = new RemoveRowMapper();
		IImportRowMapper mapperDetail = new RemoveDetailRowMapper();
		dataLists.add(rpLists);
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
		MoveNoticeService mns = new MoveNoticeService();
		
		// 获得页面查询数据
		BankInterfaceForm form = (BankInterfaceForm) actionform;
		MoveNotice query = form.getMovenotice();
		// 分页工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("MoveNotice", request);
		// 设置分页条数
		tools.setPageSize(2);
		// 获得数据
		List<MoveNotice> list = mns.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("list", list);
		request.setAttribute("movenotice", query);
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
		MoveNoticeService mns = new MoveNoticeService();
		MoveDetailService mds = new MoveDetailService();
		
		String no = request.getParameter("mdno");
		if (no == null) {
			no = request.getAttribute("mdno").toString();
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
		MoveNoticeService mns = new MoveNoticeService();
		MoveDetailService mds = new MoveDetailService();
		
		String no = request.getParameter("mdno");
		List<MoveDetail> list = mds.findAll(no);
		MoveNotice mn = mns.fingByNo(no);

		//创建导出内容
		IExportFile export = new ExportFileExcelImpl();

		//处理需求内容
		List<MoveNotice> mnLists = new ArrayList<MoveNotice>();
		mnLists.add(mn);
		List<List<?>> dataLists = new ArrayList<List<?>>();
		List<IImportRowMapper> mappers = new ArrayList<IImportRowMapper>();
		IImportRowMapper mapper = new MoveRowMapper();
		IImportRowMapper mapperDetail = new MoveDetailRowMapper();
		dataLists.add(mnLists);
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
	 * 初始化质物入库
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initgager(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("gagerApp");
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

		if (!importFile.read(0)[0].equals("商品代码")) {
			request.setAttribute("message", "导入文件错误");
			return mapping.findForward("gagerApp");
		}

		@SuppressWarnings("unchecked")
		List<Commodity> list = (List<Commodity>) importFile.readAll(1, new CommodityImportRowMapper());

		if (list == null || list.size() == 0) {
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("gagerApp");
		}

		for (int i = 0; i < list.size(); i++) {
			Commodity commodity = list.get(i);
			if (StringUtil.isEmpty(commodity.getCmCmdcode())) {
				request.setAttribute("message", "第" + (i + 2) + "行商品代码不能为空");
				return mapping.findForward("gagerApp");
			}
			if (commodity.getCmStknum() == 0) {
				request.setAttribute("message", "第" + (i + 2) + "行入库数量不能为空");
				return mapping.findForward("gagerApp");
			}
			if (commodity.getCmIstkprc().toString().equals("0")) {
				request.setAttribute("message", "第" + (i + 2) + "行入库单价不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(commodity.getCmWhcode())) {
				request.setAttribute("message", "第" + (i + 2) + "行仓库代码不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(commodity.getCmVin())) {
				request.setAttribute("message", "第" + (i + 2) + "行车架号不能为空");
				return mapping.findForward("gagerApp");
			}
			if (commodity.getCmCarprice().toString().equals("0")) {
				request.setAttribute("message", "第" + (i + 2) + "行车价不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(commodity.getCmLoancode())) {
				request.setAttribute("message", "第" + (i + 2) + "行融资编号不能为空");
				return mapping.findForward("gagerApp");
			}
		}

		request.getSession().setAttribute("gagerList", list);
		request.setAttribute("list", list);
		request.setAttribute("fileName", file.getFileName());
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
		GagerService gds = new GagerService();
		CommodityService cds = new CommodityService();
		// setOptions(request);
		// 获得form
		BankInterfaceForm bForm = (BankInterfaceForm) form;
		// 设置其他必填参数
		Gager gager = bForm.getGager();

		@SuppressWarnings("unchecked")
		List<Commodity> list = (List<Commodity>) request.getSession().getAttribute("gagerList");

		if (StringUtil.isEmpty(gager.getGaLonentno()) || StringUtil.isEmpty(gager.getGaOprtname())
				|| StringUtil.isEmpty(gager.getGaOrderno()) || gager.getGaCount() <= 0) {
			request.setAttribute("message", "必填项必须填写");
			request.setAttribute("fileName", request.getParameter("fileName"));
			request.setAttribute("list", list);
			return mapping.findForward("gagerApp");
		}

		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("action", "DLCDIGSM");
		body.put("userName", "佳兵");
		body.put("hostNo", gager.getGaLonentno());
		body.put("oprtName", gager.getGaOprtname());
		body.put("orderNo", gager.getGaOrderno());
		body.put("pcgrtntNo", gager.getGaPcgrtntno());
		body.put("cmgrtcntNo", gager.getGaCmgrtcntno());
		body.put("totnum", gager.getGaCount());

		List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
		for (Commodity commodity : list) {
			Map<String, Object> comMap = new HashMap<String, Object>();
			comMap.put("cmdCode", commodity.getCmCmdcode());
			comMap.put("stkNum", commodity.getCmStknum());
			comMap.put("istkPrc", commodity.getCmIstkprc());
			comMap.put("whCode", commodity.getCmWhcode());
			comMap.put("vin", commodity.getCmVin());
			comMap.put("hgzNo", commodity.getCmHgzno());
			comMap.put("carPrice", commodity.getCmCarprice());
			comMap.put("loanCode", commodity.getCmLoancode());
			lst.add(comMap);
		}
		body.put("lst", lst);

		//准备方法
		SocketClient socket = new SocketClient(host, port);
		String xml = socket.send(head, body);
		xml = xml.substring(2);
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZhongXinBankUtil.getRetCode(ap.element("head"))) {
			return mapping.findForward("gagerApp");
		}
		Element bodyNode = ap.element("stream");
		if (!bodyNode.hasContent()) {
			return mapping.findForward("gagerApp");
		}

		String status = bodyNode.element("status").getText();

		//获取交易码并判断
		if (status.equals("AAAAAAA")) {
			request.setAttribute("message", "导入成功");
			gager.setGaId(SqlUtil.getID(Gager.class));
			boolean bl = gds.addGager(gager);
			System.out.println(bl);
			cds.addList(list, gager.getGaId());
		} else {
			request.setAttribute("message", "导入失败");
		}

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
		GagerService gds = new GagerService();
		
		BankInterfaceForm gform = (BankInterfaceForm) form;
		Gager query = gform.getGager();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Gager", request);
		tools.setPageSize(2);
		List<Gager> list = gds.findBusinessList(query, tools);
		request.setAttribute("gaLonentno", query.getGaLonentno());
		request.setAttribute("list", list);
		request.setAttribute("gager", query);
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
		GagerService gds = new GagerService();
		CommodityService cds = new CommodityService();
		
		Commodity query = new Commodity();
		query.setCmGaid(Integer.parseInt(request.getParameter("gaId")));

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Commodity", request);
		tools.setPageSize(2);
		List<Commodity> list = cds.findBusinessList(query, tools);

		request.setAttribute("list", list);
		Gager gager = gds.getGager(request.getParameter("gaId"));
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
		// 获得form
		BankInterfaceForm bForm = (BankInterfaceForm) form;
		Checkstock checkstock = bForm.getCheckstock();

		// 设置其他必填参数
		FormFile file = bForm.getImportFile();

		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);

		if (!importFile.read(0)[0].equals("仓库级别")) {
			request.setAttribute("message", "导入文件错误");
			return mapping.findForward("stocktaking");
		}

		@SuppressWarnings("unchecked")
		List<CheckstockVO> list = (List<CheckstockVO>) importFile.readAll(1, new CheckstockVOImportRowMapper());

		if (list == null || list.size() == 0) {
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("stocktaking");
		}

		for (int i = 0; i < list.size(); i++) {
			CheckstockVO check = list.get(i);
			if (StringUtil.isEmpty(check.getWhlevel())) {
				request.setAttribute("message", "第" + (i + 2) + "行仓库级别不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(check.getWhcode())) {
				request.setAttribute("message", "第" + (i + 2) + "行仓库代码不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(check.getWhaddr())) {
				request.setAttribute("message", "第" + (i + 2) + "行仓库地址不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(check.getCmcode())) {
				request.setAttribute("message", "第" + (i + 2) + "行商品代码不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(check.getNum())) {
				request.setAttribute("message", "第" + (i + 2) + "行盘库商品数量不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(check.getCmgrtcntno())) {
				request.setAttribute("message", "第" + (i + 2) + "行动产质押担保合同编号不能为空");
				return mapping.findForward("gagerApp");
			}
			if (StringUtil.isEmpty(check.getVin())) {
				request.setAttribute("message", "第" + (i + 2) + "行车架号不能为空");
				return mapping.findForward("gagerApp");
			}
		}

		request.getSession().setAttribute("checkList", list);
		request.setAttribute("list", list);
		request.setAttribute("fileName", file.getFileName());
		request.setAttribute("checkstock", checkstock);
		return mapping.findForward("stocktaking");
	}

	/**
	 * 盘库导入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stockApp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BankInterfaceForm bForm = (BankInterfaceForm) form;

		Checkstock check = bForm.getCheckstock();

		@SuppressWarnings("unchecked")
		List<CheckstockVO> list = (List<CheckstockVO>) request.getSession().getAttribute("checkList");
		//其它必填项确认
		if (StringUtil.isEmpty(check.getCsLoncpid())) {
			request.setAttribute("message", "必填项必须填写");
			request.setAttribute("fileName", request.getParameter("fileName"));
			request.setAttribute("list", list);
			return mapping.findForward("gagerApp");
		}

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

		request.setAttribute("list", list);
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
	public ActionForward checkstock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckstockService ckds = new CheckstockService();
		
		BankInterfaceForm bform = (BankInterfaceForm) form;
		Checkstock query = bform.getCheckstock();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Checkstock", request);
		tools.setPageSize(2);
		List<Checkstock> list = ckds.findBusinessList(query, tools);
		request.setAttribute("list", list);
		request.setAttribute("checkstock", query);
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
	public ActionForward checkstockDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckstockService ckds = new CheckstockService();
		int id = 0;
		int loncpid = 0;
		try {
			id = Integer.parseInt(request.getParameter("csid"));
			loncpid = Integer.parseInt(request.getParameter("loncpid"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("CheckstockVO", request);
		Checkstock checkstock = ckds.getCheckstock(loncpid);//获取共同盘库信息部分
		List<CheckstockVO> list = ckds.findAllVOList(id, tools);//获取详情信息列表
		request.setAttribute("checkstock", checkstock);
		request.setAttribute("list", list);
		return mapping.findForward("checkstockDetail");
	}

	/**
	 * 远程连接处理
	 */
	private boolean commonRequest(ActionMapping mapping, HttpServletRequest request, String listName,
			Class<?> resultClassType, String... field) throws Exception, DocumentException {
		Document doc = null;
		return commonRequest(mapping, request, doc, listName, resultClassType, field);
	}

	/**
	 * 远程连接并获取返回值
	 */
	private boolean commonRequest(ActionMapping mapping, HttpServletRequest request, Document doc, String listName,
			Class<?> resultClassType, String[] field) throws Exception {

		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, request, field);//报文体数据填充
		SocketClient socket = new SocketClient(host, port);
		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
		String xml = socket.send(head, body);
		xml = xml.substring(2);
		// 拼接返回值
		doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZhongXinBankUtil.getRetCode(ap.element("head"))) {
			return false;
		}
		Element bodyNode = ap.element("stream");
		if (!bodyNode.hasContent()) {
			return false;
		}
		String status = bodyNode.element("status").getText();//交易状态
		String statusText = bodyNode.element("statusText").getText();//交易状态信息
		if (!status.trim().equals("AAAAAAA")) {
			System.out.println("交易状态异常");
			return false;
		}
		Element list = bodyNode.element(listName + "lst");
		List infos = list.elements("row");
		List<Object> resultList = new ArrayList<Object>();
		if (infos != null)
			for (Iterator it = infos.iterator(); it.hasNext();) {
				Element info = (Element) it.next();
				Object bean = ZhongXinBankUtil.getBean(resultClassType, info);
				resultList.add(bean);
			}
		request.setAttribute("resultList", resultList);

		return true;
	}

	/**
	 * 远程查询保存到本地
	 */
	public static boolean NoticeSynchronous(int noticeType, Map<String, Object> body, Map<String, Object> head,
			String listName, Class<?> beanClassType, Class<?> resultClassType) throws Exception {
		NoticeService ns = new NoticeService();
		SocketClient socket = new SocketClient(host, port);
		Document doc = null;
		String xml = socket.send(head, body);
		xml = xml.substring(2);
		// 拼接返回值
		doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZhongXinBankUtil.getRetCode(ap.element("head"))) {
			return false;
		}
		Element bodyNode = ap.element("stream");
		if (!bodyNode.hasContent()) {
			return false;
		}
		List liststs = ap.elements("stream");
		String status = bodyNode.element("status").getText();//交易状态
		String statusText = bodyNode.element("statusText").getText();//交易状态信息
		if (!status.trim().equals("AAAAAAA")) {
			System.out.println("交易状态异常");
			return false;
		}
		Object bean = null;
		for (Iterator it = liststs.iterator(); it.hasNext();) {
			Element listst = (Element) it.next();
			bean = ZhongXinBankUtil.getBean(beanClassType, listst);
		}
		Element list = bodyNode.element(listName + "lst");
		List infos = list.elements("row");
		List resultList = new ArrayList();
		if (infos != null)
			for (Iterator it = infos.iterator(); it.hasNext();) {
				Element info = (Element) it.next();
				Object beans = ZhongXinBankUtil.getBean(resultClassType, info);
				resultList.add(beans);
			}
		ns.saveNotice(noticeType, resultList, bean);
		return true;
	}
}
