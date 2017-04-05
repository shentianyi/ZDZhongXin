package com.zd.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zd.core.BeanManager;

/**
 * 线程管理监听器
 * 用于在服务启动时按配置启动制定的线程
 * */
public class ThreadStartListener implements ServletContextListener {
	
	private static Log log = LogFactory.getLog(ThreadStartListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		// TODO Auto-generated method stub
		String beanName = arg0.getServletContext().getInitParameter("initThreadBeanName");
		if(beanName==null){
			return;
		}
		String[] names = beanName.split(",");
		if(names==null){
			return;
		}
		
		Thread thread;
		for(int i=0; i<names.length; i++){
			if(names[i]==null || names[i].trim().equals("")){
				continue;
			}
			try{
				thread = (Thread)BeanManager.getBean(names[i]);
			}catch(ClassCastException e){
				log.error(e);
				thread=null;
			}
			if(thread==null){
				continue;
			}
			try{
				thread.start();
			}catch(Exception e){
				log.error(e);
			}
		}
	}

}
