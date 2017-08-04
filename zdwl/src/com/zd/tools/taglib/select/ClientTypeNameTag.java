package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.constants.ClientTypeConstants;

@SuppressWarnings("serial")
public class ClientTypeNameTag extends TagSupport {

	private Object clientType;

	public int doStartTag() throws JspException {
		
		int clientType = (Integer)this.clientType;
		String name = ClientTypeConstants.getName(clientType);
		try{
			pageContext.getOut().write(name);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}

	public Object getClientType() {
		return clientType;
	}

	public void setClientType(Object clientType) throws JspException {
		this.clientType = (Integer)ExpressionEvaluatorManager.evaluate("clientType", clientType.toString(), Integer.class, this, pageContext); 
	}
}
