package com.zd.csms.bank.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

import com.zd.csms.bank.dock.ZheShangBankUtil;

/**
 * @author licheng
 *
 */
public class SocketClient {
	private String host;
	private int port;// 端口
	private int timeout=10000;// 超时时间，默认10秒

	public SocketClient(String host, int port, int timeout) {
		super();
		this.host = host;
		this.port = port;
		this.timeout = timeout;
	}

	public SocketClient(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	/**
	 * 发送报文并返回String类型的XML格式返回报文
	 * @param headMap
	 * @param bodyMap
	 * @return
	 * @throws Exception
	 */
	public String send(Map<String, Object> headMap, Map<String, Object> bodyMap) throws Exception{
		byte[] msg = ZheShangBankUtil.sendMsg(headMap, bodyMap);
		byte[] returnmsg = null;
		Socket socket = new Socket(host, port);
		socket.setReuseAddress(true);
		socket.setSoTimeout(this.timeout);
		
		try {
			//发送报文
			OutputStream out = socket.getOutputStream();
			out.write(msg);
			out.flush();
			//发送结束
			byte[] len = new byte[10];
			//接受报文
			InputStream input = socket.getInputStream();
			
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
		}finally{
			if(socket.isClosed()){
				socket.close();
			}

		}
		String result = new String(returnmsg, ZheShangBankUtil.ENCODING);
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
		String length = new String(Arrays.copyOfRange(len, 0, 10), ZheShangBankUtil.ENCODING);
		
		return Integer.parseInt(length.toString());
	}

}
