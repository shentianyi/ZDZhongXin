package com.zd.csms.message.dbsy;

import java.math.BigDecimal;
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
import com.zd.tools.StringUtil;

/**
 * 开票30天汇票未押满
 * @author ly
 *
 */
public class Billing30nofull {

	public static void billing30nofull(){
		
		DraftService ds = new DraftService();
		try {
			BigDecimal sum = new BigDecimal(0);
			DraftQueryVO dq = new DraftQueryVO();
			dq.setState(2);
			List<DraftVO> dList = ds.searchDraftList(dq);
			if(dList != null && dList.size()>0){
				for(DraftVO dvo : dList){
					int day = DateUtil.daysBetween(dvo.getBilling_date(), new Date());
					if(day > 30){
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(dvo.getDraft_num());
						List<SuperviseImportVO> siList = siservice.searchSuperviseImportList(siquery);
						if(siList != null && siList.size()>0){
							for(SuperviseImportVO sivo : siList){
								BigDecimal money = new BigDecimal(sivo.getMoney());
								sum = sum.add(money);
							}
						}
						if(!StringUtil.isEmpty(dvo.getBilling_money())){
							int bm = Integer.parseInt(dvo.getBilling_money());
							int mm = sum.intValue();
							if(mm >= bm){
								MessageUtil.sendMsg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",RoleConstants.BANK_APPROVE.getCode()+""}, "票号为"+dvo.getDraft_num()+"的汇票开票30天汇票未押满",
										"/message/Billing30nofullMessage.do?method=billing30nofull&draftnum="+dvo.getDraft_num(), 1, MessageTypeContant.BILLING30NOFULL.getCode(), dvo.getCreateuserid());
							}
						}
					}
				}
			}
			
			
		} catch (Exception e) {
		}
	}
}
