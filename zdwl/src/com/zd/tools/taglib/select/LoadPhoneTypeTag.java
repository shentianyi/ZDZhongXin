package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.rbac.constants.PhoneTypeConstants;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class LoadPhoneTypeTag extends TagSupport {

	private Object idtype;

	public int doStartTag() throws JspException {
		
		PhoneTypeConstants[] values = PhoneTypeConstants.values();
		String typeinfo = new String(idtype.toString());
		StringBuffer sb = new StringBuffer("");
		
		if (null != typeinfo) {
			String[] idtypes = typeinfo.split(",");
			for (String type : idtypes) {
				for (PhoneTypeConstants phoneTypeConstants : values) {
					if (StringUtil.isNotEmpty(type)) {
						int typeNum = Integer.parseInt(type);
						if (typeNum == phoneTypeConstants.getCode()) {
							sb.append(phoneTypeConstants.getName() + " , ");
						}
					}
				}
			}
		}
		try {
			if(StringUtil.isNotEmpty(sb.toString())){
				pageContext.getOut().write(sb.toString().substring(0,sb.length() - 2));
			}
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
