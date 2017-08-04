package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.service.BrandService;

@SuppressWarnings("serial")
public class BrandTag extends TagSupport {

	private Object bid;

	public int doStartTag() throws JspException {
		
		int id = (Integer)this.bid;
		
		String name = "";
		
		try {
			
			BrandService us = new BrandService();
			BrandVO brand = us.loadBrandById(id);
			if(brand != null){
				name = brand.getName();
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
