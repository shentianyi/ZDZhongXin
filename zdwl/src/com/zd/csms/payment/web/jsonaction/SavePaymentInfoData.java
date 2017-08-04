package com.zd.csms.payment.web.jsonaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.payment.model.PaymentInfoVO;
import com.zd.csms.payment.service.PaymentInfoService;
import com.zd.csms.planandreport.service.InspectionPlanService;
import com.zd.tools.StringUtil;

public class SavePaymentInfoData extends JSONAction{
	InspectionPlanService service = new InspectionPlanService();
	private PaymentInfoService pService = new PaymentInfoService();
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int paymentInfoId = 0;
		int objectId = 0;
		double num = 0;
		String callback = request.getParameter("callback");
		String objid = request.getParameter("objid");//1: 审批不通过 2:替岗费
		String payInfoId = request.getParameter("id");//薪酬信息记录Id
		String data = request.getParameter("data");//输入的值
		if (StringUtil.isNotEmpty(payInfoId)) {
			paymentInfoId = Integer.parseInt(payInfoId);//薪酬信息记录Id
		}
		if (StringUtil.isNotEmpty(objid)) {
			objectId = Integer.parseInt(objid);//更新类型Id
		}
		
		PaymentInfoVO paymentInfoVO = pService.get(paymentInfoId);
		if (null != paymentInfoVO) {
			if (objectId == 1) {//1: 审批不通过
				paymentInfoVO.setStatus(ApprovalContant.APPROVAL_NOPASS.getCode());
				
			}else if(objectId == 2 && StringUtil.isNotEmpty(data)){//2:替岗费
				paymentInfoVO.setReplaceCost(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 3 && StringUtil.isNotEmpty(data)){//3:餐补
				paymentInfoVO.setMealSubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 4 && StringUtil.isNotEmpty(data)){//4:话补
				paymentInfoVO.setPhoneSubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 5 && StringUtil.isNotEmpty(data)){//5:交通补
				paymentInfoVO.setTrafficSubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 6 && StringUtil.isNotEmpty(data)){//6:房补
				paymentInfoVO.setHouseSubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 7 && StringUtil.isNotEmpty(data)){//7:多行多店补助
				paymentInfoVO.setManySubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 8 && StringUtil.isNotEmpty(data)){//8:远疆补助
				paymentInfoVO.setFarSubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 9 && StringUtil.isNotEmpty(data)){//9:全勤
				paymentInfoVO.setFullTimeSubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 10 && StringUtil.isNotEmpty(data)){//10:安家费
				paymentInfoVO.setSettleCost(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 11 && StringUtil.isNotEmpty(data)){//11:异常补助
				paymentInfoVO.setElseSubsidy(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 12 && StringUtil.isNotEmpty(data)){//12:补款一
				paymentInfoVO.setSubsidyOne(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 13 && StringUtil.isNotEmpty(data)){//13:补款二
				paymentInfoVO.setSubsidyTwo(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 14 && StringUtil.isNotEmpty(data)){//14:扣款一
				paymentInfoVO.setDeductOne(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 15 && StringUtil.isNotEmpty(data)){//15:扣款二
				paymentInfoVO.setDeductTwo(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 16 && StringUtil.isNotEmpty(data)){//16:合计补款一
				paymentInfoVO.setSubsidyOneTotal(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 17 && StringUtil.isNotEmpty(data)){//17:合计补款二
				paymentInfoVO.setSubsidyTwoTotal(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 18 && StringUtil.isNotEmpty(data)){//18:合计扣款一
				paymentInfoVO.setDeductOneTotal(StringUtil.doubleValue(data, 0));
				
			}else if(objectId == 19 && StringUtil.isNotEmpty(data)){//19:合计扣款二
				paymentInfoVO.setDeductTwoTotal(StringUtil.doubleValue(data, 0));
				
			}else if(null != data && objectId == 20){//20:备注
				paymentInfoVO.setRemark(new String(data.getBytes("ISO8859-1"),"UTF-8"));
				
			}
			if (objectId > 0) {
				pService.update(paymentInfoVO);
			}
		}
		
		super.makeJSONObject(response, callback,num);
		return null;
	}
	
}
