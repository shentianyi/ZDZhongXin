package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.constants.ComplaintObjConstants;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class LoadComplaintObjTag extends TagSupport {

	private Object idtype;

	public int doStartTag() throws JspException {
		
		ComplaintObjConstants[] values = ComplaintObjConstants.values();
		String type = new String(idtype.toString());
		int num = 0;
		String name = "";
		if (StringUtil.isNotEmpty(type)) {
			num = Integer.parseInt(type);
		}
		for (ComplaintObjConstants complaintObjConstants : values) {
			if (num > 0 && num == complaintObjConstants.getCode()) {
				name = complaintObjConstants.getName();
			}
		}
		
		try {
			pageContext.getOut().write(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 6;
	}

	
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}

}
