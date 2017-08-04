package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.message.contant.MessageTypeContant;

@SuppressWarnings("serial")
public class MsgtypeTag extends TagSupport {

	private Object mid;

	public int doStartTag() throws JspException {
		
		int type = (Integer)this.mid;
		
		String name = "";
		
		try {
			
			MessageTypeContant[] mess = MessageTypeContant.values();
			if(mess!=null&&mess.length>0){
				for (int i = 0; i < mess.length; i++) {
					if(type==mess[i].getCode()){
						name= mess[i].getName();
						break;
					}
						
				}
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

	public Object getMid() {
		return mid;
	}

	public void setMid(Object mid) throws JspException {
		this.mid = (Integer)ExpressionEvaluatorManager.evaluate("mid", mid.toString(), Integer.class, this, pageContext); 
	}
	
}
