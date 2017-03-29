package com.zd.csms.zxbank.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.ZXBankDAOFactory;
import com.zd.csms.zxbank.dao.ZXIBankDockDao;
import com.zd.csms.zxbank.dao.oracle.WareHouseDao;
import com.zd.csms.zxbank.dao.oracle.ZXBankDockDao;


public class ZXBankInterfaceAction extends ActionSupport{
	/*private static final String host;
	private static final int port;
	private static final String currentCustNo;
	private static final String custType;*/
	private static final int pageSize=10;
	private static final int pageMaxNum = 50;
	
	/*private static final int[] roles = new int[]{
		RoleConstants.SUPERVISORY.getCode()ZXBankDockDao
	};*/
	//相关用户登录的缓存信息
	/*private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, roles);
	}*/

	/*static {
		host = SystemProperty.getPropertyValue("bankdock.properties", "zs.host");
		port = Integer.parseInt(SystemProperty.getPropertyValue("bankdock.properties", "zs.port"));
		currentCustNo = SystemProperty.getPropertyValue("bankdock.properties", "zs.currentCustNo");
		custType = SystemProperty.getPropertyValue("bankdock.properties", "zs.custType");
	}*/
	
	private ZXIBankDockDao dao = ZXBankDAOFactory.getBankDockDAO();
	/*private ZXIBankDockDao d=new ZXBankDockDao(null);*/
	public ActionForward customer(ActionMapping mapping,ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response)  {
		List<Customer> list=dao.findAllList();
		request.setAttribute("list", list);
		return mapping.findForward("cuslist");
	}
	
	
	private WareHouseDao da=ZXBankDAOFactory.getWareHouseDAO();
	public ActionForward warehouse(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		System.out.println("---ZXBankInterfaceAction");
		List<Warehouse> list=da.findAllList();
		request.setAttribute("list", list);
		System.out.println("------"+list.size());
		return mapping.findForward("warehouse");
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
	/*public ActionForward customer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
		System.out.println("嗨这是中信银行的客户查询 ... ...");
		try {
			IThumbPageTools tools = ToolsFactory.getThumbPageTools("Customer", request);
			ThumbPageToolsForOracle oracleTools = (ThumbPageToolsForOracle) tools;
			oracleTools.setPageSize(10);
			request.setAttribute("pageSize", oracleTools.getThumbPageVO().getPageSize()+"");
			request.setAttribute("pageIndex", oracleTools.getThumbPageVO().getCurrentPageNum()+"");
			
			request.setAttribute("currentCustNo", currentCustNo);
			request.setAttribute("custType", custType);
			
			boolean flag = commonRequest(mapping, request, "cust", Customer.class,
					new String[]{"currentCustNo", "custType", "custOrganizationcode","pageSize","pageIndex"});
			
			if(flag){
				Integer totalCount = (Integer)request.getAttribute("totalCount");
				oracleTools.setTotalItemsNum(totalCount);
				oracleTools.setTotalPagesNum(totalCount%10==0?(totalCount/10):(totalCount/10)+1);
			}
			
			request.setAttribute("custNo", request.getParameter("custNo"));
			request.setAttribute("pledgeName", request.getParameter("pledgeName"));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mapping.findForward("gyl001");
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
	}*/
}
