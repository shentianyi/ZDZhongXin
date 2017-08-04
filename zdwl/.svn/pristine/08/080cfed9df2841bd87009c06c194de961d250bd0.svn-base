package com.zd.csms.supervisory.web.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.alibaba.fastjson.JSONObject;
import com.zd.core.ActionSupport;
import com.zd.csms.bank.dock.BankDock;
import com.zd.csms.bank.dock.ZSBankDockImpl;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messageAndWaring.message.service.business.BusinessMessageTypeService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.contants.MsgUrlContant;
import com.zd.csms.messagequartz.service.MessageQuartzService;
import com.zd.csms.messagequartz.util.MsgUtil;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.set.contants.BankDockType;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.model.outstock.SupervisorOutStockMessageVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.service.BankApproveService;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.service.outstock.SupervisorOutStockMessageService;
import com.zd.csms.supervisory.web.form.SuperviseImportForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 出库
 */
public class SuperviseOutAction extends ActionSupport {
	SuperviseImportService service = new SuperviseImportService();
	BankApproveService baservice = new BankApproveService();
	CarOperationService coservice = new CarOperationService();
	DraftService ds = new DraftService();
	DistribsetService distribsetService = new DistribsetService();
	MessageQuartzService dsService = new MessageQuartzService();
	private BusinessMessageTypeService typeService=new BusinessMessageTypeService() ;
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

	public ActionForward superviseOutListEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return superviseOutList(mapping, form, request, response);
	}

	public ActionForward superviseOutList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm) form;

		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setStates("2,4");
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
		
		// 初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseOutList", request);
		thumbPageTools.saveQueryVO(siQuery);// 记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(50);
		// 按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		// 将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		// 返回列表页面
		return mapping.findForward("supervise_out_list");
	}

	public ActionForward superviseOutNowList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm) form;

		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setState(3);
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
		
		// 初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseOutNowList", request);
		thumbPageTools.saveQueryVO(siQuery);// 记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(50);
		// 按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		// 将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		// 返回列表页面
		return mapping.findForward("supervise_out_now_list");
	}

	public ActionForward updSuperviseOutEntry(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SuperviseImportForm siform = (SuperviseImportForm) form;

		// 根据id获取修改对象
		SuperviseImportVO vo = service.loadSuperviseImportById(siform.getSuperviseImport().getId());

		// 对象不存在时返回列表页
		if (vo == null) {
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return superviseOutList(mapping, form, request, response);
		}

		siform.setSuperviseImport(vo);

		// 返回修改页面
		return mapping.findForward("upd_supervise_out");
	}

	public ActionForward updSuperviseOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SuperviseImportForm siform = (SuperviseImportForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();

		SuperviseImportVO sivo = siform.getSuperviseImport();
		sivo.setUpduserid(user.getId());
		sivo.setUpddate(new Date());
		// 执行修改操作并获取操作结果
		boolean flag = service.updSuperviseImport(sivo, 3);
		// 将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());

		if (flag) {
			// 操作成功时返回列表页面
			return superviseOutList(mapping, form, request, response);
		}

		// 操作失败时返回修改 页面
		return mapping.findForward("upd_supervise_out");
	}

	public ActionForward updSuperviseOuts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String carIds = request.getParameter("ids");
		
		if (!StringUtil.isEmpty(carIds)) {
			final String[] idArray = carIds.split(",");
			
			//为了获取经销商ID
			int did = 0;
			String siid = idArray[0];
			SuperviseImportVO svo = service.loadSuperviseImportById(Integer.parseInt(siid));
			DraftQueryVO drquery = new DraftQueryVO();
			drquery.setDraft_num(svo.getDraft_num());
			List<DraftVO> drList = ds.searchDraftList(drquery);
			DraftVO dfvo = null;
			if (drList != null && drList.size() > 0) {
				dfvo = drList.get(0);
				did = dfvo.getDistribid();
			}
			int sid = 0;
			
			
			List<SuperviseImportVO> carList = service.findListByIds(idArray);
			//银行接口开始
			DistribsetVO distribsetVO =  distribsetService.getDistribsetVOByDistribid(did);
			if(distribsetVO!=null&&distribsetVO.getBankDockType()!=null&&distribsetVO.getBankDockType()!=0){
				BankDock bankDock = null;
				if(distribsetVO.getBankDockType()==BankDockType.ZSBANK.getCode()){
					if(StringUtil.isEmpty(distribsetVO.getZsCustNo())){
						request.setAttribute("message", "所选车辆的所属经销商未绑定客户号，请到参数设置中绑定");
						return superviseOutList(mapping, form, request, response);
					}
					if(StringUtil.isEmpty(distribsetVO.getContractNo())){
						request.setAttribute("message", "所选车辆的所属经销商未绑定质押合同编号，请绑定");
						return superviseOutList(mapping, form, request, response);
					}
					bankDock = new ZSBankDockImpl();
					request.setAttribute("custNo", distribsetVO.getZsCustNo());
					request.setAttribute("pledgeContractCode", distribsetVO.getContractNo());
				}
				
				boolean result = bankDock.chuku(carList, request);
				if(!result){
					return superviseOutList(mapping, form, request, response);
				}
				
			}
			//银行接口结束
			
			try {
				for (SuperviseImportVO vo : carList) {
					//判断车辆状态，设置车辆标识
					if (vo.getIdentifi()==0) {
					//	SuperviseImportVO bean=new SuperviseImportVO();
						if (vo.getApply()==0 && vo.getState()==2) {
							vo.setIdentifi(1);
						}else if (vo.getApply()==0 && vo.getState()==4) {
							vo.setIdentifi(2);
						}
					}
					vo.setState(3);
					vo.setApply(1);
					vo.setUpduserid(user.getId());
					vo.setUpddate(new Date());
				
					
					service.updSuperviseImport(vo, 0);
					sid = vo.getId();

					BankApproveVO bavo = baservice.searchBankApproveBySid(sid,3);
					if (null != bavo) {
						bavo.setState(1);
						bavo.setType(3);
						bavo.setCreatetime(new Date());
						baservice.updBankApprove(bavo);
					}else{
						bavo = new BankApproveVO();
						bavo.setSid(sid);
						bavo.setState(1);
						bavo.setType(3);
						bavo.setCreatetime(new Date());
						baservice.addBankApprove(bavo);
					}
					
				}
				
				
				CarOperationVO covo = new CarOperationVO();
				covo.setCars(carIds);
				covo.setType(3);
				covo.setDistribid(did);
				covo.setUserid(user.getId());
				covo.setCreatetime(new Date());
				coservice.addCarOperation(covo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			final int thread_sid = sid;
			
			final List<SuperviseImportVO> cars = carList;
			
			final List<DraftVO> drVos = drList;
			
			final DraftVO df = dfvo;
			
			final int dearleId = did;
			
			//消息推送开启一个新线程
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//----------封装提醒信息对象
						DealerService dService = new DealerService();
						BrandService bService = new BrandService();
						DealerVO dVo = new DealerVO();
						SupervisorOutStockMessageVO sosvo = new SupervisorOutStockMessageVO();
						if (drVos != null && drVos.size() > 0) {
							//金融结构
							sosvo.setBankname(df.getFinancial_institution());
							sosvo.setIsread(MsgIsContants.NOREAD.getCode());
							sosvo.setMessagetype(MessageTypeContant.SUPSERVISOUTSTOCK.getName());
							sosvo.setCreateDate(new Date());
							//经销商
							dVo = dService.get(dearleId);
							if (dVo != null) {
								sosvo.setMerchantname(dVo.getDealerName());
							}
						}
						UserRoleQueryVO urquery = new UserRoleQueryVO();
						RoleService rs = new RoleService();
						urquery.setRole_id(RoleConstants.BUSINESS_COMMISSIONER.getCode());
						List<UserRoleVO> urList;
						boolean flag = false;
					    urList = rs.searchUserRoleList(urquery);
					    
					    for(SuperviseImportVO superviseImportVO : cars) {
							BrandVO vo = bService.loadBrandById(superviseImportVO.getBrandid());
							if (vo != null) {
								sosvo.setBrandname(vo.getName());
							}
							sosvo.setImportId(superviseImportVO.getId());
							sosvo.setDraft_num(superviseImportVO.getDraft_num());
							sosvo.setVin(superviseImportVO.getVin());
							sosvo.setOutTime(superviseImportVO.getOuttime());
							addSRCMmessage(urList,sosvo);
					     }
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// 如果是最后一笔交易 发送通知
					try {
						String draftnum = "";
						Set<String> set = new HashSet<String>();
						for (int i = 0; i < idArray.length; i++) {
							SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(idArray[i]));
							draftnum = vo.getDraft_num();
							set.add(draftnum);
						}
						
						int sumCount = service.carCountByDraft(draftnum, 2);
						if (sumCount == idArray.length) {
							typeService.msgLastBusiness(set);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		return superviseOutList(mapping, form, request, response);
	}

	
	    //申请信息提醒
		private boolean addSRCMmessage(List<UserRoleVO> urList,SupervisorOutStockMessageVO srcmvo) throws Exception{
			MessageService ms = new MessageService();
			SupervisorOutStockMessageService srcmService = new SupervisorOutStockMessageService();
			UserService userService = new UserService();
			UserVO userVO = null;
			boolean flag = false;
			if(urList != null && urList.size()>0){
				for(UserRoleVO ur : urList){
					userVO = userService.get(ur.getUser_id());
					if (userVO != null && userVO.getState() == 1) {
						MessageVO msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVISOUTSTOCK.getCode());
						if (msVo != null) {
							//该用户该类型提醒信息数量增加1
							msVo.setName(String.valueOf(Integer.parseInt(msVo.getName())+1));//数量增加1
							if (msVo.getIsread()==2) {
								msVo.setIsread(1);
							}
							flag = ms.updMessage(msVo);
							if (flag) {
								srcmvo.setUserId(ur.getUser_id());
								srcmvo.setMessageId(msVo.getId());
								flag = srcmService.addOutStockMessage(srcmvo);
							}else {
								return false;
							}
						}else {
							MessageUtil.addMsg(ur.getUser_id(),String.valueOf(1), MsgUrlContant.SUPERRVISEOUTSTOCK.getName(), 1,MsgIsContants.NOREAD.getCode(),MessageTypeContant.SUPSERVISOUTSTOCK.getCode(),ClientTypeConstants.SUPERVISORY.getName());
							msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVISOUTSTOCK.getCode());
							srcmvo.setUserId(ur.getUser_id());
							srcmvo.setMessageId(msVo.getId());
							flag = srcmService.addOutStockMessage(srcmvo);
						}
					}
				}
			}
			return flag;
		}
	
	
	public void sendMsg(int num,int type,String url,String...userIds){
		for (String ids : userIds) {
			if (StringUtil.isNotEmpty(ids)) {
				try {
					MsgUtil.send(ids, num, type, 
							url,ClientTypeConstants.BUSINESS.getName(), 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ActionForward delSuperviseOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SuperviseImportForm siform = (SuperviseImportForm) form;

		// 执行删除操作并获取操作结果
		boolean flag = service.deleteSuperviseImport(siform.getSuperviseImport().getId());
		// 将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());

		// 返回列表页面
		return superviseOutList(mapping, form, request, response);
	}

	public void setOptions(HttpServletRequest request) {
		request.setAttribute("superviseStateOptions", OptionUtil.superviseState());
		request.setAttribute("draftOptions", OptionUtil.draftOptions());
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());

	}
	
	/**
	 * 异步更新回款金额调用的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updatePaymentAmount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = (String) request.getParameter("id");
		PrintWriter out = getWrite(response);
		JSONObject json = new JSONObject();
		if(StringUtil.isEmpty(id)){
			json.put("success", false);
			json.put("message", "id为空");
			out.write(json.toJSONString());
			out.flush();
			out.close();
			return null;
		}
		String payAmount=request.getParameter("payAmount");
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(NumberUtils.isNumber(payAmount)||StringUtil.isEmpty(payAmount)){
			SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(id));
			vo.setPayment_amount(payAmount);
			vo.setUpddate(new Date());
			vo.setUpduserid(user.getId());
			boolean result = service.updSuperviseImport(vo, 3);
			if(result){
				json.put("success", true);
				json.put("message", "更新成功");
				out.write(json.toJSONString());
				out.flush();
				out.close();
				return null;
			}else{
				json.put("success", false);
				json.put("message", "更新失败");
				out.write(json.toJSONString());
				out.flush();
				out.close();
				return null;
			}
		}else{
			json.put("success", false);
			json.put("message", "回款金额必须是数值");
			out.write(json.toJSONString());
			out.flush();
			out.close();
			return null;
		}
	}
	
	
	
}
