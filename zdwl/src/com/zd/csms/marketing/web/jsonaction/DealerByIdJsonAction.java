package com.zd.csms.marketing.web.jsonaction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.zd.core.JSONAction;
import com.zd.csms.bank.model.BankManagerVO;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankManagerService;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisory.contants.SuperVisoryStatusContants;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.StringUtil;

public class DealerByIdJsonAction extends JSONAction{
	private DealerService service = new DealerService();
	private RegionService regionService=new RegionService();
	private BankService bankService = new BankService();
	private BankManagerService bankManagerService = new BankManagerService();
	private BrandService brandService = new BrandService();
	private SuperviseImportService superviseImportService=new SuperviseImportService();
	
	public ActionForward execute(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String callback = request.getParameter("callback");
		int id = Integer.parseInt(request.getParameter("id"));
		
		DealerQueryBean queryBean = getDealer(id);
		queryBean.setSuperviseMoney(service.getMoneyByClientId(queryBean.getSuperviseMoney(), UserSessionUtil.getUserSession(request).getUser().getClient_type()));
		String findRep = request.getParameter("findRep");//是否查询对应的监管员
		
		if(StringUtil.isNotEmpty(findRep)){
			//根据经销商ID加载交付人列表
			queryBean.setRepList(OptionUtil.getDealerSupervisor(id));
		}
		
		super.makeJSONObject(response, callback, queryBean);
		return null;
	}

	public DealerQueryBean getDealer( int id) throws Exception {
		DealerQueryBean queryBean = new DealerQueryBean();
		DealerVO dealer = service.get(id);
		if(dealer==null){
			dealer=new DealerVO();
		}
		BeanUtils.copyProperties(dealer, queryBean);
		
		DealerQueryVO query = new DealerQueryVO();
		query.setIds(new Integer[]{dealer.getId()});
		List<DealerBankVO> banks = service.findBankListByIds(query);
		
		StringBuffer bankName=new StringBuffer();
		StringBuffer bankId=new StringBuffer();
		bankId.append(",");
		if(banks!=null){
			for (DealerBankVO dealerBankVO : banks) {
				BankVO bank = bankService.get(dealerBankVO.getBankId());
				if(bank!=null){
					bankName.append(bank.getBankFullName()+",");
					bankId.append(bank.getId()+",");
				}
			}
			if(bankName.length()>0)
				bankName.deleteCharAt(bankName.length()-1);
		}
		queryBean.setBankId(bankId.toString());
		int managerId = dealer.getBankManagerId();
		BankManagerVO bankManager = bankManagerService.get(managerId);
		queryBean.setBankName(bankName.toString());
		if(bankManager!=null){
			queryBean.setBankContact(bankManager.getManager());
			queryBean.setBankPhone(bankManager.getManagerPhone());
		}
		queryBean.setProvince(regionService.getNameById(StringUtil.intValue(queryBean.getProvince(), 0)));
		queryBean.setCity(regionService.getNameById(StringUtil.intValue(queryBean.getCity(), 0)));
		
		BrandVO brand = brandService.loadBrandById(dealer.getBrandId());
		if(brand!=null){
			queryBean.setBrand(brand.getName());
		}
		
		try {
			if(StringUtil.isNotEmpty(queryBean.getBindInfo())){
				queryBean.setBindId(queryBean.getBindInfo());
				queryBean.setBindInfo(service.getBindNameById(queryBean.getBindInfo()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String bindBankInfo="";
			if(StringUtil.isNotEmpty(queryBean.getBindId())){
				String[] bindMerchantIds=queryBean.getBindId().split(",");
				if(bindMerchantIds!=null && bindMerchantIds.length>0){
					for(String bindMerchantId:bindMerchantIds){
						DealerVO bandMerchant = service.get(Integer.parseInt(bindMerchantId));
						if(bandMerchant!=null){
							brand = brandService.loadBrandById(bandMerchant.getBrandId());
						}
						if(brand!=null){
							bindBankInfo=bindBankInfo+"  "+brand.getName();
						}
					}
				}
			}
			queryBean.setBindBank(bindBankInfo);
			
			//在库车辆:2,销售未还款车辆:3,私自移动车辆:6,私自移动车辆:5
			int[] status=new int[]{SuperVisoryStatusContants.IN_STORE.getCode(),
					SuperVisoryStatusContants.SALE_NO_PAY.getCode(),
					SuperVisoryStatusContants.PRIVATE_MOVE.getCode(),
					SuperVisoryStatusContants.PRIVATE_SALE.getCode()};
			int totalStock=superviseImportService.carCountByDealerId(queryBean.getId(), status);
			//异常数据中的总库存（库存车不包括出库和在途车辆，其余全部包括。）
			queryBean.setTotalStock(totalStock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryBean;
	}
	

}
