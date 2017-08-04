package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDockDAO;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class CustNameTag extends TagSupport {

	private Object custNo;
	private IBankDockDAO dao = BankDAOFactory.getBankDockDAO();

	public int doStartTag() throws JspException {
		String name = "";
		String custNo = (String) this.custNo;
		if(StringUtil.isNotEmpty(custNo)){
			name = dao.getcustName(custNo);
		}
		try{
		
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

	public Object getCustNo() throws JspException{
		return custNo;
	}


	public void setCustNo(Object custNo) throws JspException {
		this.custNo = (String)ExpressionEvaluatorManager.evaluate("custNo", custNo.toString(), String.class, this, pageContext);;
	}


}
