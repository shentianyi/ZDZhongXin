package com.zd.csms.zxbank.web.action;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zd.core.ActionSupport;
import com.zd.core.SystemProperty;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.service.*;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.csms.zxbank.util.ZhongXinBankUtil;
import com.zd.csms.zxbank.web.bean.AgreementFar;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.csms.zxbank.web.bean.FinancingFar;
import com.zd.csms.zxbank.web.bean.WarehouseFar;
import com.zd.csms.zxbank.web.excel.FinancingRowMapper;
import com.zd.csms.zxbank.web.excel.MoveDetailRowMapper;
import com.zd.csms.zxbank.web.excel.MoveRowMapper;
import com.zd.csms.zxbank.web.excel.ReceivingDetailRowMapper;
import com.zd.csms.zxbank.web.excel.ReceivingRowMapper;
import com.zd.csms.zxbank.web.excel.RemoveDetailRowMapper;
import com.zd.csms.zxbank.web.excel.RemoveRowMapper;
import com.zd.csms.zxbank.web.form.*;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportRowMapper;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ZXBankInterfaceAction extends ActionSupport {
	private static final String userName;
	private static final String url;
	private static Log log = LogFactory.getLog(ZXBankInterfaceAction.class);
	static {
		url = SystemProperty.getPropertyValue("bankdock.properties", "zx.url");
		userName=SystemProperty.getPropertyValue("bankdock.properties","zx.userName");
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
		NoticeService ns = new NoticeService();
		BankInterfaceForm not = (BankInterfaceForm) form;
		Notice dquery = not.getNotice();
//		String state=request.getParameter("state");
		if(dquery!=null&&dquery.getNtcno()!=null){
			if(dquery.getNtcno().equals("0"))
				dquery.setNtcno("");
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Notice", request);
//		tools.setPageSize(3);
		tools.saveQueryVO(dquery);
		List<Notice> types = ns.findnoticetype();
		List<Notice> list = ns.findNotice(dquery, tools);
		//通知书编号输入框下拉表List
		request.setAttribute("draftOptions", ns.draftsOptions());
		request.setAttribute("types", types);
		request.setAttribute("list", list);
		request.setAttribute("notice", dquery);
		return mapping.findForward("noticelist");
	}
	/**
	 *  通知推送    重新读取
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward noticeReread(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NoticeService ns = new NoticeService();
		
		String ntctp=request.getParameter("ntType");
		int ntType = Integer.parseInt(ntctp);
		String ntcno = request.getParameter("ntcno");
		if (ntctp!=null&&!ntctp.equals("")) {
			if(ns.FarQuery(ntcno, ntType,request)){
				Notice notice=new Notice();
				notice.setNtcno(ntcno);
				notice=ns.getNotice(notice);
				notice.setNtfailflag(2);
				log.info("通知推送状态更新:["+ns.update(notice)+"]");
			}else{
				log.info("通知回查结果失败");
			}
		} else {
			log.info("通知类型出错");
		}
		return findnotice(mapping, form, request, response);
	}

	/**
	 * 通知推送 	通知详情跳转
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
	/**
	 * 通知推送 -质物与融资关系的变更详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward noticePushDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PushNoticeDetailService pnds=new PushNoticeDetailService();
		String ntcno=request.getParameter("ntcno");
		PushNoticeDetail pnd=new PushNoticeDetail();
		if(ntcno!=null){
			pnd.setNtcno(ntcno);
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("PushNoticeDetail", request);
		List<PushNoticeDetail> list=pnds.findNotice(pnd,tools);
		/*request.setAttribute("draftOptions", pnds.draftsOptions());*/
		request.setAttribute("list", list);
		request.setAttribute("ntcno",ntcno);
		return mapping.findForward("noticePushDetail");
	}
	
	/**
	 * 操作--重新变更，质物与融资的关系
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward retPushDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PushNoticeDetailService pnds=new PushNoticeDetailService();
		NoticeService ns=new NoticeService();
		Notice notice=new Notice();
		List<PushNoticeDetail> pndlist=pnds.findPushNoticeDetail();
		
		if(pndlist.size()>0){
			log.info("通知编号："+pndlist.get(0).getNtcno());
			notice.setNtcno(pndlist.get(0).getNtcno());
			for (PushNoticeDetail pushNoticeDetail : pndlist) {
				if(!ns.saveDetail(pushNoticeDetail,5)){
					request.setAttribute("message","车辆："+pushNoticeDetail.getPndvin()+"关系变更失败");
					return findnotice(mapping, form, request, response);
				}
			}
		}
		//更新通知推送信息
		Notice nce=ns.getNotice(notice);
		if(nce!=null){
			nce.setNtfailflag(2);
			log.info("通知更新结果："+ns.update(notice));
		}
		request.setAttribute("message","所有车辆关系变更成功");
		return findnotice(mapping, form, request, response);
	}
	
	

	/**
	 * 用户信息查询数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ActionForward customer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CustomerService cs = new CustomerService();
		DistribsetService ds=new DistribsetService();
		//获取form表单数据
		BankInterfaceForm cust = (BankInterfaceForm) form;
		Customer query = cust.getCustomer();
//		if(query!=null&&query.get()!=null){
//			if(query.getCustNo().equals("0"))
//				query.setCustNo("");
//		}
		//查询方式
		String queryType ="";
		if (request.getParameter("queryType") != null) {
			try {
				queryType = request.getParameter("queryType");
			} catch (Exception e) {
			}
		}
			//远程查询返回数据
		if (queryType.equals("2")) {
			if(query.getCustOrganizationcode()==null||query.getCustOrganizationcode().trim().isEmpty()){
				request.setAttribute("message","组织机构代码不能为空");
			}else{
				System.out.println("--远程查询--");
				request.setAttribute("action", "DLCDCMLQ");
				//userName 需要等到整合时将Session中用户保存
				request.setAttribute("userName",userName);
				request.setAttribute("orgCode", query.getCustOrganizationcode().trim());
				boolean flg;
				try {
					flg = commonRequest(mapping, request, "", CustomerFar.class, new String[] { "action", "userName",
							"orgCode" });
					log.info("银企直连查询结果:["+flg+"]");
					if (flg) {
						//保存或更新数据
						List resultList = (List) request.getAttribute("resultList");
						cs.autoUpdateCust(resultList, query);
					}else{
						if(request.getAttribute("message")==null||request.getAttribute("message").equals("")){
							request.setAttribute("message", "银企直连交易异常,转至本地查询");
						}
					}
				} catch (Exception e) {
					log.error("银企直连查询错误信息:",e);
					request.setAttribute("message", "银企直连异常,转至本地查询");
					e.printStackTrace();
					
				}
			}
		}
	
//		System.out.println("--开始本地查询--");
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
		request.setAttribute("draftOptions",ds.draftsOptions());
		request.setAttribute("queryType",queryType);
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
			HttpServletResponse response) {
		CustomerService cs = new CustomerService();
		WareHouseService whs = new WareHouseService();
		BankInterfaceForm WarHouseform = (BankInterfaceForm) form;
		Warehouse query = WarHouseform.getWarehouse();// 查询条件获取
		//查询方式
//		String queryType ="0";
		String queryType ="";
		if (request.getParameter("queryType") != null) {
			queryType =request.getParameter("queryType");
		}
		System.out.println("客户查询方式：queryType:" + queryType);
		//远程查询返回数据
		if (queryType.equals("2")) {
			if(query.getCustNo()==null||query.getCustNo().trim().isEmpty()){
				request.setAttribute("message","客户号不能为空");
			}else{
				System.out.println("--远程查询--");//
				request.setAttribute("action", "DLCDWMLQ");//请求代码
				request.setAttribute("userName",userName);//登录名
				request.setAttribute("hostNo", query.getCustNo());//- ECIF客户号
				try {
					boolean flg = commonRequest(mapping, request, "", WarehouseFar.class, new String[] { "action",
							"userName", "hostNo" });
					if (flg) {
						//保存或更新数据
						List resultList = (List) request.getAttribute("resultList");
						if(whs.autoUpdateWare(resultList,query.getCustNo())){
						}else{
							request.setAttribute("message", "数据更新失败");
						}
					}else{
						if(request.getAttribute("message")==null||request.getAttribute("message").equals("")){
							request.setAttribute("message", "银企直连交易异常,转至本地查询");
						}
					}
				} catch (Exception e) {
					log.error("银企直连查询错误：",e);
					request.setAttribute("message", "银企直连异常,转至本地查询");
					e.printStackTrace();
				}
			}
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Warehouse", request);// 获取分页模板
		List<Warehouse> list = whs.findBusinessList(query, tools);// 获取分页后的数据list
		request.setAttribute("list", list);
		request.setAttribute("warehouse", query);
		request.setAttribute("draftOptions",cs.draftsOptions());
		request.setAttribute("queryType",queryType);
		//--开始本地查询--
		return mapping.findForward("warehouse");
	}

	/**
	 * 仓库修改展示页
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 */
	public ActionForward warehouseUpdateShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		WareHouseService whs = new WareHouseService();
		String custNo = request.getParameter("custNo");
		String whCode = request.getParameter("whCode");
		Warehouse warehouse = whs.getWarehouse(custNo, whCode);
		request.setAttribute("warehouse", warehouse);
		return mapping.findForward("warehouseUpdate");
	}

	//仓库更新
	public ActionForward warehouseUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BankInterfaceForm WarHouseform = (BankInterfaceForm) form;
		Warehouse query = WarHouseform.getWarehouse();
		WareHouseService whs = new WareHouseService();

		UserVO user = UserSessionUtil.getUserSession(request).getUser();

		query.setUpdateDate(new Date());
		if (whs.update(query)) {
			log.info(user.getUserName() + "更新" + query.getWhName() + "的仓库信息成功(" + "距离:"
					+ query.getWhdistance() + "联系人:" + query.getWhContacts() + ")");
			return warehouse(mapping, WarHouseform, request, response);
		}
		log.warn(user.getUserName() + "更新" + query.getWhName() + "的仓库信息失败");
		request.setAttribute("message", "更新失败");
		return mapping.findForward("warehouseUpdate");
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
			HttpServletResponse response) {
		FinancingService fs = new FinancingService();
		AgreementService as=new AgreementService();
		BankInterfaceForm form = (BankInterfaceForm) actionform;
		FinancingQueryVO query = form.getFinancingVO();

		//查询方式
		String queryType = "0";
		if (request.getParameter("queryType") != null) {
			queryType = request.getParameter("queryType");
//			System.out.println(choose);
		}

		if (queryType.equals("2")) {
			
			if(query.getFgLonentNo()==null||query.getFgLonentNo().trim().length()==0){
				request.setAttribute("message", "借款企业ID不能为空");
			}else if(query.getFgStDateStart()==null||query.getFgStDateStart().trim().length()==0){
				request.setAttribute("message", "开始时间不能为空");
			}else if(query.getFgStDateEnd()==null||query.getFgStDateEnd().trim().length()==0){
				request.setAttribute("message", "结束时间不能为空");
			}else{
				request.setAttribute("action", "DLCDLMLQ");
				request.setAttribute("userName",userName);
				request.setAttribute("loncpId", query.getFgLonentNo());
				request.setAttribute("loanstDate",query.getFgStDateStart());
				request.setAttribute("loanendDate",query.getFgStDateEnd());
				//远程查询
				boolean flg;
				try {
					flg = commonRequest(mapping, request, "", FinancingFar.class, new String[] { "action", "userName",
							"loncpId", "loanstDate","loanendDate"});
					if (flg) {
						@SuppressWarnings("unchecked")
						List<FinancingFar> resultList = (List<FinancingFar>) request.getAttribute("resultList");
						//处理远程查询并保存到本地服务器
						if(!fs.addOrUpdate(resultList)){
							request.setAttribute("message", "信息更新异常出错");
						}
					}else{
						if(request.getAttribute("message")==null||request.getAttribute("message").equals("")){
							request.setAttribute("message", "银企直连交易异常,转至本地查询");
						}
					}
				} catch (Exception e) {
					log.error("银企直连查询错误：",e);
					request.setAttribute("message", "银企直连查询错误,转至本地查询");
					e.printStackTrace();
				}
			}
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Financing", request);
		// 设置每页显示几条数据
//		tools.setPageSize(2);
		// 从数据库查询
		List<Financing> list = fs.findByQuery(query, tools);
		request.setAttribute("draftOptions",as.draftsOptions());
		request.setAttribute("list", list);
		request.setAttribute("financingVO", query);
		request.setAttribute("queryType",queryType);
		return mapping.findForward("financing");
	}

	/**
	 * 融资修改界面
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward financingUpdateShow(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) {
		String fgLoanCode = request.getParameter("fgLoanCode");
		FinancingService fs = new FinancingService();
		Financing financing = fs.getFinancing(fgLoanCode);
		request.setAttribute("financing", financing);
		return mapping.findForward("financingupdate");
	}

	/**
	 * 融资更新
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward financingUpdate(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) {
		BankInterfaceForm form = (BankInterfaceForm) actionform;
		Financing query = form.getFinancing();
		FinancingService fs = new FinancingService();

		UserVO user = UserSessionUtil.getUserSession(request).getUser();

		query.setFgUpdateDate(new Date());
		if (fs.update(query)) {
			log.info(user.getUserName() + "更新" + query.getFgLoanCode() + "的融资信息成功(" + "协议编号:"
					+ query.getFgagtid() + ")");
			return financing(mapping, actionform, request, response);
		}
		log.warn(user.getUserName() + "更新" + query.getFgLoanCode() + "的融资信息失败");
		request.setAttribute("message", "更新失败");
		return mapping.findForward("financingupdate");
	}
	
	/**
	 * 融资信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward financingOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FinancingService finSce=new FinancingService();
		List<Financing> list = finSce.getFinancingList();
		//创建导出内容
		IExportFile export = new ExportFileExcelImpl();
		//处理需求内容
		List<List<?>> dataLists = new ArrayList<List<?>>();
		List<IImportRowMapper> mappers = new ArrayList<IImportRowMapper>();
		IImportRowMapper mapperDetail = new FinancingRowMapper();
		dataLists.add(list);
		mappers.add(mapperDetail);

		try {
			//下载
			export.export(dataLists, mappers, export.createDefaultFileName("企业融资信息"), "企业融资信息", response);
		} catch (Exception e) {
			log.error("错误：",e);
			e.printStackTrace();
		}
		return financing(mapping,form,request,response);
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
			HttpServletResponse response) {
		AgreementService as = new AgreementService();
		CustomerService cs = new CustomerService();
		//获取form表单数据
		BankInterfaceForm agreementfrom = (BankInterfaceForm) form;
		Agreement query = agreementfrom.getAgreement();// 获取查询条件
		//查询方式
		String queryType ="";
		if (request.getParameter("queryType") != null) {
			try {
				queryType =request.getParameter("queryType");
			} catch (Exception e) {
				log.error("错误：",e);
			}
		}
//		System.out.println("客户查询方式：queryType:" + queryType);
		//远程查询返回数据
		if (queryType.equals("2")) {
			if(query.getHostno()==null||query.getHostno().trim().isEmpty()){
				request.setAttribute("message","客户号不能为空");
			}else{
				System.out.println("--远程查询--");
				request.setAttribute("action", "DLCDAGTQ");
				//userName 需要等到整合时将Session中用户保存
				request.setAttribute("userName",userName);
				request.setAttribute("hostNo", query.getHostno());
				try {
					boolean flg = commonRequest(mapping, request, "", AgreementFar.class, new String[] { "action",
							"userName", "hostNo" });
					if (flg) {
						//保存或更新数据
						List resultList = (List) request.getAttribute("resultList");
						if(!as.autoUpdateCust(resultList)){
							request.setAttribute("message", "信息更新异常出错");
						}
					}else{
						if(request.getAttribute("message")==null||request.getAttribute("message").equals("")){
							request.setAttribute("message", "银企直连交易异常,转至本地查询");
						}
					}
				} catch (Exception e) {
					log.error("错误：",e);
					request.setAttribute("message", "银企直连错误,转至本地查询");
					e.printStackTrace();
				}
			}
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Agreement", request);// 分页模板获取
//		tools.setPageSize(5);
		List<Agreement> list = as.findBusinessList(query, tools);// 分页数据查询
		request.setAttribute("draftOptions",cs.draftsOptions());
		request.setAttribute("list", list);
		request.setAttribute("agreement", query);
		request.setAttribute("queryType",queryType);
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
//		tools.setPageSize(2);
		List<ReceivingNotice> list = rns.findBusinessList(query, tools);
		request.setAttribute("draftOptions", rns.draftsOptions());
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
//		tools.setPageSize(2);
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

		log.info("导出数据");
		log.info(list.toString());
		log.info(receiving.toString());
		
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
			log.error("错误：",e);
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
//		tools.setPageSize(2);
		// 获得数据
		List<RemovePledge> list = rps.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("draftOptions", rps.draftsOptions());
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
//		tools.setPageSize(2);
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
			log.error("错误：",e);
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
//		tools.setPageSize(2);
		// 获得数据
		List<MoveNotice> list = mns.findByQuery(query, tools);
		// 页面设置参数
		request.setAttribute("draftOptions", mns.draftsOptions());
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
//		tools.setPageSize(2);
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
			log.error("错误：",e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 质物入库
	 */
	public List<Map<String,Object>> gagerApp(Gager gager, List<Commodity> list, HttpServletRequest request) {
		List<Map<String,Object>> result = null;
		GagerService gds = new GagerService();
		CommodityService cds = new CommodityService();

//		Map<String, Object> body = new HashMap<String, Object>();
//		Map<String, Object> body = new TreeMap<String, Object>();
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("action", "DLCDIGSM");
		body.put("userName",userName);
		body.put("hostNo", gager.getGaLonentno());
		body.put("lonNm", gager.getGaLonentname());//可空
		body.put("oprtName", gager.getGaOprtname());
		body.put("orderNo", gager.getGaOrderno());
		body.put("pcgrtntNo", gager.getGaPcgrtntno());//可空
		body.put("cmgrtcntNo", gager.getGaCmgrtcntno());//可空
		body.put("remark", gager.getGaRemark());//可空
		body.put("totnum", list.size());
		body.put("whCode", gager.getGawhCode());
//		body.put("Confirmno", gager.getGaConfirmno());
		body.put("field3", gager.getGaConfirmno());//确认书编号
		List<Map<String, Object>> lst = new ArrayList<Map<String, Object>>();
		for (Commodity commodity : list) {
			Map<String, Object> comMap = new HashMap<String, Object>();
			comMap.put("cmdCode", commodity.getCmCmdcode());//可空
			comMap.put("stkNum", commodity.getCmStknum());
			comMap.put("istkPrc", commodity.getCmIstkprc());
			//comMap.put("whCode", commodity.getCmWhcode());
			comMap.put("vin", commodity.getCmVin());
			comMap.put("hgzNo", commodity.getCmHgzno());//可空
			comMap.put("carPrice", commodity.getCmCarprice());
			comMap.put("loanCode", commodity.getCmLoancode());
			lst.add(comMap);
		}
		body.put("lst", lst);
		String xmlReq = ZhongXinBankUtil.parseXml(body);
		log.info("质物入库申请请求报文:["+xmlReq+"]");
		String xml = ZhongXinBankUtil.sendPost(url, xmlReq);
		if(xml.equals("")){
			request.setAttribute("message",xml);
		}else{
			log.info("质物入库申请响应报文:["+xml+"]");
			Document doc;
			try {
				doc = DocumentHelper.parseText(xml);
				Element root = doc.getRootElement();
				String status = root.element("status").getText();
				String statusText=root.element("statusText").getText();
				log.info("状态码:["+status+"]--状态信息:["+statusText+"]");
				Map<String,Object> vinMap = null;
				result = new ArrayList<Map<String,Object>>();
				//获取交易码并判断
				if (status.equals("AAAAAAA")) {
					Element vinList = root.element("list");
					
					gager.setGaState(1);
					gager.setGaCount(list.size());
					log.info("入库信息保存2"+gds.addGager(gager));
					log.info("商品保存2："+cds.addList(list, gager.getGaId()));
					
					if(vinList==null)
						return result;
				}else{
	//				if(status.equals("IN00006")) {
					request.setAttribute("message",statusText);
					Element vinList = root.element("list");
					if(vinList==null){
						log.info("获取报文lst集合为空");
						return result;
					}
					List infos = vinList.elements("row");
					if (infos != null){
						List<Commodity> abCars = new ArrayList<Commodity>();
						
	//					vinMap.put("status",true);
						for (Iterator it = infos.iterator(); it.hasNext();) {
							vinMap =  new HashMap<String,Object>();
							Element info = (Element) it.next();
							String abVin =  info.element("vin").getText();
							vinMap.put("vin",abVin);
							vinMap.put("remark", info.element("remark").getText());
							//处理List
							for (int i = 0; i < list.size(); i++) {
								if(list.get(i).getCmVin().equals(abVin)){
									abCars.add(list.get(i));
									list.remove(i);
									break;
								}
							}
							result.add(vinMap);
						}
						
						gager.setGaState(2);	
						gager.setGaCount(abCars.size());
						log.info("入库信息保存1"+gds.addGager(gager));
						log.info("商品保存1："+cds.addList(abCars, gager.getGaId()));
					}else{
						gager.setGaState(1);
						gager.setGaCount(list.size());
						log.info("入库信息保存2"+gds.addGager(gager));
						log.info("商品保存2："+cds.addList(list, gager.getGaId()));
					}
				}
				/*else{
					vinMap=new HashMap<String, Object>();
					vinMap.put("status",false);
					result.add(vinMap);
					return result;
				}*/
			} catch (DocumentException e) {
				log.error("错误：",e);
				e.printStackTrace();
			}
		}
		return result;
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
		AgreementService as=new AgreementService();
		BankInterfaceForm gform = (BankInterfaceForm) form;
		Gager query = gform.getGager();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Gager", request);
//		tools.setPageSize(2);
		List<Gager> list = gds.findBusinessList(query, tools);
		request.setAttribute("draftOptions", as.draftsOptions());
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
//		tools.setPageSize(2);
		List<Commodity> list = cds.findBusinessList(query, tools);

		request.setAttribute("list", list);
		Gager gager = gds.getGager(request.getParameter("gaId"));
		request.setAttribute("Gager", gager);
		return mapping.findForward("commodity");
	}

	/**
	 * 盘库导入	
	 * 		
	 */
	public boolean stockApp(Checkstock check, List<Checkwarehouse> wlist, List<Check> clist, HttpServletRequest request) {
		log.info("盘点信息提交");
		CheckstockService cs = new CheckstockService();
		try {
			Map<String, Object> body = new HashMap<String, Object>();
			body.put("action", "DLCDCSSM");
			body.put("userName",userName);
			body.put("hostNo", check.getCsLoncpid());
			body.put("hostName", check.getCsLoncpname());
			body.put("spvAgtId", check.getCsProtocolno());
			body.put("spvAgtNo", check.getCsProtocolcode());//可空
			body.put("orderNo", check.getCsTradeid());
			body.put("pchkstkdt", check.getCsPlandate());//可空
			body.put("rchkstkdt", check.getCsFactdate());
			body.put("oprNo", check.getCsUserno());
			body.put("oprNm", check.getCsUsername());
			body.put("remark", check.getCsRemark());//可空
			body.put("errorExplain", check.getCsErrorreport());//可空

			List<Map<String, Object>> wlst = new ArrayList<Map<String, Object>>();
			for (Checkwarehouse ware : wlist) {
				Map<String, Object> wMap = new HashMap<String, Object>();
				wMap.put("whCode", ware.getChWhcode());
				wMap.put("whName", ware.getChWhname());
				wMap.put("whLevel", ware.getChWhlevel());
				wMap.put("whAddr", ware.getChWhlevel());
				wMap.put("carCount", ware.getChNum());
				wlst.add(wMap);
			}

			List<Map<String, Object>> clst = new ArrayList<Map<String, Object>>();
			for (Check ck : clist) {
				Map<String, Object> cMap = new HashMap<String, Object>();
				cMap.put("spvWhCode", ck.getCkSpvwhcode());
				cMap.put("vin", ck.getCkVin());
				clst.add(cMap);
			}
			body.put("warehouselist", wlst);
			body.put("spvwhcmdlst", clst);

			String xmlReq = ZhongXinBankUtil.parseXml(body);
			log.info("盘点信息提交报文：["+xmlReq+"]");
			String xml = ZhongXinBankUtil.sendPost(url, xmlReq);
			log.info("盘点提交请求响应报文：["+xml+"]");
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element bodyNode = doc.getRootElement();
			String status = bodyNode.element("status").getText();
			String statusText = bodyNode.element("statusText").getText();
			log.info("状态码：["+status+"]--["+statusText+"]");
			if (status.equals("AAAAAAA")) {
				return cs.saveCheckstock(check, wlist, clist);
			}
		} catch (Exception e) {
			log.error("盘点提交错误：",e);
			return false;
		}
		return false;
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
		AgreementService as=new AgreementService();
		BankInterfaceForm bform = (BankInterfaceForm) form;
		Checkstock query = bform.getCheckstock();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("Checkstock", request);
//		tools.setPageSize(2);
		List<Checkstock> list = ckds.findBusinessList(query, tools);
		request.setAttribute("draftOptions", as.draftsOptions());
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
		try {
			id = Integer.parseInt(request.getParameter("csid"));
		} catch (Exception e) {
			log.error("错误：",e);
			e.printStackTrace();
		}

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("CheckstockVO", request);
		Checkstock checkstock = ckds.getCheckstock(id);//获取共同盘库信息部分
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
		String sendxml = ZhongXinBankUtil.parseXml(body);
		log.info("发送至服务端报文:["+ sendxml+"]");
		String xml = ZhongXinBankUtil.sendPost(url, sendxml);
		if(xml==null||xml.equals("")){
			request.setAttribute("message","请求超时,银行服务器未响应");
			return false;
		}else{
			log.info("接收到服务端发来报文:["+ xml+"]");
			doc = DocumentHelper.parseText(xml);
			doc.setXMLEncoding("GBK");
			Element root = doc.getRootElement();
			String status = root.element("status").getText();//交易状态
			String statusText = root.element("statusText")==null?"":root.element("statusText").getText();//交易状态信息
			log.info("交易状态码：["+status+"]-交易信息：["+statusText+"]");
			if (status.trim().equals("AAAAAAA")) {
	//			Element list = bodyNode.element(listName + "lst");
				Element list = root.element("list");
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
			}else{
				log.error("[交易状态异常]");
				request.setAttribute("message", statusText);
				return false;
			}
		}
	}

	/**
	 * 远程查询保存到本地
	 */
	public static Map<String, Object> NoticeSynchronous(int noticeType, Map<String, Object> body, String listName,
			Class<?> beanClassType, Class<?> resultClassType) throws Exception {
		NoticeService ns = new NoticeService();
		Map<String, Object> statusMap=new HashMap<String, Object>();
		String sendxml = ZhongXinBankUtil.parseXml(body);
		log.info("发送至服务端报文:["+ sendxml+"]");
		String xml = ZhongXinBankUtil.sendPost(url, sendxml);
		statusMap.put("boolean", false);
		statusMap.put("message","读取失败");
		if(xml==null||xml.equals("")){
			statusMap.put("message","请求超时,银行服务器未响应");
		}else{
				log.info("接收到服务端发来报文:["+ xml+"]");
				Document doc = null;
				// 拼接返回值
				doc = DocumentHelper.parseText(xml);
				doc.setXMLEncoding("GBK");
				Element root = doc.getRootElement();
				List liststs = root.elements();
				String status = root.element("status").getText();//交易状态
				String statusText = root.element("statusText").getText();//交易状态信息
				log.info("交易状态码：["+status+"]-交易信息：["+statusText+"]");
				statusMap.put("status", status);
				if (status.trim().equals("AAAAAAA")) {
					Object bean = ZhongXinBankUtil.getBean(beanClassType, root);
		//			Element list = root.element(listName + "lst");
					Element list = root.element("list");
					List infos = list.elements("row");
					List resultList = new ArrayList();
					if (infos != null)
						for (Iterator it = infos.iterator(); it.hasNext();) {
							Element info = (Element) it.next();
							Object beans = ZhongXinBankUtil.getBean(resultClassType, info);
							resultList.add(beans);
						}
					//		UserVO user = UserSessionUtil.getUserSession(request).getUser();
					//		ns.setUser(user);
					return ns.saveNotice(noticeType, resultList, bean);
				}else{
					log.error("[交易状态异常]");
					statusMap.put("message", statusText);
				}
			}
		return statusMap;
		
	}

}
