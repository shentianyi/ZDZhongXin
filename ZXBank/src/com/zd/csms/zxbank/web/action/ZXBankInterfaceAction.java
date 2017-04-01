package com.zd.csms.zxbank.web.action;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PRTokeniser;
import com.sun.script.javascript.JSAdapter;
import com.zd.core.ActionSupport;
import com.zd.core.JSONAction;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.dao.*;
import com.zd.csms.zxbank.dao.oracle.*;
import com.zd.csms.zxbank.model.*;
import com.zd.csms.zxbank.service.*;
import com.zd.csms.zxbank.web.form.*;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;
import com.zd.tools.thumbPage.impl.ThumbPageToolsForOracle;

public class ZXBankInterfaceAction extends ActionSupport {
	/*
	 * private static final String host; private static final int port; private
	 * static final String currentCustNo; private static final String custType;
	 */
	private static final int pageSize = 10;
	private static final int pageMaxNum = 50;

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

	/*
	 * static { host = SystemProperty.getPropertyValue("bankdock.properties",
	 * "zs.host"); port =
	 * Integer.parseInt(SystemProperty.getPropertyValue("bankdock.properties",
	 * "zs.port")); currentCustNo =
	 * SystemProperty.getPropertyValue("bankdock.properties",
	 * "zs.currentCustNo"); custType =
	 * SystemProperty.getPropertyValue("bankdock.properties", "zs.custType"); }
	 */

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
	//发货通知
	private ReceivingNoticeService rns = new ReceivingNoticeService();
	//发货通知详情
	private ReceivingDetailService rds = new ReceivingDetailService();

	public ActionForward distribset(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("cuslist");
	}

	// 经销商 参数ajax查询经销商参数的

	public ActionForward disajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
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
	public ActionForward customer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

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
	public ActionForward warehouse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

	public ActionForward findnotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward financing(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward agreement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	public ActionForward removepledge(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward removepledgedetail(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * 移库通知查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward movenotice(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	public ActionForward movedetail(ActionMapping mapping, ActionForm actionform, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward receivingnotice(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		ReceivingNoticeForm notifyrom=(ReceivingNoticeForm)form;
		ReceivingNotice query=notifyrom.getReceivingnotice();
		IThumbPageTools tools=ToolsFactory.getThumbPageTools("ReceivingNotice", request);
		tools.setPageSize(2);
		List<ReceivingNotice> list=rns.findBusinessList(query, tools);
		request.setAttribute("list", list);
		request.setAttribute("nyNo",query.getNyNo());
		request.setAttribute("nyLonentname", query.getNyLonentname());
		return mapping.findForward("receivingnotice");
	}
	
	/**
	 * 收货通知书详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward receivingdetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		String no = request.getParameter("nyNo");
		ReceivingDetail query= new ReceivingDetail();
		query.setNdNo(no);
		IThumbPageTools tools=ToolsFactory.getThumbPageTools("ReceivingDetail", request);
		tools.setPageSize(2);
		List<ReceivingDetail> list=rds.findBusinessList(query, tools);
		
		ReceivingNotice receiving=rns.getNotify(no);
		request.setAttribute("list", list);
		request.setAttribute("receiving", receiving);
		
		return mapping.findForward("receivingdetail");
	}

	/**
	 * 客户信息查询（gyl001） 根据登陆客户的角色查询与之对应的客户信息. 1：监管公司-只能查询与之签订监管协议的授信客户信息。
	 * 2：核心厂商-只能查询到与之签到协议的授信客户信息。 3：授信客户无此接口。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	 * public ActionForward customer(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) {
	 * System.out.println("嗨这是中信银行的客户查询 ... ..."); try { IThumbPageTools tools =
	 * ToolsFactory.getThumbPageTools("Customer", request);
	 * ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
	 * oracleTools.setPageSize(10); request.setAttribute("pageSize",
	 * oracleTools.getThumbPageVO().getPageSize()+"");
	 * request.setAttribute("pageIndex",
	 * oracleTools.getThumbPageVO().getCurrentPageNum()+"");
	 * 
	 * request.setAttribute("currentCustNo", currentCustNo);
	 * request.setAttribute("custType", custType);
	 * 
	 * boolean flag = commonRequest(mapping, request, "cust", Customer.class,
	 * new String[]{"currentCustNo", "custType",
	 * "custOrganizationcode","pageSize","pageIndex"});
	 * 
	 * if(flag){ Integer totalCount =
	 * (Integer)request.getAttribute("totalCount");
	 * oracleTools.setTotalItemsNum(totalCount);
	 * oracleTools.setTotalPagesNum(totalCount
	 * %10==0?(totalCount/10):(totalCount/10)+1); }
	 * 
	 * request.setAttribute("custNo", request.getParameter("custNo"));
	 * request.setAttribute("pledgeName", request.getParameter("pledgeName"));
	 * }catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * return mapping.findForward("gyl001"); }
	 * 
	 * private boolean commonRequest(ActionMapping mapping, HttpServletRequest
	 * request, String listName, Class<?> resultClassType, String... field)
	 * throws Exception, DocumentException { Document doc = null; return
	 * commonRequest(mapping, request, doc, listName, resultClassType, field); }
	 * private boolean commonRequest(ActionMapping mapping, HttpServletRequest
	 * request, Document doc, String listName, Class<?> resultClassType,
	 * String... field) throws Exception, DocumentException { String methodName
	 * = request.getParameter("method"); Map<String, Object> body = new
	 * HashMap<String, Object>(); ZheShangBankUtil.autoFill(body, request,
	 * field);
	 * 
	 * SocketClient socket = new SocketClient(host, port); Map<String, Object>
	 * head = ZheShangBankUtil.getBaseHeadList(methodName, ""); String xml =
	 * socket.send(head, body);
	 * 
	 * xml = xml.substring(2); // 拼接返回值 doc = DocumentHelper.parseText(xml);
	 * Element ap = doc.getRootElement(); if
	 * (!ZheShangBankUtil.getRetCode(ap.element("head"))) { return false; }
	 * 
	 * Element bodyNode = ap.element("body"); if(!bodyNode.hasContent()){ return
	 * false; } int totalCount =
	 * Integer.parseInt(bodyNode.element("totalCount").getText());
	 * request.setAttribute("totalCount", totalCount);
	 * request.setAttribute("currentPageSize",
	 * Integer.parseInt(bodyNode.element("currentPageSize").getText()));
	 * if(totalCount<=0) return false;
	 * 
	 * Element list = bodyNode.element(listName + "List"); List infos =
	 * list.elements(listName + "Info"); List resultList = new ArrayList(); if
	 * (infos != null) for (Iterator it = infos.iterator(); it.hasNext();) {
	 * Element info = (Element) it.next(); Object bean =
	 * ZheShangBankUtil.getBean(resultClassType, info); resultList.add(bean); }
	 * request.setAttribute("list", resultList); request.setAttribute("doc",
	 * doc); return true; }
	 */
}
