package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.model.ResourceVO;
import com.zd.csms.rbac.service.ResourceService;

@SuppressWarnings("serial")
public class MenuNameTag extends TagSupport {

	private Object bid;

	public int doStartTag() throws JspException {
		
		int id = (Integer)this.bid;
		
		String name = "";
		
		try {
			
			ResourceService service = new ResourceService();
			ResourceVO rvo = service.loadResourceById(id);
			if(rvo != null){
				name = rvo.getResource_name();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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

	public Object getBid() {
		return bid;
	}

	public void setBid(Object bid) throws JspException {
		this.bid = (Integer)ExpressionEvaluatorManager.evaluate("bid", bid.toString(), Integer.class, this, pageContext); 
	}
	
}
