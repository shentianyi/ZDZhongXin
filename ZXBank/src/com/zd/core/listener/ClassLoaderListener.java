package com.zd.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zd.core.BeanManager;

/**
 * 对象管理监听器
 * 用于在服务启动时将Spring的ApplicationContext加载到BeanManager中
 * */
public class ClassLoaderListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		WebApplicationContext factory = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		BeanManager.setFactory(factory);
	}

}
