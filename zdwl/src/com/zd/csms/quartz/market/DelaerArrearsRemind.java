package com.zd.csms.quartz.market;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.WarningTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;

/**
 * 经销商定时扣费
 * @author licheng
 *
 */
public class DelaerArrearsRemind {
	
	public void run(){
		DealerService service = new DealerService();
		IUserRoleDAO ur = RbacDAOFactory.getUserRoleDAO();
		List<String> userIds = new ArrayList<String>(Arrays.asList(ur.findUsingUserIdByRole(RoleConstants.MARKET_AMALDAR.getCode()+"",RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+"")));
		
		List<DealerVO> list = service.findListByArrearsRemind();
		if(list!=null&&list.size()>0)
			for (DealerVO dealer : list){
				MessageUtil.addMsg(dealer.getCreateUserid(), "经销商"+dealer.getDealerName()+"监管费到期！"
						, "/market/payment.do?method=findList", 2, WarningTypeContant.PAYMENT.getCode(),dealer.getCreateUserid());
				for (String id : userIds) {
					MessageUtil.addMsg(Integer.parseInt(id), "经销商"+dealer.getDealerName()+"监管费到期！"
							, "/market/payment.do?method=findList", 2, WarningTypeContant.PAYMENT.getCode(),dealer.getCreateUserid());
				}
			}	
	}
}
