package com.zd.csms.supervisory.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messageAndWaring.message.service.business.BusinessMessageTypeService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.CarManualVO;
import com.zd.csms.supervisory.model.CarOperationQueryVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.CreatePdfVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.service.BankApproveService;
import com.zd.csms.supervisory.service.CarManualService;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.util.CreatePDFUtil;
import com.zd.csms.supervisory.web.form.CarOperationForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class CarOperationAction extends ActionSupport {
	
	private SuperviseImportService siservice = new SuperviseImportService();
	private CarManualService cservice = new CarManualService();
	private BankApproveService baservice = new BankApproveService();
	private BusinessMessageTypeService typeService=new BusinessMessageTypeService() ;
	
	/**
	 * 流程角色:市场部专员->招聘专员->业务部专员
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.BANK_APPROVE.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			};
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	public ActionForward storageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CarOperationForm cform = (CarOperationForm)form;
		SuperviseImportQueryVO query = cform.getSuperviseImportquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int currRole=getCurrRole(request);
		if(currRole==RoleConstants.BANK_APPROVE.getCode()){
			query.setType(2);
			query.setUserid(user.getClient_id());
			query.setState(2);
			query.setApply(1);
			query.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
		}else if(currRole==RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			query.setType(3);
			query.setUserid(user.getId());
			query.setState(2);
			query.setApply(1);
			query.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
		}
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("storageList", request);
		tools.saveQueryVO(query);
		
		List<CarInfoQueryBean> list = siservice.searchSuperviseImportList(query, tools);
		try {
			request.setAttribute("summary", siservice.getSummaryByBank(query));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		
		setOptions(request);
		
		return mapping.findForward("storage_list");
	}
	
	public ActionForward updSuperviseStorages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarOperationForm cform = (CarOperationForm)form;
		
		String carIds = request.getParameter("ids");
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int currRole=getCurrRole(request);
		int sid = 0;
		if (!StringUtil.isEmpty(carIds)) {
			String[] idArray = carIds.split(",");
			try {
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO vo = siservice.loadSuperviseImportById(Integer.parseInt(idArray[i]));
					//如果当前审批人是业务专员，修改下一审批人为银行审批人
					if(currRole==RoleConstants.BUSINESS_COMMISSIONER.getCode() 
							&& vo.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()){
						vo.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
						siservice.updSuperviseImport(vo,0);
					//如果当前审批人是银行审批人，执行其他操作
					}else if(currRole==RoleConstants.BANK_APPROVE.getCode()
							&& vo.getNextApproval()==RoleConstants.BANK_APPROVE.getCode()){
						vo.setNextApproval(0);
						vo.setApply(0);
						vo.setStoragetime(new Date());
						siservice.updSuperviseImport(vo,0);
						
						CarManualVO cvo = new CarManualVO();
						cvo.setSid(Integer.parseInt(idArray[i]));
						cvo.setUserid(user.getId());
						cvo.setReceiveid(0);
						cservice.addCarManual(cvo);
						
						//BankApproveVO bavo = new BankApproveVO();
						//bavo.setSid(Integer.parseInt(idArray[i]));
						BankApproveVO bavo = baservice.searchBankApproveBySid(Integer.parseInt(idArray[i]),2);
						if (null != bavo) {
							bavo.setState(2);
							bavo.setType(2);
							bavo.setApprovetime(new Date());
							baservice.updBankApprove(bavo);
						}
					}
					sid = Integer.parseInt(idArray[i]);
				}
				
				List<UserVO> uList = siservice.searchUserBySId(sid);
				if(uList != null && uList.size()>0){
					for(UserVO ur : uList){
						MessageUtil.addMsg(ur.getId(), "监管物入库", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARSTORAGE.getCode(),user.getId());
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return storageList(mapping, form, request, response);
	}
	
	public ActionForward updSuperviseStoragesno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarOperationForm cform = (CarOperationForm)form;
		
		String carIds = request.getParameter("ids");
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		int sid = 0;
		
		if (!StringUtil.isEmpty(carIds)) {
			String[] idArray = carIds.split(",");
			try {
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO vo = siservice.loadSuperviseImportById(Integer.parseInt(idArray[i]));
					vo.setApply(0);
					vo.setState(1);
					siservice.updSuperviseImport(vo,0);
					
					//BankApproveVO bavo = new BankApproveVO();
					//bavo.setSid(Integer.parseInt(idArray[i]));
					BankApproveVO bavo = baservice.searchBankApproveBySid(Integer.parseInt(idArray[i]),2);
					if (null != bavo) {
						bavo.setState(3);
						bavo.setType(2);
						bavo.setApprovetime(new Date());
						baservice.updBankApprove(bavo);
					}
					sid = Integer.parseInt(idArray[i]);
				}
				List<UserVO> uList = siservice.searchUserBySId(sid);
				if(uList != null && uList.size()>0){
					for(UserVO ur : uList){
						MessageUtil.addMsg(ur.getId(), "监管物入库失败", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARSTORAGE.getCode(),user.getId());
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return storageList(mapping, form, request, response);
	}
	
	
	public ActionForward outList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CarOperationForm cform = (CarOperationForm)form;
		SuperviseImportQueryVO query = cform.getSuperviseImportquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		query.setType(2);
		query.setUserid(user.getClient_id());
		query.setState(3);
		query.setApply(1);
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("outList", request);
		tools.saveQueryVO(query);
		
		List<CarInfoQueryBean> list = siservice.searchSuperviseImportList(query, tools);
		
		request.setAttribute("list", list);
		try {
			request.setAttribute("summary", siservice.getSummaryByBank(query));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setOptions(request);
		
		return mapping.findForward("out_list");
	}
	
	/**
	 * 发送消息
	 * @param name
	 * @param url
	 * @param roles
	 */
	public ActionForward updSuperviseOuts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarOperationForm cform = (CarOperationForm)form;
		String carIds = request.getParameter("ids");
		int sid = 0;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		System.out.println("1"+(new Date()).toString());
		
		if (!StringUtil.isEmpty(carIds)) {
			String[] idArray = carIds.split(",");
			try {
				if (null != idArray && idArray.length > 0) {
					List<Integer> msgOuts = new ArrayList<Integer>();
					for (int i = 0; i < idArray.length; i++) {
						SuperviseImportVO vo = siservice.loadSuperviseImportById(Integer.parseInt(idArray[i]));
						System.out.println("2"+(new Date()).toString());
						if(vo!=null){
							vo.setApply(0);
							vo.setOuttime(new Date());
							siservice.updSuperviseImport(vo,0);
							System.out.println("3"+(new Date()).toString());
							//-----------------------------银行释放审批提醒start--------------------------------
							//BusinMessageInfoVO msgOut = new BusinMessageInfoVO();
							//msgOut.setDraft_num(vo.getDraft_num());//汇票号
							//msgOut.setVin(vo.getVin());//车架号
							//msgOut.setPrice(vo.getPrice());//价格
							//-----------------------------银行释放审批提醒end----------------------------------
							BankApproveVO bavo = baservice.searchBankApproveBySid(Integer.parseInt(idArray[i]),3);
							System.out.println("4"+(new Date()).toString());
							if (null != bavo) {
								bavo.setState(2);//状态(1.待审批2.审核通过3.审核不通过)
								bavo.setType(3);
								bavo.setApprovetime(new Date());
								baservice.updBankApprove(bavo);
								msgOuts.add(Integer.parseInt(idArray[i])) ;
							}
							System.out.println("5"+(new Date()).toString());
							
						}
						sid = Integer.parseInt(idArray[i]);
					}
					typeService.msgBankOutStorage(msgOuts,2) ;//状态(1.待审批2.审核通过3.审核不通过)
					System.out.println("6"+(new Date()).toString());
				}
				
				List<UserVO> uList = siservice.searchUserBySId(sid);
				if(uList != null && uList.size()>0){
					for(UserVO ur : uList){
						MessageUtil.addMsg(ur.getId(), "监管物出库", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CAROUT.getCode(),user.getId());
						MessageUtil.sendMsg(new String[]{RoleConstants.BANK_APPROVE.getCode()+""}, "监管物出库", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CAROUT.getCode(),user.getId());
					}
				}
				System.out.println("7"+(new Date()).toString());
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return outList(mapping, form, request, response);
	}
	
	public ActionForward updSuperviseOutsno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarOperationForm cform = (CarOperationForm)form;
		String carIds = request.getParameter("ids");
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int sid = 0;
		if (!StringUtil.isEmpty(carIds)) {
			String[] idArray = carIds.split(",");
			try {
			 if (null != idArray && idArray.length > 0) { 
				 List<Integer> msgOuts = new ArrayList<Integer>();
					for (int i = 0; i < idArray.length; i++) {
						SuperviseImportVO vo = siservice.loadSuperviseImportById(Integer.parseInt(idArray[i]));
						if(vo!=null){
							if (vo.getIdentifi()==1) {//在库
								vo.setApply(0);
								vo.setState(2);
								vo.setIdentifi(1);
							}if (vo.getIdentifi()==2) {//移动
								vo.setApply(0);
								vo.setState(4);
								vo.setIdentifi(2);
							}
							siservice.updSuperviseImport(vo,0);
							
							//-----------------------------银行释放审批提醒start--------------------------------
							//BusinMessageInfoVO msgOut = new BusinMessageInfoVO();
							//msgOut.setDraft_num(vo.getDraft_num());//汇票号
							//msgOut.setVin(vo.getVin());//车架号
							//msgOut.setPrice(vo.getPrice());//价格
							//-----------------------------银行释放审批提醒end--------------------------------
							BankApproveVO bavo = baservice.searchBankApproveBySid(Integer.parseInt(idArray[i]),3);
							if (null != bavo) {
								bavo.setState(3);
								//状态(1.待审批2.审核通过3.审核不通过)
								bavo.setType(3);
								bavo.setApprovetime(new Date());
								baservice.updBankApprove(bavo);
								msgOuts.add(Integer.parseInt(idArray[i])) ;
							}
								
							sid = Integer.parseInt(idArray[i]);
						}
						
					}
					typeService.msgBankOutStorage(msgOuts,3) ;//状态(1.待审批2.审核通过3.审核不通过)
					List<UserVO> uList = siservice.searchUserBySId(sid);
					if(uList != null && uList.size()>0){
						for(UserVO ur : uList){
							MessageUtil.addMsg(ur.getId(), "监管物出库失败", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CAROUT.getCode(),user.getId());
							MessageUtil.sendMsg(new String[]{RoleConstants.BANK_APPROVE.getCode()+""}, "监管物出库失败", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CAROUT.getCode(),user.getId());
						}
					}
					
			 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return outList(mapping, form, request, response);
	}
	
	public ActionForward moveList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CarOperationForm cform = (CarOperationForm)form;
		SuperviseImportQueryVO query = cform.getSuperviseImportquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		query.setType(2);
		query.setUserid(user.getClient_id());
		query.setState(4);
		query.setApply(1);
		
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("moveList", request);
		tools.saveQueryVO(query);
		
		List<CarInfoQueryBean> list = siservice.searchSuperviseImportList(query, tools);

		request.setAttribute("list", list);
		try {
			request.setAttribute("summary", siservice.getSummaryByBank(query));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setOptions(request);
		
		return mapping.findForward("move_list");
	}
	
	
	public ActionForward updSuperviseMoves(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarOperationForm cform = (CarOperationForm)form;
		String carIds = request.getParameter("ids");
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int sid = 0;
		
		if (!StringUtil.isEmpty(carIds)) {
			String[] idArray = carIds.split(",");
		  try {
			 if(null != idArray && idArray.length > 0){
				List<Integer> msgOuts = new ArrayList<Integer>();
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO vo = siservice.loadSuperviseImportById(Integer.parseInt(idArray[i]));
					if(vo!=null){
						vo.setApply(0);
						//vo.setState(4);
						
						//判断是否是移动到本库
						//1是  将状态改为2
						
						
						if(StringUtil.isNotEmpty(vo.getIsmovetoone()) && vo.getIsmovetoone().equals("1")){
							vo.setState(2);
						}
						
						vo.setMovetime(new Date());
						siservice.updSuperviseImport(vo,0);
						
						//-----------------------------银行移动审批提醒start--------------------------------
						//BusinMessageInfoVO msgOut = new BusinMessageInfoVO();
						//msgOut.setVin(vo.getVin());//车架号
						//msgOut.setPrice(vo.getPrice());//价格
						//msgOut.setDraft_num(vo.getDraft_num());
						//msgOut.setMoveAddress(vo.getTwo_name());
						//-----------------------------银行移动审批提醒end----------------------------------
						
						BankApproveVO bavo = baservice.searchBankApproveBySid(Integer.parseInt(idArray[i]),4);
						if (null != bavo) {
							bavo.setState(2);//状态(1.待审批2.审核通过3.审核不通过)
							bavo.setType(4);
							bavo.setApprovetime(new Date());
							baservice.updBankApprove(bavo);
						}
						msgOuts.add(Integer.parseInt(idArray[i]));
						sid = Integer.parseInt(idArray[i]);
					}
				}
				
				typeService.msgBankMoveStorage(msgOuts,2);//状态(1.待审批2.审核通过3.审核不通过)
				List<UserVO> uList = siservice.searchUserBySId(sid);
				if(uList != null && uList.size()>0){
					for(UserVO ur : uList){
						MessageUtil.addMsg(ur.getId(), "监管物移动", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARMOVE.getCode(),user.getId());
						MessageUtil.sendMsg(new String[]{RoleConstants.BANK_APPROVE.getCode()+""}, "监管物移动", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARMOVE.getCode(),user.getId());
					}
				}
			  }
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return moveList(mapping, form, request, response);
	}
	
	public ActionForward updSuperviseMovesno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CarOperationForm cform = (CarOperationForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String carIds = request.getParameter("ids");
		
		int sid = 0;
		
		if (!StringUtil.isEmpty(carIds)) {
			String[] idArray = carIds.split(",");
			try {
				if(null != idArray && idArray.length > 0){
					List<Integer> msgOuts = new ArrayList<Integer>();
					for (int i = 0; i < idArray.length; i++) {
						SuperviseImportVO vo = siservice.loadSuperviseImportById(Integer.parseInt(idArray[i]));
						if(vo!=null){
							vo.setApply(0);
							if (vo.getIdentifi()==2) {
								vo.setState(4);
							}else if (vo.getIdentifi()==1) {
								vo.setState(2);
							}	
							//vo.setState(2);
							vo.setBond("");
							vo.setTwo_name("");
							siservice.updSuperviseImport(vo,0);
							//-----------------------------银行移动审批提醒start--------------------------------
							//BusinMessageInfoVO msgOut = new BusinMessageInfoVO();
							//msgOut.setVin(vo.getVin());//车架号
							//msgOut.setPrice(vo.getPrice());//价格
							//msgOut.setDraft_num(vo.getDraft_num());
							//msgOut.setMoveAddress(vo.getTwo_name());
							//-----------------------------银行移动审批提醒end----------------------------------
							BankApproveVO bavo = baservice.searchBankApproveBySid(Integer.parseInt(idArray[i]),4);
							if (null != bavo) {
								bavo.setState(3);//状态(1.待审批2.审核通过3.审核不通过)
								bavo.setType(4);
								bavo.setApprovetime(new Date());
								baservice.updBankApprove(bavo);
								msgOuts.add(Integer.parseInt(idArray[i])) ;
							}
							
							sid = Integer.parseInt(idArray[i]);
						}
						
					}
					typeService.msgBankMoveStorage(msgOuts,3);//状态(1.待审批2.审核通过3.审核不通过)
					List<UserVO> uList = siservice.searchUserBySId(sid);
					if(uList != null && uList.size()>0){
						for(UserVO ur : uList){
							MessageUtil.addMsg(ur.getId(), "监管物移动失败", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARMOVE.getCode(),user.getId());
							MessageUtil.sendMsg(new String[]{RoleConstants.BANK_APPROVE.getCode()+""}, "监管物移动失败", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARMOVE.getCode(),user.getId());
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return moveList(mapping, form, request, response);
	}
	
	
	public ActionForward zaicarOperationList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CarOperationForm cform = (CarOperationForm)form;
		CarOperationService service = new CarOperationService();
		
		CarOperationQueryVO cQuery = cform.getCarOperationquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
			cQuery.setUserid(user.getId());
		}
		
		cQuery.setType(2);
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("zaicarOperationList",request);
		thumbPageTools.saveQueryVO(cQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<CarOperationVO> list = service.searchCarOperationListByPage(cQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("zai_car_list");
	}
	
	public ActionForward outcarOperationList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CarOperationForm cform = (CarOperationForm)form;
		CarOperationService service = new CarOperationService();
		
		CarOperationQueryVO cQuery = cform.getCarOperationquery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
			cQuery.setUserid(user.getId());
		}
		cQuery.setType(3);
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("outcarOperationList",request);
		thumbPageTools.saveQueryVO(cQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<CarOperationVO> list = service.searchCarOperationListByPage(cQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("out_car_list");
	}
	
	public ActionForward movecarOperationList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CarOperationForm cform = (CarOperationForm)form;
		CarOperationService service = new CarOperationService();
		
		CarOperationQueryVO cQuery = cform.getCarOperationquery();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(user.getClient_type() == ClientTypeConstants.SUPERVISORY.getCode()){
			cQuery.setUserid(user.getId());
		}
		cQuery.setType(4);
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("movecarOperationList",request);
		thumbPageTools.saveQueryVO(cQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<CarOperationVO> list = service.searchCarOperationListByPage(cQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("move_car_list");
	}
	
	public ActionForward zaiDownLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		
		String idstr = request.getParameter("id");
		int id = 0;
		if(!StringUtil.isEmpty(idstr)){
			id = Integer.parseInt(idstr);
			CreatePdfVO cvo = CreatePDFUtil.jgqrs(id, request);
			if(cvo != null){
				request.setAttribute("path", cvo.getUrl());
				request.setAttribute("fileName", cvo.getName());
			}
			
			
			return mapping.findForward("down_upfile");
		}else{
			return null;
		}
		
	}
	
	public ActionForward clysDownLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		
		String idstr = request.getParameter("id");
		int id = 0;
		if(!StringUtil.isEmpty(idstr)){
			id = Integer.parseInt(idstr);
			CreatePdfVO cvo = CreatePDFUtil.clysjj(id, request);
			if(cvo != null){
				request.setAttribute("path", cvo.getUrl());
				request.setAttribute("fileName", cvo.getName());
			}
			return mapping.findForward("down_upfile");
		}else{
			return null;
		}
		
	}
	
	public ActionForward sgtzDownLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		
		String idstr = request.getParameter("id");
		int id = 0;
		if(!StringUtil.isEmpty(idstr)){
			id = Integer.parseInt(idstr);
			CreatePdfVO cvo = CreatePDFUtil.sgtz(id, request);
			if(cvo != null){
				request.setAttribute("path", cvo.getUrl());
				request.setAttribute("fileName", cvo.getName());
			}
			return mapping.findForward("down_upfile");
		}else{
			return null;
		}
		
	}
	public ActionForward jgwsfDownLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		
		String idstr = request.getParameter("id");
		int id = 0;
		if(!StringUtil.isEmpty(idstr)){
			id = Integer.parseInt(idstr);
			CreatePdfVO cvo = CreatePDFUtil.jgwsf(id, request);
			if(cvo != null){
				request.setAttribute("path", cvo.getUrl());
				request.setAttribute("fileName", cvo.getName());
			}
			return mapping.findForward("down_upfile");
		}else{
			return null;
		}
		
	}
	
	public ActionForward jgwydDownLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		
		String idstr = request.getParameter("id");
		int id = 0;
		if(!StringUtil.isEmpty(idstr)){
			id = Integer.parseInt(idstr);
			CreatePdfVO cvo = CreatePDFUtil.jgwyd(id, request);
			if(cvo != null){
				request.setAttribute("path", cvo.getUrl());
				request.setAttribute("fileName", cvo.getName());
			}
			return mapping.findForward("down_upfile");
		}else{
			return null;
		}
		
	}
	
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		
	}
}
