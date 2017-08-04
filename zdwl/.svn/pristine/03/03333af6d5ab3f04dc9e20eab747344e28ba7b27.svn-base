package com.zd.csms.util.tagutil;

import java.util.ArrayList;
import java.util.List;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;

public class DealerUtil {
	
	DealerByIdJsonAction dja = new DealerByIdJsonAction();

	public String Dealer(int id,String type,String dn){
		String name = "";
		
		DealerService ds = new DealerService();
		BrandService bs = new BrandService();
		DraftService draftservice = new DraftService();
		SuperviseImportService siservice = new SuperviseImportService();
		List<SuperviseImportVO> sumList = new ArrayList<SuperviseImportVO>();
		try {
			DealerVO dvo = ds.loadDealerById(id);
			if(dvo != null){
				if(type.equals("kc")){
					DraftQueryVO dq = new DraftQueryVO();
					dq.setDistribid(dvo.getId());
					List<DraftVO> dList = draftservice.searchDraftList(dq);
					if(dList != null && dList.size()>0){
						for(DraftVO draftvo : dList){
							SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
							siquery.setDraft_num(draftvo.getDraft_num());
							List<SuperviseImportVO> sList = siservice.searchSuperviseImportList(siquery);
							if(sList != null && sList.size()>0){
								for(SuperviseImportVO svo : sList){
									sumList.add(svo);
								}
							}
							
						}
					}
					
					name = sumList.size()+"";
				}else if(type.equals("hp")){
					DraftQueryVO dq = new DraftQueryVO();
					dq.setDistribid(dvo.getId());
					List<DraftVO> dList = draftservice.searchDraftList(dq);
					name = dList.size()+"";
				}else if(type.equals("jrjgs")){
					name = ds.getBankNameByDealerId(id);
				}else if(type.equals("brands")){
					DealerVO dealer = ds.loadDealerById(id);
					if(dealer != null){
						int brandid = dealer.getBrandId();
						BrandVO brand = bs.loadBrandById(brandid);
						name = brand.getName();
					}
					
				}
			}
			
			DraftQueryVO dq = new DraftQueryVO();
			dq.setDraft_num(dn);
			List<DraftVO> dList = draftservice.searchDraftList(dq);
			if(dList != null && dList.size() > 0){
				DraftVO draft = dList.get(0);
				int dealerid = draft.getDistribid();
				DealerQueryBean dealer = dja.getDealer(dealerid);
				
				
				if(dealer != null){
					if(type.equals("jxs")){
						name = dealer.getDealerName();
					}else if(type.equals("jrjg")){
						name = dealer.getBankName();
					}else if(type.equals("brand")){
						name = dealer.getBrand();
					}
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
