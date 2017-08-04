package com.zd.tools.taglib.select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;

@SuppressWarnings("serial")
public class DealerTag extends TagSupport {

	private Object sid;
	private Object idtype;
	private Object dn;
	DealerByIdJsonAction dja = new DealerByIdJsonAction();
	
	public int doStartTag() throws JspException {
		
		int id = (Integer)this.sid;
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		String draftnum = "";
		if(dn != null){
			draftnum = dn.toString();
		}
		
		String name = "";
		
		DealerService ds = new DealerService();
		DraftService draftservice = new DraftService();
		SuperviseImportService siservice = new SuperviseImportService();
		List<SuperviseImportVO> sumList = new ArrayList<SuperviseImportVO>();
		try {
			DealerVO dvo = ds.loadDealerById(id);
			if(dvo != null){
				if(type.equals("kc")){
					DraftQueryVO dq = new DraftQueryVO();
					dq.setDistribid(dvo.getId());
					List<DraftVO> dList = draftservice.searchDraftList(dq);
					if(dList != null && dList.size()>0){
						for(DraftVO draftvo : dList){
							SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
							siquery.setDraft_num(draftvo.getDraft_num());
							List<SuperviseImportVO> sList = siservice.searchSuperviseImportList(siquery);
							if(sList != null && sList.size()>0){
								for(SuperviseImportVO svo : sList){
									sumList.add(svo);
								}
							}
							
						}
					}
					
					name = sumList.size()+"";
				}else if(type.equals("hp")){
					DraftQueryVO dq = new DraftQueryVO();
					dq.setDistribid(dvo.getId());
					List<DraftVO> dList = draftservice.searchDraftList(dq);
					name = dList.size()+"";
				}
			}
			
			DraftQueryVO dq = new DraftQueryVO();
			dq.setDraft_num(draftnum);
			List<DraftVO> dList = draftservice.searchDraftList(dq);
			if(dList != null && dList.size() > 0){
				DraftVO draft = dList.get(0);
				int dealerid = draft.getDistribid();
				DealerQueryBean dealer = dja.getDealer(dealerid);
				
				
				if(dealer != null){
					if(type.equals("jxs")){
						name = dealer.getDealerName();
					}else if(type.equals("jrjg")){
						name = dealer.getBankName();
					}else if(type.equals("brand")){
						name = dealer.getBrand();
					}
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

	public Object getDn() {
		return dn;
	}

	public void setDn(Object dn) throws JspException {
		this.dn = (String) ExpressionEvaluatorManager.evaluate("dn", dn.toString(), String.class, this,pageContext);
	}
	
}
