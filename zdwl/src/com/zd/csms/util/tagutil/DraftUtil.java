package com.zd.csms.util.tagutil;

import java.math.BigDecimal;
import java.util.List;

import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.StringUtil;

public class DraftUtil {

	private DealerService dealerService = new DealerService();
	private DraftService service = new DraftService();
	
	public String Draft(int id,String type,String num){
		String name = "";
		
		try {
			if(id > 0){
				DraftVO draft = service.loadDraftById(id);
				
				if(draft != null){
					//敞口金额
					if(type.equals("ckje")){
						//开票金额
						String billing_money = draft.getBilling_money();
						int billing = Integer.parseInt(billing_money);
						//回款金额
						String payment_money = draft.getPayment_money();
						int payment = Integer.parseInt(payment_money);
						
						name = (billing - payment)+"";
						//库存台数
					}else if(type.equals("kcts")){
						String draft_num = "";
						if(!StringUtil.isEmpty(draft.getDraft_num())){
							draft_num = draft.getDraft_num();
						}
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft_num);
						siquery.setState(2);
						//入库台数
						List<SuperviseImportVO> rkList = siservice.searchSuperviseImportList(siquery);
						int rk = rkList.size();
						
						//出库台数
						siquery.setState(3);
						List<SuperviseImportVO> ckList = siservice.searchSuperviseImportList(siquery);
						int ck = ckList.size();
						
						//在途台数
						siquery.setState(1);
						List<SuperviseImportVO> ztList = siservice.searchSuperviseImportList(siquery);
						int zt = ztList.size();
						
						name = (rk-ck-zt)+"";
						//库存金额
					}else if(type.equals("kcje")){
						String draft_num = draft.getDraft_num();
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft_num);
						siquery.setStates("2,4");
						List<SuperviseImportVO> list = siservice.searchSuperviseImportList(siquery);
						int sum = 0;
						for(SuperviseImportVO si : list){
							if(!StringUtil.isEmpty(si.getMoney())){
								int price = Integer.parseInt(si.getMoney());
								sum += price;
							}
						}
						name = sum+"";
						//未押车金额
					}else if(type.equals("wycje")){
						//开票金额
						String billing_money = draft.getBilling_money();
						int billing = Integer.parseInt(billing_money);
						int yycsum = 0;
						//已押车金额
						BigDecimal sum = new BigDecimal(0);
						
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft.getDraft_num());
						List<SuperviseImportVO> siList = siservice.searchSuperviseImportList(siquery);
						if(siList != null && siList.size()>0){
							for(SuperviseImportVO sivo : siList){
								BigDecimal money = new BigDecimal(sivo.getMoney());
								sum = sum.add(money);
							}
						}
						yycsum = sum.intValue();
						
						name = (billing - yycsum)+"";
						//已押车金额
					}else if(type.equals("yycje")){
						
						BigDecimal sum = new BigDecimal(0);
						
						SuperviseImportService siservice = new SuperviseImportService();
						SuperviseImportQueryVO siquery = new SuperviseImportQueryVO();
						siquery.setDraft_num(draft.getDraft_num());
						List<SuperviseImportVO> siList = siservice.searchSuperviseImportList(siquery);
						if(siList != null && siList.size()>0){
							for(SuperviseImportVO sivo : siList){
								BigDecimal money = new BigDecimal(sivo.getMoney());
								sum = sum.add(money);
							}
						}
						
						
						name = sum.toString();
					}
					
					if(type.equals("jxs")){
						DealerVO dealer = dealerService.get(draft.getDistribid());
						if(dealer != null){
							name = dealer.getDealerName();
						}
					}if(type.equals("jrjg")){
						name = dealerService.getBankNameByDealerId(draft.getDistribid());
					}
					
					
				}
				
			}
			if(!StringUtil.isEmpty(num)){
				DraftService service = new DraftService();
				DraftQueryVO dquery = new DraftQueryVO();
				dquery.setDraft_num(num);
				List<DraftVO> dList = service.searchDraftList(dquery);
				if(dList != null && dList.size()>0){
					DraftVO draft = dList.get(0);
					//开票金额
					if(type.equals("billing_money")){
						name = draft.getBilling_money();
					//开票日
					}else if(type.equals("billing_date")){
						if(draft.getBilling_date()!=null)
							name = draft.getBilling_date().toString();
						else
							name="";
					//到期日
					}else if(type.equals("due_date")){
						if(draft.getDue_date()!=null)
							name = draft.getDue_date().toString();
						else 
							name="";
					}
					
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
