package com.zd.tools.taglib.select;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.roster.model.RosterQueryBean;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class DealerNameTag extends TagSupport {

	private Object dealerid;
	private Object idtype;

	public int doStartTag() throws JspException {
		
		int id = Integer.parseInt(this.dealerid.toString());
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		String name="";
		
		DealerService ds = new DealerService();
		
		try{
			if(!StringUtil.isEmpty(type)){
				if(id > 0){
					RosterService rs = new RosterService();
					RosterQueryVO rquery = new RosterQueryVO();
					rquery.setSupervisorId(id);
					List<RosterVO> rList = rs.searchRosterList(rquery);
					if(rList != null && rList.size()>0){
						RosterVO rvo = rList.get(0);
						DealerSupervisorService dsservice = new DealerSupervisorService();
						DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
						dsquery.setSupervisorId(rvo.getRepositoryId());
						List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
						if(dsList != null && dsList.size()>0){
							DealerSupervisorVO dsvo = dsList.get(0);
							int dealerId = dsvo.getDealerId();
							DealerVO dvo = ds.loadDealerById(dealerId);
							if(dvo != null){
								name = dvo.getDealerName();
							}
						}
					}
					
					
				}
			}else{
				if(id > 0){
					DealerVO dvo = ds.loadDealerById(id);
					if(dvo != null){
						name = dvo.getDealerName();
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

	

	
	public Object getDealerid() {
		return dealerid;
	}

	public void setDealerid(Object dealerid) throws JspException {
		this.dealerid = (Integer)ExpressionEvaluatorManager.evaluate("dealerid", dealerid.toString(), Integer.class, this, pageContext);;
	}
	
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}

}
