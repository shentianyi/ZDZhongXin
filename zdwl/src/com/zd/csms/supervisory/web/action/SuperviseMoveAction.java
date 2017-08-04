package com.zd.csms.supervisory.web.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.alibaba.fastjson.JSONObject;
import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.business.service.TwoAddressService;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.contants.MsgUrlContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.model.movestock.SupervisorMoveStockMessageVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.service.BankApproveService;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.service.movestock.SupervisorMoveStockMessageService;
import com.zd.csms.supervisory.web.form.SuperviseImportForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 移动
 *
 */
public class SuperviseMoveAction extends ActionSupport {
	private SuperviseImportService service = new SuperviseImportService();
	
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

	public ActionForward superviseMoveListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SuperviseImportVO vo=new SuperviseImportVO();
		if (vo.getState()==2) {
			vo.setIdentifi(1);
		}else if (vo.getState()==4) {
			vo.setIdentifi(2);
			
		}
		return superviseMoveList(mapping, form,request, response);
	}
	
	
	public ActionForward superviseMoveList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm)form;
		
		
		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setStates("2,4");
		siQuery.setApply(0);
		if (siform.getSuperviseImport().getState()==2) {
			siform.getSuperviseImport().setIdentifi(1);
		}
		if (siform.getSuperviseImport().getState()==4) {
			siform.getSuperviseImport().setIdentifi(2);
		}
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
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseMoveList",request);
		thumbPageTools.setPageSize(50);
		//thumbPageTools.saveQueryVO(siQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		//返回列表页面
		return mapping.findForward("supervise_move_list");
	}
	
	public ActionForward superviseMoveNowList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		SuperviseImportForm siform = (SuperviseImportForm)form;
		
		SuperviseImportQueryVO siQuery = siform.getSuperviseImportquery();
		siQuery.setStates("4");//只查询状态为移动申请中的车
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
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseMoveNowList",request);
		thumbPageTools.saveQueryVO(siQuery);//记录查询条件,用于查询条件变更时返回第一页
		thumbPageTools.setPageSize(50);
		//按条件查询分页数据
		List<CarInfoQueryBean> list = service.searchSuperviseImportList(siQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		setOptions(request);
		request.setAttribute("draftOptions", OptionUtil.draftsOptions(user,currRole));
		//返回列表页面
		return mapping.findForward("supervise_move_now_list");
	}
	
	
	public ActionForward updSuperviseMoveEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		
		//根据id获取修改对象
		SuperviseImportVO vo = service.loadSuperviseImportById(siform.getSuperviseImport().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return superviseMoveList(mapping, form, request, response);
		}
		
		siform.setSuperviseImport(vo);
		
		//返回修改页面
		return mapping.findForward("upd_supervise_move");
	}
	
	public ActionForward updSuperviseMove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		SuperviseImportVO sivo = siform.getSuperviseImport();
		sivo.setUpduserid(user.getId());
		sivo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updSuperviseImport(sivo,4);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return superviseMoveList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_supervise_move");
	}
	
	public ActionForward updSuperviseMoves(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BankApproveService baservice = new BankApproveService();
		CarOperationService coservice = new CarOperationService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		String carIds = request.getParameter("ids");
		String addressId = request.getParameter("ta");//twoAddress 二网地址
		TwoAddressVO tavo = new TwoAddressVO();
		String twoname = "";
		int sid = 0;
		if(!StringUtil.isEmpty(addressId)){
			TwoAddressService taservice = new TwoAddressService();
			tavo = taservice.loadTwoAddressById(Integer.parseInt(addressId));
			twoname = tavo.getTwo_name();
			if (!StringUtil.isEmpty(carIds)) {
				String[] idArray = carIds.split(",");
				String id = "";
				if(tavo.getType()==1){//移动到本库
					for (int i = 0; i < idArray.length; i++) {
						SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(idArray[i]));
						vo.setTwo_name("");
						vo.setState(4);
						vo.setBond(vo.getBond());
						vo.setAddressId(null);
						vo.setApply(1);
						vo.setUpduserid(user.getId());
						vo.setUpddate(new Date());
						vo.setIdentifi(1);
						vo.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
						vo.setIsmovetoone("1");
						
						service.updSuperviseImport(vo,0);
						id = idArray[i];
					}
				}else{
						for (int i = 0; i < idArray.length; i++) {
							SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(idArray[i]));
							vo.setTwo_name(twoname);
							if (vo.getState()==2) {
								vo.setIdentifi(1);
							}else if (vo.getState()==4) {
								vo.setIdentifi(2);
							}
							vo.setApply(1);
							vo.setState(4);
							vo.setUpduserid(user.getId());
							vo.setUpddate(new Date());
							vo.setAddressId(tavo.getId());
							service.updSuperviseImport(vo,0);
							id = idArray[i];
						}
				}
							sid = Integer.parseInt(id);
							
							
							BankApproveVO bavo = baservice.searchBankApproveBySid(sid,4);
							if (null != bavo) {
								bavo.setState(1);
								bavo.setType(4);
								bavo.setCreatetime(new Date());
								baservice.updBankApprove(bavo);
							}else{
								bavo = new BankApproveVO();
								bavo.setSid(sid);
								bavo.setState(1);
								bavo.setType(4);
								bavo.setCreatetime(new Date());
								baservice.addBankApprove(bavo);
							}
							
					
						
						int did = 0;
						String siid = idArray[0];
						SuperviseImportVO svo = service.loadSuperviseImportById(Integer.parseInt(siid));
						DraftService ds = new DraftService();
						DraftQueryVO drquery = new DraftQueryVO();
						drquery.setDraft_num(svo.getDraft_num());
						List<DraftVO> drList = ds.searchDraftList(drquery);
						DraftVO df = null;
						if(drList != null && drList.size()>0){
							df = drList.get(0);
							did =df.getDistribid();
						}
						CarOperationVO covo = new CarOperationVO();
						covo.setCars(carIds);
						covo.setType(4);
						covo.setDistribid(did);
						covo.setUserid(user.getId());
						covo.setCreatetime(new Date());
						coservice.addCarOperation(covo);
						
						
						
						DealerService dService = new DealerService();
						BrandService bService = new BrandService();
						DealerVO dVo = new DealerVO();
						SupervisorMoveStockMessageVO sosvo = new SupervisorMoveStockMessageVO();
						if (drList != null && drList.size() > 0) {
							//金融结构
							sosvo.setBankname(df.getFinancial_institution());
							sosvo.setIsread(MsgIsContants.NOREAD.getCode());
							sosvo.setMessagetype(MessageTypeContant.SUPSERVIMOVETSTOCK.getName());
							sosvo.setCreateDate(new Date());
							//经销商
							dVo = dService.get(did);
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
					    
						SuperviseImportService serviceIs = new SuperviseImportService();
						List<SuperviseImportVO> cars = serviceIs.findListByIds(idArray);
					    for(SuperviseImportVO superviseImportVO : cars) {
							BrandVO vo = bService.loadBrandById(superviseImportVO.getBrandid());
							if (vo != null) {
								sosvo.setBrandname(vo.getName());
							}
							sosvo.setImportId(superviseImportVO.getId());
							sosvo.setDraft_num(superviseImportVO.getDraft_num());
							sosvo.setVin(superviseImportVO.getVin());
							sosvo.setMoveTime(superviseImportVO.getMovetime());
							sosvo.setMoveaddress(tavo.getTwo_address());
							addSRCMmessage(urList,sosvo);
					     }
						
						
						List<UserVO> uYList = service.searchUserByYWId(sid);
						if(uYList != null && uYList.size()>0){
							for(UserVO ur : uYList){
								MessageUtil.addMsg(ur.getId(), "监管物移动超出比例", "/carOperation.do?method=moveList",1,MessageTypeContant.CARMOVE.getCode(),user.getId());
							}
						}
			
				
			}
		}
		return superviseMoveList(mapping, form, request, response);
	}
	
	
	 //申请信息提醒
	private boolean addSRCMmessage(List<UserRoleVO> urList,SupervisorMoveStockMessageVO srcmvo) throws Exception{
		MessageService ms = new MessageService();
		SupervisorMoveStockMessageService srcmService = new SupervisorMoveStockMessageService();
		UserService userService = new UserService();
		UserVO userVO = null;
		boolean flag = false;
		if(urList != null && urList.size()>0){
			for(UserRoleVO ur : urList){
				userVO  = userService.get(ur.getUser_id());
				if (userVO != null && userVO.getState() == 1) {
					MessageVO msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVIMOVETSTOCK.getCode());
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
							flag = srcmService.addMoveStockMessage(srcmvo);
						}else {
							return false;
						}
					}else {
						MessageUtil.addMsg(ur.getUser_id(),String.valueOf(1), MsgUrlContant.SUPERRVISEMOVESTOCK.getName(), 1,MsgIsContants.NOREAD.getCode(),MessageTypeContant.SUPSERVIMOVETSTOCK.getCode(),ClientTypeConstants.SUPERVISORY.getName());
						msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.SUPSERVIMOVETSTOCK.getCode());
						srcmvo.setUserId(ur.getUser_id());
						srcmvo.setMessageId(msVo.getId());
						flag = srcmService.addMoveStockMessage(srcmvo);
					}
				}
			}
		}
		return flag;
	}
	
	public ActionForward delSuperviseMove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseImportForm siform = (SuperviseImportForm)form;

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSuperviseImport(siform.getSuperviseImport().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return superviseMoveList(mapping, form, request, response);
	}
	
	public ActionForward chooseTwoAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//UserVO user = UserSessionUtil.getUserSession(request).getUser();
		//request.setAttribute("twoAddressOptions", OptionUtil.getTwoAddress(user));
		
		//修改为获取本次移动的监管物所属的经销商的二网名称。
		String dealerId = (String) request.getParameter("dealerId");
		String ewName = (String) request.getParameter("ewName");
		List<OptionObject> list = OptionUtil.getAllAddress(Integer.parseInt(dealerId));
		if(ewName != null && !"".equals(ewName.trim())){
			for (int i = 0;i < list.size();i++) {
				if(ewName.trim().equals(list.get(i).getLabel())){
					list.remove(i);
				}
			}
		}
		request.setAttribute("twoAddressOptions", list);
		//返回列表页面
		return mapping.findForward("choose_two_address");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		
	}
	
	public ActionForward updatebond(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		String bond=request.getParameter("bond");
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(NumberUtils.isNumber(bond)||StringUtil.isEmpty(bond)){
			SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(id));
			vo.setBond(bond);
			vo.setUpddate(new Date());
			vo.setUpduserid(user.getId());
			boolean result = service.updSuperviseImport(vo,4);
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
			json.put("message", "保证金必须是数值");
			out.write(json.toJSONString());
			out.flush();
			out.close();
			return null;
		}
	}
}
