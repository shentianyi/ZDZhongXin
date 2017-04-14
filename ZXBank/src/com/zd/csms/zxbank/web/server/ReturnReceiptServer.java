package com.zd.csms.zxbank.web.server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zd.csms.util.DateUtil;
import com.zd.csms.zxbank.util.ReturnReceiptUtil;
import com.zd.csms.zxbank.util.SocketClient;
import com.zd.csms.zxbank.util.ZhongXinBankUtil;
import com.zd.csms.zxbank.web.action.ZXBankInterfaceAction;
import com.zd.csms.zxbank.web.bean.*;
/**
 * 通知推送回执服务 SocketServer
 * @author caizhuo
 *
 */
public class ReturnReceiptServer implements Runnable {
	
	private ServletContext servletContext;
	private ServerSocket serverSocket;
	private Socket socket;

	public ReturnReceiptServer(){
	}
	
	public ReturnReceiptServer(ServletContext servletContext){
	this.servletContext=servletContext;//从web.xml中context-param节点获取socket端口
	String port=this.servletContext.getInitParameter("socketPort");
	if(serverSocket==null){
			try{
				this.serverSocket=new ServerSocket(Integer.parseInt(port));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void findAllList() throws Exception{
		String xml = send();
		xml = xml.substring(2);
		// 拼接返回值
		Document doc = DocumentHelper.parseText(xml);
		Element ap = doc.getRootElement();
		if (ZhongXinBankUtil.getRetCode(ap.element("head"))) {
			Element bodyNode = ap.element("stream");
			if(bodyNode.hasContent()){
				String ntctp=bodyNode.element("NTCTP").getText();
				//控制台打印
				String ntcno=bodyNode.element("NTCNO").getText();
				String lonentid=bodyNode.element("LONENTID").getText();
				String branchid=bodyNode.element("BRANCHID").getText();
				String ntcdate=bodyNode.element("NTCDATE").getText();
				System.out.println("NTCTP:"+ntctp);
				System.out.println("LONENTID:"+lonentid);
				System.out.println("NTCNO:"+ntcno);
				System.out.println("BRANCHID:"+branchid);
				System.out.println("NTCDATE:"+ntcdate);
				System.out.println("---集合列表---");
				Element list = bodyNode.element("LST");
				List infos = list.elements("row");
				List resultList = new ArrayList();
				if (infos != null)
					for (Iterator it = infos.iterator(); it.hasNext();) {
						Element info = (Element) it.next();
						System.out.println(info.element("ECIFCODE").getText());
						System.out.println(info.element("OPERORG").getText());
					}
				//回执方法开始
				String status=ReturnReceiptUtil.returnReceipt(ntctp, ntcno);
				if(status!=null&&status.equals("AAAAAAA")){
					System.out.println("--回执成功--");
					//回执成功进行远程通知信息同步
					System.out.println("ntcno"+ntcno);
					if(ntcno!=null){
						Map<String, Object> head = ZhongXinBankUtil.getBaseHeadList();
						Map<String, Object> body = new HashMap<String, Object>();
						if(ntctp.equals("DLCDRGNQ")){
							body.put("action", "DLCDRGNQ");
							body.put("userName", "");
							body.put("rvccmdntcNo", ntcno);
							ZXBankInterfaceAction.NoticeSynchronous(1,body, head, "cmdinf",ReceivingNotice.class,ReceivingDetail.class);
						}else if(ntctp.equals("DLCDTWNQ")){
							body.put("action", "DLCDTWNQ");
							body.put("userName", "");
							body.put("mwntcNo", ntcno);
							ZXBankInterfaceAction.NoticeSynchronous(2,body, head, "",MoveNotice.class,MoveDetail.class);
						}else if(ntctp.equals("DLCDUINQ")){
							body.put("action", "DLCDUINQ");
							body.put("userName", "");
							body.put("rlsmgntcNo", ntcno);
							ZXBankInterfaceAction.NoticeSynchronous(3,body, head, "", RemovePledge.class,RemovePledgeDetail.class);
						}
					}
					
				}
			}
		}
	}
	
	
	
	
	
	/**
	 * 发送报文并返回String类型的XML格式返回报文
	 * @param headMap
	 * @param bodyMap
	 * @return
	 * @throws Exception
	 */
	private String send() throws Exception{
		byte[] returnmsg = null;
		try {
			byte[] len = new byte[10];
			//接受报文
			System.out.println("监听到地址为："+socket.getInetAddress()+"的客户端连接");
			InputStream input = socket.getInputStream();
			if(socket.isConnected()){
				System.out.println("建立连接成功，连接状态："+socket.isConnected());
			}
			while (socket.isConnected()) {
				if (input.read(len) == 10) {//先接收报头 获取长度 在获取报体
					returnmsg = new byte[calcLength(len)];
					int msgIndex = 0;
					int msgTotalLen = returnmsg.length;
					int readLen = 0;
					while(msgIndex<msgTotalLen){
						readLen = input.read(returnmsg,msgIndex,msgTotalLen-msgIndex);
						if(readLen>0){
							msgIndex = msgIndex+readLen;
						}else{
							break;
						}
					}
					break; 
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = new String(returnmsg, ZhongXinBankUtil.ENCODING);
		System.out.println("接收报文："+result);
		return result;
	}

	/**
	 * 计算出返回报文的总字节数
	 * @param len
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private int calcLength (byte[] len) throws UnsupportedEncodingException{
		String length = new String(Arrays.copyOfRange(len, 0, 10), ZhongXinBankUtil.ENCODING);
		return Integer.parseInt(length.toString());
	}

	@Override
	public void run() {
		while(true){//线程未中断执行循环
			try{
				socket = serverSocket.accept();
				try {
					findAllList();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("没找到");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}	
	}

}
