package com.zd.csms.message.dbsy;

import java.util.Date;
import java.util.List;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.DateUtil;

/**
 * 开票15个工作日未到车
 * @author ly
 *
 */
public class Billing15nocar {

	public static void billing15(){
		
		DraftService ds = new DraftService();
		SuperviseImportService sis = new SuperviseImportService();
		
		try {
			DraftQueryVO dq = new DraftQueryVO();
			dq.setState(2);
			List<DraftVO> dList = ds.searchDraftList(dq);
			if(dList != null && dList.size() > 0){
				for(DraftVO dvo : dList){
					SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
					siquery.setDraft_num(dvo.getDraft_num());
					siquery.setStates("2,3,4");
					List<SuperviseImportVO> sList = sis.searchSuperviseImportList(siquery);
					if(sList == null || sList.size() == 0){
						int day = DateUtil.daysBetween(dvo.getBilling_date(), new Date());
						if(day > 15){
							MessageUtil.sendMsg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",RoleConstants.BANK_APPROVE.getCode()+""}, "票号为"+dvo.getDraft_num()+"的汇票开票15个工作日未到车",
									"/message/BillingNoCarMessage.do?method=billno15&draftnum="+dvo.getDraft_num(), 1, MessageTypeContant.BILLING15NOCAR.getCode(), dvo.getCreateuserid());
						}
					}
				}
			}
			
		} catch (Exception e) {
		}
	}
	
}
