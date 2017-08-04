package com.zd.tools.taglib.select;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;

@SuppressWarnings("serial")
public class SuperviseImportTag extends TagSupport {

	private Object sid;
	private Object idtype;
	
	public int doStartTag() throws JspException {
		
		int id = (Integer)this.sid;
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		String name = "";
		
		SuperviseImportService siservice = new SuperviseImportService();
		DraftService draftservice = new DraftService();
		DealerService dealerService = new DealerService();
		BrandService bs = new BrandService();
		try {
			SuperviseImportVO svo = siservice.loadSuperviseImportById(id);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			if(type.equals("certificate_date")){
				if(svo.getCertificate_date() != null){
					Date show=formatter.parse(svo.getCertificate_date().toString());
					name=formatter.format(show);
				}
				
			}else if(type.equals("certificate_num")){
				if(svo.getCertificate_num() != null){
					name = svo.getCertificate_num();
				}
			}else if(type.equals("car_model")){
				if(svo.getCar_model() != null){
					name = svo.getCar_model();
				}
			}else if(type.equals("displacement")){
				if(svo.getDisplacement() != null){
					name = svo.getDisplacement();
				}
			}else if(type.equals("color")){
				if(svo.getColor() != null){
					name = svo.getColor();
				}
			}else if(type.equals("engine_num")){
				if(svo.getEngine_num() != null){
					name = svo.getEngine_num();
				}
			}else if(type.equals("vin")){
				if(svo.getVin() != null){
					name = svo.getVin();
				}
			}else if(type.equals("key_num")){
				if(svo.getKey_num() != null){
					name = svo.getKey_num();
				}
			}else if(type.equals("storagetime")){
				if(svo.getStoragetime() != null){
					Date show=formatter.parse(svo.getStoragetime().toString());
					name=formatter.format(show);
				}
			}else if(type.equals("outtime")){
				if(svo.getOuttime() != null){
					Date show=formatter.parse(svo.getOuttime().toString());
					name=formatter.format(show);
				}
			}else if(type.equals("draft_num")){
				if(svo.getDraft_num() != null){
					name = svo.getDraft_num();
				}
			}else if(type.equals("key_amount")){
				if(svo.getKey_amount() != null){
					name = svo.getKey_amount();
				}
			}else if(type.equals("money")){
				if(svo.getMoney() != null){
					name = svo.getMoney();
				}
			}else if(type.equals("jxs")){
				DraftQueryVO dquery = new DraftQueryVO();
				dquery.setDraft_num(svo.getDraft_num());
				List<DraftVO> draftList = draftservice.searchDraftList(dquery);
				if(draftList != null && draftList.size()>0){
					DraftVO draft = draftList.get(0);
					int distribid = draft.getDistribid();
					DealerVO dealer = dealerService.loadDealerById(distribid);
					name = dealer.getDealerName();
				}
			}else if(type.equals("brand")){
				DraftQueryVO dquery = new DraftQueryVO();
				dquery.setDraft_num(svo.getDraft_num());
				List<DraftVO> draftList = draftservice.searchDraftList(dquery);
				if(draftList != null && draftList.size()>0){
					DraftVO draft = draftList.get(0);
					int distribid = draft.getDistribid();
					DealerVO dealer = dealerService.loadDealerById(distribid);
					BrandVO brand = bs.loadBrandById(dealer.getBrandId());
					if(brand != null){
						name = brand.getName();
					}
				}
			}else if(type.equals("two_name")){
				if(svo.getTwo_name() != null){
					name = svo.getTwo_name();
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
