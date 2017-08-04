package com.zd.csms.supervisory.web.excel;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisory.contants.RecommendChannelContants;
import com.zd.csms.supervisory.contants.RegisteredStatusContants;
import com.zd.csms.supervisory.model.SuperviseVO;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class SuperviseRowMapper implements IImportRowMapper {
	RegionService regionService=new RegionService();
	@Override
	public String[] exportRow(Object obj) {
		return null;
	}

	@Override
	public String[] exportTitle() {
		return null;
	}

	@Override
	public Object importRow(String[] values) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		SuperviseVO o = new SuperviseVO();
		try {
			o.setJob(values[0]);
			Date entryTime=null;
			if(values[1]!=null){
				entryTime=formatter.parse(values[1].toString());
			}
			o.setEntryTime(entryTime);
			o.setName(values[2]);
			o.setGender(values[3]);
			
			Date birthday =null;
			if(values[4]!=null){
				birthday=formatter.parse(values[4].toString());
			}
			o.setBirthday(birthday);
			o.setIdNumber(values[5]);
			o.setNation(values[6]);
			o.setEducationBackground(values[7]);
			o.setNativePlace(values[8]);
			o.setPoliticsStatus(values[9]);
			String registeredStatus=values[10];
			if(StringUtil.isNotEmpty(registeredStatus) && registeredStatus.equals("城镇户口")){
				o.setRegisteredStatus(RegisteredStatusContants.TOWN.getCode());
			}else if(StringUtil.isNotEmpty(registeredStatus) && registeredStatus.equals("农业户口")){
				o.setRegisteredStatus(RegisteredStatusContants.AGRICULTURE.getCode());
			}else{
				o.setRegisteredStatus(0);
			}
			o.setSelfTelephone(values[11]);
			o.setHomeTelephone(values[12]);
			o.setFertilityState(values[13]);
			//根据地区名称查地区ID
			int provinceId=regionService.getIdByName(0,values[14]);
			o.setNativePlaceProvince(String.valueOf(provinceId));
			int cityId=regionService.getIdByName(provinceId,values[15]);
			o.setNativePlaceCity(String.valueOf(cityId));
			int countyId=regionService.getIdByName(cityId,values[16]);
			o.setNativePlaceCounty(String.valueOf(countyId));
			StringBuffer registeredAddress=new StringBuffer();
			for(int i=14;i<=17;i++){
				if(values[i]!=null){
					registeredAddress.append(values[i]);
				}
			}
			o.setRegisteredAddress(registeredAddress.toString());
			//根据地区名称查地区ID
			int liveAddressProvinceId=regionService.getIdByName(0,values[18]);
			o.setLiveAddressProvince(String.valueOf(liveAddressProvinceId));
			int liveAddressCityId=regionService.getIdByName(liveAddressProvinceId,values[19]);
			o.setLiveAddressCity(String.valueOf(liveAddressCityId));
			int liveAddressCountyId=regionService.getIdByName(liveAddressCityId,values[20]);
			o.setLiveAddressCounty(String.valueOf(liveAddressCountyId));
			o.setLiveAddress(values[18]+" "+values[19]+" "+values[20]+" "+values[21]);
			o.setEmergencyContactor(values[22]);
			o.setEmergencyContactNumber(values[23]);
			o.setEmergencyContactRelationship(values[24]);
			
			Date educationStartTime =null;
			if(values[25]!=null){
				educationStartTime=formatter.parse(values[25].toString());
			}
			o.setEducationStartTime(educationStartTime);
			Date educationEndTime =null;
			if(values[26]!=null){
				educationEndTime=formatter.parse(values[26].toString());
			}
			o.setEducationEndTime(educationEndTime);
			o.setSchoolName(values[27]);
			o.setMajor(values[28]);
			o.setCertificate(values[29]);
			o.setDegree(values[30]);
			
			Date workStartTime = null;
			if(values[31]!=null){
				workStartTime=formatter.parse(values[31].toString());
			}
			o.setWorkStartTime(workStartTime);
			Date workEndTime =null;
			if(values[32]!=null){
				 workEndTime = formatter.parse(values[32].toString());
			}
			o.setWorkEndTime(workEndTime);
			o.setServiceOrganization(values[33]);
			o.setPosition(values[34]);
			o.setCompensation(values[35]);
			o.setLeaveReason(values[36]);
			o.setCertifier(values[37]);
			o.setContactNumber(values[38]);
			o.setFname(values[39]);
			o.setFprofession(values[40]);
			o.setForganization(values[41]);
			o.setFtelephone(values[42]);
			o.setFrelationship("父亲");
			o.setMname(values[43]);
			o.setMprofession(values[44]);
			o.setMorganization(values[45]);
			o.setMtelephone(values[46]);
			o.setMrelationship("母亲");
			o.setXname(values[47]);
			o.setXprofession(values[48]);
			o.setXorganization(values[49]);
			o.setXtelephone(values[50]);
			o.setXrelationship("兄弟姐妹");
			o.setZname(values[51]);
			o.setZprofession(values[52]);
			o.setZorganization(values[53]);
			o.setZtelephone(values[54]);
			o.setZrelationship("子女");
			//o.setIsInsideRecommend(values[55]);
			String otherChannel = values[55];
			if(StringUtil.isNotEmpty(otherChannel) && otherChannel.equals("校园招聘")){
				o.setOtherChannel(RecommendChannelContants.CAMPUS_RECRUITMENT.getCode());
			}else if(StringUtil.isNotEmpty(otherChannel) && otherChannel.equals("监管员推荐")){
				o.setOtherChannel(RecommendChannelContants.SUPERVISORYRECOMMEND.getCode());
			}else if(StringUtil.isNotEmpty(otherChannel) && otherChannel.equals("社会招聘")){
				o.setOtherChannel(RecommendChannelContants.SOCIETY_RECRUITMENT.getCode());
			}else if(StringUtil.isNotEmpty(otherChannel) && otherChannel.equals("内部人员推荐")){
				o.setOtherChannel(RecommendChannelContants.INTERNAL_RECOMMEND.getCode());
			}else{
				o.setOtherChannel(0);
			}
			o.setRecommenderName(values[56]);
			o.setRecommenderPosition(values[57]);
			o.setRecommenderPhone(values[58]);
			o.setRecommenderRelationship(values[59]);
			o.setRecommenderDepartment(values[60]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return o;
	}

}
