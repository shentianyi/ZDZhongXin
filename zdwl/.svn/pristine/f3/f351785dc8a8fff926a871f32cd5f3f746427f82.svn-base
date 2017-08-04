package com.zd.tools.taglib.select;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class DealerInfoByRepositoryIdTag extends TagSupport {

	private Object repositoryId;
	private Object idtype;

	public int doStartTag() throws JspException {
		int repositoryId=0;
		if(this.repositoryId.toString()!=null){
			repositoryId = Integer.parseInt(this.repositoryId.toString());
		}
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		String name="";
		
		DealerService dealerService = new DealerService();
		try{
			if(repositoryId > 0 && StringUtil.isNotEmpty(type)){
				DealerSupervisorService dss = new DealerSupervisorService();
				DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
				dsquery.setSupervisorId(repositoryId);
				List<DealerSupervisorVO> dsList = dss.searchDealerSupervisorList(dsquery);
				if(dsList != null && dsList.size()>0){
					for(DealerSupervisorVO ds:dsList){
						DealerQueryBean dealer= dealerService.detailInfo(ds.getDealerId(),0);
						if(dealer!=null){
							if(type.endsWith("dealerName")){
								name=name+" "+dealer.getDealerName();
							}else if(type.endsWith("bankName")){
								name=name+" "+dealer.getBankName();
							}else if(type.endsWith("brandName")){
								name=name+" "+dealer.getBrand();
							}
						}
					}
				}
			}
			pageContext.getOut().write(name);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new JspException(e);
		}
		
		return 6;
	}

	

	
	public Object getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(Object repositoryId) throws JspException {
		this.repositoryId = (Integer)ExpressionEvaluatorManager.evaluate("repositoryId", repositoryId.toString(), Integer.class, this, pageContext);;
	}
	
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}

}
