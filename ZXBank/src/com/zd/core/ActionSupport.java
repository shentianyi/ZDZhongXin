package com.zd.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


/**
 * action支撑，action类的基础类
 * 支持dispatch模式
 * */
public class ActionSupport extends DispatchAction {

	private static Logger log = Logger.getLogger(ActionSupport.class);

	protected ActionForward dispatchMethod(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String name) throws Exception {

			ActionForward forward = null;
			if (name == null)
				return unspecified(mapping, form, request, response);
			
			Method method = null;
			try {
				method = getMethod(name);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				String message = messages.getMessage("dispatch.method", mapping.getPath(), name);
				log.error(message, e);
				response.sendError(500, message);
				return null;
			}
			try {
				Object[] args = { mapping, form, request, response };
				forward = (ActionForward) method.invoke(this, args);
			} catch (ClassCastException e) {
				e.printStackTrace();
				String message = messages.getMessage("dispatch.return", mapping
						.getPath(), name);
				log.error(message, e);
				response.sendError(500, message);
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				String message = messages.getMessage("dispatch.error", mapping
						.getPath(), name);
				log.error(message, e);
				response.sendError(500, message);
				return null;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				Throwable t = e.getTargetException();
				String message = messages.getMessage("dispatch.error", mapping
						.getPath(), name);
				log.error(message, e);
				if (t instanceof Exception){
					throw (Exception) t;
				}
	
				response.sendError(500, message);
	
				return null;
			}
		
		return forward;
	}
	protected PrintWriter getWrite(HttpServletResponse response) throws IOException{
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		
		PrintWriter out = response.getWriter();
		return out;
	}
}
