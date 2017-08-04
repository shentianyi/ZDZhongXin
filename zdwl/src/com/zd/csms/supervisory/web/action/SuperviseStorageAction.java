package com.zd.csms.supervisory.web.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ActionSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDockDAO;
import com.zd.csms.bank.dock.BankDock;
import com.zd.csms.bank.dock.ZSBankDockImpl;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftToLnciVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.set.contants.BankDockType;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.service.BankApproveService;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.web.form.SuperviseImportForm;
import com.zd.csms.zxbank.dock.ZXBankDockImpl;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 入库
 * @author luyang
 */
public class SuperviseStorageAction extends ActionSupport {
	private DistribsetService distribsetService = new DistribsetService();
	private IBankDockDAO bankDockDao = BankDAOFactory.getBankDockDAO();
	private SuperviseImportService service = new SuperviseImportService();
	private DecimalFormat df = new DecimalFormat("0.00");
	/**
	 * 流程角色:市场部专员->招聘专员->业务部专员
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.BANK_APPROVE.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
			};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	
	public ActionForward superviseStorageListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return superviseStorageList(mapping, form,request, response);
	}
	
	
	public ActionForward superviseStorageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm)form;
		
		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setState(1);
		siQuery.setApply(0);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(currRole==RoleConstants.BANK_APPROVE.getCode()){
			siQuery.setType(2);
			siQuery.setUserid(user.getClient_id());
		}else if(currRole==RoleConstants.SUPERVISORY.getCode()){
			siQuery.setType(1);
			siQuery.setUserid(user.getId());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			siQuery.setType(3);
			siQuery.setUserid(user.getId());
		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseStorageList",request);
		thumbPageTools.saveQueryVO(siQuery);//记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(50);
		//按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		//返回列表页面
		return mapping.findForward("supervise_storage_list");
	}
	
	public ActionForward superviseStorageNowList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setState(2);
		siQuery.setApply(1);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(currRole==RoleConstants.BANK_APPROVE.getCode()){
			siQuery.setType(2);
			siQuery.setUserid(user.getClient_id());
		}else if(currRole==RoleConstants.SUPERVISORY.getCode()){
			siQuery.setType(1);
			siQuery.setUserid(user.getId());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			siQuery.setType(3);
			siQuery.setUserid(user.getId());
		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseStorageNowList",request);
		thumbPageTools.saveQueryVO(siQuery);//记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(50);
		//按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		//返回列表页面
		return mapping.findForward("supervise_storage_now_list");
	}
	
	
	public ActionForward updSuperviseStorageEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		
		//根据id获取修改对象
		SuperviseImportVO vo = service.loadSuperviseImportById(siform.getSuperviseImport().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return superviseStorageList(mapping, form, request, response);
		}
		
		DraftService ds = new DraftService();
		DraftQueryVO dq = new DraftQueryVO();
		dq.setDraft_num(vo.getDraft_num());
		List<DraftVO> dList = ds.searchDraftList(dq);
		if(dList != null && dList.size()>0){
			DraftVO draft = dList.get(0);
			vo.setDraft_num(draft.getId()+"");
		}
		
		siform.setSuperviseImport(vo);
		
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_supervise_storage");
	}
	
	public ActionForward updSuperviseStorage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		SuperviseImportVO sivo = siform.getSuperviseImport();
		sivo.setUpduserid(user.getId());
		sivo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updSuperviseImport(sivo,2);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return superviseStorageList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_supervise_storage");
	}
	
	public ActionForward updSuperviseStorages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int currRole=getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm)form;
		BankApproveService baservice = new BankApproveService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		CarOperationService coservice = new CarOperationService();
		int sid = 0;
		
		String carIds = request.getParameter("ids");
		if (!StringUtil.isEmpty(carIds)) {
			int did = 0;//经销商Id
			String[] idArray = carIds.split(",");
			String siid = idArray[0];
			SuperviseImportVO svo = service.loadSuperviseImportById(Integer.parseInt(siid));
			DraftService ds = new DraftService();
			DraftQueryVO dquery = new DraftQueryVO();
			String drafNum = svo.getDraft_num();
			dquery.setDraft_num(drafNum);
			List<DraftVO> dList = ds.searchDraftList(dquery);
			//汇票的开票金额
			double billing_money=0;
			//汇票已押车金额
			double pledge_car_money = 0;
			if(dList != null && dList.size()>0){
				DraftVO draftVO = dList.get(0);
				did = draftVO.getDistribid();
				if(NumberUtils.isNumber(draftVO.getBilling_money())){
					billing_money =  df.parse(draftVO.getBilling_money()).doubleValue();
				}
				if(NumberUtils.isNumber(draftVO.getMortgagecar_money())  && draftVO.getMortgagecar_money() != null){
					pledge_car_money = df.parse(draftVO.getMortgagecar_money()).doubleValue();
				}
			}
			
			List<SuperviseImportVO> carList = service.findListByIds(carIds);
			//这批车的总价
			long totalPrice=service.findTotalPricesByIds(carIds);
			
			
			//银行接口开始
			DistribsetVO distribsetVO =  distribsetService.getDistribsetVOByDistribid(did);
			if(distribsetVO!=null&&distribsetVO.getBankDockType()!=null&&distribsetVO.getBankDockType()!=0){
				BankDock bankDock = null;
				if(distribsetVO.getBankDockType()==BankDockType.ZSBANK.getCode()){
					if(StringUtil.isEmpty(distribsetVO.getZsCustNo())){
						request.setAttribute("message", "所选车辆的所属经销商未绑定客户号，请到参数设置中绑定");
						return superviseStorageList(mapping, form, request, response);
					}
					if(StringUtil.isEmpty(distribsetVO.getContractNo())){
						request.setAttribute("message", "所选车辆的所属经销商未绑定合同编号，请绑定");
						return superviseStorageList(mapping, form, request, response);
					}
					bankDock = new ZSBankDockImpl();
					DraftToLnciVO dtvo = bankDockDao.getDraftToLnciByDraftNum(drafNum);
					if(dtvo==null){
						request.setAttribute("message", "票号为"+drafNum+"的汇票与借据关联信息未同步");
						return superviseStorageList(mapping, form, request, response);
					}else if(StringUtil.isEmpty(dtvo.getLnciId())){
						request.setAttribute("message", "票号对应的借据ID未同步");
						return superviseStorageList(mapping, form, request, response);
					}
					
					request.setAttribute("lnciId", dtvo.getLnciId());
					request.setAttribute("custNo", distribsetVO.getZsCustNo());
					request.setAttribute("pledgeContractCode", distribsetVO.getContractNo());
					DealerVO dealer = bankDockDao.get(DealerVO.class, did, new BeanPropertyRowMapper(DealerVO.class));
					if(dealer!=null){
						request.setAttribute("warehouseAddress", dealer.getAddress()==null?"":dealer.getAddress());
					}else{
						request.setAttribute("warehouseAddress", "未知");
					}
					
				}else if(distribsetVO.getBankDockType()==BankDockType.ZXBANK.getCode()){
					if(StringUtil.isEmpty(distribsetVO.getZxOrgCode())){
						request.setAttribute("message", "所选车辆的所属经销商未绑定组织机构代码，请到参数设置中绑定");
						return superviseStorageList(mapping, form, request, response);
					}
					
					bankDock = new ZXBankDockImpl();
				}
				
				boolean result = bankDock.ruku(carList, request);
				if(!result){
					return superviseStorageList(mapping, form, request, response);
				}
			}
			//银行接口结束
			
			//这批车的总金额是否超过该汇票的开票金额5万以上
			//boolean isOverstep=totalPrice-billing_money>50000;
			
			//车辆入库时，如果这批入库车辆的总金额加上当前汇票的已押车金额总和、超出当前汇票的开票金额5万、就需要到业务专员审核，然后再到银行审核入库
			boolean isOverstep=(totalPrice+pledge_car_money)-billing_money>50000;
			try {
				for (SuperviseImportVO vo : carList) {
					vo.setStoragetime(new Date());
					vo.setApply(1);
					vo.setState(2);
					vo.setUpduserid(user.getId());
					vo.setUpddate(new Date());
					//监管员入库时,如果这批车的总金额超过该汇票的开票金额5万以上，先让业务专员审批，业务专员审批同意后让银行审批人审批
					if(currRole==RoleConstants.SUPERVISORY.getCode() && isOverstep){
						vo.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
					}else{
						vo.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
					}
					service.updSuperviseImport(vo,0); 
					
					BankApproveVO bavo = baservice.searchBankApproveBySid(vo.getId(),2);
					if (null != bavo) {
						bavo.setState(1);
						bavo.setType(2);
						bavo.setCreatetime(new Date());
						baservice.updBankApprove(bavo);
					}else{
						bavo = new BankApproveVO();
						bavo.setSid(vo.getId());
						bavo.setState(1);
						bavo.setType(2);
						bavo.setCreatetime(new Date());
						baservice.addBankApprove(bavo);
					}	
					sid=vo.getId();	
				}
				CarOperationVO covo = new CarOperationVO();
				covo.setCars(carIds);
				covo.setType(2);
				covo.setDistribid(did);
				covo.setUserid(user.getId());
				covo.setCreatetime(new Date());
				coservice.addCarOperation(covo);
				
				List<UserVO> uList = service.searchUserById(sid);
				if(uList != null && uList.size()>0){
					for(UserVO ur : uList){
						MessageUtil.addMsg(ur.getId(), "监管物入库", "/carOperation.do?method=storageList",1,MessageTypeContant.CARSTORAGE.getCode(),user.getId());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return superviseStorageList(mapping, form, request, response);
	}
	
	public ActionForward updSuperviseStoragekeys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String keyamount = request.getParameter("keyamount");
		int keys = 0;
		if(!StringUtil.isEmpty(keyamount)){
			keys = Integer.parseInt(keyamount);
		}
		String carIds = request.getParameter("ids");
		if (!StringUtil.isEmpty(carIds) && keys > -1) {
			String[] idArray = carIds.split(",");
			try {
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(idArray[i]));
					vo.setKey_amount(keyamount);
					vo.setUpduserid(user.getId());
					vo.setUpddate(new Date());
					service.updSuperviseImport(vo,0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return superviseStorageList(mapping, form, request, response);
	}
	
	public ActionForward delSuperviseStorage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSuperviseImport(siform.getSuperviseImport().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return superviseStorageList(mapping, form, request, response);
	}
	
	public ActionForward importExcelEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		setOptions(request);
		return mapping.findForward("import_supervise");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("superviseStateOptions", OptionUtil.superviseState());
		request.setAttribute("draftOptions", OptionUtil.draftOptions());
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		
	}
}
