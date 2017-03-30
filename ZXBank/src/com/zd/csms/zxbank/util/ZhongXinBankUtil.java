package com.zd.csms.zxbank.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

/**
 * 中信银行对接 工具类
 *
 */
public class ZhongXinBankUtil {
	public final static String ENCODING = "GBK";
	/**
	 * 字段允许为任何可见字符（包括数字和中文），n在xml中表示有效数据的最大总长度，
	 * 如果有效数据长度不足n位按实际位数；在格式化字符串中表示固定的长度， 如果有效数据长度不足n位，则有效数据左对齐，右边用空格补足至n位；
	 */
	public final static int DATATYPE_C = 1;

	/**
	 * 字段内容只允许为0-9数字，n在xml中表示有效数据的最大总长度， 如果有效数据长度不足n位按实际位数；在格式化字符串中表示固定的长度，
	 * 如果有效数据长度不足n位，则有效数据左对齐，右边用空格补足至n位
	 */
	public final static int DATATYPE_N = 2;

	/**
	 * 基本上与nm类型类似，但字段内容可以允许为负号、0-9数字和小数点，
	 * 其中m表示有效数据的最大总长度（对于格式化字符串中表示固定长度），n表示小数点后位数，
	 * 该类型通常用来表示发生额、余额等金额类字段，如n15.2表示总共15位长度的字段，其中包含小数点后两位
	 */
	public final static int DATATYPE_NMN = 3;
	
	/**
	 * 报文请求头部的长度格式 :报头总共有10个字节，表示报体的长度，如果报体的长度不足10位则左边用”0”补足
	 */
	public static final DecimalFormat dfmt = new DecimalFormat("0000000000");//头部表达长度的格式
	
	public static final byte[] BODY_HEAD=new byte[]{48,48};// 报体为2个字节（00）+数据报文，前一个字节表示加密标志（0：不加密，1：加密），后一个字节为保留位（用‘0’表示）
	
	

	public static String parseXml(Map<String, Object> headMap, Map<String, Object> bodyMap) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("ap");
		Element head = root.addElement("head");
		Element body = root.addElement("body");

		if (headMap != null) {
			for (String key : headMap.keySet()) {
				if (headMap.get(key) instanceof List) {
					Element temp = head.addElement(key + "List");
					List<Map<String, Object>> list = (List<Map<String, Object>>) headMap.get(key);
					for (Map<String, Object> map : list) {
						Element temp1 = temp.addElement(key + "Info");
						for (String key1 : map.keySet()) {
							Element tempChildren = temp1.addElement(key1);
							tempChildren.setText(map.get(key1).toString());
						}
					}
				} else {
					Element temp = head.addElement(key);
					temp.setText(headMap.get(key).toString());
				}
			}
		}

		if (bodyMap != null) {
			for (String key : bodyMap.keySet()) {
				if (bodyMap.get(key) instanceof List) {
					Element temp = body.addElement(key + "List");
					List<Map<String, Object>> list = (List<Map<String, Object>>) bodyMap.get(key);
					for (Map<String, Object> map : list) {
						Element temp1 = temp.addElement(key + "Info");
						for (String key1 : map.keySet()) {
							Element tempChildren = temp1.addElement(key1);
							tempChildren.setText(map.get(key1).toString());
						}
					}
				} else {
					Element temp = body.addElement(key);
					temp.setText(bodyMap.get(key).toString());
				}
			}
		}
		StringBuffer str = new StringBuffer(document.asXML());
		str.delete(0, str.indexOf(">") + 2);

		return str.toString();
	}

	/**
	 * 拼接报文体，转成符合要求的字节
	 * 
	 * @param headMap
	 * @param bodyMap
	 * @return
	 * @throws Exception
	 */
	public static byte[] sendMsg(Map<String, Object> headMap, Map<String, Object> bodyMap) throws Exception {
		String xml = parseXml(headMap, bodyMap);
		byte[] sendMsg = xml.getBytes(ZhongXinBankUtil.ENCODING);
		byte[] head = dfmt.format(sendMsg.length+BODY_HEAD.length).getBytes(ZhongXinBankUtil.ENCODING);
		
		byte[] send = new byte[sendMsg.length + head.length + BODY_HEAD.length];
		System.arraycopy(head, 0, send, 0, head.length);
		System.arraycopy(BODY_HEAD, 0, send, head.length, BODY_HEAD.length);
		System.arraycopy(sendMsg, 0, send, head.length + BODY_HEAD.length, sendMsg.length);
		System.out.println("发送报文："+new String(send,ZhongXinBankUtil.ENCODING));
		return send;
	}

	/**
	 * 获取标准头部
	 * 
	 * @param trCode
	 * @param reqNo
	 * @return
	 */
	public static Map<String, Object> getBaseHeadList(String trCode, String reqNo) {
		Map<String, Object> head = new HashMap<String, Object>();
		Date nowDate = new Date();
		head.put("tr_code", trCode);
		head.put("cms_corp_no", "");
		head.put("user_no", "");
		head.put("org_code", "");
		head.put("serial_no", "");
		head.put("req_no", reqNo);
		head.put("tr_acdt", DateUtil.getStringFormatByDate(nowDate, "yyyyMMdd"));
		head.put("tr_time", DateUtil.getStringFormatByDate(nowDate, "HHmmss"));
		head.put("channel", "5");
		head.put("sign", "");
		head.put("file_flag", "0");
		head.put("reserved", "");
		
		/*head.put("stdmsgtype",);
		head.put("std400trcd",);
		head.put("stdprocode",);
		head.put("std400aqid",);
		head.put("stdmercno",);
		head.put("stdlocdate",DateUtil.getStringFormatByDate(nowDate, "yyyyMMdd"));
		head.put("stdloctime",DateUtil.getStringFormatByDate(nowDate, "HHmmss"));
		head.put("stdautotln",);
		head.put("cpytrnflow",);
		head.put("stdmqmsgid",);*/

		
		return head;
	}

	/**
	 * 分页时获取body
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getCacheBody(HttpServletRequest request) {
		String pageIndex = request.getParameter("pageIndex");
		Map<String, Object> body = null;
		if (StringUtil.isNotEmpty(pageIndex)) {
			String methodName = request.getParameter("method");
			body = (Map<String, Object>) request.getSession().getAttribute("bankInterface_" + methodName);
			if (body != null) {
				if (body.containsKey("pageIndex")) {
					body.put("pageIndex", pageIndex);
				}
			}
			request.setAttribute("pageIndex", Integer.parseInt(pageIndex));
		}
		return body;
	}

	/**
	 * 填充字段到Map集合
	 * 
	 * @param map
	 * @param request
	 * @param fieldNames
	 */
	public static void autoFill(Map<String, Object> map, HttpServletRequest request, String... fieldNames) {
		if (fieldNames != null) {
			for (String field : fieldNames) {
				String value = request.getParameter(field);
				if(value == null){
					value = (String) request.getAttribute(field);
				}
				if(value == null)
					value="";
				if(value!=null&&(field.toLowerCase().contains("startdate") || field.toLowerCase().contains("enddate"))){
					value = value.replaceAll("-", "");
				}
				map.put(field, value);
			}
		}
	}

	public static <T> T getBean(Class<T> type, Element ele) throws Exception {
		T t = type.newInstance();
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			StringBuffer sb = new StringBuffer();       
	        sb.append("set");       
	        sb.append(fieldName.substring(0, 1).toUpperCase());       
	        sb.append(fieldName.substring(1));
			Method setMethod = type.getMethod(sb.toString(), String.class);
			Element filedEle = ele.element(field.getName());
			if(filedEle!=null){
				setMethod.invoke(t, filedEle.getText());
			}
		}
		return t;

	}
	
	/**
	 * 返回是否正确的请求报文
	 * @param head
	 * @return
	 */
	public static boolean getRetCode(Element head){
		return head.element("ret_code").getText().equals("000000");
	}

}
