package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.constants.StateConstants;

@SuppressWarnings("serial")
public class StateNameTag extends TagSupport {

	private Object state;

	public int doStartTag() throws JspException {
		
		int state = (Integer)this.state;
		
		String name = "";
		
		for(StateConstants StateConstant: StateConstants.values()){
			if(state==StateConstant.getCode()){
				name = StateConstant.getName();
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
