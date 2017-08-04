package com.zd.csms.planandreport.web.jsonaction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.planandreport.model.InspectionPlanInfoVO;
import com.zd.csms.planandreport.model.InspectionPlanVO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;
import com.zd.csms.planandreport.service.InspectionPlanInfoService;
import com.zd.csms.planandreport.service.InspectionPlanService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;

public class SaveInspectionJsonAction extends JSONAction{
	InspectionPlanService service = new InspectionPlanService();
	private InspectionPlanInfoService iservice = new InspectionPlanInfoService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = 0;
		int objIdValue = 0;
		double num = 0;
		String callback = request.getParameter("callback");
		String planCodeInfo = request.getParameter("planCodeInfo");
		String videoPlanId = request.getParameter("id");
		String data = request.getParameter("data");
		String objid = request.getParameter("objid");// 1 代表预计开始时间  2 代表预计产生费用 3 代表预计完成日期 4预计检查台数5 代表备注
		if (StringUtil.isNotEmpty(objid)) {
			objIdValue = Integer.parseInt(objid);
		}
		if (StringUtil.isNotEmpty(videoPlanId)) {
			id = Integer.parseInt(videoPlanId);
		}
		if (objIdValue > 0 && objIdValue == 1 && StringUtil.isNotEmpty(data) && id > 0) {//更新预计开始时间
			InspectionPlanVO inspectionPlanVO = service.get(id);
			Date predictBeginDate = DateUtil.StringToDate(data);
			if (null != predictBeginDate) {
				inspectionPlanVO.setPredictBeginDate(predictBeginDate);
			}
			service.update(inspectionPlanVO);//更新  t_inispection_plan 表的预计开始时间
		}else if(objIdValue > 0 && objIdValue == 2 && StringUtil.isNotEmpty(data) && id > 0){//更新预计产生费用
			InspectionPlanVO inspectionPlanVO = service.get(id);
			Double predictCost = Double.valueOf(data);
			inspectionPlanVO.setPredictCost(predictCost);
			service.update(inspectionPlanVO);//更新  t_inispection_plan 表的预计产生费用
			if (StringUtil.isNotEmpty(planCodeInfo)) {
				InspectionPlanInfoQueryBean amountInfo = iservice.getDealerInfo(planCodeInfo);
				if (null != amountInfo) {
					String predictCostAmount = amountInfo.getPredictCostAmount();
					if (StringUtil.isNotEmpty(predictCostAmount)) {
						num = Double.parseDouble(predictCostAmount);
					}
				}
			}
		}else if(objIdValue > 0 && objIdValue == 3 && StringUtil.isNotEmpty(data) &&
				StringUtil.isNotEmpty(planCodeInfo)){
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			Date predictBeginDate = DateUtil.StringToDate(data);
			if (null != predictBeginDate) {
				inspectionPlanInfoVO.setPredictFinishDate(predictBeginDate);//更新预计完成日期
				iservice.update(inspectionPlanInfoVO);
			}
		}else if(objIdValue > 0 && objIdValue == 4 && StringUtil.isNotEmpty(data) 
				 && id > 0){
			InspectionPlanVO inspectionPlanVO = service.get(id);
			double value = StringUtil.doubleValue(data, 0);
			if (value >= 0) {
				inspectionPlanVO.setPredictCheckdays(value);//预计检查天数
				service.update(inspectionPlanVO);
			}
		}else if(objIdValue > 0 && objIdValue == 5 && StringUtil.isNotEmpty(data) 
				 && id > 0){
			InspectionPlanVO inspectionPlanVO = service.get(id);
			inspectionPlanVO.setRemark(new String(data.getBytes("ISO8859-1"),"UTF-8"));//备注
			service.update(inspectionPlanVO);
		}else if(objIdValue > 0 && objIdValue == 6 && StringUtil.isNotEmpty(data) 
				 && id > 0){
			int outControlUserId = Integer.parseInt(data);
			InspectionPlanVO inspectionPlanVO = service.get(id);//更新经销商信息的外控专员
			inspectionPlanVO.setOutControlUserId(outControlUserId);
			service.update(inspectionPlanVO);
			if (StringUtil.isNotEmpty(planCodeInfo)) {
				InspectionPlanInfoQueryBean amountInfo = iservice.getDealerInfo(planCodeInfo);
				if (null != amountInfo) {
					String outControlUserAmount = amountInfo.getOutControlUserAmount();
					if (StringUtil.isNotEmpty(outControlUserAmount)) {
						num = Double.parseDouble(outControlUserAmount);
					}
				}
			}
		}else if(objIdValue > 0 && objIdValue == 7 && StringUtil.isNotEmpty(data) 
				 && id > 0){
			InspectionPlanVO inspectionPlanVO = service.get(id);//更新经销商信息的陪同人员信息
			inspectionPlanVO.setEscortUserInfo(new String(data.getBytes("ISO8859-1"),"UTF-8"));
			service.update(inspectionPlanVO);
		}
		super.makeJSONObject(response, callback,num);
		return null;
	}
}
