package com.zd.csms.attendance.web.jsonaction;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSONObject;
import com.zd.core.JSONAction;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.service.AttendanceService;
import com.zd.csms.planandreport.service.InspectionPlanService;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

public class SaveAttendanceData extends JSONAction {
	InspectionPlanService service = new InspectionPlanService();
	private AttendanceService aService = new AttendanceService();

	// private InspectionPlanInfoService iservice = new
	// InspectionPlanInfoService();
	// private DealerSupervisorService dsService = new
	// DealerSupervisorService();
	// private DealerService dService = new DealerService();
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		
		int objectId = 0;
		String objid = request.getParameter("objid");// 1 上午签到 2 中午签退 3下午签到 4 下午签退
		String callback = request.getParameter("callback");
		String currTime = request.getParameter("currTime");
		Date date = DateUtil.getDateFormatByString(currTime, "yyyy-MM-dd HH:mm");//Test专用
		
		if (StringUtil.isNotEmpty(objid)) {
			objectId = Integer.parseInt(objid);
		}
		Calendar nowDate = Calendar.getInstance();
		nowDate.setTime(date);//Test专用 应为 new Date()
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int repositoryId = user.getClient_id();
		
		String dealerId = request.getParameter("dealerId");
		AttendanceTime currAttTime = aService.getAttendanceTime(Integer.parseInt(dealerId));
		int w = nowDate.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0)
            w = 7;
        if(currAttTime.getWorkDays().indexOf(w+"")==-1){
        	json.put("success", false);
        	json.put("message", "非工作日，无需签到");
        	super.makeJSONObject(response, callback, json);
    		return null;
        }
		
		String shouldtime = "";
		switch (objectId) {
		case 1:
			shouldtime = currAttTime.getMorningStart();
			break;
		case 2:
			shouldtime = currAttTime.getMorningEnd();
			break;
		case 3:
			shouldtime = currAttTime.getAfternoonStart();
			break;
		case 4:
			shouldtime = currAttTime.getAfternoonEnd();
			break;
		default:
			break;
		}

		

		Calendar calendar = Calendar.getInstance();
		if (StringUtil.isNotEmpty(shouldtime)) {
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shouldtime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(shouldtime.split(":")[1]));
		}
		

		// 签到时间和应签到时间的差别 前者减后者
		int minutes = (nowDate.get(Calendar.HOUR_OF_DAY) * 60 + nowDate.get(Calendar.MINUTE))
				- (calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE));
		
		SignRecord signRecord = aService.getSignRecordByUserId(nowDate.getTime(),repositoryId);
		if (signRecord == null) {
			signRecord = new SignRecord();
			signRecord.setRespId(repositoryId);
			setAddValue(signRecord, user.getId(),nowDate.getTime());// 设置创建日期,创建人
			signRecord.setWorkDays(currAttTime.getWorkDays());
			signRecord.setState(0);
			aService.addSignRecord(signRecord);
		}

		if (objectId == 1) {// 1 上午签到
			signRecord.setMorningStart(nowDate.getTime());// 设置上午签到时间
			if (minutes > 60) {
				signRecord.setIsAbsenteeism(1);// 旷工
			} else if (minutes > 0) {
				signRecord.setIsLate(1);// 迟到
			}
			aService.update(signRecord);
		} else if (objectId == 2) {// 2 中午签退
			signRecord.setMorningEnd(nowDate.getTime());// 设置上午签退时间
			if (minutes < -60) {// 旷工
				signRecord.setIsAbsenteeism(1);// 旷工
			} else if (minutes < 0) {// 早退
				signRecord.setIsEarly(1);// 早退
			}
			aService.update(signRecord);

		} else if (objectId == 3) {// 3下午签到
			signRecord.setAfternoonStart(nowDate.getTime());// 设置下午签到时间
			if (minutes > 60) {
				signRecord.setIsAbsenteeism(1);// 旷工
			} else if (minutes > 0) {
				signRecord.setIsLate(1);// 迟到
			}
			aService.update(signRecord);
		} else if (objectId == 4) {// 4 下午签退
			signRecord.setAfternoonEnd(nowDate.getTime());// 设置下午签退时间
			if (minutes < -60) {//旷工
				signRecord.setIsAbsenteeism(1);// 旷工
			} else if (minutes < 0) {//  早退
				signRecord.setIsEarly(1);// 早退
			}
			if(signRecord.getMorningStart()!=null&&signRecord.getMorningEnd()!=null
					&&signRecord.getAfternoonStart()!=null&&signRecord.getIsAbsenteeism()!=1){
				signRecord.setIsNormal(1);
			}
			aService.update(signRecord);
		}
		json.put("success", true);
		json.put("nowDate", DateUtil.getStringFormatByDate(nowDate.getTime(), "yyyy-MM-dd HH:mm"));
		super.makeJSONObject(response, callback, json);
		return null;
	}

	public void setAddValue(SignRecord signRecord, int uId,Date date) {
		signRecord.setCreateDate(date);// 创建日期
		signRecord.setCreateUserId(uId);// 创建人
	}
}
