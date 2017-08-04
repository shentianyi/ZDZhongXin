package com.zd.csms.attendance.excel;

import com.zd.csms.attendance.model.AttendanceLegerBean;
import com.zd.tools.file.importFile.IImportRowMapper;

public class AttendanceRowMapper implements IImportRowMapper {

	@Override
	public String[] exportRow(Object obj) {
		
		AttendanceLegerBean bean=(AttendanceLegerBean) obj;
		if(bean==null){
			bean=new AttendanceLegerBean();
		}
		String[] result=new String[]{bean.getStaffNo(),bean.getName(),bean.getDate(),bean.getProvinceName(),bean.getCityName(),
				bean.getDealerNames(),bean.getBankNames(),bean.getOne(),bean.getTwo(),bean.getThree(),
				bean.getFour(),bean.getFive(),bean.getSix(),bean.getSeven(),bean.getEight(),bean.getNine(),
				bean.getTen(),bean.getEleven(),bean.getTwelve(),bean.getThirteen(),bean.getFourteen(),bean.getFifteen(),
				bean.getSixteen(),bean.getSeventeen(),bean.getEighteen(),bean.getNineteen(),bean.getTwenty(),
				bean.getTwentyOne(),bean.getTwentyTwo(),bean.getTwentyThree(),bean.getTwentyFour(),bean.getTwentyFive(),
				bean.getTwentySix(),bean.getTwentySeven(),bean.getTwentyEight(),bean.getTwentyNine(),bean.getThirty(),
				bean.getThirtyOne(),String.valueOf(bean.getMatterHoliday()),String.valueOf(bean.getMatterHolidayUpdate()),
				String.valueOf(bean.getAilingHoliday()),String.valueOf(bean.getAilingHolidayUpdate()),String.valueOf(bean.getLateDay()),
				String.valueOf(bean.getEarlyDay()),String.valueOf(bean.getAbsenteeism()),String.valueOf(bean.getDaoxiu()),String.valueOf(bean.getDaoxiuUpdate()),
				String.valueOf(bean.getZhengxiu()),String.valueOf(bean.getZhengxiuUpdate()),String.valueOf(bean.getOvertime()),
				String.valueOf(bean.getOvertimeUpdate()),"岗前","岗后",String.valueOf(bean.getActualAttendance()),bean.getFullTime()==1?"是":"否",bean.getRemark()};
		return result;
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"员工工号","员工姓名","年月","省","市","经销商","金融机构","1","2",
				"3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31",
				"事假修改前","事假修改后","病假修改前","病假修改后","迟到","早退","旷工","倒休修改前","倒休修改后","正休修改前",
				"正休修改后","加班修改前","加班修改后","岗前","岗后","实际出勤","全勤","备注"};
	}

	@Override
	public Object importRow(String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
