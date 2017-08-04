package com.zd.csms.supervisory.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.set.contants.BankDockType;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.supervisory.contants.CheckStockCarOperationContants;
import com.zd.csms.supervisory.model.CheckStockCarBean;
import com.zd.csms.supervisory.model.CheckStockCarUpdateVO;
import com.zd.csms.supervisory.model.CheckStockCarVO;
import com.zd.csms.supervisory.model.CheckStockManageBean;
import com.zd.csms.supervisory.model.CheckStockManageQueryVO;
import com.zd.csms.supervisory.model.CheckStockManageVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.CheckStockManageService;
import com.zd.csms.supervisory.web.excel.CheckStockManageRowMapper;
import com.zd.csms.supervisory.web.form.CheckStockManageForm;
import com.zd.csms.windcontrol.model.Image;
import com.zd.csms.zxbank.dock.ZXBankDockImpl;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class CheckStockManageAction extends ActionSupport{
	private static Log log = LogFactory.getLog(ZXBankDockImpl.class);
	private CheckStockManageService service = new CheckStockManageService();
	
	/**
	 * 分页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		CheckStockManageQueryVO query = checkStockManageForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<CheckStockManageBean> list = service.findCheckStockManage(query, tools,request);
		tools.saveQueryVO(query);
		request.setAttribute("list", list);
		initOption(request);
		return mapping.findForward("checkStock_manage_list");
	}
	
	public ActionForward downloadPictures(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response){
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		service.downloadFiles(checkStockManageForm.getCheckStockBean().getId(),checkStockManageForm.getCheckStockBean().getDealerName(),response);
		return null;
	}
	
	
	/**
	 * 上传日查库照片
	 * @number:	31
	 * @author:		sxs
	 * @describe:
	 * @param:
	 * @return:
	 */
	public ActionForward addPicture(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response){
		Hashtable fileElements = form.getMultipartRequestHandler().getFileElements();
		FormFile file = (FormFile) fileElements.get("files[]");
		 
		String idStr = request.getParameter("id");
		int id = 0;
		if(StringUtil.isNumber(idStr)){
			id = Integer.parseInt(idStr);
		}
		if (file == null || id == 0) {
			return null;
		}
		boolean flag = service.addPic(file, request,id);
		System.out.println(file.getFileName().trim()+"\t上传成功");
		PrintWriter pw = null;
		try {
			if(!flag){
				response.setStatus(500);
			}
			 pw = getWrite(response);
			pw.write(service.getExecuteMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.flush();
				pw.close();
			}
		}
		
		return null;
	}
	

	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<CheckStockCarBean> cars=service.findCheckStockCarList(checkStockManageForm.getCheckStock().getId(), tools);
		tools.saveQueryVO(checkStockManageForm);
		request.setAttribute("checkStockCars",cars);
		initOption(request);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		CheckStockManageVO checkStock=checkStockManageForm.getCheckStock();
		int dealerId=checkStock.getDealerid();
		checkStock.setDealerid(dealerId);
		int[] carStatus=checkStockManageForm.getQuery().getCarStatus();
		//全部日查库车辆信息
		List<CheckStockCarVO> checkStockCars=new ArrayList<CheckStockCarVO>();
		//在库本库
		List<SuperviseImportVO> whsuperviseImportList=new ArrayList<SuperviseImportVO>();
		//在库二库
		List<SuperviseImportVO> twowhsuperviseImportList=new ArrayList<SuperviseImportVO>();
		// 在库移动
		List<SuperviseImportVO> movesuperviseImportList=new ArrayList<SuperviseImportVO>();
		// 在库车辆
		List<SuperviseImportVO> allwhsuperviseImportList = new ArrayList<SuperviseImportVO>();
		if(carStatus!=null && carStatus.length>0){
			List<CheckStockCarVO> checkStockCarList=new ArrayList<CheckStockCarVO>();
			for(int status:carStatus){
				if(status==CheckStockCarOperationContants.WH.getCode()){
					whsuperviseImportList=service.findSuperviseImportList(dealerId,status);
					checkStockCarList=assemCheckStockCar(whsuperviseImportList,CheckStockCarOperationContants.WH.getCode());
				}else if(status==CheckStockCarOperationContants.TWO_WH.getCode()){
					twowhsuperviseImportList=service.findSuperviseImportList(dealerId,status);
					checkStockCarList=assemCheckStockCar(twowhsuperviseImportList,CheckStockCarOperationContants.TWO_WH.getCode());
				}else if(status==CheckStockCarOperationContants.MOVE.getCode()){
					movesuperviseImportList=service.findSuperviseImportList(dealerId,status);
					checkStockCarList=assemCheckStockCar(movesuperviseImportList,CheckStockCarOperationContants.MOVE.getCode());
				}
				checkStockCars.addAll(checkStockCarList);
			}
		}else{
			whsuperviseImportList=service.findSuperviseImportList(dealerId,CheckStockCarOperationContants.WH.getCode());
			List<CheckStockCarVO> whcheckStockCarList=assemCheckStockCar(whsuperviseImportList,CheckStockCarOperationContants.WH.getCode());
			twowhsuperviseImportList=service.findSuperviseImportList(dealerId,CheckStockCarOperationContants.TWO_WH.getCode());
			List<CheckStockCarVO> twowhcheckStockCarList=assemCheckStockCar(twowhsuperviseImportList,CheckStockCarOperationContants.TWO_WH.getCode());
			movesuperviseImportList=service.findSuperviseImportList(dealerId,CheckStockCarOperationContants.MOVE.getCode());
			List<CheckStockCarVO> movecheckStockCarList=assemCheckStockCar(movesuperviseImportList,CheckStockCarOperationContants.MOVE.getCode());
			checkStockCars.addAll(whcheckStockCarList);
			checkStockCars.addAll(twowhcheckStockCarList);
			checkStockCars.addAll(movecheckStockCarList);
			
		}
		allwhsuperviseImportList.addAll(whsuperviseImportList);
		allwhsuperviseImportList.addAll(twowhsuperviseImportList);
		allwhsuperviseImportList.addAll(movesuperviseImportList);
		if(allwhsuperviseImportList==null || allwhsuperviseImportList.size()==0){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "当前条件下没有车辆信息！");
			checkStockManageForm.getQuery().setCarStatus(carStatus);
			return preAdd(mapping, checkStockManageForm, request, response);
		}
		checkStock.setAll_wh_count(allwhsuperviseImportList.size());
		checkStock.setWh_count(whsuperviseImportList.size());
		checkStock.setTwo_wh_count(twowhsuperviseImportList.size());
		checkStock.setMove_count(movesuperviseImportList.size());
		checkStock.setCheck_date(new Date());
		//提交状态 1：未提交 ，2：已提交
		checkStock.setSubmitflag(1);
		//查库方式1：手工查库，2：设备查库
		checkStock.setCheckStockType(1);
		checkStock.setCreatedate(new Date());
		checkStock.setCreateuser(UserSessionUtil.getUserSession(request).getUser().getId());
		checkStock.setUpdatedate(new Date());
		checkStock.setUpdateuser(UserSessionUtil.getUserSession(request).getUser().getId());
		int checkStockId=service.add(checkStock);
		boolean flag;
		if(checkStockId>0){
			flag=true;
			if(checkStockCars!=null && checkStockCars.size()>0){
				for(CheckStockCarVO vo:checkStockCars){
					vo.setCheckstock_id(checkStockId);
					flag=flag && service.addCheckStockCar(vo);
					if(!flag){
						break;
					}
				}
			}
		}else{
			flag=false;
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		checkStockManageForm.getQuery().setCarStatus(carStatus);
		return preAdd(mapping, checkStockManageForm, request, response);
	}
	public List<CheckStockCarVO> assemCheckStockCar(List<SuperviseImportVO> superviseImportList,int status){
		List<CheckStockCarVO> checkStockCarList=new ArrayList<CheckStockCarVO>();
		if(superviseImportList!=null && superviseImportList.size()>0){
			for(SuperviseImportVO superviseImport:superviseImportList){
				CheckStockCarVO checkStockCar=new CheckStockCarVO();
				checkStockCar.setVin(superviseImport.getVin());
				checkStockCar.setModel(superviseImport.getCar_model());
				checkStockCar.setColor(superviseImport.getColor());
				checkStockCar.setCurrstate(status);
				checkStockCar.setActualstate(status);
				checkStockCarList.add(checkStockCar);
			}
		}
		return checkStockCarList;
	}
	
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		int checkstockId=checkStockManageForm.getCheckStock().getId();
		CheckStockManageBean checkStockBean = service.getCheckStockManageBranById(checkstockId);
		List<CheckStockCarBean> checkStockCarBeans=service.findCheckStockCarList(checkstockId);
 		request.setAttribute("checkStockCarBeans", checkStockCarBeans);
		request.setAttribute("checkStockCarBeansSize", checkStockCarBeans.size());
		checkStockManageForm.setCheckStockBean(checkStockBean);
		checkStockManageForm.setCheckStockCarBeans(checkStockCarBeans);
		List<Image> list = service.showPic(checkstockId);
		request.setAttribute("list", list);
		initOption(request);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json=request.getParameter("carJson");
		List<CheckStockCarUpdateVO> carList = JSON.parseArray(json,CheckStockCarUpdateVO.class);
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		CheckStockManageBean bean = checkStockManageForm.getCheckStockBean();
		boolean flag=true;
		//实盘在库
		int actual_all_wh_count=0;
	 	//实盘本库
		int actual_wh_count=0;
		//实盘二库
		int actual_two_wh_count=0;
		//实盘移动
		int actual_move_count=0;
		//实盘出库
		int actual_out_count=0;
		/**
		 * 私自移动
		 */
		 int secretly_move_count = 0;
		/**
		 * 私自销售
		 */
		 int secretly_sale_count = 0;
		/**
		 * 在途销售
		 */
		 int way_sale_count = 0;
		/**
		 * 在途移动
		 */
		 int way_move_count = 0;
		/**
		 * 信誉额度
		 */
		 int credit_line_count = 0;
		/**
		 * 展厅
		 */
		 int exhibition_room_count = 0;
		/**
		 * 异常合计
		 */
		 int actual_abnormal_count=0;
		if(carList!=null && carList.size()>0){
			for(CheckStockCarUpdateVO carbean:carList){
				
				switch(carbean.getActualstate()){
					case 1:
						actual_wh_count++;
						actual_all_wh_count++;
						break;
					case 2:
						actual_two_wh_count++;
						actual_all_wh_count++;
						break;
					case 3:
						actual_move_count++;
						actual_all_wh_count++;
						break;
					case 4:
						actual_out_count++;
						actual_all_wh_count++;
						break;
					case 5:
						secretly_move_count++;
						actual_abnormal_count++;
						break;
					case 6:
						secretly_sale_count++;
						actual_abnormal_count++;
						break;
					case 7:
						way_sale_count++;
						actual_abnormal_count++;
						break;
					case 8:
						way_move_count++;
						actual_abnormal_count++;
						break;
					case 9:
						credit_line_count++;
						actual_abnormal_count++;
						break;
					case 10:
						exhibition_room_count++;
						break;
					default:
						break;
						
				}
				
				
				
				
				/*//车辆实际状态 1:本库  2：二库 3：移动 4：出库
				if(carbean.getActualstate()==1){
					actual_wh_count++;
					actual_all_wh_count++;
				}else if(carbean.getActualstate()==2){
					actual_two_wh_count++;
					actual_all_wh_count++;
				}else if(carbean.getActualstate()==3){
					actual_move_count++;
					actual_all_wh_count++;
				}else if(carbean.getActualstate()==4){
					actual_out_count++;
					actual_all_wh_count++;
				}else if(carbean.getActualstate()>4){
					actual_abnormal_count++;
				}*/
				flag=flag && service.updateCheckStockCarActualstate(carbean.getId(), carbean.getActualstate(),carbean.getRemark());
			}
		}
		if(flag){
			bean.setActual_all_wh_count(actual_all_wh_count);
			bean.setActual_wh_count(actual_wh_count);
			bean.setActual_two_wh_count(actual_two_wh_count);
			bean.setActual_move_count(actual_move_count);
			bean.setActual_out_count(actual_out_count);
			bean.setActual_abnormal_count(actual_abnormal_count);
			bean.setSecretly_move_count(secretly_move_count);
			bean.setSecretly_sale_count(secretly_sale_count);
			bean.setWay_sale_count(way_sale_count);
			bean.setWay_move_count(way_move_count);
			bean.setCredit_line_count(credit_line_count);
			bean.setExhibition_room_count(exhibition_room_count);
			bean.setUpdatedate(new Date());
			bean.setUpdateuser(UserSessionUtil.getUserSession(request).getUser().getId());
			service.updateCheckStockManage(bean);
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findList(mapping, checkStockManageForm, request, response);
	}
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		int checkstockId=checkStockManageForm.getCheckStock().getId();
		CheckStockManageBean checkStockBean = service.getCheckStockManageBranById(checkstockId);
		List<CheckStockCarBean> checkStockCarBeans=service.findCheckStockCarList(checkstockId);
		IExportFile export = new ExportFileExcelImpl();
		String titleName=checkStockBean.getDealerName()+"日查库盘点表";
		try {
			export.export(checkStockCarBeans, new CheckStockManageRowMapper(), export.createDefaultFileName("日查库盘点表"),titleName, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回列表页面
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		CheckStockManageVO bean = checkStockManageForm.getCheckStock();
		service.delete(bean);
		return findList(mapping, checkStockManageForm, request, response);
	}
	
	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		int checkstockId=checkStockManageForm.getCheckStock().getId();
		
		//银行接口开始
		CheckStockManageBean checkStockBean = service.getCheckStockManageBranById(checkstockId);
		List<CheckStockCarBean> checkStockCarBeans=service.findCheckStockCarList(checkstockId);
		DistribsetService distribsetService = new DistribsetService();
		log.info("checkID:"+checkStockBean.getDealerid());
		DistribsetVO distribsetVO =  distribsetService.getDistribsetVOByDistribid(checkStockBean.getDealerid());
		log.info("distribsetVO:"+distribsetVO.toString());
		if(distribsetVO!=null&&distribsetVO.getBankDockType()!=null&&distribsetVO.getBankDockType()!=0){

			ZXBankDockImpl bankDock = null;
			if(distribsetVO.getBankDockType()==BankDockType.ZXBANK.getCode()){
				log.info("判断成功2");
				bankDock = new ZXBankDockImpl();
			}
			if(bankDock!=null){
				log.info("判断成功3");
				boolean result = bankDock.checkStock(distribsetVO,checkStockBean,checkStockCarBeans,request);
				if(!result){
					return findList(mapping, form, request, response);
				}
			}
		}
		//银行接口结束
		
		boolean flag=service.submit(checkstockId);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findList(mapping, checkStockManageForm, request, response);
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
	public ActionForward detailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CheckStockManageForm checkStockManageForm = (CheckStockManageForm) form;
		int checkstockId=checkStockManageForm.getCheckStock().getId();
		CheckStockManageBean checkStockBean = service.getCheckStockManageBranById(checkstockId);
		List<CheckStockCarBean> checkStockCarBeans=service.findCheckStockCarList(checkstockId);
		request.setAttribute("checkStockCarBeans", checkStockCarBeans);
		request.setAttribute("checkStockCarBeansSize", checkStockCarBeans.size());
		checkStockManageForm.setCheckStockBean(checkStockBean);
		checkStockManageForm.setCheckStockCarBeans(checkStockCarBeans);
		
		List<Image> list = service.showPic(checkstockId);
		request.setAttribute("list", list);
		
		initOption(request);
		return mapping.findForward("detailPage");
	}
	/**
	 * 初始化参数
	 * 
	 * @param request
	 */
	private void initOption(HttpServletRequest request) {
		int currRole=getCurrRole(request);
		if (currRole == RoleConstants.SUPERVISORY.getCode()) {
			//监管员监管经销商
			request.setAttribute("dealers", OptionUtil.getDealersBySUPERVISOR(UserSessionUtil.getUserSession(request).getUser().getClient_id()));
		}else{
			//所有经销商
			request.setAttribute("dealers", OptionUtil.getDealers());
		}
		//初始化车辆状态复选框
		List<OptionObject> statusOptions = OptionUtil.carStatusOptions();
		request.setAttribute("carStatusOptions", statusOptions);
		request.setAttribute("checkStockResults", OptionUtil.checkStockResults());
		request.setAttribute("checkStockCarActualstate", OptionUtil.checkStockCarActualstate());
		
	}
	
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private static int[] approvalRole = new int[]{
		RoleConstants.SUPERVISORY.getCode()
	};
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}

}
