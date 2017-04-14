package com.zd.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zd.core.Constants;

/**
 * 服务路径监听器
 * 用于在服务启动时将服务运行路径加载到系统属性中
 * */
public class ContextPathListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.setProperty(Constants.ROOT_WEBAPP.getCode(), arg0.getServletContext().getRealPath("/"));
	}

}
