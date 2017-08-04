package com.zd.csms.zxbank.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zd.core.SystemProperty;
import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.service.NoticeService;
import com.zd.csms.zxbank.services.PushNoticeImpl;

/**
 * 车辆通知工具
 * 
 * @author caizhuo
 * 
 */
public class ZXNoticeUtil {
	public final static String RECEIVING = "DLCDRGNQ";// 收货通知类型DLCDRGNQ

	public final static String MOVE = "DLCDTWNQ";// 移库通知类型DLCDTWNQ

	public final static String REMOVEPLEDGE = "DLCDUINQ";// 解除质押通知类型DLCDUINQ

	public final static String WAREHOUSING = "STXTLGCS";// 入库通知类型

	public final static String CHANGE_FINANCING = "STXTCIWH";// 质物与融资关系变更通知类型
	
	public final static String userName;
	
	private static Log log = LogFactory.getLog(NoticeService.class);
	static{
		userName=SystemProperty.getPropertyValue("bankdock.properties","zx.userName");
	}
	/**
	 * 回执报文拼接
	 * 
	 * @param ntctp
	 *            通知类型
	 * @param ntcno
	 *            通知编号
	 * @param whetappr
	 *            是否确实业务处理已完成
	 * @param loncpid
	 *            借款企业id 可空
	 * @return
	 */
	public static String returnReceipt(String ntctp, String ntcno, int whetappr, String loncpid) {
		Map<String, Object> body = new HashMap<String, Object>();
		// 回执的内容head, body
		body.put("action", "DLCDRRSM");// 对应请求代码 DLCDRRSM 交易吗CPCDRRSM
		body.put("userName",userName);// --登录名
		body.put("ntcTp", ntctp);// 通知书类型 --必输
		body.put("rlsmgntcNo", ntcno);// 通知书编号--必输
		body.put("whetappr", whetappr);// 是否确认-- 必输 0否 1是
		body.put("loncpId", loncpid);// 借款企业ID
		body.put("rrDate", DateUtil.getStringFormatByDate(new Date(), "yyyyMMdd"));// 回执日期
		body.put("rrsnderNo", "");// 回执发送人编号
		body.put("rrsnderNm", "");// 回执发送人名称
		body.put("rrcontent", "");// 回执内容
		return ZhongXinBankUtil.parseXml(body);
	}
	// 回执通知回执、质物入库响应报文解析
	public static Map<String, Object> resultParse(String xml) throws DocumentException {
		log.info("回执后接受的报文["+xml+"]");
		String status = null;
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element bodyNode = doc.getRootElement();
		if (!bodyNode.hasContent()) {
			return null;
		}
		status = bodyNode.element("status").getText();
		String statusText = bodyNode.element("statusText").getText();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", status);
		if(status.equals("AAAAAAA")){
			map.put("message","读取成功");
		}else{
			map.put("message",statusText);
		}
		log.info("---交易信息：[" + statusText + "]---交易状态码：[" + status+"]");
		return map;
	}
	
		/**
		 * 通知回执方法。
		 * @param notice
		 * @param whetappr
		 * @throws Exception
		 */
		public static Map<String, Object> retReceipt(String ntctp, String ntcno, int whetappr, String loncpid) throws Exception {
//			NoticeService ns = new NoticeService();
			Log log = LogFactory.getLog(PushNoticeImpl.class);
			String url = SystemProperty.getPropertyValue("bankdock.properties", "zx.url");
			// 1. 回执请求
			String returnXml =returnReceipt(ntctp,ntcno,whetappr,loncpid);
			if(ntctp.equals(MOVE)){
				log.info("移库回执报文:"+returnXml);
			}else if(ntctp.equals(REMOVEPLEDGE)){
				log.info("解除质押回执报文:"+returnXml);
			}
			return resultParse(ZhongXinBankUtil.sendPost(url, returnXml));
		}
		
		/**
		 * 接收的通知推送信息的加密报文解析返回加密的字符窜内容。
		 * @param xml
		 * @return
		 * @throws DocumentException
		 */
		public static String pushNoticeParse(String xml) throws DocumentException {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			if (!root.hasContent()) {
				return null;
			}
			String MD5= root.element("MD5").getText();
			String MSG = root.element("MSG").getText();//加密字段
			return MSG;
		}
		
		/**
		 * 通知推送报文的校验
		 * @param xml
		 * @return
		 * @throws DocumentException
		 */
		public static String backhaulxml(String xml) throws DocumentException {
			log.info("接收到通知推送报文["+xml+"]");
			//通知推送报文校验
			Document doc = DocumentHelper.parseText(xml);
			Element bodyNode = doc.getRootElement();
			if (!bodyNode.hasContent()) {
				return "报文格式错误";
			}
			
			Element ntp=bodyNode.element("NTCTP");
			if(ntp==null){
				return "缺少字段NTCTP";
			}else{
				String ntctp=ntp.getText();
				if(ntctp==null||"".equals(ntctp)){
					return "字段NTCTP内容不能为空";
				}
			}
			Element ntd=bodyNode.element("NTCDATE");
			if(ntd==null){
				return "缺少字段NTCDATE";
			}else{
				String ntcDate=ntd.getText();
				if("".equals(ntcDate)||ntcDate==null){
					return "字段NTCDATE内容不能为空";
				}
			}
			return "success";
		}

}
