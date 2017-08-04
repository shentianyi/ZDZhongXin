package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.service.RoleService;

@SuppressWarnings("serial")
public class NextApprovalNameTag extends TagSupport {

	private Object roleId;

	public int doStartTag() throws JspException {
		
		int roleId = (Integer) this.roleId;
		String name = "";
		
		RoleService rs = new RoleService();
		try {
			for (RoleConstants role : RoleConstants.values()) {
				if(role.getCode()==roleId){
					name=role.getName();
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


	public Object getRoleId() {
		return roleId;
	}

	public void setRoleId(Object roleId) throws JspException {
		this.roleId = (Integer)ExpressionEvaluatorManager.evaluate("roleId", roleId.toString(), Integer.class, this, pageContext);;
	}


}
