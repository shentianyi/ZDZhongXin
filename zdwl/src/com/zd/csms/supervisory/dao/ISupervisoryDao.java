package com.zd.csms.supervisory.dao;

import java.util.Date;
import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.model.SupervisorFamilyVO;
import com.zd.csms.supervisory.model.SupervisorQueryVO;
import com.zd.csms.supervisory.model.SupervisorRecommendVO;
import com.zd.csms.supervisory.model.SupervisorWorkExperienceVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ISupervisoryDao extends IDAO{
	
	public String getDataSourceName();
	
	public List<SupervisorEducationVO> getEducationBySupervisorId(int SupervisorId);
	public boolean deleteEducationBySupervisorId(int SupervisorId);
	
	public List<SupervisorFamilyVO> getFamilyBySupervisorId(int SupervisorId);
	public boolean deleteFamilyBySupervisorId(int SupervisorId);
	
	public SupervisorRecommendVO getRecommendBySupervisorId(int SupervisorId);
	public boolean deleteRecommendBySupervisorId(int SupervisorId);
	
	public List<SupervisorWorkExperienceVO> getWorkExperienceBySupervisorId(int SupervisorId);
	public boolean deleteWorkExperienceBySupervisorId(int SupervisorId);
	
	public List<SupervisorBaseInfoVO> searchList(SupervisorQueryVO supervisorQuery);

	public List<SupervisorBaseInfoVO> searchPageList(
			SupervisorQueryVO supervisoryQuery, IThumbPageTools tools);

	public boolean updateSupervisorEntryDate(int supervisorId, Date date);
	//验证身份证号是否唯一 true:唯一，false:不唯一
	public boolean validateIdNumberUnique(int supervisorId,String idNumber);
}
