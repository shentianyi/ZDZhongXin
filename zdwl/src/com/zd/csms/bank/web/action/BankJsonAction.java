package com.zd.csms.bank.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.bank.web.form.BankForm;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.BrandGroupService;

public class BankJsonAction extends JSONAction{
	BankService bankService = new BankService();
	
	/**
	 * 根据parentId查询子集合
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String callback = request.getParameter("callback");
		BankForm bankForm = (BankForm) form;
		Integer id = bankForm.getBankQuery().getId();
		List<BankVO> list = new ArrayList<BankVO>();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(id == null){
			id = -1;
		}
		
			if(user.getClient_type() == 15 && id == -1){
				BrandGroupService bgService = new BrandGroupService();
				DealerService dealerService = new DealerService();
				DealerQueryVO query = new DealerQueryVO();
				List<Integer> dealerList = bgService.findDealerIdsByUserVOId(user.getId());
			
				query.setIds(bankService.convertIntegerArray(dealerList));
				
				List<DealerBankVO> banks = dealerService.findBankListByIds(query);
				boolean flag = true;
				for (DealerBankVO vo : banks) {
					BankVO bankVO = bankService.getTopBank(vo.getBankId());
					flag = true;
					for (BankVO temp : list) {
						if(temp.getId() == bankVO.getId()){
							flag = false;
						}
					}
					
					if(flag){
						list.add(bankVO);
					}
				}
			}else if(user.getClient_type() == 13 && id == -1){
				id = user.getClient_id();
				BankVO bankVO = bankService.getTopBank(id);
				list.add(bankVO);
			} else{
				list =  bankService.findChildListById(id);
			}
		super.makeJSONObject(response, callback, list);
		return null;
	}
	
	
	

 }
