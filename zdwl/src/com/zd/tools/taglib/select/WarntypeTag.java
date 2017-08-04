package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.message.contant.WarningTypeContant;

@SuppressWarnings("serial")
public class WarntypeTag extends TagSupport {

	private Object mid;

	public int doStartTag() throws JspException {
		
		int type = (Integer)this.mid;
		
		String name = "";
		
		try {
			
			if(type==WarningTypeContant.INNOTPAY.getCode()){
				name = WarningTypeContant.INNOTPAY.getName();
			}else if(type==WarningTypeContant.AGREEMENTEXPIRES30.getCode()){
				name = WarningTypeContant.AGREEMENTEXPIRES30.getName();
			}else if(type==WarningTypeContant.AGREEMENTOVERDUE.getCode()){
				name = WarningTypeContant.AGREEMENTOVERDUE.getName();
			}else if(type==WarningTypeContant.PAYDATEBEFORE30.getCode()){
				name = WarningTypeContant.PAYDATEBEFORE30.getName();
			}else if(type==WarningTypeContant.NOTAPPLYDATE.getCode()){
				name = WarningTypeContant.NOTAPPLYDATE.getName();
			}else if(type==WarningTypeContant.AGREEMENTEXPIRES7.getCode()){
				name = WarningTypeContant.AGREEMENTEXPIRES7.getName();
			}else if(type==WarningTypeContant.PAYDATEBEFORE15.getCode()){
				name = WarningTypeContant.PAYDATEBEFORE15.getName();
			}else if(type==WarningTypeContant.PAYDATEBEFORE7.getCode()){
				name = WarningTypeContant.PAYDATEBEFORE7.getName();
			}else if(type==WarningTypeContant.PAYDATEEXPIRE.getCode()){
				name = WarningTypeContant.PAYDATEEXPIRE.getName();
			}else{
				WarningTypeContant[] typeds =  WarningTypeContant.values();
				for (WarningTypeContant wt : typeds) {
					if(type == wt.getCode()){
						name = wt.getName();
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
