package com.zd.core;

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
 * ajax类 action支撑，action类的基础类
 * 修改方法返回结果为String，并将返回结果设置到response中
 * */
public class AjaxActionSuppor extends DispatchAction {

	
	private static Logger log = Logger.getLogger(AjaxActionSuppor.class);

	protected ActionForward dispatchMethod(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String name) throws Exception {
		
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
		String result = null;
		try {
			Object[] args = { mapping, form, request, response };
			result = (String) method.invoke(this, args);

			response.getWriter().print(result);
			
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
			if (t instanceof Exception)
				throw (Exception) t;

			response.sendError(500, message);

			return null;
		}
		
		return null;
	}
	
}