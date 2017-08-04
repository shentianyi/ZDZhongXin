package com.zd.csms.marketing.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.bank.model.BankManagerVO;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankManagerService;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.MarketProjectCirculationQueryVO;
import com.zd.csms.marketing.model.MarketProjectCirculationVO;
import com.zd.csms.marketing.querybean.PCListQueryBean;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.service.MarketProjectCirculationService;
import com.zd.csms.marketing.web.form.MarketProjectCirculationForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.region.service.RegionService;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 项目进驻流转单
 * @author licheng
 *
 */
public class ProjectCirculationAction extends ActionSupport{
	private MarketProjectCirculationService service = new MarketProjectCirculationService();
	private BankService bankService = new BankService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	private BankManagerService bmService = new BankManagerService();
	private BrandService brandService = new BrandService();
	private DealerService dealerService = new DealerService();
	
	/**
	 * 流程角色:市场部专员->市场部经理->市场部部长->物流金融中心总监
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(),
			RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.SR.getCode()};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	private MarketProjectCirculationForm getForm(ActionForm form){
		
		return (MarketProjectCirculationForm) form;
	}
	
	public ActionForward preAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		initOptions(request);
		request.setAttribute("brands",OptionUtil.getBrands());
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		MarketProjectCirculationForm mpcForm = (MarketProjectCirculationForm) form;
		MarketProjectCirculationVO mpc = mpcForm.getMpc();
		mpc.setIsSignAnAgreement(1);//默认已签署协议
		mpc.setAgreementIsRecovery(1);
		mpc.setSuperviseMoneyPerson(UserSessionUtil.getUserSession(request).getUser().getUserName());
		mpc.setIsNeedHandover(2);
		mpc.setIsBindShop(2);
		mpc.setCooperationModel(1);
		mpc.setProvideLunch(2);
		mpc.setSupervisionMode("1");
		return mapping.findForward("add");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketProjectCirculationForm mpcForm = (MarketProjectCirculationForm) form;
		MarketProjectCirculationVO vo = mpcForm.getMpc();
		//验证数据是否重复
		if(service.validateRepeat(vo.getDealerName(),vo.getBankId())>0){
			request.setAttribute("errorMessage","当前经销商和银行已经进驻，请重新填写！");
			return preAdd(mapping, form, request, response);
		}
		
		vo.setCreateUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		vo.setCreateDate(new Date());
		service.add(vo);
		return findList(mapping, form, request, response);
	}
	
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		MarketProjectCirculationForm mpcForm = getForm(form);
		MarketProjectCirculationVO vo = getForm(form).getMpc();
		vo = service.get(vo.getId());
		
		if(vo.getBankId()>0){
			BankVO bank = bankService.get(vo.getBankId());
			mpcForm.setBankName(bank.getBankName());
			request.setAttribute("bank", bank);
		}
		mpcForm.setMpc(vo);
		initOptions(request);
		request.setAttribute("brands",OptionUtil.getBrands());
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		return mapping.findForward("update");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketProjectCirculationForm mpcForm = (MarketProjectCirculationForm) form;
		MarketProjectCirculationVO vo = mpcForm.getMpc();
		vo.setLastModifyDate(new Date());
		vo.setLastModifyUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		service.update(vo);
		return findList(mapping, form, request, response);
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketProjectCirculationVO vo = getForm(form).getMpc();
		service.delete(vo.getId());
		return findList(mapping, form, request, response);
	}
	
	
	
	/**
	 * 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MarketProjectCirculationQueryVO query = getForm(form).getQuery();
		query.setCurrRole(getCurrRole(request));
		
		if(query.getPageType()==null){
			query.setPageType(1);
		}
		int pageType = query.getPageType();
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("marketProjectList", request);	
		query.setPageType(pageType);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<PCListQueryBean> list = service.findList(query, tools);
		
		request.setAttribute("list", list);
		initOptions(request);
		
		
		if(query.getPageType()==1){//待审批列表
			return mapping.findForward("list_not_approval");
		}else{//已审批列表
			return mapping.findForward("list_already_approval");
		}
		
	}
	
	private void initOptions(HttpServletRequest request){
		request.setAttribute("supervisionModes", OptionUtil.supervisionModes());
		request.setAttribute("cooperationModels", OptionUtil.cooperationModels());
		request.setAttribute("yesorno", OptionUtil.yesorno());
		request.setAttribute("payModes", OptionUtil.payModes());
	}
	
	/**
	 * 跳转审批界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		MarketProjectCirculationForm mpcForm = (MarketProjectCirculationForm) form;
		int id = mpcForm.getQuery().getId();
		MarketProjectCirculationVO bean = service.get(id);
		BankVO bank = null;
		if(bean.getBankId()!=0){
			bank = bankService.get(bean.getBankId());
			if(bank!=null){
				mpcForm.setBank(bank);
			}
		}
		MarketApprovalQueryVO query = new MarketApprovalQueryVO();
		query.setObjectId(id);
		query.setObjectType(ApprovalTypeContant.MARKETPROJECTCIRCULATION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(query));
		RegionService regionService=new RegionService();
		bean.setProvince(regionService.getNameById(StringUtil.intValue(bean.getProvince(), 0)));
		bean.setCity(regionService.getNameById(StringUtil.intValue(bean.getCity(), 0)));
		
		mpcForm.setMpc(bean);
		initOptions(request);
		
		String sMode = bean.getSupervisionMode();
		Integer[] mode = null;
		if(!StringUtil.isEmpty(sMode)){
			String[] modes  = sMode.split(",");
			mode = new Integer[modes.length];
			for (int i = 0; i < modes.length; i++) {
				mode[i] = Integer.parseInt(modes[i]);
			}
		}
		mpcForm.setSupervisionMode(mode);
		
		BankManagerVO bm = bmService.get(bean.getBankManagerId());
		if(bm!=null)
			mpcForm.setBm(bm);
		BrandVO brand = brandService.loadBrandById(bean.getBrandId());
		if(brand!=null)
			request.setAttribute("brandName", brand.getName());
		
		String bindShopIds = "";
		//查询绑定店
		if(bean.getBindShop()>0){
			bindShopIds+=bean.getBindShop()+"";
		}
		if(bean.getBindShop2()>0){
			if(!bindShopIds.equals("")){
				bindShopIds+=",";
			}
			bindShopIds+=bean.getBindShop2()+"";
		}
		if(!bindShopIds.equals("")){
			request.setAttribute("bindShopName",dealerService.getBindNameById(bindShopIds));
		}
		
		return mapping.findForward("approval");
	}
	
	/**
	 * 审批
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward approval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currRole = getCurrRole(request);
		UserSession user = UserSessionUtil.getUserSession(request);
		MarketProjectCirculationQueryVO query = getForm(form).getQuery();
		query.setCurrRole(currRole);
		service.approval(query, user);
		//query.setPageType(2);
		return findList(mapping, form, request, response);
	}
	
	/**
	 * 申请单提交操作，提交后禁止修改和删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		MarketProjectCirculationQueryVO query = getForm(form).getQuery();
		int id = query.getId();
		service.submit(id);
		return findList(mapping, form, request, response);
	}
	
	
	/**
	 * 详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deatilInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		MarketProjectCirculationForm mpcForm = (MarketProjectCirculationForm) form;
		int id = mpcForm.getQuery().getId();
		MarketProjectCirculationVO bean = service.get(id);
		BankVO bank = null ;
		if(bean.getBankId()!=0){
			bank = bankService.get(bean.getBankId());
			if(bank!=null){
				mpcForm.setBank(bank);
			}
		}
		RegionService regionService=new RegionService();
		bean.setProvince(regionService.getNameById(StringUtil.intValue(bean.getProvince(), 0)));
		bean.setCity(regionService.getNameById(StringUtil.intValue(bean.getCity(), 0)));
		
		MarketApprovalQueryVO query = new MarketApprovalQueryVO();
		query.setObjectId(id);
		query.setObjectType(ApprovalTypeContant.MARKETPROJECTCIRCULATION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(query));
		mpcForm.setMpc(bean);
		initOptions(request);
		
		
		BankManagerVO bm = bmService.get(bean.getBankManagerId());
		if(bm!=null)
			mpcForm.setBm(bm);
		BrandVO brand = brandService.loadBrandById(bean.getBrandId());
		if(brand!=null)
			request.setAttribute("brandName", brand.getName());
		
		String bindShopIds = "";
		//查询绑定店
		if(bean.getBindShop()>0){
			bindShopIds+=bean.getBindShop()+"";
		}
		if(bean.getBindShop2()>0){
			if(!bindShopIds.equals("")){
				bindShopIds+=",";
			}
			bindShopIds+=bean.getBindShop2()+"";
		}
		if(!bindShopIds.equals("")){
			request.setAttribute("bindShopName",dealerService.getBindNameById(bindShopIds));
		}
		request.setAttribute("currClientType", UserSessionUtil.getUserSession(request).getUser().getClient_type());		
		return mapping.findForward("detail");
	}
	
	/*
	 * 项目进驻流转单台账
	 * @time 20170621
	*/
	public ActionForward marketProjectListLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		MarketProjectCirculationQueryVO query = getForm(form).getQuery();
		query.setCurrRole(getCurrRole(request));
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("marketProjectListLedger", request);	
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<PCListQueryBean> list = service.findmarketProjectListLedger(query, tools);
		
		request.setAttribute("list", list);
		initOptions(request);
		return mapping.findForward("marketProjectList_ledger");
		
	}
	
	public ActionForward detailLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		MarketProjectCirculationForm mpcForm = (MarketProjectCirculationForm) form;
		int id = mpcForm.getQuery().getId();
		MarketProjectCirculationVO bean = service.get(id);
		BankVO bank = null ;
		if(bean.getBankId()!=0){
			bank = bankService.get(bean.getBankId());
			if(bank!=null){
				mpcForm.setBank(bank);
			}
		}
		RegionService regionService=new RegionService();
		bean.setProvince(regionService.getNameById(StringUtil.intValue(bean.getProvince(), 0)));
		bean.setCity(regionService.getNameById(StringUtil.intValue(bean.getCity(), 0)));
		
		MarketApprovalQueryVO query = new MarketApprovalQueryVO();
		query.setObjectId(id);
		query.setObjectType(ApprovalTypeContant.MARKETPROJECTCIRCULATION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(query));
		mpcForm.setMpc(bean);
		initOptions(request);
		
		
		BankManagerVO bm = bmService.get(bean.getBankManagerId());
		if(bm!=null)
			mpcForm.setBm(bm);
		BrandVO brand = brandService.loadBrandById(bean.getBrandId());
		if(brand!=null)
			request.setAttribute("brandName", brand.getName());
		
		String bindShopIds = "";
		//查询绑定店
		if(bean.getBindShop()>0){
			bindShopIds+=bean.getBindShop()+"";
		}
		if(bean.getBindShop2()>0){
			if(!bindShopIds.equals("")){
				bindShopIds+=",";
			}
			bindShopIds+=bean.getBindShop2()+"";
		}
		if(!bindShopIds.equals("")){
			request.setAttribute("bindShopName",dealerService.getBindNameById(bindShopIds));
		}
		request.setAttribute("currClientType", UserSessionUtil.getUserSession(request).getUser().getClient_type());		
		return mapping.findForward("detailLedger");
	}
	
}
