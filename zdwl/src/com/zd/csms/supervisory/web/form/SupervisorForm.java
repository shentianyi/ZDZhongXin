package com.zd.csms.supervisory.web.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.model.SupervisorEntity;
import com.zd.csms.supervisory.model.SupervisorFamilyVO;
import com.zd.csms.supervisory.model.SupervisorQueryVO;
import com.zd.csms.supervisory.model.SupervisorRecommendVO;
import com.zd.csms.supervisory.model.SupervisorWorkExperienceVO;

public class SupervisorForm extends ActionForm{
	
	private static final long serialVersionUID = -8564571356027231103L;
	private SupervisorQueryVO query=new SupervisorQueryVO();
	private SupervisorBaseInfoVO baseInfo=new SupervisorBaseInfoVO();
	private SupervisorEducationVO education=new SupervisorEducationVO();
	private SupervisorFamilyVO family=new SupervisorFamilyVO();
	private SupervisorRecommendVO recommend=new SupervisorRecommendVO();
	private SupervisorWorkExperienceVO workExperience=new SupervisorWorkExperienceVO();
	private SupervisorEntity supervisor=new SupervisorEntity();
	private FormFile headPicture;
	private FormFile excelfile;
	public SupervisorQueryVO getQuery() {
		return query;
	}
	public void setQuery(SupervisorQueryVO query) {
		this.query = query;
	}
	public SupervisorBaseInfoVO getBaseInfo() {
		return baseInfo;
	}
	public void setBaseInfo(SupervisorBaseInfoVO baseInfo) {
		this.baseInfo = baseInfo;
	}
	public SupervisorEducationVO getEducation() {
		return education;
	}
	public void setEducation(SupervisorEducationVO education) {
		this.education = education;
	}
	public SupervisorFamilyVO getFamily() {
		return family;
	}
	public void setFamily(SupervisorFamilyVO family) {
		this.family = family;
	}
	public SupervisorRecommendVO getRecommend() {
		return recommend;
	}
	public void setRecommend(SupervisorRecommendVO recommend) {
		this.recommend = recommend;
	}
	public SupervisorWorkExperienceVO getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(SupervisorWorkExperienceVO workExperience) {
		this.workExperience = workExperience;
	}
	public SupervisorEntity getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(SupervisorEntity supervisor) {
		this.supervisor = supervisor;
	}
	public FormFile getHeadPicture() {
		return headPicture;
	}
	public void setHeadPicture(FormFile headPicture) {
		this.headPicture = headPicture;
	}
	public FormFile getExcelfile() {
		return excelfile;
	}
	public void setExcelfile(FormFile excelfile) {
		this.excelfile = excelfile;
	}
	
}
