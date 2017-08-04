package com.zd.csms.message.dbsy;

import java.util.Date;
import java.util.List;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.DateUtil;

public class Move30day {

	public static void move30day(){
		
		SuperviseImportService siservice = new SuperviseImportService();
		DraftService ds = new DraftService();
		DealerService deservice = new DealerService();
		
		try {
			SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
			siquery.setState(4);
			List<SuperviseImportVO> siList = siservice.searchSuperviseImportList(siquery);
			if(siList != null && siList.size()>0){
				for(SuperviseImportVO sivo : siList){
					int day = DateUtil.daysBetween(sivo.getMovetime(), new Date());
					if(day > 30){
						String draftnum = sivo.getDraft_num();
						DraftQueryVO dquery = new DraftQueryVO();
						dquery.setDraft_num(draftnum);
						List<DraftVO> dList = ds.searchDraftList(dquery);
						if(dList != null && dList.size()>0){
							DraftVO draft = dList.get(0);
							DealerVO dealer = deservice.loadDealerById(draft.getDistribid());
							MessageUtil.sendMsg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",RoleConstants.BANK_APPROVE.getCode()+""}, dealer.getDealerName()+"移动车辆超过30天未赎车或移回",
									"/message/Move30dayMessage.do?method=move30day&dealerid="+dealer.getId(), 1, MessageTypeContant.MOVE30DAY.getCode(), sivo.getCreateuserid());
						}
					}
				}
			}
			
			
		} catch (Exception e) {
		}
		
		
	}
}
