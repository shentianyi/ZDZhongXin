package com.zd.csms.message.dbsy;

import java.util.List;

import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.supervisory.querybean.CarOperationBean;
import com.zd.csms.supervisory.service.CarOperationService;

/**
 * 连续3天无业务
 * @author ly
 *
 */
public class Nowork3day {

	public static void nowork3day(){
		CarOperationService coservice = new CarOperationService();
		DealerSupervisorService ds = new DealerSupervisorService();
		DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
		DealerService dealerservice = new DealerService();
		
		try {
			List<CarOperationBean> theList = coservice.searchtheday(0);
			if(theList != null && theList.size()>0){
				for(CarOperationBean the : theList){
					if(the.getSum() == 0){
						List<CarOperationBean> zuoList = coservice.searchzuoday(the.getUserid(),0);
						if(zuoList != null && zuoList.size()>0){
							for(CarOperationBean zuo : zuoList){
								if(zuo.getSum() == 0){
									List<CarOperationBean> qianList = coservice.searchqianday(zuo.getUserid(),0);
									if(qianList != null && qianList.size()>0){
										for(CarOperationBean qian : qianList){
											if(qian.getSum() == 0){
												int superviseid = qian.getUserid();
												dsquery.setSupervisorId(superviseid);
												List<DealerSupervisorVO> dsList = ds.searchDealerSupervisorList(dsquery);
												if(dsList != null && dsList.size()>0){
													DealerSupervisorVO dsvo = dsList.get(0);
													DealerVO dealer = dealerservice.loadDealerById(dsvo.getDealerId());
													MessageUtil.sendMsg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+""},  dealer.getDealerName()+"连续三天无业务",
															"/message/Nowork3dayMessage.do?method=nowork3day&dealerid="+dsvo.getDealerId(), 1, MessageTypeContant.NOWORK3DAY.getCode(), qian.getUserid());
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		
		
	}
}
