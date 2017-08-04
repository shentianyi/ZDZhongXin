package com.zd.csms.util.tagutil;

import java.util.List;

import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;

public class DealerNameUtil {

	public String dealerName(int id, String type){
		
		String name="";
		
		DealerService ds = new DealerService();
		try {
			if(type.equals("null")){
				if(id > 0){
					DealerSupervisorService dsservice = new DealerSupervisorService();
					DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
					dsquery.setSupervisorId(id);
					List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
					if(dsList != null && dsList.size()>0){
						DealerSupervisorVO dsvo = dsList.get(0);
						int dealerId = dsvo.getDealerId();
						DealerVO dvo = ds.loadDealerById(dealerId);
						if(dvo != null){
							name = dvo.getDealerName();
						}
					}
				}
			}else{
				if(id > 0){
					DealerVO dvo = ds.loadDealerById(id);
					if(dvo != null){
						name = dvo.getDealerName();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
