package com.zd.csms.roster.web.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterEntity;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;

public class RosterForm extends ActionForm{
	private static final long serialVersionUID = -8564571356027231103L;
	private RosterEntity rosterEntity=new RosterEntity();
	private RosterQueryVO rosterQuery=new RosterQueryVO();
	private RosterVO roster=new RosterVO();
	private PostChangeVO postChange=new PostChangeVO();
	
	/* 添加操作用户类型 */
	private int client_type;
	
	public int getClient_type() {
		return client_type;
	}
	public void setClient_type(int client_type) {
		this.client_type = client_type;
	}
	public RosterEntity getRosterEntity() {
		return rosterEntity;
	}
	public void setRosterEntity(RosterEntity rosterEntity) {
		this.rosterEntity = rosterEntity;
	}
	public RosterQueryVO getRosterQuery() {
		return rosterQuery;
	}
	public void setRosterQuery(RosterQueryVO rosterQuery) {
		this.rosterQuery = rosterQuery;
	}
	public RosterVO getRoster() {
		return roster;
	}
	public void setRoster(RosterVO roster) {
		this.roster = roster;
	}
	public PostChangeVO getPostChange() {
		return postChange;
	}
	public void setPostChange(PostChangeVO postChange) {
		this.postChange = postChange;
	}
	
}
