package com.zd.csms.bank.web.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zd.csms.bank.bean.Gyl001;
import com.zd.csms.bank.bean.Gyl002;
import com.zd.csms.bank.bean.Gyl004;
import com.zd.csms.bank.bean.Gyl005;
import com.zd.csms.bank.bean.Gyl006;
import com.zd.csms.bank.bean.Gyl007;
import com.zd.csms.bank.bean.Gyl009VO;
import com.zd.csms.bank.bean.Gyl011;
import com.zd.csms.bank.bean.Gyl013;
import com.zd.csms.bank.bean.Gyl016VO;
import com.zd.csms.bank.bean.Gyl017;
import com.zd.csms.bank.bean.Gyl019;
import com.zd.csms.bank.bean.Gyl020VO;
import com.zd.csms.bank.bean.Gyl021;
import com.zd.csms.bank.bean.Gyl022;
import com.zd.csms.bank.bean.Gyl025;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDockDAO;
import com.zd.csms.bank.dock.ZheShangBankUtil;
import com.zd.csms.bank.service.BankDockService;
import com.zd.csms.bank.util.SocketClient;
import com.zd.csms.bank.web.form.BankInterfaceForm;
import com.zd.csms.bank.web.mapper.Gyl009ImportRowMapper;
import com.zd.csms.bank.web.mapper.Gyl013ExcelRowMapper;
import com.zd.csms.bank.web.mapper.Gyl016ImportRowMapper;
import com.zd.csms.bank.web.mapper.Gyl020ImportRowMapper;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;
import com.zd.tools.thumbPage.impl.ThumbPageToolsForOracle;

public class BankInterfaceAction extends ActionSupport {
	private static final String host;
	private static final int port;
	private static final String currentCustNo;
	private static final String custType;
	private static final int pageSize=10;
	private static final int pageMaxNum = 50;
	//private IBankDockDAO dao = BankDAOFactory.getBankDockDAO();
	
	private static final int[] roles = new int[]{
			RoleConstants.SUPERVISORY.getCode()
	};
	
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, roles);
	}
	
	static {
		host = SystemProperty.getPropertyValue("bankdock.properties", "zs.host");
		port = Integer.parseInt(SystemProperty.getPropertyValue("bankdock.properties", "zs.port"));
		currentCustNo = SystemProperty.getPropertyValue("bankdock.properties", "zs.currentCustNo");
		custType = SystemProperty.getPropertyValue("bankdock.properties", "zs.custType");
	}
	
	
	
	/**
	 * 跳转页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String page = request.getParameter("toPage");
		

		ActionForward af = new ActionForward("/WEB-INF/jsp/bankinterface/" + page + ".jsp");
		return af;
	}
	
	public ActionForward updateGyl001toLocal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BankDockService service = new BankDockService();
		service.autoUpdateCust();
		response.getWriter().write("success");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	
	public static List<Gyl001> findAllList() throws Exception{
		Map<String, Object> head = ZheShangBankUtil.getBaseHeadList("gyl001", "");
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("currentCustNo", currentCustNo);
		body.put("custType", custType);
		body.put("pageSize", "50");
		body.put("pledgeName", "");
		body.put("custNo", "");
		int totalCount = 0;
		int pageIndex=1;
		List<Gyl001> resultList = new ArrayList<Gyl001>();
		do{
			body.put("pageIndex", pageIndex+++"");
			SocketClient socket = new SocketClient(host, port);
			
			String xml = socket.send(head, body);
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			if (!ZheShangBankUtil.getRetCode(ap.element("head"))) {
				return null;
			}

			Element bodyNode = ap.element("body");
			if(!bodyNode.hasContent()){
				return null;
			}
			totalCount = Integer.parseInt(bodyNode.element("totalCount").getText());

			if(totalCount<=0)
				return null;
			
			
			Element list = bodyNode.element("custList");
			List infos = list.elements("custInfo");
			if (infos != null)
				for (Iterator it = infos.iterator(); it.hasNext();) {
					Element info = (Element) it.next();
					Gyl001 bean = ZheShangBankUtil.getBean(Gyl001.class, info);
					resultList.add(bean);
				}
			totalCount-=50;
		}while(totalCount>0);
		return resultList;
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
	public ActionForward gyl001(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl001", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		
		boolean flag = commonRequest(mapping, request, "cust", Gyl001.class,
				new String[]{"currentCustNo", "custType", "custNo","pledgeName","pageSize","pageIndex"});
		
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		
		request.setAttribute("custNo", request.getParameter("custNo"));
		request.setAttribute("pledgeName", request.getParameter("pledgeName"));
		
		return mapping.findForward("gyl001");
	}

	/**
	 * 查询当前客户项下的所有通知书列表(按照通知书编号倒序排列) 1：监管公司只能查询到监管公司对应的通知书。
	 * 2：授信客户只能查询到授信客户对应的通知书。 3：核心厂商只能查询到核心厂商对应的通知书。 根据是否可回执这个字段来判断通知书是否有回执操作。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl002(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setOptions(request);
		int currRole = getCurrRole(request);
		if(StringUtil.isEmpty(request.getParameter("startDate"))){
			request.setAttribute("startDate", DateUtil.getStringFormatByDate(DateUtil.addMonth(new Date(), -2), "yyyyMMdd"));
		}
		if(StringUtil.isEmpty(request.getParameter("endDate"))){
			request.setAttribute("endDate", DateUtil.getStringFormatByDate(new Date(), "yyyyMMdd"));
		}
		//监管员必须传客户号，不然直接返回页面
		if(currRole == RoleConstants.SUPERVISORY.getCode()&&StringUtil.isEmpty(request.getParameter("custNo"))){
			return mapping.findForward("gyl002");
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl002", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		
		
		boolean flag = commonRequest(mapping, request, "notice", Gyl002.class,
				new String[]{"currentCustNo", "custType", "custNo",
				"noticeType", "status", "startDate", "endDate","pageSize","pageIndex"});
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		request.setAttribute("custNo", request.getParameter("custNo"));
		request.setAttribute("noticeType", request.getParameter("noticeType"));
		request.setAttribute("status", request.getParameter("status"));
		String startDate = request.getParameter("startDate");
		if(StringUtil.isNotEmpty(startDate)){
			StringBuffer sb = new StringBuffer(startDate);
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("startDate", sb.toString());
		}else{
			StringBuffer sb = new StringBuffer(request.getAttribute("startDate").toString());
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("startDate", sb.toString());
		}
		String endDate = request.getParameter("endDate");
		if(StringUtil.isNotEmpty(endDate)){
			StringBuffer sb = new StringBuffer(endDate);
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("endDate", sb.toString());
		}else{
			StringBuffer sb = new StringBuffer(request.getAttribute("endDate").toString());
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("endDate", sb.toString());
		}
		
		return mapping.findForward("gyl002");
	}

	/**
	 * 根据通知书编号查询到未回执确认的通知书，进行回执确认。（适用于所有通知确认回执） 根据通知书列表中的是否可回执的字段来判断是否有回执操作。
	 * 通知书回执必须在通知书详情页面操作，因为实际业务操作过程中可以对接口发送供应链的系统中的数据进行修改。 回执确认时才是最终数据。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl003(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*if(request.getParameter("key")==null){
			request.setAttribute("noticeAckId", request.getParameter("noticeAckId"));
			request.setAttribute("noticeType", request.getParameter("noticeType"));
			return mapping.findForward("gyl003");
		}*/
		String methodName = request.getParameter("method");
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		request.setAttribute("result", "01");
		request.setAttribute("sellerChecker", "1");
		request.setAttribute("remark", "");
		Map<String, Object> body = null;
		if (body == null) {
			body = new HashMap<String, Object>();
			ZheShangBankUtil.autoFill(body, request, "currentCustNo", "custType", "noticeAckId", "result", "sellerChecker",
					"remark");
		}
		SocketClient socket = new SocketClient(host, port);
		Map<String, Object> head = ZheShangBankUtil.getBaseHeadList(methodName, "");
		String xml = socket.send(head, body);
		xml = xml.substring(2);
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		Element returnhead = ap.element("head");
		if (!ZheShangBankUtil.getRetCode(returnhead)) {
			String msg = returnhead.element("ret_info").getText();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(msg);
			response.getWriter().flush();
			response.getWriter().close();
			return null;
		}
		response.getWriter().write("success");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	/**
	 * 监管公司协议查询,可查询客户向下所有的有效协议。(按照协议号倒序排列)根据当前客户类型来控制查询权限。 1：授信客户能查询到项下所有有效的监管协议
	 * 2：监管公司能查询到与之签订所有有效的监管协议 3：核心厂商无此接口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl004(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		int currRole = getCurrRole(request);
		//监管员必须传客户号，不然直接返回页面
		if(currRole == RoleConstants.SUPERVISORY.getCode()&&StringUtil.isEmpty(request.getParameter("custNo"))){
			return mapping.findForward("gyl004");
		}
		
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl004", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		
		boolean flag = commonRequest(mapping, request, "protocol", Gyl004.class, 
				new String[]{"currentCustNo", "custType",
				"protocolNo", "protocolCode", "custNo","pageSize","pageIndex"});
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		request.setAttribute("protocolNo", request.getParameter("protocolNo"));
		request.setAttribute("protocolCode", request.getParameter("protocolCode"));
		request.setAttribute("custNo", request.getParameter("custNo"));
		
		return mapping.findForward("gyl004");
	}

	/**
	 * 质押合同查询,可查询所有有效的监管协议项下有效的质押合同信息。(按照质押合同号倒序排列)根据当前系统客户号及客户类型来控制查询权限
	 * 1：监管公司能查询到与之签订监管协议（有效的）的项下有效的质押合同 2：授信客户能查询所有有效的监管协议项下有效的质押合同 3：核心厂商无此接口
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl005(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		int currRole = getCurrRole(request);
		//监管员必须传客户号，不然直接返回页面
		if(currRole == RoleConstants.SUPERVISORY.getCode()&&StringUtil.isEmpty(request.getParameter("custNo"))){
			return mapping.findForward("gyl005");
		}
		
		request.setAttribute("gyl005Synchronize", "");
		if(StringUtil.isNotEmpty(request.getParameter("gyl005Synchronize"))){
			gyl005Synchronize(mapping, form, request, response);
		}
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl005", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		boolean flag = commonRequest(mapping, request, "pledgecont", Gyl005.class,
				new String[]{"currentCustNo", "custType",
				"pledgeContractId", "pledgeContractCode", "custNo", "protocolNo","pageSize","pageIndex"});

		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		request.setAttribute("pledgeContractId", request.getParameter("pledgeContractId"));
		request.setAttribute("pledgeContractCode", request.getParameter("pledgeContractCode"));
		request.setAttribute("custNo", request.getParameter("custNo"));
		request.setAttribute("protocolNo", request.getParameter("protocolNo"));
		
		return mapping.findForward("gyl005");
	}
	/**
	 * 将质押合同编号根据客户号与系统同步
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl005Synchronize(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageIndex=1;
		request.setAttribute("pageSize", "50");
		request.setAttribute("pageIndex", pageIndex+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		boolean flag = commonRequest(mapping, request, "pledgecont", Gyl005.class,
				new String[]{"currentCustNo", "custType",
				"pledgeContractId", "pledgeContractCode", "custNo", "protocolNo","pageSize","pageIndex"});

		List list = (List) request.getAttribute("list");
		int totalCount = 0;
		if(request.getAttribute("totalCount")!=null){
			totalCount = Integer.parseInt(request.getAttribute("totalCount").toString());
		}
		if(totalCount > 50){
			while((totalCount-=50)>0){
				request.setAttribute("pageIndex", ++pageIndex+"");
				flag = commonRequest(mapping, request, "pledgecont", Gyl005.class,
						new String[]{"currentCustNo", "custType",
						"pledgeContractId", "pledgeContractCode", "custNo", "protocolNo","pageSize","pageIndex"});
				List list2 = (List) request.getAttribute("list");
				if(list2!=null&&list2.size()>0){
					list.addAll(list2);
				}
			}
		}
		Map<String,Object> map=new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd");
		if(list!=null && list.size()>0){
			for(Object o:list){
				Gyl005 current=(Gyl005) o;
				if(map.get(current.getCustNo())==null){
					map.put(current.getCustNo(), current);
				}else{
					Gyl005 origin=(Gyl005) map.get(current.getCustNo());
					Date originEndDate=null;
					if(origin.getEndDate()!=null){
						originEndDate=formatter.parse(origin.getEndDate());
					}else{
						originEndDate=formatter.parse("18000101");
					}
					if(current.getEndDate()!=null && formatter.parse(current.getEndDate()).after(originEndDate)){
						map.put(current.getCustNo(), current);
					}
				}
			}
		}		
		DistribsetService distribsetService=new DistribsetService();
		boolean result=false;
	    for (String key : map.keySet()) {
	    	Gyl005 g=(Gyl005) map.get(key);
	    	result=distribsetService.updateContractNoByZsCustNo(g.getPledgeContractCode(), g.getCustNo());
		}
	    //将执行结果及消息设置到request为页面处理使用
	    request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
	    request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "同步成功！");
		return mapping.findForward("gyl005");
	}
	/**
	 * 根据当前系统客户号查询自己所发起的流程申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl006(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		int currRole = getCurrRole(request);
		if(StringUtil.isEmpty(request.getParameter("startDate"))){
			request.setAttribute("startDate", DateUtil.getStringFormatByDate(DateUtil.addMonth(new Date(), -2), "yyyyMMdd"));
		}
		if(StringUtil.isEmpty(request.getParameter("endDate"))){
			request.setAttribute("endDate", DateUtil.getStringFormatByDate(new Date(), "yyyyMMdd"));
		}
		//监管员必须传客户号，不然直接返回页面
		if(currRole == RoleConstants.SUPERVISORY.getCode()&&StringUtil.isEmpty(request.getParameter("custNo"))){
			return mapping.findForward("gyl006");
		}
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl006", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		if(StringUtil.isEmpty(request.getParameter("status"))){
			request.setAttribute("status", "01");
		}
		if(StringUtil.isEmpty(request.getParameter("applyType"))){
			request.setAttribute("applyType", "2116");
		}
		
		boolean flag = commonRequest(mapping, request, "apply", Gyl006.class, "currentCustNo", "custType", "serialNo",
				"applyType", "status", "startDate", "endDate", "custNo","pageSize","pageIndex");

		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		request.setAttribute("serialNo", request.getParameter("serialNo"));
		request.setAttribute("applyType", request.getParameter("applyType"));
		request.setAttribute("status", request.getParameter("status"));
		request.setAttribute("custNo", request.getParameter("custNo"));
		
		
		String startDate = request.getParameter("startDate");
		if(StringUtil.isNotEmpty(startDate)){
			StringBuffer sb = new StringBuffer(startDate);
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("startDate", sb.toString());
		}else{
			StringBuffer sb = new StringBuffer(request.getAttribute("startDate").toString());
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("startDate", sb.toString());
		}
		String endDate = request.getParameter("endDate");
		if(StringUtil.isNotEmpty(endDate)){
			StringBuffer sb = new StringBuffer(endDate);
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("endDate", sb.toString());
		}else{
			StringBuffer sb = new StringBuffer(request.getAttribute("endDate").toString());
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("endDate", sb.toString());
		}
		
		
		return mapping.findForward("gyl006");
	}

	/**
	 * 申请信息同步操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syncGyl007toLocal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String serialNo = request.getParameter("serialNo");
		if (!CarOperationService.getInstance().serialNoExist(serialNo)) {
			List<Gyl007> syncGyl007 = syncGyl007(request,response);
			if(null != syncGyl007 && syncGyl007.size() > 0){
				String applyType = request.getParameter("applyType");
				String status = request.getParameter("status");
				boolean flag = CarOperationService.getInstance().syncGyl007(user,syncGyl007,applyType,status,serialNo);
				if(flag){
					response.getWriter().write("success");
				}
			}else{
				response.getWriter().write("nosync");
			}
		}else{
			response.getWriter().write("sync");
		}
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	
	public static List<Gyl007> syncGyl007(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> head = ZheShangBankUtil.getBaseHeadList("gyl007", "");
		Map<String, Object> body = new HashMap<String, Object>();
		String serialNo = request.getParameter("serialNo");
		body.put("currentCustNo", currentCustNo);
		body.put("custType", custType);
		body.put("pageSize", "50");
		body.put("serialNo", serialNo);//交易流水号
		
		int totalCount = 0;
		int pageIndex=1;
		List<Gyl007> resultList = new ArrayList<Gyl007>();
		do{
			body.put("pageIndex", pageIndex+++"");
			SocketClient socket = new SocketClient(host, port);
			
			String xml = socket.send(head, body);	
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			if (!ZheShangBankUtil.getRetCode(ap.element("head"))) {
				return null;
			}

			Element bodyNode = ap.element("body");
			if(!bodyNode.hasContent()){
				return null;
			}
			totalCount = Integer.parseInt(bodyNode.element("totalCount").getText());

			if(totalCount<=0){
				return null;
			}
			Element list = bodyNode.element("morgageList");
			List infos = list.elements("morgageInfo");
			Gyl007 bean = null;
			Element info = null;
			if (infos != null)
				for (Iterator it = infos.iterator(); it.hasNext();) {
					info = (Element) it.next();
					bean = ZheShangBankUtil.getBean(Gyl007.class, info);
					resultList.add(bean);
				}
			totalCount-=50;
		}while(totalCount>0);
		return resultList;
	}
	
	/**
	 * 根据流水号查询流程申请明细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl007(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl007", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		boolean flag = commonRequest(mapping, request, "morgage", Gyl007.class,
				new String[]{"currentCustNo", "custType",
				"serialNo","pageSize","pageIndex"});
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		Document doc = (Document) request.getAttribute("doc");
		if(doc!=null){
			Element ap = doc.getRootElement();
			Element bodyNode = ap.element("body");
			request.setAttribute("protocolCode", bodyNode.element("protocolCode").getText());
			request.setAttribute("pledgeContractCode", bodyNode.element("pledgeContractCode").getText());
			request.setAttribute("lnciNo", bodyNode.element("lnciNo").getText());
			request.setAttribute("custNo", bodyNode.element("custNo").getText());
			request.setAttribute("pledgeName", bodyNode.element("pledgeName").getText());
			request.setAttribute("applyType", bodyNode.element("applyType").getText());
			request.setAttribute("status", bodyNode.element("status").getText());
		}
		request.setAttribute("serialNo", request.getParameter("serialNo"));

		return mapping.findForward("gyl007");
	}

	
	/**
	 * 借据信息查询: 1：授信客户能查询到与之对应的主合同项下所有的借据信息
	 * 2：监管公司能查询到与之签订的监管协议的授信客户主合同项下的所有借据信息（只有先票后货、动产静态和动产动态才有监管公司查询借据信息，
	 * 其它不允许查询）
	 * 3：业务编号（先票后货对应先票后货协议、厂商一票通对应厂商一票通协议、动产对应为监管协议、保理对应保理协议号、应收账款质押对应应收账款质押协议号）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl022(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		request.setAttribute("custList", OptionUtil.getShouxinCust(request,currRole));
		if(StringUtil.isEmpty(request.getParameter("custNo"))){
			return mapping.findForward("gyl022");
		}
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl022", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		boolean flag = commonRequest(mapping, request, "lnci", Gyl022.class, "currentCustNo", "custType", "custNo",
				"businessType", "businessCode", "contractId", "startDate1", "startDate2", "endDate1", "endDate2",
				"status", "lnciNo", "lnciId", "pageSize","pageIndex");
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		
		request.setAttribute("custNo", request.getParameter("custNo"));
		request.setAttribute("businessType", request.getParameter("businessType"));
		request.setAttribute("businessCode", request.getParameter("businessCode"));
		request.setAttribute("contractId", request.getParameter("contractId"));
		request.setAttribute("status", request.getParameter("status"));
		request.setAttribute("lnciNo", request.getParameter("lnciNo"));
		request.setAttribute("lnciId", request.getParameter("lnciId"));
		
		String startDate = request.getParameter("startDate1");
		if(StringUtil.isNotEmpty(startDate)){
			StringBuffer sb = new StringBuffer(startDate);
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("startDate1", sb.toString());
		}
		String startDate2 = request.getParameter("startDate2");
		if(StringUtil.isNotEmpty(startDate2)){
			StringBuffer sb = new StringBuffer(startDate2);
			if(sb.indexOf("-")<0)
				sb.insert(4, "-").insert(7, "-");
			request.setAttribute("startDate2", sb.toString());
		}
		
		
		return mapping.findForward("gyl022");
	}

	/**
	 * 根据业务模式及业务编号查询敞口信息，主要是供提货使用
	 * 业务编号：根据业务模式下拉（先票后货-质押合同ID、厂商一票通-借据ID、动产-质押合同ID）
	 * 保证金账号：涉及到保证金缴款保证金账号、户名必输。当出账模式为银票（包括纸质银票、电子银票）时，会涉及到保证金账号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl018(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		String businessType = request.getParameter("businessType");
		String businessNo = request.getParameter("businessNo");
		String custNo = request.getParameter("custNo");
		String bailAccount = request.getParameter("bailAccount");
		String bailAccountNm = request.getParameter("bailAccountNm");
		if(StringUtil.isEmpty(businessType)||StringUtil.isEmpty(businessNo)||StringUtil.isEmpty(businessNo)){
			return mapping.findForward("gyl018");
		}
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		request.setAttribute("businessType", businessType);
		request.setAttribute("businessNo", businessNo);
		request.setAttribute("custNo", custNo);
		request.setAttribute("bailAccount", bailAccount);
		request.setAttribute("bailAccountNm", bailAccountNm);

		String methodName = request.getParameter("method");
		Map<String, Object> body = null;
		if (body == null) {
			body = new HashMap<String, Object>();
			ZheShangBankUtil.autoFill(body, request, "currentCustNo", "custType", "businessType", "businessNo", "custNo",
					"bailAccount", "bailAccountNm");
		}
		SocketClient socket = new SocketClient(host, port);
		Map<String, Object> head = ZheShangBankUtil.getBaseHeadList(methodName, "");
		String xml = socket.send(head, body);
		xml = xml.substring(2);
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZheShangBankUtil.getRetCode(ap.element("head"))) {
			return mapping.findForward("gyl018");
		}
		Element bodyNode = ap.element("body");
		Element amount = bodyNode.element("amount");
		if(amount!=null){
			request.setAttribute("amount", amount.getText());			
		}
		return mapping.findForward("gyl018");
	}
	
	/**
	 * 根据通知书编号查询通知书信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl017(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String custNo = request.getParameter("custNo");
		String noticeId = request.getParameter("noticeId");
		request.setAttribute("custNo", custNo);
		request.setAttribute("noticeId", noticeId);// 通知书编号
		if (StringUtil.isEmpty(custNo) || StringUtil.isEmpty(noticeId)) {
			return mapping.findForward("gyl017");
		}
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl017", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		boolean flag = commonRequest(mapping, request, "morgage", Gyl017.class,
				new String[]{"currentCustNo", "custType",
				"custNo","noticeId","pageSize","pageIndex"});
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		Document doc = (Document) request.getAttribute("doc");
		if(doc!=null){
			Element ap = doc.getRootElement();
			Element bodyNode = ap.element("body");
			request.setAttribute("fourProtocolCode", bodyNode.element("fourProtocolCode").getText());//先票后货协议号
			request.setAttribute("protocolCode", bodyNode.element("protocolCode").getText());//监管协议号
			request.setAttribute("pledgeContractCode", bodyNode.element("pledgeContractCode").getText());//质押合同编号
			
			String signDate = bodyNode.element("signDate").getText();
			if (StringUtil.isNotEmpty(signDate)) {
				StringBuffer sb = new StringBuffer(signDate);
				request.setAttribute("signDate", sb.insert(4, "-").insert(7, "-").toString());
			}
			request.setAttribute("moniName", bodyNode.element("moniName").getText());//监管人名称
			request.setAttribute("pledgeeCertName", bodyNode.element("pledgeeCertName").getText());//质权人名称
			request.setAttribute("exposureAmount", bodyNode.element("exposureAmount").getText());//敞口额度
			request.setAttribute("ration", bodyNode.element("ration").getText());//抵质押率
		}

		return mapping.findForward("gyl017");
	}
	
	/**
	 * 借据信息同步操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syncGyl022toLocal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Gyl022> syncGyl022 = syncGyl022(mapping,form,request,response);
		if(null != syncGyl022 && syncGyl022.size() > 0){
			boolean flag = BankDockService.getInstance().syncGyl022(syncGyl022);
			if(flag){
				response.getWriter().write("success");
			}
		}else{
			response.getWriter().write("nosync");
		}
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	public static List<Gyl022> syncGyl022(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> head = ZheShangBankUtil.getBaseHeadList("gyl022", "");
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("currentCustNo", currentCustNo);
		body.put("custType", custType);
		body.put("pageSize", "50");
		
		ZheShangBankUtil.autoFill(body, request, "custNo",
				"businessType", "businessCode", "contractId", "startDate1", "startDate2", "endDate1", "endDate2", "status", "lnciNo", "lnciId");
		
		int totalCount = 0;
		int pageIndex=1;
		List<Gyl022> resultList = new ArrayList<Gyl022>();
		do{
			body.put("pageIndex", pageIndex+++"");
			SocketClient socket = new SocketClient(host, port);
			
			String xml = socket.send(head, body);	
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			if (!ZheShangBankUtil.getRetCode(ap.element("head"))) {
				return null;
			}

			Element bodyNode = ap.element("body");
			if(!bodyNode.hasContent()){
				return null;
			}
			totalCount = Integer.parseInt(bodyNode.element("totalCount").getText());

			if(totalCount<=0){
				return null;
			}
			Element list = bodyNode.element("lnciList");
			List infos = list.elements("lnciInfo");
			Gyl022 bean = null;
			Element info = null;
			if (infos != null)
				for (Iterator it = infos.iterator(); it.hasNext();) {
					info = (Element) it.next();
					bean = ZheShangBankUtil.getBean(Gyl022.class, info);
					resultList.add(bean);
				}
			totalCount-=50;
		}while(totalCount>0);
		return resultList;
	}
	
	/**
	 * 票据信息同步操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syncGyl025toLocal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Gyl025> syncGyl025 = syncGyl025(mapping,form,request,response);
		String custNo = request.getParameter("custNo");
		PrintWriter writer = response.getWriter();
		int distribsetid = 0;
		if (null != custNo) {
			distribsetid = DistribsetService.getInstance().findDistidByCustNo(custNo);
		}
		if (null != syncGyl025 && syncGyl025.size() > 0&&distribsetid>0) {
			boolean flag = BankDockService.getInstance().syncGyl025(syncGyl025,distribsetid);
			if(flag){
				writer.write("success");
			}
		}else{
			writer.write("nosync");
		}
		writer.flush();
		writer.close();
		return null;
	}
	
	public static List<Gyl025> syncGyl025(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> head = ZheShangBankUtil.getBaseHeadList("gyl025", "");
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("currentCustNo", currentCustNo);
		body.put("custType", custType);
		body.put("pageSize", "50");
		
		String billDate1 = request.getParameter("billDate1");
		String billDate2 = request.getParameter("billDate2");
		if(StringUtil.isNotEmpty(billDate1)){
			billDate1 = billDate1.replaceAll("-", "");
			body.put("billDate1", billDate1);
		}
		if(StringUtil.isNotEmpty(billDate2)){
			billDate2 = billDate2.replaceAll("-", "");
			body.put("billDate2", billDate2);
		}
		
		ZheShangBankUtil.autoFill(body, request, "custNo",
				"billNo", "lnciNo", "lnciId", "businessType", "billEndDate1", "billEndDate2");
		
		int totalCount = 0;
		int pageIndex=1;
		List<Gyl025> resultList = new ArrayList<Gyl025>();
		do{
			body.put("pageIndex", pageIndex+++"");
			SocketClient socket = new SocketClient(host, port);
			
			String xml = socket.send(head, body);	
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			if (!ZheShangBankUtil.getRetCode(ap.element("head"))) {
				return null;
			}

			Element bodyNode = ap.element("body");
			if(!bodyNode.hasContent()){
				return null;
			}
			totalCount = Integer.parseInt(bodyNode.element("totalCount").getText());

			if(totalCount<=0){
				return null;
			}
			Element list = bodyNode.element("billList");
			List infos = list.elements("billInfo");
			Gyl025 bean = null;
			Element info = null;
			if (infos != null)
				for (Iterator it = infos.iterator(); it.hasNext();) {
					info = (Element) it.next();
					bean = ZheShangBankUtil.getBean(Gyl025.class, info);
					resultList.add(bean);
				}
			totalCount-=50;
		}while(totalCount>0);
		return resultList;
	}

	/**
	 * 票据信息查询: 1、 授信客户能查询到与之对应的主合同项下所有的借据项下的票据信息 2、
	 * 监管公司能查询到与之签订的监管协议的授信客户主合同项下的所有借据项下的票据信息（只有先票后货、动产静态和动产动态才有监管公司查询借据项下的票据信息
	 * ，其它不允许查询） 3、 查询结果根据借据ID +票号排序
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl025(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		request.setAttribute("custList", OptionUtil.getShouxinCust(request,currRole));
		if(StringUtil.isEmpty(request.getParameter("custNo"))){
			return mapping.findForward("gyl025");
		}
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl025", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		boolean flag = commonRequest(mapping, request, "bill", Gyl025.class, "currentCustNo", "custType", "custNo",
				"billNo", "lnciNo", "lnciId", "businessType", "billDate1","billDate2", "billEndDate1", "billEndDate2","pageSize","pageIndex");
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		
		request.setAttribute("custNo", request.getParameter("custNo"));//客户号
		request.setAttribute("billNo", request.getParameter("billNo"));//票号
		request.setAttribute("lnciNo", request.getParameter("lnciNo"));//借据号
		request.setAttribute("lnciId", request.getParameter("lnciId"));//借据ID
		request.setAttribute("businessType", request.getParameter("businessType"));//业务模式
		
		String billDate1 = request.getParameter("billDate1");
		String billDate2 = request.getParameter("billDate2");
		String billEndDate1 = request.getParameter("billEndDate1");
		String billEndDate2 = request.getParameter("billEndDate2");
		//开始日
		if(StringUtil.isNotEmpty(billDate1)){
			StringBuffer sb = new StringBuffer(billDate1);
			if (sb.indexOf("-") < 0) {
				sb.insert(4, "-").insert(7, "-").toString();
			}
			request.setAttribute("billDate1", sb.toString());
		}
		if(StringUtil.isNotEmpty(billDate2)){
			StringBuffer sb = new StringBuffer(billDate2);
			if (sb.indexOf("-") < 0) {
				sb.insert(4, "-").insert(7, "-").toString();
			}
			request.setAttribute("billDate2", sb.toString());
		}
		//到期日
		if(StringUtil.isNotEmpty(billEndDate1)){
			StringBuffer sb = new StringBuffer(billEndDate1);
			if (sb.indexOf("-") < 0) {
				sb.insert(4, "-").insert(7, "-").toString();
			}
			request.setAttribute("billEndDate1", sb.toString());
		}
		if(StringUtil.isNotEmpty(billEndDate2)){
			StringBuffer sb = new StringBuffer(billEndDate2);
			if (sb.indexOf("-") < 0) {
				sb.insert(4, "-").insert(7, "-").toString();
			}
			request.setAttribute("billEndDate2", sb.toString());
		}
		request.setAttribute("custList", OptionUtil.getShouxinCust(request,currRole));
		return mapping.findForward("gyl025");
	}

	public ActionForward pregyl016(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		String custNo =  request.getParameter("custNo");
		String pledgeContractCode =  request.getParameter("pledgeContractCode");
		String warehouseAddress =  request.getParameter("warehouseAddress");
		request.setAttribute("custNo",custNo );
		request.setAttribute("pledgeContractCode",pledgeContractCode);
		request.setAttribute("warehouseAddress",warehouseAddress);
		
		if(StringUtil.isEmpty(custNo)||StringUtil.isEmpty(pledgeContractCode)||StringUtil.isEmpty(warehouseAddress)){
			return mapping.findForward("pregyl016");
		}
		
		BankInterfaceForm bForm = (BankInterfaceForm) form;
		FormFile file =  bForm.getImportFile();
		
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		
		@SuppressWarnings("unchecked")
		List<Gyl016VO> list = (List<Gyl016VO>) importFile.readAll(1,new Gyl016ImportRowMapper());
		if(list==null||list.size()==0){
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("pregyl016");
		}
		
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
		request.setAttribute("list", list);
		request.getSession().setAttribute("gyl016List", list);
		return mapping.findForward("pregyl016");
	}
	
	/**
	 * 押品出质入库的申请，数据发送到供应链系统后供应链系统的操作员可以对接口发送过来的数据进行修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl016(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Gyl016VO> addList = (List<Gyl016VO>) request.getSession().getAttribute("gyl016List");
		request.setAttribute("list", addList);
		gyl016(addList,request);
		return mapping.findForward("pregyl016");
	}
	
	public boolean gyl016(List<Gyl016VO> addList,HttpServletRequest request) throws Exception{
		int totalCount  = addList.size();
		if(totalCount>50){
			setOptions(request);
			request.setAttribute("message", "每次最多发送50条");
			return false;
		}
		String methodName = "gyl016";
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		
		String custNo = request.getParameter("custNo");
		String pledgeContractCode = request.getParameter("pledgeContractCode");
		String warehouseAddress = request.getParameter("warehouseAddress");
		if(StringUtil.isEmpty(custNo))
			custNo = (String) request.getAttribute("custNo");
		if(StringUtil.isEmpty(pledgeContractCode))
			pledgeContractCode = (String) request.getAttribute("pledgeContractCode");
		if(StringUtil.isEmpty(warehouseAddress))
			warehouseAddress = (String) request.getAttribute("warehouseAddress");
		
		request.setAttribute("custNo", custNo);
		request.setAttribute("pledgeContractCode", pledgeContractCode);
		request.setAttribute("warehouseAddress", warehouseAddress);
		
		String uuid =  UUID.randomUUID().toString().replaceAll("-","");
		bodyMap.put("currentCustNo", currentCustNo);
		bodyMap.put("custType", custType);
		bodyMap.put("custNo", custNo);
		bodyMap.put("pledgeContractCode", pledgeContractCode);
		bodyMap.put("warehouseAddress", warehouseAddress);
		bodyMap.put("businessSerialNo",uuid);
		bodyMap.put("totalSize", totalCount+"");
		

		int pageNum = (int)Math.ceil(((double)totalCount/(double)pageMaxNum));
		for (int i = 0; i < pageNum; i++) {
			int pageSize=0;
			if(pageNum == (i+1)){
				bodyMap.put("pageSize", totalCount);
				bodyMap.put("submitFlag", "1");
				pageSize = totalCount;
			}else{
				bodyMap.put("submitFlag", "0");
				bodyMap.put("pageSize", pageMaxNum);
				totalCount-=pageMaxNum;
				pageSize=pageMaxNum; 
			}
			bodyMap.put("pageIndex", (i+1)+"");
			
			List<Map<String, Object>> inputList = new ArrayList<Map<String,Object>>();
			for (int j = 0; j < pageSize; j++) {
				Gyl016VO gyl016 = addList.get(0);
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("morgageNo", gyl016.getMorgageNo());
				temp.put("name", gyl016.getName());
				temp.put("model", gyl016.getModel());
				temp.put("manufacturer", gyl016.getManufacturer());
				temp.put("quantity", gyl016.getQuantity());
				temp.put("mortgageUnits", gyl016.getMortgageUnits());
				temp.put("price", gyl016.getPrice());
				temp.put("engineNo", gyl016.getEngineNo());
				temp.put("chassisNo", gyl016.getChassisNo());
				temp.put("certificationNo", gyl016.getCertificationNo());
				temp.put("memo", gyl016.getMemo());
				inputList.add(temp);
				addList.remove(0);
			}
			bodyMap.put("morgage",inputList);
			
			SocketClient socket = new SocketClient(host, port);
			Map<String, Object> head = ZheShangBankUtil.getBaseHeadList(methodName, "");
			String xml = socket.send(head, bodyMap);
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			Element returnHead = ap.element("head");
			if (!ZheShangBankUtil.getRetCode(returnHead)) {
				setOptions(request);
				request.setAttribute("message", "发送失败:"+returnHead.element("ret_info").getText());
				return false;
			}
		}
		request.setAttribute("message", "发送成功");
		return true;
	}

	/**
	 * 发货清单录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pregyl009(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		String custNo =  request.getParameter("custNo");
		String lnciId =  request.getParameter("lnciId");
		request.setAttribute("custNo",custNo );
		request.setAttribute("lnciId",lnciId);
		
		if(StringUtil.isEmpty(custNo)||StringUtil.isEmpty(lnciId)){
			return mapping.findForward("pregyl009");
		}
		
		BankInterfaceForm bForm = (BankInterfaceForm) form;
		FormFile file =  bForm.getImportFile();
		
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		
		@SuppressWarnings("unchecked")
		List<Gyl009VO> list = (List<Gyl009VO>) importFile.readAll(1,new Gyl009ImportRowMapper());
		if(list==null||list.size()==0){
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("pregyl009");
		}
		
		for (int i = 0; i < list.size(); i++) {
			Gyl009VO gyl009 = list.get(i);
			if(StringUtil.isEmpty(gyl009.getModel())){
				request.setAttribute("message", "第"+(i+2)+"行押品规格型号不能为空");
				return mapping.findForward("pregyl009");
			}
			if(StringUtil.isEmpty(gyl009.getQuantity())){
				request.setAttribute("message", "第"+(i+2)+"行数量/重量不能为空");
				return mapping.findForward("pregyl009");
			}
		}
		request.setAttribute("list", list);
		request.getSession().setAttribute("gyl009List", list);
		return mapping.findForward("pregyl009");
	}
	
	/**
	 * 核心厂商发货清单录入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl009(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Gyl009VO> addList = (List<Gyl009VO>) request.getSession().getAttribute("gyl009List");
		request.setAttribute("list", addList);
		this.gyl009(addList,request);
		return mapping.findForward("pregyl009");
	}
	
	public boolean gyl009(List<Gyl009VO> addList,HttpServletRequest request) throws Exception{
		int totalCount  = addList.size();
		String methodName = "gyl009";
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		String custNo = request.getParameter("custNo");
		String lnciId = request.getParameter("lnciId");
		if(StringUtil.isEmpty(custNo))
			custNo = (String) request.getAttribute("custNo");
		if(StringUtil.isEmpty(lnciId))
			lnciId = (String) request.getAttribute("lnciId");
		request.setAttribute("custNo", custNo);
		request.setAttribute("lnciId", lnciId);
		String uuid =  UUID.randomUUID().toString().replaceAll("-","");
		bodyMap.put("currentCustNo", currentCustNo);
		bodyMap.put("custType", custType);
		bodyMap.put("custNo", custNo);
		bodyMap.put("lnciId", lnciId);
		bodyMap.put("businessSerialNo",uuid);
		bodyMap.put("totalSize", totalCount+"");
		

		int pageNum = (int)Math.ceil(((double)totalCount/(double)pageMaxNum));
		for (int i = 0; i < pageNum; i++) {
			int pageSize=0;
			if(pageNum == (i+1)){
				bodyMap.put("pageSize", totalCount);
				bodyMap.put("submitFlag", "2");
				pageSize = totalCount;
			}else{
				bodyMap.put("submitFlag", "0");
				bodyMap.put("pageSize", pageMaxNum);
				totalCount-=pageMaxNum;
				pageSize=pageMaxNum; 
			}
			bodyMap.put("pageIndex", (i+1)+"");
			
			List<Map<String, Object>> inputList = new ArrayList<Map<String,Object>>();
			for (int j = 0; j < pageSize; j++) {
				Gyl009VO gyl009 = addList.get(0);
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("name", gyl009.getName());
				temp.put("model", gyl009.getModel());
				temp.put("manufacturer", gyl009.getManufacturer());
				temp.put("quantity", gyl009.getQuantity());
				temp.put("mortgageUnits", gyl009.getMortgageUnits());
				temp.put("price", gyl009.getPrice());
				temp.put("engineNo", gyl009.getEngineNo());
				temp.put("chassisNo", gyl009.getChassisNo());
				temp.put("certificationNo", gyl009.getCertificationNo());
				inputList.add(temp);
				addList.remove(0);
			}
			bodyMap.put("morgage",inputList);
			
			SocketClient socket = new SocketClient(host, port);
			Map<String, Object> head = ZheShangBankUtil.getBaseHeadList(methodName, "");
			String xml = socket.send(head, bodyMap);
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			Element returnHead = ap.element("head");
			if (!ZheShangBankUtil.getRetCode(returnHead)) {
				request.setAttribute("message", "发送失败:"+returnHead.element("ret_info").getText());
				return false;
			}
		}
		request.setAttribute("message", "发送成功");
		return true;
	}


	/**
	 * 用于提货申请时查询在库押品信息。(按照押品编号倒序排列)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl019(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		if(request.getParameter("pledgeContractCode")==null){
			return mapping.findForward("gyl019");
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl019", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(10);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		
		boolean flag = commonRequest(mapping, request, "morgage", Gyl019.class,
				new String[]{"currentCustNo", "custType","protocolNo", "pledgeContractCode","custNo","pageSize","pageIndex"});
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
		}
		
		request.setAttribute("protocolNo", request.getParameter("protocolNo"));
		request.setAttribute("pledgeContractCode", request.getParameter("pledgeContractCode"));
		request.setAttribute("custNo", request.getParameter("custNo"));
		
		return mapping.findForward("gyl019");
	}
	
	public ActionForward pregyl020(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setOptions(request);
		String custNo =  request.getParameter("custNo");
		String pledgeContractCode =  request.getParameter("pledgeContractCode");
		request.setAttribute("custNo",custNo );
		request.setAttribute("pledgeContractCode",pledgeContractCode);
		
		if(StringUtil.isEmpty(custNo)||StringUtil.isEmpty(pledgeContractCode)){
			return mapping.findForward("pregyl020");
		}
		
		BankInterfaceForm bForm = (BankInterfaceForm) form;
		FormFile file =  bForm.getImportFile();
		
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		
		@SuppressWarnings("unchecked")
		List<Gyl020VO> list = (List<Gyl020VO>) importFile.readAll(1,new Gyl020ImportRowMapper());
		if(list==null||list.size()==0){
			request.setAttribute("message", "导入列表不能为空");
			return mapping.findForward("pregyl020");
		}
		
		for (int i = 0; i < list.size(); i++) {
			Gyl020VO gyl020 = list.get(i);
			if(StringUtil.isEmpty(gyl020.getChassisNo())){
				request.setAttribute("message", "第"+(i+2)+"行车架号不能为空");
				return mapping.findForward("pregyl020");
			}
			if(StringUtil.isEmpty(gyl020.getDeliveryQuantity())){
				request.setAttribute("message", "第"+(i+2)+"行提货数量不能为空");
				return mapping.findForward("pregyl020");
			}
		}
		request.setAttribute("list", list);
		request.getSession().setAttribute("gyl020List", list);
		return mapping.findForward("pregyl020");
	}
	
	/**
	 * 出质人发起提货的申请，数据发送到供应链系统后供应链系统的操作员可以对接口发送过来的数据进行修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl020(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Gyl020VO> addList = (List<Gyl020VO>) request.getSession().getAttribute("gyl020List");
		request.setAttribute("list", addList);
		gyl020(addList, request);
		return mapping.findForward("pregyl020");
	}
	
	public boolean gyl020(List<Gyl020VO> addList,HttpServletRequest request) throws Exception{
		int totalCount  = addList.size();
		String methodName="gyl020";
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		
		String custNo = request.getParameter("custNo");
		String pledgeContractCode = request.getParameter("pledgeContractCode");
		if(StringUtil.isEmpty(custNo))
			custNo = (String) request.getAttribute("custNo");
		if(StringUtil.isEmpty(pledgeContractCode))
			pledgeContractCode = (String) request.getAttribute("pledgeContractCode");
		request.setAttribute("custNo", custNo);
		request.setAttribute("pledgeContractCode", pledgeContractCode);
		
		String uuid =  UUID.randomUUID().toString().replaceAll("-","");
		bodyMap.put("currentCustNo", currentCustNo);
		bodyMap.put("custType", custType);
		bodyMap.put("custNo", custNo);
		bodyMap.put("pledgeContractCode", pledgeContractCode);
		bodyMap.put("businessSerialNo",uuid);
		bodyMap.put("totalSize", totalCount+"");
		

		int pageNum = (int)Math.ceil(((double)totalCount/(double)pageMaxNum));
		for (int i = 0; i < pageNum; i++) {
			int pageSize=0;
			if(pageNum == (i+1)){
				bodyMap.put("pageSize", totalCount);
				bodyMap.put("submitFlag", "1");
				pageSize = totalCount;
			}else{
				bodyMap.put("submitFlag", "0");
				bodyMap.put("pageSize", pageMaxNum);
				totalCount-=pageMaxNum;
				pageSize=pageMaxNum; 
			}
			bodyMap.put("pageIndex", (i+1)+"");
			
			List<Map<String, Object>> inputList = new ArrayList<Map<String,Object>>();
			for (int j = 0; j < pageSize; j++) {
				Gyl020VO gyl020 = addList.get(0);
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("chassisNo", gyl020.getChassisNo());
				temp.put("deliveryQuantity", gyl020.getDeliveryQuantity());
				inputList.add(temp);
				addList.remove(0);
			}
			bodyMap.put("morgage",inputList);
			
			SocketClient socket = new SocketClient(host, port);
			Map<String, Object> head = ZheShangBankUtil.getBaseHeadList(methodName, "");
			String xml = socket.send(head, bodyMap);
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			Element returnHead = ap.element("head");
			if (!ZheShangBankUtil.getRetCode(returnHead)) {
				request.setAttribute("message", "发送失败:"+returnHead.element("ret_info").getText());
				return false;
			}
		}
		request.setAttribute("message", "发送成功");
		return true;
	}

	/**
	 * 根据通知书编号查询通知书信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl021(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl021", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(pageSize);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		boolean flag = commonRequest(mapping, request, "morgage", Gyl021.class, "currentCustNo", "custType",
				"noticeId","pageSize","pageIndex");
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize)+1);
		}
		Document doc = (Document) request.getAttribute("doc");
		if(doc!=null){
			Element ap = doc.getRootElement();
			Element bodyNode = ap.element("body");
			request.setAttribute("noticeId", bodyNode.element("noticeId").getText());
			request.setAttribute("custNo", bodyNode.element("custNo").getText());
			request.setAttribute("protocolCode", bodyNode.element("protocolCode").getText());
			request.setAttribute("pledgeContractCode", bodyNode.element("pledgeContractCode").getText());
			request.setAttribute("signDate", bodyNode.element("signDate").getText());
			request.setAttribute("moniName", bodyNode.element("moniName").getText());
			request.setAttribute("pledgeeCertName", bodyNode.element("pledgeeCertName").getText());
			request.setAttribute("pledgeName", bodyNode.element("pledgeName").getText());
		}
		request.setAttribute("serialNo", request.getParameter("serialNo"));

		return mapping.findForward("gyl021");
		
	}

	/**
	 * 根据通知书编号查询价格调整通知书信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl011(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Document doc = null;
		boolean flag = commonRequest(mapping, request, doc, "morgage", Gyl011.class, "currentCustNo", "custType",
				"noticeId");
		Element ap = doc.getRootElement();
		Element bodyNode = ap.element("body");
		setField(request, bodyNode, "pledgeContractCode", "noticeId", "protocolCode", "signDate", "moniName",
				"pledgeeCertName", "custNo");
		if (flag)
			return null;
		else
			return null;
	}

	/**
	 * 质押合同对应先票后货协议项下的发货清单信息用于出质入库添加货物信息。(按照押品编号倒序排列)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyl013(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("gyl013ExtExcel", "");
		if(StringUtil.isNotEmpty(request.getParameter("gyl013ExtExcel"))){
			gyl013ExtExcel(mapping, form, request, response);
		}
		
		if(request.getParameter("pledgeContractId")==null){
			return mapping.findForward("gyl013");
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("gyl013", request);
		ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
		oracleTools.setPageSize(50);
		request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
		request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
		
		request.setAttribute("currentCustNo", currentCustNo);
		request.setAttribute("custType", custType);
		
		
		boolean flag = commonRequest(mapping, request, "morgage", Gyl013.class,
				new String[]{"currentCustNo", "custType", "pledgeContractId","pageSize","pageIndex"});
		if(flag){
			Integer totalCount = (Integer)request.getAttribute("totalCount");
			oracleTools.setTotalItemsNum(totalCount);
			oracleTools.setTotalPagesNum(totalCount%50==0?(totalCount/50):(totalCount/50)+1);
		}
		
		request.setAttribute("pledgeContractId", request.getParameter("pledgeContractId"));
		return mapping.findForward("gyl013");
	}
	
	public ActionForward gyl013ExtExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			if(request.getParameter("pledgeContractId")==null){
				return mapping.findForward("gyl013");
			}
			int pageIndex=1;
			request.setAttribute("pageSize", "50");
			request.setAttribute("pageIndex", pageIndex+"");
			
			request.setAttribute("currentCustNo", currentCustNo);
			request.setAttribute("custType", custType);
			
			boolean flag = commonRequest(mapping, request, "morgage", Gyl013.class,
					new String[]{"currentCustNo", "custType", "pledgeContractId","pageSize","pageIndex"});
			List list = (List) request.getAttribute("list");
			int totalCount = 0;
			if(request.getAttribute("totalCount")!=null){
				totalCount = Integer.parseInt(request.getAttribute("totalCount").toString());
			}
			if(totalCount > 50){
				while((totalCount-=50)>0){
					request.setAttribute("pageIndex", ++pageIndex+"");
					flag = commonRequest(mapping, request, "morgage", Gyl013.class,
							new String[]{"currentCustNo", "custType", "pledgeContractId","pageSize","pageIndex"});
					List list2 = (List) request.getAttribute("list");
					if(list2!=null&&list2.size()>0){
						list.addAll(list2);
					}
				}
			}
			
			IExportFile export = new ExportFileExcelImpl();
			export.export(list, new Gyl013ExcelRowMapper(), export.createDefaultFileName("未出质入库"), response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	private void setField(HttpServletRequest request, Element bodyNode, String... fields) {
		for (String fieldName : fields) {
			request.setAttribute(fieldName, bodyNode.element(fieldName).getText());
		}
	}

	private boolean commonRequest(ActionMapping mapping, HttpServletRequest request, String listName,
			Class<?> resultClassType, String... field) throws Exception, DocumentException {
		Document doc = null;
		return commonRequest(mapping, request, doc, listName, resultClassType, field);
	}

	private boolean commonRequest(ActionMapping mapping, HttpServletRequest request, Document doc, String listName,
			Class<?> resultClassType, String... field) throws Exception, DocumentException {
		String methodName = request.getParameter("method");
		Map<String, Object> body = new HashMap<String, Object>();
		ZheShangBankUtil.autoFill(body, request, field);
		
		SocketClient socket = new SocketClient(host, port);
		Map<String, Object> head = ZheShangBankUtil.getBaseHeadList(methodName, "");
		String xml = socket.send(head, body);

		xml = xml.substring(2);
		// 拼接返回值
		doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZheShangBankUtil.getRetCode(ap.element("head"))) {
			return false;
		}

		Element bodyNode = ap.element("body");
		if(!bodyNode.hasContent()){
			return false;
		}
		int totalCount = Integer.parseInt(bodyNode.element("totalCount").getText());
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPageSize",	 Integer.parseInt(bodyNode.element("currentPageSize").getText()));
		if(totalCount<=0)
			return false;
		
		Element list = bodyNode.element(listName + "List");
		List infos = list.elements(listName + "Info");
		List resultList = new ArrayList();
		if (infos != null)
			for (Iterator it = infos.iterator(); it.hasNext();) {
				Element info = (Element) it.next();
				Object bean = ZheShangBankUtil.getBean(resultClassType, info);
				resultList.add(bean);
			}
		request.setAttribute("list", resultList);
		request.setAttribute("doc", doc);
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		/*SocketClient socket = new SocketClient("101.68.90.119", 10020);
		Map<String, Object> headMap = ZheShangBankUtil.getBaseHeadList("gyl009","");
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("currentCustNo", "552545455");
		bodyMap.put("custType", "01");
		bodyMap.put("custNo", "443453121");
		bodyMap.put("lnciId", "1AA3I6GND0Q627F3000A0000693BA060");
		bodyMap.put("pageSize", "2");
		bodyMap.put("pageIndex", "1");
		bodyMap.put("businessSerialNo", UUID.randomUUID().toString().replaceAll("-",""));
		bodyMap.put("totalSize", "2");
		bodyMap.put("submitFlag", "2");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "1");
		map.put("model", "xinghao123");
		map.put("manufacturer", "");
		map.put("quantity", "1");
		map.put("mortgageUnits", "辆");
		map.put("price", "50000.00");
		map.put("engineNo", "MNPS601B001");
		map.put("chassisNo", "abcd1234");
		map.put("certificationNo", "asdasd123");
		list.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "2");
		map1.put("model", "xinghao333");
		map1.put("manufacturer", "");
		map1.put("quantity", "1");
		map1.put("mortgageUnits", "辆");
		map1.put("price", "40000.00");
		map1.put("engineNo", "MNPS623B001");
		map1.put("chassisNo", "abcd1134");
		map1.put("certificationNo", "a1dasd123");
		list.add(map1);
		bodyMap.put("morgage", list);
		String xml = socket.send(headMap, bodyMap);//返回的结果带有2位保留位
		xml = xml.substring(2);*/
		
		
		
		/*SocketClient socket = new SocketClient("101.68.90.119", 10020);
		Map<String, Object> headMap = ZheShangBankUtil.getBaseHeadList("gyl016","");
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("currentCustNo", "552545455");
		bodyMap.put("custType", "01");
		bodyMap.put("custNo", "443453121");
		bodyMap.put("pledgeContractCode", "长久测试高质20160128");
		bodyMap.put("pageSize", "2");
		bodyMap.put("pageIndex", "1");
		bodyMap.put("businessSerialNo", UUID.randomUUID().toString().replaceAll("-",""));
		bodyMap.put("totalSize", "2");
		bodyMap.put("warehouseAddress", "嘻嘻哈哈哈12213");
		bodyMap.put("submitFlag", "1");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("morgageNo", "");
		map.put("name", "1");
		map.put("model", "xinghao123");
		map.put("manufacturer", "");
		map.put("quantity", "1");
		map.put("mortgageUnits", "辆");
		map.put("price", "50000.00");
		map.put("engineNo", "MNPS601B001");
		map.put("chassisNo", "abcd1234");
		map.put("certificationNo", "asdasd123");
		map.put("memo", "没有备注");
		list.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("morgageNo", "");
		map1.put("name", "2");
		map1.put("model", "xinghao333");
		map1.put("manufacturer", "");
		map1.put("quantity", "1");
		map1.put("mortgageUnits", "辆");
		map1.put("price", "40000.00");
		map1.put("engineNo", "MNPS623B001");
		map1.put("chassisNo", "abcd1134");
		map1.put("certificationNo", "a1dasd123");
		map1.put("memo", "没有备注");
		list.add(map1);
		bodyMap.put("morgage", list);
		String xml = socket.send(headMap, bodyMap);//返回的结果带有2位保留位
		xml = xml.substring(2);*/
		
		
		
		
		SocketClient socket = new SocketClient("101.68.90.119", 10020);
		Map<String, Object> headMap = ZheShangBankUtil.getBaseHeadList("gyl020","");
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("currentCustNo", "552545455");
		bodyMap.put("custType", "01");
		bodyMap.put("custNo", "443453121");
		bodyMap.put("pledgeContractCode", "长久测试高质20160128");
		bodyMap.put("pageSize", "2");
		bodyMap.put("pageIndex", "1");
		bodyMap.put("businessSerialNo", UUID.randomUUID().toString().replaceAll("-",""));
		bodyMap.put("totalSize", "2");
		bodyMap.put("submitFlag", "1");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chassisNo", "LFV5A24G33333333");
		map.put("deliveryQuantity", "0.00");
		list.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("chassisNo", "LFV5A24G22222222");
		map1.put("deliveryQuantity", "0.00");
		list.add(map1);
		bodyMap.put("morgage", list);
		String xml = socket.send(headMap, bodyMap);//返回的结果带有2位保留位
		xml = xml.substring(2);
		
		
		
		
		
		
		
		/*Document doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZheShangBankUtil.getRetCode(ap.element("head"))) {
			//return false;
		}

		Element bodyNode = ap.element("body");

		
		Element list = bodyNode.element("custList");
		List infos = list.elements("custInfo");
		List resultList = new ArrayList();
		if (infos != null)
			for (Iterator it = infos.iterator(); it.hasNext();) {
				Element info = (Element) it.next();
				Object bean = ZheShangBankUtil.getBean(Gyl001.class, info);
				resultList.add(bean);
			}
		for (Object object : resultList) {
			Gyl001 gyl001 = (Gyl001) object;
			System.out.println(gyl001.getCustNo()+"-"+gyl001.getPledgeName());
		}*/
		
	}
	
	private void setOptions(HttpServletRequest request){
		int currRole = getCurrRole(request);
		request.setAttribute("currRole", currRole);
		request.setAttribute("custList", OptionUtil.getCust(request,currRole));
	}

}
