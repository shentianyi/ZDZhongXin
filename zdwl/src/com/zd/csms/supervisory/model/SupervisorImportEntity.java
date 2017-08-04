package com.zd.csms.supervisory.model;

import java.util.ArrayList;
import java.util.List;

public class SupervisorImportEntity {
	
	private SupervisorBaseInfoVO superVisorBaseInfo=new SupervisorBaseInfoVO();
	private SupervisorEducationVO supervisoryEducation=new SupervisorEducationVO();
	private List<SupervisorFamilyVO> supervisoryFamily=new ArrayList<SupervisorFamilyVO>();
	private SupervisorRecommendVO supervisoryRecommend=new SupervisorRecommendVO();
	private SupervisorWorkExperienceVO supervisoryWorkExperience=new SupervisorWorkExperienceVO();
	public SupervisorBaseInfoVO getSuperVisorBaseInfo() {
		return superVisorBaseInfo;
	}
	public void setSuperVisorBaseInfo(SupervisorBaseInfoVO superVisorBaseInfo) {
		this.superVisorBaseInfo = superVisorBaseInfo;
	}
	public SupervisorEducationVO getSupervisoryEducation() {
		return supervisoryEducation;
	}
	public void setSupervisoryEducation(SupervisorEducationVO supervisoryEducation) {
		this.supervisoryEducation = supervisoryEducation;
	}
	public List<SupervisorFamilyVO> getSupervisoryFamily() {
		return supervisoryFamily;
	}
	public void setSupervisoryFamily(List<SupervisorFamilyVO> supervisoryFamily) {
		this.supervisoryFamily = supervisoryFamily;
	}
	public SupervisorRecommendVO getSupervisoryRecommend() {
		return supervisoryRecommend;
	}
	public void setSupervisoryRecommend(SupervisorRecommendVO supervisoryRecommend) {
		this.supervisoryRecommend = supervisoryRecommend;
	}
	public SupervisorWorkExperienceVO getSupervisoryWorkExperience() {
		return supervisoryWorkExperience;
	}
	public void setSupervisoryWorkExperience(SupervisorWorkExperienceVO supervisoryWorkExperience) {
		this.supervisoryWorkExperience = supervisoryWorkExperience;
	}
	
}
