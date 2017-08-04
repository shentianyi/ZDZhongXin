package com.zd.csms.base.web.form;

import org.apache.struts.action.ActionForm;

import com.zd.csms.repository.model.RepositoryQueryVO;


public class SelectForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3490019220709128103L;
	
	private RepositoryQueryVO repositoryQuery=new RepositoryQueryVO();

	public RepositoryQueryVO getRepositoryQuery() {
		return repositoryQuery;
	}

	public void setRepositoryQuery(RepositoryQueryVO repositoryQuery) {
		this.repositoryQuery = repositoryQuery;
	}
	
	
	
}
