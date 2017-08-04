package com.zd.tools.taglib.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class CarOperationTag extends TagSupport {

	private Object did;
	private Object idtype;
	
	CarOperationService coservice = new CarOperationService();
	
	DealerByIdJsonAction dja = new DealerByIdJsonAction();
	
	SuperviseImportService sservice = new SuperviseImportService();
	
	DraftService ds = new DraftService();
	
	public int doStartTag() throws JspException {
		
		int id = (Integer)this.did;
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		
		String name = "";
		
		try {
			
			CarOperationVO covo = coservice.loadCarOperationById(id);
			if(covo != null){
				String cars = covo.getCars();
				if (!StringUtil.isEmpty(cars)) {
					String[] idArray = cars.split(",");
					String siid = idArray[0];
					SuperviseImportVO svo = sservice.loadSuperviseImportById(Integer.parseInt(siid));
					DraftQueryVO dquery = new DraftQueryVO();
					dquery.setDraft_num(svo.getDraft_num());
					List<DraftVO> dList = ds.searchDraftList(dquery);
					if(dList != null && dList.size()>0){
						int did = dList.get(0).getDistribid();
						
						DealerQueryBean dealer = dja.getDealer(did);
						
						if(dealer != null){
							if(type.equals("jxs")){
								if(!StringUtil.isEmpty(dealer.getDealerName())){
									name = dealer.getDealerName();
								}
							}else if(type.equals("jrjg")){
								if(!StringUtil.isEmpty(dealer.getBankName())){
									name = dealer.getBankName();
								}
							}else if(type.equals("brand")){
								if(!StringUtil.isEmpty(dealer.getBrand())){
									name = dealer.getBrand();
								}
							}else if(type.equals("province")){
								if(!StringUtil.isEmpty(dealer.getProvince())){
									name = dealer.getProvince();
								}
							}else if(type.equals("city")){
								if(!StringUtil.isEmpty(dealer.getCity())){
									name = dealer.getCity();
								}
							}else if(type.equals("contact")){
								if(!StringUtil.isEmpty(dealer.getContact())){
									name = dealer.getContact();
								}
							}else if(type.equals("contactPhone")){
								if(!StringUtil.isEmpty(dealer.getContactPhone())){
									name = dealer.getContactPhone();
								}
							}
						}
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

	public Object getDid() {
		return did;
	}

	public void setDid(Object did) throws JspException {
		this.did = (Integer)ExpressionEvaluatorManager.evaluate("did", did.toString(), Integer.class, this, pageContext); 
	}
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}
	
}
