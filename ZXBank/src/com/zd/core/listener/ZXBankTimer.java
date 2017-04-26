package com.zd.core.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jfree.util.Log;

import com.zd.core.SystemProperty;
import com.zd.csms.zxbank.bean.Customer;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.service.CustomerService;
import com.zd.csms.zxbank.service.DistribsetService;
import com.zd.csms.zxbank.service.WareHouseService;
import com.zd.csms.zxbank.util.SocketClient;
import com.zd.csms.zxbank.util.ZhongXinBankUtil;
import com.zd.csms.zxbank.web.bean.CustomerFar;
import com.zd.csms.zxbank.web.bean.WarehouseFar;

/**
 * ZXBank定时启动服务
 * 
 */
public class ZXBankTimer extends TimerTask {
	private static final String host;
	private static final int port;

	static {
		host = SystemProperty.getPropertyValue("bankdock.properties", "zx.host");
		port = Integer.parseInt(SystemProperty.getPropertyValue("bankdock.properties", "zx.port"));
	}

	private CustomerService cs = new CustomerService();
	private WareHouseService whs = new WareHouseService();

	@Override
	public void run() {
		SocketClient socket = new SocketClient(host, port);
		/**
		 * 客户信息的自动同步
		 */
		String org = SystemProperty.getPropertyValue("bankdock.properties", "zx.orgcode");
		try {
			customerAuto(socket, org);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		/**
		 * 仓库信息同步
		 */
		//是否应该从数据库查询ECIF客户号  还是直接调用上面的用户集合？
		List<String> ECIFcodes = cs.findAllByECIF();
		for (String ecifcode : ECIFcodes) {
			try {
				warehouseAuto(socket, ecifcode);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.getMessage());
			}
		}
		
		/**
		 * 监管协议同步
		 */
		
		/**
		 * 融资信息同步
		 * 今日？
		 * 不需要？直接批量数据处理
		 */
		
		/**
		 * 融资批量数据同步
		 */
		
	}


	public void customerAuto(SocketClient socket, String orgcode) throws Exception {
		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, "DLCDCMLQ", "admin", orgcode);
		String xml = socket.send(head, body);
		List list = parseXML(xml, "", CustomerFar.class);
		cs.autoUpdateCust(list);
	}
	public void warehouseAuto(SocketClient socket, String ecifcode) throws Exception {
		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
		Map<String, Object> body = new HashMap<String, Object>();
		ZhongXinBankUtil.autoFill(body, "DLCDWMLQ", "admin", ecifcode);
		String xml = socket.send(head, body);
		List list = parseXML(xml, "", WarehouseFar.class);
		whs.autoUpdateWare(list,new Warehouse(ecifcode));
	}

	public List<Object> parseXML(String xml, String listName, Class<?> clazz) throws Exception {
		List<Object> lists = new ArrayList<Object>();
		xml = xml.substring(2);
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (!ZhongXinBankUtil.getRetCode(ap.element("head"))) {
			return lists;
		}
		Element bodyNode = ap.element("stream");
		if (!bodyNode.hasContent()) {
			return lists;
		}
		String status = bodyNode.element("status").getText();//交易状态
		String statusText = bodyNode.element("statusText").getText();//交易状态信息

		if (status!=null && status.equals("AAAAAAA")) {
			Element list = bodyNode.element(listName + "lst");
			List infos = list.elements("row");
			if (infos != null)
				for (Iterator it = infos.iterator(); it.hasNext();) {
					Element info = (Element) it.next();
					Object bean = ZhongXinBankUtil.getBean(clazz, info);
					lists.add(bean);
				}
		}
		return lists;
	}

}