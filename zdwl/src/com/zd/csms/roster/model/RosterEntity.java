package com.zd.csms.roster.model;

import java.util.ArrayList;
import java.util.List;

public class RosterEntity {
	private RosterVO roster=new RosterVO();
	private List<PostChangeVO> postChangeList=new ArrayList<PostChangeVO>();
	public RosterVO getRoster() {
		return roster;
	}
	public void setRoster(RosterVO roster) {
		this.roster = roster;
	}
	public List<PostChangeVO> getPostChangeList() {
		return postChangeList;
	}
	public void setPostChangeList(List<PostChangeVO> postChangeList) {
		this.postChangeList = postChangeList;
	}
	
}
