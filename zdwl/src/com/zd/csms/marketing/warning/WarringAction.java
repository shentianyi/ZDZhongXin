package com.zd.csms.marketing.warning;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.marketing.contants.PayModeContant;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.model.AgreementSendQueryVO;
import com.zd.csms.marketing.model.AgreementSendVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.AgreementBackService;
import com.zd.csms.marketing.service.AgreementSendService;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.service.DealerService;

public class WarringAction extends ActionSupport{
	private AgreementSendService sendService = new AgreementSendService();
	private DealerService dealerService = new DealerService();
	private AgreementBackService bankService = new AgreementBackService();
	private BusinessTransferService btService = new BusinessTransferService();
	
	/**
	 * 进驻7天未交监管费提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward inNotPay(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		DealerVO dealer = dealerService.get(Integer.parseInt(id));
		String payMode= "";
		double money = Double.parseDouble(dealer.getSuperviseMoney());
		if(dealer.getPayMode()==PayModeContant.PAYMODE_MONTH.getCode()){
			payMode=PayModeContant.PAYMODE_MONTH.getName();
			money = money/12;
			
		}else if(dealer.getPayMode()==PayModeContant.PAYMODE_QUARTER.getCode()){
			payMode=PayModeContant.PAYMODE_QUARTER.getName();
			money = money/4;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_SIXMONTH.getCode()){
			payMode=PayModeContant.PAYMODE_SIXMONTH.getName();
			money = money/2;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_YEAR.getCode()){
			payMode=PayModeContant.PAYMODE_YEAR.getName();
		}
		
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		AgreementSendQueryVO sendQuery = new AgreementSendQueryVO();
		sendQuery.setDistribid(dealer.getId());
		List<AgreementSendVO> sendList = sendService.searchAgreementSendList(sendQuery);
		if(sendList!=null&&sendList.size()>0){
			request.setAttribute("agreementNum", sendList.get(0).getAgreement_num());
		}
		
		request.setAttribute("bankName", bankName);
		request.setAttribute("dealer", dealer);
		request.setAttribute("money",money);
		return mapping.findForward("inNotPay");
	}
	
	
	/**
	 * 监管协议到期前30天提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward agreementExpires30(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		AgreementBackVO back = bankService.loadAgreementBackById(id);
		DealerVO dealer = dealerService.get(back.getDistribid());
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		
		request.setAttribute("back", back);
		request.setAttribute("dealer", dealer);
		request.setAttribute("bankName", bankName);
		
		return mapping.findForward("agreementExpires30");
	}
	
	/**
	 * 监管协议到期前7天提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward agreementExpires7(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		AgreementBackVO back = bankService.loadAgreementBackById(id);
		DealerVO dealer = dealerService.get(back.getDistribid());
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		
		request.setAttribute("back", back);
		request.setAttribute("dealer", dealer);
		request.setAttribute("bankName", bankName);
		
		return mapping.findForward("agreementExpires7");
	}
	
	
	/**
	 * 协议逾期未回收提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward agreementOverdue(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		AgreementSendVO send = sendService.loadAgreementSendById(id);
		DealerVO dealer = dealerService.get(send.getDistribid());
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		request.setAttribute("send", send);
		request.setAttribute("dealer", dealer);
		request.setAttribute("bankName", bankName);
		return mapping.findForward("agreementOverdue");
	}
	
	/**
	 * 监管费到期前
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward payDateBefore30(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		DealerVO dealer = dealerService.get(id);
		String payMode= "";
		double money = Double.parseDouble(dealer.getSuperviseMoney());
		if(dealer.getPayMode()==PayModeContant.PAYMODE_MONTH.getCode()){
			payMode=PayModeContant.PAYMODE_MONTH.getName();
			money = money/12;
			
		}else if(dealer.getPayMode()==PayModeContant.PAYMODE_QUARTER.getCode()){
			payMode=PayModeContant.PAYMODE_QUARTER.getName();
			money = money/4;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_SIXMONTH.getCode()){
			payMode=PayModeContant.PAYMODE_SIXMONTH.getName();
			money = money/2;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_YEAR.getCode()){
			payMode=PayModeContant.PAYMODE_YEAR.getName();
		}
		
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		AgreementSendQueryVO sendQuery = new AgreementSendQueryVO();
		sendQuery.setDistribid(dealer.getId());
		List<AgreementSendVO> sendList = sendService.searchAgreementSendList(sendQuery);
		if(sendList!=null&&sendList.size()>0){
			request.setAttribute("agreementNum", sendList.get(0).getAgreement_num());
		}
		
		request.setAttribute("bankName", bankName);
		request.setAttribute("dealer", dealer);
		request.setAttribute("money",money);
		return mapping.findForward("payDateBefore30");
	}
	
	/**
	 * 发送进店流转单未按时进驻提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward notApplyDate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		BusinessTransferVO bt = btService.get(id);
		DealerVO dealer = dealerService.get(bt.getDealerId());
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		request.setAttribute("bt", bt);
		request.setAttribute("dealer", dealer);
		request.setAttribute("bankName", bankName);
		return mapping.findForward("notApplyDate");
	}
	
	/**
	 * 监管费到期前7天进行提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward payDateBefore7(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		DealerVO dealer = dealerService.get(id);
		String payMode= "";
		double money = Double.parseDouble(dealer.getSuperviseMoney());
		if(dealer.getPayMode()==PayModeContant.PAYMODE_MONTH.getCode()){
			payMode=PayModeContant.PAYMODE_MONTH.getName();
			money = money/12;
			
		}else if(dealer.getPayMode()==PayModeContant.PAYMODE_QUARTER.getCode()){
			payMode=PayModeContant.PAYMODE_QUARTER.getName();
			money = money/4;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_SIXMONTH.getCode()){
			payMode=PayModeContant.PAYMODE_SIXMONTH.getName();
			money = money/2;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_YEAR.getCode()){
			payMode=PayModeContant.PAYMODE_YEAR.getName();
		}
		
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		AgreementSendQueryVO sendQuery = new AgreementSendQueryVO();
		sendQuery.setDistribid(dealer.getId());
		List<AgreementSendVO> sendList = sendService.searchAgreementSendList(sendQuery);
		if(sendList!=null&&sendList.size()>0){
			request.setAttribute("send", sendList.get(0));
		}
		
		request.setAttribute("bankName", bankName);
		request.setAttribute("dealer", dealer);
		request.setAttribute("money",money);
		
		return mapping.findForward("payDateBefore7");
	}
	
	/**
	 * 监管费到期前15天进行提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward payDateBefore15(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		DealerVO dealer = dealerService.get(id);
		String payMode= "";
		double money = Double.parseDouble(dealer.getSuperviseMoney());
		if(dealer.getPayMode()==PayModeContant.PAYMODE_MONTH.getCode()){
			payMode=PayModeContant.PAYMODE_MONTH.getName();
			money = money/12;
			
		}else if(dealer.getPayMode()==PayModeContant.PAYMODE_QUARTER.getCode()){
			payMode=PayModeContant.PAYMODE_QUARTER.getName();
			money = money/4;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_SIXMONTH.getCode()){
			payMode=PayModeContant.PAYMODE_SIXMONTH.getName();
			money = money/2;
		}
		else if(dealer.getPayMode()==PayModeContant.PAYMODE_YEAR.getCode()){
			payMode=PayModeContant.PAYMODE_YEAR.getName();
		}
		
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		AgreementSendQueryVO sendQuery = new AgreementSendQueryVO();
		sendQuery.setDistribid(dealer.getId());
		List<AgreementSendVO> sendList = sendService.searchAgreementSendList(sendQuery);
		if(sendList!=null&&sendList.size()>0){
			request.setAttribute("send", sendList.get(0));
		}
		
		request.setAttribute("bankName", bankName);
		request.setAttribute("dealer", dealer);
		request.setAttribute("money",money);
		
		return mapping.findForward("payDateBefore15");
	}
	
	/**
	 * 监管费到期当天提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward payDateExpire(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		DealerVO dealer = dealerService.get(id);
		String bankName = dealerService.getBankNameByDealerId(dealer.getId());
		request.setAttribute("bankName", bankName);
		request.setAttribute("dealer", dealer);
		return mapping.findForward("payDateExpire");
	}
}
