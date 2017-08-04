package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.constants.ClientTypeConstants;

@SuppressWarnings("serial")
public class ClientTypeTag extends TagSupport {

	private Object idtype;

	public int doStartTag() throws JspException {
		
		int id = new Integer(idtype.toString());

		String name="";
		
		ClientTypeConstants[] list = ClientTypeConstants.values();
		
		for (ClientTypeConstants ct : list) {
			if(ct.getCode() == id){
				name=ct.getName();
				break;
			}
				
		}
		
		try {
			pageContext.getOut().write(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 6;
	}

	
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}

}
