package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;

@SuppressWarnings("serial")
public class UserTag extends TagSupport {

	private Object userid;

	public int doStartTag() throws JspException {
		
		int id = (Integer)this.userid;
		String name = "";
		try {
			UserService us = new UserService();
			UserVO user = us.loadUserById(id);
			if(user!=null){
				name = user.getUserName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			pageContext.getOut().write(name);
		}catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		return 6;
	}

	public Object getUserid() {
		return userid;
	}

	public void setUserid(Object userid) throws JspException {
		this.userid = (Integer)ExpressionEvaluatorManager.evaluate("userid", userid.toString(), Integer.class, this, pageContext); 
	}
	
}
