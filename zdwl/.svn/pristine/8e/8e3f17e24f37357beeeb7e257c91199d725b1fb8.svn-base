package com.zd.csms.supervisory.web.jsonaction;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.zd.core.JSONAction;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.contants.RecommendChannelContants;
import com.zd.csms.supervisory.contants.RegisteredStatusContants;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.model.SupervisorRecommendVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

public class SupervisorJsonAction extends JSONAction{
	SupervisoryService service = new SupervisoryService();
	RegionService regionService=new RegionService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String callback = request.getParameter("callback");
		String parameter = request.getParameter("id");
		int id = 0;
		if(parameter==null||!"".equals(parameter.trim())){
			 id = Integer.parseInt(parameter);
		}
		super.makeJSONObject(response, callback,getSupervisorBaseInfoJsonVOById(id) );
		return null;
	}
	public SupervisorBaseInfoJsonVO getSupervisorBaseInfoJsonVOById(int id){
		SupervisorBaseInfoVO bean = service.getBaseInfo(id);
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
		json.setBirthdayStr(DateUtil.getStringFormatByDate(bean.getBirthday(),"yyyy-MM-dd"));
		Date entryDate=bean.getEntryTime();
		if(entryDate!=null){
			json.setEntryTimeStr(DateUtil.getStringFormatByDate(bean.getEntryTime(),"yyyy-MM-dd"));
		}
		SupervisorRecommendVO recommend=service.getRecommendBySupervisorId(id);
		if(recommend!=null){
			RecommendChannelContants recommendChannel=RecommendChannelContants.getByCode(recommend.getOtherChannel());
			if(recommendChannel!=null){
				json.setRecommendChannel(recommendChannel.getName());
			}
			json.setRecommenderName(recommend.getRecommenderName());
			json.setRecommenderPosition(recommend.getRecommenderPosition());
			json.setRecommenderPhone(recommend.getRecommenderPhone());
		}
		List<SupervisorEducationVO> educations=service.getEducationBySupervisorId(id);
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
}
