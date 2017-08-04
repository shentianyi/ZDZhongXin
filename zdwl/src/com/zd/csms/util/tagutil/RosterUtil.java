package com.zd.csms.util.tagutil;

import java.util.List;

import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;

public class RosterUtil {

	public String roster(int id, String type){
		
		String name = "";
		
		RosterService rservice = new RosterService();
		DealerSupervisorService dsservice = new DealerSupervisorService();
		DealerService dservice = new DealerService();
		//工号
		if(type.equals("gh")){
			RosterQueryVO rqvo = new RosterQueryVO();
			rqvo.setSupervisorId(id);
			List<RosterVO> rList = rservice.searchRosterList(rqvo);
			if(rList != null && rList.size()>0){
				RosterVO rvo = rList.get(0);
				name = rvo.getStaffNo()+"";
			}
		//监管员
		}else if(type.equals("jgy")){
			SupervisoryService sservice = new SupervisoryService();
			SupervisorBaseInfoVO svo = sservice.getBaseInfo(id);
			if(svo != null){
				name = svo.getName();
			}
		//经销商
		}else if(type.equals("jxs")){
			DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
			dsquery.setSupervisorId(id);
			List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
			if(dsList != null && dsList.size()>0){
				DealerSupervisorVO dsvo = dsList.get(0);
				int dealerid = dsvo.getDealerId();
				DealerVO dvo;
				try {
					dvo = dservice.loadDealerById(dealerid);
					if(dvo != null){
						name = dvo.getDealerName();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		//金融机构
		}else if(type.equals("jrjg")){
			DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
			dsquery.setSupervisorId(id);
			List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
			if(dsList != null && dsList.size()>0){
				DealerSupervisorVO dsvo = dsList.get(0);
				int dealerid = dsvo.getDealerId();
				try {
					name = dservice.getBankNameByDealerId(dealerid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return name;
	}
}
