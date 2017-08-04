package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;

@SuppressWarnings("serial")
public class SuperviseTag extends TagSupport {

	private Object sid;

	public int doStartTag() throws JspException {
		
		int id = (Integer)this.sid;
		
		String name = "";
		
		SupervisoryService sservice = new SupervisoryService();
		SupervisorBaseInfoVO svo = sservice.getBaseInfo(id);
		if(svo != null){
			name = svo.getName();
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

	public Object getSid() {
		return sid;
	}

	public void setSid(Object sid) throws JspException {
		this.sid = (Integer)ExpressionEvaluatorManager.evaluate("sid", sid.toString(), Integer.class, this, pageContext); 
	}
}
