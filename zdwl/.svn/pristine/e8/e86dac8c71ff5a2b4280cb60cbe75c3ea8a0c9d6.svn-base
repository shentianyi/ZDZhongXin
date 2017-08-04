package com.zd.tools.taglib.select;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.service.SupervisoryService;

@SuppressWarnings("serial")
public class SupervisorNameTag extends TagSupport {

	private Object supervisorId;
	private Object idtype;
	private Object repositoryId;

	public int doStartTag() throws JspException {
		
		int id = Integer.parseInt(this.supervisorId.toString());
		int repositoryId = Integer.parseInt(this.repositoryId.toString());
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		String name="";
		SupervisoryService ss = new SupervisoryService();
		try{
			if(id>0){
				 SupervisorBaseInfoJsonVO supervisor=ss.getBaseInfoJson(id);
				if(supervisor!=null){
					if(type.equals("name")){
						name=supervisor.getName();
					}else if(type.equals("idNumber")){
						name = supervisor.getIdNumber();
					}else if(type.equals("selfTelephone")){
						name = supervisor.getSelfTelephone();
					}else if(type.equals("address")){
						name=supervisor.getLiveAddress();
					}
					
				}
			}
			BusinessTransferService service = new BusinessTransferService();
			if(repositoryId>0){
				SupervisorJsonBean bean = service.getSupervisorByRepositoryId(repositoryId);
				if(bean!=null){
					if(type.equals("name")){
						name=bean.getName();
					}else if(type.equals("idNumber")){
						name = bean.getIdNumber();
					}else if(type.equals("selfTelephone")){
						name = bean.getSelfTelephone();
					}else if(type.equals("staffNo")){
						RosterService rservice=new RosterService();
						List<RosterVO> rList = rservice.searchRosterByRepositoryId(repositoryId);
						if(rList != null && rList.size()>0){
							RosterVO rvo = rList.get(0);
							name = rvo.getStaffNo()+"";
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

	

	
	public Object getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Object supervisorId) throws JspException {
		this.supervisorId = (Integer)ExpressionEvaluatorManager.evaluate("supervisorId", supervisorId.toString(), Integer.class, this, pageContext);
	}
	
	public Object getIdtype() {
		return idtype;
	}
	
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}

	public Object getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(Object repositoryId) throws JspException {
		this.repositoryId = (Integer)ExpressionEvaluatorManager.evaluate("repositoryId", repositoryId.toString(), Integer.class, this, pageContext);
	}

}
