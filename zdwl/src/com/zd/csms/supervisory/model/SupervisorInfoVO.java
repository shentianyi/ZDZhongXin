package com.zd.csms.supervisory.model;

import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.roster.model.RosterVO;
public class SupervisorInfoVO {
	
	private SupervisorBaseInfoVO baseInfo=new SupervisorBaseInfoVO();
	private RepositoryVO repository=new RepositoryVO();
	private RosterVO roster=new RosterVO();
	public SupervisorBaseInfoVO getBaseInfo() {
		return baseInfo;
	}
	public void setBaseInfo(SupervisorBaseInfoVO baseInfo) {
		this.baseInfo = baseInfo;
	}
	public RepositoryVO getRepository() {
		return repository;
	}
	public void setRepository(RepositoryVO repository) {
		this.repository = repository;
	}
	public RosterVO getRoster() {
		return roster;
	}
	public void setRoster(RosterVO roster) {
		this.roster = roster;
	}
	
}
