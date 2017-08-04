package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.dbsy.constants.UrgencyLevelConstants;

@SuppressWarnings("serial")
public class LevelTag extends TagSupport {

	private Object level;

	public int doStartTag() throws JspException {
		
		int workType = (Integer)this.level;
		
		String name = "";
		
		for(UrgencyLevelConstants urgencyLevelConstants: UrgencyLevelConstants.values()){
			if(workType==urgencyLevelConstants.getCode()){
				name = urgencyLevelConstants.getName();
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

	public Object getLevel() {
		return level;
	}

	public void setLevel(Object level) throws JspException {
		this.level = (Integer)ExpressionEvaluatorManager.evaluate("level", level.toString(), Integer.class, this, pageContext); 
	}

}
