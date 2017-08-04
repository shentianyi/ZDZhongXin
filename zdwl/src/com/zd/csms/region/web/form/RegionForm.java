package com.zd.csms.region.web.form;


import org.apache.struts.action.ActionForm;

import com.zd.csms.region.model.RegionQueryVO;
import com.zd.csms.region.model.RegionVO;

public class RegionForm extends ActionForm{
	
	private static final long serialVersionUID = -8564571356027231103L;
	private RegionVO region=new RegionVO();
	private RegionQueryVO query=new RegionQueryVO();
	public RegionVO getRegion() {
		return region;
	}
	public void setRegion(RegionVO region) {
		this.region = region;
	}
	public RegionQueryVO getQuery() {
		return query;
	}
	public void setQuery(RegionQueryVO query) {
		this.query = query;
	}
	
}
