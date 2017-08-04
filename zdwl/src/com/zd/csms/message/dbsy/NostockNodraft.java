package com.zd.csms.message.dbsy;

import java.util.List;

import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.service.RoleService;

/**
 * 0库存0汇票
 * @author ly
 *
 */
public class NostockNodraft {

	public static void nostocknodraft(){
		DealerService dealerservice = new DealerService();
		try {
			List<DealerVO> dealerList = dealerservice.searchDealerqcList();
			for(DealerVO vo : dealerList){
				MessageUtil.sendMsg(
						new String[]{""+RoleConstants.MARKET_COMMISSIONER.getCode(),""+RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()
				,""+RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),""+RoleConstants.BUSINESS_COMMISSIONER.getCode()}, 
						vo.getDealerName()+"0库存0汇票",
						"/message/NostockNodraft.do?method=nostocknodraft&dealerid="+vo.getId(), 1, MessageTypeContant.NOSTOCKNODRAFT.getCode(), vo.getCreateUserid());
			}
		} catch (Exception e) {
		}
		
	}
}
