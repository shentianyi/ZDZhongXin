package com.zd.tools.taglib.select;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import com.zd.csms.supervisory.dao.ISuperviseImportDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class SyncstatTag extends TagSupport {

	private Object serialNo;
	private ISuperviseImportDAO supervisorDao = SupervisorDAOFactory.getSuperviseImportDAO();

	public int doStartTag() throws JspException {
		String sync = "已同步";
		String syncno = "未同步";
		String serialNo = (String) this.serialNo;
		boolean exist = false;
		if(StringUtil.isNotEmpty(serialNo)){
			exist = supervisorDao.serialNoExist(serialNo);
		}
		try{
			if (exist) {
				pageContext.getOut().write(sync);
			}else{
				pageContext.getOut().write(syncno);
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		return 6;
	}

	public Object getSerialNo() throws JspException {
		return serialNo;
	}

	public void setSerialNo(Object serialNo) throws JspException {
		this.serialNo = (String)ExpressionEvaluatorManager.evaluate("custNo", serialNo.toString(), String.class, this, pageContext);
	}
}
