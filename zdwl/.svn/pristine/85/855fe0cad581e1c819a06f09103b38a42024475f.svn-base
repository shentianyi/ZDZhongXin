package com.zd.csms.repository.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.zd.core.ServiceSupport;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.SupMaMsgInfoVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.dao.IRepositoryDAO;
import com.zd.csms.repository.dao.RepositoryDAOFactory;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryEntity;
import com.zd.csms.repository.model.RepositoryJsonVO;
import com.zd.csms.repository.model.RepositoryQueryVO;
import com.zd.csms.repository.model.RepositorySelectBean;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class RepositoryService extends ServiceSupport {
	
	private IRepositoryDAO dao = RepositoryDAOFactory.getRepositoryDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	RegionService regionService=new RegionService();
	SupervisoryService supervisoryService=new SupervisoryService();
	public RepositoryVO get(int id){
		if(id<=0)
			return null;
		return dao.get(RepositoryVO.class, id, new BeanPropertyRowMapper(RepositoryVO.class));
	}
	
	public boolean delete(int id) {
		
		RosterService rService=new RosterService();
		List<RosterVO> rosters=rService.searchRosterByRepositoryId(id);
		if(rosters!=null && rosters.size()>0){
			this.setExecuteMessage("该储备库信息已生成花名册，不可删除！");
			return false;
		}
		boolean flag=true;
		RepositoryVO repositoryVO=dao.get(RepositoryVO.class, id, new BeanPropertyRowMapper(RepositoryVO.class));
		if(repositoryVO!=null){
			List<RepositoryDispatchCityVO> repositoryDispatchCityVO=dao.getDispatchCityByRepositoryId(id);
			if(repositoryDispatchCityVO!=null && repositoryDispatchCityVO.size()>0){
				for(RepositoryDispatchCityVO repositoryDispatchCity:repositoryDispatchCityVO){
					flag=dao.delete(SupervisorEducationVO.class, repositoryDispatchCity.getId());
					if(!flag){
						break;
					}
				}
			}
			RepositoryTrainVO RepositoryTrainVO=dao.getRepositoryTrainByRepositoryId(repositoryVO.getId());
			if(RepositoryTrainVO!=null && flag){
				flag=dao.delete(RepositoryTrainVO.class, RepositoryTrainVO.getId());
			}
			if(flag){
				flag=dao.delete(RepositoryVO.class, id);
			}
		}
		if(flag){
			this.setExecuteMessage("删除成功！");
			return true;
		}else{
			this.setExecuteMessage("删除失败！");
			return false;
		}
	}

	public boolean update(RepositoryVO repository) {
		
		boolean flag=dao.update(repository);
		if(flag){
			if(repository.getStatus()==RepositoryStatus.ALREADYPOST.getCode()){
				supervisoryService.updateSupervisorEntryDate(repository.getSupervisorId(),new Date());
			}
			this.setExecuteMessage("修改成功！");
			return true;
		}else{
			this.setExecuteMessage("修改失败！");
			return false;
		}
	}

	public RepositoryVO load(int id) {
		RepositoryVO repository=dao.get(RepositoryVO.class, id,new BeanPropertyRowMapper(RepositoryVO.class));
		if(repository!=null){
			repository.setNativePlaceProvince(regionService.getNameById(StringUtil.intValue(repository.getNativePlaceProvince(),0)));
			repository.setNativePlaceCity(regionService.getNameById(StringUtil.intValue(repository.getNativePlaceCity(),0)));
			repository.setNativePlaceCounty(regionService.getNameById(StringUtil.intValue(repository.getNativePlaceCounty(),0)));
			repository.setLiveAddressProvince(regionService.getNameById(StringUtil.intValue(repository.getLiveAddressProvince(),0)));
			repository.setLiveAddressCity(regionService.getNameById(StringUtil.intValue(repository.getLiveAddressCity(),0)));
			repository.setLiveAddressCounty(regionService.getNameById(StringUtil.intValue(repository.getLiveAddressCounty(),0)));
		}
		return repository;
	}

	public boolean add(RepositoryVO repository) {
		RepositoryQueryVO repositoryQuery=new RepositoryQueryVO();
		repositoryQuery.setSupervisorId(repository.getSupervisorId());
		List<RepositoryVO> list=dao.searchList(repositoryQuery);
		if(!CollectionUtils.isEmpty(list)){
			this.setExecuteMessage("该监管员已经创建储备库信息，请选择其他监管员！");
			return false;
		}
		boolean flag=false;
		try {
			repository.setId(Util.getID(RepositoryVO.class));
			flag=dao.add(repository);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			this.setExecuteMessage("新增成功！");
			//修改监管员基本信息中入职时间
			if(repository.getStatus()==RepositoryStatus.ALREADYPOST.getCode()){
				supervisoryService.updateSupervisorEntryDate(repository.getSupervisorId(),new Date());
			}
			//发送通知
			SupervisoryService supervisoryService=new SupervisoryService();
			SupervisorBaseInfoVO supervisor=supervisoryService.getBaseInfo(repository.getSupervisorId());
			String name=supervisor.getName()+" 储备库新增完成，请补充培训信息";
			String url="/repository.do?method=repositoryPageList";
			MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode()+""},
					name, url, 1, MessageTypeContant.REPOSITORY.getCode(), repository.getCreateUser());
		}else{
			this.setExecuteMessage("新增失败！");
		}
		return flag;
	}

	public List<RepositoryEntity> searchList(
			RepositoryQueryVO repositoryQueryVO) {
		List<RepositoryEntity> result=new ArrayList<RepositoryEntity>();
		List<RepositoryVO> list=dao.searchList(repositoryQueryVO);
		if(list!=null){
			for(RepositoryVO repository:list){
				if(repository!=null){
					RepositoryEntity repositoryEntity=new RepositoryEntity();
					repositoryEntity.setRepository(repository);
					int repositoryId=repository.getId();
					repositoryEntity.setRepositoryDispatchCity(dao.getDispatchCityByRepositoryId(repositoryId));
					repositoryEntity.setRepositoryTrain(dao.getRepositoryTrainByRepositoryId(repositoryId));
					result.add(repositoryEntity);
				}
			}
		}
		return result;
	}
	
	public List<RepositoryVO> repositoryList(RepositoryQueryVO repositoryQueryVO){
		return dao.searchList(repositoryQueryVO);
	}

	public List<RepositoryEntity> searchPageList(
			RepositoryQueryVO repositoryQueryVO, IThumbPageTools tools) {
		List<RepositoryEntity> result=new ArrayList<RepositoryEntity>();
		List<RepositoryVO> list=dao.searchPageList(repositoryQueryVO, tools);
		if(list!=null){
			for(RepositoryVO repository:list){
				if(repository!=null){
					RepositoryEntity repositoryEntity=new RepositoryEntity();
					repositoryEntity.setRepository(repository);
					int repositoryId=repository.getId();
					repositoryEntity.setRepositoryDispatchCity(dao.getDispatchCityByRepositoryId(repositoryId));
					repositoryEntity.setRepositoryTrain(dao.getRepositoryTrainByRepositoryId(repositoryId));
					result.add(repositoryEntity);
				}
			}
		}
	
		
	
		
		
		/*Collections.sort(result, new Comparator<RepositoryEntity>(){
			public int compare(RepositoryEntity vo1,RepositoryEntity vo2){
				if(vo1.getRepository().getLastModifyTime()!= null && vo2.getRepository().getLastModifyTime() != null){
					if(vo1.getRepository().getLastModifyTime().compareTo(vo2.getRepository().getLastModifyTime()) < 0){
						return -1;
					}else if(vo1.getRepository().getLastModifyTime().compareTo(vo2.getRepository().getLastModifyTime()) > 0){
						return 1;
					}else{
						return 0;
					}
				}else if(vo1.getRepository().getLastModifyTime()!= null && vo2.getRepository().getLastModifyTime() == null){
					return -1;
				}else if(vo1.getRepository().getLastModifyTime() == null && vo2.getRepository().getLastModifyTime() != null){
					return 1;
				}else{
					return 0;
				}
			}
		});*/
		return result;
	}

	public List<RepositoryDispatchCityVO> getRepositoryDispatchCityByRepositoryId(
			int repositoryId) {
		List<RepositoryDispatchCityVO> dispatchCityList=dao.getDispatchCityByRepositoryId(repositoryId);
		if(dispatchCityList!=null && dispatchCityList.size()>0){
			for(RepositoryDispatchCityVO dispatchCity:dispatchCityList){
				if(dispatchCity!=null){
					dispatchCity.setProvince(regionService.getNameById(StringUtil.intValue(dispatchCity.getProvince(),0)));
					dispatchCity.setCity(regionService.getNameById(StringUtil.intValue(dispatchCity.getCity(),0)));
					dispatchCity.setCounty(regionService.getNameById(StringUtil.intValue(dispatchCity.getCounty(),0)));
				}
			}
		}
		return dispatchCityList;
	}

	public boolean addRepositoryDispatchCity(
			RepositoryDispatchCityVO repositoryDispatchCityVO) {
		if(repositoryDispatchCityVO!=null ){
			try {
				repositoryDispatchCityVO.setId(Util.getID(RepositoryDispatchCityVO.class));
				return dao.add(repositoryDispatchCityVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean updRepositoryDispatchCity(
			RepositoryDispatchCityVO repositoryDispatchCityVO) {
		return dao.update(repositoryDispatchCityVO);
	}

	public boolean delRepositoryDispatchCity(int id) {
		return dao.delete(RepositoryDispatchCityVO.class, id);
	}

	public RepositoryDispatchCityVO loadRepositoryDispatchCity(int id) {
		return dao.get(RepositoryDispatchCityVO.class, id,new BeanPropertyRowMapper(RepositoryDispatchCityVO.class));
	}
	
	public RepositoryDispatchCityVO loadDispatchCity(int id){
		RepositoryDispatchCityVO dispatchCity=dao.get(RepositoryDispatchCityVO.class, id,new BeanPropertyRowMapper(RepositoryDispatchCityVO.class));
		if(dispatchCity!=null){
			dispatchCity.setProvince(regionService.getNameById(StringUtil.intValue(dispatchCity.getProvince(),0)));
			dispatchCity.setCity(regionService.getNameById(StringUtil.intValue(dispatchCity.getCity(),0)));
			dispatchCity.setCounty(regionService.getNameById(StringUtil.intValue(dispatchCity.getCounty(),0)));
		}
		return dispatchCity;
	}
	public boolean  updateRepositoryStatus(int id,int status,int reason) {
		return dao.updateRepositoryStatus(id,status,reason);
	}
	public RepositoryVO loadRepositoryBySupervisorId(int supervisorId) {
		return dao.loadRepositoryBySupervisorId(supervisorId);
	}
	public RepositoryJsonVO loadRepositoryJsonBySupervisorId(int supervisorId) {
		RepositoryJsonVO json=new RepositoryJsonVO();
		RepositoryVO bean = loadRepositoryBySupervisorId(supervisorId);
		if(bean!=null){
			BeanUtils.copyProperties(bean, json);
			RepositoryTrainVO train=loadRepositoryTrainByRepositoryId(bean.getId());
			if(train!=null){
				BeanUtils.copyProperties(train, json);
				json.setIsTrain("是");
				json.setStartTimeStr(DateUtil.getStringFormatByDate(train.getStartTime(),"yyyy-MM-dd"));
				json.setEndTimeStr(DateUtil.getStringFormatByDate(train.getEndTime(),"yyyy-MM-dd"));
			}
		}
		json.setIsTrain("否");
		return json;
	}
	public RepositoryTrainVO loadRepositoryTrain(int repositoryTrainId) {
		return dao.get(RepositoryTrainVO.class, repositoryTrainId,new BeanPropertyRowMapper(RepositoryTrainVO.class));
	}
	public boolean updateTrain(RepositoryTrainVO train) {
		boolean flag=false;
		if(train.getId()>0){
			flag=dao.update(train);
		}else{
			try {
				train.setId(Util.getID(RepositoryTrainVO.class));
				flag=dao.add(train);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(flag){
			this.setExecuteMessage("修改成功！");
			return true;
		}else{
			this.setExecuteMessage("修改失败！");
			return false;
		}
	}
	public RepositoryTrainVO loadRepositoryTrainByRepositoryId(int repositoryId) {
		return dao.getRepositoryTrainByRepositoryId(repositoryId);
	}
	public RepositoryEntity loadRepositoryEntity(int repositoryId) {
		
		RepositoryEntity repositoryEntity=new RepositoryEntity();
		
		RepositoryVO repository=load(repositoryId);
		if(repository==null){
			repository=new RepositoryVO();
		}
		repositoryEntity.setRepository(repository);
		
		List<RepositoryDispatchCityVO> dispatchCityList=dao.getDispatchCityByRepositoryId(repositoryId);
		if(dispatchCityList!=null && dispatchCityList.size()>0){
			for(RepositoryDispatchCityVO dispatchCity:dispatchCityList){
				if(dispatchCity!=null){
					dispatchCity.setProvince(regionService.getNameById(StringUtil.intValue(dispatchCity.getProvince(),0)));
					dispatchCity.setCity(regionService.getNameById(StringUtil.intValue(dispatchCity.getCity(),0)));
					dispatchCity.setCounty(regionService.getNameById(StringUtil.intValue(dispatchCity.getCounty(),0)));
				}
			}
		}
		/*RepositoryDispatchCityVO dispatchCity=new RepositoryDispatchCityVO();
		if(dispatchCityList.size()<4){
			int count=dispatchCityList.size();
			for(;count<4;count++){
				dispatchCityList.add(dispatchCity);
			}
		}else{
			dispatchCityList=dispatchCityList.subList(0, 3);
		}*/
		repositoryEntity.setRepositoryDispatchCity(dispatchCityList);
		
		RepositoryTrainVO train=dao.getRepositoryTrainByRepositoryId(repositoryId);
		if(train==null){
			train=new RepositoryTrainVO();
		}
		repositoryEntity.setRepositoryTrain(train);
		
		return repositoryEntity;
	}
	
	public List<RepositorySelectBean> selectRepositoryList(RepositoryQueryVO query,IThumbPageTools tools){
		return dao.selectRepositoryList(query, tools);
	}
	public List<RepositoryEntity> searchExportList(
			RepositoryQueryVO repositoryQueryVO) {
		List<RepositoryEntity> result=new ArrayList<RepositoryEntity>();
		List<RepositoryVO> list=dao.searchList(repositoryQueryVO);
		if(list!=null){
			for(RepositoryVO repository:list){
				if(repository!=null){
					repository.setNativePlaceProvince(regionService.getNameById(StringUtil.intValue(repository.getNativePlaceProvince(),0)));
					repository.setNativePlaceCity(regionService.getNameById(StringUtil.intValue(repository.getNativePlaceCity(),0)));
					repository.setNativePlaceCounty(regionService.getNameById(StringUtil.intValue(repository.getNativePlaceCounty(),0)));
					repository.setLiveAddressProvince(regionService.getNameById(StringUtil.intValue(repository.getLiveAddressProvince(),0)));
					repository.setLiveAddressCity(regionService.getNameById(StringUtil.intValue(repository.getLiveAddressCity(),0)));
					repository.setLiveAddressCounty(regionService.getNameById(StringUtil.intValue(repository.getLiveAddressCounty(),0)));
					RepositoryEntity repositoryEntity=new RepositoryEntity();
					repositoryEntity.setRepository(repository);
					int repositoryId=repository.getId();
					repositoryEntity.setRepositoryDispatchCity(getRepositoryDispatchCityByRepositoryId(repositoryId));
					repositoryEntity.setRepositoryTrain(dao.getRepositoryTrainByRepositoryId(repositoryId));
					result.add(repositoryEntity);
				}
			}
		}
		return result;
	}
	public SupervisorBaseInfoVO getSupervisorBaseInfoByRepositoryId(int repositoryId) {
		return dao.getSupervisorBaseInfoByRepositoryId(repositoryId);
	}
	public List<RepositoryDispatchCityVO> findDispatchCityList(String provinceId,String cityId,String countyId){
		List<RepositoryDispatchCityVO> dispatchCityList=dao.findDispatchCityList(provinceId,cityId,countyId);
		return dispatchCityList;
	}
	/**
	 * 监管员培训考核不通过提醒
	 * @param today
	 * @return
	 */
	public List<SupMaMsgInfoVO> supAssessNotPassList(Date today) {
		List<SupMaMsgInfoVO> supAssessNotPassList=dao.supAssessNotPassList(today);
		if(supAssessNotPassList!=null && supAssessNotPassList.size()>0){
			for(SupMaMsgInfoVO supAssessNotPass:supAssessNotPassList){
				 List<RepositoryDispatchCityVO> dispatchCityList= getRepositoryDispatchCityByRepositoryId(supAssessNotPass.getId());
				 if(dispatchCityList!=null && dispatchCityList.size()>0){
					 StringBuffer intentionCity=new StringBuffer();
					 for(RepositoryDispatchCityVO dispatchCity:dispatchCityList){
						 intentionCity.append(dispatchCity.getProvince()).append(" ");
						 intentionCity.append(dispatchCity.getCity()).append(" ");
						 intentionCity.append(dispatchCity.getCounty()).append("  ");
					 }
					 supAssessNotPass.setIntentionCity(intentionCity.toString());
				 }
			}
		}
		return supAssessNotPassList;
	}

	public List<RepositoryVO> findRepositoryByStatus(int status) {
		return dao.findRepositoryByStatus(status);
	}
}
