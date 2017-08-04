package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.bank.contants.BankContants;
import com.zd.csms.dbsy.constants.UrgencyLevelConstants;

@SuppressWarnings("serial")
public class BankTypeTag extends TagSupport {

	private Object type;

	public int doStartTag() throws JspException {
		
		int type = Integer.parseInt(this.type.toString());
		String name="";
		BankContants[] bankTypes  = BankContants.values();
		for (BankContants bc : bankTypes) {
			if(bc.getCode()==type){
				name=bc.getName();
				break;
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


	public Object getType() {
		return type;
	}

	public void setType(Object type) throws JspException {
		this.type = (Integer)ExpressionEvaluatorManager.evaluate("type", type.toString(), Integer.class, this, pageContext);;
	}

}
