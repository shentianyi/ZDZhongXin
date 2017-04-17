package com.zd.csms.zxbank.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zd.core.SystemProperty;
import com.zd.csms.util.DateUtil;

/**
 * 通知回执工具
 * @author caizhuo
 *
 */
public class ReturnReceiptUtil {
	private static final String host;
	private static final int port;
	private static SocketClient socketclient;
	static {
		host = SystemProperty.getPropertyValue("bankdock.properties", "zx.host");
		port = Integer.parseInt(SystemProperty.getPropertyValue("bankdock.properties", "zx.port"));
	}

	public static String returnReceipt(String ntctp, String ntcno) {
		socketclient = new SocketClient(host, port);
		Map<String, Object> body = new HashMap<String, Object>();
		//回执的内容head, body
		Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
		String status = null;
		try {
			//byte[] msg = ZhongXinBankUtil.sendMsg(head, body);
			body.put("action", "DLCDRRSM");//对应请求代码
			body.put("userName", "");//--登录名
			body.put("ntcTp", ntctp);//通知书类型 --必输 
			body.put("rlsmgntcNo", ntcno);//通知书编号--必输
			body.put("whetappr", "1");//是否确认-- 必输
			body.put("loncpId", "");//借款企业ID
			body.put("rrDate", DateUtil.getStringFormatByDate(new Date(), "YYYYMMDD"));//回执日期 
			body.put("rrsnderNo", "");//回执发送人编号
			body.put("rrsnderNm", "");//回执发送人名称 
			body.put("rrcontent", "");//回执内容 

			String xml = socketclient.send(head, body);
			xml = xml.substring(2);
			// 拼接返回值
			Document doc = DocumentHelper.parseText(xml);
			Element ap = doc.getRootElement();
			if (!ZhongXinBankUtil.getRetCode(ap.element("head"))) {
				return null;
			}
			Element bodyNode = ap.element("stream");
			if (!bodyNode.hasContent()) {
				return null;
			}
			status = bodyNode.element("status").getText();
			String statusText = bodyNode.element("statusText").getText();
			System.out.println("---交易信息：" + statusText + "---交易状态码：" + status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
