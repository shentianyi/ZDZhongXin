package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.marketing.contants.PayModeContant;

@SuppressWarnings("serial")
public class PayModeTag extends TagSupport {

	private Object state;

	public int doStartTag() throws JspException {
		
		int state = (Integer)this.state;
		
		String name = "";
		
		for(PayModeContant payMode: PayModeContant.values()){
			if(state==payMode.getCode()){
				name = payMode.getName();
			}
		}

		try{
			pageContext.getOut().write(name);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}

	public Object getState() {
		return state;
	}

	public void setState(Object state) throws JspException {
		this.state = (Integer)ExpressionEvaluatorManager.evaluate("state", state.toString(), Integer.class, this, pageContext); 
	}

}
