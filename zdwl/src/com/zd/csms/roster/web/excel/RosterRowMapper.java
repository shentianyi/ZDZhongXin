package com.zd.csms.roster.web.excel;

import java.util.List;

import com.zd.core.util.ArrayUtils;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterEntity;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.web.jsonaction.SupervisorJsonAction;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class RosterRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		RosterService service=new RosterService();
		RosterEntity entity=(RosterEntity)obj;
		RosterVO roster=entity.getRoster();
		if(roster==null){
			roster=new RosterVO();
		}
		//set驻派地区
		String dispatchCity="";
		RepositoryService repositoryService=new RepositoryService();
		RepositoryDispatchCityVO dispatchCityVO=repositoryService.loadDispatchCity(StringUtil.intValue(roster.getDispatchCity(),0));
		if(dispatchCityVO!=null){
			dispatchCity=dispatchCityVO.getProvince()+" "+dispatchCityVO.getCity()+" "+dispatchCityVO.getCounty();
		}
		roster.setDispatchCity(dispatchCity);
		
		//储备库招聘信息
		RepositoryVO repository=repositoryService.load(roster.getRepositoryId());
		if(repository==null){
			repository=new RepositoryVO();
		}
		
		//监管员基本信息
		SupervisorJsonAction supervisorJsonAction=new SupervisorJsonAction();
		SupervisorBaseInfoJsonVO supervisor=supervisorJsonAction.getSupervisorBaseInfoJsonVOById(roster.getSupervisorId());
		
		//岗位轮换表记录
		List<PostChangeVO> postChangeList=entity.getPostChangeList();
		String[] postChangeStr=new String[]{};
		if(postChangeList !=null && postChangeList.size()>0){
			for(PostChangeVO postChange:postChangeList){
				String[] d=new String[]{DateUtil.getStringFormatByDate(postChange.getMissionDate(),"yyyy-MM-dd")
						,postChange.getMissionNature(),DateUtil.getStringFormatByDate(postChange.getDimissionDate(),"yyyy-MM-dd"),
						postChange.getDimissionNature()};
				postChangeStr = (String[]) ArrayUtils.concat(postChangeStr, d);
			}
		}
		String[] result=new String[]{roster.getStaffNo(),
				supervisor.getName(),supervisor.getIdNumber(),supervisor.getGender(),supervisor.getBirthdayStr(),
				supervisor.getFertilityState(),supervisor.getNation(),supervisor.getEducationBackground(),supervisor.getGraduateSchool(),
				supervisor.getMajor(),supervisor.getRegisteredAddress(),supervisor.getLiveAddress(),
				roster.getPaycardNo(),roster.getOpenBank(),supervisor.getSelfTelephone(),roster.getOrganizeType(),String.valueOf(roster.getDriveYear()),
				String.valueOf(roster.getDriveMonth()),supervisor.getEntryTimeStr(),roster.getDispatchAttribute(),roster.getDispatchCity(),
				roster.getEnlistStatus(),supervisor.getRecommendChannel(),supervisor.getRecommenderName(),supervisor.getRecommenderPosition(),
				repository.getInterviewee(),roster.getSystemAccount(),roster.getSystemPassword()
				};
		return (String[]) ArrayUtils.concat(result,postChangeStr);
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"员工工号","监管员姓名","身份证号","性别","出生日期","婚姻状况","民族","学历","毕业院校","专业",
				"户口所在地","现住址","工资卡号","开户行","联系方式"
				,"编制类型","司龄（年）","司龄（月数）","入职日期","驻派属性","驻派地区","服役状态","招聘渠道","推荐人姓名","推荐人背景"
				,"面试经手人","系统账号","系统密码","上岗日期","上岗性质","离岗日期","离岗性质","上岗日期","上岗性质","离岗日期","离岗性质"
				,"上岗日期","上岗性质","离岗日期","离岗性质","上岗日期","上岗性质","离岗日期","离岗性质","…"};
	}

	@Override
	public Object importRow(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
