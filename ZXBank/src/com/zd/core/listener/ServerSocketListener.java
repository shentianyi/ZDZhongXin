package com.zd.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zd.csms.zxbank.web.server.ReturnReceiptServer;

/**
 *回执服务线程监听启动
 * @author caizhuo
 *
 */
public class ServerSocketListener implements ServletContextListener {
	private Thread server;
	
	public void contextDestroyed(ServletContextEvent e){
		if(server!=null&&server.isInterrupted()){
			server.interrupt();
		}
	}
	
	public void contextInitialized(ServletContextEvent e){
		ReturnReceiptServer socket = new ReturnReceiptServer(e.getServletContext());
		server = new Thread(socket);
		server.start();
	}
}

