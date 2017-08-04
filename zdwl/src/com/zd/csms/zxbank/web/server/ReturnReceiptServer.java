//package com.zd.csms.zxbank.web.server;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//
//import com.zd.csms.zxbank.bean.Notice;
//import com.zd.csms.zxbank.bean.PushNoticeDetail;
//import com.zd.csms.zxbank.service.NoticeService;
//import com.zd.csms.zxbank.service.PushNoticeDetailService;
//import com.zd.csms.zxbank.util.DateUtil;
//import com.zd.csms.zxbank.util.ZXNoticeUtil;
//import com.zd.csms.zxbank.util.SqlUtil;
//import com.zd.csms.zxbank.util.ZhongXinBankUtil;
//import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
//import com.zd.csms.zxbank.web.bean.*;
//
///**
// * 通知推送回执服务 SocketServer
// * @author caizhuo
// *
// */
//public class ReturnReceiptServer implements Runnable {
//
//	private ServletContext servletContext;
//	private ServerSocket serverSocket;
//	private Socket socket;
//
//	private NoticeService ns = new NoticeService();
//	private PushNoticeDetailService pnds=new PushNoticeDetailService();
//	
//	public ReturnReceiptServer() {}
//
//	public ReturnReceiptServer(ServletContext servletContext) {
//		this.servletContext = servletContext;//从web.xml中context-param节点获取socket端口
//		String port = this.servletContext.getInitParameter("socketPort");
//		if (serverSocket == null) {
//			try {
//				this.serverSocket = new ServerSocket(Integer.parseInt(port));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void findAllList() throws Exception {
//		String xml = send();
//		xml = xml.substring(2);
//		// 拼接返回值
//		Document doc = DocumentHelper.parseText(xml);
//		Element ap = doc.getRootElement();
//		if (ZhongXinBankUtil.getRetCode(ap.element("head"))) {
//			Element bodyNode = ap.element("stream");
//			if (bodyNode.hasContent()) {
//				String ntcno = bodyNode.element("NTCNO").getText();
//				String ntctp = bodyNode.element("NTCTP").getText();
//				String branchid = bodyNode.element("BRANCHID").getText();
//				String ntcdate = bodyNode.element("NTCDATE").getText();
////				String totnum=bodyNode.element("TOTNUM").getText();
//				Element list = bodyNode.element("LST");
//				List infos = list.elements("row");
//				
//				Notice notice=new Notice();
//				
//				if(ntctp.equals(ZXNoticeUtil.RECEIVING)){//收货
//					notice.setNtctp(1);
//				}else if(ntctp.equals(ZXNoticeUtil.MOVE)){//移库
//					notice.setNtctp(2);
//				}else if(ntctp.equals(ZXNoticeUtil.REMOVEPLEDGE)){//解除质押
//					notice.setNtctp(3);
//				}else if(ntctp.equals(ZXNoticeUtil.WAREHOUSING)){//入库
//					notice.setNtctp(4);
//				}else if(ntctp.equals(ZXNoticeUtil.CHANGE_FINANCING)){//质物与融资变更
//					notice.setNtctp(5);
//				}
//				notice.setNtcno(ntcno);//通知编号
//				notice.setNtcdate(DateUtil.StringToDate(ntcdate));//通知发送时间
//				notice.setNtbranchid(branchid);//分行id
////				notice.setNttotnum(Integer.parseInt(totnum));//总记录条数
//				
//				if(ns.isNotice(notice)){
//					ns.update(notice);
//				}else{
//					//保存通知推送信息
//					notice.setNid(SqlUtil.getID(Notice.class));
//					ns.add(notice);
//					if(notice.getNtctp()==5){//质物入库与融资关系的变更通知
//						//保存通知推送明细
//						PushNoticeDetail pnd=new PushNoticeDetail();
//						if (infos != null)
//							for (Iterator it = infos.iterator(); it.hasNext();) {
//								Element info = (Element) it.next();
//								pnd.setNid(notice.getNid());
//								pnd.setPndecifcode(info.element("ECIFCODE").getText());
//								pnd.setPndoperorg(info.element("OPERORG").getText());
//								pnd.setPndvin(info.element("VIN").getText());
//								pnd.setPndloancode(info.element("LOANCODE").getText());
//								pnds.add(pnd);
//							}
//					}
//					
//				}
//				
//				//回执方法开始
//				String status = ZXNoticeUtil.returnReceipt(ntctp,ntcno,1,"");
//				if (status != null && status.equals("AAAAAAA")) {
//					System.out.println("--回执成功--");
//					//通知查询报文发送
//					if(FarQuery(ntcno,ntctp)){
//						notice.setNtfailflag(2);
//						ns.update(notice);
//					}else{
//						notice.setNtfailflag(1);
//						ns.update(notice);
//					}
//				}else{
//					System.out.println("--回执失败--");
//					notice.setNtfailflag(0);
//					ns.update(notice);
//				}
//				
//				//打印
////				System.out.println("NTCTP:" + ntctp);
////				System.out.println("NTCNO:" + ntcno);
////				System.out.println("BRANCHID:" + branchid);
////				System.out.println("NTCDATE:" + ntcdate);
//////				System.out.println("TOTNUM:" + totnum);
////				System.out.println("---集合列表---");
////				if (infos != null)
////					for (Iterator it = infos.iterator(); it.hasNext();) {
////						Element info = (Element) it.next();
////						System.out.println(info.element("ECIFCODE").getText());
////						System.out.println(info.element("OPERORG").getText());
////						System.out.println(info.element("VIN").getText());
////						System.out.println(info.element("LOANCODE").getText());
////					}
//			}
//		}
//	}
//
//	/**
//	 * 发送报文并返回String类型的XML格式返回报文
//	 * @param headMap
//	 * @param bodyMap
//	 * @return
//	 * @throws Exception
//	 */
//	private String send() throws Exception {
//		byte[] returnmsg = null;
//		try {
//			byte[] len = new byte[10];
//			//接受报文
//			System.out.println("监听到地址为：" + socket.getInetAddress() + "的客户端连接");
//			InputStream input = socket.getInputStream();
//			if (socket.isConnected()) {
//				System.out.println("建立连接成功，连接状态：" + socket.isConnected());
//			}
//			while (socket.isConnected()) {
//				if (input.read(len) == 10) {//先接收报头 获取长度 在获取报体
//					returnmsg = new byte[calcLength(len)];
//					int msgIndex = 0;
//					int msgTotalLen = returnmsg.length;
//					int readLen = 0;
//					while (msgIndex < msgTotalLen) {
//						readLen = input.read(returnmsg, msgIndex, msgTotalLen - msgIndex);
//						if (readLen > 0) {
//							msgIndex = msgIndex + readLen;
//						} else {
//							break;
//						}
//					}
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String result = new String(returnmsg, ZhongXinBankUtil.ENCODING);
//		System.out.println("接收报文：" + result);
//		return result;
//	}
//
//	/**
//	 * 计算出返回报文的总字节数
//	 * @param len
//	 * @return
//	 * @throws UnsupportedEncodingException 
//	 */
//	private int calcLength(byte[] len) throws UnsupportedEncodingException {
//		String length = new String(Arrays.copyOfRange(len, 0, 10), ZhongXinBankUtil.ENCODING);
//		return Integer.parseInt(length.toString());
//	}
//
//	@Override
//	public void run() {
//		while (true) {//线程未中断执行循环
//			try {
//				System.out.println(this.servletContext.getInitParameter("socketPort")+"端口服务已启动。。。");
//				socket = serverSocket.accept();
//				try {
//					findAllList();
//				} catch (Exception e) {
//					e.printStackTrace();
//					System.out.println("没找到");
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public boolean FarQuery(String ntcno,String ntctp)throws Exception{
//		//回执成功进行远程通知信息同步
//		if (ntcno != null) {
//			Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
//			Map<String, Object> body = new HashMap<String, Object>();
//			if (ntctp.equals(ZXNoticeUtil.RECEIVING)) {
//				body.put("action", "DLCDRGNQ");
//				body.put("userName", "");
//				body.put("rvccmdntcNo", ntcno);
//				return ZXBankInterfaceAction.NoticeSynchronous(1, body, head, "cmdinf", ReceivingNoticeFar.class,
//						ReceivingDetailFar.class,"method");
//			} else if (ntctp.equals(ZXNoticeUtil.MOVE)) {
//				body.put("action", "DLCDTWNQ");
//				body.put("userName", "");
//				body.put("mwntcNo", ntcno);
//				return ZXBankInterfaceAction.NoticeSynchronous(2, body, head, "", MoveNoticeFar.class,
//						MoveDetailFar.class,"method");
//			} else if (ntctp.equals(ZXNoticeUtil.REMOVEPLEDGE)) {
//				body.put("action", "DLCDUINQ");
//				body.put("userName", "");
//				body.put("rlsmgntcNo", ntcno);
//				return ZXBankInterfaceAction.NoticeSynchronous(3, body, head, "", RemovePledgeFar.class,
//						RemovePledgeDetailFar.class,"method");
//			}
//		}
//		return false;
//	}
//}
