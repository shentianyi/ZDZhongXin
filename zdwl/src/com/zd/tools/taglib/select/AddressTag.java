package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.region.service.RegionService;

@SuppressWarnings("serial")
public class AddressTag extends TagSupport {

	private Object sid;
	private Object idtype;
	
	public int doStartTag() throws JspException {
		
		int id = (Integer)this.sid;
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		
		String name = "";
		
		RegionService rs=new RegionService();
		try {
			if(type.equals("province")){
				if(rs.getNameById(id) != null){
					name = rs.getNameById(id);
				}
			}else if(type.equals("city")){
				if(rs.getNameById(id) != null){
					name = rs.getNameById(id);
				}
			}else if(type.equals("county")){
				if(rs.getNameById(id) != null){
					name = rs.getNameById(id);
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

	public Object getSid() {
		return sid;
	}

	public void setSid(Object sid) throws JspException {
		this.sid = (Integer)ExpressionEvaluatorManager.evaluate("sid", sid.toString(), Integer.class, this, pageContext); 
	}
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}
	
}
