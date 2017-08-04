package com.zd.tools.taglib.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.service.RoleService;

@SuppressWarnings("serial")
public class RoleNameTag extends TagSupport {

	private Object clientType;
	private Object roleCode;

	public int doStartTag() throws JspException {
		
		int clientType = (Integer)this.clientType;
		int roleCode=(Integer)this.roleCode;
		String name = "";
		
		RoleService rs = new RoleService();
		try {
			UserRoleQueryVO urquery = new UserRoleQueryVO();
			if(clientType>0){
				urquery.setUser_id(clientType);
			}else if(roleCode>0){
				urquery.setRole_id(roleCode);
			}else{
				urquery.setUser_id(-1);
				urquery.setRole_id(-1);
			}
			List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
			if(urList != null && urList.size()>0){
					RoleVO rvo = rs.loadRoleById(urList.get(0).getRole_id());
					if(rvo != null){
						name = rvo.getRole_name();
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

	public Object getClientType() {
		return clientType;
	}

	public void setClientType(Object clientType) throws JspException {
		this.clientType = (Integer)ExpressionEvaluatorManager.evaluate("clientType", clientType.toString(), Integer.class, this, pageContext); 
	}

	public Object getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Object roleCode) throws JspException{
		this.roleCode = (Integer)ExpressionEvaluatorManager.evaluate("roleCode", roleCode.toString(), Integer.class, this, pageContext); ;
	}
	
}
