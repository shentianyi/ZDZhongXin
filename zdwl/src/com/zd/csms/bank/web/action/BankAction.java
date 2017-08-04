package com.zd.csms.bank.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.zd.core.ActionSupport;
import com.zd.csms.bank.contants.BankContants;
import com.zd.csms.bank.model.BankChildrenManagerVO;
import com.zd.csms.bank.model.BankQueryBean;
import com.zd.csms.bank.model.BankQueryVO;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.bank.web.form.BankForm;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class BankAction extends ActionSupport{

	
	BankService bankService = new BankService();
	
	/**
	 * 预新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preAddBank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		BankVO parentBank = bankForm.getBank();
		BankVO addBank = new BankVO();
		if(parentBank.getId()!=0){
			parentBank = bankService.get(parentBank.getId());
			//判断上级银行是主行还是分行
			if(BankContants.MAIN_BANK.getCode()==parentBank.getBankType()){
				addBank.setBankType(BankContants.BRANCH_BANK.getCode());
			}else if(BankContants.BRANCH_BANK.getCode()==parentBank.getBankType()){
				addBank.setBankType(BankContants.SUBBRANCH_BANK.getCode());
			}else if(0==parentBank.getBankType()){
				addBank.setBankType(BankContants.MAIN_BANK.getCode());
			}
			addBank.setParentId(parentBank.getId());
			addBank.setPath(parentBank.getPath()+parentBank.getId()+"/");
			request.setAttribute("parentName", parentBank.getBankName());
		}else{
			addBank.setParentId(-1);
			addBank.setBankType(BankContants.MAIN_BANK.getCode());
			addBank.setPath("-1/");
		}
		
		bankForm.setBank(addBank);
		return mapping.findForward("add_bank");
	}
	
	/**
	 * 新增银行
	 * @author licheng at 2016年7月14日 下午1:30:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addBank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		bankService.add(bankForm.getBank());
		
		return bankList(mapping, bankForm, request, response);
	}
	
	/**
	 * 预更新跳转
	 * @author licheng at 2016年7月14日 下午1:35:11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preUpdateBank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BankForm bf = (BankForm) form;
		String bankId = request.getParameter("bankId");
		BankVO bank =  bankService.get(Integer.parseInt(bankId));
		if(bank.getBankType()!=BankContants.MAIN_BANK.getCode()){
			BankVO parentBank = bankService.get(bank.getParentId());
			request.setAttribute("parent", parentBank);
		}
		bf.setBank(bank);
		return mapping.findForward("upd_bank");
	}
	
	/**
	 * 更新银行
	 * @author licheng at 2016年7月14日 下午1:31:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward updateBank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		bankService.update(bankForm.getBank());
		
		return bankList(mapping, bankForm, request, response);
	}
	
	
	/**
	 * 删除银行
	 * @author licheng at 2016年7月14日 下午1:36:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward deleteBank(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		int id = bankForm.getBank().getId();
		bankService.delete(id);
		
		return bankList(mapping, bankForm, request, response);
	}
	
	
	/**
	 * 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward bankList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		BankQueryVO bankQuery = bankForm.getBankQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("bankList", request);
		tools.saveQueryVO(bankQuery);
		List<BankVO> list = bankService.findBankList(bankQuery, tools);
		List<BankQueryBean> resultList = new ArrayList<BankQueryBean>();
		if(list!=null)
			for (BankVO bankVO : list) {
				BankQueryBean bean = new BankQueryBean(); 
				BeanUtils.copyProperties(bankVO, bean);
				if(bankVO.getBankType() == BankContants.MAIN_BANK.getCode()){
					bean.setZhu(bankVO.getBankName());
				}else if(bankVO.getBankType() == BankContants.BRANCH_BANK.getCode()){
					bean.setZhu(bankService.get(bankVO.getParentId()).getBankName());
					bean.setFen(bankVO.getBankName());
				}else if(bankVO.getBankType() == BankContants.SUBBRANCH_BANK.getCode()){
					bean.setZhi(bankVO.getBankName());//支
					BankVO fen = bankService.get(bankVO.getParentId());
					bean.setFen(fen.getBankName());//分
					bean.setZhu(bankService.get(fen.getParentId()).getBankName());//主
				}
				
				resultList.add(bean);
			}
		request.setAttribute("list", resultList);
		initOption(request);
		return mapping.findForward("bank_list");
	}
	
	/**
	 * 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward bankChildrenManagerList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		BankQueryVO bankQuery = bankForm.getBankQuery();
		bankQuery.setBankType(new Integer[]{BankContants.BRANCH_BANK.getCode()});
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("bankList", request);
		//tools.saveQueryVO(bankQuery);
		List<BankVO> list = bankService.findBankList(bankQuery, tools);
		List<BankQueryBean> resultList = new ArrayList<BankQueryBean>();
		if(list!=null)
			for (BankVO bankVO : list) {
				BankQueryBean bean = new BankQueryBean(); 
				BeanUtils.copyProperties(bankVO, bean);
				if(bankVO.getBankType() == BankContants.MAIN_BANK.getCode()){
					bean.setZhu(bankVO.getBankName());
				}else if(bankVO.getBankType() == BankContants.BRANCH_BANK.getCode()){
					bean.setZhu(bankService.get(bankVO.getParentId()).getBankName());
					bean.setFen(bankVO.getBankName());
				}
				
				resultList.add(bean);
			}
		request.setAttribute("list", resultList);
		initOption(request);
		return mapping.findForward("bank_children_manager");
	}
	
	/**
	 * 支行管理已选列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bankChildrenIn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		BankQueryVO bankQuery = bankForm.getBankQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("bankChildrenIn", request);
		List<BankVO> list = bankService.findChildrenBankIn(bankQuery, tools);
		List<BankQueryBean> resultList = new ArrayList<BankQueryBean>();
		if(list!=null)
			for (BankVO bankVO : list) {
				BankQueryBean bean = new BankQueryBean(); 
				BeanUtils.copyProperties(bankVO, bean);
				if(bankVO.getBankType() == BankContants.SUBBRANCH_BANK.getCode()){
					bean.setZhi(bankVO.getBankName());//支
					BankVO fen = bankService.get(bankVO.getParentId());
					bean.setFen(fen.getBankName());//分
					bean.setZhu(bankService.get(fen.getParentId()).getBankName());//主
				}
				
				resultList.add(bean);
			}
		request.setAttribute("list", resultList);
		initOption(request);
		return mapping.findForward("bank_children_in");
	}
	
	public ActionForward bankChildrenOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		BankQueryVO bankQuery = bankForm.getBankQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("bankChildrenOut", request);
		List<BankVO> list = bankService.findChildrenBankOut(bankQuery, tools);
		List<BankQueryBean> resultList = new ArrayList<BankQueryBean>();
		if(list!=null)
			for (BankVO bankVO : list) {
				BankQueryBean bean = new BankQueryBean(); 
				BeanUtils.copyProperties(bankVO, bean);
				if(bankVO.getBankType() == BankContants.SUBBRANCH_BANK.getCode()){
					bean.setZhi(bankVO.getBankName());//支
					BankVO fen = bankService.get(bankVO.getParentId());
					bean.setFen(fen.getBankName());//分
					bean.setZhu(bankService.get(fen.getParentId()).getBankName());//主
				}
				
				resultList.add(bean);
			}
		request.setAttribute("list", resultList);
		initOption(request);
		return mapping.findForward("bank_children_out");
	}
	
	public ActionForward addBankChildren(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		BankQueryVO bankQuery = bankForm.getBankQuery();
		int[] childrenids = bankQuery.getBankChildrenIds();
		for (int i : childrenids) {
			BankChildrenManagerVO vo = new BankChildrenManagerVO();
			vo.setParentId(bankQuery.getParentId());
			vo.setChildrenId(i);
			bankService.addBankChildren(vo);
		}
		
		return bankChildrenOut(mapping, bankForm, request, response);
	}
	
	public ActionForward deleteBankChildren(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BankForm bankForm = (BankForm) form;
		BankQueryVO bankQuery = bankForm.getBankQuery();
		int[] childrenids = bankQuery.getBankChildrenIds();
		for (int i : childrenids) {
			bankService.deleteBankChildren(bankQuery.getParentId(), i);
		}
		
		return bankChildrenIn(mapping, bankForm, request, response);
	}
	
	private void initOption(HttpServletRequest request) throws Exception{
		List<OptionObject> bankType = OptionUtil.bankTypeOptions();
		request.setAttribute("bankType", bankType);
	}
}
