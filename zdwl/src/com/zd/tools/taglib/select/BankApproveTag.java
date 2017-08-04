package com.zd.tools.taglib.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisory.model.BankApproveQueryVO;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.service.BankApproveService;
import com.zd.csms.supervisory.service.SuperviseImportService;

@SuppressWarnings("serial")
public class BankApproveTag extends TagSupport {

	private Object sid;
	private Object idtype;
	
	public int doStartTag() throws JspException {
		
		int id = (Integer)this.sid;
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		
		String name = "";
		
		BankApproveService baservice = new BankApproveService();
		SuperviseImportService siservice = new SuperviseImportService();
		
		try {
			BankApproveQueryVO baquery = new BankApproveQueryVO();
			if(type.equals("storagetime")){
				baquery.setType(2);
				baquery.setSid(id);
				List<BankApproveVO> baList = baservice.searchBankApproveList(baquery);
				if(baList != null && baList.size()>0){
					BankApproveVO bavo = baList.get(0);
					if(bavo.getCreatetime()!= null){
						name = bavo.getCreatetime().toString();
					}
				}
			}else if(type.equals("outtime")){
				baquery.setType(3);
				baquery.setSid(id);
				List<BankApproveVO> baList = baservice.searchBankApproveList(baquery);
				if(baList != null && baList.size()>0){
					BankApproveVO bavo = baList.get(0);
					name = bavo.getCreatetime().toString();
				}
			}else if(type.equals("movetime")){
				baquery.setType(4);
				baquery.setSid(id);
				List<BankApproveVO> baList = baservice.searchBankApproveList(baquery);
				if(baList != null && baList.size()>0){
					BankApproveVO bavo = baList.get(0);
					name = bavo.getCreatetime().toString();
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
