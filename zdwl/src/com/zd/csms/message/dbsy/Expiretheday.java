package com.zd.csms.message.dbsy;

import java.util.Date;
import java.util.List;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.tools.DateUtil;

/**
 * 汇票到期当天提醒
 * @author ly
 *
 */
public class Expiretheday {

	public static void expiretheday(){
		DraftService ds = new DraftService();
		try {
			DraftQueryVO dq = new DraftQueryVO();
			dq.setState(2);
			List<DraftVO> dList = ds.searchDraftList(dq);
			if(dList != null && dList.size()>0){
				for(DraftVO dvo : dList){
					int day = DateUtil.daysBetween(new Date(),dvo.getDue_date());
					if(day == 0){
						MessageUtil.sendMsg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+""}, "票号为"+dvo.getDraft_num()+"的汇票当天到期",
								"/message/ExpirethedayMessage.do?method=expiretheday&draftnum="+dvo.getDraft_num(), 1, MessageTypeContant.EXPIRETHEDAY.getCode(), dvo.getCreateuserid());
					}
				}
			}
		} catch (Exception e) {
		}
		
	}
}
