package com.zd.csms.business.web.excel;

import java.text.SimpleDateFormat;

import com.zd.csms.business.model.ComplaintInfoVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.rbac.constants.ComplaintObjConstants;
import com.zd.csms.rbac.constants.PhoneTypeConstants;
import com.zd.csms.rbac.constants.StatusConstants;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IImportRowMapper;

public class ComplaintRowMapper implements IImportRowMapper {

	private PhoneTypeConstants[] values = PhoneTypeConstants.values();
	private DealerService dService = new DealerService();
	private static int i = 1;
	@Override
	public String[] exportRow(Object obj) {
		
		ComplaintInfoVO cvo = (ComplaintInfoVO)obj;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String createDate = "";
		String treatmentProcessTime = "";
		String solutionProcessTime = "";
		String trackProcessTime = (null != cvo.getTrackProcessTime() ? format.format(cvo.getTrackProcessTime()):"");
		if(null != cvo.getCreateDate()){
			createDate = format.format(cvo.getCreateDate());
		}
		if (null != cvo.getTreatmentProcessTime()) {
			treatmentProcessTime = format.format(cvo.getTreatmentProcessTime());
		}
		if (null != cvo.getSolutionProcessTime()) {
			solutionProcessTime = format.format(cvo.getSolutionProcessTime());
		}
		
		String state = "";
		String complaintObjName = "";
		int complaintObjId = cvo.getComplaintObjId();
		if (StringUtil.isNotEmpty(cvo.getStatus())) {
			if(cvo.getStatus().equals(StatusConstants.UNCOMMIT.getCode())){
				state = StatusConstants.UNCOMMIT.getName();
			}else if(cvo.getStatus().equals(StatusConstants.ALREADYSEND.getCode())){
				state = StatusConstants.ALREADYSEND.getName();
			}else if(cvo.getStatus().equals(StatusConstants.CORRECTION.getCode())){
				state = StatusConstants.CORRECTION.getName();
			}else if(cvo.getStatus().equals(StatusConstants.SUCCESS.getCode())){
				state = StatusConstants.SUCCESS.getName();
			}
		}
		if (complaintObjId == 1) {
			complaintObjName = ComplaintObjConstants.DEALER.getName();
		}else if (complaintObjId == 2) {
			complaintObjName = ComplaintObjConstants.FINANCIAL.getName();
		}else if (complaintObjId == 3) {
			complaintObjName = ComplaintObjConstants.SUPERVISE.getName();
		}
		
		StringBuffer sb = new StringBuffer("");
		if (StringUtil.isNotEmpty(cvo.getPhoneType())) {
			String[] types = cvo.getPhoneType().split(",");
			int typeNum = -1;
			for (String type : types) {
				for (PhoneTypeConstants phoneTypeConstants : values) {
					if (StringUtil.isNotEmpty(type)) {
						typeNum = Integer.parseInt(type);
						if (typeNum == phoneTypeConstants.getCode()) {
							sb.append(phoneTypeConstants.getName() + " , ");
						}
					}
				}
			}
		}
		DealerVO dealerVO = dService.get(cvo.getStoreId());
		return new String[]{i++ +"",createDate,cvo.getCreateTime(),cvo.getCreateUserName(),cvo.getCreateUserDept(),sb.substring(0, sb.length()-2),complaintObjName,
				cvo.getComplainantName(),cvo.getComplainantPosition(),cvo.getBrandName(),cvo.getJobNum(),dealerVO != null?dealerVO.getDealerName():"",cvo.getBankName(),cvo.getContentText(),
				cvo.getTreatmentOpinion(),cvo.getTreatmentProcessName(),treatmentProcessTime,
				cvo.getSolution(),cvo.getSolutionOperatorName(),solutionProcessTime,
				cvo.getTrackCondition(),cvo.getTrackOperatorName(),trackProcessTime,
				state};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"序号","创建日期","创建时间","记录人","所属部门","来电类型","来电人类型",
				"来电人","职务","品牌","工号","所在店","所在行","投诉内容",
				"处理意见","处理意见经办人","处理意见填写时间",
				"解决方案","解决方案经办人","解决方案填写时间",
				"跟踪情况","跟踪情况经办人","跟踪情况填写时间",
				"状态"};
	}

	@Override
	public Object importRow(String[] values) {
		return null;
	}

}
