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
import com.zd.csms.supervisory.querybean.CarOperationBean;
import com.zd.csms.supervisory.service.CarOperationService;

public class Continuity3out {

	public static void continuity3out(){
		
		CarOperationService coservice = new CarOperationService();
		DealerSupervisorService ds = new DealerSupervisorService();
		DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
		DealerService dealerservice = new DealerService();
		
		try {
			List<CarOperationBean> theList = coservice.searchtheday(3);
			if(theList != null && theList.size()>0){
				for(CarOperationBean the : theList){
					if(the.getSum() > 5){
						List<CarOperationBean> zuoList = coservice.searchzuoday(the.getUserid(),3);
						if(zuoList != null && zuoList.size()>0){
							for(CarOperationBean zuo : zuoList){
								if(zuo.getSum() > 5){
									List<CarOperationBean> qianList = coservice.searchqianday(zuo.getUserid(),3);
									if(qianList != null && qianList.size()>0){
										for(CarOperationBean qian : qianList){
											if(qian.getSum() > 5){
												int superviseid = qian.getUserid();
												dsquery.setSupervisorId(superviseid);
												List<DealerSupervisorVO> dsList = ds.searchDealerSupervisorList(dsquery);
												if(dsList != null && dsList.size()>0){
													DealerSupervisorVO dsvo = dsList.get(0);
													DealerVO dealer = dealerservice.loadDealerById(dsvo.getDealerId());
													MessageUtil.sendMsg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+""}, dealer.getDealerName()+"连续三天出库5台以上",
															"/message/Continuity3outMessage.do?method=continuity3out&dealerid="+dsvo.getDealerId(), 1, MessageTypeContant.CONTINUITY3OUT.getCode(), qian.getUserid());
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
