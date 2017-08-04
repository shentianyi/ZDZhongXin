package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.base.code.constants.BusinessCodeConstants;

@SuppressWarnings("serial")
public class WorkTypeTag extends TagSupport {

	private Object workType;

	public int doStartTag() throws JspException {
		
		int workType = (Integer)this.workType;
		
		String name = "";
		
		for(BusinessCodeConstants businessCode: BusinessCodeConstants.values()){
			if(workType==businessCode.getCode()){
				name = businessCode.getName();
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

	public Object getWorkType() {
		return workType;
	}

	public void setWorkType(Object workType) throws JspException {
		this.workType = (Integer)ExpressionEvaluatorManager.evaluate("workType", workType.toString(), Integer.class, this, pageContext); 
	}

}
