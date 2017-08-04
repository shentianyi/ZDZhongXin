package com.zd.csms.supervisory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.contants.RecommendChannelContants;
import com.zd.csms.supervisory.contants.RegisteredStatusContants;
import com.zd.csms.supervisory.dao.ISupervisoryDao;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.model.SupervisorEntity;
import com.zd.csms.supervisory.model.SupervisorFamilyVO;
import com.zd.csms.supervisory.model.SupervisorQueryVO;
import com.zd.csms.supervisory.model.SupervisorRecommendVO;
import com.zd.csms.supervisory.model.SupervisorWorkExperienceVO;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisoryService extends ServiceSupport {
		RegionService regionService=new RegionService();
		private ISupervisoryDao dao = SupervisorDAOFactory.getSupervisoryDAO();
		public boolean addBaseInfo(SupervisorBaseInfoVO baseInfo){
			boolean flag=false;
			try {
				//验证身份证号是否唯一 true:唯一，false:不唯一
				flag=dao.validateIdNumberUnique(baseInfo.getId(), baseInfo.getIdNumber());
				if(flag){
					baseInfo.setId(Util.getID(SupervisorBaseInfoVO.class));
					flag=dao.add(baseInfo);
					if(flag){
						this.setExecuteMessage("新增成功！");
					}else{
						this.setExecuteMessage("新增失败！");
					}
				}else{
					this.setExecuteMessage("该身份证号已存在，请核实后重新输入！");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		public boolean addEducation(SupervisorEducationVO educationVO){
			try {
				educationVO.setId(Util.getID(SupervisorEducationVO.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Boolean flag=dao.add(educationVO);
			if(flag){
				this.setExecuteMessage("新增成功！");
			}else{
				this.setExecuteMessage("新增失败！");
			}
			return flag;
		}
		public boolean addFamily(SupervisorFamilyVO family){
			try {
				family.setId(Util.getID(SupervisorFamilyVO.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			boolean flag=dao.add(family);
			if(flag){
				this.setExecuteMessage("新增成功！");
			}else{
				this.setExecuteMessage("新增失败！");
			}
			return flag;
		}
		public boolean addRecommend(SupervisorRecommendVO recommend){
			try {
				recommend.setId(Util.getID(SupervisorRecommendVO.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			boolean flag=dao.add(recommend);
			if(flag){
				this.setExecuteMessage("新增成功！");
			}else{
				this.setExecuteMessage("新增失败！");
			}
			return flag;
		}
		public boolean addWorkExperience(SupervisorWorkExperienceVO workExperience){
			try {
				workExperience.setId(Util.getID(SupervisorWorkExperienceVO.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			boolean flag=dao.add(workExperience);
			if(flag){
				this.setExecuteMessage("新增成功！");
			}else{
				this.setExecuteMessage("新增失败！");
			}
			return flag;
		}
		public boolean updBaseInfo(SupervisorBaseInfoVO baseInfo){
			boolean flag=false;
			//验证身份证号是否唯一 true:唯一，false:不唯一
			flag=dao.validateIdNumberUnique(baseInfo.getId(), baseInfo.getIdNumber());
			if(flag){
				flag=dao.update(baseInfo);
				if(flag){
					this.setExecuteMessage("修改成功！");
				}else{
					this.setExecuteMessage("修改失败！");
				}
			}else{
				this.setExecuteMessage("该身份证号已存在，请核实后重新输入！");
			}
			return flag;
		}
		public boolean updEducation(SupervisorEducationVO educationVO){
			boolean flag=dao.update(educationVO);
			if(flag){
				this.setExecuteMessage("修改成功！");
			}else{
				this.setExecuteMessage("修改失败！");
			}
			return flag;
		}
		public boolean updFamily(SupervisorFamilyVO family){
			boolean flag=dao.update(family);
			if(flag){
				this.setExecuteMessage("修改成功！");
			}else{
				this.setExecuteMessage("修改失败！");
			}
			return flag;
		}
		public boolean updRecommend(SupervisorRecommendVO recommend){
			boolean flag=dao.update(recommend);
			if(flag){
				this.setExecuteMessage("修改成功！");
			}else{
				this.setExecuteMessage("修改失败！");
			}
			return flag;
		}
		public boolean updWorkExperience(SupervisorWorkExperienceVO workExperience){
			boolean flag=dao.update(workExperience);
			if(flag){
				this.setExecuteMessage("修改成功！");
			}else{
				this.setExecuteMessage("修改失败！");
			}
			return flag;
		}
		public boolean delBaseInfo(int id){
			boolean flag=dao.delete(SupervisorBaseInfoVO.class, id);
			if(flag){
				this.setExecuteMessage("删除成功！");
			}else{
				this.setExecuteMessage("删除失败！");
			}
			return flag;
		}
		public boolean delEducation(int id){
			boolean flag=dao.delete(SupervisorEducationVO.class, id);
			if(flag){
				this.setExecuteMessage("删除成功！");
			}else{
				this.setExecuteMessage("删除失败！");
			}
			return flag;
		}
		public boolean delFamily(int id){
			boolean flag=dao.delete(SupervisorFamilyVO.class, id);
			if(flag){
				this.setExecuteMessage("删除成功！");
			}else{
				this.setExecuteMessage("删除失败！");
			}
			return flag;
		}
		public boolean delRecommend(int id){
			boolean flag= dao.delete(SupervisorRecommendVO.class, id);
			if(flag){
				this.setExecuteMessage("删除成功！");
			}else{
				this.setExecuteMessage("删除失败！");
			}
			return flag;
		}
		public boolean delWorkExperience(int id){
			boolean flag=dao.delete(SupervisorWorkExperienceVO.class, id);
			if(flag){
				this.setExecuteMessage("删除成功！");
			}else{
				this.setExecuteMessage("删除失败！");
			}
			return flag;
		}
		
		public SupervisorBaseInfoVO getBaseInfo(int id){
			return dao.get(SupervisorBaseInfoVO.class, id,new BeanPropertyRowMapper(SupervisorBaseInfoVO.class));
		}
		public SupervisorBaseInfoJsonVO getBaseInfoJson(int id){
			SupervisorBaseInfoVO bean = getBaseInfo(id);
			if(bean==null){
				return null;
			}
			SupervisorBaseInfoJsonVO json=new SupervisorBaseInfoJsonVO();
			BeanUtils.copyProperties(bean, json);
			String registeredAddress= bean.getRegisteredAddress()==null?"":bean.getRegisteredAddress();
			String liveAddress=bean.getLiveAddress()==null?"":bean.getLiveAddress();
			json.setRegisteredAddress(regionService.getNameById(StringUtil.intValue(bean.getNativePlaceProvince(),0))
					+" "+regionService.getNameById(StringUtil.intValue(bean.getNativePlaceCity(),0))
					+" "+regionService.getNameById(StringUtil.intValue(bean.getNativePlaceCounty(),0))
					+" "+registeredAddress);
			json.setLiveAddress(regionService.getNameById(StringUtil.intValue(bean.getLiveAddressProvince(),0))
					+" "+regionService.getNameById(StringUtil.intValue(bean.getLiveAddressCity(),0))
					+" "+regionService.getNameById(StringUtil.intValue(bean.getLiveAddressCounty(),0))
					+" "+liveAddress);
			json.setNativePlaceProvince(regionService.getNameById(StringUtil.intValue(bean.getNativePlaceProvince(),0)));
			json.setNativePlaceCity(regionService.getNameById(StringUtil.intValue(bean.getNativePlaceCity(),0)));
			json.setNativePlaceCounty(regionService.getNameById(StringUtil.intValue(bean.getNativePlaceCounty(),0)));
			json.setLiveAddressProvince(regionService.getNameById(StringUtil.intValue(bean.getLiveAddressProvince(),0)));
			json.setLiveAddressCity(regionService.getNameById(StringUtil.intValue(bean.getLiveAddressCity(),0)));
			json.setLiveAddressCounty(regionService.getNameById(StringUtil.intValue(bean.getLiveAddressCounty(),0)));
			
			json.setRegisteredStatusStr(RegisteredStatusContants.getNameByCode(bean.getRegisteredStatus()));
			Date birthday=bean.getBirthday();
			if(birthday!=null){
				json.setBirthdayStr(DateUtil.getStringFormatByDate(bean.getBirthday(),"yyyy-MM-dd"));
			}
			Date entryDate=bean.getEntryTime();
			if(entryDate!=null){
				json.setEntryTimeStr(DateUtil.getStringFormatByDate(bean.getEntryTime(),"yyyy-MM-dd"));
			}
			SupervisorRecommendVO recommend=getRecommendBySupervisorId(id);
			if(recommend!=null){
				RecommendChannelContants recommendChannel=RecommendChannelContants.getByCode(recommend.getOtherChannel());
				if(recommendChannel!=null){
					json.setRecommendChannel(recommendChannel.getName());
				}
				json.setRecommenderName(recommend.getRecommenderName());
				json.setRecommenderPosition(recommend.getRecommenderPosition());
			}
			List<SupervisorEducationVO> educations=getEducationBySupervisorId(id);
			if(educations!=null && educations.size()>0){
				SupervisorEducationVO education=educations.get(0);
				if(education!=null){
					json.setGraduateSchool(education.getSchoolName());
					json.setMajor(education.getMajor());
				}
			}
			RosterService rosterService=new RosterService();
			RosterVO roster=rosterService.getRosterBySupervisorId(id);
			if(roster!=null){
				json.setStaffNo(roster.getStaffNo());
			}
			return json;
		}
		public SupervisorEducationVO getEducation(int id){
			return dao.get(SupervisorEducationVO.class, id,new BeanPropertyRowMapper(SupervisorEducationVO.class));
		}
		
		public SupervisorFamilyVO getFamily(int id){
			return dao.get(SupervisorFamilyVO.class, id,new BeanPropertyRowMapper(SupervisorFamilyVO.class));
		}
		
		public SupervisorRecommendVO getRecommend(int id){
			return dao.get(SupervisorRecommendVO.class, id,new BeanPropertyRowMapper(SupervisorRecommendVO.class));
		}
		
		public SupervisorWorkExperienceVO getWorkExperience(int id){
			return dao.get(SupervisorWorkExperienceVO.class, id,new BeanPropertyRowMapper(SupervisorWorkExperienceVO.class));
		}
		
		public List<SupervisorEducationVO> getEducationBySupervisorId(int supervisorId){
			return dao.getEducationBySupervisorId(supervisorId);
		}
		
		public List<SupervisorFamilyVO> getFamilyBySupervisorId(int supervisorId){
			return dao.getFamilyBySupervisorId(supervisorId);
		}
		
		public SupervisorRecommendVO getRecommendBySupervisorId(int supervisorId){
			return dao.getRecommendBySupervisorId(supervisorId);
		}
		
		public List<SupervisorWorkExperienceVO> getWorkExperienceBySupervisorId(int supervisorId){
			return dao.getWorkExperienceBySupervisorId(supervisorId);
		}
		
		public boolean delete(int id) {
			boolean flag=false;
			try{
				flag=true && dao.deleteEducationBySupervisorId(id);
				
				if(flag){
					flag=flag && dao.deleteFamilyBySupervisorId(id);
				}
				if(flag){
					flag=flag && dao.deleteRecommendBySupervisorId(id);
				}
				if(flag){
					flag=flag && dao.deleteWorkExperienceBySupervisorId(id);
				}
				
				if(flag){
					flag=dao.delete(SupervisorBaseInfoVO.class, id);
				}
				if(flag){
					this.setExecuteMessage("删除成功！");
				}else{
					this.setExecuteMessage("删除失败！");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return flag;
		}


		public SupervisorEntity load(int id) {
			SupervisorEntity supervisorEntity=new SupervisorEntity();
			SupervisorBaseInfoVO supervisorBaseInfoVO=dao.get(SupervisorBaseInfoVO.class, id,new BeanPropertyRowMapper(SupervisorBaseInfoVO.class));
			if(supervisorBaseInfoVO!=null){
				String registeredAddress=supervisorBaseInfoVO.getRegisteredAddress()==null?"":supervisorBaseInfoVO.getRegisteredAddress();
				String liveAddress=supervisorBaseInfoVO.getLiveAddress()==null?"":supervisorBaseInfoVO.getLiveAddress();
				supervisorBaseInfoVO.setRegisteredAddress(regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getNativePlaceProvince(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getNativePlaceCity(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getNativePlaceCounty(),0))
						+" "+registeredAddress);
				supervisorBaseInfoVO.setLiveAddress(regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getLiveAddressProvince(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getLiveAddressCity(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getLiveAddressCounty(),0))
						+" "+liveAddress);
				
			}
			List<SupervisorEducationVO> supervisoryEducation=dao.getEducationBySupervisorId(id);
			/*SupervisorEducationVO education =new SupervisorEducationVO();
			if(supervisoryEducation.size()<4){
				int count = supervisoryEducation.size();
				for(;count<4;count++){
					supervisoryEducation.add(education);
				}
			}else{
				supervisoryEducation=supervisoryEducation.subList(0, 3);
			}*/
			List<SupervisorFamilyVO> supervisoryFamily=dao.getFamilyBySupervisorId(id);
			/*SupervisorFamilyVO family=new SupervisorFamilyVO();
			if(supervisoryFamily.size()<3){
				int count=supervisoryFamily.size();
				for(;count<3;count++){
					supervisoryFamily.add(family);
				}
			}else{
				supervisoryFamily=supervisoryFamily.subList(0, 2);
			}*/
			
			SupervisorRecommendVO supervisoryRecommend=dao.getRecommendBySupervisorId(id);
			if(supervisoryRecommend==null){
				supervisoryRecommend=new SupervisorRecommendVO();
			}
			
			List<SupervisorWorkExperienceVO> supervisoryWorkExperience=dao.getWorkExperienceBySupervisorId(id);
			/*SupervisorWorkExperienceVO workExperience=new SupervisorWorkExperienceVO();
			if(supervisoryWorkExperience.size()<3){
				int count=supervisoryWorkExperience.size();
				for(;count<3;count++){
					supervisoryWorkExperience.add(workExperience);
				}
			}else{
				supervisoryWorkExperience=supervisoryWorkExperience.subList(0, 2);
			}*/
			
			supervisorEntity.setSuperVisorBaseInfo(supervisorBaseInfoVO);
			supervisorEntity.setSupervisoryEducation(supervisoryEducation);
			supervisorEntity.setSupervisoryFamily(supervisoryFamily);
			supervisorEntity.setSupervisoryRecommend(supervisoryRecommend);
			supervisorEntity.setSupervisoryWorkExperience(supervisoryWorkExperience);
			return supervisorEntity;
		}

		public SupervisorEntity loadSupervisorInfo(int id) {
			SupervisorEntity supervisorEntity=new SupervisorEntity();
			
			
			SupervisorBaseInfoVO supervisorBaseInfoVO=dao.get(SupervisorBaseInfoVO.class, id,new BeanPropertyRowMapper(SupervisorBaseInfoVO.class));
			if(supervisorBaseInfoVO!=null){
				String registeredAddress=supervisorBaseInfoVO.getRegisteredAddress()==null?"":supervisorBaseInfoVO.getRegisteredAddress();
				String liveAddress=supervisorBaseInfoVO.getLiveAddress()==null?"":supervisorBaseInfoVO.getLiveAddress();
				supervisorBaseInfoVO.setRegisteredAddress(regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getNativePlaceProvince(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getNativePlaceCity(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getNativePlaceCounty(),0))
						+" "+registeredAddress);
				supervisorBaseInfoVO.setLiveAddress(regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getLiveAddressProvince(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getLiveAddressCity(),0))
						+" "+regionService.getNameById(StringUtil.intValue(supervisorBaseInfoVO.getLiveAddressCounty(),0))
						+" "+liveAddress);
			}
			List<SupervisorEducationVO> supervisoryEducation=dao.getEducationBySupervisorId(id);
			
			List<SupervisorFamilyVO> supervisoryFamily=dao.getFamilyBySupervisorId(id);
			
			SupervisorRecommendVO supervisoryRecommend=dao.getRecommendBySupervisorId(id);
			
			List<SupervisorWorkExperienceVO> supervisoryWorkExperience=dao.getWorkExperienceBySupervisorId(id);
			
			supervisorEntity.setSuperVisorBaseInfo(supervisorBaseInfoVO);
			supervisorEntity.setSupervisoryEducation(supervisoryEducation);
			supervisorEntity.setSupervisoryFamily(supervisoryFamily);
			supervisorEntity.setSupervisoryRecommend(supervisoryRecommend);
			supervisorEntity.setSupervisoryWorkExperience(supervisoryWorkExperience);
			return supervisorEntity;
		}
		
		public List<SupervisorBaseInfoVO> searchLists(SupervisorQueryVO supervisorQuery){
			return dao.searchList(supervisorQuery);
		}

		public List<SupervisorEntity> searchList(
				SupervisorQueryVO supervisorQuery) {
			List<SupervisorEntity> result=new ArrayList<SupervisorEntity>();
			List<SupervisorBaseInfoVO> list=dao.searchList(supervisorQuery);
			if(list!=null && list.size()>0){
				for(SupervisorBaseInfoVO supervisorBaseInfo:list){
					if(supervisorBaseInfo!=null){
						SupervisorEntity SupervisorEntity=new SupervisorEntity();
						SupervisorEntity.setSuperVisorBaseInfo(supervisorBaseInfo);
						int SupervisorId=supervisorBaseInfo.getId();
						SupervisorEntity.setSupervisoryEducation(dao.getEducationBySupervisorId(SupervisorId));
						SupervisorEntity.setSupervisoryFamily(dao.getFamilyBySupervisorId(SupervisorId));
						SupervisorEntity.setSupervisoryRecommend(dao.getRecommendBySupervisorId(SupervisorId));
						SupervisorEntity.setSupervisoryWorkExperience(dao.getWorkExperienceBySupervisorId(SupervisorId));
						result.add(SupervisorEntity);
					}
				}
			}
			return result;
		}

		public List<SupervisorEntity> searchPageList(
				SupervisorQueryVO supervisoryQuery, IThumbPageTools tools) {
			List<SupervisorEntity> result=new ArrayList<SupervisorEntity>();
			List<SupervisorBaseInfoVO> list=dao.searchPageList(supervisoryQuery, tools);
			if(list!=null && list.size()>0){
				for(SupervisorBaseInfoVO supervisorBaseInfo:list){
					if(supervisorBaseInfo!=null){
						supervisorBaseInfo.setNativePlaceProvince(regionService.getNameById(StringUtil.intValue(supervisorBaseInfo.getNativePlaceProvince(),0)));
						supervisorBaseInfo.setNativePlaceCity(regionService.getNameById(StringUtil.intValue(supervisorBaseInfo.getNativePlaceCity(),0)));
						supervisorBaseInfo.setNativePlaceCounty(regionService.getNameById(StringUtil.intValue(supervisorBaseInfo.getNativePlaceCounty(),0)));
						supervisorBaseInfo.setLiveAddressProvince(regionService.getNameById(StringUtil.intValue(supervisorBaseInfo.getLiveAddressProvince(),0)));
						supervisorBaseInfo.setLiveAddressCity(regionService.getNameById(StringUtil.intValue(supervisorBaseInfo.getLiveAddressCity(),0)));
						supervisorBaseInfo.setLiveAddressCounty(regionService.getNameById(StringUtil.intValue(supervisorBaseInfo.getLiveAddressCounty(),0)));
						
						SupervisorEntity SupervisorEntity=new SupervisorEntity();
						SupervisorEntity.setSuperVisorBaseInfo(supervisorBaseInfo);
						int SupervisorId=supervisorBaseInfo.getId();
						SupervisorEntity.setSupervisoryEducation(dao.getEducationBySupervisorId(SupervisorId));
						SupervisorEntity.setSupervisoryFamily(dao.getFamilyBySupervisorId(SupervisorId));
						SupervisorEntity.setSupervisoryRecommend(dao.getRecommendBySupervisorId(SupervisorId));
						SupervisorEntity.setSupervisoryWorkExperience(dao.getWorkExperienceBySupervisorId(SupervisorId));
						result.add(SupervisorEntity);
					}
				}
			}
			return result;
		}
		public boolean updateSupervisorEntryDate(int supervisorId, Date date) {
			return dao.updateSupervisorEntryDate(supervisorId,date);
			
		}
}
